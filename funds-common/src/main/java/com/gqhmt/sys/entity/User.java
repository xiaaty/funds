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
@Table(name="t_sys_users")
public class User implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                // int(11) NOT NULL AUTO_INCREMENT,

    @Column(name = "login_name")
    private String loginName;                      // varchar(20) NOT NULL DEFAULT '',

    @Column(name="user_name")
    private String userName;                       // varchar(20) NOT NULL DEFAULT '',


    @Column(name="password")
    private String password;                        //` varchar(32) NOT NULL DEFAULT '',

    @Column(name = "employee_no")
    private String employeeNo;                     // varchar(30) NOT NULL DEFAULT '',
    @Column(name="sex")
    private Integer sex;                            // int(11) NOT NULL DEFAULT '0',

    @Column(name = "department")
    private String department;                      // varchar(20) NOT NULL DEFAULT '',

    @Column(name="description")
    private String description;                     // varchar(100) NOT NULL DEFAULT '',

    @Column(name="status")
    private Integer status;                         // int(11) NOT NULL DEFAULT '0',

    @Column(name="is_del")
    private int isDel;                             // int(11) NOT NULL DEFAULT '1',


    @Column(name="create_id")
    private Long createId;                         // int(11) NOT NULL DEFAULT '0',

    @Column(name="create_time")
    private Date createTime;                       //` datetime DEFAULT NULL,

    @Column(name="modify_id") private Long modifyId;                         // int(11) NOT NULL DEFAULT '0',
    private Date modify_date;                       // datetime DEFAULT NULL,

    @Column(name="user_tel")
    private String userTel;                        // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',

    @Column(name="company")
    private String  company;                        // varchar(50) DEFAULT NULL,

    @Column(name="leader")
    private String leader;                          //` varchar(50) DEFAULT NULL,

    @Column(name="fuiouFtp")
    private String job;                             //` varchar(20) DEFAULT NULL,

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Column(name="dept_code")
    private String deptCode;                       //` varchar(20) DEFAULT NULL,
}
