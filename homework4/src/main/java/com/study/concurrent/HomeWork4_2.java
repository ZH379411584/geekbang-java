package com.study.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hong.zheng
 * @Date: 10/15/21 8:40 PM
 **/
public class HomeWork4_2 {

    public static void main(String[] args) throws Exception {
        method11();
    }

    /**
     * 解决内部类不能修改的问题
     */
    private static class Local{
        private Object object;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
    /**
     * Thread.join
     * @throws InterruptedException
     */
    public static void method1() throws InterruptedException {
        Local local = new Local();
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Son Thread End");
            local.setObject(1L);
        });
        thread.start();
        thread.join();
        System.out.println("Main Thread End result:" + local.getObject());

    }

    /**
     * Object.wait()
     * @throws InterruptedException
     */
    public static void method2() throws InterruptedException {
        Local local = new Local();
        Object object = new Object();
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Son Thread End");
            synchronized (object){
                local.setObject(1L);
                object.notifyAll();
            }


        });
        thread.start();
        synchronized (object){
            object.wait();
            System.out.println("wait end");
        }


        System.out.println("Main Thread End result:" + local.getObject());

    }


    /**
     * Lock
     * @throws InterruptedException
     */
    public static void method3() throws InterruptedException {
        Local local = new Local();
        Lock lock = new ReentrantLock();

        Thread thread = new Thread(()->{
            lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println("Son Thread End");
                    local.setObject(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally
                    {
                        lock.unlock();
                    }
        });
        thread.start();
        // 让子线程先获得锁
        Thread.sleep(10);
        lock.lock();
        try {
            System.out.println("Main Thread End result:" + local.getObject());
        }finally {
            lock.unlock();
        }

    }

    /**
     * Condition
     * @throws InterruptedException
     */
    public static void method4() throws InterruptedException {
        Local local = new Local();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("Son Thread End");
                condition.signal();
                local.setObject(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        thread.start();

        lock.lock();
        try {
            condition.await();
            System.out.println("Main Thread End result:" + local.getObject());
        }finally {
            lock.unlock();
        }

    }

    /**
     * FutureTask
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void method5() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            Thread.sleep(1000);
            System.out.println("Son Thread End");
            return 1;
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("result:"+futureTask.get());
        System.out.println("Main Thread End");

    }

    /**
     * Future
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void method6() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future future = executorService.submit(()->{
            Thread.sleep(1000);
            System.out.println("Son Thread End");
            return 1;
        });
        System.out.println("result:"+future.get());
        System.out.println("Main Thread End");
        executorService.shutdown();

    }

    /**
     * CountDownLatch
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void method7() throws  InterruptedException {
        Local local = new Local();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Son Thread End");
            local.setObject(1L);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println("Main Thread End result:" + local.getObject());

    }


    /**
     * CompletableFuture
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void method8() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            System.out.println("ThreadName:"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        System.out.println("Main Thread End result:" + future.get());

    }


    /**
     * LockSupport
     */
    public static void method9()  {

        Local local = new Local();
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Son Thread End");
            local.setObject("1");
            LockSupport.unpark(mainThread);

        });
        thread.start();
        System.out.println("Main Thread park");
        LockSupport.park();
        System.out.println("Main Thread End result:" + local.getObject());
    }

    /**
     * synchronized
     */
    public static void method10() throws InterruptedException {

        Local local = new Local();
        Thread thread = new Thread(()->{
            synchronized (local){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Son Thread End");
                local.setObject("1");
            }


        });
        thread.start();
        // 让子线程先获得锁
        Thread.sleep(10);
        synchronized (local){
            System.out.println("Main Thread End result:" + local.getObject());

        }
    }

    /**
     * synchronized
     */
    public static void method11() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        Local local = new Local();
        Thread thread = new Thread(()->{

                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("Son Thread End");
                    local.setObject("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }


        });
        thread.start();
        // 让子线程先获得锁
        Thread.sleep(10);
        try{
            System.out.println("Main Thread wait");
            semaphore.acquire();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

        System.out.println("Main Thread End result:" + local.getObject());


    }

}