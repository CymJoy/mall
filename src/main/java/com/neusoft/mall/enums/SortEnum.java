package com.neusoft.mall.enums;

/**
 * Created by chenyingmiao on 17/10/19.
 *
 * @author chenyingmiao
 */
public enum SortEnum {

    /**
     * 降序
     */
    DESC("DESC"),

    /**
     * 升序
     */
    ASC("ASC"),

    ;

    private String value;

    SortEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
