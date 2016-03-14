package com.gqhmt.funds.architect.account.service;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.funds.architect.account.entity.Notice;
import com.gqhmt.funds.architect.account.mapper.write.NoticeWriteMapper;

@Service
public class NoticeService {

	private static final String PREFIX_NOTICE = "尊敬的用户:\n ";

	@Resource
	private NoticeWriteMapper noticeWriteMapper;

	public void sendNotice(NoticeType noticeType, int userId, int customerId, String... noticeBodyParams) {
		StringBuilder sb = new StringBuilder();
		sb.append(PREFIX_NOTICE);
		String noticeArray[] = noticeType.getDescription().split("-");
		String noticeBody = noticeArray[1];
		for (int i = 0; i < noticeBodyParams.length; i++) {
			noticeBody = noticeBody.replaceFirst("\\|", noticeBodyParams[i]);
		}
		sb.append(noticeBody);

		Notice notice = new Notice();
		notice.setCustomerId(customerId);
		notice.setTitle(noticeArray[0]);
		notice.setContent(sb.toString());
		notice.setSendTime(new Date());
		notice.setState(0);
		notice.setCreateTime(new Date());
		noticeWriteMapper.insertSelective(notice);
	}
	
	public Integer sendDebtNotice(NoticeType noticeType, int userId, int customerId, String... noticeBodyParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NOTICE);
        String noticeArray[] = noticeType.getDescription().split("-");
        String noticeBody = noticeArray[1];
        for (int i = 0; i < noticeBodyParams.length; i++) {
            noticeBody = noticeBody.replaceFirst("\\|", noticeBodyParams[i]);
        }
        sb.append(noticeBody);

        Notice notice = new Notice();
        notice.setCustomerId(customerId);
        notice.setTitle(noticeArray[0]);
        notice.setContent(sb.toString());
        notice.setSendTime(new Date());
        notice.setState(0);
        notice.setCreateTime(new Date());
        int i=noticeWriteMapper.insertSelective(notice);
        return Integer.valueOf(i);
    }
	
	public void removeById(Integer noticeId){
		noticeWriteMapper.deleteByPrimaryKey(noticeId);
	}

	public enum NoticeType {
		TENDER_NOTICE("出借冻结-您出借的【|】于【|】出借冻结金额【|】元，详情请在[我的账户]>[我的出借]中查看，如需帮助请致电4006526818。"),
        SETTLE_NOTICE("满标出借成功-您出借的【|】于【|】满标计息，开始赚钱啦！详情请在【我的账户】>【我的出借】中查看，多投多赚让每一分发挥最大价值。"),
        ABORT_BID_NOTICE("满标出借失败-很遗憾，您出借的【|】已撤销，出借金额【|】元会尽快解冻并返回您的账户，去看看其他出借项目吧！"),
        REPAY_NOTICE("回款成功- 您参与出借的项目【|】于【|】收到回款【|】元，资金不闲置，继续出借赚起来！详询4006526818。"),
        DEBT_NOTICE("转让申请成功-您于【|】转让【|】项目，现已申请成功，转让结果请耐心等待。"),
        DEBT_AUDIT_PASS_NOTICE("转让审核通过-债权【|】,转让债权【|】元"),
        DEBT_AUDIT_NOTPASS_NOTICE("转让审核不通过-债权【|】,转让债权|元"),
        DEBT_DEAL_NOTICE("转让成功-您于【|】成功转让【|】项目，成交价【|】元，服务费【|】元。"),
        DEBT_AUCTION_NOTICE("债权转让承接成功-您于【|】承接的【|】债权转让项目承接成功。"),
        FUND_CHARGE("充值成功-您的账户于【|】充值【|】元，请在[我的账户]中查看，点击[出借]即刻开启财富增值之旅！详询4006526818。"),
        FUND_WITHHOLDING("代扣成功-代扣充值【|】元"),
        FUND_WITHDRAWAPPLY("提现申请已提交-您于【|】申请提现成功，冻结提现金额【|】元，请注意查收。"),
        FUND_WITHDRAW("提现成功-您的账户于【|】提现【|】元，提现手续费【|】元，具体请以实际到账情况为准。如需帮助请致电4006526818。"),
        FUND_WITHDRAWAPPLYCANCLE("提现失败-非常抱歉您申请提现【|】元失败，【|】，为了不耽误您的资金使用请您重新申请。详询4006526818，我们将竭诚为您服务。"),
        FUND_UPDATE_BANKCARD_SUBMIT("变更提交-您正在提交新银行卡变更业务，新银行卡号：【|】已提交变更。如非本人操作，请及时联系客服：4006526818"),
        FUND_UPDATE_BANKCARD_SUCESS("变更成功-您提交的新银行卡变更成功，新银行卡号：【|】，资料审核通过，变更成功.如非本人操作，请及时联系客服：4006526818。"),
        FUND_UPDATE_BANKCARD_FAIL("变更失败-您提交的银行卡变更失败，失败原因：【|】。为保证您的业务正常办理，原银行卡号：【|】仍可继续使用。如非本人操作，请及时联系客服：4006526818"),
        RECEIVE_BONUS("获得奖励红包-恭喜您获得【|】元红包奖励，请在[我的宝箱]>[我的红包]查看，赶快去出借，赢取额外收益！"),
        RECEIVE_CASH_COUPON("获得现金券-恭喜您获得【|】元现金券，请及时领取，好事从天而降，抓紧机会哦。"),
        GET_CASH_COUPON_SUCCESS("领取优惠券-您已成功领取一张【|】元现金券，可在[账户余额]中查看。还等什么，去出借赚取收益吧！"),
        CASH_COUPON_PAST_NOTICE("优惠券过期提醒-您有【|】张优惠券将在2天后过期，面值【|】，记得使用哦!")
        ;
		private String description;

		private NoticeType(String name) {
			this.description = name;

		}

		public String getDescription() {
			return description;
		}
	}
	
}
