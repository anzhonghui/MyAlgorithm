package com.qk.myleetcode.medium;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * 8.String To Integer
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
 * @Description : 字符串转整形，遍历位数相加（注意处理边界问题）
 * 常用的字符转数字的做法（重要）：int a = s.charAt(i) - '0'
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class StringToInteger {

	@Test
	public void MyTest() {
		System.out.println(myAtoi("0-1"));
//		System.out.println(myAtoi("words and 987"));
//		System.out.println(myAtoi("42"));
//		System.out.println(myAtoi("-abc"));
//		System.out.println(myAtoi("-91283472332"));
		System.out.println(myAtoi("   -42"));
//		System.out.println(myAtoi("42"));
//		System.out.println(myAtoi("+"));
//		System.out.println(myAtoi("+"));
//		System.out.println(myAtoi("-"));
		System.out.println(myAtoi("-+1"));
		System.out.println(myAtoi("+-1"));
	}
	
	public int myAtoi(String str) {
		str = str.trim();
		if (str == null || str.length() < 1)
			return 0;
		// trim white spaces 记录正负的flag
		char flag = '+';
		// check negative or positive 检查是正数还是负数
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// use double to store result 使用double类型，存储结果
		double result = 0;
		// calculate value 计算值，剔除符号，计算每一位
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
		if (flag == '-')
			result = -result;
		// handle max and min 处理是边界值
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int) result;
	}
	
	public int myAtoi1(String str) {
		int index = 0; // 字符串的索引
		int total = 0; // 总数
		int sign = 1;  // 正负标识

		// Check if empty string
		if (str.length() == 0)
			return 0;

		// remove white spaces from the string
		while (index < str.length() && str.charAt(index) == ' ')
			index++;
		
		// if is ' ' string
		if (index == str.length())
			return 0;

		// get the sign
		if (str.charAt(index) == '+' || str.charAt(index) == '-') {
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}

		// convert to the actual number and make sure it's not overflow
		while (index < str.length()) {
			// convert char to int 
			int digit = str.charAt(index) - '0';
			// Non-digital break
			if (digit < 0 || digit > 9)
				break;

			// check for overflow (= > <)
			if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

			// calculate total 
			total = total * 10 + digit;
			index++; // don't forget to increment the counter
		}
		return total * sign;
	}

	public int myAtoiByMe(String str) {
		str = str.trim();
		if (str == null || str.length() == 0 || "".equals(str)
				|| (str.length() == 1 && (str.charAt(0) == 45 || str.charAt(0) == 43))
				|| (!(str.charAt(0) == 45 || str.charAt(0) == 43 || (str.charAt(0) >= 48 && str.charAt(0) <= 57)))) {
			return 0;
		}
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
				sBuilder.append(str.charAt(i));

			} else if ((str.charAt(i) == 43 || str.charAt(i) == 45) && str.charAt(i + 1) >= 48
					&& str.charAt(i + 1) <= 57) {
				sBuilder.append(str.charAt(i));

			} else {
				break;
			}
		}

		String string = sBuilder.toString();
		if ("".equals(string)) {
			return 0;
		}

		double result = Double.valueOf(string);
		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		if (result < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) result;
	}

	public int myAtoi3(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		str = str.trim(); // kill add white spaces
		int i = 0; // index of str
		char flag = '+'; // default positive
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		double res = 0;
		// abandon the non-digit char; calculate the result
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			res = res * 10 + str.charAt(i) - '0';
			i++;
		}
		if (flag == '-')
			res = -1 * res;
		if (res > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (res < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return (int) res;
	}

	public int myAtoi2(String str) {

		if (str == null || str.length() == 0 || "".equals(str)) {
			return 0;
		}
		str = str.trim();
		if (!(str.charAt(0) >= 48 && str.charAt(0) <= 57) && !(str.charAt(0) == 45)) {
			return 0;
		}
		if (!(str.charAt(1) >= 48 && str.charAt(1) <= 57 && str.charAt(0) == 45)) {
			return 0;
		}
		String regEx = "[^0-9-]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String trim = m.replaceAll("").trim();

		double result = Double.valueOf(trim);
		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		if (result < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) result;
	}
	
}
