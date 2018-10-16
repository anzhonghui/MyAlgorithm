package com.qk.myleetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

import com.qk.myleetcode.medium.ListNode;

public class MergeKSortedLists {

	@Test
	public void MyTest() {
		ListNode[] lists = new ListNode[3];
		ListNode list = new ListNode(1);
		list.next = new ListNode(4);
		list.next.next = new ListNode(5);
		lists[0] = list;
		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(3);
		list1.next.next = new ListNode(4);
		lists[1] = list1;
		ListNode list2 = new ListNode(2);
		list2.next = new ListNode(6);
		lists[2] = list2;
		ListNode mergeKLists = mergeKLists(lists);
		System.out.println(mergeKLists.toString());
	}

	/**
	 * Input:
	 * [
	 *   1->4->5,
	 *   1->3->4,
	 *   2->6
	 * ]
	 * Output: 1->1->2->3->4->4->5->6
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			// 自定义优先级,从低往高
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				else if (o1.val == o2.val)
					return 0;
				else
					return 1;
			}
		});

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);
		System.out.println(queue.toString());

		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

	@Test
	public void MyTest2() {
		List<Student> lists = new ArrayList<>();
		lists.add(new Student(95, "张三"));
		lists.add(new Student(89, "李四"));
		lists.add(new Student(67, "王五"));
		lists.add(new Student(92, "赵六"));
		PriorityQueue<Student> queue = new PriorityQueue<Student>(lists.size(), new Comparator<Student>() {
			// 自定义优先级
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.score < o2.score)
					return -1;
				else if (o1.score == o2.score)
					return 0;
				else
					return 1;
			}
		});
		for (Student student : lists) {
			queue.add(student);
		}
		while (!queue.isEmpty()) {
			System.out.println(queue.poll().toString());
		}
	}
}

class Student {
	int score;
	String name;

	public Student(int score, String name) {
		super();
		this.score = score;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [score=" + score + ", name=" + name + "]";
	}

}
