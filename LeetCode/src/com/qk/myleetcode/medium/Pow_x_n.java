package com.qk.myleetcode.medium;

import org.junit.Test;

/**
 * 
 * 50.Pow(x, n)
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
 * @Description : 求x的n次方
 * @Programme：
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class Pow_x_n {

	@Test
	public void MyTest() {
		System.out.println(myPow(2, 3));
//		System.out.println(myPow(2, 10));
//		System.out.println(myPow(2.1, 3));
//		System.out.println(myPow(2, -2));
//		System.out.println(myPow(2, 1));
//		System.out.println(myPow(2, 0));
		// 8.84372 -5
//		System.out.println(myPow(8.84372, -5));
//		System.out.println(myPow(2.00000, -2147483648));
//		System.out.println(myPow(2.00000, -2));
//		System.out.println(Double.MAX_VALUE*Double.MAX_VALUE);
	}

	/**
	 * 采用二分的思想计算乘积
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if (x == 0)
			return 0;
		if (n == 0)
			return 1;
		if (n == 1)
			return x;

		if (n < 0) {
			// 先求出来前n个数的乘积，在*最后一个,如果采用先乘积，最后除的话，会出来无穷问题
			return myPow(1 / x, (n + 1) * -1) * 1 / x;
		}

		// 递归二分
		double half = myPow(x, n / 2);

		// 处理奇数和偶数的情况
		return n % 2 == 0 ? half * half : half * half * x;
	}
	
	

	public double myPow3(double x, int n) {
		if (n == 0)
			return 1;
		if (n < 0) {
			n = -n;
			x = 1 / x;
		}
		return (n % 2 == 0) ? myPow3(x * x, n / 2) : x * myPow3(x * x, n / 2);
	}

	/**
	 * 采用二分法,没有处理最大值和最小值的情况
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow2(double x, int n) {
		if (n == 0) {
			return 1;
		}

		int pn = 1;
		if (n < 0) {
			n = -n;
			pn = -1;
		}

		return (pn == 1 ? calculate(x, n) : 1.0 / calculate(x, n));
	}

	public double calculate(double x, int n) {
		double result = 1;
		// 基线条件
		if (n / 2 == 1) {
			return result * x * x * (n % 2 == 1 ? x : 1);

		} else {
			// 循环条件
			return Math.pow(myPow2(x, n / 2), 2) * (n % 2 == 1 ? x : 1);
		}
	}

	/**
	 * 直接采用循环的方式，超时
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow1(double x, int n) {
		double result = 1;
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				result *= x;
			}
		} else {
			for (int i = n; i < 0; i++) {
				result *= 1.0 / x;
			}
		}

		return result;
	}
}
