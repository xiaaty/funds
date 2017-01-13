package com.gqhmt.tyzf.common.frame.handler;

import com.gqhmt.tyzf.common.frame.cache.BaseCacheManager;
import com.gqhmt.tyzf.common.frame.common.Constants;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exec.IEventAppsExec;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import com.gqhmt.tyzf.common.frame.message.ServiceRequest;
import com.gqhmt.tyzf.common.frame.spring.BaseSpring;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

/**
 * Created by zhou on 2016/10/23.
 * Description:BaseHandler工具类
 */
public class BaseHandlerUtil {

    /**
     * 构造函数
     */
    private BaseHandlerUtil(){
        throw new IllegalAccessError("Utility class");
    }


    /**
     * 为servRequest设置ExecId、MsgObject
     * @param servRequest
     * @return
     * @throws FrameException
     */
    public static boolean readXmlToMo(ServiceRequest servRequest) throws FrameException {
        MsgObject mo = null;
        try {
            mo = new MsgObject(servRequest.getRequestMsg());
            String req_id = mo.getRequest_service_ID();
            BaseSpring baseSpring = (BaseSpring) ConfigManager.getInstance().get(Constants.SPRING_INSTANCE);
            BaseCacheManager baseCacheManager = (BaseCacheManager) baseSpring.getBean(Constants.CACHE_MANAGER_BEAN_ID);
            servRequest.setExecId(baseCacheManager.getSpringServiceId(req_id)); // 找出对应的spring服务BeanName
            servRequest.setMo(mo);
        } catch (Exception e) {
            LogUtil.getInstance().error(e, servRequest.getRequestMsg());
            System.out.println(e.getMessage());
            throw new FrameException(e);
        }
        return true;
    }

    /**
     * 初始化本地应用程序类
     * @param servRequest
     * @return IEventAppsExec
     * @throws FrameException
     */
    public static IEventAppsExec initEventAppsExec(ServiceRequest servRequest) throws FrameException {
        String execId = servRequest.getExecId();
        IEventAppsExec exec = null;
        try {
            exec = (IEventAppsExec) ServiceFunctionMapping.getService(servRequest.getMo(), execId);
        } catch (Exception ex) {
            LogUtil.getInstance().error(ex, servRequest.getMo());
            throw new FrameException(ex);
        }
        return exec;
    }

    /**
     * 调用发送前的本地应用程序逻辑处理
     * @param servRequest
     * @param exec
     * @throws FrameException
     */
    public static void executeBeforeSend(ServiceRequest servRequest, IEventAppsExec exec) throws FrameException {
        if (exec != null) {
            exec.executeBeforeSend(servRequest);
        }
    }

}
