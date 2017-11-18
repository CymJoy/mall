package com.neusoft.mall.domain;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.neusoft.mall.enums.SortEnum;
import com.neusoft.mall.utils.VerifyUtil;
import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页请求实体
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
@Data
public class PageRequest extends Page {

    private String sort;                // /api/v1/users?sort=create_time,desc;integral,desc

    public PageRequest() {
        this.page = 1;
        this.size = 20;
    }

    public PageRequest(int page, int size) {
        super(page, size);
    }

    public List<Sort> getSorts() {
        List<Sort> sorts = null;
        if (StringUtils.isEmpty(this.sort)) {
            return sorts;
        } else {
            sorts = Arrays.stream(this.sort.split(";"))
                    .filter(obj -> !StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.trim()))
                    .map(obj -> {
                        String[] sortSplit = obj.split(",");
                        String field = sortSplit[0];
                        String sort = sortSplit[1];
                        return new Sort(field, sort);
                    })
                    .collect(Collectors.toList());
        }
        return sorts;
    }

    /**
     * 获取排序包装，使用 mybatis plus 分页的时候需要
     *
     * @return
     */
    public EntityWrapper getSortWrapper() {
        List<Sort> sorts = this.getSorts();
        EntityWrapper wrapper = new EntityWrapper();
        if (VerifyUtil.isNotEmpty(sorts)) {
            for (Sort sort : sorts) {
                String direction = sort.getSort().toLowerCase();
                wrapper.orderBy(sort.getField(), ObjectUtils.nullSafeEquals(direction, "desc") ? false : true);
            }
        }
        return wrapper;
    }

    /**
     * 获取排序字符串，使用自定义 sql 的时候需要
     *
     * @return
     */
    public String getSortString() {
        List<Sort> sorts = this.getSorts();
        String orderBy = null;
        if (VerifyUtil.isNotEmpty(sorts)) {
            orderBy = sorts.stream()
                    .map(obj -> obj.getField() + " " + obj.getSort().toUpperCase())
                    .reduce((sort1, sort2) -> sort1 + "," + sort2
                    ).get();
        }
        return orderBy;
    }

    public PageRequest setSort(String column, SortEnum sort) {
        if (VerifyUtil.isEmpty(this.sort)) {
            this.sort = "";
        }
        this.sort += column + "," + sort.getValue() + ";";
        return this;
    }
}
