package com.qk.swordfingeroffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

/**
 * @Description : 面试题9：
 * 1.用两个栈实现队列的功能
 * 实现删除头部元素和在尾部追加元素
 * 2.用两个对垒实现栈的功能
 * 实现删除头部元素和在尾部追加元素
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class StackAndQueue_InterviewQuestions9 {

	Stack<String> stack1 = new Stack<>();
	Stack<String> stack2 = new Stack<>();

	public void appendTail(String value) {
		stack1.push(value);
	}

	public String deleteHead() throws Exception {
		// 如果stack2为空，将stack1的所有元素放入stack2，那样stack2就是元素的入栈顺序（即队列的顺讯）
		if (stack2.size() <= 0) {
			while (stack1.size() > 0) {
				String val = stack1.pop();
				stack2.push(val);
			}
		}

		if (stack2.size() == 0) {
			throw new Exception("queue is empty");
		}

		// 删除头部元素，即提出stack2的栈顶元素
		String head = stack2.pop();
		return head;
	}

	/**
	 * @throws Exception 
	 * @Description:
	 */
	@Test
	public void MyTest() throws Exception {
		appendTail("a");
		appendTail("b");
		appendTail("c");
		System.out.println(deleteHead());
		System.out.println(deleteHead());
		appendTail("d");
		System.out.println(deleteHead());
		System.out.println(deleteHead());
	}

	Queue<String> queue1 = new LinkedList<>();
	Queue<String> queue2 = new LinkedList<>();

	public void push(String value) {
		if (queue1.isEmpty() && !queue2.isEmpty()) {
			queue2.add(value);
		} else {
			queue1.add(value);
		}
	}

	/**
	 * 弹出栈顶元素，两个队列轮换着使用
	 * 1.a,b,c入queue1
	 * 2.移除a,b并加入queue2,c删除
	 * 3.移除a并加入queue1,b删除
	 * 4.添加新元素d到不为空的queue1（如果都为空，默认加入1）
	 * 5.移除a并加入queue2，d删除
	 * 6.移除a，a删除
	 * @return
	 * @throws Exception
	 */
	public String pop() throws Exception {
		if (queue2.size() <= 0) {
			while (queue1.size() > 0) {
				String val = queue1.poll();
				if (queue1.isEmpty()) {
					return val;
				}
				queue2.add(val);
			}

		} else {
			while (queue2.size() > 0) {
				String val = queue2.poll();
				if (queue2.isEmpty()) {
					return val;
				}
				queue1.add(val);
			}
		}

		throw new Exception("stack is empty");
	}

	/**
	 * @throws Exception 
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() throws Exception {
		push("a");
		push("b");
		push("c");
		System.out.println(pop());
		System.out.println(pop());
		push("d");
		System.out.println(pop());
		System.out.println(pop());
	}
}
