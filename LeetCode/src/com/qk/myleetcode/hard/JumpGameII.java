package com.qk.myleetcode.hard;

import org.junit.Test;

public class JumpGameII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(jump(new int[] { 2, 3, 1, 1, 4 }));
//		System.out.println(jump(new int[] { 2, 1, 2, 1, 4 }));
//		System.out.println(jump(new int[] { 2, 1, 2, 1, 4 }));
		System.out.println(jump(new int[] { 2, 1, 2, 1, 1, 2, 1, 2 }));
		
	}

	/**
	 * 思路：采用BFS的思想解决问题，广度优先搜索
	 * 
	 * @param nums
	 * @return
	 */
	public int jump(int[] nums) {
		int n = nums.length;
		if (n < 2)
			return 0;
		
		int level = 0,  // 跳跃次数
			currentMax = 0,  // 当前最大的跳跃数
			i = 0,  // 数组的索引
			nextMax = 0; // 下一次能够跳跃到的坐标

		// 跳出循环的条件（作用不大，因为限定的条件都为非0的正整数；也可改为true，去掉最后的return）
		while (currentMax - i + 1 > 0) { // nodes count of current level>0
			// 跳跃次数+1
			level++;
			for (; i <= currentMax; i++) { // traverse current level , and update the max reach of next level
				// 计算下一次的最大跳跃数（坐标 + 长度 = 总共能跳跃到的坐标） --- 核心步骤
				nextMax = Math.max(nextMax, nums[i] + i);
				// 如果下一次能够达到的最远跳跃数>=长度，返回结果
				if (nextMax >= n - 1)
					return level; // if last element is in level+1, then the min jump=level
			}
			currentMax = nextMax;
		}
		return 0;
	}
}
