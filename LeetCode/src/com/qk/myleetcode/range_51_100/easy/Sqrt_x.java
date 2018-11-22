package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

/**
 * 
 * 69. Sqrt(x)
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
 * @Description : 求平方根
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月19日
 */
public class Sqrt_x {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(mySqrt(1));
		System.out.println(mySqrt(2));
		System.out.println(mySqrt(3));
		System.out.println(mySqrt(4));
		System.out.println(mySqrt(5));
		System.out.println(mySqrt(6));
		System.out.println(mySqrt(7));
		System.out.println(mySqrt(8));
		System.out.println(mySqrt(9));
		System.out.println(mySqrt(10));
		System.out.println(mySqrt(25));
		System.out.println(mySqrt(100));
		System.out.println(mySqrt(10000));
	}

	/**
	 * 二分法
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if (x == 0) return 0;
        int start = 1 , end = x;
        while (start < end) {
        	// 计算mid的方式（经过计算发现这两种方式就然结果相同，显得比价吊：）
        	// start + (end - start)/2 = start/2 + end /2 = (start + end)/2
//            int mid = start + (end - start)/2;
            int mid = (end + start)/2;
            // 小于 & 取整的情况 8->2
            if (mid <= x/mid && (mid + 1) > x/(mid + 1))  return mid;
            // 大于的情况
            else if ( mid > x/mid ) end = mid;
            // 小于的情况
            else start = mid + 1; 
        }
        return start;
	}
	
	/**
	 * 采用二分法
	 * @param x
	 * @return
	 */
	public int mySqrtByMeProblem(int x) {

		if (x == 0 || x == 1) {
			return x;
		}

		// 1.对x进行二分
		int low = 0;
		int high = x;
		int mid = (low + high) / 2;
		int lastResult = 0;
		int curSqrt = mid * mid;

		while (curSqrt != x) {

			// 2.对结果进行比较
			// 2.1. 如果等于，跳出
			if (curSqrt == x) {
				break;
			}

			// 2.2. 平方后的结果跟x比较，大于，继续二分
			if (curSqrt > x) {
				high = mid - 1;
				mid = (low + high) / 2;
				curSqrt = mid * mid;
				if (Math.abs(lastResult - mid) == 1 && lastResult != 0) {
					mid = lastResult;
					break;
				} else {
					lastResult = high + 1;
				}
			}

			// 2.3.小于比较的同时，比较上一次的值跟x的关系，已确定是否取整 8 -> 2
			if (curSqrt < x) {

				low = mid + 1;
				mid = (low + high) / 2;
				curSqrt = mid * mid;

				if (lastResult - mid == 1 && lastResult != 0) {
					break;
				} else {
					lastResult = low - 1;
				}
			}
		}

		return mid;
	}

}
