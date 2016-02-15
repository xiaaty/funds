package com.gqhmt.sys.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Filename:    com.gqhmt.sys.entity.Menu
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/18 18:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/18  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_dict_order")
public class DictOrderEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
  	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "order_name")
    private String orderName;           
    
    @Column(name = "order_dict")
    private String orderDict;   
    
    @Column(name = "order_list")
    private String orderList;
    
    @Column(name = "memo")
    private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderDict() {
		return orderDict;
	}

	public void setOrderDict(String orderDict) {
		this.orderDict = orderDict;
	}

	public String getOrderList() {
		return orderList;
	}

	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
