package com.qk.myleetcode.range_51_100.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * 找柱状图中最大的矩形面积
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class LargestRectangleinHistogram {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }));
	}

	public int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		// 以i为当前柱子的坐标，数组存储从当前位置往左数比当前柱子低的第一个坐标
		int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
		// 以i为当前柱子的坐标，数组存储从当前位置往右数比当前柱子低的第一个坐标
		int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
		// 6表示没有
		lessFromRight[height.length - 1] = height.length;
		// -1表示没有
		lessFromLeft[0] = -1;

		for (int i = 1; i < height.length; i++) {
			// p比对的是i左边的值，如果小于，则满足，大于；取lessFromLeft的上一个值继续比较
			int p = i - 1;
			// 如果p位置的值大于i位置的值，更新p的值，
			while (p >= 0 && height[p] >= height[i]) {
				p = lessFromLeft[p];
			}
			lessFromLeft[i] = p;
		}

		for (int i = height.length - 2; i >= 0; i--) {
			int p = i + 1;
			while (p < height.length && height[p] >= height[i]) {
				p = lessFromRight[p];
			}
			lessFromRight[i] = p;
		}
		
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			// S = h * (i - j - 1),i是lessFromRight的值【区分for循环的i】，j是lessFromLeft的值
			maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
		}

		return maxArea;
	}
}
