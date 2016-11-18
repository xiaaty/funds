package com.gqhmt.mqtt;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.MessageListenerImpl;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Administrator on 2016/11/8.
 */
public class TestNodeA {

    public static void main(String[] args) throws Exception{
        AmqSendAndReceive asr = new AmqSender(null, "tradeCheck");
        AmqSendAndReceive receiver = new AmqReceiver("AMQ.TTT3");

        StringBuilder sb = new StringBuilder();
            //转换为统一报文格式
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
        sb.append("<req>");
        sb.append("<requestHeader>");
        sb.append("<sys_node_flag></sys_node_flag>");
        sb.append("<is_actual></is_actual>");
        sb.append("<req_sys_id></req_sys_id>");
        sb.append("<serv_sys_id></serv_sys_id>");
        sb.append("<service_id>0001</service_id>");
        sb.append("<receive_time></receive_time>");
        sb.append("</requestHeader>");
        sb.append("<requestMsg>");
        sb.append("<PmtID>");
        sb.append("<OrderId>521235323198</OrderId>");
        sb.append("<MsgID></MsgID>");
        sb.append("<FrmtVrsn></FrmtVrsn>");
        sb.append("<ExTxnId></ExTxnId>");
        sb.append("<SplitFlg1></SplitFlg1>");
        sb.append("<SplitFlg2></SplitFlg2>");
        sb.append("<TxnTp>30120010</TxnTp>");
        sb.append("<TxnSubTp></TxnSubTp>");
        sb.append("<PmtTp></PmtTp>");
        sb.append("<BizTp>11020001</BizTp>");
        sb.append("<BizAttr></BizAttr>");
        sb.append("<ProcMd></ProcMd>");
        sb.append("<MsgSts></MsgSts>");
        sb.append("<ErrDesp></ErrDesp>");
        sb.append("<RespCode></RespCode>");
        sb.append("<RespCdDesp></RespCdDesp>");
        sb.append("<ReasonFlg></ReasonFlg>");
        sb.append("<ChkSts></ChkSts>");
        sb.append("<SignInfo></SignInfo>");
        sb.append("<RevFlg></RevFlg>");
        sb.append("<RevId></RevId>");
        sb.append("<RevDt></RevDt>");
        sb.append("<CustID></CustID>");
        sb.append("<CardTp></CardTp>");
        sb.append("<BatchIdIn></BatchIdIn>");
        sb.append("<BatchIdOut></BatchIdOut>");
        sb.append("<ChnReUrl></ChnReUrl>");
        sb.append("<ChnNotiUrl></ChnNotiUrl>");
        sb.append("<ExReUrl></ExReUrl>");
        sb.append("<ExNotiUrl></ExNotiUrl>");
        sb.append("<CapTm></CapTm>");
        sb.append("<TraceTm></TraceTm>");
        sb.append("<ExeTm></ExeTm>");
        sb.append("<ExSttlDt></ExSttlDt>");
        sb.append("<NsSttlDt></NsSttlDt>");
        sb.append("<OperateType></OperateType>");
        sb.append("<OutTxnId></OutTxnId>");
        sb.append("<PstgStatus></PstgStatus>");
        sb.append("</PmtID>");
        sb.append("<OrgnlMsgInfo>");
        sb.append("<OrgnlOrderId></OrgnlOrderId>");
        sb.append("<OrgnlMsgId></OrgnlMsgId>");
        sb.append("<OrgnlTxnTp></OrgnlTxnTp>");
        sb.append("<OrgnlSttlmAmt></OrgnlSttlmAmt>");
        sb.append("<OrgnlSttlmCcy></OrgnlSttlmCcy>");
        sb.append("</OrgnlMsgInfo>");
        sb.append("<Chnl>");
        sb.append("<ChnlID>2016-11-11 15:10:13</ChnlID>");
        sb.append("<OprtrID></OprtrID>");
        sb.append("<APPId></APPId>");
        sb.append("<ProdInfo>");
        sb.append("<MerchID>88721657SUKQ</MerchID>");
        sb.append("<ProdID></ProdID>");
        sb.append("<PrdInfo></PrdInfo>");
        sb.append("<PrdQty></PrdQty>");
        sb.append("</ProdInfo>");
        sb.append("<fastPayInfo>");
        sb.append("<Cvv></Cvv>");
        sb.append("<SmsCd></SmsCd>");
        sb.append("<ValidDt></ValidDt>");
        sb.append("<TokenCd></TokenCd>");
        sb.append("<PayFlg></PayFlg>");
        sb.append("<AuthId></AuthId>");
        sb.append("</fastPayInfo>");
        sb.append("</Chnl>");
        sb.append("<TxnAmtInfo>");
        sb.append("<IntrBkSttlmAmt></IntrBkSttlmAmt>");
        sb.append("<IntrBkSttlmCcy></IntrBkSttlmCcy>");
        sb.append("<ActSttlmAmt></ActSttlmAmt>");
        sb.append("<ActSttlmCcy></ActSttlmCcy>");
        sb.append("<RtrdIntrBkSttlmAmt></RtrdIntrBkSttlmAmt>");
        sb.append("<RtrdIntrBkSttlmCcy></RtrdIntrBkSttlmCcy>");
        sb.append("</TxnAmtInfo>");
        sb.append("<RoutInfo>");
        sb.append("<AssCdtrId></AssCdtrId>");
        sb.append("<CdtrId>97010001</CdtrId>");
        sb.append("<CdtrNm></CdtrNm>");
        sb.append("<KeyPath></KeyPath>");
        sb.append("<IsChange></IsChange>");
        sb.append("<ExMerchId></ExMerchId>");
        sb.append("</RoutInfo>");
        sb.append("<Dbtr>");
        sb.append("<Nm></Nm>");
        sb.append("<PorO></PorO>");
        sb.append("<BirthDt></BirthDt>");
        sb.append("<PrvcOfBirth></PrvcOfBirth>");
        sb.append("<CityOfBirth></CityOfBirth>");
        sb.append("<CtryOfBirth></CtryOfBirth>");
        sb.append("<IDType></IDType>");
        sb.append("<IDNo></IDNo>");
        sb.append("<ContactNo></ContactNo>");
        sb.append("<ContractNo></ContractNo>");
        sb.append("</Dbtr>");
        sb.append("<Cdtr>");
        sb.append("<Nm>keyulai</Nm>");
        sb.append("<PorO>30090001</PorO>");
        sb.append("<BirthDt></BirthDt>");
        sb.append("<PrvcOfBirth></PrvcOfBirth>");
        sb.append("<CityOfBirth></CityOfBirth>");
        sb.append("<CtryOfBirth></CtryOfBirth>");
        sb.append("<CrdtrTp></CrdtrTp>");
        sb.append("<IDType>1</IDType>");
        sb.append("<IDNo>420325199101133017</IDNo>");
        sb.append("<ContactNo></ContactNo>");
        sb.append("<ContractNo></ContractNo>");
        sb.append("</Cdtr>");
        sb.append("<DbtrAcct>");
        sb.append("<IdTp></IdTp>");
        sb.append("<IssrCd></IssrCd>");
        sb.append("<Issr></Issr>");
        sb.append("<Branch></Branch>");
        sb.append("<Id></Id>");
        sb.append("<ShortCard></ShortCard>");
        sb.append("<Ccy></Ccy>");
        sb.append("<Nm></Nm>");
        sb.append("<Tp></Tp>");
        sb.append("</DbtrAcct>");
        sb.append("<CdtrAcct>");
        sb.append("<IdTp>3</IdTp>");
        sb.append("<IssrCd></IssrCd>");
        sb.append("<Issr></Issr>");
        sb.append("<Branch></Branch>");
        sb.append("<Id></Id>");
        sb.append("<Ccy>30080001</Ccy>");
        sb.append("<Nm></Nm>");
        sb.append("<Tp></Tp>");
        sb.append("</CdtrAcct>");
        sb.append("</requestMsg>");
        sb.append("</req>");
        try {
            asr.sendMsg(sb.toString());
            } catch (AmqException e) {
                e.printStackTrace();
        }

        System.out.print("----------------------sleep-------start");
        Thread.sleep(1000*5);
        System.out.print("----------------------sleep-------end");

        TextMessage msg=(TextMessage)receiver.receiveMessage();
        String mymessage=msg.getText();
        System.out.print("响应报文数据："+mymessage);
        StringBuffer sbb=new StringBuffer();
        sbb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
        sbb.append(mymessage);
        String aa=mymessage;
        System.out.print("----------------------test-------end");
        System.out.print(aa);

    }

}