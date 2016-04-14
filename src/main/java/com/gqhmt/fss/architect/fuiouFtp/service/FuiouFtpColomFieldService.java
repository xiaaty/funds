package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.mapper.read.FuiouFtpColomFieldReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.mapper.write.FuiouFtpColomFieldWriteMapper;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.fuiou.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 13:50
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
@Service
public class FuiouFtpColomFieldService {

    @Resource
    private FuiouFtpColomFieldReadMapper fuiouFtpColomFieldReadMapper;
    @Resource
    private FuiouFtpColomFieldWriteMapper fuiouFtpColomFieldWriteMapper;

    public void insert(FuiouFtpColomField fuiouFtpColomField)throws FssException{
    	fuiouFtpColomFieldWriteMapper.insertSelective(fuiouFtpColomField);
    }

    public void insertList(List<FuiouFtpColomField> fuiouFtpColomFields)throws FssException{
    	fuiouFtpColomFieldWriteMapper.insertList(fuiouFtpColomFields);
    }

    public void update(FuiouFtpColomField fuiouFtpColomField)throws FssException{
    	fuiouFtpColomFieldWriteMapper.updateByPrimaryKey(fuiouFtpColomField);
    }

    public List<FuiouFtpColomField> getFuiouFtpColunm(int state)throws FssException{
        return fuiouFtpColomFieldReadMapper.getByState(state);
    }

    public List<FuiouFtpColomField> getFuiouFtpColunm(String orderNo)throws FssException{
        return fuiouFtpColomFieldReadMapper.getByOrderNo(orderNo);
    }

    public Map<String,FuiouFtpColomField> getFuiouFtpColunm(Long fileId)throws FssException{
    	List<FuiouFtpColomField> list=fuiouFtpColomFieldReadMapper.selectByFileId(fileId);
    	 if(list == null){
             return null;
         }
         Map<String,FuiouFtpColomField> map = new HashMap<>();
         for(FuiouFtpColomField colomField:list){
             map.put(colomField.getSeqNo(),colomField);
         }
        return map;
    }

    public List<Long> getOrder()throws FssException{
        return this.fuiouFtpColomFieldReadMapper.getOrder();
    }

    public List<String > getReqCode(String orderNo)throws FssException{
        return fuiouFtpColomFieldReadMapper.getReqCode(orderNo);
    }

    /**
     * 新增信息
     * @param fromEntity
     * @param toEntity
     * @param fundOrderEntity
     * @param amt
     * @param type
     * @param bidTitle
     * @param contractNo
     */
    public void addColomField(FundAccountEntity fromEntity, FundAccountEntity toEntity, FundOrderEntity fundOrderEntity, BigDecimal amt, int type, String bidTitle, String contractNo)throws FssException{
        FuiouFtpColomField field = this.addColomFieldByNotInsert(fromEntity,toEntity,fundOrderEntity,amt,type,bidTitle,contractNo);
        insert(field);
    }

    public FuiouFtpColomField addColomFieldByNotInsert(FundAccountEntity fromEntity,FundAccountEntity toEntity,FundOrderEntity fundOrderEntity,BigDecimal amt,int type,String bidTitle,String contractNo)throws FssException{
        FuiouFtpColomField field = new FuiouFtpColomField();
        field.setFromAccountId(fromEntity.getId());
        field.setFromUserName(fromEntity.getUserName());
        String  custName = fromEntity.getCustName();
        if(custName == null || "".equals(custName)){
            throw new CommandParmException("账户信息错误，客户姓名为空");
        }

        field.setFromCnUserName(fromEntity.getCustName());
        field.setAmt(amt);
        field.setToAccountId(toEntity.getId());
        field.setToUserName(toEntity.getUserName());
        field.setToCnUserName(toEntity.getCustName().trim());
        field.setState(1);
        field.setOrderId(fundOrderEntity.getId());
        if(type == 1){//投标解冻
            field.setRem("投标 "+bidTitle+ "解冻资金 "+amt.toPlainString()+"元");
        }else  if(type == 2){//投标转账
            field.setRem("投标 "+bidTitle+ " 成功，转给借款人资金 "+amt.toPlainString()+"元");
        }else  if(type == 3){//还款转账
            field.setRem(bidTitle+"借款人偿还资金"+amt.toPlainString()+"元");
        }else  if(type == 4){//还款后冻结
            field.setRem("账户资金冻结 "+amt.toPlainString()+"元");
        }else if(type == 5){
            field.setRem("投标返现 "+amt.toPlainString()+"元");
        }else{//其他
        }
        field.setOrderNo(fundOrderEntity.getOrderNo());
        field.setContractNo(contractNo);
        return field;
    }


    public void updateByFileSeqId(String fileSeq,String reqCode,String msg)throws FssException{
    	FuiouFtpColomField fuiouFtpColomField=new FuiouFtpColomField();
    	fuiouFtpColomField.setId(Long.valueOf(fileSeq));
    	fuiouFtpColomField.setReturnCode(reqCode);
    	fuiouFtpColomField.setReturnMsg(msg);
    	fuiouFtpColomFieldWriteMapper.updateByFileSeqId(fuiouFtpColomField);
    }

    public List<FuiouFtpColomField> listAll(){
        return fuiouFtpColomFieldReadMapper.selectAll();
    }
    
    /**
     * 批量插入
     * @param fuiyoulist
     */
   public void saveOrUpdateAll(List<FuiouFtpColomField> fuiyoulist)throws FssException{
	   for (FuiouFtpColomField fuiouFtpColomField : fuiyoulist) {
		
		   fuiouFtpColomFieldWriteMapper.insert(fuiouFtpColomField);
	}
   }
   /**
    * 
    * author:jhz
    * time:2016年4月14日
    * function：批量修改
    */
   public void updateList(List<FuiouFtpColomField> fuiyoulist)throws FssException{
	   for (FuiouFtpColomField fuiouFtpColomField : fuiyoulist) {
		   
		   fuiouFtpColomFieldWriteMapper.updateByPrimaryKey(fuiouFtpColomField);
	   }
   }
   /**
    * 
    * author:jhz
    * time:2016年4月14日
    * function：批量修改
    */
   public void updateCollection(Collection<FuiouFtpColomField> collection)throws FssException {
	   for (FuiouFtpColomField fuiouFtpColomField : collection) {
		   fuiouFtpColomFieldWriteMapper.updateByPrimaryKey(fuiouFtpColomField);
	   }
   }
    
    
    
    
    
    
    
    
    
    
}
