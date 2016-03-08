package com.gqhmt.extServInter.dto;

/**
 * Filename:    com.gqhmt.extServInter.dto.PageSuperDto
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/8 21:18
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/8  于泳      1.0     1.0 Version
 */
public class PageSuperDto extends SuperDto {


    private Integer cpage;
    private Integer pageNum;

    public Integer getCpage() {
        return cpage;
    }

    public void setCpage(Integer cpage) {
        this.cpage = cpage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
