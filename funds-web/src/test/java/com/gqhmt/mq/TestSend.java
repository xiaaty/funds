package com.gqhmt.mq;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.MessageListenerImpl;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import org.junit.Test;
import javax.jms.JMSException;
import javax.jms.TextMessage;

public class TestSend {

    @Test
    public void testSendMessage(){
        AmqSendAndReceive asr = new AmqSender("createAccount.QD", "createAccount.QB");
        StringBuilder sb = new StringBuilder();
        //转换为统一报文格式
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<req>\n");
        sb.append(" <requestHeader>\n");
        sb.append("     <service_id>1</service_id>\n");
        sb.append(" <requestHeader>\n");
        sb.append(" <requestMsg>\n");
        sb.append("   <PmtID>\n");
        sb.append("     <OrderId>1</OrderId>\n");
        sb.append("     <MsgID>1</MsgID>\n");
        sb.append("     <FrmtVrsn>1</FrmtVrsn>\n");
        sb.append("     <ExTxnId>1</ExTxnId>\n");
        sb.append("     <SplitFlg1>1</SplitFlg1>\n");
        sb.append("     <SplitFlg2>1</SplitFlg2>\n");
        sb.append("     <TxnTp>1</TxnTp>\n");
        sb.append("     <TxnSubTp>1</TxnSubTp>\n");
        sb.append("     <PmtTp>1</PmtTp>\n");
        sb.append("     <BizTp>1</BizTp>\n");
        sb.append("     <BizAttr>1</BizAttr>\n");
        sb.append("     <ProcMd>1</ProcMd>\n");
        sb.append("     <MsgSts>1</MsgSts>\n");
        sb.append("     <ErrDesp>1</ErrDesp>\n");
        sb.append("     <RespCode>1</RespCode>\n");
        sb.append("     <RespCdDesp>1</RespCdDesp>\n");
        sb.append("     <ReasonFlg>1</ReasonFlg>\n");
        sb.append("     <ChkSts>1</ChkSts>\n");
        sb.append("     <SignInfo>1</SignInfo>\n");
        sb.append("     <RevFlg>1</RevFlg>\n");
        sb.append("     <RevId>1</RevId>\n");
        sb.append("     <RevDt>1</RevDt>\n");
        sb.append("     <CustID>1</CustID>\n");
        sb.append("     <CardTp>1</CardTp>\n");
        sb.append("     <BatchIdIn>1</BatchIdIn>\n");
        sb.append("     <BatchIdOut>1</BatchIdOut>\n");
        sb.append("     <ChnReUrl>1</ChnReUrl>\n");
        sb.append("     <ChnNotiUrl>1</ChnNotiUrl>\n");
        sb.append("     <ExReUrl>1</ExReUrl>\n");
        sb.append("     <ExNotiUrl>1</ExNotiUrl>\n");
        sb.append("     <CapTm>1</CapTm>\n");
        sb.append("     <TraceTm>1</TraceTm>\n");
        sb.append("     <ExeTm>1</ExeTm>\n");
        sb.append("     <ExSttlDt>11</ExSttlDt>\n");
        sb.append("     <NsSttlDt>1</NsSttlDt>\n");
        sb.append("     <OperateType>1</OperateType>\n");
        sb.append("     <OutTxnId>1</OutTxnId>\n");
        sb.append("   </PmtID>\n");
        sb.append("   <OrgnlMsgInfo>\n");
        sb.append("     <OrgnlOrderId>1</OrgnlOrderId>\n");
        sb.append("     <OrgnlMsgId>1</OrgnlMsgId>\n");
        sb.append("     <OrgnlTxnTp>1</OrgnlTxnTp>\n");
        sb.append("     <OrgnlSttlmAmt>1</OrgnlSttlmAmt>\n");
        sb.append("     <OrgnlSttlmCcy>1</OrgnlSttlmCcy>\n");
        sb.append("   </OrgnlMsgInfo>\n");
        sb.append("   <Chnl>\n");
        sb.append("     <ChnlID>1</ChnlID>\n");
        sb.append("     <OprtrID>1</OprtrID>\n");
        sb.append("     <APPId>1</APPId>\n");
        sb.append("     <ProdInfo>\n");
        sb.append("         <MerchID>1</MerchID>\n");
        sb.append("         <ProdID>1</ProdID>\n");
        sb.append("         <PrdInfo>1</PrdInfo>\n");
        sb.append("         <PrdQty>1</PrdQty>\n");
        sb.append("     </ProdInfo>\n");
        sb.append("     <FastPayInfo>\n");
        sb.append("         <Cvv>1</Cvv>\n");
        sb.append("         <SmsCd>1</SmsCd>\n");
        sb.append("         <ValidDt>1</ValidDt>\n");
        sb.append("         <TokenCd>90</TokenCd>\n");
        sb.append("         <PayFlg>1</PayFlg>\n");
        sb.append("         <AuthId>1</AuthId>\n");
        sb.append("     </FastPayInfo>\n");
        sb.append("   </Chnl>\n");
        sb.append("   <TxnAmtInfo>\n");
        sb.append("     <IntrBkSttlmAmt>1</IntrBkSttlmAmt>\n");
        sb.append("     <IntrBkSttlmCcy>1</IntrBkSttlmCcy>\n");
        sb.append("     <ActSttlmAmt>1</ActSttlmAmt>\n");
        sb.append("     <ActSttlmCcy>1</ActSttlmCcy>\n");
        sb.append("     <RtrdIntrBkSttlmAmt>1</RtrdIntrBkSttlmAmt>\n");
        sb.append("     <RtrdIntrBkSttlmCcy>1</RtrdIntrBkSttlmCcy>\n");
        sb.append("   </TxnAmtInfo>\n");
        sb.append("     <RoutInfo>\n");
        sb.append("         <AssCdtrId>1</AssCdtrId>\n");
        sb.append("         <CdtrId>1</CdtrId>\n");
        sb.append("         <CdtrNm>1</CdtrNm>\n");
        sb.append("         <KeyPath>1</KeyPath>\n");
        sb.append("         <IsChange>1</IsChange>\n");
        sb.append("         <ExMerchId>1</ExMerchId>\n");
        sb.append("     </RoutInfo>\n");
        sb.append("     <Dbtr>\n");
        sb.append("         <Nm>1</Nm>\n");
        sb.append("         <PorO>1</PorO>\n");
        sb.append("         <BirthDt>1</BirthDt>\n");
        sb.append("         <PrvcOfBirth>1</PrvcOfBirth>\n");
        sb.append("         <CityOfBirth>1</CityOfBirth>\n");
        sb.append("         <CtryOfBirth>1</CtryOfBirth>\n");
        sb.append("         <IDType>1</IDType>\n");
        sb.append("         <IDNo>1</IDNo>\n");
        sb.append("         <ContactNo>1</ContactNo>\n");
        sb.append("         <ContractNo>1</ContractNo>\n");
        sb.append("     </Dbtr>\n");
        sb.append("     <Cdtr>\n");
        sb.append("         <Nm>1</Nm>\n");
        sb.append("         <PorO>1</PorO>\n");
        sb.append("         <BirthDt>1</BirthDt>\n");
        sb.append("         <PrvcOfBirth>1</PrvcOfBirth>\n");
        sb.append("         <CityOfBirth>1</CityOfBirth>\n");
        sb.append("         <CtryOfBirth>1</CtryOfBirth>\n");
        sb.append("         <CrdtrTp>1</CrdtrTp>\n");
        sb.append("         <IDType>1</IDType>\n");
        sb.append("         <IDNo>1</IDNo>\n");
        sb.append("         <ContactNo>1</ContactNo>\n");
        sb.append("         <ContractNo>1</ContractNo>\n");
        sb.append("     </Cdtr>\n");
        sb.append("     <DbtrAcct>\n");
        sb.append("         <IdTp>1</IdTp>\n");
        sb.append("         <IssrCd>1</IssrCd>\n");
        sb.append("         <Issr>1</Issr>\n");
        sb.append("         <Branch>1</Branch>\n");
        sb.append("         <Id>1</Id>\n");
        sb.append("         <ShortCard>1</ShortCard>\n");
        sb.append("         <Ccy>1</Ccy>\n");
        sb.append("         <Nm>1</Nm>\n");
        sb.append("         <Tp>1</Tp>\n");
        sb.append("         </DbtrAcct>\n");
        sb.append("     <CdtrAcct>\n");
        sb.append("         <IdTp>1</IdTp>\n");
        sb.append("         <IssrCd>1</IssrCd>\n");
        sb.append("         <Issr>1</Issr>\n");
        sb.append("         <Branch>1</Branch>\n");
        sb.append("         <Id>1</Id>\n");
        sb.append("         <Ccy>1</Ccy>\n");
        sb.append("         <Nm>1</Nm>\n");
        sb.append("         <Tp>1</Tp>\n");
        sb.append("      </CdtrAcct>\n");
        sb.append(" </requestMsg>\n");
        sb.append("</req>\n");
        for (int i = 0; i < 50; ++i)
        try {
                TextMessage msg = (TextMessage) asr.sendSynchronizeMessage(sb.toString(), null, true, 0L);
                if (msg != null) {
                    String msgContent = null;
                    try {
                        msgContent = msg.getText();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                    System.out.println("msg -- = " + msgContent);
                }
            } catch (AmqException e) {
                e.printStackTrace();
            }


    }
}
