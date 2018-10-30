package com.qk.myleetcode.medium;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 16. 3Sum Closest
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
 * @Description : 从数组中找出最接近目标值的三个数值和
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class ThreeSumClosest {

	@Test
	public void MyTest() {
		System.out.println(threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
		System.out.println(threeSumClosest(new int[] { -1, -2, -3, 0, 1, 2, 3, 4 }, 0));
		System.out.println(threeSumClosest(new int[] { 0, 1, 2 }, 0));
		System.out.println(threeSumClosest(new int[] { 0, 2, 1, -3 }, 1));// -1
		System.out.println(threeSumClosest(new int[] { 1, 1, -1, -1, 3 }, -1));// 1
		System.out.println(threeSumClosest(new int[] { 1, 2, 4, 8, 16, 32, 64, 128 }, 82));// 1
	}

	/**
	 * 采用计算和的方式，通过计算和，计算差值，移动坐标
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		// 最关键的一步：排序
		Arrays.sort(nums);

		int result = 0;
		int minClose = Integer.MAX_VALUE;

		for (int i = 0; i < nums.length - 1; i++) {
			int start = i + 1;
			int end = nums.length - 1;

			while (start < end) {
				int sum = nums[i] + nums[start] + nums[end];

				// 计算差值
				int close = Math.abs(target - sum);
				// 比较最小差值，如果小于，重新赋值
				if (close < minClose) {
					minClose = close;
					result = sum;
				}

				// 如果和小于目标值+
				if (sum < target) {
					start++;

					// 如果和大于目标值-
				} else {
					end--;
				}
			}
		}

		return result;
	}

	/**
	 * 一开始考虑的方式，失败
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosestByFailure(int[] nums, int target) {
		// 先排序
		Arrays.sort(nums);
		int minClose = Integer.MAX_VALUE;
		int result = 0;

		// 遍历
		for (int i = 0; i < nums.length - 2; i++) {
			// 相差的数值(初始一个数组的最大值)
			int closeValue = Integer.MAX_VALUE;
			// 先计算除本身外，其他两个数应该需要达到的和（中间和）
			int partialSum = target - nums[i];
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
				int sum = nums[ptr1] + nums[ptr2];
				if (Math.abs(partialSum - sum) < closeValue) {
					if (minClose > Math.abs(partialSum - sum)) {
						result = nums[i] + nums[ptr1] + nums[ptr2];
						minClose = Math.abs(partialSum - sum);
					}

					System.out.println(nums[i] + "  ---  " + nums[ptr1] + "  ---  " + nums[ptr2]);
					closeValue = Math.abs(partialSum - sum);
//					// 如果第一个元素往后有相同的元素跳过
//					while (ptr1 < ptr2 && nums[ptr1] == nums[ptr1 + 1])
//						ptr1++;
//					// 如果第二个元素往前有相同的元素跳过
//					while (ptr1 < ptr2 && nums[ptr2] == nums[ptr2 - 1])
//						ptr2--;

					if (Math.abs(partialSum - (nums[ptr1 + 1] + nums[ptr2])) < closeValue) {
						ptr1++;
					} else if (Math.abs(partialSum - (nums[ptr1] + nums[ptr2 - 1])) < closeValue) {
						ptr2--;
					} else {
						ptr1++;
						ptr2--;
					}

				} else
					ptr2--;
			}
		}

		return result;
	}
}
