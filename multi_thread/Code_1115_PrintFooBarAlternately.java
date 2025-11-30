package multi_thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Code_1115_PrintFooBarAlternately {
    // 使用synchronized + wait/notify
    class FooBar {
        private int n;
        private boolean flag = true;
        private final Object lock = new Object();

        public FooBar(int n) {
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
}