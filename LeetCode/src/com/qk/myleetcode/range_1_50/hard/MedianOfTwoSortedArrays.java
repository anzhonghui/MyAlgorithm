package com.qk.myleetcode.range_1_50.hard;

import org.junit.Test;

/**
 * 
 * 4.Median Of Two Sorted Arrays
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
 * @Description : 通过活用二分法解决问题，注意处理奇数和偶数不同位数的中位数求法
 * 假如我有两个数组，长度分别为a，b，中位数的位置(a+b)/2；二分的计算，remLength=(a+b)/2,
 * 对数组1进行二分a/2,计算数组2的位置 (a+b)/2 - a/2 = b/2，从结果可以看出来对数组2也进行了二分
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class MedianOfTwoSortedArrays {
	
	@Test
	public void MyTes3() {
//		int[] a = {1,3};
//		int[] b = {2};
//		System.out.println(findMedianSortedArraysByMe(a, b));
//		int[] a1 = {1,3};
//		int[] b1 = {4};
//		System.out.println(findMedianSortedArraysByMe(a1, b1));
//		int[] a2 = {1,2,3};
//		int[] b2 = {4,5,6,7};
//		System.out.println(findMedianSortedArraysByMe(a2, b2));
//		int[] a3 = {1,2,3,4};
//		int[] b3 = {5,6,7,8};
//		System.out.println(findMedianSortedArraysByMe(a3, b3));
//		int[] a4 = {1,2,3,4};
//		int[] b4 = {5,6,7,8,9};
//		System.out.println(findMedianSortedArraysByMe(a4, b4));
//		System.out.println(findMedianSortedArraysByMe(new int[] { 1, 2, 4 }, new int[] { 3, 5, 6, 7 }));
		System.out.println(findMedianSortedArraysByMe(new int[] { 1, 2, 4, 5 }, new int[] { 3, 6, 7, 8, 9 }));
	}
	
	// 1.数组交换（短数组在前）
	// 2.计算长度等信息（两个数组的长度，总长度的中位数）
	// 3.进入循环（短数组的开始坐标小于终止坐标）
	// 4.循环里两个数组中位数坐标的计算
	// 5.对比的三种情况
	// 6.第三种情况，区分奇数长度和偶数长度中位数的计算
	public double findMedianSortedArraysByMe(int[] nums1, int[] nums2) {
		// 1.数组交换
		if (nums1.length > nums2.length) {
			return findMedianSortedArraysByMe(nums2, nums1);
		}

		// 2.计算长度等信息（两个数组的长度，总长度的中位数）
		int m = nums1.length, n = nums2.length, iMin = 0, iMax = m, remLength = (m + n + 1) / 2;

		// 3.进入循环（短数组的开始坐标小于终止坐标）
		while (iMin <= iMax) {
			// 4.循环里两个数组中位数坐标的计算
			int i = (iMin + iMax) / 2;
			int j = remLength - i;

			// 5.对比的三种情况(通过对短数组坐标的二分控制，也实现了长数组的二分)
			if (i < iMax && nums2[j - 1] > nums1[i]) { // 如果长数组比短数组大，二分；短数组起始坐标叠加中间坐标
				iMin = i + 1;
			} else if (i > iMin && nums1[i - 1] > nums2[j]) { // 如果短数组比短数组大，二分；短数组终点坐标叠减中间坐标-1
				iMax = i - 1;
			} else {
				// 6.第三种情况，区分奇数长度和偶数长度中位数的计算
				int medianLeft = 0;
				if (i == 0) { // 说明短数组中的数比长数组的都大
					medianLeft = nums2[j - 1];
				} else if (j == 0) { // 说明长数组中的数比短数组的都大
					medianLeft = nums1[i - 1];
				} else { // 左侧的数，取两个数较大的数
					medianLeft = Math.max(nums1[i - 1], nums2[j - 1]);
				}

				if ((m + n) % 2 != 0) {
					return medianLeft;
				}

				// 处理偶数的中位数的情况
				int medianRight = 0;
				if (i == m) { // 说明短数组中的数比长数组的都小
					medianRight = nums2[j];
				} else if (j == n) { // 说明长数组中的数比短数组的都小
					medianRight = nums1[i];
				} else { // 右侧的数，取两个数较小的数
					medianRight = Math.min(nums1[i], nums2[j]);
				}
				return (medianLeft + medianRight) / 2.0;
			}
		}

		return 0.0;
	}
	
	
	@Test
	public void MyTest() {
		int[] a = {1,2};
		int[] b = {-1,3};
		System.out.println(findMedianSortedArrays(a,b));
		System.out.println(findMedianSortedArraysByMe(a,b));
	}
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// 如果数组1的长度大于数组2的长度，iMax数组1和数组2交换
		if (nums1.length > nums2.length) {
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
		}

		int m = nums1.length;
		int n = nums2.length;

		int iMin = 0, iMax = nums1.length;
		int remLength = (m + n + 1) / 2;

		while (iMin <= iMax) {
			/**
			 ** 目的：保证每次计算的值，要不就是奇数长度的中间值，要不就是偶数长度右侧的值，也可以以左侧之为基准 （长度>=3）
			 ** 示例： a = {1,3,6} b = {2,4,5,4}
			 *  i = ( 0 + 3)/2 = 1  短数组的中位数的在1的位置
			 *  j = ( 3 + 4 + 1)/2 - 1 = 3 长数组的中位数右侧在3的位置
			 */
			// 计算的短数组的中间位置，一直移动的是短数组
			int i = (iMin + iMax) / 2;
			// 计算的长数组的中间大1位置（remLength 是两个数组长度的中间位置、remLength - i(端数组中间位置) 即长数组的中间位置）
			int j = remLength - i;

			// 如果长数组的大于短数组的，则继续移动短数组的起始坐标位置
			if (i < iMax && nums2[j - 1] > nums1[i]) {
				iMin = i + 1;
				
			// 如果短数组大于长数组的，则继续移动段数组的终点坐标位置
			} else if (i > iMin && nums1[i - 1] > nums2[j]) {
				iMax = i - 1;
				
			} else { // perfect condition
				// 计算左侧的中位数（如果是奇数则是中间的数，如果是偶数，则是左边的数）
				int maxLeft = 0;
				// i==0 说明短数组的中的数字比长数组的都大，那样中位数就在长数组中
				if (i == 0) {
					maxLeft = nums2[j - 1];
					System.out.println("maxLeft:" + maxLeft);
				}
				
				// j==0 说明长数组的中的数字比短数组的都大，那样中位数就在短数组中
				else if (j == 0)
					maxLeft = nums1[i - 1];
				// 其他情况计算两个数比较大的数
				else
					maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);

				// 如果两个数组的长度和是奇数，直接反馈
				if ((m + n) % 2 == 1)
					return maxLeft;

				// 计算长度为偶数右边的数字
				int minRight = 0;

				// i 的大小达到了自己短数组的长度，说明他的数比长数组的都小，说明中位数在长数组中
				if (i == m)
					minRight = nums2[j];
				
				// j 的大小达到了自己长数组的长度，说明他的数比短数组的都小，说明中位数在短数组中
				else if (j == n)
					minRight = nums1[i];
				
				// 其他情况计算两数字的最大值
				else
					minRight = Math.min(nums1[i], nums2[j]);
				
				// 返回两个数字的平均数
				return (maxLeft + minRight) / 2.0;

			}

		}
		return 0.0;
	}
}
