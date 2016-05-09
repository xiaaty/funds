package com.gqhmt.fss.architect.sftp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.fss.architect.sftp.entity.FssProjectCallbackEntity;
import com.gqhmt.fss.architect.sftp.mapper.read.FssProjectInfoCallBackReadMapper;
import com.gqhmt.fss.architect.sftp.mapper.write.FssPorjectCallbackWriteMapper;

@Service
public class FssProjectInfoCallBackService {
	
	@Resource
	private FssProjectInfoCallBackReadMapper fssProjectInfoCallBackReadMapper;
	@Resource
	private FssPorjectCallbackWriteMapper fssPorjectCallbackWriteMapper;
	
	public List<FssProjectCallbackEntity> queryFssProjectCallBackList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("itemNo",map.get("itemNo"));
			map2.put("itemName", map.get("itemName"));
		}
		return fssProjectInfoCallBackReadMapper.queryFssProjectCallBackList(map2);
	}
	
}
