package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * 斐波那契数列
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月16日
 */
public class FibonacciSequence_InterviewQuestion10 {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(getNumByRecursion(0));
//		System.out.println(getNumByRecursion(1));
//		System.out.println(getNumByRecursion(2));
//		System.out.println(getNumByRecursion(3));
	}

	/**
	 * 通过递归的方法获取结果，效率低
	 * @return
	 */
	public int getNumByRecursion(int temp) {
		if (temp == 0) {
			return 0;
		}
		if (temp == 1) {
			return 1;
		}

		return getNumByRecursion(temp - 1) + getNumByRecursion(temp - 2);
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		System.out.println(getNumByLoop(0));
		System.out.println(getNumByLoop(1));
		System.out.println(getNumByLoop(2));
		System.out.println(getNumByLoop(3));
		System.out.println(getNumByLoop(4));
	}

	/**
	 * 将递归的方式改成循环
	 * @param temp
	 * @return
	 */
	public int getNumByLoop(int val) {
		if (val == 0) {
			return 0;
		}
		int first = 0;
		int second = 1;
		for (int i = 1; i < val; ++i) {
			int temp = second;
			second += first;
			first = temp;
		}

		return second;
	}
}
