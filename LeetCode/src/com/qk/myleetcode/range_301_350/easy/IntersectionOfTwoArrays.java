package com.qk.myleetcode.range_301_350.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * 349.Intersection of Two Arrays    
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
 * @Description : 找两个数组的交集
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class IntersectionOfTwoArrays {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(Arrays.toString(intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));
	}

	/**
	 * 采用hash
	 * @param nums1
	 * @param nums2
	 * @return
	 * @TimeComplexity: O(n)
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> resultSet = new HashSet<>();

		// 1.遍历存入map集合
		for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}

		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) {
				resultSet.add(nums2[i]);
            }
		}

		int[] result = new int[resultSet.size()];
		int i = 0;
		for (Integer num : resultSet) {
			result[i++] = num;
		}
		// 2.返回结果
		return result;
	}

	/**
	 * 采用循环
	 * @param nums1
	 * @param nums2
	 * @return 
	 * @TimeComplexity: O(nlogn)
	 */
	public int[] intersectionByTwoPointers(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0;
		int j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				set.add(nums1[i]);
				i++;
				j++;
			}
		}
		int[] result = new int[set.size()];
		int k = 0;
		for (Integer num : set) {
			result[k++] = num;
		}
		return result;
	}
	
	/**
	 * 二分查找
	 * @param nums1
	 * @param nums2
	 * @return
	 * @TimeComplexity: O(nlogn)
	 */
	public int[] intersectionByBinarySearch(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
    
    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}
