package com.qk.myleetcode.range_1_50.medium;

import org.junit.Test;
/**
 * 
 * 24.Swap Nodes In Pairs
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
 * @Description : 交换链表的每两个节点
 * @Programme：采用循环和递归的方式
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class SwapNodesInPairs {

	@Test
	public void MyTest() {
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		System.out.println(swapPairs(head));
		ListNode head2 = new ListNode(0);
		head2.next = new ListNode(1);
		head2.next.next = new ListNode(2);
		System.out.println(swapPairs(head2));
	}
	
	/**
	 * 采用循环的方式
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
		
		ListNode result = new ListNode(-1);
		ListNode temp = result;
		
		while(head != null && head.next != null) {
			// 交换
			ListNode right = head.next;
			head.next = right.next;
			right.next = head;
			// temp是交换后的链表
			temp.next = right;
			// 跳过交换完成的两个元素
			temp = temp.next.next;
			// 继续（因为head交换了，所以此处去next即可）
			head = head.next;
		}
		
		return result.next;
	}
	
	/**
	 * 尝试采用递归的方式：
	 * 拿 1->2->3->4 举例：
	 * 第一次递归：1和2交换，编程2->1，对2的next进行递归
	 * 第二次递归：3和4交换，没有后续数据，返回4->3
	 * 跳出递归：每两个一层一层的返回（有奇偶得区分），得到最终结果
	 * @param head
	 * @return
	 */
	public ListNode swapPairsByRecursive(ListNode head) {
		
		// 基线条件,偶数个（跳出递归的条件），其他：递归条件
		if(head == null) {
			return head;
		}
		
		// 基线条件，奇数个（跳出递归的条件），其他：递归条件
		if(head.next == null) {
			return head;
		}
		
		// 通过递归一直往后追加
		ListNode right = head.next;
		head.next = swapPairs(right.next);
		right.next = head;
		return right;
	}
	
	public ListNode swapPairs2(ListNode head) {

		ListNode result = head;
		ListNode temp = result;
		
		ListNode left = null;
		ListNode right = null;
		
		int i = 0;
		while(head != null) {
			i++;
			if(i % 2 == 0) {
				left.next = right.next;
				right.next = left;
				result = right;
				head = head.next.next;
				if(head == null) {
					break;
				}
				i++;
			}
			
			left = head;
			right = head.next;
			head = head.next;
		}
		
		System.out.println(result.toString());
		
		return result;
	}

	public ListNode swapPairs1(ListNode head) {

		ListNode result = null;
		
		ListNode left = null;
		ListNode right = null;
		
		int i = 0;
		while(head != null) {
			i++;
			if(i == 2) {
				result = head;
				result.next = left;
				result.next.next = right;
				head = head.next;
				if(right == null || right.next == null) {
					break;
				}
			}else {
				left = head;
				if(head.next != null) {
					right = head.next.next;
				}
				head = head.next;
				left.next = null;
			}
		}
		
		System.out.println(result.toString());
		
		return result;
	}
}
