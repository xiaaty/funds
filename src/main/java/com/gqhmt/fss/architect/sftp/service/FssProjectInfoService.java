package com.gqhmt.fss.architect.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.fss.architect.sftp.mapper.read.FssProjectInfoReadMapper;
import com.gqhmt.fss.architect.sftp.mapper.write.FssProjectInfoWriteMapper;

@Service
public class FssProjectInfoService {
	
	@Resource
	private FssProjectInfoReadMapper fssProjectInfoReadMapper;
	@Resource
	private FssProjectInfoWriteMapper fssProjectInfoWriteMapper;
	
	public List<FssProjectInfoEntity> queryFssProjectList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("mchn",map.get("mchn"));
			map2.put("seqNo", map.get("seqNo"));
			map2.put("itemNo", map.get("itemNo"));
		}
		return fssProjectInfoReadMapper.queryFssProjectInfoList(map2);
	}
	
	public void createProjectInfo(List<FssProjectInfoEntity> projectlist) throws FssException{
		fssProjectInfoWriteMapper.insert(projectlist);
	}
	
}
