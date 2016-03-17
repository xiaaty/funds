package com.gqhmt.pay.fuiou.service;

import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.entity.FuiouPreauth;
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 2015/5/10.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED,
        isolation= Isolation.READ_COMMITTED,
        noRollbackFor={FssException.class},
        readOnly=false)
public class AbortBidService {

    @Resource
    protected FundOrderService fundOrderService;

    @Resource
    protected BidService bidService;

    @Resource
    protected TenderService tenderService;

    @Resource
    protected FuiouPreauthService fuiouPreauthService ;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    public void abortBid(){
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listAbort();
        for(FuiouFtpOrder fuiouFtpOrder:list){
            abortBid(fuiouFtpOrder);
        }
    }

    public void abortBid(FuiouFtpOrder fuiouFtpOrder){

        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
        if(fundOrderEntity.getOrderType() != GlobalConstants.ORDER_ABORT_BID){
            return ;
        }
        Bid bid = bidService.findById(fundOrderEntity.getOrderFrormId());
        Integer cusId = bid.getCustomerId();
        Map<Integer,FuiouPreauth> map = fuiouPreauthService.getFuiouPreauth(bid.getId().longValue());
        if(bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() >0){
            cusId = bid.getHypothecarius();
        }
        List<Tender> list = tenderService.queryTenderByBidId(Long.valueOf(bid.getId()));

        int falidSize =  0;

        for(Tender tender:list){
            FuiouPreauth fuiouPreauth  = map.get(tender.getId());
            if(fuiouPreauth.getState() == 2){
                continue;
            }
            try {
//                AccountCommand.payCommand.command(CommandEnum.TenderCommand.TENDER_ABORT_ASYN, ThirdPartyType.FUIOU, tender,fuiouPreauth.getContractNo());
                fuiouPreauth.setState(2);
                fuiouPreauthService.insert(fuiouPreauth);
                System.out.println("fuiouFtp:abortBid:success:"+fuiouFtpOrder.getOrderNo());
            }catch (Exception e){
                fuiouPreauth.setState(3);
                fuiouPreauthService.insert(fuiouPreauth);
                falidSize++;
                System.out.println("fuiouFtp:abortBid:failed:"+fuiouFtpOrder.getOrderNo());
            }
        }

        if(falidSize == 0){
            tenderService.updateCallbackFlowBidStatus(fundOrderEntity.getOrderFrormId().intValue(),true,0);
            fuiouFtpOrder.setUploadStatus(3);
            fuiouFtpOrder.setDownloadStatus(4);
            fuiouFtpOrder.setResultStatus(3);
            fuiouFtpOrder.setResult(1);
            fuiouFtpOrder.setRetrunResultStatus(1);
        }
//        else if(falidSize == list.size()){
//            tenderService.updateCallbackFlowBidStatus(fundOrderEntity.getOrderFrormId().intValue(),false,0);
//            fuiouFtpOrder.setUploadStatus(3);
//            fuiouFtpOrder.setDownloadStatus(4);
//            fuiouFtpOrder.setResultStatus(3);
//            fuiouFtpOrder.setResult(2);
//            fuiouFtpOrder.setRetrunResultStatus(1);
//        }

        fuiouFtpOrderService.insert(fuiouFtpOrder);

    }



    public void bidFailed(){
        List<FuiouPreauth> list = this.fuiouPreauthService.bidFaild();
        if(list == null || list.size() == 0){
            return;
        }

        for(FuiouPreauth fuiouPreauth:list){

           /* try {
                AccountCommand.payCommand.command(CommandEnum.TenderCommand.TENDER_BID_FAILED_RETURN, ThirdPartyType.FUIOU, fuiouPreauth);
                fuiouPreauth.setState(2);
                fuiouPreauthService.insert(fuiouPreauth);
                System.out.println("fuiouFtp:BidFailed:success:"+fuiouPreauth.getOrderNo());
            }catch (FssException e){
                System.out.println("fuiouFtp:BidFailed:failed:"+fuiouPreauth.getOrderNo());
            }*/
        }
    }

}
