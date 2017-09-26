package com.michealyang.parallel.v1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/9/18
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class MThreadPool {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(20, new NamedThreadFactory("Micheal"));
    private MThreadPool() {
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    static class NamedThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;


        public NamedThreadFactory(String preffix) {

            StringBuffer sb = new StringBuffer();
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = sb.append(preffix).append("-THREAD-").toString();
        }

        public Thread newThread(Runnable r) {

            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
