package com.gqhmt.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name="t_gq_fss_sys_menu")
public class Menu implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name = "menu_name")
    private String menuName;           // varchar(30) NOT NULL COMMENT '菜单名',

    @Column(name = "menu_url")
    private String menuUrl;            // varchar(200) NOT NULL COMMENT '菜单链接',

    @Column(name = "url_type")
    private int urlype;               // int(11) DEFAULT NULL COMMENT '重复菜单类别',

    @Column(name = "roles")
    private int roles;                  // int(11) NOT NULL DEFAULT '0' COMMENT '菜单对应权限',

    @Column(name = "parma")
    private String parma;               //varchar(300) DEFAULT NULL COMMENT '菜单对应参数，无参数不显示',

    @Column(name="parmaDefaule")
    private String parmaDefaule;

    @Column(name = "parent_id")
    private Long parent_id;             // bigint(20) NOT NULL DEFAULT '0' COMMENT '上级菜单',

    @Column(name = "sort")
    private Long sort;                  //int(11) NOT NULL COMMENT '排序',

    @Column(name = "create_time")
    private Date createTime;

    @Column(name="modify_time")
    private Date modifyTime;


    @Transient
    private List<Menu> list = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    public int getUrlype() {
        return urlype;
    }

    public void setUrlype(int urlype) {
        this.urlype = urlype;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public String getParma() {
        return parma;
    }

    public void setParma(String parma) {
        this.parma = parma;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }


    public List<Menu> getList() {
        return list;
    }
    public void addMenu(Menu menu){
        this.list.add(menu);
    }

    public String getParmaDefaule() {
        return parmaDefaule;
    }

    public void setParmaDefaule(String parmaDefaule) {
        this.parmaDefaule = parmaDefaule;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public boolean getIsChild(){
        return "#".equals(menuUrl.substring(0,1))?true:false;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
