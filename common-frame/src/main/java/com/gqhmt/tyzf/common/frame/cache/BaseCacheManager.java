package com.gqhmt.tyzf.common.frame.cache;

import com.gqhmt.tyzf.common.frame.common.Constants;
import com.gqhmt.tyzf.common.frame.common.BasicStatus;
import com.gqhmt.tyzf.common.frame.common.IConfigurable;
import com.gqhmt.tyzf.common.frame.common.IStatus;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou on 2016/10/23.
 * Description: 数据库数据缓存数据
 */
public class BaseCacheManager implements ICacheManager, IConfigurable {

    //JDBC模板对象，由spring注入
    private NamedParameterJdbcTemplate nameParameterJdbcTemplate;

    /** 映射服务信息 */
    private Map<String, String> serviceMapping = new HashMap<String, String>();

    private String host;

    /**
     * 有参构造
     * @param simpleJdbcTemplate JDBC模板对象
     * @throws FrameException
     */
    public BaseCacheManager(NamedParameterJdbcTemplate simpleJdbcTemplate) throws FrameException {
        this.nameParameterJdbcTemplate = simpleJdbcTemplate;
        initConfig(nameParameterJdbcTemplate);
        LogUtil.getInstance().debug("CacheManager initiation is successful!");
    }

    /**
     * 初始化服务映射关系
     * @param nameParameterJdbcTemplate
     * @throws FrameException
     */
    private void initConfig(NamedParameterJdbcTemplate nameParameterJdbcTemplate) throws FrameException {
        //获取配置文件中Adapter.SYSID标签值（系统ID）
        String req_sys_id = (String) ConfigManager.getInstance().get(Constants.ADAPTER_SYSID);
        //加载系统中的所有服务对象
        loadServiceMapping(req_sys_id);
    }

    /**
     * 根据系统ID获取服务ID和服务对象名称并加载到map中
     * @param req_sys_id 系统ID
     */
    public void loadServiceMapping(String req_sys_id) {
        try {
            String sql = "select REQ_SYS_ID,REQ_APP_SERV_ID,REQ_SERV_ID from t_gq_fss_servid_mapping where REQ_SYS_ID = :req_sys_id";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("req_sys_id", req_sys_id);
            List<Map<String, Object>> seqMap = nameParameterJdbcTemplate.queryForList(sql,parameters);

            String req_app_serv_id;
            String req_serv_id;
            for (Map mp : seqMap) {
                req_app_serv_id = (String) mp.get("REQ_APP_SERV_ID");//服务ID
                req_serv_id = (String) mp.get("REQ_SERV_ID");//服务对象名称
                serviceMapping.put(req_app_serv_id, req_serv_id);
            }
        } catch (Exception e) {
            LogUtil.getInstance().error(e);
        }
        LogUtil.getInstance().debug("---load Service_Mapping sucess.");
    }

    /**
     * 获取服务对象名称
     * @param req_app_serv_id 服务ID
     * @return String 服务对象名称
     */
    public String getSpringServiceId(String req_app_serv_id) {
        return serviceMapping.get(req_app_serv_id);
    }

    /******ICacheManager接口方法************/
    @Override
    public String getSequenceNo(String type_id) {
        return null;
    }

    /******IConfigurable************/
    @Override
    public void refresh() throws FrameException {
    }

    @Override
    public IStatus checkStatus() throws FrameException {
        StringBuilder sbd = new StringBuilder();
        sbd.append("数据库缓存组件运行良好" + "；");
//        sbd.append("适配器应用系统编号:[" + host + "]；");
        return new BasicStatus(true, sbd.toString());
    }

    @Override
    public void compensate() throws FrameException { }

    @Override
    public void release() { }


    /******************getter,setter方法 ************************/
    public NamedParameterJdbcTemplate getNameParameterJdbcTemplate() {
        return nameParameterJdbcTemplate;
    }

    public void setNameParameterJdbcTemplate(NamedParameterJdbcTemplate nameParameterJdbcTemplate) {
        this.nameParameterJdbcTemplate = nameParameterJdbcTemplate;
    }

    public Map<String, String> getServiceMapping() {
        return serviceMapping;
    }

    public void setServiceMapping(Map<String, String> serviceMapping) {
        this.serviceMapping = serviceMapping;
    }
}
