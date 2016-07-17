package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.*;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chris on 2015/5/10.
 */
@Service
public class FtpResultService {

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private FuiouUploadFileService fuiouUploadFileService;

    @Resource
    private  FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FundOrderService fundOrderService;


    @Resource
    private FundSequenceService fundSequenceService;

    @Resource
    private BidSettleService settleService;

    @Resource
    private BidRepaymentService bidRepaymentService;

    @Resource
    private BidAbortService abortService;

    /**
     * 解析下载文件状态
     * @throws FssException 
     */
    public void parseDownloadResult() throws FssException{
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listNotDownload();
        if(list == null || list.size() == 0){
            return;
        }

        for(FuiouFtpOrder fuiouFtpOrder:list){
            List<Integer> state = fuiouUploadFileService.list(fuiouFtpOrder.getOrderNo());
            if(state == null || state.size() == 0){
                continue;
            }
            if(state.size() == 1 && (state.get(0) == 3 || state.get(0) == 5)){
                fuiouFtpOrder.setDownloadStatus(4);
                fuiouFtpOrderService.update(fuiouFtpOrder);
                continue;
            }

            System.out.println("fuiouFtp:parseDownloadResult:"+((FuiouFtpOrder) fuiouFtpOrder).getOrderNo());
            fuiouFtpOrderService.update(fuiouFtpOrder);
        }
    }

    /**
     * 解析结果
     * @throws FssException 
     */
    public void parseResult() throws FssException{

        List<FuiouFtpOrder> list = fuiouFtpOrderService.listNotResultStatus();
        if(list == null || list.size() == 0){
            return;
        }
        for(FuiouFtpOrder fuiouFtpOrder:list){
        	parseResult(fuiouFtpOrder);
        	fuiouFtpOrderService.update(fuiouFtpOrder);
        }

    }

    /**
     * 对单一订单执行结果处理
     * @param order
     * @throws FssException 
     */

    public void parseResult(FuiouFtpOrder order) throws FssException{
        System.out.println("fuiouFtp:parseResult:"+order.getOrderNo());
        List<String> reqCode  = fuiouFtpColomFieldService.getReqCode(order.getOrderNo());
        if(reqCode == null || reqCode.size() == 0){
            return;
        }
        String reqCodeMsg = reqCode.get(0);
        if(reqCode.size() == 1 && "0000".equals(reqCodeMsg)){
            order.setResultStatus(3);
            order.setResult(1);
        }
    }

    //资金流水入账
/*    private void  callback(FundOrderEntity orderEntity,int result){
        int type = 0;
        int orderType = orderEntity.getOrderType();
        if(orderType == GlobalConstants.ORDER_SETTLE_UNFORZEN){
            type = 1;
        }else if(orderType == GlobalConstants.ORDER_SETTLE){
            type = 2;
        }else if(orderType == GlobalConstants.ORDER_REPAYMENT){
            type = 3;
        }else if(orderType == GlobalConstants.ORDER_POINT_GQ_RETURN_FEE){
            type = 4;
        }
        if(result == 1) {
            AccountCommand.payCommand.command(CommandEnum.TenderCommand.TENDER_CALLBACK, ThirdPartyType.FUIOU, type, orderEntity.getOrderNo());
        }else{
            orderEntity.setOrderState(3);
        }
    }*/


    /**
     * 结果回调
     * @param orderEntity
     * @param fuiouFtpOrder
     * @throws FssException 
     */
    private void returnResult(FundOrderEntity orderEntity,FuiouFtpOrder fuiouFtpOrder) throws FssException{
        int result = fuiouFtpOrder.getResult();
        if(result >1 && result<3){
//            returnResult(orderEntity,fuiouFtpOrder);
            orderEntity.setOrderState(3);
            orderEntity.setRetCode("9999");
            orderEntity.setRetMessage("处理失败");
            try {
                fundOrderService.update(orderEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }else if(result > 2){
            orderEntity.setOrderState(result ==3?998:999);
            orderEntity.setRetCode("0009");
            orderEntity.setRetMessage("需手动处理");
            try {
                fundOrderService.update(orderEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        Long sourceId = orderEntity.getOrderFrormId();
        int size = fundSequenceService.getSizeByOrderNo(orderEntity.getOrderNo());
        if(size>0){
            return;
        }
        int type = orderEntity.getOrderType();
        try {
           if (type == GlobalConstants.ORDER_SETTLE_NEW) {
                settleService.settleCallback(orderEntity);
            } else if (type == GlobalConstants.ORDER_REPAYMENT_NEW) {
               bidRepaymentService.BidRepaymentCallback(orderEntity);
            }else if (type == GlobalConstants.ORDER_ABORT_BID_NEW) {
               abortService.bidAbortCallback(orderEntity);
           }else if(type == GlobalConstants.ORDER_REPAYMENT_REFUND){
               bidRepaymentService.refundsCallBack(orderEntity);
           }
            fuiouFtpOrder.setRetrunResultStatus(1);
        }catch (RuntimeException e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            fuiouFtpOrder.setRetrunResultStatus(0);
        } catch (FssException e) {
            fuiouFtpOrder.setRetrunResultStatus(0);
        }
        fuiouFtpOrderService.update(fuiouFtpOrder);
    }

    public  void notReturnResult() throws FssException{
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listNotReturnResult();
        if(list == null || list.size() == 0){
            return;
        }
        for(Object object:list){
            FuiouFtpOrder fuiouFtpOrder = (FuiouFtpOrder) object;
            System.out.println("fuiouFtp:notReturnResult:"+fuiouFtpOrder.getOrderNo());
            FundOrderEntity orderEntity = fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
            returnResult(orderEntity,fuiouFtpOrder);
        }
    }
}
