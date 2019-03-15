package com.qk.myleetcode.range_51_100.medium;

import com.qk.myleetcode.common.ListNode;

/**
 * 86. Partition List
 * @Description : 进行分区，小于x的节点都位于大于或等于x的节点之前
 * @Author : huihui
 * @Date : Create in 2018年12月23日
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionList {

	/**
	 * 基本思想是维护两个队列，第一个队列存储val小于x的所有节点，第二个队列存储所有剩余节点。
	 * 然后连接这两个队列。记住接下来将第二个队列的尾部设置为空，否则您将得到TLE。
	 * 过程：
	 * curr1 = 1 -> 2 -> 2
	 * curr2 = 4 -> 3 -> 5 -> 2(next)
	 * dummy1 = 0 -> curr1
	 * dummy2 = 0 -> curr2
	 * curr1 -> dummy2.next = curr2
	 * curr1 -> curr2
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0); // dummy heads of the 1st and 2nd queues
		ListNode curr1 = dummy1, curr2 = dummy2; // current tails of the two queues;
		while (head != null) {
			if (head.val < x) {
				curr1.next = head;
				curr1 = head;
			} else {
				curr2.next = head;
				curr2 = head;
			}
			head = head.next;
		}
		curr2.next = null; // important! avoid cycle in linked list. otherwise u will get TLE.
		curr1.next = dummy2.next;
		return dummy1.next;
	}
}
