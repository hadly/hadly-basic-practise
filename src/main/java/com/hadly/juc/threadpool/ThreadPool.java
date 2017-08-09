/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadly.juc.threadpool;

import java.util.concurrent.*;

/**
 * Thread pool.
 *
 * @author lizhinian
 */
public class ThreadPool {

    private static final int POOL_SIZE_DS_REGISTER_MIN = 1;
    private static final int POOL_SIZE_DS_REGISTER_MAX = 10;
    private static final int POOL_SIZE_MISC_MIN = 1;
    private static final int POOL_SIZE_MISC_MAX = 20;
    private static final int POOL_SIZE_MSG_MIN = 1;
    private static final int POOL_SIZE_MSG_MAX = 20;
    private static final int CACHED_POOL_QUEUE_SIZE_MAX = 2000;
    //Must specify a max pool size, otherwise may cause out of memory if too many threads are created

    //Thread pool to handle misc tasks
    private static final ExecutorService miscPool = new CachedThreadPoolBuilder().
            setMinSize(POOL_SIZE_MISC_MIN).setMaxSize(POOL_SIZE_MISC_MAX).setQueueSize(CACHED_POOL_QUEUE_SIZE_MAX).build();

    //Thread pool to handle DS register
    private static final ExecutorService dsRegisterPool = new CachedThreadPoolBuilder().
            setMinSize(POOL_SIZE_DS_REGISTER_MIN).setMaxSize(POOL_SIZE_DS_REGISTER_MAX).setQueueSize(CACHED_POOL_QUEUE_SIZE_MAX).build();

    //Thread pool to handle messages between Node and KUP
    private static final ExecutorService msgPool = new CachedThreadPoolBuilder().
            setMinSize(POOL_SIZE_MSG_MIN).setMaxSize(POOL_SIZE_MSG_MAX).setQueueSize(CACHED_POOL_QUEUE_SIZE_MAX).build();

    public static ExecutorService getCachedPool() {
        return miscPool;
    }

    public static ExecutorService getDsRegisterPool() {
        return dsRegisterPool;
    }

    public static ExecutorService getMsgPool() {
        return msgPool;
    }

    /**
     * Create a CachedThreadPool.
     * 1. When task is submitted, if thread count does not reach minSize, then create a new thread
     * and bind the task(i.e., will not reuse alreay created threads before thread count reaches minSize)
     * 1.a By default, minSize is 0, it can be set as non-zero to guarantee minimum not-be-recycled threads.
     * <p>
     * 2. After {@code minSize} times' submit, the new task will be added to Queue, all of the thread in the Pool
     * take task from Queue and execute it.
     * 2.a If the queue is full, will create new thread until the thread count reaches the maxSize.
     * 2.b When the queue is full and the threads count reaches maxSize, RejectHandler will be executed. The default
     * policy is CallerRunsPolicy. Of course you can use other policies.
     * <p>
     * 3. The threads that greater than minSize and less than maxSize will be recycled if no task
     * to be executed within keepAliveTime. The default value of keeAliveTime is 60 seconds, and can be configured too.
     */
    private static class CachedThreadPoolBuilder {

        private int minSize = 0;
        private int maxSize = Integer.MAX_VALUE;
        private int keepAliveSecs = 60;
        private int queueSize = 0;

        private ThreadFactory threadFactory = null;
        private RejectedExecutionHandler rejectHandler;

        public CachedThreadPoolBuilder setMinSize(int minSize) {
            this.minSize = minSize;
            return this;
        }

        public CachedThreadPoolBuilder setMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public CachedThreadPoolBuilder setKeepAliveSecs(int keepAliveSecs) {
            this.keepAliveSecs = keepAliveSecs;
            return this;
        }

        public CachedThreadPoolBuilder setQueueSize(int queueSize) {
            this.queueSize = queueSize;
            return this;
        }

        public CachedThreadPoolBuilder setThreadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

        public CachedThreadPoolBuilder setRejectHanlder(RejectedExecutionHandler rejectHandler) {
            this.rejectHandler = rejectHandler;
            return this;
        }

        public ExecutorService build() {

            if (threadFactory == null) {
                threadFactory = Executors.defaultThreadFactory();
            }

            BlockingQueue<Runnable> queue = null;
            if (queueSize == 0) {
                queue = new LinkedBlockingQueue<>();
            } else {
                queue = new ArrayBlockingQueue<>(queueSize);
            }

            if (rejectHandler == null) {
                rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();
            }

            return new ThreadPoolExecutor(minSize, maxSize, keepAliveSecs, TimeUnit.SECONDS,
                    queue, threadFactory, rejectHandler);
        }
    }

    /**
     * Create a FixedThreadPool.
     * 1. When task is submited, if thread count does not reach poolSize, then create a new thread
     * and bind the task(i.e., will not reuse alreay created threads before thread count reaches poolSize)
     * 1.A poolSize must be specified.
     * <p>
     * 2. After {@code poolSize} times' submit, the new task will be added to Queue, all of the thread in the Pool
     * take task from Queue and execute it.
     * 2.a By default, the Queue will be unlimited LinkedBlockingQueue. It can be set to bounded Queue by specify queueSize.
     * 2.b If bounded queue is used, when the queue is full, RejectHandler will be executed. The default policy is
     * AbortPolicy which will throw RejectedExecutionException. Of course you can use
     * CallerRunsPolicy, DiscardOldestPolicy or DiscardPolicy.
     * <p>
     * 3. All of the threads are core threads, so, will not be recycled.
     */
    private static class FixedThreadPoolBuilder {

        private int poolSize = 0;
        private int queueSize = 0;

        private ThreadFactory threadFactory = null;
        private RejectedExecutionHandler rejectHandler;

        public FixedThreadPoolBuilder setPoolSize(int poolSize) {
            this.poolSize = poolSize;
            return this;
        }

        public FixedThreadPoolBuilder setQueueSize(int queueSize) {
            this.queueSize = queueSize;
            return this;
        }

        public FixedThreadPoolBuilder setThreadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

        public FixedThreadPoolBuilder setRejectHanlder(RejectedExecutionHandler rejectHandler) {
            this.rejectHandler = rejectHandler;
            return this;
        }

        public ExecutorService build() {
            if (poolSize < 1) {
                throw new IllegalArgumentException("size not set");
            }

            BlockingQueue<Runnable> queue = null;
            if (queueSize == 0) {
                queue = new LinkedBlockingQueue<>();
            } else {
                queue = new ArrayBlockingQueue<>(queueSize);
            }

            if (threadFactory == null) {
                threadFactory = Executors.defaultThreadFactory();
            }

            if (rejectHandler == null) {
                rejectHandler = new ThreadPoolExecutor.AbortPolicy();
            }

            return new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, queue, threadFactory,
                    rejectHandler);
        }
    }
}
