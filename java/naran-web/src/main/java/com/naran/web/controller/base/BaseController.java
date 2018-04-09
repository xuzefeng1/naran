package com.naran.web.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.naran.foundation.entity.ImageReturnValue;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 基础控制器，用于被继承
 * 
 * @author zefeng.xu
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param response
     */
    protected PrintWriter getWriter(HttpServletResponse response) {
	if (response == null)
	    return null;

	PrintWriter writer = null;
	try {
	    writer = response.getWriter();
	} catch (Exception e) {
	    logger.error("unknow exception", e);
	}

	return writer;
    }

    /**
     * @param returnResult
     * @param response
     */
    protected void writeAjaxResponse(String returnResult, HttpServletResponse response) {
	PrintWriter writer = getWriter(response);
	if (writer == null || returnResult == null) {
	    return;
	}
	try {
	    writer.write(returnResult);
	} finally {
	    writer.flush();
	    writer.close();
	}
    }

    /**
     * 
     * @param responseObj
     * @param writer
     */
    public void writeAjaxJSONResponse(Object responseObj, PrintWriter writer) {
	if (writer == null || responseObj == null) {
	    return;
	}
	try {
	    writer.write(JSON.toJSONString(responseObj, new SerializerFeature[] { SerializerFeature.DisableCircularReferenceDetect }));
	} finally {
	    writer.flush();
	    writer.close();
	}
    }

    /**
     * @param responseObj
     * @param response
     */
    public void writeAjaxJSONResponse(Object responseObj, HttpServletResponse response) {
	response.setContentType("application/json; charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
										    // 1.1
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setDateHeader("Expires", 0); // Proxies.

	PrintWriter writer = getWriter(response);
	writeAjaxJSONResponse(responseObj, writer);
    }

    /**
     * 图片上传（单个）
     * 
     * @param request
     * @param imageName
     * @return
     */
    public ImageReturnValue uploadImage(HttpServletRequest request, String imageName) {
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	MultipartFile file = multipartRequest.getFile(imageName);

	ImageReturnValue value = new ImageReturnValue();
	if (null != file) {
	    try {
		value = QiniuImageUtil.upload(file.getName(), file.getInputStream());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	return value;
    }

    /**
     * 图片上传（批量）
     * 
     * @param request
     * @param imageName
     * @return
     */
    public List<ImageReturnValue> uploadImages(HttpServletRequest request, String imageName) {
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	List<MultipartFile> files = multipartRequest.getFiles(imageName);
	List<ImageReturnValue> values = new ArrayList<ImageReturnValue>();

	if (CollectionUtils.isNotEmpty(files)) {
	    for (MultipartFile file : files) {
		try {
		    values.add(QiniuImageUtil.upload(file.getName(), file.getInputStream()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}

	return values;
    }

}
