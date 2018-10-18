package com.qk.myleetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SubstringWithConcatenationOfAllWords {

	/**
	 * s = "barfoothefoobarman",
	 * words = ["foo","bar"]
	 */
	@Test
	public void MyTest() {
//		System.out.println(findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
//		System.out.println(findSubstring("wordgoodstudentgoodword", new String[] { "word", "student" }));
//		System.out.println(findSubstring("wordgoodgoodwgoodbestword", new String[] { "word", "good", "best", "word" }));
		System.out.println(findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake",
				new String[] { "fooo", "barr", "wing", "ding", "wing" }));
	}

	/**
	 * 按照块对字符串进行切分
	 * 举例：barfoothefoobarman    "foo", "bar"；判断是否符合条件
	 * 第一次切分：barfoo  |  thefoo  |  barman
	 * 第二次切分：arfoot  |  hefoob  |  arman
	 * 第三次切分：rfooth  |  efooba  |  rman
	 * 第四次切分：foothe  |  foobar  |  man
	 * 第五次切分：oothef  |  oobarm  |  an
	 * 第六次切分：othefo  |  obarma  |  n
	 *
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstring(String s, String[] words) {
		int n = s.length(), m = words.length;
		List<Integer> result = new ArrayList<>();
		if (m == 0 || n == 0)
			return result;
		int l = words[0].length();
		// 存储words出现的次数
		Map<String, Integer> map = new HashMap<>();
		for (String w : words) {
			map.put(w, map.getOrDefault(w, 0) + 1);
		}

		for (int i = 0; i < l; i++) {
			for (int j = 0; j + m * l <= n; j = j + l) {
				// 截取指定长度的串
				String ss = s.substring(j, j + m * l);
				Map<String, Integer> temp = new HashMap<>();
				// 按照子串的长度解析截取的串
				for (int k = m - 1; k >= 0; k--) {
					// 根据长度，截取出指定长度的子串
					String w = ss.substring(k * l, (k + 1) * l);
					// 统计子串出现的此处
					int count = temp.getOrDefault(w, 0) + 1;
					// 比对大小，如果大于，跳出；获取没有，也大于，跳出
					if (count > map.getOrDefault(w, 0)) {
						// j 值跳跃，跳过不符合条件的
						j = j + k * l;
						break;
						// 如果等于，说明得到了最终结果
					} else if (k == 0) {
						result.add(j);
					} else {
						// 将符合条件的值添加到临时的map集合中
						temp.put(w, count);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 采用两个map存储words的方式，一个map存储words，另一个map存储截取的words（慢）
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstringSlow(String s, String[] words) {
		// 存放结果集
		List<Integer> indexes = new ArrayList<>();
		if (s == null || "".equals(s) || words.length == 0) {
			return indexes;
		}
		// 存放words，以及他们出现的次数
		Map<String, Integer> counts = new HashMap<>();
		for (String word : words) {
			counts.put(word, counts.getOrDefault(word, 0) + 1);
		}
		// 获取母串的长度，words的长度，子串的长度
		int n = s.length(), num = words.length, len = words[0].length();
		// 长度要减去最后一串的长度
		for (int i = 0; i < n - num * len + 1; i++) {
			// 存放应匹配的串和出现的次数
			Map<String, Integer> seen = new HashMap<>();
			int j = 0;
			while (j < num) {
				String word = s.substring(i + j * len, i + (j + 1) * len);
				// 如果截取的串中包含，则放入map
				if (counts.containsKey(word)) {
					seen.put(word, seen.getOrDefault(word, 0) + 1);
					// 检查两个串中相同的key
					if (seen.get(word) > counts.getOrDefault(word, 0)) {
						break;
					}
				} else {
					break;
				}
				j++;
			}
			// 匹配j和words的长度
			if (j == num) {
				indexes.add(i);
			}
		}
		return indexes;
	}

	/**
	 * fail
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstringByMe(String s, String[] words) {
		List<Integer> result = new ArrayList<>();
		Set<Character> start = new HashSet<>();

		// 将第一个字符放入set集合中
		for (int i = 0; i < words.length; i++) {
			start.add(words[i].charAt(0));
		}

		int len = start.size() * words[0].length();

		for (int i = 0; i < s.length(); i++) {
			if (start.contains(s.charAt(i))) {
				if ((i + len) <= s.length()) {
					String temp = s.substring(i, i + len);
//					System.out.println(temp);
					boolean flag = true;
					for (int j = 0; j < words.length; j++) {
						if (temp.indexOf(words[j]) == -1) {
							flag = false;
						}
					}
					if (flag) {
						result.add(i);
					}
				}
			}
		}

		return result;
	}
}
