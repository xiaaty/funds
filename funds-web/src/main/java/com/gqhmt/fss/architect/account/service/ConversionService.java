package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.pay.service.TyzfTradeService;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/11/10
 * Description:对象参数转换为统一报文
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/11/10  keyulai      1.0     1.0 Version
 */
@Service
public class ConversionService {
    @Resource
    private TyzfTradeService tyzfTradeService;


    /**
     * 接收消息
     * @param msg
     * @return
     * @throws FssException
     */
    public void ReceiveMqMsg(String msg) throws Exception {
        try {
            MsgObject mo2 = new MsgObject(msg);
            MessageConvertDto bm = mo2.getMessge4Bean(MessageConvertDto.class);
            tyzfTradeService.asyncallBack(bm);
        } catch (FrameException e) {
            LogUtil.error(this.getClass(), e.getMessage());
        }catch (Exception e){
            LogUtil.error(this.getClass(), e.getMessage());
            throw new FssException("内部异常");
        }
    }
}
