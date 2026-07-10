package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author CD
 * @date 7/10/2026
 */
public class Code_232_ImplementQueueUsingStacks {
    // 接收新加入的元素
    private final Deque<Integer> inStack = new ArrayDeque<>();

    // 负责取出元素
    private final Deque<Integer> outStack = new ArrayDeque<>();

    public Code_232_ImplementQueueUsingStacks() {
    }

    // 将元素加入队列尾部
    public void push(int x) {
        inStack.push(x);
    }

    // 确保 outStack 中有可以取出的元素
    private void moveIfNeeded() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    // 移除并返回队列头部元素
    public int pop() {
        moveIfNeeded();
        return outStack.pop();
    }

    // 返回队列头部元素，但不移除
    public int peek() {
        moveIfNeeded();
        return outStack.peek();
    }

    // 判断队列是否为空
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
