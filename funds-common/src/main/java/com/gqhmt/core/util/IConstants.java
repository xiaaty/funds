package com.gqhmt.core.util;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class IConstants {
	//出借合同签署地
		public static final String AGREEMENTS_ADDRESS = "北京";
		//证件类型
		public static final String DOCUMENT_TYPE = "身份证";
		//公司名称
		public static final String COMPANY_NAME = "冠群驰骋投资管理（北京）有限公司";
		//公司地址
		public static final String COMPANY_ADDRESS = "北京市东城区东直门南大街3号国华投资大厦5层";
		//担保机构
		public static final String GUARANTEE_INSTITUTIONS = "冠群中誉投资担保（北京）有限公司";
		//担保机构地址
		public static final String GUARANTEE_INSTITUTIONS_ADDRESS = "北京市朝阳区工人体育场北路8号院2楼18层03-2103";
		//罚息31天以内
		public static final String DEFAULT_INTEREST_1 = "6%";
		//罚息31天以上
		public static final String DEFAULT_INTEREST_2 = "8%";
		//提前还款违约率
		public static final String DEFAULT_EARLY_REPAYMENT = "5%";
		/**
		 * 合同文件存放地址
		 */
		public static final String filePath = "/GQGETfiles/p2pContract/file/";
		
		/**
		 * 附件上传存储路径
		 */
		public static final String OriginalFilePath = "/GQGETfiles/p2pContract/file/Original/";
		public static final String OmitGraphFilePath = "/GQGETfiles/p2pContract/file/OmitGraph/";

		public static final String CardImageFilePath = "/GQGETfiles/cardImage/";
		public static final String BankImageFilePath = "/GQGETfiles/bank/";
		
		public static final int FRONT_END_SHOW_SIGN = 99;			//前端显示图片

		public static Map<Integer,String> property = new ConcurrentSkipListMap<>();
		public static Map<Integer,String> creditCase = new ConcurrentSkipListMap<>();				//	credit_case  征信情况
		public static Map<Integer,String> lawsuitCase = new ConcurrentSkipListMap<>();				//	lawsuit_case 涉诉情况

		static{
			creditCase.put(0,"无征信");
			creditCase.put(1,"当前未查询到不良征信记录");
			creditCase.put(2,"当前未查询到恶意逾期征信记录");
			creditCase.put(3,"当前未查询到严重逾期征信记录");
			creditCase.put(99,"其他");

			lawsuitCase.put(0,"当期未查询到执行记录");
			lawsuitCase.put(1,"当前未查询到执行记录");
			lawsuitCase.put(2,"当前有执行记录，影响程度有限");
			lawsuitCase.put(99,"其他");

//			property.put(0,"无资产抵押");
			property.put(1,"有房");
			property.put(2,"有车");
			property.put(3,"有车有房");
			property.put(99,"其他");
		}


}
