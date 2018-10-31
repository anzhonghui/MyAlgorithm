package com.qk.myleetcode.range_1_50.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 43. Multiply Strings
 * 
 * @Description : 字符串中的数值相乘，不能使用BigInteger，也就是说不能先将字符串转整形，然后计算乘法
 * @Programme：从后往前遍历计算，主要需要处理当前位数和进位数的控制
 * 例如：
 *       1 2 3
 *         4 5
 * ——————————————
 *         1 5
 *       1 0
 *     0 5
 * ——————————————
 *       1 2
 *     0 8
 *   0 4
 * ——————————————
 *   0 5 5 3 5
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月7日
 */
public class MultiplyStrings {

	@Test
	public void MyTest() {
//		multiply("123","45");
		System.out.println(multiply("123456789", "987654321"));
	}

	/**
	 * 以坐标的形式计算，`num1[i] * num2[j]` will be placed at indices（最终结果） `[i + j`, `i + j + 1]` 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];

		// 从后往前
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				// 进位数
				int p1 = i + j;
				// 当前位数的计算
				int p2 = i + j + 1;
				// 计算和
				int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				// 加上原先的进位数
				sum += pos[p2];
				// 进位
				pos[p1] += sum / 10;
				// 当前数
				pos[p2] = sum % 10;

			}
		}
		StringBuilder sb = new StringBuilder();
		for (int p : pos)
			if (!(sb.length() == 0 && p == 0))
				sb.append(p);
		return sb.length() == 0 ? "0" : sb.toString();
	}

	public String multiply2(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0"))
			return "0";

		int[] a1 = new int[num1.length()];
		for (int i = num1.length() - 1; i >= 0; i--) {
			char c = num1.charAt(i);
			a1[num1.length() - 1 - i] = c - '0';
		}
		int[] a2 = new int[num2.length()];
		for (int i = num2.length() - 1; i >= 0; i--) {
			char c = num2.charAt(i);
			a2[num2.length() - 1 - i] = c - '0';
		}

		int[] result = new int[num1.length() + num2.length()];

		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a2.length; j++) {
				result[i + j] += a1[i] * a2[j];
			}
		}
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		for (int n : result) {
			int x = n + carry;
			sb.append(x % 10);
			carry = x / 10;
		}
		if (carry > 0)
			sb.append(carry);
		sb.reverse();
		while (sb.length() > 1 && sb.charAt(0) == '0')
			sb.deleteCharAt(0);
		return sb.toString();
	}

	/**
	 * 自己写的，循环次数较多
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiplyByMe(String num1, String num2) {

		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}

		List<String> lists = new ArrayList<>();
		StringBuilder sBuilder = new StringBuilder();
		int zeroSize = 0;
		for (int i = num2.length() - 1; i >= 0; i--) {
			for (int j = num1.length() - 1; j >= 0; j--) {
				sBuilder.append(((num2.charAt(i) - '0') * (num1.charAt(j) - '0')) + "");
				for (int k = 0; k < zeroSize; k++) {
					sBuilder.append('0');
				}
				lists.add(sBuilder.reverse().toString());
				sBuilder.setLength(0);
				zeroSize++;
			}
			zeroSize -= (num1.length() - 1);
		}

		int jin = 0;
		for (int i = 0; i < lists.get(lists.size() - 1).length(); i++) {
			int sum = jin;
			for (String string : lists) {
				if (string.length() > i) {
					sum += (string.charAt(i) - '0');
				}
			}

			jin = sum / 10;
			sBuilder.append(sum % 10);
		}

		if (jin != 0) {
			sBuilder.append(jin);
		}

		return sBuilder.reverse().toString();
	}
}
