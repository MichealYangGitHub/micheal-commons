package com.michealyang.parallel.v1;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/9/18
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class MGroup {
    private static final Logger logger = LoggerFactory.getLogger(MGroup.class);
    private List<MWorker> workers = Lists.newArrayList();
    private CountDownLatch latch;

    public void add(MWorker worker) {
        workers.add(worker);
    }

    public void run() {
        if (!CollectionUtils.isEmpty(workers)) {
            long start = System.currentTimeMillis();
            logger.info("[MGroup] ThreadPool worker num={}", workers.size());
            latch = new CountDownLatch(workers.size());
            for (MWorker worker : workers) {
                if (worker == null) {
                    continue;
                }
                worker.setCountDownLatch(latch);
                MThreadPool.getThreadPool().submit(worker);
            }
            await();
            logger.info("[MGroup.TIME] time cost: {} ms", System.currentTimeMillis() - start);
        }
    }

    private void await() {
        try {
            /**
             * 应对如下这种情况，即没有调用run()就调用await()，或者没有任何Worker即调用await()
             * MGroup group = new MGroup();
             * group.await();
             */
            if(latch != null) {
                latch.await();
            }
        } catch (InterruptedException e) {
            logger.error("[MGroup] ThreadPool interrupted!", e);
        }finally {
            workers.clear();
        }
    }
}
