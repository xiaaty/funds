package com.gqhmt.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.sftp.entity.FssSftpRecordEntity;
import com.gqhmt.sftp.mapper.read.FssSftpRecordReadMapper;

@Service
public class FssSftpRecordService {
	
	@Resource
	private FssSftpRecordReadMapper fssSftpRecordReadMapper;
	
	public List<FssSftpRecordEntity> findSftpRecordList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("title",map.get("title"));
			map2.put("type", map.get("type"));
			map2.put("status", map.get("status"));
		}
		return fssSftpRecordReadMapper.getRecordList(map2);
	}
	
	
}
