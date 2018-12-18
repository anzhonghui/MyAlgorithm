package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题60：n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s，输入n，打印出s的所有可能的值出现的概率
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class RecursionAndCirculation_InterviewQuestions60 {

	/**
	 * @Description:
	 */
	@Test
	public void MyTest() {

	}
	
	int maxValue = 6;
	
	public void printProbability(int number) {
		if(number < 1) {
			return;
		}
		
		int maxSum = number * maxValue;
		int[] probabilities = new int[maxSum - number + 1];
		for (int i = number; i < maxSum; ++i) {
			probabilities[i - number] = 0;
		}
		
		
		
	}
	
	public void probbility() {
		
	}

}
