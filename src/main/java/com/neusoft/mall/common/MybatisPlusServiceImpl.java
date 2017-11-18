package com.neusoft.mall.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.neusoft.mall.domain.PageRequest;
import com.neusoft.mall.domain.PageResponse;

/**
 * mybatis plus 的实现，主要重写了分页逻辑
 * 由于mybatis-plus 插件的分页有问题，之后所有的分页都调用这两个方法，不然就需要自己自定义 sql
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public class MybatisPlusServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 重写分页插件的逻辑，增加统计中记录条数
     *
     * @param page
     * @param wrapper
     * @return
     */
    public PageResponse<T> selectPage(PageRequest page, Wrapper<T> wrapper) {
        Page<T> p = new Page(page.getPage(), page.getSize());
        p.setAsc(false);
        p.setOpenSort(false);
        p = super.selectPage(p, wrapper);
        int total = this.selectCount(wrapper);
        p.setTotal(total);
        PageResponse<T> response = new PageResponse();
        response.setTotal(total);
        response.setPages(p.getPages())         // 总页数
                .setList(p.getRecords())        // 单页数据
                .setPage(page.getPage());       // 当前页
        response.setSize(page.getSize());       // 每页多少条数据
        return response;
    }

    /**
     * 重写分页插件的逻辑，增加统计中记录条数
     *
     * @param page
     * @return
     */
    public PageResponse<T> selectPage(PageRequest page) {
        return this.selectPage(page, new EntityWrapper());
    }
}
