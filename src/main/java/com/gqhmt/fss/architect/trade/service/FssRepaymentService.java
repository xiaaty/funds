package com.gqhmt.fss.architect.trade.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.Repayment;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssRepaymentParentReadMapper;
import com.gqhmt.fss.architect.trade.mapper.read.FssRepaymentReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssRepaymentWriteMapper;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:29
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Service
public class FssRepaymentService {
	
	@Resource
    private FssRepaymentWriteMapper fssRepaymentWriteMapper;
	
	@Resource
	private FssRepaymentReadMapper fssRepaymentReadMapper;
	
	@Resource
	private FssRepaymentParentReadMapper fssRepaymentParentReadMapper;
	/**
	 * 创建借款人提现申请
	 * @param fssTradeApplyEntity
	 * @throws FssException
	 */
	public void createRepayments(List<FssRepaymentEntity> repayments) throws FssException{
		fssRepaymentWriteMapper.insertList(repayments);
//		fssRepaymentWriteMapper.insertRepaymentList(repayments);
	}
	
	/**
	 * 根据商户号和流水号查询还款信息
	 * @param seqNo
	 * @param mchn
	 * @return
	 * @throws FssException
	 */
	public List<FssRepaymentEntity> searRepaymentByparam(String seqNo,String mchn) throws FssException{
		FssRepaymentEntity entity=new FssRepaymentEntity();
		entity.setSeqNo(seqNo);
		entity.setMchnChild(mchn);
		return fssRepaymentReadMapper.select(entity);
	}
	
	/**
	 * 查询所有借款代扣主表信息
	 * @return
	 * @throws FssException
	 */
	public List<FssRepaymentParentEntity> queryRepaymentParents(FssRepaymentParentEntity repayment) throws FssException{
		return fssRepaymentParentReadMapper.queryFssRepaymentParent(repayment);
	}
	/**
	 * 查询所有借款代扣详细
	 * @return
	 * @throws FssException
	 */
	public List<FssRepaymentEntity> queryFssRepaymentEntity(FssRepaymentEntity repayment) throws FssException{
			return fssRepaymentReadMapper.queryFssRepayment(repayment);
	}
	/**
	 * 还款划扣
	 * @param repaymentDto
	 * @return
	 * @throws FssException
	 */
	 public boolean createRefundDraw(RepaymentDto repaymentDto) throws FssException {
		List<FssRepaymentEntity> fssRepaymentlist=new ArrayList<FssRepaymentEntity>();
    	List<Repayment> repaymentlist=null;
    	repaymentlist=repaymentDto.getList();
    	for(Repayment repyament:repaymentlist){
    		FssRepaymentEntity repaymentEntity = this.createFssRepaymentEntity(repyament,repaymentDto);
    		fssRepaymentlist.add(repaymentEntity);
    	}
    	try {
			this.createRepayments(fssRepaymentlist);
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			throw new FssException("还款划扣失败！");
		}
    	return true;
	 }
	
	/**
	 * 创建实体类FssRepaymentEntity
	 * @param dto
	 * @param repaymentDto
	 * @return
	 */
	public FssRepaymentEntity createFssRepaymentEntity(Repayment repyament,RepaymentDto repaymentDto) throws FssException{
		FssRepaymentEntity repaymentEntity = new FssRepaymentEntity();
		repaymentEntity.setAccNo(repyament.getAcc_no());
		repaymentEntity.setTradeType(repaymentDto.getTrade_type());
		repaymentEntity.setCreateTime(new Date());
		repaymentEntity.setMotifyTime(new Date());
		repaymentEntity.setAmt(repyament.getAmt());
		repaymentEntity.setState("10090001");
		repaymentEntity.setResultState("10080001");
		repaymentEntity.setSeqNo(repaymentDto.getSeq_no());
		repaymentEntity.setSerialNumber(repyament.getSerial_number());
		repaymentEntity.setContractId(repyament.getContract_id());
		repaymentEntity.setMchnParent(Application.getInstance().getParentMchn(repaymentDto.getMchn()));
		repaymentEntity.setMchnChild(repaymentDto.getMchn());
		repaymentEntity.setRemark(repyament.getRemark());
		repaymentEntity.setRespCode("");
		repaymentEntity.setRespMsg("");
		repaymentEntity.setParentId(1l);
		return repaymentEntity;
	}
	
	/**
	 * 还款划扣通知
	 */
    public List<FssRepaymentEntity> rePaymentCallBack(String seqNo,String mchn) throws FssException{
    	List<FssRepaymentEntity> repaymentlist=null;
    	repaymentlist=this.searRepaymentByparam(seqNo,mchn);
    	if(repaymentlist==null){
    		throw new FssException("还款划扣失败");
    	}
    	return repaymentlist;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
