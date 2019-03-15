package com.qk.myleetcode.range_51_100.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import org.junit.Test;

/**
 * Minimum Window Substring，最小窗口子串
 * @Description : Given a string S and a string T, find the minimum window in S which will contain 
 * all the characters in T in complexity O(n).
 * @Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class MinimumWindowSubstring {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(minWindowBySlidingWindow("ADOBECODEBANC", "ABC"));
	}

	public String minWindow(String s, String t) {
		if (s == null || s.length() < ((t == null) ? 0 : t.length()))
			return "";

		// 统计t字符串中各个字符出现的次数
		int[] cnt = new int[128]; // ascii
		for (char c : t.toCharArray()) {
			cnt[c]++;
		}
		int min = Integer.MAX_VALUE;
		int total = t.length();
		int from = 0;

		for (int i = 0, j = 0; i < s.length(); i++) {
			// 判断s中是否有t中的字符，有的话，长度减少
			if (cnt[s.charAt(i)]-- > 0)
				total--;
			while (total == 0) {
				if (i - j + 1 < min) {
					min = i - j + 1;
					from = j;
				}
				if (++cnt[s.charAt(j++)] > 0)
					total++;
			}
		}
		return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
	}

	/**
	 * 滑动窗口的方法
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindowBySlidingWindow(String s, String t) {
		if (s.length() == 0 || t.length() == 0) {
			return "";
		}
		// Dictionary which keeps a count of all the unique characters in t.
		Map<Character, Integer> dictT = new HashMap<Character, Integer>();
		for (int i = 0; i < t.length(); i++) {
			int count = dictT.getOrDefault(t.charAt(i), 0);
			dictT.put(t.charAt(i), count + 1);
		}
		// Number of unique characters in t, which need to be present in the desired
		// window.
		int required = dictT.size();
		// Left and Right pointer
		int l = 0, r = 0;
		// formed is used to keep track of how many unique characters in t
		// are present in the current window in its desired frequency.
		// e.g. if t is "AABC" then the window must have two A's, one B and one C.
		// Thus formed would be = 3 when all these conditions are met.
		int formed = 0;
		// Dictionary which keeps a count of all the unique characters in the current
		// window.
		Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
		// ans list of the form (window length, left, right)
		int[] ans = { -1, 0, 0 };
		while (r < s.length()) {
			// Add one character from the right to the window
			char c = s.charAt(r);
			int count = windowCounts.getOrDefault(c, 0);
			windowCounts.put(c, count + 1);
			// If the frequency of the current character added equals to the
			// desired count in t then increment the formed count by 1.
			if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
				formed++;
			}
			// Try and contract the window till the point where it ceases to be 'desirable'.
			while (l <= r && formed == required) {
				c = s.charAt(l);
				// Save the smallest window until now.
				if (ans[0] == -1 || r - l + 1 < ans[0]) {
					ans[0] = r - l + 1;
					ans[1] = l;
					ans[2] = r;
				}
				// The character at the position pointed by the
				// `Left` pointer is no longer a part of the window.
				windowCounts.put(c, windowCounts.get(c) - 1);
				if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
					formed--;
				}
				// Move the left pointer ahead, this would help to look for a new window.
				l++;
			}
			// Keep expanding the window once we are done contracting.
			r++;
		}
		return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
	}
}
