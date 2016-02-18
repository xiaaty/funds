package com.gqhmt.util;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CustomerConstants {

	
	public static Map<String,String> codeMap=new ConcurrentHashMap<String,String>();

    public static Map<String,String> actionMap=new LinkedHashMap<String,String>();
    
	
    public static final String CACHE_FUIOU_AREA_LIST = "fuiouAreaList";
    public static final String CACHE_FUIOU_AREA_CODE = "fuiouAreaCode";
    public static final String CACHE_FUIOU_AREA_VALUE = "fuiouAreaValue";
    public static final String CACHE_FUIOU_BANK_LIST = "fuiouBankList";
    public static final String CACHE_FUIOU_BANK_CODE = "fuiouBankCode";
    public static final String CACHE_FUIOU_BANK_VALUE = "fuiouBankValue";
    
    //批处理用富友短信更改模式
    public static final String CACHE_FUIOU_SEND_MSG_MAP = "cacheFuiouSendmsgMap";

	
	static{

        codeMap.put("0000","文件上传成功");
		codeMap.put("0001","没有文件");
		codeMap.put("0002","上传文件失败");
		codeMap.put("0003","上传文件成功");
		codeMap.put("0004","文件格式不正确");
		codeMap.put("0005","文件夹不存在");
		codeMap.put("0006","写文件失败");
        codeMap.put("0007","数据库操作失败");
        
        codeMap.put("0012","导入失败，身份证号已经存在！");
        codeMap.put("0013","导入失败，手机号码已经存在！");
        codeMap.put("0014","导入失败，银行卡号已经存在！");
        codeMap.put("0015","导入失败，富友地区代码不存在！");
        codeMap.put("0016","导入失败，富友银行代码不存在！");
        codeMap.put("0020","创建账户失败！");
        codeMap.put("0021","创建账户密码失败！");
        
		
		

        //

        actionMap.put("0000","成功");
        actionMap.put("0001","失败");
        actionMap.put("0002","数据库操作失败");
        actionMap.put("0003","匹账错误，查账只能匹配一条流水记录");
        actionMap.put("0004","匹账错误，流水匹配金额不能小于合同未匹配余额");
        actionMap.put("0005","匹账错误，流水匹配金额与合同未匹配金额不等");
        actionMap.put("0008","合同不存在");
        actionMap.put("0009","添加失败，合同编号已经存在！");
        actionMap.put("9998","参数错误");
        actionMap.put("9999","未知错误");
        
        actionMap.put("0012","添加失败，身份证号已经存在！");
        actionMap.put("0013","添加失败，手机号码已经存在！");
        actionMap.put("0014","修改失败，身份证号已经存在！");
        actionMap.put("0015","修改失败，手机号码已经存在！");
        actionMap.put("0016","添加失败，开户行地区不存在！");
        actionMap.put("0017","添加失败，开户行行别不存在！");
        actionMap.put("0018","修改失败，开户行地区不存在！");
        actionMap.put("0019","修改失败，开户行行别不存在！");
        actionMap.put("0020","修改失败，不能与原银行卡号相同！");
        actionMap.put("0021","修改失败，查询绑定银行卡失败！");

	}
}
