package com.gqhmt.tyzf.common.frame.handler;

import com.gqhmt.tyzf.common.frame.common.IConfigurable;

/**
 * Created by zhou on 2016/10/21.
 * Description: 业务处理线程接口
 */
public interface IServiceHandler extends IConfigurable {

    /**
     * 执行方法
     */
    public void execute();

    /**
     * 尝试停止方法
     */
    public void askStop();

    /**
     * 停止线程方法
     */
    public void interruptThread();

    /**
     * 获取状态
     * @return
     */
    public int getStatus();


}
