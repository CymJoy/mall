package com.neusoft.mall.common.base;


import com.neusoft.mall.domain.PageRequest;
import com.neusoft.mall.domain.PageResponse;

import java.util.List;

/**
 * 基础业务逻辑接口
 * 默认实现的接口，实现类不一定要实现，视情况而定是否需要实现
 * 目的是统一命名、返回数据格式、入参格式
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public interface BaseService<T, PK> {

    /**
     * 分页查询
     *
     * @param page
     * @param param
     * @return
     */
    default PageResponse<T> findByPage(PageRequest page, T param) {
        return null;
    }

    /**
     * 获取所有
     *
     * @return
     */
    default List<T> findAll() {
        return null;
    }

    /**
     * 通过参数获取
     *
     * @return
     */
    default List<T> findByParam(T param) {
        return null;
    }

    /**
     * 通过 id 获取数据
     *
     * @param id
     * @return
     */
    default T findById(PK id) {
        return null;
    }

    /**
     * 通过多个 id 获取数据
     *
     * @param ids
     * @return
     */
    default List<T> findByIds(List<PK> ids) {
        return null;
    }

    /**
     * 添加
     *
     * @param obj
     * @return
     */
    default T add(T obj) {
        return null;
    }

    /**
     * 添加
     *
     * @param list
     * @return
     */
    default List<T> batchAdd(List<T> list) {
        return null;
    }

    /**
     * 更新
     *
     * @param obj
     * @return
     */
    default void update(T obj) {
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    default void removeById(PK id) {
    }
}
