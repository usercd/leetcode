package handwriting;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CD
 * @date 6/9/2026
 * 两个线程交替打印字母 一个大写 一个小写 AaBbCc...Zz
 * 两个线程交替打印字母 一个大写 一个小写 AbCd...Yz
 */
public class AlternateLetterPrinting {

    // AaBb
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();
    private static boolean upperTurn = true;

    private static void printLetter() {
        Thread upper = new Thread(() -> {
            for (char c = 'A'; c <= 'Z'; c++) {
                LOCK.lock();
                try {
                    while (!upperTurn) {
                        CONDITION.await();
                    }

                    System.out.print(c);

                    upperTurn = false;
                    CONDITION.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    LOCK.unlock();
                }
            }
        });

        Thread lower = new Thread(() -> {
            for (char c = 'a'; c <= 'z'; c++) {
                LOCK.lock();
                try {
                    while (upperTurn) {
                        CONDITION.await();
                    }

                    System.out.print(c);

                    upperTurn = true;
                    CONDITION.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    LOCK.unlock();
                }
            }
        });

        lower.start();
        upper.start();
    }


    // AbCd
    private static final ReentrantLock LOCK1 = new ReentrantLock();
    private static final Condition upperCondition = LOCK1.newCondition();
    private static final Condition lowerCondition = LOCK1.newCondition();
    private static boolean upperTurn1 = true;

    private static void printLetter2() {
        Thread upper = new Thread(() -> {

            for (char c = 'A'; c <= 'Y'; c += 2) {
                LOCK1.lock();
                try {
                    while (!upperTurn1) {
                        upperCondition.await();
                    }
                    System.out.print(c);
                    upperTurn1 = false;
                    lowerCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    LOCK1.unlock();
                }
            }
        });

        Thread lower = new Thread(() -> {
            for (char c = 'b'; c <= 'z'; c += 2) {
                LOCK1.lock();
                try {
                    while (upperTurn1) {
                        lowerCondition.await();
                    }
                    System.out.print(c);
                    upperTurn1 = true;
                    upperCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    LOCK1.unlock();
                }
            }
        });
        upper.start();
        lower.start();
    }

    public static void main(String[] args) {
        printLetter();
        // printLetter2();

    }
}
