package com.qk.myleetcode.easy;

import org.junit.Test;

/**
 * 
 * 21.Merge Two Sorted Lists
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
 * @Description : 合并两个排好序的链表
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class MergeTwoSortedLists {

	@Test
	public void MyTest() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		mergeTwoLists(l1, l2);
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null && l2 == null) {
			return null;
		}

		if (l1 == null) {
			return l2;
		}

		if (l2 == null) {
			return l1;
		}

		ListNode head = null;
		// 确定头部
		if (l1.val < l2.val) {
			head = l1;
			l1 = l1.next;
		} else {
			head = l2;
			l2 = l2.next;
		}
		// 添加头部是为了操作temp的next时就像当与给head的next操作，而重新赋值temp，却不影响head
		ListNode temp = head;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				temp.next = l1;
				temp = temp.next;
				l1 = l1.next;

			} else {
				temp.next = l2;
				temp = temp.next;
				l2 = l2.next;
			}
		}

		if (l1 != null) {
			temp.next = l1;
		}
		if (l2 != null) {
			temp.next = l2;
		}

		return head;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}
}