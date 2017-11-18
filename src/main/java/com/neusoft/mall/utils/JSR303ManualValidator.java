package com.neusoft.mall.utils;


import com.neusoft.mall.exception.ExceptionEnumeration;
import com.neusoft.mall.exception.common.RestBadRequestException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 自定义JSR303手动验证器工具类
 * Created by chenyingmiao on 11/7/2017 AD.
 *
 * @author chenyingmiao
 */
public class JSR303ManualValidator<T> {

    private T _t;

    private boolean hasException = false;

    private String message;

    private JSR303ManualValidator(T t) {
        this._t = t;
    }

    public static <T> JSR303ManualValidator<T> of(T t) {
        return new JSR303ManualValidator(t);
    }

    /**
     * 验证
     *
     * @return
     */
    public JSR303ManualValidator<T> validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> errors = validator.validate(this._t);
        errors.forEach(e -> this.message = e.getMessage());
        if (errors.size() > 0) {
            this.hasException = true;
        }
        return this;
    }

    /**
     * 如果有问题就抛异常
     */
    public void throwRestBadRequestExceptionCheck() {
        if (this.hasException && VerifyUtil.isNotEmpty(this.message)) {
            throw new RestBadRequestException(ExceptionEnumeration.CommonCustomError, this.message);
        }
    }

    public boolean isHasException() {
        return hasException;
    }

    public String getMessage() {
        return message;
    }
}
