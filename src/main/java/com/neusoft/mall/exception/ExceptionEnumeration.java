package com.neusoft.mall.exception;

/**
 * LEVEL(1) + MODULE(3) + ERROR(3)
 * 级别+模块+错误信息：1001001
 * 异常枚举命名规范，模块名+错误信息描述：GarbageCategory(模块)NameIsExisted(错误信息描述)
 * 消息占位符 {n} 从 1 开始
 * <p>
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public enum ExceptionEnumeration {

    /*********************************************** 通用模块 start *******************************************/
    // NOT DEFINED ERROR
    CommonNotDefinedError(1000000, "未知异常，请联系管理员！"),
    CommonEmptyResult(1000001, "暂无数据！"),
    CommonInvalidToken(1000002, "无效token！"),
    CommonAccessClientInvalid(1000003, "没有权限访问！"),
    CommonRoleAccessForbidden(1000004, "当前角色无权限访问！"),
    CommonBadRequest(1000005, "请求异常，请检查数据格式是否正确！"),
    CommonCustomError(1000006, "{1}"),   // 这个错误消息自定义
    CommonMobileUnAuthenticated(1000007, "{1}，手机号码 {2} 未认证，{3}！"),
    CommonDataBaseException(1000008, "数据库异常，请联系管理员！"),
    CommonUnsupportedGrantTypeException(1000009, "不支持该 grant_type！"),
    CommonUnsupportedResponseTypeException(1000010, "不支持的返回格式！"),
    CommonUserDeniedAuthorizationException(1000011, "用户拒接授权！"),
    CommonTransactionRollbackException(1000012, "事务回滚异常！"),
    /*********************************************** 通用模块 end *******************************************/

    /*********************************************** 用戶模块 start *******************************************/
    UserAccountNumberIsExisted(1001001, "用戶名已存在"),
    UserIsNotFound(1001002, "找不到該用戶"),
    UserAccountOrPasswordIsNotCorrect(1001003, "用戶名或密碼不正確"),

    ;
    private int code;
    private String message;

    ExceptionEnumeration(int code) {
        this.code = code;
    }

    ExceptionEnumeration(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
