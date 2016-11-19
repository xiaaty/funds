package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.TextMessage;

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
    /**
     * 统一报文发送到MQ
     * @param bean
     * @return
     */
    public MessageConvertDto sendAndReceiveMsg(MessageConvertDto bean) throws FssException {
        MessageConvertDto bm=null;
        try {
            AmqSendAndReceive asr = new AmqSender(null, CoreConstants.MQ_SEND_NAME);//发送到该队列
            AmqSendAndReceive receiver = new AmqReceiver(CoreConstants.MQ_RESIVE_NAME);//从该队列接收
            String sendMessage = "";
            //将bean转换成xml统一报文
            MsgObject mo = new MsgObject(MsgObject.initSR);
            bean = mo.getBean4Message(bean);
            sendMessage=mo.toString();
            System.out.println(mo.toString());
            asr.sendMsg(sendMessage);//发送报文
           TextMessage resiveMsg=(TextMessage)receiver.receiveMessage();//监听队列中的报文
            String msg=resiveMsg.getText();//读取报文内容
            System.out.print("返回报文："+msg);
            MsgObject mo2 = new MsgObject(msg);
            bm = mo2.getMessge4Bean(MessageConvertDto.class);
        } catch (JMSException e){
            throw new FssException(e.getMessage());
        } catch (FrameException e){
            throw new FssException("");
        } catch (Exception e) {
            throw new FssException(e.getMessage());
        }
        return bm;
    }
}
