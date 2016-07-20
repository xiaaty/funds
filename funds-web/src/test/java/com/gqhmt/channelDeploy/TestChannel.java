package com.gqhmt.channelDeploy;

import com.gqhmt.TestService;
import com.gqhmt.fss.architect.channelDeploy.entity.ChannelOrgEntity;
import com.gqhmt.fss.architect.channelDeploy.service.ChannelService;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class TestChannel extends TestService {

    @Resource
    private ChannelService channelService;

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    private Element root;

    @Test
    public void getChannelOrgEntityList(){
        List<ChannelOrgEntity> channelEntityList = new ArrayList<ChannelOrgEntity>();
        System.out.println(channelService);
        channelEntityList = channelService.getChannelOrgList();
        System.out.println(channelEntityList);
    }

   /* public void setRequestMsgAttributer(String nodeName, String attributeName, String value) {
        if (StringUtils.isBlank(nodeName) || StringUtils.isBlank(attributeName) || StringUtils.isBlank(value)) {
            return;
        }
        Node node = root.selectSingleNode(MsgConstants.REQ_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
        if (null != node) {
            Element element = (Element)node;
            element.addAttribute(attributeName,value);
            return;
        }
        if (requestMsg == null) {
            requestMsg = root.addElement(MsgConstants.REQ_BODY_TAG);
        }
        this.setTextbyXpath(nodeName, null, requestMsg, MsgConstants.REQ_BODY_TAG+MsgConstants.SPLIT_CHAR,attributeName,value);
    }
    //通过xpath  set some node value
    private void setTextbyXpath(String nodeName,String value,Element startElement,String prePath,
                                String attrName,String attrValue){
        String[] nodeArr = nodeName.split(MsgConstants.SPLIT_CHAR);
        StringBuilder xpath = new StringBuilder().append(prePath);
        Node parentNode = (Node)startElement;
        for (int i = 0; i < nodeArr.length; i++) {
            String childNodename = nodeArr[i];
            Node childNode = root.selectSingleNode((xpath.append(childNodename).toString()));
            if (childNode == null) {
                Element parentElement = (Element)parentNode;
                childNode = (Node)parentElement.addElement(childNodename);
            }
            if (null != childNode && i == nodeArr.length-1) {
                Element element = (Element)childNode;
                element.setText((value != null) ? value:"" );
                if (attrName != null && !"".equals(attrName)) {
                    element.addAttribute(attrName,attrValue);
                }
                break;
            }
            parentNode = childNode;
            xpath.append(MsgConstants.SPLIT_CHAR);
        }
    }


    //2.2获取请求报文某一节点的值
    //参数说明：nodeName节点的全路径
    public String getRequestMsgParameter(String nodeName) {
        return XMLHandleUtil.getSelectSingleNode(root, MsgConstants.REQ_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
    }

    public static String getSelectSingleNode(Element element,String nodeName){
        Node node=element.selectSingleNode(nodeName);
        if(node==null)
            return null;
        return node.getStringValue();
    }


//    2.3从本地缓存读取报文
//    从消息队列中读取待处理的报文内容，
//
//    线程关闭。
    public void run() {
        // 运行前，设置为运行状态
        setStatus(AbstractMultiThread.RUN);
        while (AbstractMultiThread.RUN == this.getStatus()) {
            IServiceObject iServObj = null;
            processFlag = true;
            try {
                iServObj = this.receiveBuffer.get();
                //获取中断消息处理
                if(iServObj instanceof InterruptMessage) {
                    //继续往buffer中放中断消息，触发其它线程停止
                    System.err.println("**********************************");
                    this.receiveBuffer.put((InterruptMessage)iServObj);
                    break;
                }
                if ((null == iServObj) || (null == iServObj.getServPara())) {
                    processFlag = false;
                    continue;
                }

            } catch (Exception e) {
                Log.getInstance().stdError(e);
                processFlag = false;
                continue;
            }
            if (!processStatus) {
                Log.getInstance().stdInfo("当前正在等待交易开启命令，交易暂停受理...........................");
                continue;
            }
            if (signInCheck(iServObj)) {
                processFlag = false;
                continue;
            }
            Log.getInstance().stdInfo(LOG_PREFIX + "当前交易开始执行...........................");
            // 根据消息类型，进行不同的处理
            try {
                ServiceRequest sRequest = (ServiceRequest) iServObj;
                pt = new PerformanceTimer(" BaseHandler ");
                pt.counterStart(" StartHandler ");
                requestMessageProc(sRequest);
                pt.setMO(sRequest.getMo());
                pt.counterEnd(" EndHandler ");
                pt.show();
                Log.getInstance().stdInfo(LOG_PREFIX + "当前交易执行结束...........................");
            } catch (Exception ex) {
                Log.getInstance().stdError("Handler Catched Exception:");
                Log.getInstance().stdError(ex);
            } finally {
                if (iServObj.getSignInTag()) {
                    SignInTag = false;
                }
            }
            processFlag = false;
            continue;
        }
        this.release();
        this.setStatus(AbstractMultiThread.STOPPED);
        Log.getInstance().stdInfo(LOG_PREFIX + " has been stopped");
    }


//    2.4从队列接收报文
//            从消息队列读取待接收的报文内容
//    将消息放到ServiceRequest对象中
//            将ServiceRequest放到receiveBuffer中
    public void run() {
        // 运行前，设置为运行状态
        setStatus(AbstractMultiThread.RUN);
        // 启动心跳日志
        Thread timeLog = new Thread(timeLogger);
        timeLog.start();
        Log.getInstance().stdInfo(LOG_PREFIX + " begin to run");
        String msg=null;
        while(getStatus() == AbstractMultiThread.RUN){
            Message osg = null;
            try {
                osg = amqReceiver.receiveMessage();
            } catch (Exception ex) {
                Log.getInstance().stdInfo(LOG_PREFIX +" getNextMessage  Interrupted !");
                continue;
            }
            try {
                if (osg == null) {
                    continue;
                }
                if(!(osg instanceof TextMessage)) {
                    Log.getInstance().stdWarn("Receive Message from Queue is not instanceof TextMessage.");
                    continue;
                }
                msg = ((TextMessage)osg).getText();
                //无论同步异步先获取correlationId.
                String correlationId = osg.getJMSCorrelationID();
                if(correlationId == null || correlationId.trim().length()<= 0) {
                    correlationId = osg.getJMSMessageID();
                }
                System.out.println("JMS CorrelationId is : " + correlationId);
                ServiceRequest servReq = new ServiceRequest();
                servReq.setCorrelationId(correlationId);

                pt = new PerformanceTimer(" BaseMQMsgReceive ");
                pt.counterStart(" GetMessage ");

                Log.getInstance().stdDebug(LOG_PREFIX + " receive msg success");
                if (Log.getInstance().isDebugEnabled()) {
                    Log.getInstance().stdDebug("Receive Message from Queue = " + msg.length());
                    Log.getInstance().stdInfo(" Receive Message: \n" + msg);
                }
                servReq.setRequestMsg(msg);
                String size = String.valueOf(receiveBuffer.getNum());
                pt.counter(" ReceiveQueueSize(" + size + ") ");
                receiveBuffer.put(servReq);
                pt.setMO(new MsgObject(msg));
                pt.counterEnd(" PutReceiveBuffer ");
                Log.getInstance().stdInfo(LOG_PREFIX + " receiveBuffer Size reach " + receiveBuffer.getNum());
            } catch (Exception e) {
                pt.counter(" Exception ");
                Log.getInstance().stdError(e,(Object)msg);
            }finally{
                if(pt != null)
                    pt.show();
            }
        }

        Log.getInstance().stdInfo(LOG_PREFIX + " stopped");
        if (null != stopReason)
            Log.getInstance().stdInfo(LOG_PREFIX + " stop reason is: " + stopReason);
        // 设置为结束状态，线程结束
        setStatus(AbstractMultiThread.STOPPED);
    }
*/

}
