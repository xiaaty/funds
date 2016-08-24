package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.fuiouFtp.bean.FundOrder;
import com.gqhmt.fss.architect.fuiouFtp.mapper.read.FuiouFtpOrderReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.mapper.write.FuiouFtpOrderWriteMapper;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
 * Create at:   2015/5/10 11:56
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
@Service
public class FuiouFtpOrderService {

	@Resource
    private FuiouFtpOrderReadMapper fuiouFtpOrderReadMapper;
    @Resource
    private FuiouFtpOrderWriteMapper fuiouFtpOrderWriteMapper;
    @Resource
    private FundOrderService fundOrderService;
    @Resource
    private FuiouUploadFileService fuiouUploadFileService;

    public FuiouFtpOrder select(Long id) throws FssException{
    	return fuiouFtpOrderReadMapper.selectByPrimaryKey(id);
    }
    
    public void insert(FuiouFtpOrder fuiouFtpOrder)throws FssException{
    	fuiouFtpOrderWriteMapper.insertSelective(fuiouFtpOrder);
    }

    public void update(FuiouFtpOrder fuiouFtpOrder)throws FssException{
    	fuiouFtpOrderWriteMapper.updateByPrimaryKeySelective(fuiouFtpOrder);
    }
    
    public void insertlist(List<FuiouFtpOrder> list)throws FssException{
    	fuiouFtpOrderWriteMapper.insertList(list);
    }

    public void addOrder(FundOrderEntity fundOrderEntity,int type)throws FssException{
        FuiouFtpOrder order = new FuiouFtpOrder();
        order.setOrderNo(fundOrderEntity.getOrderNo());
        order.setType(type);
        insert(order);
    }

    public void updateUploadStatus(FuiouFtpOrder fuiouFtpOrder,int status)throws FssException{
        fuiouFtpOrder.setUploadStatus(status);
        update(fuiouFtpOrder);
    }

    /**
     * 获取ftp未上传的订单
     * @return
     */
    public List<FuiouFtpOrder> listNotUpload()throws FssException{
    	
        return fuiouFtpOrderReadMapper.listUpload();
    }

    /**
     * 获取未回盘的ftp订单
     * @return
     */
    public List<FuiouFtpOrder> listNotDownload()throws FssException{
        return fuiouFtpOrderReadMapper.listDownload();
    }

    /**
     * 获取未处理结果的回盘文件列表
     * @return
     */
    public List<FuiouFtpOrder> listNotResultStatus()throws FssException{
        return fuiouFtpOrderReadMapper.listResultStatus();
    }

    /**
     * 获取存在部分失败的回盘文件列表
     * @return
     */
    public List<FuiouFtpOrder> listNotResult()throws FssException{
        return fuiouFtpOrderReadMapper.listResult();
    }


    public List<FuiouFtpOrder>  listAbort()throws FssException{
        return fuiouFtpOrderReadMapper.listAbort();
    }

    public List<FuiouFtpOrder> listFile()throws FssException{
        return fuiouFtpOrderReadMapper.listFile();
    }

    public List<FuiouFtpOrder> listNotReturnResult()throws FssException{
        return fuiouFtpOrderReadMapper.listNoReturnResult();
    }

    public List<FuiouFtpOrder> listAll(FundOrder fundOrder)throws FssException{
    	FuiouFtpOrder fuiouFtpOrder=new FuiouFtpOrder();
    	fuiouFtpOrder.setOrderNo(fundOrder.getOrderNo());
        return fuiouFtpOrderReadMapper.select(fuiouFtpOrder);
    }

    public void repeatUpload(long id)throws FssException{
        FuiouFtpOrder fuiouFtpOrder = fuiouFtpOrderReadMapper.selectByPrimaryKey(id);
        if(fuiouFtpOrder.getFileSize()>1){
            throw new CommandParmException("多文件上传，需手动数据库调整");
        }
        //删除上传表数据
        List<FuiouUploadFile> fuiouUploadFile = fuiouUploadFileService.listAll(
        		fuiouFtpOrder.getOrderNo());
        if(fuiouUploadFile != null && fuiouUploadFile.size()>= 0){
            for(FuiouUploadFile file : fuiouUploadFile){
                fuiouUploadFileService.delete(file.getId());
            }
            fuiouUploadFileService.saveOrUpdateAll(fuiouUploadFile);
        }
        fuiouFtpOrder.setFileStatus(1);
        fuiouFtpOrder.setUploadStatus(1);
        fuiouFtpOrder.setDownloadStatus(1);
        fuiouFtpOrder.setResultStatus(1);
        fuiouFtpOrder.setResult(0);
        fuiouFtpOrder.setRetrunResultStatus(0);
        fuiouFtpOrderWriteMapper.insert(fuiouFtpOrder);
        FundOrderEntity order =fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
        order.setOrderState(6);
        try {
            fundOrderService.insert(order);
        } catch (Exception e) {
            throw new CommandParmException("数据库录入错误",e);
        }
    }

    public void repeatDownload(long id)throws FssException{
        FuiouFtpOrder fuiouFtpOrder = select(id);
        fuiouFtpOrder.setDownloadStatus(1);
        fuiouFtpOrder.setResultStatus(1);
        fuiouFtpOrder.setResult(0);
        fuiouFtpOrder.setRetrunResultStatus(0);
        update(fuiouFtpOrder);
        //删除上传表数据
        List<FuiouUploadFile> fuiouUploadFile =fuiouUploadFileService.listAll( fuiouFtpOrder.getOrderNo());
        if(fuiouUploadFile != null && fuiouUploadFile.size()>= 0){
            ;for(FuiouUploadFile file : fuiouUploadFile){
                file.setState(2);
            }
            fuiouUploadFileService.update(fuiouUploadFile);
        }
        //
        FundOrderEntity order =fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
        order.setOrderState(6);
        try {
            fundOrderService.update(order);
        } catch (Exception e) {
            throw new CommandParmException("数据库录入错误",e);
        }
    }

    public void repeatAccount(long id)throws FssException{
        FuiouFtpOrder fuiouFtpOrder = select(id);
        fuiouFtpOrder.setResultStatus(0);
        update(fuiouFtpOrder);
        FundOrderEntity order =fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
        order.setOrderState(6);
        try {
            fundOrderService.update(order);
        } catch (Exception e) {
            throw new CommandParmException("数据库录入错误",e);
        }
    }

    /**
     * 批量保存
     */
    public void saveAll(List<FuiouFtpOrder> list)throws FssException{
    	fuiouFtpOrderWriteMapper.insertList(list);
    }

    /**
     * 查询FtpOrder集合，根据FuiouFtpOrder
     */
    public List<FuiouFtpOrder> selectFuiouFtpOrderList(Map<String, String> map){
        return fuiouFtpOrderReadMapper.selectFuiouFtpOrderList(map);
    }
    
    /**
     * 失败重试
     * @param id
     * @throws FssException
     */
    public void failureRetry(Long id)throws FssException{
    	FuiouFtpOrder record = this.select(id);
    	if(record != null){
    		record.setFileStatus(1);
        	this.update(record);
    	}
    }

}