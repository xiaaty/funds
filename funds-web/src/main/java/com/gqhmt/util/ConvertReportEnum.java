package com.gqhmt.util;


/**
 * 统一报文转换映射关系
 * @author zhaoenyue
 */
public enum ConvertReportEnum {
	OBJTOXML("objtoxml", "/msgTemplate/toChannelReport/UniteChange.xml");
    private String code;
    private String filePath;
    
    ConvertReportEnum(String code, String filePath){
    	this.code = code;
    	this.filePath = filePath;
    }
    
    public static ConvertReportEnum codeOf(String code) {
        for (ConvertReportEnum state : ConvertReportEnum.values()) {
            if (state.getCode().equals(code)) {
                return state;
            }
        }
		return null;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
    
}
