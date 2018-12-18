package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

/**
 * @Description : 81. Search in Rotated Sorted Array II
 * 找出旋转排序数组（排好序的数组【包含重复元素】，按照某一个指定的值旋转，例如：[0,0,1,2,2,5,6] -> [2,5,6,0,0,1,2]）中是否包含target元素
 * @Author : huihui
 * @Date : Create in 2018年12月17日
 */
public class SearchInRotatedSortedArrayII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {

	}

	/**
	 * 使用改进的二分法
	 * @param nums
	 * @param target
	 * @return
	 */
	public boolean search(int[] nums, int target) {
		int start = 0, end = nums.length - 1, mid = -1;
		while (start <= end) {
			mid = (start + end) / 2;
			if (nums[mid] == target) {
				return true;
			}
			// If we know for sure right side is sorted or left side is unsorted
			// 如果我们确定右侧是排好序的，左侧是未排好序的（分析：1.mid<end为T 2. mid < start 为T，通过多种尝试发现，当他满足时，也能得出右侧是排好序的结论）
			if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
				// target值在右侧排好序的数组中
				if (target > nums[mid] && target <= nums[end]) {
					start = mid + 1;
					// 在左侧
				} else {
					end = mid - 1;
				}
				// If we know for sure left side is sorted or right side is unsorted
				// 如果我们确定左侧是排好序的，右侧是未排好序的
			} else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
				// target值在左侧排好序的数组中
				if (target < nums[mid] && target >= nums[start]) {
					end = mid - 1;
					// 在右侧
				} else {
					start = mid + 1;
				}
				// If we get here, that means nums[start] == nums[mid] == nums[end], then
				// shifting out any of the two sides won't change the result but can help remove
				// duplicate from consideration, here we just use end-- but left++ works too
				// 说明三个元素相等，那么移出两边中的任何一个都不会改变结果，但是可以帮助从考虑中移除重复项，这里我们只使用end——但是左++也可以
				// [2, 5, 2, 2, 2, 2], target = 5, nums[start] == nums[mid] == nums[end] = 2
			} else {
				end--;
			}
		}

		return false;
	}
}
