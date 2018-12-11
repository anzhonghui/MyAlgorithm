package com.qk.swordfingeroffer;

import java.util.Stack;
import org.junit.Test;

/**
 * @Description : 面试题30：包含min函数的栈
 * 定义函数min，push，pop；实现min函数找最小值
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Stack_InterviewQuestions30 {

	private Stack<Integer> data = new Stack<>();
	private Stack<Integer> min = new Stack<>();

	/**
	 * 重写push方法，判断新元素是否小于辅助栈的栈顶元素，入栈
	 * @param value
	 */
	public void push(Integer value) {
		data.push(value);
		if (min.size() == 0 || value < min.peek()) {
			min.push(value);
		} else {
			min.push(min.pop());
		}
	}

	/**
	 * 重写pop方法，弹出data和辅助栈的栈顶元素
	 */
	public void pop() {
		if (data.size() > 0 && min.size() > 0) {
			data.pop();
			min.pop();
		}
	}

	/**
	 * min方法，弹出辅助栈的栈顶元素
	 * @return
	 */
	public Integer min() {
		if (data.size() > 0 && min.size() > 0) {
			return min.peek();
		}
		return null;
	}

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		push(3);
		System.out.println(min());
		push(4);
		System.out.println(min());
		push(2);
		System.out.println(min());
		push(1);
		System.out.println(min());

		pop();
		System.out.println(min());
		pop();
		System.out.println(min());

		push(0);
		System.out.println(min());
	}

}
