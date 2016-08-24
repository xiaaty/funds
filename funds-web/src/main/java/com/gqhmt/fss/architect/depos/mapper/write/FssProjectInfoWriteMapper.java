package com.gqhmt.fss.architect.depos.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.depos.bean.FssProjectInfoBean;
import com.gqhmt.fss.architect.depos.entity.FssProjectInfoEntity;
/**
 * Filename:    com.gqhmt.quartz.mapper.write.FssQuartzWriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 10:15
 * Description:项目信息
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  柯禹来      1.0     1.0 Version
 */
public interface FssProjectInfoWriteMapper extends ReadAndWriteMapper<FssProjectInfoEntity> {
    /**
     * jhz
     * 添加项目信息
     * @param fssProjectInfoEntity
     */
   public void insertProjectInfo(FssProjectInfoEntity fssProjectInfoEntity);
}
