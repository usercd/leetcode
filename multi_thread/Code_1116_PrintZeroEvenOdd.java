package multi_thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * LeetCode 1116. 打印零与奇偶数
 * 
 * 题目描述：
 * 假设有这样一个类：
 * public class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 三个不同的线程将会共用一个 ZeroEvenOdd 实例。
 * - 线程 A 将调用 zero()，它只输出 0 。
 * - 线程 B 将调用 even()，它只输出偶数。
 * - 线程 C 将调用 odd()，它只输出奇数。
 * 请设计修改程序，以确保输出的顺序为 "0102030405..."，其中 "0" 总是出现在奇数和偶数之前。
 * 
 * 解题思路：
 * 提供多种同步机制的实现方式，包括使用 synchronized + wait + notifyAll、信号量（Semaphore）、CountDownLatch。
 * 每种方法都确保了按要求的顺序打印数字。
 * 
 * 时间复杂度：O(n) 每个方法的时间复杂度为 O(n)
 * 空间复杂度：O(1) 使用的额外空间为常数空间
 */

public class Code_1116_PrintZeroEvenOdd {

    class PrintZeroEvenOddSynchronized {

        private int n;
        private volatile int state = 0; // 0: zero's turn, 1: even's turn, 2: odd's turn

        public PrintZeroEvenOddSynchronized(int n) {
            this.n = n;
        }

        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                synchronized (this) {
                    while (state != 0) {
                        wait();
                    }
                    printNumber.accept(0);
                    state = (i % 2 == 0) ? 1 : 2; // Set state to even or odd
                    notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                synchronized (this) {
                    while (state != 1) {
                        wait();
                    }
                    printNumber.accept(i);
                    state = 0; // Set state back to zero
                    notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                synchronized (this) {
                    while (state != 2) {
                        wait();
                    }
                    printNumber.accept(i);
                    state = 0; // Set state back to zero
                    notifyAll();
                }
            }
        }

        interface IntConsumer {
            void accept(int x);
        }
    }

    class ZeroEvenOddSemaphore {
        private int n;
        private Semaphore zero = new Semaphore(1);
        private Semaphore odd = new Semaphore(0);
        private Semaphore even = new Semaphore(0);

        public ZeroEvenOddSemaphore(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                zero.acquire();
                printNumber.accept(0);
                if ((i & 1) == 0) {
                    odd.release();
                } else {
                    even.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                even.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                odd.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }

        interface IntConsumer {
            void accept(int x);
        }
    }

    class ZeroEvenOddCountDownLatch {
        private int n;
        private CountDownLatch zero = new CountDownLatch(0);
        private CountDownLatch even = new CountDownLatch(1);
        private CountDownLatch odd = new CountDownLatch(1);

        public ZeroEvenOddCountDownLatch(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                zero.await();
                printNumber.accept(0);
                zero = new CountDownLatch(1);
                if ((i & 1) == 1) {
                    odd.countDown();
                } else {
                    even.countDown();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                even.await();
                printNumber.accept(i);
                even = new CountDownLatch(1);
                zero.countDown();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                odd.await();
                printNumber.accept(i);
                odd = new CountDownLatch(1);
                zero.countDown();
            }
        }

        interface IntConsumer {
            void accept(int x);
        }
    }

}
