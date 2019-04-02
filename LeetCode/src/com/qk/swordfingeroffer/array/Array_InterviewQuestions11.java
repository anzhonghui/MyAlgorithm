package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题11：旋转数组中最小的数字
 * 排好序的数组在某个值出发生旋转，找出最小的元素
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Array_InterviewQuestions11 {

	/**
	 * @Description:
	 */
	@Test
	public void MyTest() {
//		System.out.println(Min(new int[] { 3, 4, 5, 1, 2 }));
		System.out.println(Min(new int[] {1,0,1,1,1}));
	}

	/**
	 * 数组元素的重复次数少，少于4次
	 * @param arr
	 * @return
	 */
	public int Min(int[] arr) {
		int len = arr.length;
		if (arr == null || len == 0) {
			return 0;
		}
		int start = 0;
		int end = len - 1;
		int mid = start;
		while (arr[start] >= arr[end]) {
			// 跳出循环的条件
			if (end - start == 1) {
				mid = end;
				break;
			}

			mid = (start + end) / 2;
			if (arr[mid] >= arr[start]) {
				start = mid;
			} else if (arr[mid] <= arr[end]) {
				end = mid;
			}
		}

		return arr[mid];
	}

	/**
	 * 数组元素的重复次数多，大于等于4次
	 * @param arr
	 * @return
	 */
	public int Min2(int[] arr) {
		int len = arr.length;
		if (arr == null || len == 0) {
			return 0;
		}
		int start = 0;
		int end = len - 1;
		int mid = start;
		while (arr[start] >= arr[end]) {
			// 跳出循环的条件
			if (end - start == 1) {
				mid = end;
				break;
			}

			mid = (start + end) / 2;

			// 对原来方法进行改良，如果是哪个元素都相等，则从start到end顺序查找，降低时间复杂度
			if (arr[start] == arr[end] && arr[mid] == arr[start]) {
				return MinInOrder(arr, start, end);
			}

			if (arr[mid] >= arr[start]) {
				start = mid;
			} else if (arr[mid] <= arr[end]) {
				end = mid;
			}
		}

		return arr[mid];
	}

	private int MinInOrder(int[] arr, int start, int end) {
		int result = arr[start];
		for (int i = start + 1; i <= end; ++i) {
			if (result > arr[i]) {
				result = arr[i];
			}
		}
		return 0;
	}

}
