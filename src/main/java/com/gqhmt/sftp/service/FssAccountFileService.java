package com.gqhmt.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssAccountFileEntity;
import com.gqhmt.sftp.mapper.read.FssAccountFileReadMapper;
import com.gqhmt.sftp.mapper.write.FssAccountFileWriteMapper;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月12日
 * Description:
 * <p>P2P个人平台开户文件
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月12日 柯禹来      1.0     1.0 Version
 */
@Service
public class FssAccountFileService {
	@Resource
	private FssAccountFileReadMapper fssAccountFileReadMapper;
	@Resource
	private FssAccountFileWriteMapper fssAccountFileWriteMapper;
	
	
	public List<FssAccountFileEntity> queryItemsInfos(Map<String,String> map)throws FssException {
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("mchn",map.get("mchn"));
			map2.put("platformUsername", map.get("platformUsername"));
		}
		return fssAccountFileReadMapper.queryAccountFileList(map);
	}
	public List<FssAccountFileEntity> queryAccountFiles()throws FssException {
		return fssAccountFileReadMapper.selectAll();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月18日
	 * function：添加
	 */
	public void insertAccountFile(FssAccountFileEntity fileEntity){
		fssAccountFileWriteMapper.insertSelective(fileEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月18日
	 * function：创建开户文件对象并添加金数据库
	 */
	public void creatAccountFile(String registeredSeqNo,String platformUsername,
			String loginUsername,Integer age,String accName,String certType,String certNo,Integer sex,
			String mobile,String address,Integer userProperties,String registrationDate ,String thirdPartyPaymentId,String actionType,String remark) throws FssException{
		FssAccountFileEntity fileEntity=new FssAccountFileEntity();
		fileEntity.setMchn("0001000F0279762");
		fileEntity.setRegisteredSeqNo(registeredSeqNo);
		fileEntity.setPlatformUsername(platformUsername);
		fileEntity.setLoginUsername(loginUsername);
		fileEntity.setAge(age);
		fileEntity.setAccName(accName);
		fileEntity.setCertType(certType);
		fileEntity.setCertNo(certNo);
		fileEntity.setSex(sex);
		fileEntity.setMobile(mobile);
		fileEntity.setAddress(address);
		fileEntity.setUserProperties(userProperties);
		fileEntity.setRegistrationDate(registrationDate);
		fileEntity.setThirdPartyPaymentId(thirdPartyPaymentId);
		fileEntity.setActionType(actionType);
		fileEntity.setRemark(remark);
		this.insertAccountFile(fileEntity);
	}
}
