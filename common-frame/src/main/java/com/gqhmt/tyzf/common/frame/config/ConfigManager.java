package com.gqhmt.tyzf.common.frame.config;

import com.gqhmt.tyzf.common.frame.buffer.ESBMsgBuffer;
import com.gqhmt.tyzf.common.frame.common.*;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.handler.BaseHandlerPool;
import com.gqhmt.tyzf.common.frame.message.IServiceObject;
import com.gqhmt.tyzf.common.frame.message.InterruptMessage;
import com.gqhmt.tyzf.common.frame.mqserver.BaseMQMsgReceive;
import com.gqhmt.tyzf.common.frame.mqserver.BaseMQMsgSend;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.util.*;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

/**
 * Created by zhou on 2016/10/20.
 * Description:节点启动主类
 */
public class ConfigManager implements ConfigConstants {

    /**日志对象*/
    private final static Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    /**日志前缀*/
    protected String LOG_PREFIX = "configManager";
    /** 本类对象 */
    private static ConfigManager configManager = null;
    /** 配置文件类型 */
    private String configType = "XML";
    /** 运行状态 */
    protected boolean running = true;
    /** 配置文件属性键值 */
    private Hashtable<String, String> property = new Hashtable<String, String>();
    /** 标签组集合 */
    private ArrayList<String> sequence = new ArrayList<String>();
    /** 类实例 */
    private Properties instances = new Properties();
    /*** 配置文件解析对象***/
    private DOMParser parser = new DOMParser();
    private Document document;
    private Element root;
    /** 系统关闭时的hook*/
    private ConfigShutdownHook hook = new ConfigShutdownHook();

    /**
     * 构造函数
     * @param configType
     */
    protected ConfigManager(String configType) {
        configManager = this;
        this.configType = configType;
    }

    /**
     * 获取ConfigManager对象实例
     * @return
     * @throws FrameException
     */
    public static ConfigManager getInstance() throws FrameException {
        if (configManager == null) {
            throw new FrameException("Lack configManager");
        }
        return configManager;
    }

    /**
     * 初始化
     * @param configFileName
     * @return boolean
     */
    protected boolean init(String configFileName) {
        logger.info("Node init ......");
        /** 第一步：加载所有配置到内存从配置文件 */
        if (!loadConfigurations(configFileName)) {
            return false;
        }
        logger.info("loadConfigurations succeed!");

        /** 第二步：设置shutdown钩子 */
        Runtime.getRuntime().addShutdownHook(hook);
        logger.info("addShutdownHook succeed!");

        /** 第三步：加载Common标签下的内容 */
        if (!generateSequence()) {
            return false;
        }
        logger.info("generateSequence succeed!");

        /** 第四步：加载类实例 */
        if(!loadInstances()){
            stop();
            return false;
        }
        logger.info("loadInstances succeed!");

        /** 第五步：启动守护线程 */
        if (!startDaemons()) {
            stop();
            return false;
        }
        logger.info("startDaemons succeed!");
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info("startDaemons succeed!");
        }

        /** 第六步：Execute compensate action of every component */
        if (!executeCompensate()) {
            stop();
            return false;
        }
        System.out.println("executeCompensate succeed!");
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info("executeCompensate succeed!");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LogUtil.getInstance().error(e);
        }

        ConfigCommandResult result = checkStatus();

        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info(result.toBytes());
        }

        logger.debug("Adapter init succeed");
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info("Adapter init succeed.");
        }
        return true;
    }

    /**
     * 加载config.xml配置文件
     * @param configFileName
     * @return
     */
    private boolean loadConfigurations(String configFileName) {
        System.out.println(configFileName);
        if ("XML".equals(configType)) {
            try {
                parser.parse(configFileName);
                document = parser.getDocument();
                root = document.getDocumentElement();
            } catch (Exception e) {
                LogUtil.getInstance().error(e);
                LogUtil.getInstance().info("loadConfigurations fail : " + configFileName);
                return false;
            }
            return true;
        } else {
            LogUtil.getInstance().info("loadConfigurations fail : " + configType);
            return false;
        }
    }

    /**
     * 读入config配置中的节点和adapter配置文件内容
     * @return
     */
    private boolean generateSequence() {
        String items = getValueProperty(COMMON_LOAD_SEQUENCE);
        if (items != null) {
            String item = null;
            while (items.indexOf(",") > 0) {
                item = items.substring(0, items.indexOf(",")).trim();
                if ((item != null) && (item.length() > 0)) {
                    sequence.add(item);
                }
                items = items.substring(items.indexOf(",") + 1);
            }
            item = items.trim();
            if ((item != null) && (item.length() > 0)) {
                sequence.add(item);
            }
        }
        return true;
    }

    /**
     * 获取节点内容
     * @param name
     * @return
     */
    private String getValueProperty(String name) {
        if (property.containsKey(name)) {
            return property.get(name);
        } else {
            if ("XML".equals(configType)) {
                String rs = getValueFromNode(name);
                if ((rs != null) && !"".equals(rs.trim())) {
                    property.put(name, rs);
                }
                return rs;
            }
        }
        return null;
    }

    /**
     * 获取节点内容
     * @param name
     * @return
     */
    private String getValueFromNode(String name) {
        Node nd = getChildByName(name);
        String rs = null;
        if ((null != nd) && (null != nd.getFirstChild())) {
            rs = nd.getFirstChild().getNodeValue();
        }
        return rs;
    }

    /**
     * 获取子节点(xxx.xxx)
     * @param name
     * @return
     */
    private Node getChildByName(String name) {
        Node nd = root;
        String[] nameStructure = name.split("\\.");
        for (int k = 0; k < nameStructure.length; k++) {
            NodeList temp = null;
            if (null != nd) {
                temp = nd.getChildNodes();
            }
            if (null == temp) {
                return null;
            }
            String tempName;
            for (int i = 0; i < temp.getLength(); i++) {
                tempName = temp.item(i).getNodeName();
                if (tempName.equals(nameStructure[k])) {
                    nd = temp.item(i);
                    break;
                } else {
                    nd = null;
                }
            }
        }
        return nd;
    }

    /**
     * 加载所有类实例
     * @return boolean
     */
    private boolean loadInstances() {
        for (int i = 0, s = sequence.size(); i < s; i++) {
            String name = sequence.get(i);
            try {
                System.out.println("load config node : " + name);
                String className = getValueProperty(name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_CLASSNAME);
                className = className.trim();//防止在配置文件中结尾的标签换行导致className换行
                logger.info("load config node class : " + className);
                Object instance = null;
                Constructor<?> constructor = null;
                try {
                    constructor = Class.forName(className).getConstructor(String.class);
                } catch (Exception ex) {
                    logger.info("No String structure parameters : "+className);
                }

                if (constructor != null) {
                    instance = constructor.newInstance(name);
                } else {
                    instance = Class.forName(className).newInstance();
                }

                String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
                instances.put(instanceName, instance);
                if (instance instanceof IConfigurable) {
                    hook.registerComponent((IConfigurable) instance);
                }

                String infoMessage = MSG_COMPONENT_LOAD + name;
                LogUtil.getInstance().info(infoMessage);
            } catch (Exception ex) {
                if (LogUtil.getInstance() != null) {
                    LogUtil.getInstance().error(MSG_ERROR_COMPONENT + name);
                    LogUtil.getInstance().error(MSG_START_ERROR);
                    LogUtil.getInstance().error(ex);
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 启动守护线程
     * @return
     */
    private boolean startDaemons() {
        for (int i = 0, s = sequence.size(); i < s; i++) {
            String name = sequence.get(i);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof AbstractMultiThread)) {
                AbstractMultiThread daemon = (AbstractMultiThread) instance;
                Thread thread = new Thread(daemon);
                daemon.setThread(thread);
                thread.setDaemon(true);
                thread.start();
                hook.registerDaemon(daemon);
                String infoMessage = MSG_DAEMON_START + name;
                System.out.println(infoMessage);
                if (LogUtil.getInstance() != null) {
                    LogUtil.getInstance().info(infoMessage);
                }
            }
        }
        return true;
    }

    /**
     * 执行组件的补偿方法
     * @return
     */
    private boolean executeCompensate() {
        for (int i = 0, s = sequence.size(); i < s; i++) {
            String name = sequence.get(i);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof IConfigurable)) {
                try {
                    ((IConfigurable) instance).compensate();
                } catch (FrameException e) {
                    System.out.println(MSG_ERROR_COMPENSATE + name);
                    System.out.println(MSG_START_ERROR);
                    System.out.println(e);
                    if (LogUtil.getInstance() != null) {
                        LogUtil.getInstance().error(MSG_ERROR_COMPENSATE + name);
                        LogUtil.getInstance().error(MSG_START_ERROR);
                        LogUtil.getInstance().error(e);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查状态
     * @return
     */
    private ConfigCommandResult checkStatus() {
        ConfigCommandResult result = new ConfigCommandResult();
        for (Iterator<Object> components = instances.keySet().iterator(); components.hasNext();) {
            String element = (String) components.next();
            if (element != null) {
                Object component = instances.get(element);
                if (component instanceof IConfigurable) {
                    IConfigurable iconfig = (IConfigurable) component;
                    String componentName = element.substring(0,
                            element.indexOf(Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE));
                    String infoMessage = null;
                    IStatus istatus = null;
                    if (LogUtil.getInstance() != null) {
                        LogUtil.getInstance().info(componentName + " checkStatus Begin");
                    }
                    try {
                        istatus = iconfig.checkStatus();
                    } catch (FrameException e) {
                        // e.printStackTrace();
                        istatus = new BasicStatus(false, componentName, "", "Status unknown");
                    }
                    if (istatus == null) {
                        istatus = new BasicStatus(false, componentName, "", "Status unknown");
                    }
                    if (istatus.getStatus()) {
                        infoMessage = componentName + " : " + istatus.getDetail();
                    } else {
                        infoMessage = componentName + " : " + istatus.getDetail();
                    }
                    result.add(infoMessage);
                    if (LogUtil.getInstance() != null) {
                        LogUtil.getInstance().info(infoMessage);
                        LogUtil.getInstance().info(componentName + " checkStatus End");
                    }
                }
            }
        }
        return result;
    }

    public static void initial(String configFileName) throws FrameException{
        logger.info("Node is starting ......");
        ConfigManager configManager = new ConfigManager("XML");

        if (StringUtils.isEmpty(configFileName)) {
            throw new FrameException("config file location must be specified");
        }
        logger.info("XML config file is : " + configFileName);

        if (configManager.init(configFileName)) {
            logger.info(MSG_START_INFO);
            while (configManager.running) {
                try {
                    Thread.sleep(2000);
                    LogUtil.getInstance().debug("Main开始休息2秒钟");
//                    logger.debug("Main开始休息2秒钟");
                } catch (InterruptedException e1) {
                    LogUtil.getInstance().error(e1);
                }
            }
            System.exit(0);
        }
        if(LogUtil.getInstance() != null)
            LogUtil.getInstance().info("Node is ended! ......");
    }

    public static void main(String[] args) throws FrameException {
        //调用的配置文件名称
        String configFileName = null;
        try {
            configFileName = args[0];
        } catch (Exception e) {
            configFileName = ConfigManager.class.getClassLoader().getResource(DEFAULT_CONFIG_FILE_NAME_XML).getPath();
        }

        initial(configFileName);

    }

    /**
     * 执行命令方法
     * @param commands
     * @return
     */
    public ConfigCommandResult execute(ConfigCommand commands) {
        LogUtil.getInstance().info("com.eigp.adapter.config.ConfigManager.execute() - begin");
        if (commands == null) {
            return errorCommand();
        }
        ConfigCommandResult result = null;
        String command = commands.getCommand();
        LogUtil.getInstance().info(MSG_COMMAND + commands.toString());
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info(MSG_COMMAND + commands.toString());
        }
        if (COMMAND_STOP.equals(command) || COMMAND_SHUTDOWN.equals(command)) {
            result = stop();
        } else {
            result = errorCommand();
        }
        result.add("命令\"" + commands.toString() + "\"处理结束.");
        LogUtil.getInstance().info("com.eigp.adapter.config.ConfigManager.execute() - end");

        return result;
    }

    /**
     * 错误命令
     * @return
     */
    private ConfigCommandResult errorCommand() {
        ConfigCommandResult result = new ConfigCommandResult();
        result.add(MSG_COMMAND_ERROR);
        System.out.println(MSG_COMMAND_ERROR);
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().error(MSG_COMMAND_ERROR);
        }
        return result;
    }

    /**
     * 获取实例或名称
     * @param key
     * @return
     */
    public synchronized Object get(Object key) {
        if (key == null) {
            return null;
        }
        if (key instanceof String) {
            String instanceName = (String) key;
            if (instanceName.endsWith(Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE)) {
                return instances.get(key);
            } else {
                return getValueProperty(instanceName);
            }
        }
        return null;
    }

    /**
     * 停止方法
     * @return
     */
    private ConfigCommandResult stop() {
        ConfigCommandResult result = new ConfigCommandResult();

        /**1.停掉mq接收线程*/
        for(int i = sequence.size(); i > 0; i--){
            String name = sequence.get(i - 1);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof BaseMQMsgReceive)) {
                stopOperation(instance,result,name);
            }
        }

        /**2.处理接收Buffer*/
        Set<String> receiveBufferNameSet = new HashSet<String>(); //所有接收buffer名.
        Set<String> sendBufferNameSet = new HashSet<String>();//所有的发送buffer名.
        //2.1 get所有的接收buffer名和发送buffer名称
        for(int i = sequence.size(); i > 0; i--){
            String name = sequence.get(i - 1);
            if(name.trim().split("\\.").length == 2) {
                String receiveVal = getValueProperty(name + ".ReceiveBuffer");
                String sendVal = getValueProperty(name + ".SendBuffer");
                if(receiveVal != null && receiveVal.trim().length() > 0) {
                    receiveBufferNameSet.add(receiveVal.trim());
                }
                if(sendVal != null && sendVal.trim().length() > 0) {
                    sendBufferNameSet.add(sendVal.trim());
                }
            }
        }
        //2.2 get所有的接收buffer和所有的发送buffer对象
        Set<ESBMsgBuffer> receiveBufferSet = new HashSet<ESBMsgBuffer>();
        Set<ESBMsgBuffer> sendBufferSet = new HashSet<ESBMsgBuffer>();
        for (int i = sequence.size(); i > 0; i--) {
            String name = sequence.get(i - 1);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof ESBMsgBuffer)) {
                if(receiveBufferNameSet.contains(name.trim())) {
                    receiveBufferSet.add((ESBMsgBuffer)instance);
                }
                if(sendBufferNameSet.contains(name.trim())) {
                    sendBufferSet.add((ESBMsgBuffer)instance);
                }
            }
        }
        System.out.println("2.receiveBufferSet="+receiveBufferSet.toString());
        System.out.println("2.sendBufferSet="+receiveBufferSet.toString());
        //2.3 等待所有的接收buffer都为0
        for(ESBMsgBuffer receiveBuffer : receiveBufferSet) {
            boolean flag = true;
            try {
                while(flag) {
                    int num = receiveBuffer.getMsgBuffer().getNum();
                    System.out.println("3.receiveBuffer.getMsgBuffer().getNum()="+receiveBuffer.getMsgBuffer().getNum());
                    if (num == 0) {
                        flag = false;
                    }
                }
            }catch(FrameException e) {
                LogUtil.getInstance().error(e);
            }
        }
        //2.4 在空的接收Buffer里发送一个中断消息
        for(ESBMsgBuffer receiveBuffer : receiveBufferSet) {
            try {
                IServiceObject im = new InterruptMessage(); //中断消息.
                receiveBuffer.getMsgBuffer().put(im);
                System.out.println("4.receiveBuffer.getMsgBuffer()="+receiveBuffer.getMsgBuffer());
            } catch(FrameException e) {
                LogUtil.getInstance().error(e);
            }
        }
        System.out.println("4.receiveBufferSet="+receiveBufferSet.toString());

        /**3 业务处理线程池*/
        //3.1 线程池资源的释放
        for (int i = sequence.size(); i > 0; i--) {
            String name = sequence.get(i - 1);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof BaseHandlerPool)) {
                ((BaseHandlerPool)instance).release();
            }
        }
        //5、等待发送buffer size 为0.
        for(ESBMsgBuffer sendBuffer : sendBufferSet) {
            boolean flag = true;
            try {
                while(flag) {
                    int num = sendBuffer.getMsgBuffer().getNum();
                    if (num == 0) {
                        flag = false;
                    }
                }
            }catch(FrameException e) {
                LogUtil.getInstance().error(e);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            LogUtil.getInstance().error(e);
        }
        //6、停掉发送线程
        for(int i = sequence.size(); i > 0; i--){
            String name = sequence.get(i - 1);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof BaseMQMsgSend)) {
                stopOperation(instance,result,name);
            }
        }
        //7、释放所有组件资源
		/* Release all component resources */
        for (int i = sequence.size(); i > 0; i--) {
            String name = sequence.get(i - 1);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof IConfigurable)) {
                if(instance instanceof BaseHandlerPool || instance instanceof BaseMQMsgReceive || instance instanceof BaseMQMsgSend)
                    continue;
                ((IConfigurable) instance).release();
                String infoMessage = MSG_COMPONENT_RELEASE + name;
                result.add(infoMessage);
                if (LogUtil.getInstance() != null) {
                    LogUtil.getInstance().info(infoMessage);
                }
            }
        }

		/* Release resources of ConfigManager */
        cache.clear();
        instances.clear();
        parser = null;
        document = null;
        root = null;
        configManager = null;
		/* Step 2 : Register the shutdown hook */
        Runtime.getRuntime().removeShutdownHook(hook);
        hook = null;

		/* Set running false */
        running = false;

		/* Log and exit */
        result.add(MSG_STOP);
        System.out.println(MSG_STOP);
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info(MSG_STOP);
            LogUtil.getInstance().info(MSG_STOP);
        }
        return result;
    }

    /**
     * 停止接收/发送线程
     * @param instance
     * @param result
     * @param name
     */
    private void stopOperation(Object instance, ConfigCommandResult result, String name) {
        AbstractMultiThread thread = (AbstractMultiThread) instance;
        if (AbstractMultiThread.RUN == thread.getStatus()) {
            thread.askStop();
        }
        if (AbstractMultiThread.STOPPED != thread.getStatus()) {
            try {
                Thread.currentThread().join(50);
            } catch (Exception e) {
            }
            if (AbstractMultiThread.STOPPED != thread.getStatus()) {
                // interrupt线程(停止接收/发送线程，当阻塞时将抛异常，不用处理，线程依旧会被停掉)
                thread.interruptThread();
                if (AbstractMultiThread.STOPPED == thread.getStatus()) {
                    printStopDaemonInfo(name, result, true);
                } else {
                    try {
                        Thread.currentThread().join(50);
                    } catch (Exception e) {
                    }
                    if (AbstractMultiThread.STOPPED == thread.getStatus()) {
                        printStopDaemonInfo(name, result, true);
                    } else {
                        printStopDaemonInfo(name, result, false);
                    }
                }
            } else {
                printStopDaemonInfo(name, result, true);
            }
        } else {
            printStopDaemonInfo(name, result, true);
        }
    }

    /**
     * 打印守护线程停止信息
     * @param name
     * @param result
     * @param flag
     */
    private void printStopDaemonInfo(String name, ConfigCommandResult result, boolean flag) {
        String infoMessage = null;
        if (flag) {
            infoMessage = MSG_DAEMON_STOP + name;
        } else {
            infoMessage = MSG_DAEMON_STOP_EXCEPTION + name;
        }
        result.add(infoMessage);
        System.out.println(infoMessage);
        if (LogUtil.getInstance() != null) {
            LogUtil.getInstance().info(infoMessage);
        }
    }

}
