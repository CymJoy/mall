package com.neusoft.mall.domain;

import lombok.Data;

/**
 * 分页基类
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
@Data
public class Page {

    protected int page;           // 当前页

    protected int size;           // 每页大小

    public Page() {
    }

    public Page(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
