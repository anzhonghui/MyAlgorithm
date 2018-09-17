package com.qk.myleetcode.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ValidPermutationsForDISequence {

	@Test
	public void Mytest() {
		System.out.println(numPermsDISequence("DID"));
		System.out.println(numPermsDISequence("DD"));
	}

	public int numPermsDISequence(String s) {
		if (s == null || s.length() == 0 || s.length() == 1 || "".equals(s)) {
			return 0;
		}
		
		this.s = s;
		
		int[] p = new int[s.length() + 1];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
		System.out.println(Arrays.toString(p));

		// 排列组合
		allSort(p, 0, p.length - 1);
		return count;
	}

	private Integer count = 0;
	private String s = "DID";

	public void allSort(int[] array, int begin, int end) {
		// 打印数组的内容
		if (begin == end) {
			System.out.println(Arrays.toString(array));
			boolean temp = true;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == 'D' && array[i] > array[i + 1]) {
					continue;
				} else if (s.charAt(i) == 'I' && array[i] < array[i + 1]) {
					continue;

				} else {
					temp = false;
					break;
				}
			}
			if (temp) {
				count++;
			}

			return;
		}
		// 把子数组的第一个元素依次和第二个、第三个元素交换位置
		for (int i = begin; i <= end; i++) {
			swap(array, begin, i);
			allSort(array, begin + 1, end);
			// 交换回来
			swap(array, begin, i);
		}
	}

	public void swap(int[] array, int a, int b) {
		int tem = array[a];
		array[a] = array[b];
		array[b] = tem;
	}

	/**
	 * Input: "DID" Output: 5 Explanation: The 5 valid permutations of (0, 1, 2, 3)
	 * are: (1, 0, 3, 2) (2, 0, 3, 1) (2, 1, 3, 0) (3, 0, 2, 1) (3, 1, 2, 0)
	 * 
	 * @param S
	 * @return
	 */
	public int numPermsDISequenceByMe(String s) {
		int count = 0;
		if (s == null || s.length() == 0 || "".equals(s)) {
			return count;
		}

		int[] p = new int[s.length() + 1];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
		System.out.println(Arrays.toString(p));

		List<int[]> lists = new ArrayList<>();
		// 第一次遍历去所有的第一个数
		for (int i = 0; i < p.length; i++) {
			// 如果第一个需要递减，而且第一个是0，则跳过
			if (i == 0 && s.charAt(i) == 'D') {
				continue;
			}

			// 存储字符，使用hash的思想判断数组中的字符是否出现过
			int[] arr = new int[p.length];
			arr[p[i]] = 1;

			// 存储结果
			int[] result = new int[p.length];
			result[0] = p[i];

			// 遍历字符串的顺序，减增减
			for (int j = 0; j < s.length(); j++) {

				if (s.charAt(j) == 'D') {

					// 说明在范围内，而且没有值
					for (int k = 1; k < s.length(); k++) {
						if (result[j] - k >= 0 && result[j] - k <= s.length() && arr[result[j] - k] == 0) {
							result[j + 1] = result[j] - k;
							continue;
						}
					}

				} else { // I

					for (int k = 1; k < s.length(); k++) {
						if (result[j] + k >= 0 && result[j] + k <= s.length() && arr[result[j] + k] == 0) {
							result[j + 1] = result[j] + k;
							continue;
						}
					}

				}
			}

			lists.add(result);
		}

		for (int[] is : lists) {
			System.out.println(Arrays.toString(is));
		}

		return lists.size();
	}
}
