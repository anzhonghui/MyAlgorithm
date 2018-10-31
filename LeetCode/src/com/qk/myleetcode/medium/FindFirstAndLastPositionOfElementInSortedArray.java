package com.qk.myleetcode.medium;

import java.util.Arrays;

import org.junit.Test;
/**
 * 
 * 34.Find First And Last Position Of Element In Sorted Array
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
 * @Description : 查找目标元素在数组中第一次和最后一次出现的位置
 * @Programme：改进的二分法，通过查找target第一次出现的位置，和比target大的元素第一次出现的位置，
 * 实现了元素第一次出现位置和最后一次出现位置坐标的查找
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

	@Test
	public void MyTest() {
//		int[] arr = { 5, 7, 7, 8, 8, 10 };
//		System.out.println(Arrays.toString(searchRange(arr, 8)));
//		System.out.println(Arrays.toString(searchRange(arr, 6)));
//		int[] arr2 = { 2, 2 };
//		System.out.println(Arrays.toString(searchRange(arr2, 2)));
//		int[] arr3 = { 1, 2 };
//		System.out.println(Arrays.toString(searchRange(arr3, 2)));
//		int[] arr4 = { 2 };
//		System.out.println(Arrays.toString(searchRange(arr4, 2)));
//		int[] arr5 = { 3, 3, 3, 5 };
		int[] arr5 = { 1, 2, 3, 5 };
		System.out.println(Arrays.toString(searchRange(arr5, 3)));
	}
	
	public int[] searchRange(int[] nums, int target) {
		if (nums.length == 0)
			return new int[] { -1, -1 };

		// 获取target第一次出现的位置
		int first = getFirst(nums, target);
		// 获取比target大（大1是为了查找）的元素第一次出现的位置
		int last = getFirst(nums, target + 1);
		if (nums[first] != target)
			return new int[] { -1, -1 };

		last = nums[last] == target ? last : last - 1;

		return new int[] { first, last };
	}

	/**
	 * 变形的二分法查找某个数字出现的第一次，如果没有，返回的是比它大的元素第一次出现的位置
	 * @param nums
	 * @param target
	 * @return
	 */
	private int getFirst(int[] nums, int target) {
		int p = 0, q = nums.length - 1;
		while (p < q) {
			int mid = (p + q) / 2;
			// 小于目标元素，p+
			if (nums[mid] < target)
				p = mid + 1;
			// 大于等于目标元素，q-
			else
				q = mid;
		}
		return q;
	}

	/**
	 * 自己写的，没有解出来
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRangeByMe(int[] nums, int target) {
		int[] arr = new int[2];

		if (nums.length == 1 && nums[0] == target) {
			return arr;
		}

		arr[0] = -1;
		arr[1] = -1;

		int lo = 0, hi = nums.length - 1;

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] == target) {
				while (nums[mid] > nums[lo]) {
					lo++;
				}
				while (nums[mid] < nums[hi]) {
					hi--;
				}
				arr[0] = lo;
				arr[1] = hi;
				break;
			} else if (nums[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return arr;
	}

}
