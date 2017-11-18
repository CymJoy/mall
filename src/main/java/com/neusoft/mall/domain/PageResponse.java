package com.neusoft.mall.domain;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页的返回对象
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
@Data
public class PageResponse<T> extends Page {

    /**
     * 总条数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 返回数据
     */
    private List<T> list;

    /**
     * 对象的 json 字符串，只在下面 convertList 和 convertPage 中使用到
     */
    @JsonIgnore
    private String json;

    public PageResponse() {
    }

    /**
     * 设配器对象，将分页插件的分页对象转成接口返回需要的对象
     *
     * @param pageInfo
     */
    public PageResponse(PageInfo<T> pageInfo) {
        super(pageInfo.getPageNum(), pageInfo.getSize());
        this.setTotal(pageInfo.getTotal());
        this.setPages(pageInfo.getPages());
        this.setList(pageInfo.getList());
    }

    public long getTotal() {
        return total;
    }

    public PageResponse setTotal(long total) {
        this.total = total;
        return this;
    }

    public int getPages() {
        return pages;
    }

    public PageResponse setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public PageResponse setList(List<T> list) {
        this.list = list;
        return this;
    }

    /**
     * 将实体对象转成 dto，或者其它数据类型，需要配合下面的 convertPage 一起使用
     * DTO 需要实现 Serializable
     * demo:
     * PageResponse<Community> pageList = this.selectPage(page, wrapper);
     * PageResponse<CommunityDTO> result = pageList.convertList(CommunityDTO::copyProperties).convertPage(PageResponse.class);
     *
     * @param mapper
     * @return
     */
    public PageResponse<T> convertList(Function<? super T, ? extends Serializable> mapper) {
        this.setList((List) this.getList().stream().map(mapper).collect(Collectors.toList()));
        this.json = JSONObject.toJSONString(this);
        return this;
    }

    /**
     * 转换分页对象
     * demo:
     * PageResponse<Community> pageList = this.selectPage(page, wrapper);
     * PageResponse<CommunityDTO> result = pageList.convertList(CommunityDTO::copyProperties).convertPage(PageResponse.class);
     *
     * @param classs
     * @param <T>
     * @return
     */
    public <T> T convertPage(Class<T> classs) {
        return JSONObject.parseObject(json, classs);
    }
}
