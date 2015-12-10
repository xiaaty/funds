package com.gqhmt.sys.beans;

import java.io.Serializable;
import java.util.Date;

public class LogSearch  {
	public String getLoginTime1() {
		return loginTime1;
	}
	public void setLoginTime1(String loginTime1) {
		this.loginTime1 = loginTime1;
	}
	public String getLoginTime2() {
		return loginTime2;
	}
	public void setLoginTime2(String loginTime2) {
		this.loginTime2 = loginTime2;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	private String loginTime1;
	private String loginTime2;
	private String loginName;
	private String department;
}
