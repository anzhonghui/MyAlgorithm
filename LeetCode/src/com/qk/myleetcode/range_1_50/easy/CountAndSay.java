package com.qk.myleetcode.range_1_50.easy;

import org.junit.Test;

/**
 * 
 * 38.Count And Say
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
 * @Description : 统计和计算作为下一次的数值
 * 1.     1
 * 2.     11 (统计上一个的1个1)
 * 3.     21 (统计上一个的2个1)
 * 4.     1211 (统计上一个的1个2,1个1)
 * 5.     111221 (统计上一个的1个1,1个2,2个1)
 * 
 * @Programme：通过嵌套循环实现，外层循环数字，内层遍历上一次的结果统计
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class CountAndSay {

	@Test
	public void MyTest() {
		System.out.println(countAndSay(1));
		System.out.println(countAndSay(2));
		System.out.println(countAndSay(3));
		System.out.println(countAndSay(4));
		System.out.println(countAndSay(5));
	}

	/**
	 * 思路一：采用循环的方式（原因：1.采用循环的方式效率比递归高 2.采用递归的方式并没有感觉便于理解）
	 * @param n
	 * @return
	 */
	public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		// 用来存放每次的结果
		String result = "1";
		// 用来拼接
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 1; i < n; i++) {
			// 统计数量
			int count = 0;
			// 获取首元素
			char s = result.charAt(0);
			// 遍历结果进行统计
			for (int j = 0; j < result.length(); j++) {
				// 如果碰到相同的+1
				if (result.charAt(j) == s) {
					count++;

					// 如果没有，重新初始化
				} else {
					sBuilder.append(count + "" + s);
					// 重新初始化
					s = result.charAt(j);
					count = 1;
				}
			}
			// 追加最后一组结果，因为已经跳出循环
			sBuilder.append(count + "" + s);
			result = sBuilder.toString();
			// builder重新初始化
			sBuilder.setLength(0);
		}

		return result;
	}

	private String result;

	/**
	 * 思路2：采用递归的方式
	 * @param n
	 * @return
	 */
	public String countAndSay2(int n) {
		if (n <= 0)
			return "";
		result = "";
		Helper("1", n);
		return result;
	}

	private void Helper(String curt, int n) {
		if (n == 1) {
			result = curt;
			return;
		}
		char[] sAry = curt.toCharArray();
		int count = 0;
		char ref = sAry[0];
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < sAry.length; i++) {
			if (ref == sAry[i])
				count++;
			else {
				result.append(count).append(ref);
				ref = sAry[i];
				count = 1;
			}

		}
		result.append(count).append(ref);
		Helper(result.toString(), n - 1);
	}

//	@Test
//	public void MyTest2() {
//		int a = 1;
//		char b = '1';
//		System.out.println(a+b);
//		System.out.println(a+""+b);
//		
//	}
}
