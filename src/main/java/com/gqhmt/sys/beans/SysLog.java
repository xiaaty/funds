package com.gqhmt.sys.beans;


import java.util.Date;


public class SysLog {
	private long id;
    private long userId;
	private Date loginTime;
	private String ip;
    private String loginName;
    private String department;
	public SysLog() {
	
	}
	
//	public SysLog(HttpServletRequest request,SysUsers sysUsers) {
//		this.ip=request.getRemoteAddr();
//		this.userId=sysUsers.getId();
//		this.loginName=sysUsers.getLoginName();
//		this.department=sysUsers.getDepartment();
//	}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginName() {
        return loginName == null?"":loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDepartment() {
        return department==null?"":department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
