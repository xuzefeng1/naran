package com.naran.web.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.naran.core.entity.account.Account;
import com.naran.core.enums.CertificationType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.StringUtil;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BaseParam;
import com.naran.web.param.account.AccountParam;
import com.naran.web.param.account.CertificationParam;
import com.naran.web.station.interceptor.AccountAuthCenter;
import com.naran.web.vo.account.AccountVO;

/**
 * （账号）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/account")
public class AccountController extends BaseController {

    @Autowired
    private IAccountService accountService;

    /**
     * 注册
     * 
     * @param mobile
     * @param password
     * @param verifycode
     * @param request
     * @param response
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(String mobile, String password, String cPassword, String verifycode, HttpServletRequest request, HttpServletResponse response) {
	DubboResponse<Account> dResponse = accountService.register(null, mobile, password, cPassword, verifycode);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}

	AccountVO accountVO = new AccountVO();
	accountVO.convertPOToVO(dResponse.getData());

	// 正确响应
	writeAjaxJSONResponse(ResultMessageBuilder.build(accountVO), response);
    }

    /**
     * 注册-获取验证码
     * 
     * @param mobile
     * @param request
     * @param response
     */
    @RequestMapping(value = "/register/verifycode", method = RequestMethod.POST)
    public void getVerifycodeByRegister(String mobile, @RequestParam(value = "isTest", required = false) Boolean isTest, HttpServletRequest request, HttpServletResponse response) {
	DubboResponse<String> dResponse = accountService.sendVerifycodeByRegister(mobile);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	// 正确响应
	if (isTest != null && isTest) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE, dResponse.getData()), response);
	} else {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
	}

    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String accountNum, String password, String deviceNo, HttpServletRequest request, HttpServletResponse response) {

	DubboResponse<Account> dResponse = accountService.login(deviceNo, accountNum, password);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	Account account = dResponse.getData();
	if (null == account) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "用户名或密码错误！"), response);
	    return;
	}
	// 封装返回
	AccountVO accountVO = new AccountVO();
	accountVO.convertPOToVO(account);

	// session当前登录员工
	AccountAuthCenter.sessionAccount(request, dResponse.getData());
	// 正确响应
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE, "登陆成功！", accountVO), response);
    }

    /**
     * 登出
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(BaseParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	accountService.logout(current.getId());
	AccountAuthCenter.sessionOut(request);

	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE, "登出成功！"), response);
    }

    /**
     * 实名认证
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/certification", method = RequestMethod.POST)
    public void certification(CertificationParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (CertificationType.SUCCESS.equals(current.getCertificationType())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "该账号已实名认证！"), response);
	    return;
	}
	if (StringUtil.isBlank(param.getCardOpposite()) || StringUtil.isBlank(param.getCardPositive()) || StringUtil.isBlank(param.getIdentityCard())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "请完善身份证信息！"), response);
	    return;
	}
	BeanUtils.copyProperties(param, current);
	current.setCertificationType(CertificationType.SUCCESS.name());
	accountService.certification(current);

	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE, "实名认证成功！"), response);
    }

    /**
     * 获取用户信息
     * 
     */
    @RequestMapping(value = "/my", method = RequestMethod.POST)
    public void my(BaseParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	AccountVO accountVO = new AccountVO();
	accountVO.convertPOToVO(current);
	writeAjaxJSONResponse(ResultMessageBuilder.build(accountVO), response);
    }

    /**
     * 修改信息
     * 
     * @param param
     */
    @RequestMapping(value = "/update/my", method = RequestMethod.POST)
    public void updateAccount(AccountParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	BeanUtils.copyProperties(param, current);
	accountService.updateAccount(current);
	current = accountService.currentAccount(param.getAccessToken());
	AccountVO accountVO = new AccountVO();
	accountVO.convertPOToVO(current);
	writeAjaxJSONResponse(ResultMessageBuilder.build(accountVO), response);
    }

    /**
     * 修改头像信息
     *
     * @param param
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update/my/photo", method = RequestMethod.POST)
    public void updatePhoto(BaseParam param, String photo, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	current.setPhoto(photo);
	accountService.updateAccount(current);
	AccountVO accountVO = new AccountVO();
	accountVO.convertPOToVO(current);
	writeAjaxJSONResponse(ResultMessageBuilder.build(accountVO), response);
    }

}
