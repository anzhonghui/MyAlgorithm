package com.qk.myleetcode.range_1_50.medium;

import org.junit.Test;
/**
 * 
 * 19.Remove Nth Node From End Of List
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
 * @Description : 移除链表中指定的第n个节点
 * @Programme：错位移动，先移动fast链表n个节点，后同时移动fast和slow链表，跳出，slow链表得到指定的要剔除的节点，剔除
 * 可以将时间复杂度控制在O(n),n是链表的size
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class RemoveNthNodeFromEndOfList {

	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		System.out.println(removeNthFromEnd(listNode, 2));
	}

	/**
	 * 参考：错位移动，fast移动n，剩余的size - n为slow需要移动的距离，这样可以将时间复杂度控制在O(n)
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode fast = head;
		ListNode slow = head;

		int temp = n;
		// fast从后往前移动
		while (temp > 0 && fast != null) {
			fast = fast.next;
			temp--;
		}

		// 说明指定的n已经超出链表的长度
		if (fast == null) {
			return head.next;
		}

		// slow从前往后移动
		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		// 移除节点
		slow.next = slow.next.next;

		return head;
	}

	/**
	 * 自己想的：通过先计算节点的长度，然后移动
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEndByMe(ListNode head, int n) {

		if (head.next == null) {
			return null;
		}

		int size = 0;

		ListNode result = head;
		ListNode temp = head;

		while (head != null) {
			size++;
			head = head.next;
		}

		if (size == n) {
			return temp.next;
		}

		for (int i = 0; i <= size - n - 1; i++) {
			if (i == (size - n - 1)) {
				temp.next = temp.next.next;
				break;
			}
			temp = temp.next;
		}

		System.out.println(result.toString());

		return result;
	}

}
