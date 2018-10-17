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

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		return helper(lists, 0, lists.length - 1);
	}

	/**
	 * 通过嵌套递归，一次比对两个节点，比对完成的两个节点作为一个新节点，就行与其他节点进行对比
	 * L1:
	 * ListNode [val=1, next=ListNode [val=4, next=ListNode [val=5, next=null]]]
	 * L2:
	 * ListNode [val=1, next=ListNode [val=3, next=ListNode [val=4, next=null]]]
	 * L1:(为上面两个节点比较完成后合并的节点)
	 * ListNode [val=1, next=ListNode [val=1, next=ListNode [val=3, next=ListNode [val=4, next=ListNode [val=4, next=ListNode [val=5, next=null]]]]]]
	 * L2:
	 * ListNode [val=2, next=ListNode [val=6, next=null]]	
	 * L1:(上面两个节点合并后的节点)
	 * ListNode [val=1, next=ListNode [val=1, next=ListNode [val=2, next=ListNode [val=3, next=ListNode [val=4, next=ListNode [val=4, next=ListNode [val=5, next=ListNode [val=6, next=null]]]]]]]]
	 * @param lists
	 * @param start
	 * @param end
	 * @return
	 */
	private ListNode helper(ListNode[] lists, int start, int end) {
		if (start > end)
			return null;
		if (start == end)
			return lists[start];
		int mid = (start + end) / 2;
		// 嵌套递归
		return merge(helper(lists, start, mid), helper(lists, mid + 1, end));
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				l1 = l1.next;
				cur = cur.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
				cur = cur.next;
			}
		}
		if (l1 == null)
			cur.next = l2;
		if (l2 == null)
			cur.next = l1;
		return dummy.next;
	}

	/**
	 * Input:
	 * [
	 *   1->4->5,
	 *   1->3->4,
	 *   2->6
	 * ]
	 * Output: 1->1->2->3->4->4->5->6
	 * @description 采用优先队列的方式，比较简单，容易理解
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsByPriorityQueue(ListNode[] lists) {
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
				queue.add(node);// 往优先队列中添加节点
		System.out.println(queue.toString());

		// 跳出条件，队列为空
		while (!queue.isEmpty()) {
			tail.next = queue.poll();// 弹出第一个节点值最小的几点
			tail = tail.next; // 将节点接到结果集中

			if (tail.next != null)
				queue.add(tail.next); // 如果后续还有节点，将后续的节点继续放入优先队列中
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
