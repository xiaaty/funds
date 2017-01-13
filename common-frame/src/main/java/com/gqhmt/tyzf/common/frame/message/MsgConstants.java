package com.gqhmt.tyzf.common.frame.message;

/**
 * Created by zhou on 2016/10/21.
 * Description:
 */
public interface MsgConstants {

    /** 请求报文的根节点 */
     String REQ_ROOT="req";

    /** 响应报文的根节点 */
     String RESP_ROOT="res";

    /** 分格符 */
     String SPLIT_CHAR="/";

     String REQ_BODY_TAG="requestMsg";

     String REQ_HEADER_TAG="requestHeader";

     String RESP_BODY_TAG="responseMsg";

     String RESP_HEADER_TAG="responseHeader";


    /**标识是否为最后一个处理模块*/
     String SYS_NODE_FLAG="sys_node_flag";

    /** 是否实时交易 */
     String IS_ACTUAL="is_actual";

    /** 单笔同步交易标识 */
     String ACTUAL_TRADE="Y";

    /**  */
     String REQ_SYS_ID="req_sys_id";

    /**  */
     String SERV_SYS_ID="serv_sys_id";

    /**  */
     String SERVICE_ID="service_id";

    /**报文唯一标识*/
     String MsgID="PmtID/MsgID";






}
