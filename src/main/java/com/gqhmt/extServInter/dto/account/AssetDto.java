package com.gqhmt.extServInter.dto.account;

import java.util.Date;

import javax.persistence.Column;

import com.gqhmt.extServInter.dto.SuperDto;

/**
 *资产信息接口---账户资产信息
 */
public class AssetDto extends SuperDto {
	    private Long id;                          
	    private String cust_no;                     
	    private String user_no;             
	    private String acc_no   ; 
	    private Date create_time;                        
	    private Date modify_time;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCust_no() {
			return cust_no;
		}
		public void setCust_no(String cust_no) {
			this.cust_no = cust_no;
		}
		public String getUser_no() {
			return user_no;
		}
		public void setUser_no(String user_no) {
			this.user_no = user_no;
		}
		public String getAcc_no() {
			return acc_no;
		}
		public void setAcc_no(String acc_no) {
			this.acc_no = acc_no;
		}
		public Date getCreate_time() {
			return create_time;
		}
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public Date getModify_time() {
			return modify_time;
		}
		public void setModify_time(Date modify_time) {
			this.modify_time = modify_time;
		}    
	    
}