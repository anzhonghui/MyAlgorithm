package com.qk.myleetcode.range_51_100.easy;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 88. Merge Sorted Array
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
 * @Description : 合并两个排好序的数组
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月22日
 */
public class MergeSortedArray {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		// 正常
		int[] arr1 = new int[] { 1, 2, 3, 0, 0, 0 };
		merge(arr1, 3, new int[] { 2, 5, 6 }, 3);
		System.out.println(Arrays.toString(arr1));
		int[] arr2 = new int[] { 1, 2, 7, 0, 0, 0 };
		merge(arr2, 3, new int[] { 2, 5, 6 }, 3);
		System.out.println(Arrays.toString(arr2));

		// 为空
		int[] arr3 = new int[] { 1 };
		merge(arr3, 1, new int[] {}, 0);
		System.out.println(Arrays.toString(arr3));

		int[] arr4 = new int[] { 0 };
		merge(arr4, 0, new int[] { 1 }, 1);
		System.out.println(Arrays.toString(arr4));
	}

	/**
	 * 更好的算法：通过反向法，从后往前排序，从而避免数组的移动
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int current = m + n - 1;
		m = m - 1;
		n = n - 1;

		while (m >= 0 && n >= 0) {
			if (nums1[m] >= nums2[n]) {
				nums1[current--] = nums1[m--];
			} else {
				nums1[current--] = nums2[n--];
			}
		}
		while (n >= 0) {
			nums1[current--] = nums2[n--];
		}
	}

	/**
	 * 自己的想法，通过正向的比对交换
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void mergeByMe(int[] nums1, int m, int[] nums2, int n) {

		int i = 0;
		int j = 0;
		while (i < (m + n)) {
			if (j < n && i < m) {
				// 大于，交换，并进行重排序
				if (nums1[i] > nums2[j]) {
					swapTwoArr(nums1, nums2, i, j);
					// 对新的num2进行排序
					for (int k = j + 1; k < n; ++k) {
						if (nums2[k] < nums2[k - 1]) {
							swap(nums2, k - 1, k);
						} else {
							break;
						}
					}

				}
			} else if (j < n) {
				nums1[i] = nums2[j];
				++j;
			}

			++i;
		}
	}

	public void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public void swapTwoArr(int[] arr1, int[] arr2, int index1, int index2) {
		int temp = arr1[index1];
		arr1[index1] = arr2[index2];
		arr2[index2] = temp;
	}
}
