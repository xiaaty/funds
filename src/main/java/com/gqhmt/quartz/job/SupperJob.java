package com.gqhmt.quartz.job;

import org.quartz.Job;

/**
 * Filename:    com.gqhmt.quartz.job.SupperJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/25 10:05
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/25  于泳      1.0     1.0 Version
 */
public abstract class SupperJob implements Job {

    protected boolean isRunning ;

    public final boolean isRunning() {
        return isRunning;
    }
}
