package com.gqhmt.common.context;

/**
 * Created by zhou on 2016/10/20.
 * Description: ConfigManager常量
 */
public interface ContextConstants {

    /** 默认配置文件路径 */
    String DEFAULT_CONFIG_FILE_NAME_XML = "config/config.xml";

    /* Message Definitions */
    String MSG_START_INFO = "[节点消息] 节点已经正常运行。";
    String MSG_COMPONENT_LOAD = "[节点消息] 此组件启动正常: ";
    String MSG_ERROR_COMPONENT = "[节点消息] 此组件启动失败: ";
    String MSG_START_ERROR = "[节点消息] 适配器启动失败，请检查各配置项是否设置正确。";
    String MSG_DAEMON_START = "[节点消息] 此守护线程启动正常: ";
    String MSG_ERROR_COMPENSATE = "[节点消息] 此组件的补偿启动动作失败: ";
    String MSG_COMMAND = "[节点消息] 适配器收到如下命令：";
    String MSG_COMMAND_ERROR = "[节点消息] 命令执行失败：错误的命令格式。";
    String MSG_DAEMON_STOP = "[节点消息] 此守护线程已经被正常停止：";
    String MSG_STOP = "[节点消息] 适配器已经正常退出。";
    String MSG_DAEMON_STOP_EXCEPTION = "[节点消息] 此守护线程不能被正常停止：";
    String MSG_COMPONENT_RELEASE = "[节点消息] 此组件正常释放: ";

    /* Cache Constants */
    /**类加载顺序*/
    String COMMON_LOAD_SEQUENCE = "Common.LoadSequence";
    // 全局常量引用路径(弃用)
    String COMMON_CONSTANT_DEFINITION_FILE = "Common.ConstantDefinitionFile";

    /* Command Constants */
    String COMMAND_SPLIT = "|";
    String COMMAND_STOP = "STOP";
    String COMMAND_SHUTDOWN = "SHUTDOWN";
    String COMMAND_UNKNOWN = "UNKNOWN";

}
