package com.gqhmt.fss.architect.depos.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.fss.architect.depos.entity.FssSftpRecordEntity;
import com.gqhmt.fss.architect.depos.mapper.read.FssSftpRecordReadMapper;
import com.gqhmt.fss.architect.depos.mapper.write.FssSftpRecordWriteMapper;

@Service
public class FssDeposRecordService {
	
	@Resource
	private FssSftpRecordReadMapper fssSftpRecordReadMapper;
	@Resource
	private FssSftpRecordWriteMapper fssSftpRecordWriteMapper;
	
	public List<FssSftpRecordEntity> findSftpRecordList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("title",map.get("title"));
			map2.put("type", map.get("type"));
			map2.put("status", map.get("status"));
		}
		return fssSftpRecordReadMapper.getRecordList(map2);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月20日
	 * function：修改
	 */
	public void updateSftpRecord(FssSftpRecordEntity fssSftpRecordEntity){
		fssSftpRecordWriteMapper.updateByPrimaryKey(fssSftpRecordEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月20日
	 * function：添加
	 */
	public void insertSftpRecord(FssSftpRecordEntity fssSftpRecordEntity){
		fssSftpRecordWriteMapper.insertSelective(fssSftpRecordEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：添加记录表信息
	 */
	public FssSftpRecordEntity insertSftpRecord(String title,int count,String type){
		FssSftpRecordEntity record=new FssSftpRecordEntity();
		record.setCount(count);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setTitle(title);
		record.setType(type);
		fssSftpRecordWriteMapper.insertSelective(record);
		return record;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月11日
	 * function：修改记录表信息
	 */
	public void updateSftpRecord(String title,int count,String type){
		FssSftpRecordEntity record=new FssSftpRecordEntity();
		record.setCount(count);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setTitle(title);
		record.setType(type);
		fssSftpRecordWriteMapper.insertSelective(record);
	}
	
}
