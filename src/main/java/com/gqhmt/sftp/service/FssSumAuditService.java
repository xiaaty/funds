package com.gqhmt.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.sftp.entity.FssSumAuditEntity;
import com.gqhmt.sftp.mapper.read.FssFinanceSumAuditReadMapper;

@Service
public class FssSumAuditService {
	
	@Resource
	private FssFinanceSumAuditReadMapper fssFinanceSumAuditReadMapper;
	
	public List<FssSumAuditEntity> querySumAuditList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("custNo",map.get("custNo"));
			map2.put("custName", map.get("custName"));
			map2.put("certNo", map.get("certNo"));
		}
		return fssFinanceSumAuditReadMapper.querySumAuditList(map2);
	}
	
}
