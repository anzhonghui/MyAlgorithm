package com.qk.myleetcode.range_1_50.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
/**
 * 
 * 40.Combination Sum II
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
 * @Description : 找出给定数组中所有能够达到目标元素的解（元素不可重复）
 * @Programme：
 * 
 * 递归：
 * 1.基线条件：当目标元素的值被减到0，添加结果，跳出递归；循环条件即没有被减到0
 * 2.循环数组中的元素
 * 3.判断重复的情况
 * 4.目标元素减去当前的数组元素
 *  4.1 >=0
 *  	4.1.1 将当前元素添加到list中
 *  	4.1.2 递归（因为不可以重复，所以起始坐标+1，target减掉当前元素）
 *  	4.1.3 移除list中最后一个元素
 *  4.2 <0，跳出循环
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class CombinationSumII {

	@Test
	public void MyTest() {
		System.out.println(combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		search(result, new ArrayList<Integer>(), candidates, target, 0);
		return result;
	}

	/**
	 * 跟前一个方法基本相同，知识start的值不是i，而是i+1，因为每个数字只能用一次；另外需要处理重复的情况
	 * @param result
	 * @param temp
	 * @param nums
	 * @param target
	 * @param start
	 */
	public void search(List<List<Integer>> result, List<Integer> temp, int[] nums, int target, int start) {
		if (target == 0) {
			result.add(new ArrayList<>(temp));
			return;
		}

		// i=1（循环后+1的值）,start=0(第一次递归的值)
		for (int i = start; i < nums.length; i++) {
			// 处理重复的情况
			if(i > start && nums[i] == nums[i-1]) {
				continue;
			}
			if(target - nums[i] >= 0) {
				temp.add(nums[i]);
				search(result, temp, nums, target - nums[i], i + 1);
				temp.remove(temp.size() - 1);
			}else {
				break;
			}
		}
	}
}
