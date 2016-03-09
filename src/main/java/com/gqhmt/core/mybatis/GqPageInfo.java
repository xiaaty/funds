package com.gqhmt.core.mybatis;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.gqhmt.core.util.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.page
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/11/9 15:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/11/9  于泳      1.0     1.0 Version
 */
public class GqPageInfo<T> extends PageInfo {

    private JsonGenerator jsonGenerator = null;

    private ObjectMapper objectMapper = new ObjectMapper();


    public GqPageInfo(List list){
        super(list);
    }


    @JsonIgnore
    public String getJSON(){
        return JsonUtil.getInstance().getJson(getjsonMap());
    }

    private Map<String,String> getjsonMap(){
        Map<String,String> map = new HashMap<>();
        map.put("pageNum",String.valueOf(super.getPageNum()));
        map.put("pageSize",String.valueOf(super.getPageSize()));
        map.put("size",String.valueOf(super.getSize()));
        map.put("startRow",String.valueOf(super.getStartRow()));
        map.put("endRow",String.valueOf(super.getEndRow()));
        map.put("total",String.valueOf(super.getTotal()));
        map.put("pages",String.valueOf(super.getPages()));
        map.put("firstPage",String.valueOf(super.getFirstPage()));
        map.put("prePage",String.valueOf(super.getPrePage()));
        map.put("nextPage",String.valueOf(super.getNextPage()));
        map.put("lastPage",String.valueOf(super.getLastPage()));
        map.put("isFirstPage",String.valueOf(super.isIsFirstPage()));
        map.put("isLastPage",String.valueOf(super.isIsLastPage()));
        map.put("hasPreviousPage",String.valueOf(super.isHasPreviousPage()));
        map.put("hasNextPage",String.valueOf(super.isHasNextPage()));
        return map;
    }


}
