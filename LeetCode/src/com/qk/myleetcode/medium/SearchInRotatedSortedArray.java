package com.qk.myleetcode.medium;

import org.junit.Test;

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
