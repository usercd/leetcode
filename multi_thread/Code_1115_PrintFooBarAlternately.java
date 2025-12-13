package multi_thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class Code_1115_PrintFooBarAlternately {
    
    // Thread.yield()
    class FooBarYield {
        private int n;
        private volatile boolean flag = true;

        public FooBarYield(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (!flag) {
                    Thread.yield();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = !flag;
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (flag) {
                    Thread.yield();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = !flag;
            }
        }
    }

    // 使用synchronized + wait/notify
    class FooBarSynchronized {
        private int n;
        private boolean flag = true;
        private final Object lock = new Object();

        public FooBarSynchronized(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (!flag) {
                        lock.wait();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    flag = !flag;
                    lock.notify();
                }

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (flag) {
                        lock.wait();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    flag = !flag;
                    lock.notify();
                }

            }
        }
    }

    // 使用ReentrantLock + Condition
    class FooBarLock {
        private int n;
        private final Lock lock;
        private final Condition condition;
        private boolean flag;

        public FooBarLock(int n) {
            this.n = n;
            this.lock = new ReentrantLock();
            this.condition = lock.newCondition();
            this.flag = true;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (!flag) {
                        condition.await();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    flag = !flag;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (flag) {
                        condition.await();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    flag = !flag;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    // 使用Semaphore
    class FooBarSemaphore {
        private int n;
        private final Semaphore semaphoreFoo;
        private final Semaphore semaphoreBar;

        public FooBarSemaphore(int n) {
            this.n = n;
            this.semaphoreFoo = new Semaphore(1);
            this.semaphoreBar = new Semaphore(0);
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreFoo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                semaphoreBar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreBar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                semaphoreFoo.release();
            }
        }
    }

    // 使用CyclicBarrier
    static class FooBarCyclicBarrier {
        /**
         * CyclicBarrier是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点
         * （common barrier point）。CyclicBarrier在构造时需要指定参与同步的线程数量，
         * 当所有线程都到达屏障点时，屏障被打开，所有被屏障阻塞的线程才会继续执行。
         * CyclicBarrier可以被重用，所以称为“循环”的屏障。
         * 在这个例子中，我们使用CyclicBarrier来确保foo和bar方法交替执行。
         * 当foo方法打印完"foo"后，它会调用cb.await()，等待bar方法到达屏障点。当bar方法打印完"bar"后，
         * 它也会调用cb.await()，此时两个线程都到达了屏障点，屏障被打开，foo和bar方法继续执行，达到交替打印的效果。
         * 注意：为了实现交替打印，我们还使用了一个volatile变量fooExec来指示当前应该执行哪个方法。
         * 当fooExec为true时，foo方法可以执行；当fooExec为false时，bar方法可以执行。
         * 这种方法结合了CyclicBarrier的同步机制和volatile变量的状态指示，实现了线程间的有序交替执行。
         * 
         * CyclicBarrier vs CountDownLatch:
         * 1. CountDownLatch是一次性的，不能重用，而CyclicBarrier可以被重用。
         * 2. CountDownLatch是一个线程等待其他线程完成任务的工具，而CyclicBarrier是多个线程互相等待到达一个公共屏障点的工具。
         * 3. CountDownLatch通常用于一个线程等待多个线程完成任务，而CyclicBarrier用于多个线程之间的协调和同步。
         * 4. CountDownLatch等待的多个线程职责可以不相同，而CyclicBarrier等待的多个线程任务通常是相同的。
         */
        private int n;
        private CyclicBarrier cb = new CyclicBarrier(2);
        volatile boolean fooExec = true;

        public FooBarCyclicBarrier(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (!fooExec) {
                    //false的时候，bar线程在执行，foo线程在这此处空转
                }
                printFoo.run();//打印foo
                fooExec = false;//设置变量
                try {
                    cb.await();//线程foo到达同步点
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                printBar.run();
                fooExec = true;

            }
        }

        public static void main(String[] args) {
            FooBarCyclicBarrier fooBar = new FooBarCyclicBarrier(10);//打印10次foo bar
            Runnable printFoo = () -> {
                System.out.printf("%s\n", "foo");
            };
            Runnable printBar = () -> {
                System.out.printf("%s\n", "bar");
            };
            Thread fooThread = new Thread(() -> {
                try {
                    fooBar.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread barThread = new Thread(() -> {
                try {
                    fooBar.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            fooThread.start();
            barThread.start();
        }
    }

    // 使用BlockingQueue
    class FooBarBlockingQueue {
        private int n;
        private final ArrayBlockingQueue<Integer> queueFoo;
        private final ArrayBlockingQueue<Integer> queueBar;

        public FooBarBlockingQueue(int n) {
            this.n = n;
            this.queueFoo = new ArrayBlockingQueue<>(1);
            this.queueBar = new ArrayBlockingQueue<>(1);
            try {
                queueFoo.put(1); // 初始化时让foo先执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                queueFoo.take();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                queueBar.put(1);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                queueBar.take();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                queueFoo.put(1);
            }
        }
    }

    // LockSupport
    class FooBarLockSupport {
        private int n;
        private volatile Thread fooThread;
        private volatile Thread barThread;

        public FooBarLockSupport(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            fooThread = Thread.currentThread();
            while (barThread == null) { Thread.yield(); }
            for (int i = 0; i < n; i++) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                LockSupport.unpark(barThread);
                LockSupport.park();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            barThread = Thread.currentThread();
            for (int i = 0; i < n; i++) {
                LockSupport.park();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                LockSupport.unpark(fooThread);
            }
        }
    }
}