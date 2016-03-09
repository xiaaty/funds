package com.gqhmt.business.architect.loan.service;

import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.mapper.read.TenderReadMapper;
import com.gqhmt.business.architect.loan.mapper.write.TenderWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.ProductSalesPackEntity;

import java.util.Date;
import java.util.List;

/**
 * @author fanfever
 * @date 2015年1月15日
 * @time 下午3:50:32
 */
@Service
public class TenderService {
	
	@Autowired
	private TenderReadMapper tenderReadMapper;
	
	@Autowired
	private TenderWriteMapper tenderWriteMapper;
	
	@Autowired
	private ProductSalesPackService productSalesPackService;
	
	@Autowired
	private BidService bidService;
	
	public Tender selectOne(Tender tender) {
		return tenderReadMapper.selectOne(tender);
	}
	
	public List<Tender> select(Tender tender) {
		return tenderReadMapper.select(tender);
	}
	
	public void update(Tender tender) {
		tenderWriteMapper.updateByPrimaryKeySelective(tender);
	}

	public Tender findById(long id) {
		Tender tender = tenderReadMapper.selectByPrimaryKey(id);
		return tender;
	}
	/**
	 * 根据bidId查询投标信息
	 * @param bidId
	 * @return
	 */
	public List<Tender> queryTenderByBidId(Long bidId){
		return tenderReadMapper.getTenderByBidId(bidId);
	}
	
	
	public void updateCallbackFlowBidStatus(Integer bidId, boolean isSuccess, int sysUserId) throws RuntimeException {
		Bid bid = bidService.findById(Long.valueOf(bidId));
		if (bid.getPayState().intValue()!=11) {
			// throw new RuntimeException("流标回调接口错误:支付状态未流转到此节点");
		} else {
			if (isSuccess) {
				updateFlowBidStatus(bid, sysUserId);
				bid.setPayState(99);
				bid.setBidState(6);
			} else {
				bid.setPayState(13);
			}
			bid.setModifyUserId(sysUserId);
			bid.setModifyTime(new Date());
			bidService.updateBid(bid);
		}
	}
	
	/**
	 * 修改流标状态
	 * @param bid
	 * @param sysUserId
	 * @throws RuntimeException
	 */
	@SuppressWarnings("unchecked")
	private void updateFlowBidStatus(Bid bid, int sysUserId) throws RuntimeException {
		String paramName=bid.getBidTitle();
		ProductSalesPackEntity productSalesPack = productSalesPackService.queryProductSalesPackInfoByBid(bid.getId());
		if(productSalesPack!=null){
			paramName=productSalesPack.getProductName();
		}
/*		List<Tender> tenderList = tenderDao.createCriteria(Tender.class).add(Restrictions.eq("bidId", bid.getId())).list();
		for (Tender tender : tenderList) {
			if (null != tender.getInvestmentId()) {
				InvestmentEntity investment = investmentDao.get(tender.getInvestmentId());
				investment.setSurplusInvestAmount(Arith.returnBigDecimal(Arith.add(investment.getSurplusInvestAmount().doubleValue(), tender.getInvestAmount())));
				investment.setModifyUserId(sysUserId);
				investment.setModifyTime(new Date());
				bid.setBidSurplusAmount(Arith.returnBigDecimal(Arith.sub(bid.getBidSurplusAmount().doubleValue(), tender.getInvestAmount())));
				investmentDao.update(investment);
			} else {
				noticeService.sendNotice(NoticeType.ABORT_BID_NOTICE, tender.getUserId(), tender.getCustomerId(),paramName, CommonUtil.getDecimalFormat(String.valueOf(tender.getInvestAmount())));
			}
			tender.setState(TenderConstants.TENDER_STATE_FLOW);
			tender.setModifyTime(new Date());
			tender.setModifyUserId(String.valueOf(sysUserId));
			tenderDao.update(tender);
			bid.setBidState(BidStatus.BID_FLOW);
			bid.setModifyTime(new Date());
			bid.setModifyUserId(sysUserId);
			bidDao.update(bid);
			productSalesPackService.updatePackStateByBidId(bid.getId(), sysUserId, 6, 9);
		}*/
	}
	
	
	
}
