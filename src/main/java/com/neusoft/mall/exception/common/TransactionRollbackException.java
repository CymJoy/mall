package com.neusoft.mall.exception.common;

/**
 * 事务回滚异常
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class TransactionRollbackException extends CommonCustomException {

    private static final long serialVersionUID = 5383834525965985904L;

    public TransactionRollbackException() {
    }

    public TransactionRollbackException(String message) {
        super(message);
    }
}
