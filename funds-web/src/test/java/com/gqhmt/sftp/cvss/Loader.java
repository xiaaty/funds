package com.gqhmt.sftp.cvss;

import java.util.List;

/** 
 * 逗号分割文件行数据加载接口 
 * @version 1.0 2011-02-17 
 * @author zhanzhengqiang 
 */ 
public interface  Loader {
	 /** 
     * 加载文件一行数据 
     * @param recFiledList 数据列表 
     * @throws Exception 加载数据异常 
     */  
    public void load(List recFiledList) throws Exception;  
}
