package com.gqhmt.tyzf.common.frame.exec;

import com.gqhmt.tyzf.common.frame.buffer.IMsgBuffer;
import com.gqhmt.tyzf.common.frame.buffer.IMsgReceiveSend;
import com.gqhmt.tyzf.common.frame.buffer.MsgBuffer;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MsgConstants;
import com.gqhmt.tyzf.common.frame.message.ServiceRequest;
import com.gqhmt.tyzf.common.frame.message.ServiceResponse;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

/**
 * Created by zhou on 2016/10/21.
 * Description:业务处理抽象类；在BaseHandler类中调用此类接口，由使用此框架的类实现接口
 */
public abstract class EventAppsExec implements IEventAppsExec{

    /**
     * 用于同步调用中，标记当前处理模块是最后的处理模块
     * 2:表示最后一个处理系统系统
     */
    public static final int is_end_sys=2;

    /**
     * 用于同步调用中，标记当前处理模块是中间环节处理模块
     * 1:表示中间处理系统
     */
    public static final int is_mid_sys=1;

    /**
     * 用于同步调用中，标记当前处理模块是最后的处理模块
     * 0:表示第一个处理系统系统
     */
    public static final int is_begin_sys=0;

    /**
     * 用于异步发送调用
     * 处理转发； 根据业务逻辑将消息放到不同的队列，用于后续处理
     * servRequest:需要发送的报文
     * buffer:发送的buffer
     * @param servRequest
     * @param buffer
     */
    protected void send(ServiceRequest servRequest, IMsgBuffer buffer) {
        if (servRequest == null) {
            return;
        }
        if (servRequest.getMo() == null) {
            servRequest.release();
            return;
        } else {
            try {
                buffer.put(servRequest);
            } catch (FrameException e) {
                LogUtil.getInstance().debug(e);
            }
        }
    }

    /**
     * 用于同步实时发送调用
     * 处理转发； 根据业务逻辑将消息放到不同的队列，用于后续处理
     * servRequest:需要发送的报文
     * buffer:发送的buffer
     * sys_node_flag:标记当前处理模块是处理流程中那个节点
     */
    protected void send(ServiceRequest servRequest, IMsgBuffer buffer,int sys_node_flag) {
        if (servRequest == null) {
            return;
        }
        if (servRequest.getMo() == null) {
            servRequest.release();
            return;
        } else {
            try {
                //判断该交易是否是实时交易,如果不是记录错误。
                if(!MsgConstants.ACTUAL_TRADE.equalsIgnoreCase(servRequest.getMo().getRequestHeaderParameter(MsgConstants.IS_ACTUAL))){
                    LogUtil.getInstance().error("-----send fail.the 'is_actual' node_value error");
                    throw new FrameException("send fail.the 'is_actual' node_value error");
                }
                if(is_end_sys==sys_node_flag){
                    servRequest.getMo().setRequestHeaderParameter(MsgConstants.SYS_NODE_FLAG,String.valueOf(is_end_sys));
                }else{
                    servRequest.getMo().setRequestHeaderParameter(MsgConstants.SYS_NODE_FLAG, String.valueOf(is_mid_sys));
                }
                buffer.put(servRequest);
            } catch (FrameException e) {
                LogUtil.getInstance().error(e);
            }
        }
    }

}
