package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

/**
 * 
 * 53.Maximum Subarray
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
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class MaximumSubarray {

	/**
	 * @Description:
	 */
	@Test
	public void MyTest() {
		System.out.println(maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
	}

	/**
	 * 也是采用DP的思想，少了创建数组的过程
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int add = nums[0];
		int total = nums[0];
		for (int i = 1; i < nums.length; i++) {
			add = Math.max(nums[i], nums[i] + add);
			total = Math.max(add, total);
		}

		return total;
	}

	/**
	 * 采用dp的思想，一次叠加值，如果上一个值小于0，则重新拿当前元素作为起点
	 * nums：-2  1  -3  4  -1  2  1  -5  4
	 * dp:   -2  1  -2  4   3  5  6   1  5
	 * 时间复杂度：O(n)
	 * @param nums
	 * @return
	 */
	public int maxSubArrayDP(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];// dp[i] means the maximum subarray ending with A[i];
		dp[0] = nums[0];
		int max = dp[0];

		for (int i = 1; i < n; i++) {
			// 查看dp上一个数是否大于0
			dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

}
