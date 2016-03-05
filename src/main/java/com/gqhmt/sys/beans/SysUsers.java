package com.gqhmt.sys.beans;

import java.util.Date;

import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.util.StringUtils;

public class SysUsers {
	private String id;
    private long createId;
    private long modifyId;
    private int sex;
    private int status;
    private int isDel;
	private String userName;
    private String loginName;
    private String password;
    private String roleIds;
    private String department;
    private String description;
    private String employeeNo;
    private String userTel;
    private String leader;
    private String job;
    private String company;
    private String addLoginName;
    private String addPassword;
	private Date createTime;
    private Date modifyDate;

    public String getUserName() {
        return userName==null?"":userName.trim();
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getLoginName() {
        return loginName==null?getAddLoginName():loginName.trim();
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return password==null?getAddPassword():password.trim();
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoleIds() {
        return roleIds==null?"":roleIds.trim();
    }
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
    public String getDepartment() {
        return department==null?"":department.trim();
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDescription() {
        return description==null?"":description.trim();
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreateTime() {
        return createTime==null?new Date():createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyDate() {
        return modifyDate==null?new Date():modifyDate;
    }
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    public String getEmployeeNo() {
        return employeeNo==null?"":employeeNo.trim();
    }
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    public String getUserTel() {
        return userTel==null?"":userTel.trim();
    }
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    public String getLeader() {
        return leader==null?"":leader.trim();
    }
    public void setLeader(String leader) {
        this.leader = leader;
    }
    public String getJob() {
        return job==null?"":job.trim();
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getCompany() {
        return company==null?"":company.trim();
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getAddLoginName() {
        return addLoginName==null?"":addLoginName.trim();
    }
    public String getAddPassword() {
        return addPassword==null?"":addPassword.trim();
    }
    public void setAddLoginName(String addLoginName) {
        this.addLoginName = addLoginName;
    }
    public void setAddPassword(String addPassword) {
        this.addPassword = addPassword;
    }
    public String getSexName(){
        if(GlobalConstants.empSexMap.containsKey(sex))
            return GlobalConstants.empSexMap.get(sex);
        return "";
    }

    public long getRoleId(){
        if(StringUtils.isEmpty(roleIds))
            return 0;
        for(String rid:roleIds.split(",")){
            rid=rid.trim();
            if(rid.length()>0)
                return Long.parseLong(rid);
        }
        return 0;
    }

    public String getNickName(){
        String name= userName.substring(0,1);
        if(name.matches("[\u4E00-\u9FA5]+")){
            return name+(sex==1?"先生":"女士");
        }
        return userName;
    }

    public String getRoleName(){
        if(StringUtils.isEmpty(roleIds))
            return "";
        StringBuffer sb=new StringBuffer();
        for(String s:roleIds.split(",")){
            if(StringUtils.isNotEmptyString(s)){
                sb.append(GlobalConstants.roleMap.get(Long.parseLong(s))).append(",");
            }
        }
        return sb.substring(0,sb.length()-1).toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public long getModifyId() {
        return modifyId;
    }

    public void setModifyId(long modifyId) {
        this.modifyId = modifyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex(){
        return this.sex;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
