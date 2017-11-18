package com.neusoft.mall.common.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 所有 dao 都需要继承这个对象
 * Created by chenyingmiao on 17/10/30.
 *
 * @author chenyingmiao
 */
public interface BaseDAO<T, PK extends Serializable> extends BaseMapper<T> {

    /**
     * 按主键取记录
     *
     * @param primaryKey 主键值
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null
     */
    T selectPk(PK primaryKey);

    /**
     * 取全部记录
     *
     * @return 全部记录实体对象的List
     */
    List<T> select();

    /**
     * 按条件查询记录
     *
     * @param param 查询条件参数，包括WHERE条件、分页条件、排序条件
     * @return 符合条件记录的实体对象的List
     */
    List<T> selectParam(Map param);

    /**
     * 修改符合条件的记录
     * <p>此方法特别适合于一次性把多条记录的某些字段值设置为新值（定值）的情况，比如修改符合条件的记录的状态字段</p>
     * <p>此方法的另一个用途是把一条记录的个别字段的值修改为新值（定值），此时要把条件设置为该记录的主键</p>
     *
     * @param param 用于产生SQL的参数值，包括WHERE条件、目标字段和新值等
     * @return 修改的记录个数，用于判断修改是否成功
     */
    int updateParam(Map param);
}
