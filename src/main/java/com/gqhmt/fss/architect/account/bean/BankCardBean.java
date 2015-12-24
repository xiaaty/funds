package com.gqhmt.fss.architect.account.bean;


/**
 * 银行卡信息查询数据传输对象
 * com.gq.p2p.account.Bean
 * @className FundsAccountBean<br/> 
 * @author yangfei.eng@gmail.com
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class BankCardBean implements java.io.Serializable{
		
		//客户编号
		private Integer custId;
		//客户编号
		private String customerName;
		
		//银行名称
		private String bankLongName;
		//银行简称
		private String bankSortName;
		//银行卡号
		private String bankNo;
		//手机号码
		private String phoneNo;
		//人银行卡 1 个人 2 公司
		private Integer isPersonalCard;
		//绑定银行卡编号
		private String cardIndex;
		
		private String firstFlg;
		
		private String createTime;
		
		private String endCreateTime;

		/**
		 * @return the custId
		 */
		public Integer getCustId() {
			return custId;
		}

		/**
		 * @param custId the custId to set
		 */
		public void setCustId(Integer custId) {
			this.custId = custId;
		}

		/**
		 * @return the bankLongName
		 */
		public String getBankLongName() {
			return bankLongName;
		}

		/**
		 * @param bankLongName the bankLongName to set
		 */
		public void setBankLongName(String bankLongName) {
			this.bankLongName = bankLongName;
		}

		/**
		 * @return the bankSortName
		 */
		public String getBankSortName() {
			return bankSortName;
		}

		/**
		 * @param bankSortName the bankSortName to set
		 */
		public void setBankSortName(String bankSortName) {
			this.bankSortName = bankSortName;
		}

		/**
		 * @return the bankNo
		 */
		public String getBankNo() {
			return bankNo;
		}

		/**
		 * @param bankNo the bankNo to set
		 */
		public void setBankNo(String bankNo) {
			this.bankNo = bankNo;
		}

		/**
		 * @return the isPersonalCard
		 */
		public Integer getIsPersonalCard() {
			return isPersonalCard;
		}

		/**
		 * @param isPersonalCard the isPersonalCard to set
		 */
		public void setIsPersonalCard(Integer isPersonalCard) {
			this.isPersonalCard = isPersonalCard;
		}

		/**
		 * @return the cardIndex
		 */
		public String getCardIndex() {
			return cardIndex;
		}

		/**
		 * @param cardIndex the cardIndex to set
		 */
		public void setCardIndex(String cardIndex) {
			this.cardIndex = cardIndex;
		}
		
		

		/**
		 * @return the customerName
		 */
		public String getCustomerName() {
			return customerName;
		}

		/**
		 * @param customerName the customerName to set
		 */
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		/**
		 * @return the phoneNo
		 */
		public String getPhoneNo() {
			return phoneNo;
		}

		/**
		 * @param phoneNo the phoneNo to set
		 */
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getFirstFlg() {
			return firstFlg;
		}

		public void setFirstFlg(String firstFlg) {
			this.firstFlg = firstFlg;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getEndCreateTime() {
			return endCreateTime;
		}

		public void setEndCreateTime(String endCreateTime) {
			this.endCreateTime = endCreateTime;
		}
		
}
