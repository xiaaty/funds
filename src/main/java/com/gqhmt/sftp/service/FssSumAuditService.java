package com.gqhmt.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssSumAuditEntity;
import com.gqhmt.sftp.mapper.read.FssFinanceSumAuditReadMapper;
import com.gqhmt.sftp.mapper.write.FssSumAuditWriteMapper;

@Service
public class FssSumAuditService {
	
	@Resource
	private FssFinanceSumAuditReadMapper fssFinanceSumAuditReadMapper;
	@Resource
	private FssSumAuditWriteMapper fssSumAuditWriteMapper;
	
	public List<FssSumAuditEntity> querySumAuditList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("custNo",map.get("custNo"));
			map2.put("custName", map.get("custName"));
			map2.put("certNo", map.get("certNo"));
		}
		return fssFinanceSumAuditReadMapper.querySumAuditList(map2);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月16日
	 * function：批量插入
	 */
	public void createSumAudit(List<FssSumAuditEntity> sumAudits) throws FssException{
		fssSumAuditWriteMapper.insertList(sumAudits);
	}
	
}
