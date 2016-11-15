package com.gqhmt.fss.architect.accounting.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Repository
public interface FssCheckDateReadMapper extends ReadMapper<FssCheckDate> {
    /**
     * jhz
     * 获得订单表跑批日期
     * @return
     */
    public FssCheckDate getOrderDate();
    /**
     * jhz
     * 获得订单表跑批日期
     * @return
     */
    public int selectOrderDate(@Param("orderDate")String orderDate);

    public FssCheckDate queryOrderDate();

    public List<FssCheckDate> selectFssCheckDateList(Map map);
}
