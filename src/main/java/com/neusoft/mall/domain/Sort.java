package com.neusoft.mall.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 排序实体
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
@Data
public class Sort implements Serializable {

    private String field;

    private String sort;

    public Sort() {
    }

    public Sort(String field, String sort) {
        this.field = field;
        this.sort = sort;
    }
}
