package multi_thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

/**
 * LeetCode 1114. 按序打印
 * 
 * 题目描述：
 * 我们提供了一个类：
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B 和 C 将会共用一个 Foo 实例。
 * 线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * 
 * 解题思路：
 * 提供多种同步机制的实现方式，包括使用 volatile 变量、信号量（Semaphore）、锁和条件变量（Lock 和 Condition）、CountDownLatch 以及 synchronized + wait + notifyAll。
 * 每种方法都确保了方法调用的顺序性。
 * 
 * 时间复杂度：O(1) 每个方法的时间复杂度为常数时间
 * 空间复杂度：O(1) 使用的额外空间为常数空间
 */

public class Code_1114_PrintInOrder {
    // 使用volatile变量作为标志位
    class Foo {
        private volatile int flag = 1;

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag = 2;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (flag != 2) {
                Thread.yield();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag = 3;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (flag != 3) {
                Thread.yield();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    // 使用信号量
    class FooSemaphore {
        private final Semaphore semaphore2;
        private final Semaphore semaphore3;

        public FooSemaphore() {
            semaphore2 = new Semaphore(0);
            semaphore3 = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            semaphore2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            semaphore2.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            semaphore3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            semaphore3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    // 使用锁和条件变量

    class FooLock {
        private final Lock lock;
        private final Condition condition2;
        private final Condition condition3;
        private int flag;

        public FooLock() {
            lock = new ReentrantLock();
            condition2 = lock.newCondition();
            condition3 = lock.newCondition();
            flag = 1;
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            try {
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                flag = 2;
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            try {
                while (flag != 2) {
                    condition2.await();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                flag = 3;
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            try {
                while (flag != 3) {
                    condition3.await();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
            } finally {
                lock.unlock();
            }
        }
    }

    // 使用CountDownLatch
    class FooCountDownLatch {
        private final CountDownLatch latch2;
        private final CountDownLatch latch3;

        public FooCountDownLatch() {
            latch2 = new CountDownLatch(1);
            latch3 = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            latch2.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            latch2.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            latch3.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            latch3.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    // synchronized + wait + notifyAll
    class FooSynchronized {
        private int flag;
        public FooSynchronized() {
            flag = 1;
        }
        public synchronized void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag = 2;
            notifyAll();
        }
        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (flag != 2) {
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag = 3;
            notifyAll();
        }
        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (flag != 3) {
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}