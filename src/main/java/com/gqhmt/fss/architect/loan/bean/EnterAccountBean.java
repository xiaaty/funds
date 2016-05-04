package com.gqhmt.fss.architect.loan.bean;



/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月15日
 * Description:	入账Bean
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月15日  jhz      1.0     1.0 Version
 */
public class EnterAccountBean {
	
		private Long id;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同
	    
		private int  count;		//该批总数
		
		private int  isSuccess;			//成功数
		
		private int  isFailed;			//失败数
		
	    private String tradeType  ;                               //交易类型    (NULL)           YES             (NULL)                   select,insert,update,references
	  
	    private String seqNo   ;  		//交易流水号
	    
	    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',

	    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
	  
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getTradeType() {
			return tradeType;
		}

		public void setTradeType(String tradeType) {
			this.tradeType = tradeType;
		}

		public String getMchnParent() {
			return mchnParent;
		}

		public void setMchnParent(String mchnParent) {
			this.mchnParent = mchnParent;
		}

		public String getMchnChild() {
			return mchnChild;
		}

		public void setMchnChild(String mchnChild) {
			this.mchnChild = mchnChild;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getSeqNo() {
			return seqNo;
		}

		public void setSeqNo(String seqNo) {
			this.seqNo = seqNo;
		}

		public int getIsSuccess() {
			return isSuccess;
		}

		public void setIsSuccess(int isSuccess) {
			this.isSuccess = isSuccess;
		}

		public int getIsFailed() {
			return isFailed;
		}

		public void setIsFailed(int isFailed) {
			this.isFailed = isFailed;
		}
		

		
}
