package com.qk.myleetcode.range_1_50.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 22.Generate Parentheses
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
 * @Description : 生成括号
 * @Programme：通过递归实现
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class GenerateParentheses {

	@Test
	public void MyTest() {
//		System.out.println(generateParenthesis(1).toString());
//		System.out.println(generateParenthesis(2).toString());
		System.out.println(generateParenthesis(3).toString());
	}

	/**
	 * 生成n对括号的全部有效组合
	 * 
	 * @param num 有几对括号
	 * @return 装有所有括号组合的列表容器
	 */
	public static List<String> generateParenthesis(int num) {
		char[] buffer = new char[num * 2];
		List<String> list = new ArrayList<String>();
		make(list, num, num, buffer, 0);
		return list;
	}

	/**
	 * 通过递归生成n对括号的全部有效组合
	 * 
	 * @param list     装括号组合的容器
	 * @param leftRem  左括号剩余数量
	 * @param rightRem 右括号剩余数量
	 * @param buffer   放括号的字符数组，临时
	 * @param count    插入括号的位置
	 */
	private static void make(List<String> list, int leftRem, int rightRem, char[] buffer, int count) {
		if (leftRem < 0 || rightRem < leftRem) { // 无效输入
			return;
		}
		if (leftRem == 0 && rightRem == 0) { // 木有括号了，得到结果
			String s = String.copyValueOf(buffer);
			list.add(s);
		} else {
			if (leftRem > 0) { // 还有左括号可用则加入左括号
				buffer[count] = '(';
				make(list, leftRem - 1, rightRem, buffer, count + 1);
			}
			if (rightRem > leftRem) { // 右括号比左括号跟多就可以加入右括号
				buffer[count] = ')';
				make(list, leftRem, rightRem - 1, buffer, count + 1);
			}
		}
	}

	public List<String> generateParenthesisByMe(int num) {
		List<String> lists = new ArrayList<>();
		char[] buffer = new char[num * 2];
		makeParenthesis(lists, num, num, buffer, 0);
		System.out.println(lists.toString());
		return lists;
	}

	public void makeParenthesis(List<String> lists, int left, int right, char[] buffer, int count) {
		if (left < 0) {
			return;
		}

		// 如果递归到0，添加
		if (left == 0 && right == 0) {
			lists.add(String.copyValueOf(buffer));
		} else {
			if (left > 0) {
				buffer[count] = '(';
				makeParenthesis(lists, left - 1, right, buffer, count + 1);
			}

			if (right > left) {
				buffer[count] = ')';
				makeParenthesis(lists, left, right - 1, buffer, count + 1);
			}
		}

	}

}
