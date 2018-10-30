package com.qk.myleetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

/**
 * 
 * 20.Valid Parentheses
 *  ┏┓　　┏┓
 * ┏┛┻━━━━┛┻┓
 * ┃　　　　　┃
 * ┃　　　━　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　 ┃
 * ┃　　　┻　　┃
 * ┃　　　　　 ┃
 * ┗━━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　┣┓
 * 　　┃　　　　　　┏┛
 * 　　┗┓┓┏━┳┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 匹配括号
 * @Programme：通过栈的思想解决，将左半部分存入栈push，如果碰到右半部分，pop取出
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class ValidParentheses {

	@Test
	public void MyTest() {
//		System.out.println(isValid("()"));// true
//		System.out.println(isValid("()[]{}"));// true
//		System.out.println(isValid("(]"));// false
//		System.out.println(isValid("([)]"));// false
//		System.out.println(isValid("{[]}"));// true
//		System.out.println(isValid("(()("));// false
//		System.out.println(isValid("[({(())}[()])]"));// true
		System.out.println(isValidByStack("()"));// true
		System.out.println(isValidByStack("()[]{}"));// true
		System.out.println(isValidByStack("(]"));// false
		System.out.println(isValidByStack("([)]"));// false
		System.out.println(isValidByStack("{[]}"));// true
		System.out.println(isValidByStack("(()("));// false
		System.out.println(isValidByStack("[({(())}[()])]"));// true
	}

	/**
	 * 思路三（参考）：栈的思想解决
	 * 
	 * @param
	 * @return
	 */
	public boolean isValidByStack(String s) {
		if (s == null || s.isEmpty())
			return true;

		// 定义一个栈
		Stack<Character> stack = new Stack<>();

		String open = "{[(";
		String close = "}])";

		for (char c : s.toCharArray()) {
			// 如果是开阔号，放入栈
			if (open.indexOf(c) != -1) {
				stack.push(c);
			} else {
				// 闭括号
				// 1. 说明是空串
				if (stack.isEmpty()) {
					return false;
				}

				// 2.如果不为空，提取出来对比
				if (close.indexOf(c) != open.indexOf(stack.pop())) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	/**
	 * 思路二（自己）：通过截取 [({(())}[()])] ，对比成功的截取出来，即从字符串中剔除
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {

		if (s == null || s.length() % 2 != 0) {
			return false;
		}

		if (s.length() == 0 || "".equals(s)) {
			return true;
		}

		Map<Character, Character> maps = new HashMap<>();
		maps.put('(', ')');
		maps.put('[', ']');
		maps.put('{', '}');

		int len = s.length();
		StringBuilder sBuilder = new StringBuilder(s);
		// 第二步遍历中心点查找
		for (int i = 0; i < len; i++) {
			if (maps.get(sBuilder.charAt(i)) != null && i + 1 < len) {
				if (maps.get(sBuilder.charAt(i)) == sBuilder.charAt(i + 1)) {
					sBuilder.replace(i, i + 2, "");
					len -= 2;

					// 区别嵌套和不嵌套
					if (i == 0) {
						i--;
					} else {
						i -= 2;
					}
				}
			} else {
				break;
			}
		}

		return "".equals(sBuilder.toString());
	}

	/**
	 * 思路一：通过取中间值对比两边，无法解决的情况"[({(())}[()])]"
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid2(String s) {

		if (s == null || s.length() % 2 != 0) {
			return false;
		}

		if (s.length() == 0 || "".equals(s)) {
			return true;
		}

		Map<Character, Character> maps = new HashMap<>();
		maps.put('(', ')');
		maps.put('[', ']');
		maps.put('{', '}');
		maps.put(']', '[');
		maps.put('}', '{');
		maps.put(')', '(');

		int len = s.length();
		int left, right = 0;
		right = len / 2;
		left = right - 1;

		// 遍历
		while (left >= 0 && right < len) {
			// 判断是成对的情况 {[]}
//			System.out.println(s.charAt(left));
//			System.out.println(s.charAt(right));
			if (maps.get(s.charAt(left)) == s.charAt(right)) {
				left--;
				right++;
				continue;

				// 判断不包含的情况 ()[]{}
			} else if (!(left - 1 < 0) && maps.get(s.charAt(left - 1)) == s.charAt(left)) {
				left -= 2;
			} else if (!(right + 1 >= len) && maps.get(s.charAt(right)) == s.charAt(right + 1)) {
				right += 2;
			} else {
				return false;
			}

		}

		return true;
	}
}
