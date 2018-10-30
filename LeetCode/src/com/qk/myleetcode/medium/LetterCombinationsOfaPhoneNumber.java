package com.qk.myleetcode.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 *	17.Letter Combinations Of a Phone Number
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
 * @Description : 给定数字键，找出数字键中包含字母的所有可能情况，即排列组合
 * @Programme：采用深度优先搜索的方式，沿着树的深度一直遍历节点（将字母看做节点），通过递归找结果集
 * 例如：     R    
 *      /  |  \
 *     a   b   c
 *    /|\ /|\ /|\
 *    def def def
 * 结果：ad、ae、af；bd、be、bf；cd、ce、cf
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class LetterCombinationsOfaPhoneNumber {

	@Test
	public void MyTest() {
		letterCombinations("23");
	}

	public List<String> letterCombinations(String digits) {

		// 将事先存储好的数字跟字符的对应
		String[] table = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		List<String> list = new ArrayList<>();
		dfsLetterCombinations(list, digits, "", 0, table);
		System.out.println(list.toString());

		return list;
	}

	/**
	 * 
	 * @param list   存放结果的集合
	 * @param digits 数字
	 * @param curr   当前的串
	 * @param index  当前遍历的索引值
	 * @param tables 存储好的字符串数组
	 * @use 扩充：dfs 深度优先搜索：沿着树的深度遍历树的节点，尽可能深的搜索树的分支（时间复杂度：b^m（b是分支数，m是最大深度））
	 */
	public void dfsLetterCombinations(List<String> list, String digits, String curr, int index, String[] tables) {

		// 当最后一个字符串的第一个字符调用到该处时，长度符合条件，添加到集合中，return
		// return后，最后一个字符串的第二个字符执行到该处
		if (index == digits.length()) {
			if (curr.length() != 0) {
				list.add(curr);
			}
			return;
		}

		String temp = tables[digits.charAt(index) - '0'];
		for (int i = 0; i < temp.length(); i++) {
			String next = curr + temp.charAt(i);
			dfsLetterCombinations(list, digits, next, index + 1, tables);
		}
	}

}
