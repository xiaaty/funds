package com.gqhmt.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssCreditInfoEntity;
import com.gqhmt.sftp.mapper.read.FssCreditInfoReadMapper;

@Service
public class FssCreditInfoService {
	
	@Resource
	private FssCreditInfoReadMapper fssCreditInfoReadMapper;
	
	
	public List<FssCreditInfoEntity> queryFssCreditInfoEntityList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("custName", map.get("custName"));
			map2.put("certNo", map.get("certNo"));
		}
		return fssCreditInfoReadMapper.queryCreditInfoList(map2);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：标的放款明细文件 
	 */
	public List<FssCreditInfoEntity> queryCreditInfo()throws FssException {
		return fssCreditInfoReadMapper.selectAll();
	}
	
}
