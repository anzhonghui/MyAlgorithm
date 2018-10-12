package com.qk.myleetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GroupAnagrams {

	@Test
	public void MyTest() {
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(groupAnagrams(strs));
	}

	/**
	 * 思路：采用key->value的方式，key的取值是每个字符串的字符数组排好序的值，value是list
	 * 时间复杂度：O(N*K*logK) N是strs的长度，K*logK 排序所需要的时间，K是strs中最长的长度
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (int i = 0; i < strs.length; i++) {
			char[] charArray = strs[i].toCharArray();
			// 排序字符数组
			Arrays.sort(charArray);
			// 获取新的串
			String string = String.valueOf(charArray);
			// 判断map是否含数组
			if (!map.containsKey(string)) {
				map.put(string, new ArrayList<>());
			}
			map.get(string).add(strs[i]);
		}

		// 这用法有点
		return new ArrayList<>(map.values());
	}

	/**
	 *  思路：采用key->value的方式，key由指定格式的字符串组成，value是list
	 *  key: #1#2#3#0#0#0...#0 -> abbccc
	 *  时间复杂度：O(N*K) N是strs的长度，K是strs中最长的长度
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagramsBetter(String[] strs) {
		if (strs.length == 0)
			return new ArrayList<>();
		Map<String, List<String>> ans = new HashMap<>();
		int[] count = new int[26];
		for (String s : strs) {
			// 数组所有的数据填充0，初始化
			Arrays.fill(count, 0);
			// 统计
			for (char c : s.toCharArray())
				count[c - 'a']++;

			// 拼接key
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);
			}
			String key = sb.toString();
			if (!ans.containsKey(key))
				ans.put(key, new ArrayList<>());
			ans.get(key).add(s);
		}
		return new ArrayList<>(ans.values());
	}

	public List<List<String>> groupAnagramsByMe(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (int i = 0; i < strs.length; i++) {
			char[] charArray = strs[i].toCharArray();
			Arrays.sort(charArray);
			String string = new String(charArray);
			List<String> list = map.get(string);
			if (list != null) {
				list.add(strs[i]);
			} else {
				List<String> newList = new ArrayList<>();
				newList.add(strs[i]);
				map.put(string, newList);
			}

		}

		return new ArrayList<>(map.values());
	}
}
