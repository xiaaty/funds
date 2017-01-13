package com.gqhmt.tyzf.common.frame.handler;

import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import com.gqhmt.tyzf.common.frame.spring.BaseSpring;
import com.gqhmt.tyzf.common.frame.spring.BaseSpringConstants;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhou on 2016/10/24.
 * Description: 服务映射处理类
 */
public class ServiceFunctionMapping {

    private ServiceFunctionMapping(){
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 获取服务对象
     * @param mo
     * @param serviceName
     * @return Object
     * @throws FrameException
     */
    public static Object getService(MsgObject mo, String serviceName) throws FrameException {
        String esbspringswitch = (String) ConfigManager.getInstance().get(BaseSpringConstants.BASE_SPRING_SWITCH);
        if (StringUtils.isNotEmpty(esbspringswitch)||esbspringswitch.equalsIgnoreCase(BaseSpringConstants.BASE_SPRING_SWITCH_ON)) {
            {
                BaseSpring esbspring = (BaseSpring) ConfigManager.getInstance().get(BaseSpringConstants.BASE_SPRING_INSTANCE);
                if (esbspring == null)
                    throw new FrameException(FrameConstans.SPRING_INIT_ERR);
                Object obj = null;
                try{
                    obj = esbspring.getBean(serviceName);
                }catch(Exception ex){
                    LogUtil.getInstance().info(ex.getMessage(),mo);
                }
                if(obj == null)
                    throw new FrameException("Spring中未找到该服务的应用，"+serviceName);
                return obj;
            }
        }
        return null;
    }

}
