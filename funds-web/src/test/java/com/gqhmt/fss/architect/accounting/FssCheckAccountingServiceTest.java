package com.gqhmt.fss.architect.accounting;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.gqhmt.fss.architect.accounting.FssCheckAccountingServiceTest
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author wanggp
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:  2016/11/8 0008.
 * Description:
 * <p/>
 * Modification History:
 * Date      Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/8 0008. wanggp         1.0     1.0 Version
 */
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class FssCheckAccountingServiceTest extends AbstractTestNGSpringContextTests {

    @Resource
    private FssCheckAccountingService fssCheckAccountingService;

    @Test
    public void testQueryOrderNo() throws FssException {
        fssCheckAccountingService.checkHistoryAccount("20160830");
    }

}
