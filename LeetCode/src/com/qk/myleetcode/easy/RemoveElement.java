package com.qk.myleetcode.easy;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 27. Remove Element
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
 * @Description : 移除指定的目标元素，返回新数组的长度
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年9月18日
 */
public class RemoveElement {

	@Test
	public void MyTest() {
		System.out.println(removeElement(new int[] { 3, 2, 2, 3 }, 2));
		System.out.println(removeElement(new int[] { 1 }, 2));
		System.out.println(removeElement(new int[] { 1 }, 1));
		System.out.println(removeElement(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2));
	}

	/**
	 * 思路：跟上一个题的思路差不多，采用两个坐标移动的方式
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {

		if (nums == null || nums.length == 0) {
			return 0;
		}

		int i = 0;
		int j = 0;
		while (j < nums.length) {
			if (nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
			j++;
		}
		System.out.println(Arrays.toString(nums));
		return i;
	}
}
