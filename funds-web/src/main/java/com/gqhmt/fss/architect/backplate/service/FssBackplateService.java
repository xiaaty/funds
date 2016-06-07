package com.gqhmt.fss.architect.backplate.service;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.mapper.read.FssFssBackplateReadMapper;
import com.gqhmt.fss.architect.backplate.mapper.write.FssFssBackplateWriteMapper;
import com.gqhmt.util.LogUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月19日
 * Description:	数据回盘Service
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月19日  jhz      1.0     1.0 Version
 */
@Service
public class FssBackplateService {

    @Resource
    private FssFssBackplateWriteMapper fssBackplateWriteMapper;
    @Resource
    private FssFssBackplateReadMapper fssBackplateReadMapper;

    /**
     * 
     * author:jhz
     * time:2016年3月19日
     * function：添加
     */
    public void insert(FssBackplateEntity fssBackplateEntity)throws FssException {
    	fssBackplateWriteMapper.insertSelective(fssBackplateEntity);
    }
    /**
     * 
     * author:jhz
     * time:2016年3月19日
     * function：修改
     */
    public void update(FssBackplateEntity fssBackplateEntity) throws FssException{
    	fssBackplateWriteMapper.updateByPrimaryKeySelective(fssBackplateEntity);
    }
    /**
     * 
     * author:jhz
     * time:2016年3月19日
     * function：查询
     */
    public FssBackplateEntity get(Long id)throws FssException{
        return fssBackplateReadMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 创建回盘信息
     * @return
     */
    public void createFssBackplateEntity(String seqNo,String mchn,String applyType) throws FssException {
    	FssBackplateEntity backplateEntity=new FssBackplateEntity();
		backplateEntity.setSeqNo(seqNo);
		backplateEntity.setMchn(mchn);
		backplateEntity.setTradeType(applyType);
		backplateEntity.setCreateTime(new Date());
		backplateEntity.setModifyTime(new Date());
		backplateEntity.setRepayCount(0);//回盘次数
		backplateEntity.setRepayResult(null);//回盘结果
		try {
			this.insert(backplateEntity);
		} catch (Exception e) {
			LogUtil.info(this.getClass(), e.getMessage());
			throw new FssException("91009804");
		}
    }


    public List<FssBackplateEntity> findBackAll()throws FssException{
    	List<FssBackplateEntity> list= this.fssBackplateReadMapper.findBackAll();
    	if(list==null && list.size()==0){
    		list=new ArrayList<FssBackplateEntity>();
    	}
    	list.addAll(this.findBackAllByTime(1, 10));
    	list.addAll(this.findBackAllByTime(2, 30));
    	list.addAll(this.findBackAllByTime(3, 60));
    	list.addAll(this.findBackAllByTime(4, 120));
    	list.addAll(this.findBackAllByTime(5, 360));
    	list.addAll(this.findBackAllByTime(6, 1440));
        return list;
    }

    public List<FssBackplateEntity> findBackAllByTime(int repayCount,int timeType){
        return this.fssBackplateReadMapper.findBackAllByTime(repayCount,timeType);
    }
    
    

}
