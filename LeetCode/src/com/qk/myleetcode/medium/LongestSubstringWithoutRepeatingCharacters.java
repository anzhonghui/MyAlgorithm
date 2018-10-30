package com.qk.myleetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * 3.Longest Substring Without Repeating Characters
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
 * @Description : 返回字符串中不重复序列的最大长度
 * @Programme：采用散列表的思想，标识字符串是否重复，一共128个字符；下面有详细的方法解释
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class LongestSubstringWithoutRepeatingCharacters {

	@Test
	public void MyTest() {
//		System.out.println(Arrays.toString(repeatingCharactersTotal("abcabcbb")));
//		System.out.println(Arrays.toString(repeatingCharactersTotal("bbbbb")));
//		System.out.println(Arrays.toString(repeatingCharactersTotal("pwwkew")));

//		System.out.println(lengthOfLongestSubstring("abcabcbb"));
//		System.out.println(lengthOfLongestSubstring("bbbbb"));
//		System.out.println(lengthOfLongestSubstring("pwwkew"));
//		System.out.println(lengthOfLongestSubstring("pwwkewpeabcdf"));
		System.out.println(lengthOfLongestSubstring("abcabcbb"));
		System.out.println(myLengthOfLongestSubstring("bbbbb"));
		System.out.println(myLengthOfLongestSubstring("pwwkew"));
		System.out.println(myLengthOfLongestSubstring("pwwkewpeabcdf"));
	}
	
	/**
	 ** 优选方案
	 ** 通过hash的思想，0表示前面没有相同的，1表示前面有相同的字符
	 *  index1 用于记录上一次相同字符的位置
	 *  index2 用于记录匹配到的当前字符的位置
	 *  length 用于记录他们之间的长度
	 *  
	 **  此处指截取部分数据，便于理解 abc abc bb
	 *  [..0, 0, 0, 1, 0, 0, 0, 0, 0..] a
	 *  [..0, 0, 0, 1, 1, 0, 0, 0, 0..] bc
	 *	[..0, 0, 0, 1, 1, 1, 0, 0, 0..] abc 说明这三个字符前面都已经有重复
	 *	[..0, 0, 0, 0, 1, 1, 0, 0, 0..] 剔除a
	 *	[..0, 0, 0, 1, 1, 1, 0, 0, 0..] bca
	 *	[..0, 0, 0, 1, 0, 1, 0, 0, 0..] 剔除b
	 *	[..0, 0, 0, 1, 1, 1, 0, 0, 0..] cab
	 *	[..0, 0, 0, 1, 1, 0, 0, 0, 0..] 剔除c
	 *	[..0, 0, 0, 1, 1, 1, 0, 0, 0..] abc
	 *	[..0, 0, 0, 0, 1, 1, 0, 0, 0..] 剔除a，剩bc，因为bc后是b，属于重复
	 *	[..0, 0, 0, 0, 0, 1, 0, 0, 0..] 剔除b，剩c
	 *	[..0, 0, 0, 0, 1, 1, 0, 0, 0..] cb
	 *	[..0, 0, 0, 0, 1, 0, 0, 0, 0..] 剔除c，剩b
	 *	[..0, 0, 0, 0, 0, 0, 0, 0, 0..] 剔除b
	 *	[..0, 0, 0, 0, 1, 0, 0, 0, 0..] b
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0 || s.length() == 1)
			return s.length();

		// 用于记录上一个重复的坐标位置
		int index1 = 0;
		// 用于计算不重复长度的位置
		int index2 = 0;
		// 当前两个坐标之间的长度
		int length = 0;
		// 最大不重复长度
		int max_length = 1;
		// 利用hash的思想存储字符是否有
		int[] arr = new int[128];

		while ((index1 < s.length()) && (index2 < s.length())) {
			char c = s.charAt(index2);
			// 元素没有重复的 +1
			// 如果位置上是否有字符（0表示没有，1表示有）
			if (arr[c] == 0) {
				arr[c] = 1;
				index2++;
				length++;
			} else {
				// 修改最大的长度
				if (length > max_length) {
					max_length = length;
				}

				// 匹配两处索引是否相同，不同则记录此处为0，坐标加1
				if (index1 != index2)
					arr[s.charAt(index1)] = 0;
				index1++;
				length--;
			}
			System.out.println(Arrays.toString(arr));
		}

		return ((index2 - index1) > max_length) ? (index2 - index1) : max_length;
	}

	/**
	 ** 思路：通过记录上一个可以开始往后匹配字符的位置开始统计
	 * arr:   a  b  c  a  b  c  b  b
	 * index: 0  1  2  3  4  5  6  7
	 * pos    0  0  0  1  2  3  5  7
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring2(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int n = s.length();
		if (n == 1)
			return 1;
		HashMap<Character, Integer> map = new HashMap<>();
		int res = 0;
		int i = 0;
		int j = 0;// 作用：记录上一个可以开始往后匹配字符的位置
		while (i < n && j < n) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(map.get(s.charAt(i)) + 1, j);
				System.out.println(s.charAt(i));
				System.out.println(j);
			}
			map.put(s.charAt(i), i);
			res = Math.max(i - j + 1, res);
			i++;
		}
		return res;

	}

	public int myLengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0 || "".equals(s)) {
			return 0;
		}

		// 最大不重复长度
		int maxLen = 0;
		// 当前两个坐标之间的长度
		int len = 0;
		// 用于记录上一个重复的坐标位置
		int index1 = 0;
		// 用于计算不重复长度的位置
		int index2 = 0;
		// 利用hash的思想存储字符是否有
		int[] arr = new int[128];

		while (index1 < s.length() && index2 < s.length()) {
			char c = s.charAt(index2);
			// 如果位置上是否有字符（0表示没有，1表示有）
			if (arr[c] == 0) {
				arr[c] = 1;
				index2++;
				len++;
			} else {
				if (len > maxLen) {
					maxLen = len;
				}
				if (index1 != index2) {
					arr[s.charAt(index1)] = 0;
				}
				index1++;
				len--;
			}
		}

		return index2 - index1 > maxLen ? index2 - index1 : maxLen;
	}


	/**
	 * 自己的方案
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring3(String s) {
		int maxLength = 0;
		int curLength = 0;
		char[] charArray = s.toCharArray();
		String string = "";
		for (int i = 0; i < charArray.length; i++) {
			string += charArray[i];
			for (int j = i + 1; j < charArray.length; j++) {
				if (string.contains(charArray[j] + "")) {
					if (curLength > maxLength) {
						maxLength = curLength;
					}
					curLength = 0;
					string = "";
					break;
				}
				string += charArray[j];
				curLength = string.length();
			}
			if (maxLength == 0) {
				maxLength = string.length();
			}
		}

		return maxLength;
	}

	public int[] repeatingCharactersTotal(String str) {
		char[] charArray = str.toCharArray();
		int[] pos = new int[charArray.length];
		Map<Character, Integer> maps = new HashMap<>();

		for (int i = 0; i < charArray.length; i++) {
			if (maps.containsKey(charArray[i])) {
				pos[i] = maps.get(charArray[i]) + 1;
				maps.put(charArray[i], pos[i]);
			} else {
				maps.put(charArray[i], 0);
			}
		}

		return pos;
	}
}
