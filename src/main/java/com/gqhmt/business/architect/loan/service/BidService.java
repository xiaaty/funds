package com.gqhmt.business.architect.loan.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.swing.SortOrder;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.Page;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.mapper.read.BidReadMapper;
import com.gqhmt.fss.architect.account.bean.CustomerInfoEntity;
import com.gqhmt.fss.architect.account.bean.FundAccountEntity;
import com.gqhmt.fss.architect.account.bean.UserEntity;
import com.gqhmt.fss.architect.account.command.AccountCommand;
import com.gqhmt.fss.architect.account.command.CommandEnum;
import com.gqhmt.fss.architect.account.exception.NeedSMSValidException;
import com.gqhmt.fss.architect.order.entity.Order;
import com.gqhmt.fss.architect.trade.bean.WithdrawApplyEntity;
import com.gqhmt.fss.architect.trade.bean.WithholdApplyEntity;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.fss.pay.exception.LazyDealException;
import com.gqhmt.fss.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.StringUtils;
import com.gqhmt.util.ThirdPartyType;

import freemarker.template.utility.DateUtil;

/**
 * 标的管理
 * 
 * @author yuankang
 */
@Service
public class BidService {

	@Autowired
	private BidReadMapper bidReadMapper;

	/**
	 * 标的列表
	 * 
	 * @param id
	 * @param id
	 * @return
	 */

	public Bid findById(int id) {
		Bid bid = bidReadMapper.selectByPrimaryKey(id);
		return bid;
	}
	
	public Page querylist(Bid bid) {
		return bidReadMapper.querylist(bid);
	}

    /**
     * 查询合同编号是否存在
     * 
     * @param customerId
     * @param bidId
     */
    public Long queryUserBidInfo(String customerId, String bidId, String mortgageNumber, String loanType) {
    	return bidReadMapper.queryUserBidInfo(customerId, bidId, mortgageNumber, loanType);
    }

}
