package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

/**
 * 
 * 70.ClimbingStairs
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
 * @Description : 爬楼梯问题
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月20日
 */
public class ClimbingStairs {

	/**
	 * @Description:
	 * 1. f(1) = 1 
	 * 2. f(2) = 2 
	 * 3. (f(1) + f(2)) = 1 + 2 = 3
	 * 4. (f(2) + f(3)) = 2 + 3 = 5
	 * 5. (f(3) + f(4)) = 5 + 3 = 8
	 */
	@Test
	public void MyTest() {
		System.out.println(climbStairs(2));
		System.out.println(climbStairs(3));
		System.out.println(climbStairs(5));
	}

	/**
	 * 1.递归调用
	 */
	int total = 0;

	public int climbStairs(int n) {
		if (n == 1 || n == 2)
			total = n;
		else
			total = climbStairs(n - 2) + climbStairs(n - 1);
		return total;
	}

	/**
	 * 2.动态规划法 (利用数组来存储)
	 * @param n
	 * @return
	 */
	public int climbStairs02(int n) {
		if (n == 0)
			return 1;
		int[] array = new int[n + 1];
		array[0] = 1;
		array[1] = 1;
		for (int i = 2; i <= n; i++) {
			array[i] = array[i - 1] + array[i - 2];
		}
		return array[n];
	}

	/**
	 * 状态压缩发
	 * @param n
	 * @return
	 */
	public int climbStairs03(int n) {
		int one = 0;
		int two = 1;
		while (n > 0) {
			two = one + two; // 记录f(n) ==> f(n) = f(n-2) + f(n-1)
			one = two - one; // 记录f(n-1) ==> f(n-1) = f(n) - f(n-2)
			n--;
		}
		return two;
	}

	/**
	 * 斐波那契数列的通项公式
	 * @param n
	 * @return
	 */
	public int climbStairsBest(int n) {
		if (n == 0)
			return 1;
		if (n == 1 || n == 2)
			return n;
		int result = (int) Math.floor(
				1 / Math.sqrt(5) * (Math.pow((1 + Math.sqrt(5)) / 2, n + 1) - Math.pow((1 - Math.sqrt(5)) / 2, n + 1)));
		return result;
	}

}
