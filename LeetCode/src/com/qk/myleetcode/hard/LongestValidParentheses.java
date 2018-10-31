package com.qk.myleetcode.hard;

import java.util.Stack;

import org.junit.Test;

/**
 * 
 * 32.Longest Valid Parentheses    
 * @Description : 找最大的有效括号长度
 * @Programme:使用栈的数据结构，存储字符的坐标；通过判断是否匹配，剔除元素；栈中最后剩下的数字即为不匹配的坐标；计算
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月20日
 */
public class LongestValidParentheses {

	/**
	 * @Description:
	 * 		Given a string containing just the characters '(' and ')', 
	 * 		find the length of the longest valid (well-formed) parentheses substring.
	 * @Example:
	 * 		(()  -> () -> 2
	 * @Pragramme:
	 * 		可以利用栈的思想解决
	 */
	@Test
	public void MyTest() {
//		System.out.println(longestValidParentheses("(()"));
//		System.out.println(longestValidParentheses(")()())"));
//		System.out.println(longestValidParentheses(")((()))())"));
//		System.out.println(longestValidParentheses("()(()"));
		System.out.println(longestValidParenthesesByStack("()(()()(((()))"));
	}

	/**
	 * 使用栈，通过记录出现不匹配的坐标计算最长的有效括号，速度慢，但是便于理解
	 * @param s
	 * @return
	 */
	private int longestValidParenthesesByStack(String s) {
		int n = s.length(), longest = 0;
		// 最终存放的是不匹配括号的 ** 坐标 **（因为最终计算的是匹配的最长长度，所以栈中存储字符的坐标）
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(')
				st.push(i);
			else {
				if (!st.empty()) {
					// 取出栈顶元素
					// 如果匹配，弹出
					if (s.charAt(st.peek()) == '(')
						st.pop();
					// 不匹配，继续push
					else
						st.push(i);
				} else
					st.push(i);
			}
		}

		// 如果栈为空，那么说明括号都匹配，所以长度为length
		if (st.empty())
			longest = n;
		else {
			// a是长度，也是不匹配的坐标，作为下一次计算的长度；b是不匹配的坐标；a是b的上一个不匹配的坐标（从后往前）
			int a = n, b = 0;
			while (!st.empty()) {
				// 取出栈顶元素
				b = st.peek();
				st.pop();
				// 因为坐标从0开始，所以要减1
				longest = Math.max(longest, a - b - 1);
				a = b;
			}
			longest = Math.max(longest, a);
		}
		return longest;
	}

	/**
	 * 快的方法，但不易理解
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;

		if (n == 0)
			return 0;
		int maxLength = 0;

		int[] dp = new int[n];

		for (int i = 0; i < n; i++) {
			// the substring must ends with () or ))
			if (arr[i] != ')') {
				dp[i] = 0;
			} else if (i >= 1) {
				if (arr[i - 1] == '(') {
					if (i >= 2) {
						dp[i] = dp[i - 2] + 2; // this is the good() case
					} else {
						dp[i] = 2; // this is the () case
					}
				} else {
					int k = dp[i - 1];
					// this is important
					if (i - k - 1 >= 0 && arr[i - k - 1] == '(') {
						if (i > k + 2) {
							dp[i] = dp[i - 1] + 2 + dp[i - k - 2]; // this is the good(good) case
						} else {
							dp[i] = dp[i - 1] + 2; // this is the (good) case
						}

					} else {
						dp[i] = 0;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			maxLength = Math.max(maxLength, dp[i]);
		}

		return maxLength;
	}

	/**
	 * 自己想的，没有解决问题
	 * @param s
	 * @return
	 */
	public int longestValidParenthesesByMe(String s) {
		Stack<Character> stack = new Stack<>();
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else {
				if (!stack.isEmpty() && stack.pop() == '(') {
					result += 2;
				}
			}
		}
		return result;
	}
}
