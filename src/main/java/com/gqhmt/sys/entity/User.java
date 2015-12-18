package com.gqhmt.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.sys.entity.User
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/18 18:18
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/18  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_sys_user")
public class User implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                // bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name="login_name")
    private String loginName;                      // varchar(20) NOT NULL COMMENT '登陆名',

    @Column(name="password")
    private String password;                        // varchar(40) NOT NULL COMMENT '密码',

    @Column(name="user_name")
    private String userName;                       // varchar(300) NOT NULL COMMENT '姓名',

    @Column(name="role_id")
    private Long   roleId;                         // bigint(20) DEFAULT NULL COMMENT '权限组id',

    @Column(name="state")
    private int state;                              // int(11) DEFAULT NULL COMMENT '员工状态，1试用期，2正式员工，离职员工',

    @Column(name="dept")
    private int dept;                               // int(11) DEFAULT NULL,


    @Column(name="company")
    private int company;                            // int(11) DEFAULT NULL COMMENT '所属公司',

    @Column(name="area")
    private int area;                               //         `area` int(11) DEFAULT NULL COMMENT '所属大区',

    @Column(name="leader")
    private String leader;                       // varchar(100) DEFAULT NULL COMMENT '上级',

    @Column(name="create_time")
    private Date createTime;                       // datetime NOT NULL COMMENT '创建时间',

    @Column(name="modify_time")
    private Date modifyTime;                       // datetime DEFAULT NULL COMMENT '最后修改时间',

    @Column(name="create_user_id")
    private Long createUserId;                    // bigint(20) DEFAULT NULL COMMENT '创建人',

    @Column(name="modify_user_id")
    private Long modifyUserId;                    // bigint(20) DEFAULT NULL COMMENT '最后修改人',


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}
