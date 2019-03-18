package com.qk.myleetcode.range_101_150.easy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PascalTriangleII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(getRow(2).toString());
		System.out.println(getRow(3).toString());
	}

	public List<Integer> getRow(int rowIndex) {
		List<Integer> list = new ArrayList<Integer>();
		if (rowIndex < 0)
			return list;

		// 把内部元素先都设置成1，然后根据坐标修改一次修改元素值
		for (int i = 0; i < rowIndex + 1; i++) {
			list.add(0, 1);
			for (int j = 1; j < list.size() - 1; j++) {
				list.set(j, list.get(j) + list.get(j + 1));
			}
		}
		return list;
	}

}
