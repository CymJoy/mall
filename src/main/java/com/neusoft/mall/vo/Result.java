package com.neusoft.mall.vo;

import lombok.Data;

/**
 * controller 统一返回的数据格式
 * Created by chenyingmiao on 17/10/30 AD.
 */
@Data
public class Result<T> {

    private Integer status;         // 错误码

    private T data;                 // 结果

    public Result() {
    }

    public Result(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
