package com.gqhmt.core.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * 任务线程池
 *
 * @author zhaoenyue
 * @create 2016年7月11日
 */
public class ThreadExecutor {
    private static final int POOL_SIZE = 100;
    private static ExecutorService executorService;

    private static ExecutorService getExecutor() {
        if (executorService == null || executorService.isShutdown()) {
            synchronized (ThreadFactory.class) {
                if (executorService == null || executorService.isShutdown()) {
                    executorService = Executors.newFixedThreadPool(POOL_SIZE);
                }
            }
        }
        return executorService;
    }

    public static <T extends Runnable> void execute(T thread) {
        getExecutor().execute(thread);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return getExecutor().submit(task);
    }

}
