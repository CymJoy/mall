package com.neusoft.mall.common.base;

import com.neusoft.mall.common.config.RouteConstants;
import com.neusoft.mall.domain.PageRequest;
import com.neusoft.mall.domain.PageResponse;
import com.neusoft.mall.vo.ResponseResult;
import com.neusoft.mall.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用的 API 接口
 * 默认实现的接口，实现类不一定要实现，视情况而定是否需要实现
 * 目的是统一命名、返回数据格式、入参格式
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
@RequestMapping(RouteConstants.PREFIX)
public interface BaseController<T, PK> {

    /**
     * 查询接口，根据 response 来决定返回的数据格式
     *
     * @param page     分页对象
     * @param param    查询条件
     * @param response list: 返回 list 数据结构; page: 分页返回
     * @return
     */
//    @GetMapping
    default ResponseEntity query(PageRequest page, T param, @RequestParam(required = false, defaultValue = "page") String response) {
        if (ObjectUtils.nullSafeEquals("page", response)) {
            // 分页查询
            return this.queryByPage(page, param);
        } else {
            return this.list(param);
        }
    }

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询的条件
     * @return
     */
    default ResponseEntity<Result<PageResponse<T>>> queryByPage(PageRequest page, T param) {
        return new ResponseResult<PageResponse<T>>().success();
    }

    /**
     * 查询所有
     *
     * @param param 查询的条件
     * @return
     */
    default ResponseEntity<Result<List<T>>> list(T param) {
        return new ResponseResult<List<T>>().success();
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
//    @GetMapping("/{id}")
    default ResponseEntity<Result<T>> queryById(@PathVariable("id") PK id) {
        return new ResponseResult<T>().success();
    }

    /**
     * 根据主键删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    default ResponseEntity<Result<Object>> delete(@PathVariable("id") PK id) {
        return new ResponseResult<>().success();
    }

    /**
     * 增加数据
     *
     * @param object
     * @return
     */
    @PostMapping
    default ResponseEntity<Result<T>> create(@RequestBody T object, BindingResult bindingResult) {
        return new ResponseResult<T>().success();
    }


    /**
     * 批量增加数据
     *
     * @param object
     * @return
     */
    @PostMapping("/batch")
    default ResponseEntity<Result<List<T>>> batchCreate(@RequestBody List<T> object) {
        return new ResponseResult<List<T>>().success();
    }

    /**
     * 更新数据
     *
     * @param object
     * @return
     */
    @PutMapping("/{id}")
    default ResponseEntity<Result<Object>> update(@PathVariable("id") PK id, @RequestBody T object, BindingResult bindingResult) {
        return new ResponseResult<>().success();
    }
}
