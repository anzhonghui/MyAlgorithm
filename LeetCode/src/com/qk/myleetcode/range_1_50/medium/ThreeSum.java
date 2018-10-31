package com.qk.myleetcode.range_1_50.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * 15.3Sum
 * @Description : 求给定数组中三个数字的和是0的所有数组
 * @Author : huihui
 * @Date : Create in 2018年9月24日
 */
public class ThreeSum {

	@Test
	public void MyTest() {
		System.out.println(threeSum(new int[] { -1, 0, 1, 2, -1, -4 }).toString());
		System.out.println(threeSum(new int[] { 3, 0, -2, -1, 1, 2 }).toString());
		System.out.println(threeSum(new int[] { -4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6 }).toString());
	}
	
	/**
	 * 思路：通过先排序的方式来避免后来的多差遍历，然后通过计算另外两个数字的和来找那两个元素的位置，这样可以省掉遍历另一半元素的时间
	 * 0.先排序
	 * 1.对数组进行一层遍历
	 * 2.计算前len-2个数的另外两数之和
	 * 3.从前取第一个元素的位置，从后取第二个元素的位置，剔除相同的元素
	 * 4.进入第二层循环，第一个元素坐标小于第二个元素坐标
	 * 5.三种情况
	 *   5.1 和为0，满足结果；相同的元素跳过，继续找
	 *   5.2 和小于，增大第一个元素的坐标，增小值
	 *   5.3 和大于，减小第二个元素的坐标，减大值
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		// 先排序
		Arrays.sort(nums);
		// 存储结果的集合
		List<List<Integer>> sol = new ArrayList<>();
		// 遍历
		for (int i = 0; i < nums.length - 2; i++) {
			// 先计算除本身外，其他两个数应该需要达到的和（中间和）
			int partialSum = 0 - nums[i];
			// 从前取第一个元素的位置
			int ptr1 = i + 1;
			// 从后取第二个元素的位置
			int ptr2 = nums.length - 1;
			// 遍历数据的时候如果两个元素相同直接跳过
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			// 第一个元素的位置小于第二个
			while (ptr1 < ptr2) {
				// 如果和0满足
				if (nums[ptr1] + nums[ptr2] == partialSum) {
					sol.add(Arrays.asList(nums[i], nums[ptr1], nums[ptr2]));
					// 如果第一个元素往后有相同的元素跳过
					while (ptr1 < ptr2 && nums[ptr1] == nums[ptr1 + 1])
						ptr1++;
					// 如果第二个元素往前有相同的元素跳过
					while (ptr1 < ptr2 && nums[ptr2] == nums[ptr2 - 1])
						ptr2--;
					ptr1++;
					ptr2--;
				
				// 如果小值+最大值还是小于中间和，递增小值
				} else if (nums[ptr1] + nums[ptr2] < partialSum)
					ptr1++;
				// 其他，递减大值
				else
					ptr2--;
			}

		}
		return sol;
	}

	public List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();

		// 先排序
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				break;

			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				if (nums[i] + nums[j] + nums[k] == 0) {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[i]);
					temp.add(nums[j]);
					temp.add(nums[k]);
					result.add(temp);
					while (j < k && nums[++j] == nums[j - 1])
						;
					while (j < k && nums[--k] == nums[k + 1])
						;
				} else if (nums[i] + nums[j] + nums[k] < 0) {
					while (j < k && nums[++j] == nums[j - 1])
						;
				} else {
					while (j < k && nums[--k] == nums[k + 1])
						;
				}
			}

		}

		return result;
	}

	/**
	 * 思路：最笨的方法，三层循环（时间超时）
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSumByMe(int[] nums) {

		List<List<Integer>> result = new ArrayList<>();
		Set<String> set = new HashSet<>();

		// 固定第一个数
		for (int i = 0; i < nums.length; i++) {
			// 固定第二个数
			for (int j = i + 1; j < nums.length; j++) {
				// 固定第三个数
				for (int k = j + 1; k < nums.length; k++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						int[] temp = new int[] { nums[i], nums[j], nums[k] };
						Arrays.sort(temp);
						if (set.add(temp[0] + "" + temp[1] + "" + temp[2])) {
							List<Integer> tempList = new ArrayList<>(3);
							tempList.add(nums[i]);
							tempList.add(nums[j]);
							tempList.add(nums[k]);
							result.add(tempList);
						}
					}
				}
			}
		}

		return result;
	}
}
