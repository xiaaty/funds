package com.gqhmt.quartz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gqhmt.business.architect.loan.service.BidRepaymentService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouUploadFileService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chris on 2015/5/10.
 */
@Transactional(propagation= Propagation.REQUIRED,
        isolation= Isolation.READ_COMMITTED,
        noRollbackFor={FssException.class},
        readOnly=false)
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
    private TenderService tenderService;

    @Resource
    private BidRepaymentService bidRepaymentService;


    @Resource
    private FundSequenceService fundSequenceService;

    /**
     * 解析下载文件状态
     */
    public void parseDownloadResult(){
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listNotDownload();
        if(list == null || list.size() == 0){
            return;
        }

        for(Object fuiouFtpOrder:list){
            List<Integer> state = fuiouUploadFileService.list(((FuiouFtpOrder)fuiouFtpOrder).getOrderNo());
            if(state == null || state.size() == 0){
                continue;
            }
            if(state.size() == 1 && (state.get(0) == 3 || state.get(0) == 5)){
                ((FuiouFtpOrder)fuiouFtpOrder).setDownloadStatus(4);
                continue;
            }

            System.out.println("fuiouFtp:parseDownloadResult:"+((FuiouFtpOrder) fuiouFtpOrder).getOrderNo());
        }
        fuiouFtpOrderService.saveAll(list);
    }

    /**
     * 解析结果
     */
    public void parseResult(){

        List<FuiouFtpOrder> list = fuiouFtpOrderService.listNotResultStatus();
        if(list == null || list.size() == 0){
            return;
        }

        for(Object object:list){
            FuiouFtpOrder fuiouFtpOrder = (FuiouFtpOrder)object;
            parseResult(fuiouFtpOrder);
        }

        fuiouFtpOrderService.saveAll(list);
    }

    /**
     * 对单一订单执行结果处理
     * @param order
     */

    public void parseResult(FuiouFtpOrder order){
        System.out.println("fuiouFtp:parseResult:"+order.getOrderNo());
        List<String> reqCode  = fuiouFtpColomFieldService.getReqCode(order.getOrderNo());
        if(reqCode == null || reqCode.size() == 0){
            return;
        }
        String reqCodeMsg = reqCode.get(0);
        if(reqCode.size() == 1 ){
            if("0000".equals(reqCodeMsg)){
                order.setResultStatus(3);
                order.setResult(1);
            }else if("5138".equals(reqCodeMsg) || "3201".equals(reqCodeMsg) || (reqCodeMsg!= null && reqCodeMsg.length() == 4 && "98".equals(reqCodeMsg.substring(0, 2)))){
                order.setResultStatus(3);
                order.setResult(4);
            }else{
                order.setResult(2);
                order.setResultStatus(3);

            }
        }else{
            for(String s:reqCode){
                if(s == null || "".equals(s)){
                    return;
                }
                if("5138".equals(reqCodeMsg) || "3201".equals(reqCodeMsg) || (reqCodeMsg!= null && reqCodeMsg.length() == 4 && "98".equals(reqCodeMsg.substring(0, 2)))){
                    order.setResultStatus(3);
                    order.setResult(4);
                    return;
                }else if(!"0000".equals(s)){
                    order.setResultStatus(3);
                    order.setResult(3);
                    return;
                }
                order.setResultStatus(3);
                order.setResult(2);
                order.setRetrunResultStatus(0);
            }
        }

    }


    //资金流水入账
    private void  callback(FundOrderEntity orderEntity,int result){
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
       /* if(result == 1) {
            AccountCommand.payCommand.command(CommandEnum.TenderCommand.TENDER_CALLBACK, ThirdPartyType.FUIOU, type, orderEntity.getOrderNo());
        }else{
            orderEntity.setOrderState(3);
        }*/
    }

    /**
     * 结果回调
     * @param orderEntity
     * @param fuiouFtpOrder
     */
    private void returnResult(FundOrderEntity orderEntity,FuiouFtpOrder fuiouFtpOrder){
        int result = fuiouFtpOrder.getResult();
        if(result >1 && result<3){
//            returnResult(orderEntity,fuiouFtpOrder);
            orderEntity.setOrderState(3);
            orderEntity.setRetCode("9999");
            orderEntity.setRetMessage("处理失败");
            try {
                fundOrderService.insert(orderEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }else if(result > 2){
            orderEntity.setOrderState(result ==3?998:999);
            orderEntity.setRetCode("0009");
            orderEntity.setRetMessage("需手动处理");
            try {
                fundOrderService.insert(orderEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        Long sourceId = orderEntity.getOrderFrormId();
        int size = fundSequenceService.getSizeByOrderNo(orderEntity.getOrderNo());
        if(size == 0){
            callback(orderEntity,result);
            size = fundSequenceService.getSizeByOrderNo(orderEntity.getOrderNo());
        }
        if(size<=0){
            return;
        }
        int type = orderEntity.getOrderType();
        try {
          /*  if (type == GlobalConstants.ORDER_SETTLE_UNFORZEN) {
                tenderService.updateCallbackFullBidStatus(sourceId.intValue(), result == 1 ? true : false, 0);
            } else if (type == GlobalConstants.ORDER_SETTLE) {
                tenderService.updateCallbackFullBidStatus(sourceId.intValue(), result == 1 ? true : false, 0);
            } else if (type == GlobalConstants.ORDER_REPAYMENT) {
                bidRepaymentService.updateCallbackBidRepaymentStatus(sourceId.intValue(), result == 1 ? true : false, 0);
            }*/
            fuiouFtpOrder.setRetrunResultStatus(1);
        }catch (RuntimeException e){
            LogUtil.error(this.getClass(),e.getMessage(),e);
            fuiouFtpOrder.setRetrunResultStatus(0);
        }
        fuiouFtpOrderService.insert(fuiouFtpOrder);
    }

    public  void notReturnResult(){
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
