package stack;

import java.util.Stack;

/**
 * LeetCode 155. 最小栈
 * 
 * 题目描述：
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类：
 * - MinStack() 初始化堆栈对象。
 * - void push(int val) 将元素 val 推入堆栈。
 * - void pop() 删除堆栈顶部的元素。
 * - int top() 获取堆栈顶部的元素。
 * - int getMin() 获取堆栈中的最小元素。
 * 
 * 解题思路：
 * 使用两个栈来实现：一个主栈用于存储所有元素，另一个辅助栈用于存储当前的最小元素。
 * 每次推入新元素时，如果该元素小于或等于辅助栈的栈顶元素，则也将其推入辅助栈。
 * 弹出元素时，如果弹出的元素等于辅助栈的栈顶元素，则也从辅助栈中弹出。
 * 这样，辅助栈的栈顶始终是当前主栈中的最小元素。
 * 
 * 时间复杂度：O(1) 对于 push，pop，top 和 getMin 操作
 * 空间复杂度：O(n) 最坏情况下辅助栈可能存储所有元素
 */

public class Code_155_MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public Code_155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int removed = stack.pop();
            if (removed == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
