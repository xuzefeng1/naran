package com.naran.bd.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 * @author zefeng.xu
 */
@Controller
public class CommonController extends BaseController {

	/**
	 * @param path 文件路径 共用跳转类
	 * @return
	 */
	@RequestMapping(value = "{path}")
	public String pageAddress(@PathVariable String path) {
		return path;
	}
	
	/**
	 * @param path 文件路径 共用跳转类
	 * @return
	 */
	@RequestMapping(value = "{path1}/{path2}")
	public String pathAddress(@PathVariable String path1, @PathVariable String path2) {
		return path1 + "/" + path2;
	}
	
	/**
	 * @param path 文件路径 共用跳转类
	 * @return
	 */
	@RequestMapping(value = "{path1}/{path2}/{path3}")
	public String pathAddress(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3) {
		return path1 + "/" + path2 + "/" + path3;
	}
	
}
