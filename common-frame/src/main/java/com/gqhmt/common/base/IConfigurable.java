package com.gqhmt.common.base;

import com.gqhmt.tyzf.common.frame.exception.FrameException;

/**
 * Created by zhou on 2016/10/20.
 * Description:组件接口
 */
public interface IConfigurable {

    /**
     * 更新组件状态
     * @throws FrameException
     */
    void refresh() throws FrameException;

    /**
     * 检查组件状态
     * @return
     * @throws FrameException
     */
    IStatus checkStatus() throws FrameException;

    /**
     * 组件补偿方法
     * @throws FrameException
     */
    void compensate() throws FrameException;

    /**
     * 释放组件资源
     */
    void release();

}
