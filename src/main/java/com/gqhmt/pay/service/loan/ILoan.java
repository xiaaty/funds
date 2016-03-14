package com.gqhmt.pay.service.loan;


import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;

/**
 * Filename:    com.gqhmt.pay.service.loan.ILoan
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/6 22:38
 * Description: 开户
 * <p>开户
 * <p>流标接口
 * <p>放款给借款人
 * <p>抵押权人提现接口
 * <p>入账
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/6  于泳      1.0     1.0 Version
 */
public interface ILoan {

    /**
     * 
     * author:jhz
     * time:2016年3月7日
     * function：放款
     */
	public boolean lending(LendingDto dto) throws FssException;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：抵押权人提现接口
	 */
	public boolean mortgageeWithDraw(MortgageeWithDrawDto dto) throws FssException;
	/**
	 * .
	 * author:jhz
	 * time:2016年3月7日
	 * function：流标接口
	 */
	public boolean failedBid(FailedBidDto dto)throws FssException;
	
	/**
	 * .
	 * author:jhz
	 * time:2016年3月9日
	 * function：入账接口
	 */
	public boolean enterAccount(EnterAccountDto enterAccountDto)throws FssException;
	/**
	 * 
	 * author:kyl
	 * time:2016年3月9日
	 * function：开户
	 */
	public String createLoanAccount(CreateLoanAccountDto dto) throws FssException;
}
