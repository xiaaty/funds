package com.gqhmt.util;

import java.util.NoSuchElementException;

import org.springframework.util.Assert;

import com.gqhmt.util.GlobalConstants;

/**
 * Filename:    com.gq.funds.util
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/3/26 11:26
 * Description:第三方支付通道类型
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/3/26  于泳      1.0     1.0 Version
 */
public enum ThirdPartyType {
    /**
     * 大钱
     */
    DAQIAN(1),
    /**
     * 富友
     */
    FUIOU(2);
    private Integer key;

    private ThirdPartyType(int key){
        this.key = key;
    }

    public static ThirdPartyType getThirdPartyType(Integer key){
        for(ThirdPartyType thirdPartyTypeEnum : ThirdPartyType.values()){
            if(thirdPartyTypeEnum.getKey() .equals(key)){
                return thirdPartyTypeEnum;
            }
        }

        throw new NoSuchElementException(key.toString());
    }
    
    public static boolean isSyncByThirdpartyType(Integer key){
    	Assert.notNull(key);
    	if(key.equals(ThirdPartyType.DAQIAN.getKey())){
    		return true;
    	}else if(key.equals(ThirdPartyType.FUIOU.getKey())){
    		return false;
    	}
    	throw new NoSuchElementException(key.toString());
    }

    public Integer getKey(){
        return key;
    }

    @Override
    public String toString() {
        return GlobalConstants.thirdpartyType.get(this.getKey());
    }
    public String toENString() {
        return GlobalConstants.thirdpartyTypeEN.get(this.getKey());
    }
}
