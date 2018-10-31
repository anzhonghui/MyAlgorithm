package com.qk.myleetcode.range_1_50.easy;

import org.junit.Test;
/**
 * 	
 * 35.Search Insert Position
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
 * @Description : 查找目标元素应该在的位置
 * @Programme：使用二分法
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class SearchInsertPosition {

	@Test
	public void MyTest() {
		System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 5));
		System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 2));
		System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 7));
		System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 6));
		System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 1));
		System.out.println(searchInsert(new int[] { 1 }, 2));
		System.out.println(searchInsert(new int[] { 2 }, 1));
		System.out.println(searchInsert(new int[] { 1,3,5 }, 1));
	}

	/**
	 * 思路：使用二分查找的思想（另一种方法，挨个查找）
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		
		int left = 0;
		int right = nums.length - 1;
		int targetIndex = 0;
		
		if(left == right) {
			if(nums[0] >= target) {
				return 0;
			}else {
				return 1;
			}
		}

		while (left < right) {
			// 中间值
			int mul = (left + right) / 2;

			// 判断中间值跟目标值的关系（如果相等，说明那个是他的位置）
			if (nums[mul] < target) {
				left = mul;
			} else if (nums[mul] == target) {
				targetIndex = mul;
				break;
			} else {
				right = mul;
			}

			// 判断不等的情况，当二分到只有两个元素的时候
			if (right - left == 1) {
				// 当位于两个数的中间，反馈left+1
				if (nums[left] < target && nums[right] >= target) {
					targetIndex = left+1;
					
				// 当大于右边的数，right+1
				} else if(nums[right] <= target) {
					targetIndex = right + 1;
					
				// 当小于左边的数，left
				} else if(nums[left] >= target){
					targetIndex = left;
				}
				break;
			}
		}

		return targetIndex;
	}
	
}
