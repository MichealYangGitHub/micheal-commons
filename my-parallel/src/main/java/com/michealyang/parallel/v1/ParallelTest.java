package com.michealyang.parallel.v1;

import com.google.common.collect.Lists;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/9/18
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class ParallelTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MGroup mGroup = new MGroup();
        MWorker mWorker1 = new MWorker() {
            @Override
            public Object execute() {
                try {
//                    Thread.sleep(RandomUtils.nextInt(1000));
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
//
                    e.printStackTrace();
                }
                throw new IllegalStateException("ex");
//                return 1;
            }
        };
        MWorker mWorker2 = new MWorker() {
            @Override
            public Object execute() {
                try {
//                    Thread.sleep(RandomUtils.nextInt(3000));
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
//
                    e.printStackTrace();
                }
                return "test";
            }
        };

        MWorker mWorker3 = new MWorker() {
            @Override
            public Object execute() {
                try {
//                    Thread.sleep(RandomUtils.nextInt(2000));
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
//
                    e.printStackTrace();
                }
                return Lists.newArrayList(1, 2, 3);
            }
        };

        mGroup.add(mWorker1);
        mGroup.add(mWorker2);
        mGroup.add(mWorker3);
        mGroup.run();

        System.out.println(mWorker1.getResult());
        System.out.println(mWorker2.getResult());
        System.out.println(mWorker3.getResult());
        System.out.println(System.currentTimeMillis() - start);
    }
}
