package com.gqhmt.fss.architect.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.fss.architect.sftp.entity.FinanceSumEntity;
import com.gqhmt.fss.architect.sftp.mapper.read.FssBidFinanceReadMapper;
import com.gqhmt.fss.architect.sftp.mapper.write.FssBidFinanceWriteMapper;

@Service
public class FssBidFinanceService {
	
	@Resource
	private FssBidFinanceReadMapper fssBidFinanceReadMapper;
	@Resource
	private FssBidFinanceWriteMapper fssBidFinanceWriteMapper;
	
	public List<FinanceSumEntity> queryBidFinanceList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("custNo",map.get("custNo"));
			map2.put("custName", map.get("custName"));
			map2.put("certNo", map.get("certNo"));
		}
		return fssBidFinanceReadMapper.queryFssFinanceSumList(map2);
	}
	
}
