package com.qk.myleetcode.medium;

import org.junit.Test;

/**
 * 
 * 11. Container With Most Water
 * @Description : 计算撑最大的水量
 * @Author : huihui
 * @Date : Create in 2018年9月22日
 */
public class ContainerWithMostWater {

	@Test
	public void MyTest() {
//		System.out.println(maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
//		System.out.println(maxArea(new int[] { 1, 7, 5 }));
//		System.out.println(maxArea(new int[] { 1, 5, 7 }));
		System.out.println(maxArea(new int[] { 2, 3, 2 }));
	}

	/**
	 * 思路（参考）：通过移动坐标，剔除前后比较小的数值
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {

		int maxArea = 0;
		int leftIndex = 0;
		int rightIndex = height.length - 1;
		int high = 0;

		while (leftIndex < rightIndex) {
			// 两边最小的高度
			high =  Math.min(height[leftIndex], height[rightIndex]);
			int temp = high * Math.abs(rightIndex - leftIndex);
			if(temp > maxArea) {
				maxArea = temp;
			}
			
			// 从前往后,从后往前,剔除比自己小的
			// 从前往后找的时候,比较相等的时候从前往后移动,如果带上大于，则陷入死循环，因为我们要剔除的是小的
			// 跟high相等，说明自己是两边中较小的一遍，剔除
			if (height[leftIndex] == high) {
				while(height[leftIndex] <= high && leftIndex < rightIndex) {
					leftIndex ++;
				}
			}else {
				while(height[rightIndex] <= high && leftIndex < rightIndex) {
					rightIndex--;
				}
			}
		}

		return maxArea;
	}

	/**
	 * 思路：变量两边，找最大值，O(n^2)
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea1(int[] height) {

		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i; j < height.length; j++) {
				int temp = Math.min(height[i], height[j]) * Math.abs(i - j);
				if (temp > maxArea) {
					maxArea = temp;
				}
			}
		}

		return maxArea;
	}
}
