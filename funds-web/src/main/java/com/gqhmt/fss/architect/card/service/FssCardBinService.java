package com.gqhmt.fss.architect.card.service;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.card.entiry.FssCardBinEntity;
import com.gqhmt.fss.architect.card.mapper.read.FssCardBinReadMapper;
import com.gqhmt.fss.architect.card.mapper.write.FssCardBinWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
 * Create at:   2016年5月27
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月19日  jhz      1.0     1.0 Version
 */
@Service
public class FssCardBinService {

    @Resource
    private FssCardBinReadMapper fssCardBinReadMapper;
    @Resource
    private FssCardBinWriteMapper fssCardBinWriteMapper;

	/**
	 * 添加
	 * @param fssCardBinEntity
	 * @throws FssException
     */
    public void insert(FssCardBinEntity fssCardBinEntity)throws FssException {
		fssCardBinWriteMapper.insert(fssCardBinEntity);
    }
	/**
	 * 修改
	 * @param fssCardBinEntity
	 * @throws FssException
     */
    public void update(FssCardBinEntity fssCardBinEntity) throws FssException{
		fssCardBinWriteMapper.updateByPrimaryKey(fssCardBinEntity);
    }
	/**
	 * 批量添加
	 * @param cardBinEntities
	 * @throws FssException
     */
    public void insetList(List<FssCardBinEntity> cardBinEntities) throws FssException{
		fssCardBinWriteMapper.insertList(cardBinEntities);
    }
	/**
	 * 查询全部
	 * @throws FssException
     */
    public List<FssCardBinEntity> findAll() throws FssException{
		return fssCardBinReadMapper.selectAll();
    }



}
