package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.qk.myleetcode.common.Interval;

public class MergeIntervals {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		List<Interval> list = new ArrayList<>();
		// [1,3],[2,6],[8,10],[15,18]
		list.add(new Interval(1, 3));
		list.add(new Interval(2, 6));
		list.add(new Interval(8, 10));
		list.add(new Interval(15, 18));
		System.out.println(merge(list).toString());
	}

	public List<Interval> mergeBest(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) {
			return new ArrayList<>();
		}

		Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval interval1, Interval interval2) {
				if (interval1.start != interval2.start) {
					return interval1.start - interval2.start;
				} else {
					return interval1.end - interval2.end;
				}
			}
		});

		Interval pre = intervals.get(0);
		for (int i = 0; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (curr.start > pre.end) {
				res.add(pre);
				pre = curr;
			} else {
				Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
				pre = merged;
			}
		}

		res.add(pre);
		return res;
	}

	private class IntervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval a, Interval b) {
			return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		Collections.sort(intervals, new IntervalComparator());

		LinkedList<Interval> merged = new LinkedList<Interval>();
		for (Interval interval : intervals) {
			// 如果数组为空或前面的intervals的end值没有小于后面intervals的start值
			// if the list of merged intervals is empty or if the current
			// interval does not overlap with the previous, simply append it.
			if (merged.isEmpty() || merged.getLast().end < interval.start) {
				merged.add(interval);
			}
			// 其他情况，重合，取两个intervals中的最大值作为end
			// otherwise, there is overlap, so we merge the current and previous
			// intervals.
			else {
				merged.getLast().end = Math.max(merged.getLast().end, interval.end);
			}
		}

		return merged;
	}
}
