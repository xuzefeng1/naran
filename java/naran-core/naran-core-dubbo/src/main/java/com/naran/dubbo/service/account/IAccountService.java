package com.naran.dubbo.service.account;

import com.naran.core.entity.account.Account;
import com.naran.dubbo.response.DubboResponse;

/**
 * 平台账号服务接口
 * 
 * @author zefeng.xu
 */
public interface IAccountService {

    /**
     * 账号注册，发送验证码
     * 
     * @param mobile
     * @return
     */
    DubboResponse<String> sendVerifycodeByRegister(String phone);

    /**
     * 账号注册
     * 
     * @param deviceNo
     * @param mobile
     * @param password
     * @param cPassword
     * @param verifycode
     * @return
     */
    DubboResponse<Account> register(String deviceNo, String phone, String password, String cPassword, String verifycode);

    /**
     * 账号登录
     * 
     * @param deviceNo
     * @param accountNum
     * @param password
     * @return
     */
    DubboResponse<Account> login(String deviceNo, String phone, String password);

    /**
     * 账号登出
     * 
     * @param accessToken
     */
    void logout(Long accountId);

    /**
     * 忘记密码，发送验证码
     * 
     * @param mobile
     * @return
     */
    DubboResponse<String> sendVerifycodeByForgetPwd(String phone);

    /**
     * 忘记密码，重置（修改）密码
     * 
     * @param deviceNo
     * @param mobile
     * @param password
     * @param cPassword
     * @param verifycode
     * @return
     */
    DubboResponse<Account> forgetPwd(String deviceNo, String phone, String password, String cPassword, String verifycode);

    /**
     * 修改密码
     * 
     * @param accessToken
     * @param password
     * @param cPassword
     * @return
     */
    DubboResponse<Long> updatePwdBySuper(Long accountId, String password);

    /**
     * 获取当前登录用户
     * 
     * @param accessToken
     * @return
     */
    Account currentAccount(String accessToken);

    /**
     * 根据ID，获取用户
     * 
     * @param accountId
     * @return
     */
    Account getAccountById(Long accountId);

    /**
     * 更新用户
     * 
     * @return
     */
    DubboResponse<Long> updateAccount(Account account);

    /**
     * 根据手机号查询用户
     * 
     */
    Account findAccountByPhone(String phone);

    /**
     * 实名认证
     * 
     */
    DubboResponse<String> certification(Account account);

}
