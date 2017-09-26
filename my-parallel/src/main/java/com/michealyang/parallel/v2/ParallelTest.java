package com.michealyang.parallel.v2;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/9/18
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class ParallelTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MWorker mWorker1 = new MWorker("hehe1", new MWorker.Worker() {
            @Override
            public Object execute() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                return true;
            }
        });
        MWorker mWorker2 = new MWorker("hehe1", new MWorker.Worker() {
            @Override
            public Object execute() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
//
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                return true;
            }
        });
        MWorker mWorker3 = new MWorker("hehe1", new MWorker.Worker() {
            @Override
            public Object execute() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
//
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                return true;
            }
        });

        MGroup mGroup = new MGroup();
        mGroup.add(mWorker1);
        mGroup.add(mWorker2);
        mGroup.add(mWorker3);
        mGroup.run();

        System.out.println(System.currentTimeMillis() - start);
    }
}
