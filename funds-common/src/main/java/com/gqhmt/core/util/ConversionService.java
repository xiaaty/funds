package com.gqhmt.core.util;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import com.gqhmt.tyzf.common.frame.message.MsgObject;

/**
 * Filename:    com.gqhmt.core.util.ConversionService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/1 15:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/1  于泳      1.0     1.0 Version
 */
public class ConversionService {

    /**
     * 统一报文发送到MQ
     * @param bean
     * @return
     */
    public void sendAndReceiveMsg(MessageConvertDto bean) throws FssException {
        try {
            AmqSendAndReceive asr = new AmqSender(null, ResourceUtil.getValue("conf.mq","mq_send_name"));//发送到该队列
            //将bean转换成xml统一报文
            MsgObject mo = new MsgObject(MsgObject.initSR);
            mo.getBean4Message(bean);
            String sendMessage = mo.toString();
            LogUtil.info(this.getClass(),"发送报文信息:"+sendMessage);
            asr.sendMsg(sendMessage);
        } catch (FrameException e) {
            LogUtil.error(this.getClass(), e.getMessage());
        } catch (Exception e) {
            LogUtil.error(this.getClass(), e.getMessage());
        }
    }
}
