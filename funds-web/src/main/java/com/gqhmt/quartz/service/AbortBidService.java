package com.gqhmt.quartz.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.fetchService.FetchDataService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.entity.FuiouPreauth;
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import com.gqhmt.pay.service.tender.IFundsTender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 2015/5/10.
 */
@Service
public class AbortBidService {

    @Resource
    protected FundOrderService fundOrderService;

    @Resource
    protected BidService bidService;

    @Resource
    protected TenderService tenderService;

    @Resource
    protected FuiouPreauthService fuiouPreauthService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private FetchDataService fetchDataService;

    @Resource
    private FssLoanService fssLoanService;

    @Resource
    private IFundsTender fundsTender;
    
    @Resource
    private FssBackplateService fssBackplateService;

    public void abortBid() throws FssException{
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listAbort();
        for(FuiouFtpOrder fuiouFtpOrder:list){
            abortBid(fuiouFtpOrder);
        }
    }

    public void abortBid(FuiouFtpOrder fuiouFtpOrder) throws FssException{

        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(fuiouFtpOrder.getOrderNo());
        if(fundOrderEntity.getOrderType() != GlobalConstants.ORDER_ABORT_BID){
            return ;
        }

//        Bid bid = bidService.findById(fundOrderEntity.getOrderFrormId());
        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11090012".equals(loanEntity.getTradeType())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }

        Bid bid = null;
        List<Tender> list  = null;
        try {
            //获取bid信息
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            //获取投标列表
            list = fetchDataService.featchData(Tender.class,"tenderList",paramMap);
            //数据回盘
//			fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(), loanEntity.getMchnChild(), loanEntity.getTradeType());
        } catch (FssException e) {
        	loanEntity.setStatus("10050014");
        	loanEntity.setModifyTime(new Date());
        	fssLoanService.update(loanEntity);
        	//数据回盘
			fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(), loanEntity.getMchnChild(), loanEntity.getTradeType());
            LogUtil.error(getClass(),e);
            return;
        }


        Integer cusId = bid.getCustomerId();
        Map<Long,FuiouPreauth> map = fuiouPreauthService.getFuiouPreauth(bid.getId().longValue());
        if(bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() >0){
            cusId = bid.getHypothecarius();
        }

        int falidSize =  0;

        for(Tender tender:list){
            FuiouPreauth fuiouPreauth  = map.get(tender.getId());
            if(fuiouPreauth.getState() == 2){
                continue;
            }
            try {
//                AccountCommand.payCommand.command(CommandEnum.TenderCommand.TENDER_ABORT_ASYN, ThirdPartyType.FUIOU, tender,fuiouPreauth.getContractNo());

                fundsTender.abortLoop(bid,tender,fuiouPreauth.getContractNo());
                fuiouPreauth.setState(2);
                fuiouPreauthService.update(fuiouPreauth);
                System.out.println("fuiouFtp:abortBid:success:"+fuiouFtpOrder.getOrderNo());
            }catch (Exception e){
                fuiouPreauth.setState(3);
                fuiouPreauthService.update(fuiouPreauth);
                falidSize++;
                System.out.println("fuiouFtp:abortBid:failed:"+fuiouFtpOrder.getOrderNo());
            }
        }

        if(falidSize == 0){
            fuiouFtpOrder.setUploadStatus(3);
            fuiouFtpOrder.setDownloadStatus(4);
            fuiouFtpOrder.setResultStatus(3);
            fuiouFtpOrder.setResult(1);
            fuiouFtpOrder.setRetrunResultStatus(1);
            loanEntity.setStatus("10050100");
            loanEntity.setModifyTime(new Date());
            fssLoanService.update(loanEntity);
          //数据回盘
			fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(), loanEntity.getMchnChild(), loanEntity.getTradeType());
        }
        else if(falidSize == list.size()){
            fuiouFtpOrder.setUploadStatus(3);
            fuiouFtpOrder.setDownloadStatus(4);
            fuiouFtpOrder.setResultStatus(3);
            fuiouFtpOrder.setResult(2);
            fuiouFtpOrder.setRetrunResultStatus(1);
            
            loanEntity.setStatus("10050102");
            loanEntity.setModifyTime(new Date());
            fssLoanService.update(loanEntity);
          //数据回盘
			fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(), loanEntity.getMchnChild(), loanEntity.getTradeType());
        }
        try {
			fuiouFtpOrderService.update(fuiouFtpOrder);
		} catch (FssException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
                fuiouPreauthService.update(fuiouPreauth);
                System.out.println("fuiouFtp:BidFailed:success:"+fuiouPreauth.getOrderNo());
            }catch (FssException e){
                System.out.println("fuiouFtp:BidFailed:failed:"+fuiouPreauth.getOrderNo());
            }*/
        }
    }

}
