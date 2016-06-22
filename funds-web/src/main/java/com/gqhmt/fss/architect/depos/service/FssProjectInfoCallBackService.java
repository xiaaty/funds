package com.gqhmt.fss.architect.depos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.depos.entity.FssProjectCallbackEntity;
import com.gqhmt.fss.architect.depos.mapper.read.FssProjectCallbackReadMapper;
import com.gqhmt.fss.architect.depos.mapper.write.FssPorjectCallbackWriteMapper;

@Service
public class FssProjectInfoCallBackService {

	@Resource
	private FssProjectCallbackReadMapper fssProjectInfoCallBackReadMapper;
	@Resource
	private FssPorjectCallbackWriteMapper fssPorjectCallbackWriteMapper;
	/**
	 * 项目信息回盘列表
	 * @param map
	 * @return
	 */
	public List<FssProjectCallbackEntity> queryFssProjectCallBackList(Map<String,String> map) throws FssException{
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("itemNo",map.get("itemNo"));
			map2.put("itemName", map.get("itemName"));
		}
		return fssProjectInfoCallBackReadMapper.queryFssProjectCallBackList(map2);
	}

	/**
	 * 批量插入项目回盘信息
	 * @param projectlist
	 * @throws FssException
	 */
	public void createProjectInfo(List<FssProjectCallbackEntity> projectlist) throws FssException{
		fssPorjectCallbackWriteMapper.insertList(projectlist);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月24日
	 * function：修改项目信息回盘
	 */
	public void update(FssProjectCallbackEntity projectCallback) throws FssException{
		fssPorjectCallbackWriteMapper.updateByPrimaryKey(projectCallback);
	}
}
