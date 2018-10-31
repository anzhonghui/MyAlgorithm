package com.qk.myleetcode.range_1_50.easy;

import org.junit.Test;

/**
 * 
 * 7.Reverse Integer
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
 * @Description : 数字的倒置
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class ReverseInteger {

	@Test
	public void MyTest() {
		System.out.println(reverse(123));
		System.out.println(reverse(-123));
		System.out.println(reverse(120));
		System.out.println(reverse(0));
		System.out.println(reverse(1534236469));
		System.out.println(reverse(-2147483648));
	}

	/**
	 * 数学问题，先对10取余，得前面的数字；然后对10取整，获得接下来要操作的数字
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		StringBuilder sBuilder = new StringBuilder();
		int symbol = 1;
		if (x < 0) {
			symbol = -1;
			x = -x;
		}
		System.out.println(x);
		do {
			sBuilder.append(x % 10);
			x = x / 10;
		} while (x != 0);

		long result = Long.valueOf(sBuilder.toString()) * symbol;
		if (result > Integer.MAX_VALUE) {
			return 0;
		}
		if (result < Integer.MIN_VALUE) {
			return 0;
		}

		return (int) result;
	}

	public int reverse1(int x) {
		StringBuilder sBuilder = new StringBuilder();
		if (x > 0) {
			do {
				sBuilder.append(x % 10);
				x = x / 10;
			} while (x != 0);
		} else {
			sBuilder.append("-");
			do {
				sBuilder.append(-x % 10);
				x = x / 10;
			} while (x != 0);
		}

		int result = 0;
		try {
			result = Integer.valueOf(sBuilder.toString());
		} catch (Exception e) {
		}

		return result;
	}
}
