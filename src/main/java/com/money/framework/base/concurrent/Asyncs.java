package com.money.framework.base.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 14-12-15
 *
 * @author jc.feng
 * @version V1.0
 */
public class Asyncs {

    public final static ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(2);
    private final static Logger LOG = LoggerFactory.getLogger(Asyncs.class);
//    private final static ThreadPoolExecutor TASK_THREAD_POOL = new ThreadPoolExecutor(32, 256,
//            1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new De("TaskPool"));
//    private final static ThreadPoolExecutor JOB_THREAD_POOL = new ThreadPoolExecutor(64, 512,
//            1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new DefaultThreadFactory("JobPool"));
//
//    public static void executeTask(Runnable runnable) {
//        TASK_THREAD_POOL.execute(runnable);
//    }
//
//    public static void executeJob(Runnable runnable) {
//        JOB_THREAD_POOL.execute(runnable);
//    }

    public static void executeSingleTask(Runnable runnable) {
        FIXED_THREAD_POOL.execute(runnable);
    }
}

