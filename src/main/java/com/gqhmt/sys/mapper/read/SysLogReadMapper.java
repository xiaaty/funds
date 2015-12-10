package com.gqhmt.sys.mapper.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.sys.beans.LogSearch;
import com.gqhmt.sys.beans.SysLog;

public interface SysLogReadMapper {
	public List<SysLog> selectLogs(SysLog sysLog);
  public List<SysLog> selectLogs1(LogSearch serch);
    public List<SysLog> selectMyLogs(@Param(value="userID") long userID);
}
