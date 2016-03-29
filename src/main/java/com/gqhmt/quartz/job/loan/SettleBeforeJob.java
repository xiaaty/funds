package com.gqhmt.quartz.job.loan;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.fuiouFtp.service.SettleService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.quartz.job.SupperJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.loan.SettleBeforeJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/15 17:00
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/15  于泳      1.0     1.0 Version
 */
@Component
public class SettleBeforeJob extends SupperJob {
    @Resource
    private FssLoanService fssLoanService;
    @Resource
    private SettleService settleService;

    public void execute(){
        //获取需满标转账功能列表 抵押权人提现\信用标放款

        List<FssLoanEntity> loanEntities = fssLoanService.findLoanBySettle();

        for (FssLoanEntity loanEntity:loanEntities){
            try {
                settleService.settle(loanEntity);
            } catch (FssException e) {
                LogUtil.error(getClass(),e);
                continue;
            }
        }

    }


}
