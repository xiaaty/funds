package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.extServInter.dto.SuperDto;
/**
 *资产信息接口---账户资产信息
 */
public class AssetDto extends SuperDto {
	    private Long id;                          
	    private String cust_no;                     
	    private String user_no;             
	    private String acc_no   ; 
	    private String busi_no   ; 
	    
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
		public String getBusi_no() {
			return busi_no;
		}
		public void setBusi_no(String busi_no) {
			this.busi_no = busi_no;
		}
	    
}