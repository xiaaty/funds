package com.gqhmt.fss.architect.card.service;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.card.entiry.FssPosBackEntity;
import com.gqhmt.fss.architect.card.mapper.read.FssPosBackReadMapper;
import com.gqhmt.fss.architect.card.mapper.write.FssPosBackWriteMapper;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
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
 * Create at:   2016年11月27
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月19日  jhz      1.0     1.0 Version
 */
@Service
public class FssPosBackService {

    @Resource
    private FssPosBackReadMapper fssPosBackReadMapper;
    @Resource
    private FssPosBackWriteMapper fssPosBackWriteMapper;
    @Resource
    private CustomerInfoService customerInfoService;

	/**
	 * jhz
	 * 添加
	 * @param fssPosBackEntity
	 * @throws FssException
     */
    public Integer insert(FssPosBackEntity fssPosBackEntity)throws FssException {
		return fssPosBackWriteMapper.insert(fssPosBackEntity);
    }
	/**
	 * 修改
	 * @param fssPosBackEntity
	 * @throws FssException
     */
    public void update(FssPosBackEntity fssPosBackEntity) throws FssException{
		fssPosBackEntity.setModifyTime(new Date());
		fssPosBackWriteMapper.updateByPrimaryKey(fssPosBackEntity);
    }

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws FssException
     */
    public FssPosBackEntity selectedById(Long id) throws FssException{
		return fssPosBackReadMapper.selectByPrimaryKey(id);
    }

	/**
	 * jhz
	 * 获得今天之前3天所有pos签约回调
	 * @return
     */
	public List<FssPosBackEntity> selectPosBacks()throws FssException{
		//获得今天之前3天的日期
		Calendar calendar=Calendar.getInstance();
		Date date=new Date();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-3);
		String startDate=DateUtil.dateTostring(calendar.getTime());

		return fssPosBackReadMapper.selectPosBacks(startDate);
	}

	/**
	 * jhz
	 * 核对该客户在3天内是否进行过pos签约
	 * @param custId
	 * @param bankNo
	 * @param mobileNo
	 * @throws FssException
     */
	public void confirmState(Long custId, String bankNo,String mobileNo)throws FssException{
		List<FssPosBackEntity> lists=this.selectPosBacks();
		CustomerInfoEntity customerInfoEntity=new CustomerInfoEntity();
		customerInfoEntity.setId(custId);
		for (FssPosBackEntity entity:lists) {
			if(StringUtils.equals("1",entity.getContractSt()) && StringUtils.equals(mobileNo,entity.getMobileNo()) && StringUtils.equals(bankNo,entity.getBankNo())){
				customerInfoEntity.setModifyTime(new Date());
				customerInfoEntity.setHasThirdAgreement(1);
				customerInfoService.update(customerInfoEntity);
			}
		}
	}
	/**
	 * jhz
	 * 创建添加pos签约回盘记录
	 * @param userName
	 * @param mobileNo
	 * @param bankNo
	 * @param credtNo
	 * @param contractSt
	 * @param userNameAcntIsVerif
	 * @param bankNoAcntIsVerif
	 * @param credtNoAcntIsVerif
	 * @param mobileNoAcntIsVerif
     * @return
     * @throws FssException
     */
	public FssPosBackEntity createPosBack(String userName,String mobileNo,String bankNo,String credtNo,String contractSt,
							 String userNameAcntIsVerif,String bankNoAcntIsVerif,
							 String credtNoAcntIsVerif,String mobileNoAcntIsVerif)throws FssException{
		FssPosBackEntity entity=new FssPosBackEntity();
		entity.setUserName(userName);
		entity.setMobileNo(mobileNo);
		entity.setBankNo(bankNo);
		entity.setCredtNo(credtNo);
		entity.setContractSt(contractSt);
		if(StringUtils.isEmpty(userNameAcntIsVerif)){
			userNameAcntIsVerif="0";
		}
		if(StringUtils.isEmpty(bankNoAcntIsVerif)){
			bankNoAcntIsVerif="0";
		}
		if(StringUtils.isEmpty(credtNoAcntIsVerif)){
			credtNoAcntIsVerif="0";
		}
		if(StringUtils.isEmpty(mobileNoAcntIsVerif)){
			mobileNoAcntIsVerif="0";
		}
		entity.setUserNameAcntIsVerif(Integer.valueOf(userNameAcntIsVerif));
		entity.setBankNoAcntIsVerif(Integer.valueOf(bankNoAcntIsVerif));
		entity.setCredtNoAcntIsVerif(Integer.valueOf(credtNoAcntIsVerif));
		entity.setMobileNoAcntIsVerif(Integer.valueOf(mobileNoAcntIsVerif));
		entity.setState("98010002");
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		return entity;
	}


}
