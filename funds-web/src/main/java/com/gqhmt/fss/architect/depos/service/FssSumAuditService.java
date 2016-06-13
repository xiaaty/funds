package com.gqhmt.fss.architect.depos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.depos.entity.FssSumAuditEntity;
import com.gqhmt.fss.architect.depos.mapper.read.FssFinanceSumAuditReadMapper;
import com.gqhmt.fss.architect.depos.mapper.write.FssSumAuditWriteMapper;

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
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的财务汇总审核回盘文件 
	 */
	public List<FssSumAuditEntity> querySumAudit()throws FssException {
		return fssFinanceSumAuditReadMapper.selectAll();
	}
	
}
