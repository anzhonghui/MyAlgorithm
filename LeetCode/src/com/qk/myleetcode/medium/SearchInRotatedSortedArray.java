package com.qk.myleetcode.medium;

import org.junit.Test;
/**
 * 
 * 33.Search In Rotated Sorted Array
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
 * @Description : 排好序的数组，找出目标元素的坐标（正常我们看到排好序，找元素，想到的都是二分法，但是这个数组有点特殊，原有的二分法也要改进）
 * @Programme：改进的二分法；明确三个关系：nums[0]、target、num[mid]
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class SearchInRotatedSortedArray {

	/**
	 * ^异或运算，相同取0，相异取1
	 * true^false^true 分开计算true^false = true^true = false
	 */
	@Test
	public void MyTest() {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(search(nums, 0));
	}

	/*	
	 * 数组分为两部分，A（较大的数组）B（较小的数组）
	 * 1.判断target在A，还是在B；
	 * 2.判断mid在A，还是在B；
	 * 3.判断target在mid的左侧还是右侧
	 * 
	 * nums[0]>target	nums[0]>num[mid]	target>nums[mid]	^的结果	可行性	Lo和hi的变动
	 * 		F					F					F				F				Hi
	 * 		F					F					T				T				lo 
	 * 		T					F					F				T				lo
	 * 		T					F					T				F	前两个条件的结果是：nums[mid]>=nums[0]>target,与第三个条件target>nums[mid]冲突，所以不可行	-
	 * 		T					T					T				T				lo
	 * 		F					T					T				F				hi
	 *	 	T					T					F				F				hi
	 * 		F					T					F				T	前两个条件的结果是：nums[mid]<nums[0]<=target，与第三个条件target<=nums[mid]冲突，不可行	-
	 */
	public int search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid]))
				lo = mid + 1;
			else
				hi = mid;
		}
		return lo == hi && nums[lo] == target ? lo : -1;
	}
	
	public int searchest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } 
            
            // two branches
            if (nums[mid] >= nums[0]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && target < nums[0]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        
        return -1;
    }

}
