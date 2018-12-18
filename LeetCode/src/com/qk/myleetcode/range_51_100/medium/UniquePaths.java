package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;
/**
 * 
 * 62. Unique Paths
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
 * @Description : 唯一路径，找到从左上角到右下角的所有路径
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月6日
 */
public class UniquePaths {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest1() {
		System.out.println(uniquePathsUnoptimized(2, 2));
		System.out.println(uniquePathsUnoptimized(3, 2));
		System.out.println(uniquePathsUnoptimized(3, 3));
		System.out.println(uniquePathsUnoptimized(4, 3));
	}

	/**
	 * DP问题
	 * 1.没有优化的代码
	 * 使用二维数组解决DP问题
	 * 1 1 1
	 * 1 2 3
	 * 1 3 6
	 * p[1][0] + p[0][1] = p[1][1] = 2
	 * p[2][0] + p[1][1] = p[2][1] = 3
	 * p[0][2] + p[1][1] = p[1][2] = 3
	 * p[1][2] + p[2][1] = p[2][2] = 6
	 * 
	 * 时间复杂度:O(n^2)
	 * 控件复杂度:O(m*n)
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePathsUnoptimized(int m, int n) {
		int[][] path = new int[m][n];
		// P[0][j] = 1, P[i][0] = 1
		for (int i = 0; i < path[0].length; ++i) {
			path[0][i] = 1;
		}
		for (int i = 0; i < path.length; ++i) {
			path[i][0] = 1;
		}
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				path[i][j] = path[i - 1][j] + path[i][j - 1];
		return path[m - 1][n - 1];
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		System.out.println(uniquePathsOptimized(2, 2));
	}

	public int uniquePathsOptimized(int m, int n) {
		if (m > n)
			return uniquePathsOptimized(n, m);
		int[] pre = new int[m];
		int[] cur = new int[m];
		for (int i = 0; i < m; ++i) {
			pre[i] = 1;
			cur[i] = 1;
		}
		for (int j = 1; j < n; j++) {
			for (int i = 1; i < m; i++)
				cur[i] = cur[i - 1] + pre[i];
//	            swap(pre, cur);
		}
		return pre[m - 1];
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest3() {
		System.out.println(uniquePaths(2, 2));
		System.out.println(uniquePaths(3, 2));
		System.out.println(uniquePaths(3, 3));
		System.out.println(uniquePaths(4, 3));
	}

	/**
	 * 对空间的维护做了进一步优化
	 * 3*3
	 * 1 1 1
	 * 1 2 3
	 * 1 3 6
	 * 
	 * 清楚的可以看出过程跟二位数组的差不多
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		// 始终保持n最大
		if (m > n)
			return uniquePaths(n, m);
		// 定义数组
		int[] cur = new int[m];
		// 初始化所有的值为1
		for (int i = 0; i < m; ++i) {
			cur[i] = 1;
		}
		// 
		for (int j = 1; j < n; j++)
			for (int i = 1; i < m; i++)
				cur[i] += cur[i - 1];
		return cur[m - 1];
	}
}
