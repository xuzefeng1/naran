package com.naran.web.controller.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.naran.foundation.entity.ImageReturnValue;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;

/**
 * 文件处理控制器
 * 
 * @author zefeng.xu
 * @create 2017年01月14日
 */
@Controller
@RequestMapping("/api/file")
public class FileController extends BaseController {

    /**
     * 上传七牛
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadForQiniu(MultipartHttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
	// 1. build an iterator
	Iterator<String> itr = request.getFileNames();
	MultipartFile mpf = null;
	List<ImageReturnValue> values = new ArrayList<ImageReturnValue>();
	// 2. get each file
	while (itr.hasNext()) {
	    mpf = request.getFile(itr.next());
	    if (null != mpf) {
		try {
		    values.add(QiniuImageUtil.upload(mpf.getName(), mpf.getInputStream()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}

	if (values.size() > 0) {
	    String[] res = new String[values.size()];
	    int i = 0;
	    for (ImageReturnValue imageReturnValue : values) {
		res[i] = imageReturnValue.getResult();
		i++;
	    }
	    model.addAttribute("data", res);
	    writeAjaxJSONResponse(ResultMessageBuilder.build(model.asMap()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "上传失败！"), response);
    }

}