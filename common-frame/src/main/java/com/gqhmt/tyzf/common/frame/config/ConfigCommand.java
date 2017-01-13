package com.gqhmt.tyzf.common.frame.config;

import java.util.Vector;

/**
 * Created by zhou on 2016/10/20.
 * Description: 命令封装类
 */
public class ConfigCommand implements ConfigConstants {

    //命令串
    private String command = null;
    //命令参数
    private Vector<String> parameters = new Vector<String>();

    /**
     * 构造函数
     */
    public ConfigCommand() {
        this.command = COMMAND_UNKNOWN;
    }

    /**
     * 构造函数
     * @param commands 命令字符串，以|分隔；例：命令|命令参数）
     */
    public ConfigCommand(String commands) {
        if (commands == null)
            this.command = COMMAND_UNKNOWN;
        else if (commands.indexOf(COMMAND_SPLIT) <= 0)
            this.command = commands;
        else {
            this.command = commands.substring(0, commands.indexOf(COMMAND_SPLIT));
            String parameter = commands.substring(commands.indexOf(COMMAND_SPLIT) + 1);
            while (parameter.indexOf(COMMAND_SPLIT) > 0) {
                parameters.addElement(parameter.substring(0, parameter.indexOf(COMMAND_SPLIT)));
                parameter = parameter.substring(parameter.indexOf(COMMAND_SPLIT) + 1);
            }
            parameters.addElement(parameter);
        }
    }

    /**
     * 获取命令参数里的第i个命令
     * @param i
     * @return
     */
    public String getParameter(int i) {
        if (i < 0 || i >= parameters.size())
            return null;
        return (String) parameters.get(i);
    }

    @Override
    public String toString() {
        StringBuffer msg = new StringBuffer(command == null ? COMMAND_UNKNOWN : command);
        for (int i = 0, s = parameters.size(); i < s; i++) {
            msg.append(COMMAND_SPLIT + (String) parameters.get(i));
        }
        return msg.toString();
    }

    /**************getter,setter方法******************/
    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void addParameter(String parameter) {
        parameters.add(parameter);
    }

    public Vector<String> getParameters() {
        return parameters;
    }

}
