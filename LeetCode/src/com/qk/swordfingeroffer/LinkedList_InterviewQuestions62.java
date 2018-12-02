package com.qk.swordfingeroffer;

import java.util.LinkedList;

import org.junit.Test;

/**
 * @Description : 
 * 面试题62：
 * 		1.圆圈中最后剩下的数字（）约瑟夫问题
.. * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions62 {

	/**
	 * @Description:从0....n-1这n个数字排成一个圆圈，从中移除第m个数字，求圆圈里最后剩下的一个数字
	 * 解决方案：1.使用环形链表
	 * 			2.使用递推公式
	 * 				f(n, m) = 0           (n = 1) 
	 *				f(n, m) = [f(n-1, m) +m] % n  (n > 1)
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		System.out.println(lastRemainingByFormula(11, 3));
	}

	/**
	 ** 综上，约瑟夫环的公式是： 
	 ** f(n, m) = 0           (n = 1) 
	 ** f(n, m) = [f(n-1, m) +m] % n  (n > 1)
	 ** f(n-1, m) 最终的结果
	 ** 由推到公式得 O(n)
	 */
	public int lastRemainingByFormula(int n, int m) {
		// 不合法输入的判断
		if (n == 0 || m < 1) {
			return -1;
		}
		int last = 0;// 上一个被剔除的数字
		for (int i = 2; i <= n; i++) { // 实际循环了n次
			last = (last + m) % i;
		}
		return last;
	}

	/**
	 * 通过链表解决
	 * @param n
	 * @param m
	 * @return
	 */
	public int lastRemainingByLinkList(int n, int m) {
		// 使用LinkedList模拟，排除一个删一个
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		int count = 0; // 计数
		// 遍历，直到链表的大小符合要求
		for (int index = 0; list.size() > 1; index++) {
			count++;
			index = index == list.size() ? 0 : index; // 重复设置索引的值，如果循环一遍，重新从 开始
			if (count % m == 0) {
				list.remove(index);
				index--;
			}
		}
		return list.get(0);
	}

}
