package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.service.TyzfTradeService;
import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.List;

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
     * 异步发送mq消息请求
     * @param bean
     * @throws FssException
     */
/*    public void sendAsynRequest(MessageConvertDto bean) throws FssException{
        Runnable runnable = this.sendMsgToMq(bean);
        if(runnable != null ){
            ThreadExecutor.execute(runnable);
        }
    }

    public Runnable sendMsgToMq(final MessageConvertDto bean){
        Runnable thread = new Runnable(){
            @Override
            public void run() {
                try {
                    sendAndReceiveMsg(bean);
                } catch (FssException e) {
                    e.printStackTrace();
                }
            }
        };
        return thread;
    }*/
    /**
     * 统一报文发送到MQ
     * @param bean
     * @return
     */
    public void sendAndReceiveMsg(MessageConvertDto bean) throws FssException {
        try {
            AmqSendAndReceive asr = new AmqSender(null, CoreConstants.MQ_SEND_NAME);//发送到该队列
            //将bean转换成xml统一报文
            MsgObject mo = new MsgObject(MsgObject.initSR);
            bean = mo.getBean4Message(bean);
            String sendMessage = mo.toString();
            LogUtil.info(this.getClass(),"发送报文信息:"+sendMessage);
            asr.sendMsg(sendMessage);
        } catch (FrameException e) {
            LogUtil.error(this.getClass(), e.getMessage());
        } catch (Exception e) {
            LogUtil.error(this.getClass(), e.getMessage());
        }
    }

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
        }
    }
}
