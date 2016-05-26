package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.RepaymentChildDto;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.extServInter.dto.loan.RepaymentResponse;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssRepaymentParentReadMapper;
import com.gqhmt.fss.architect.trade.mapper.read.FssRepaymentReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssRepaymentParentWriteMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssRepaymentWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Resource
	private FssRepaymentParentWriteMapper fssRepaymentParentWriteMapper;
	@Resource
	private FssBackplateService fssBackplateService;
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	
	
	/**
	 * 创建借款人提现申请
	 * @param repayments
	 * @throws FssException
	 */
	public void createRepayments(List<FssRepaymentEntity> repayments) throws FssException{
		fssRepaymentWriteMapper.insertList(repayments);
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
	 * author:jhz
	 * time:2016年4月25日
	 * function：根据商户号流水号查询对象信息
	 */
	public FssRepaymentParentEntity queryByMchnAndSeqNo(String mchn,String seqNo) throws FssException{
		Map<String, String> map=new HashMap<>();
		map.put("mchn",mchn);
		map.put("seqNo",seqNo);
		return fssRepaymentParentReadMapper.queryByMchnAndSeqNo(map);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：根据id查询主表对象
	 */
	public FssRepaymentParentEntity queryRepaymentParentById(Long id){
		return fssRepaymentParentReadMapper.selectByPrimaryKey(id);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：根据id查询子表对象信息
	 */
	public FssRepaymentEntity queryRepaymentById(Long id){
		return fssRepaymentReadMapper.selectByPrimaryKey(id);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：修改主表状态
	 */
	public int updateRepaymentParent(FssRepaymentParentEntity fssRepaymentParentEntity){
		return fssRepaymentParentWriteMapper.updateByPrimaryKey(fssRepaymentParentEntity);
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
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：修改借款代扣交易状态
	 */
	public int updateRepaymentEntity(FssRepaymentEntity repayment){
		return fssRepaymentWriteMapper.updateByPrimaryKey(repayment);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月25日
	 * function：修改借款代扣交易状态
	 * @throws FssException 
	 */
	public void updateRepaymentEntity(FssRepaymentEntity repayment,String tradeStatus,BigDecimal amt,String sqNo,String mchn,String tradeType) throws FssException{
		repayment.setState(tradeStatus);
		repayment.setResultState(tradeStatus);
		repayment.setMotifyTime(new Date());
		fssRepaymentWriteMapper.updateByPrimaryKey(repayment);
		if(!"10080010".equals(tradeStatus)){
		repayment.setAmt(amt);
		//更新主表执行成功条数
		this.updateSuccessCount(repayment);
		//修改主表状态
		this.changeRepaymentParentStatus(repayment,sqNo,mchn,tradeType);
		}
		
	}
	/**
	 * 还款划扣
	 * @param repaymentDto
	 * @return
	 * @throws FssException
	 */
	 public Response createRefundDraw(RepaymentDto repaymentDto) throws FssException {
		Response response=new Response();
    	try {
    		//创建主表信息
			FssRepaymentParentEntity repaymentParent = this.createRepaymentParentEntity(repaymentDto);
				response.setMchn(repaymentDto.getMchn());
				response.setSeq_no(repaymentDto.getSeq_no());
				response.setTrade_type(repaymentDto.getTrade_type());
				response.setSignature(repaymentDto.getSignature());
				response.setResp_code("0000");
				response.setResp_msg("执行成功！");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			throw new FssException("还款划扣失败！");
		}
    	return response;
	 }
	
	/**
	 * 创建实体类FssRepaymentEntity
	 * @param repaymentDto
	 * @return
	 */
	public FssRepaymentEntity createFssRepaymentEntity(RepaymentChildDto repyament,RepaymentDto repaymentDto,FssRepaymentParentEntity repaymentParent) throws FssException{
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
		repaymentEntity.setContractNo(repyament.getContract_no());
		repaymentEntity.setMchnParent(Application.getInstance().getParentMchn(repaymentDto.getMchn()));
		repaymentEntity.setMchnChild(repaymentDto.getMchn());
		repaymentEntity.setRemark(repyament.getRemark());
		repaymentEntity.setRespCode("");
		repaymentEntity.setRespMsg("");
		repaymentEntity.setParentId(repaymentParent.getId());
		return repaymentEntity;
	}
	
	/**
	 * 创建还款代扣主表信息
	 */
	public FssRepaymentParentEntity createRepaymentParentEntity(RepaymentDto repaymentDto) throws FssException{
		List<RepaymentChildDto> repaymentlist=repaymentDto.getRepay_list();
    	BigDecimal amtSum=new BigDecimal("0");
    	for(RepaymentChildDto r:repaymentlist){//还款总额
    		amtSum=amtSum.add(r.getAmt());
    	}
		FssRepaymentParentEntity repaymentParent=new FssRepaymentParentEntity();
		repaymentParent.setSeqNo(repaymentDto.getSeq_no());
		repaymentParent.setTradeType(repaymentDto.getTrade_type());
		repaymentParent.setTradeCount(repaymentDto.getRepay_list().size());
		repaymentParent.setSuccessCount(0);
		repaymentParent.setFiledCount(0);
		repaymentParent.setState("10090001");
		repaymentParent.setResultState("10080001");
		repaymentParent.setAmt(amtSum);
		repaymentParent.setPayAmt(BigDecimal.ZERO);
		repaymentParent.setCreateTime(new Date());
		repaymentParent.setMotifyTime(new Date());
		repaymentParent.setMchnChild(repaymentDto.getMchn());
		repaymentParent.setMchnParent(Application.getInstance().getParentMchn(repaymentDto.getMchn()));
		repaymentParent.setRemark("");
		fssRepaymentParentWriteMapper.insertSelective(repaymentParent);
		for(RepaymentChildDto repyament:repaymentlist){
			//创建子表信息
			FssRepaymentEntity repaymentEntity = this.createFssRepaymentEntity(repyament,repaymentDto,repaymentParent);
			this.fssRepaymentWriteMapper.insertSelective(repaymentEntity);
			fssTradeApplyService.insertTradeApply(repaymentEntity);
		}
		return repaymentParent;
	}
	
	/**
	 * 还款划扣通知
	 */
    public RepaymentResponse rePaymentCallBack(String seqNo,String mchn) throws FssException{
    	RepaymentResponse repaymentResponse=new RepaymentResponse();
    	FssRepaymentParentEntity queryByMchnAndSeqNo = this.queryByMchnAndSeqNo(mchn, seqNo);
    	List<FssRepaymentEntity> repaymentlist=this.searRepaymentByparam(seqNo,mchn);
    	if(repaymentlist==null){
    		throw new FssException("还款划扣失败");
    	}
    	List<RepaymentChildDto>repaymentChilds=new ArrayList<>();
    	RepaymentChildDto repaymentChild=null;
    	for (FssRepaymentEntity fssRepaymentEntity : repaymentlist) {
    		repaymentChild=new RepaymentChildDto();
    		repaymentChild.setAcc_no(fssRepaymentEntity.getAccNo());
    		repaymentChild.setAmt(fssRepaymentEntity.getAmt());
			repaymentChild.setReal_repay_amt(fssRepaymentEntity.getAmt());
    		repaymentChild.setContract_id(fssRepaymentEntity.getContractId());
    		repaymentChild.setContract_no(fssRepaymentEntity.getContractNo());
    		repaymentChild.setRemark(fssRepaymentEntity.getRemark());
    		repaymentChild.setSerial_number(fssRepaymentEntity.getSerialNumber());
    		repaymentChilds.add(repaymentChild);
		}
    	
    	if("10080002".equals(queryByMchnAndSeqNo.getResultState())){
    		repaymentResponse.setResp_code("0000");
    		repaymentResponse.setResp_msg("成功");
    	}else {
    		repaymentResponse.setResp_code(queryByMchnAndSeqNo.getResultState());
    	}
    	repaymentResponse.setRepay_list(repaymentChilds);
    	repaymentResponse.setMchn(mchn);
    	repaymentResponse.setSeq_no(seqNo);
    	repaymentResponse.setTrade_type(queryByMchnAndSeqNo.getTradeType());
    	return repaymentResponse;
    }
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：查询该批次交易总数
	 */
	public int getTradeCount(Long parentId) {
		return fssRepaymentReadMapper.getTradeCount(parentId);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：查询该批次交易成功数
	 */
	public int getSuccessCount(Long parentId) {
		return fssRepaymentReadMapper.getSuccessCount(parentId);
	}

	/**
	 * 根据parent_id查询子表信息
	 * @param parentId
	 * @return
     */
	public List<FssRepaymentEntity> searchRepaymentByParentId(Long parentId) {
		return fssRepaymentReadMapper.searchRepaymentByParentId(parentId);
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：修改主表执行条数
	 */
	public void updateSuccessCount(FssRepaymentEntity queryRepayment){
		fssRepaymentParentWriteMapper.updateRepaymentParentSuccessCount(queryRepayment);
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：代扣成功
	 */
	public FssRepaymentEntity changeTradeStatus(Long id){
		FssRepaymentEntity queryRepayment= this.queryRepaymentById(id);
		queryRepayment.setState("10090003");	//10090003划扣完成
		queryRepayment.setResultState("10080002");	//10080002 成功
		queryRepayment.setMotifyTime(new Date());
		this.updateRepaymentEntity(queryRepayment);
		//更新主表执行成功条数
		this.updateSuccessCount(queryRepayment);
		return queryRepayment;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：修改主表状态
	 * @throws FssException 
	 */
	//todo
	public void changeRepaymentParentStatus(FssRepaymentEntity queryRepayment,String seqNo,String mchn,String tradeType) throws FssException{
		FssRepaymentParentEntity queryRepaymentParentById = this.queryRepaymentParentById(queryRepayment.getParentId());
		if(queryRepaymentParentById.getTradeCount()<=queryRepaymentParentById.getSuccessCount()){
			int successCount = getSuccessCount(queryRepayment.getParentId());
			if(queryRepaymentParentById.getTradeCount()==successCount){
				
				//成功
				queryRepaymentParentById.setResultState("10080002");
				 queryRepaymentParentById.setState("10090003");
				 queryRepaymentParentById.setMotifyTime(new Date());
				this.updateRepaymentParent(queryRepaymentParentById);
			}else if(successCount==0){
				//失败
				queryRepaymentParentById.setResultState("10080010");
				 queryRepaymentParentById.setState("10090003");
				 queryRepaymentParentById.setMotifyTime(new Date());
				this.updateRepaymentParent(queryRepaymentParentById);
			}else{
				//部分成功
				queryRepaymentParentById.setResultState("10080003");
				 queryRepaymentParentById.setState("10090003");
				 queryRepaymentParentById.setMotifyTime(new Date());
				this.updateRepaymentParent(queryRepaymentParentById);
			}
			
			//创建回盘信息
			fssBackplateService.createFssBackplateEntity(seqNo,mchn,tradeType);
		}
	}
	
}
