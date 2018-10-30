package com.qk.myleetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * 1.Two Sum
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
 * @Description : 
 *  Given an array of integers, return indices(索引) of the two numbers such that they add up to a specific target.
 *	You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * 给定一个数组，找出数组中两数和是指定target的唯一解（假定只有一组索引），返回解的索引值
 * 
 * @Programme：时间复杂度O(n)
 * 思路：使用散列表的思想，利用hashmap，key（两个数字索引靠后的数字）-value（两个数字索引靠前的数字的索引）
 * 例如：arr = { 1, 2, 3 }, target = 3; 1 ==> 2( key [] ) -> 2(value [ 3 - 1 ] )
 * 
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年8月25日
 */
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		int[] indices = new int[2];
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				indices[0] = map.get(nums[i]);
				indices[1] = i;
			}
			map.put(target - nums[i], i);
		}
		return indices;
	}

	@Test
	public void MyTest() {
		int[] arr = { 1, 1, 2, 3 };
//		twoSum(arr, 3);
		System.out.println(Arrays.toString(twoSum(arr, 3)));
	}
}
