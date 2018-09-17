package com.qk.myleetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
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
 * @Description : 
 *  Given an array of integers, return indices(索引) of the two numbers such that they add up to a specific target.
 *	You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年8月25日
 */
public class TwoNumSolution {
	public int[] twoSum(int[] nums, int target) {
		int[] indices = new int[2]; 
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i])) {
				indices[0] = map.get(nums[i]);
				indices[1] = i;
			}
			map.put(target - nums[i], i);
		}
		return indices;
	}
	
	@Test
	public void MyTest() {
		int[] arr = {1,1,2,3};
//		twoSum(arr, 3);
		System.out.println(Arrays.toString(twoSum(arr, 3)));
	}
}
