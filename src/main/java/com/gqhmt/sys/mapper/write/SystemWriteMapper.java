package com.gqhmt.sys.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.sys.entity.DictMain;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.sys.mapper.read.MenuReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/18 23:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/18  于泳      1.0     1.0 Version
 */
public interface SystemWriteMapper extends ReadAndWriteMapper<DictMain> {

    public List<DictMain> selectDictmain(DictMain dictmain);
    
    public void insertDictmain(DictMain dict);
    
    public void updateDictMain(DictMain dict);
    
    public void delteDictMain(String dictId);
}
