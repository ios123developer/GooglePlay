package com.szzgkon.googleplay.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/11 下午2:46
 *
 * 描述：管理线程池
 *
 * 修订历史：
 *
 * ===================================================
 **/



public class ThreadManager {

    private ThreadPoolProxy longPool;
    private ThreadPoolProxy shortPool;

    private ThreadManager() {

    }

    private static ThreadManager instance = new ThreadManager();

    public static ThreadManager getInstance() {
        return instance;
    }

    /**
     * 有时根据不同的业务需求，需要创建多个线程池，将不同的耗时任务分别管理
     *
     * @return
     */
    //最多创建多少线程最合适：cpu的核数*2+1
    public synchronized ThreadPoolProxy creatLongPool() {
        if (longPool == null) {
            longPool = new ThreadPoolProxy(5, 5, 5000L);

        }
        return longPool;

    }

    public synchronized ThreadPoolProxy creatShortPool() {
        if (shortPool == null) {
            shortPool = new ThreadPoolProxy(3, 3, 5000L);

        }
        return shortPool;

    }

   public class ThreadPoolProxy {
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;
        private ThreadPoolExecutor pool;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long time) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.time = time;
        }

        public void execute(Runnable runnable) {
            if (pool == null) {
                //创建线程池
                /**
                 * 参数1：线程池里面有多少个线程
                 * 参数2：如果排队满了，额外开的线程数（和参数5有关系）
                 * 参数3:如果线程池没有要执行的任务，需要存活多久
                 * 参数4：时间的单位（存活多久的时间的单位）
                 * 参数5：如果线程池里管理的线程都已经用了，剩下的任务临时存到LinkedBlockingQueue对象中排队
                 */
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, time, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(10));
            }
            pool.execute(runnable);//调用线程池 执行异步任务


        }

        public void cancel(Runnable runnable) {
            if (pool != null && pool.isShutdown() && pool.isTerminated()) {
                pool.remove(runnable);//取消异步任务
            }
        }
    }
}
