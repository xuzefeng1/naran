package com.naran.web.controller.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naran.core.entity.account.Account;
import com.naran.core.entity.other.Mail;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.other.IMailService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.StringUtil;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BasePageParam;
import com.naran.web.param.BaseParam;
import com.naran.web.param.other.MailParam;
import com.naran.web.vo.other.MailPageVO;
import com.naran.web.vo.other.MailVO;

/**
 * （地址）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/mail")
public class MailController extends BaseController {

    @Autowired
    private IMailService mailService;
    @Autowired
    private IAccountService accountService;

    /**
     * 地址列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/page")
    public void mailPage(BasePageParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	MailPageVO pageVO = new MailPageVO();
	// 加载
	Page<Mail> page = mailService.findMailByPage(current.getId(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setMails(new ListVO<MailVO>(page.getResult(), MailVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 获取常用地址
     * 
     */
    @RequestMapping(value = "/now")
    public void nowMail(BaseParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Mail mail = mailService.findMailByNowMail(current.getId());
	MailVO VO = new MailVO();
	VO.convertPOToVO(mail);
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 地址新增或修改
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public void change(MailParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (StringUtil.isBlank(param.getProvince()) || StringUtil.isBlank(param.getCity()) || StringUtil.isBlank(param.getCounty()) || StringUtil.isBlank(param.getMailAddress())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "请完善地址信息！"), response);
	    return;
	}
	if (StringUtil.isBlank(param.getMailPhone()) || StringUtil.isBlank(param.getMailName())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "请完善收件人信息！"), response);
	    return;
	}
	Mail mail = new Mail();
	BeanUtils.copyProperties(param, mail);
	mail.setAccountId(current.getId());
	if (param.getMailId() == null) {
	    mailService.addMail(mail);
	} else {
	    mail.setId(param.getMailId());
	    mailService.updateMail(mail);
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 地址设置为常用
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/change/now", method = RequestMethod.POST)
    public void changeNow(BaseParam param, Long mailId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Mail mail = new Mail();
	BeanUtils.copyProperties(param, mail);
	mail.setId(mailId);
	mail.setAccountId(current.getId());
	mail.setNowMail(Boolean.TRUE);
	mailService.updateMail(mail);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 地址删除
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(BaseParam param, Long mailId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	mailService.deleteMail(mailId, current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

}
