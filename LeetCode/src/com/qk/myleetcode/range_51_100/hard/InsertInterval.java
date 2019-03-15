package com.qk.myleetcode.range_51_100.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.qk.myleetcode.common.Interval;

/**
 * Insert Interval 插入间隔
 *  给定一组非重叠间隔，在间隔中插入新间隔（必要时合并）。
 * 您可以假设间隔最初是根据其开始时间排序的。
 * @Description : 
 * @Example：
 *  Example 1:
	Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
	Output: [[1,5],[6,9]]
	Example 2:
	Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
	Output: [[1,2],[3,10],[12,16]]
	Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class InsertInterval {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 3));
		intervals.add(new Interval(6, 9));
		System.out.println(insert(intervals, new Interval(2, 5)).toString());
	}
	
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new LinkedList<>();
		int i = 0;
		// add all the intervals ending before newInterval starts (如果新的间隔的起始值小于集合中interval的结束值，放入result集合中)
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			result.add(intervals.get(i++));
		// merge all overlapping intervals to one considering newInterval，合并，start取最小的，end取最大的
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
			newInterval = new Interval( // we could mutate newInterval here also
					Math.min(newInterval.start, intervals.get(i).start),
					Math.max(newInterval.end, intervals.get(i).end));
			i++;
		}
		result.add(newInterval); // add the union of intervals we got 添加新的结果级
		// add all the rest 剩余的间隔，直接放入
		while (i < intervals.size())
			result.add(intervals.get(i++));
		return result;
	}
}
