package com.gqhmt.tyzf.common.frame.exec;

import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.ServiceRequest;
import com.gqhmt.tyzf.common.frame.message.ServiceResponse;

/**
 * Created by zhou on 2016/10/21.
 * Description:业务处理接口
 */
public interface IEventAppsExec {

    /**
     * prepare the message from DB, File, or other system
     * @param servReq
     * @throws FrameException
     */
    public void executeBeforeSend(ServiceRequest servReq) throws FrameException;

    /**
     * write the message back to DB, file or other system
     * @param servResp
     * @throws FrameException
     */
    public void executeAfterSend(ServiceResponse servResp) throws FrameException;

}
