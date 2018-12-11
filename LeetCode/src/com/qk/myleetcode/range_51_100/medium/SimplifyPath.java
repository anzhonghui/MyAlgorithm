package com.qk.myleetcode.range_51_100.medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class SimplifyPath {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(simplifyPath("/a/./b/../../c/"));
	}

	/**
	 * 利用栈的思想
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		Deque<String> stack = new LinkedList<>();
		Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
		// 根据斜线‘/’切串
		for (String dir : path.split("/")) {
			// 如果是..或栈不为空，弹出栈顶元素
			if (dir.equals("..") && !stack.isEmpty())
				stack.pop();
			// 如果目录中不包含'..','.'，放入新元素
			else if (!skip.contains(dir))
				stack.push(dir);
		}
		String res = "";
		// 遍历栈，重组目录
		for (String dir : stack)
			res = "/" + dir + res;

		return res.isEmpty() ? "/" : res;
	}

	/**
	 * 用stack
	 * @param path
	 * @return
	 */
	public String simplifyPath2(String path) {
		Stack<String> stack = new Stack<String>();
		String[] tokens = path.split("/");
		for (String token : tokens) {
			if (token.equals(".") || token.equals(""))
				continue;
			else if (token.equals("..")) {
				if (!stack.isEmpty())
					stack.pop();
			} else {
				stack.push(token);
			}
		}

		String res = "";
		while (!stack.isEmpty()) {
			res = "/" + stack.pop() + res;
		}

		return res.length() == 0 ? "/" : res;
	}
}
