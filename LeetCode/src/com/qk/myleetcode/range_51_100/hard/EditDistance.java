package com.qk.myleetcode.range_51_100.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * Edit Distance
 *	 给定两个单词word1和word2，找到将word1转换为word2所需的最小操作数。
	您可以对一个词执行以下3个操作：
	插入字符
	删除字符
	替换字符
 * @Description : 
 * @Example：
 *  Input: word1 = "intention", word2 = "execution"
	Output: 5
	Explanation: 
	intention -> inention (remove 't')
	inention -> enention (replace 'i' with 'e')
	enention -> exention (replace 'n' with 'x')
	exention -> exection (replace 'n' with 'c')
	exection -> execution (insert 'u')
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class EditDistance {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(minDistance("horse", "ros"));
	}

	/*
	 * 函数定义如下：
	 * f（i，j）：=将单词1的前i个字符转换为单词2的前j个字符所需的最小成本（或步骤）
	 * 例1:word1[i]==word2[j]，即第j个字符匹配。
	 * f（i，j）=f（i-1，j-1）
	 * 案例2：单词1[i]！=word2[j]，则我们必须插入、删除或替换，以较便宜者为准。
	 * f（i，j）=1+最小f（i，j-1），f（i-1，j），f（i-1，j-1）
	 * f（i，j-1）表示插入操作
	 * f（i-1，j）表示删除操作
	 * F（I-1，J-1）表示更换操作
	 * 这里，我们考虑从word1到word2的任何操作。这意味着，当我们说插入操作时，我们在word1后面插入一个与word2的第j个字符匹配的新字符。所以，
	 * 现在必须将word1的i个字符与word2的j-1个字符匹配。其他两个操作也一样。
	 * 注意问题是对称的。一个方向的插入操作（即从word1到word2）与另一个方向的删除操作相同。所以，我们可以选择任何方向。
	 * 上述方程成为dp的递归定义。
	 * 基本情况：
	 * f（0，k）=f（k，0）=k
	 * 下面是这个循环关系的直接自下而上的翻译。只需使用实际代码处理基于0的索引：
	 * 
	 * 使用动态规划的思想解决,时间复杂度O(mn)
	 */
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int[][] cost = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++)
			cost[i][0] = i;
		toStringArr(cost);
		for (int i = 1; i <= n; i++)
			cost[0][i] = i;
		toStringArr(cost);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				toStringArr(cost);
				if (word1.charAt(i) == word2.charAt(j))
					cost[i + 1][j + 1] = cost[i][j];// 如果相等，取原来的值
				else {
					int a = cost[i][j];
					int b = cost[i][j + 1];
					int c = cost[i + 1][j];
					// 三个数中取最小的,最小的+1
					cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
					cost[i + 1][j + 1]++;
				}
			}
		}
		return cost[m][n];
	}
	
	public void toStringArr(int[][] arr) {
		for (int i = 0; i < arr.length; ++i) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println("---------------------");
	}
}
