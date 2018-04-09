package com.naran.foundation.util.file.qiniu;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.naran.foundation.entity.ImageReturnValue;
import com.naran.foundation.util.StringUtil;
import com.naran.foundation.util.file.FtpUpload;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;

/**
 * 七牛图片处理工具类
 * 
 * @author QuCeng
 */
public class QiniuImageUtil {

    private final static UploadManager uploadManager = new UploadManager(); // 七牛封装的上传方法
									    // 无需改动

    public static InputStream isInputStream = null; // 压缩流

    public static URL url = null; // 静态url

    public static URLConnection con = null; // 连接网络

    /** 获取新图片路径的名字 */
    public static String getImageNew(String url) {
	String result = "";
	if (StringUtils.isNullOrEmpty(url)) {
	    return "";
	} else if (url.indexOf("/") != -1) {
	    result = url.substring(url.lastIndexOf("/") + 1, url.length());
	} else {
	    result = url;
	}
	result = result.replaceAll("-100", "");
	return result;
    }

    /** 修改图片网络路径 用来修改图片 显示的大小 此处输出的图片大小为60*60 */
    public static String newImageUrl60(String imageName) {
	if (imageName == null || imageName == "") {
	    return imageName;
	} else {
	    return CommonConfig.prefix + imageName + "-60";
	}
    }

    /** 修改图片网络路径 用来修改图片 显示的大小 此处输出的图片大小为100*100 */
    public static String newImageUrl100(String imageName) {
	if (imageName == null || imageName == "") {
	    return imageName;
	} else {
	    return CommonConfig.prefix + imageName + "-100";
	}
    }

    /** 修改图片网络路径 用来修改图片 显示的大小 此处输出的图片大小为250*250 */
    public static String newImageUrl250(String imageName) {
	if (imageName == null || imageName == "") {
	    return imageName;
	} else {
	    return CommonConfig.prefix + imageName + "-250";
	}
    }

    /** 修改图片网络路径 用来修改图片 显示的大小 此处输出的图片大小为300*300 */
    public static String newImageUrl300(String imageName) {
	if (imageName == null || imageName == "") {
	    return imageName;
	} else {
	    return CommonConfig.prefix + imageName + "-300";
	}
    }

    /** 修改图片网络路径 用来修改图片 显示的大小 原先图片大小为多少 就输出多少 */
    public static String newImageUrl(String imageName) {
	if (StringUtils.isNullOrEmpty(imageName)) {
	    return imageName;
	} else {
	    return CommonConfig.prefix + imageName;
	}
    }

    /** 单个拼接图片URL */
    public static String mosaic(String imageName) {
	return CommonConfig.prefix + imageName;
    }

    public static String newImageUrls(String nameStrs) {
	if (StringUtil.isBlank(nameStrs)) {
	    return null;
	}
	String[] names = nameStrs.split(StringUtil.DEFAULT_BOUND_SYMBOL);
	String[] allnames = new String[names.length];
	for (int i = 0; i < names.length; i++) {
	    allnames[i] = newImageUrl(names[i]);
	}
	return StringUtil.join(allnames, StringUtil.DEFAULT_BOUND_SYMBOL);
    }

    /**
     * 上传图片接口
     * 
     * @param suffix
     *            图片的后缀
     * @param inputStream
     *            上传流
     * @return
     * 
     *         转型为MultipartHttpRequest 注意 form 要加上 enctype="multipart/form-data"
     *         MultipartHttpServletRequest multipartRequest =
     *         (MultipartHttpServletRequest) request; 获得文件 MultipartFile file =
     *         multipartRequest.getFile("thefile"); if(file != null) { try {
     *         String result =
     *         UploadImage.upload(file.getName(),file.getInputStream()); if
     *         (result == "") { 上传不成功 } else { 上传成功 返回图片名字 不带路径 } } }
     */
    public static ImageReturnValue upload(final String suffix, final InputStream inputStream) {
	ImageReturnValue imageReturnValue = new ImageReturnValue();

	// 判断图片大小
	try {
	    if (inputStream.available() > 3072 * 1024) {
		imageReturnValue.setCode(false);
		imageReturnValue.setResult("图片大于3M 上传不成功");
		System.out.println("图片大于3M 上传不成功");
		return imageReturnValue;
	    }
	} catch (IOException e1) {
	    e1.printStackTrace();
	    imageReturnValue.setCode(false);
	    imageReturnValue.setResult("图片异常 换一张图片");
	    return imageReturnValue;
	}

	final String expectKey = String.valueOf(new Date().getTime()) + "_" + new Random().nextInt(1000) + getImageSuffixName(suffix);
	Thread thread = new Thread(new Runnable() {

	    @Override
	    public void run() {
		InputStream upInput = inputStream;
		StringMap params = new StringMap().put("x:foo", "foo_val");
		String token = CommonConfig.testAuth.uploadToken(CommonConfig.bucket, expectKey);
		for (int i = 0; i < 3; i++) {
		    try {
			if (upInput.available() > 1024 * 1024) {
			    upInput = compression(inputStream, getImageSuffixName(suffix));
			}
			uploadManager.put(getBytes(upInput), expectKey, token, params, null, false);
		    } catch (Exception e) {
			if (i < 3) {
			    continue;
			}
			// 三次上传失败后 上传到自己的服务器 备份
			FtpUpload.uploadFile(expectKey, upInput);
		    }

		    break;
		}

	    }
	});
	thread.start();

	imageReturnValue.setCode(true);
	imageReturnValue.setResult(expectKey);
	return imageReturnValue;
    }

    /** 获取图片后缀名 */
    public static String getImageSuffixName(String imageName) {
	String endName = ".jpg";
	if (!StringUtils.isNullOrEmpty(imageName) && imageName.contains(".")) {
	    endName = "." + imageName.split("\\.")[1];
	}
	return endName;
    }

    /** 压缩图片 */
    private static InputStream compression(InputStream inputStream, String name) throws IOException {
	name = name.replaceAll("\\.", "");
	Image img = ImageIO.read(inputStream);
	int width = 0;
	int height = 0;
	if (inputStream.available() > 2048 * 1024) {
	    width = img.getWidth(null) / 2;
	    height = img.getHeight(null) / 2;
	} else {
	    width = img.getWidth(null);
	    height = img.getHeight(null);
	}

	if (width > 3000 || height > 3000) {
	    int rate = 1;
	    if (width > height) {
		rate = width / 3000;
	    } else {
		rate = height / 3000;
	    }
	    width = width / rate;
	    height = height / rate;
	}

	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	image.getGraphics().drawImage(img, 0, 0, width, height, Color.white, null); // 绘制缩小后的图
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	if (StringUtils.isNullOrEmpty(name)) {
	    ImageIO.write(image, "png", os);
	} else {
	    ImageIO.write(image, name, os);
	}
	InputStream is = new ByteArrayInputStream(os.toByteArray());
	return is;
    }

    /** 读取字节流 */
    private static byte[] getBytes(InputStream inputStream) {
	byte[] buffer = null;
	try {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
	    byte[] b = new byte[1000];
	    int n;
	    while ((n = inputStream.read(b)) != -1) {
		bos.write(b, 0, n);
	    }
	    inputStream.close();
	    bos.close();
	    buffer = bos.toByteArray();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return buffer;
    }

    /** 下载网络图片 非7牛官方方法 */
    public static InputStream download(String urlString, String filename, String savePath) throws Exception {
	// 构造URL
	url = new URL(urlString);
	// 打开连接
	con = url.openConnection();
	// 设置请求超时为5s
	con.setConnectTimeout(5 * 1000);
	// 输入流
	isInputStream = con.getInputStream();
	return isInputStream;
    }

    public static String replaceUrl(String name) {
	if (StringUtil.isNotBlank(name)) {
	    name = name.replace(CommonConfig.prefix, "");
	}
	return name;
    }

}
