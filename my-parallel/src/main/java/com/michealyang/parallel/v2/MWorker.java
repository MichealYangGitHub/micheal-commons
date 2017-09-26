package com.michealyang.parallel.v2;

import com.michealyang.parallel.v1.MResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/9/19
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class MWorker<T> {
    private String name;
    private Integer expire;
    private Worker worker;
    private CountDownLatch countDownLatch;
    private MResult<T> result;
    private MThreadFactory threadFactory = new MThreadFactory();

    public MWorker(Worker worker) {
        this.worker = worker;
    }

    public MWorker(String name, Worker worker) {
        this.name = new String(name);
        this.expire = expire == null ? 0 : expire;
        this.worker = worker;
    }

    public MWorker(String name, Integer expire, Worker worker) {
        this.name = new String(name);
        this.expire = expire == null ? 0 : expire;
        this.worker = worker;
    }

    class MThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "MParallel-" + name);
        }
    }
    public static abstract class Worker<E> implements Runnable {
        @Override
        public void run() {
            execute();
        }

        public abstract E execute();
    }

    public MThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public MResult<T> getResult() {
        return result;
    }

    public void setResult(MResult<T> result) {
        this.result = result;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getName() {
        return name;
    }

    public Integer getExpire() {
        return expire;
    }

    public Worker getWorker() {
        return worker;
    }
}
