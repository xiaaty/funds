package com.gqhmt.fss.architect.holiday.mapper.read;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.holiday.entity.FssHolidayEntity;

import java.util.List;
import java.util.Map;

/**
 * 节假日读库操作
 *
 * @author ZhangLiang 2015年12月22日
 */
public interface FssHolidayReaderMapper extends ReadMapper<FssHolidayEntity>  {

//    FssHolidayEntity getById(@Param("id")int id);

    List<FssHolidayEntity> query(Map<String,Object> params);

//    int count(Map<String,Object> params);
}
