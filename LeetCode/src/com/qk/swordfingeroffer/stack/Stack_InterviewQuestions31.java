package com.qk.swordfingeroffer;

import java.util.Stack;

import org.junit.Test;

/**
 * @Description : 面试题31：栈的压入、弹出序列
 * 给定两个数组，num1/num2，判断num1是栈的压入顺序，num2是栈的弹出顺序，判断是否成立
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Stack_InterviewQuestions31 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		System.out.println(isPopOrder(new int[] { 1, 2, 3, 4, 5 }, new int[] { 4, 3, 5, 1, 2 }));
		System.out.println(isPopOrder(new int[] { 1, 2, 3, 4, 5 }, new int[] { 4, 5, 3, 2, 1 }));
	}

	/**
	 * @Programme：了解详细的栈的入栈和出栈操作
	 * 过程：1,2,3,4,5
	 * 判断：4,5,3,2,1 （步骤略写）
	 * 1. 压入1,2,3,4
	 * 2.弹出3
	 * 3.压入5
	 * 4.弹出5,3,2,1
	 * 
	 * 判断：4,3,5,1,2
	 * 1.压入1,2,3,4
	 * 2.弹出4,3
	 * 3.压入5
	 * 4.弹出5
	 * 5.一下个不能弹出1，所以失败
	 * @param nums
	 * @return
	 */
	public boolean isPopOrder(int[] num1, int[] num2) {

		int len = num1.length;
		boolean possible = false;

		if (num1 != null && num2 != null && len > 0) {
			// 标识两个数组的当前坐标
			int nextPush = 0;
			int nextPop = 0;

			Stack<Integer> stack = new Stack<>();
			while (nextPop < len) {
				// 如果栈为空或者栈顶元素不等于出栈的元素，入栈
				while (stack.isEmpty() || stack.peek() != num2[nextPop]) {
					// 判断出界的情况
					if (nextPush == len) {
						break;
					}
					// 入栈
					stack.push(num1[nextPush]);
					// 入栈的下一个元素+1
					++nextPush;
				}

				// 如果栈顶元素和出栈的元素不等，说明不是符合条件的
				if (stack.peek() != num2[nextPop]) {
					break;
				}
				// 弹出栈顶元素
				stack.pop();
				// 出栈匹配的下一个元素+1
				nextPop++;
			}

			// 判断栈是否为空且下一个出栈的坐标是否跟长度相等
			if (stack.empty() && nextPop == len) {
				possible = true;
			}

		}

		return possible;
	}

}
