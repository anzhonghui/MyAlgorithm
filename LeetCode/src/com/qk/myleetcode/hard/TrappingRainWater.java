package com.qk.myleetcode.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * Trapping Rain Water    
 * code is far away from bug with the animal protecting
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
 * @Description : Trapping Rain Water    
 * 
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月25日
 */
public class TrappingRainWater {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(trap1(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
//		System.out.println(trap(new int[] { 4, 2, 3 }));
//		System.out.println(trap(new int[] { 9, 1, 0 }));
//		System.out.println(trap(new int[] { 0, 2, 0 }));
//		System.out.println(trap(new int[] { 1 }));
//		System.out.println(trap(new int[] { 1, 1 }));
//		System.out.println(trap(new int[] { 1, 0, 1 }));
	}

	/**
	 * best更好的方法，采用数学的思想，时间复杂度为O(3n)
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
		if (height.length == 0) {
			return 0;
		}
		int leftMax[] = new int[height.length];
		int rightMax[] = new int[height.length];
		leftMax[0] = height[0];
		rightMax[height.length - 1] = height[height.length - 1];
		// 从左边找，能够盛水的最大高度
		for (int i = 1; i < height.length; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i]);
		}
		// 从右边找，能够盛水的最大高度
		for (int i = height.length - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i]);
		}
		// 叠加，找高度低的与原高度相减（既可以计算出盛水的区域，也可减掉杆的高度）
		int trappedWater = 0;
		for (int i = 0; i < height.length; i++) {
			trappedWater += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return trappedWater;
	}

	/**
	 * Here is my idea: instead of calculating area by height*width, we can think it in a cumulative way. In other words, sum water amount of each bin(width=1).
	 * Search from left to right and maintain a max height of left and right separately, which is like a one-side wall of partial container. 
	 * Fix the higher one and flow water from the lower part. For example, if current height of left is lower, we fill water in the left bin. 
	 * Until left meets right, we filled the whole container.
	 * @param height
	 * @return
	 */
	public int trap1(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int res = 0;
		int maxleft = 0, maxright = 0;
		while (left <= right) {
			if (height[left] <= height[right]) {
				if (height[left] >= maxleft)
					maxleft = height[left];
				else
					res += maxleft - height[left];
				left++;
			} else {
				if (height[right] >= maxright)
					maxright = height[right];
				else
					res += maxright - height[right];
				right--;
			}
		}
		return res;
	}

	/**
	 * 没有解决
	 * @param height
	 * @return
	 */
	public int trapByMe(int[] height) {

		if (height == null || height.length == 0) {
			return 0;
		}

		int result = 0;
		int max1 = height[0];
		int max2 = height[0];
		int start = 0;
		int end = 0;

		int i = 1;
		while (i < height.length) {
			if (height[i] >= max1 && max1 != 0) {
				max2 = height[i];
				end = i;
				result += (end - start - 1) * Math.min(max1, max2);
				for (int j = start + 1; j < end; j++) {
					result -= height[j];
				}

				start = end;
				max1 = max2;
			}

			if (i == height.length - 1 && start < height.length - 1) {
				while (height[i] <= height[i - 1] && i > 1) {
					i--;
				}
				end = i;
				max2 = height[i];

				result += (end - start - 1) * Math.min(max1, max2);
				for (int j = start + 1; j < end; j++) {
					result -= height[j];
				}
				break;
			}

			i++;
		}

		return result;
	}
}
