package com.gqhmt.fss.architect.fuiouFtp.service;

import com.github.pagehelper.Page;
import com.gqhmt.pay.exception.CommandParmException;
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

    private FuiouUploadFileService fuiouUploadFileService;

    public FuiouFtpOrder select(Long id) {
    	return fuiouFtpOrderReadMapper.selectByPrimaryKey(id);
    }
    
    public void insert(FuiouFtpOrder fuiouFtpOrder){
    	fuiouFtpOrderWriteMapper.insert(fuiouFtpOrder);
    }

    public void update(FuiouFtpOrder fuiouFtpOrder){
    	fuiouFtpOrderWriteMapper.updateByPrimaryKeySelective(fuiouFtpOrder);
    }
    
    public void update(List<FuiouFtpOrder> list){
    	for (FuiouFtpOrder fuiouFtpOrder : list) {
			update(fuiouFtpOrder);
		}
    }

    public void addOrder(FundOrderEntity fundOrderEntity,int type){
        FuiouFtpOrder order = new FuiouFtpOrder();
        order.setOrderNo(fundOrderEntity.getOrderNo());
        order.setType(type);
        insert(order);
    }

    public void updateUploadStatus(FuiouFtpOrder fuiouFtpOrder,int status){
        fuiouFtpOrder.setUploadStatus(status);
        update(fuiouFtpOrder);
    }

    /**
     * 获取ftp未上传的订单
     * @return
     */
    public List<FuiouFtpOrder> listNotUpload(){
        return fuiouFtpOrderReadMapper.listUpload();
    }

    /**
     * 获取未回盘的ftp订单
     * @return
     */
    public List<FuiouFtpOrder> listNotDownload(){
        return fuiouFtpOrderReadMapper.listDownload();
    }

    /**
     * 获取未处理结果的回盘文件列表
     * @return
     */
    public List<FuiouFtpOrder> listNotResultStatus(){
        return fuiouFtpOrderReadMapper.listResultStatus();
    }

    /**
     * 获取存在部分失败的回盘文件列表
     * @return
     */
    public List<FuiouFtpOrder> listNotResult(){
        return fuiouFtpOrderReadMapper.listResult();
    }


    public List<FuiouFtpOrder>  listAbort(){
        return fuiouFtpOrderReadMapper.listAbort();
    }

    public List<FuiouFtpOrder> listFile(){
        return fuiouFtpOrderReadMapper.listFile();
    }

    public List<FuiouFtpOrder> listNotReturnResult(){
        return fuiouFtpOrderReadMapper.listNoReturnResult();
    }

    public Page listAll(FundOrder fundOrder){
        return fuiouFtpOrderReadMapper.listAll(fundOrder);
    }

    public void repeatUpload(long id){
        FuiouFtpOrder fuiouFtpOrder = select(id);
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
            //fuiouUploadFileService.saveOrUpdateAll(fuiouUploadFile);
        }
        //
        fuiouFtpOrder.setFileStatus(1);
        fuiouFtpOrder.setUploadStatus(1);
        fuiouFtpOrder.setDownloadStatus(1);
        fuiouFtpOrder.setResultStatus(1);
        fuiouFtpOrder.setResult(0);
        fuiouFtpOrder.setRetrunResultStatus(0);
        insert(fuiouFtpOrder);
        FundOrderEntity order =fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
        order.setOrderState(6);
        try {
            fundOrderService.update(order);
        } catch (Exception e) {
            throw new CommandParmException("数据库录入错误",e);
        }
    }

    public void repeatDownload(long id){
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

    public void repeatAccount(long id){
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
     * 批量插入
     * @param list
     */
   public void saveOrUpdateAll(List<FuiouFtpOrder> fuiouftplist){
	   fuiouFtpOrderWriteMapper.saveOrUpdateAll(fuiouftplist);
   }

}