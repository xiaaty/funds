package com.gqhmt.funds.trade;

import com.gqhmt.TestService;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.WithholdApplyService;
import com.gqhmt.util.ThirdPartyType;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Filename:    com.gqhmt.sys.service.MenuServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/18 23:54
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  于泳      1.0     1.0 Version
 */

public class WithholdApplyServiceTest extends TestService {

    @Resource
    private WithholdApplyService withholdApplyService;

    @Test
    public void findWithholdApply() throws Exception{
    	 WithholdApplyEntity withholdInfo = withholdApplyService.getWithholdInfo(1145L);
    	 ThirdPartyType thirdPartyType = ThirdPartyType.getThirdPartyType(withholdInfo.getThirdPartyType());
    	 System.out.println(thirdPartyType+"------------------------------");
//    		assert withholdInfo.getId()==2L;
    }
    @Test
    public void insertWithholdApply() throws Exception{
    	WithholdApplyEntity withholdInfo = new WithholdApplyEntity();
    	withholdInfo.setApplyTime(new Date());
    	withholdInfo.setAccountId(22222);;
    	withholdInfo.setApplyStatus(2);
    	withholdInfo.setApplyUserId(55);
    	withholdApplyService.insert(withholdInfo);
    }
    @Test
    public void updateWithholdApply() throws Exception{
    	WithholdApplyEntity withholdInfo = new WithholdApplyEntity();
    	withholdInfo.setId(1943L);
    	withholdInfo.setApplyTime(new Date());
    	withholdInfo.setAccountId(555555);;
    	withholdInfo.setApplyStatus(6);
    	withholdInfo.setApplyUserId(999);
    	withholdApplyService.update(withholdInfo);
    }


}
