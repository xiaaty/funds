package com.gqhmt.business.architect.loan.mapper.read;

import java.util.List;

import com.github.pagehelper.Page;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.core.mybatis.ReadMapper;
import org.apache.ibatis.annotations.Param;

public interface BidReadMapper extends ReadMapper<Bid> {

    public Page querylist(Bid bid);

    /**
     * 查询合同编号
     * 
     * @param id
     * @param contractNo
     */
    public Long queryContractNo(String id, String contractNo);

    /**
     * 查询合同编号是否存在
     * 
     * @param customerId
     * @param bidId
     */
    public Long queryUserBidInfo(String customerId, String bidId, String mortgageNumber, String loanType);

    
    public Bid getBidByContractNo(@Param("contractNo") String contractNo);
    
}
