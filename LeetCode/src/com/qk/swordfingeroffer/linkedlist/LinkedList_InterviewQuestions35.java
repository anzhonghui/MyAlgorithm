package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 
 * 面试题35：
 * 		1.复制复杂的链表：给定一个复杂链表，链表除了有指向下一个节点的指针外，还有一个可以指向任意节点的指针，克隆链表
.. * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions35 {

	/**
	 * @Description:
	 * @Programme：
	 * 1.方案1：使用hash表记录任意节点的指向
	 * 2.方案2：通过复杂的步骤
	 *  2.1 复制原链表的各个节点，并将节点连接的他们复制的节点的后面，例如：A->A`->B->B`....
	 *  2.2 遍历链表，新复制的节点链接sibling，例如：A`->C`;B`->E`
	 *  2.3 分离源节点和复制节点
	 */
	@Test
	public void MyTest() {

		ComplexListNode nodeA = new ComplexListNode("A");
		ComplexListNode nodeB = new ComplexListNode("B");
		ComplexListNode nodeC = new ComplexListNode("C");
		ComplexListNode nodeD = new ComplexListNode("D");
		ComplexListNode nodeE = new ComplexListNode("E");

		nodeA.next = nodeB;
		nodeB.next = nodeC;
		nodeC.next = nodeD;
		nodeD.next = nodeE;

		nodeA.sibling = nodeC;
		nodeB.sibling = nodeE;
		nodeD.sibling = nodeB;

		System.out.println(clone(nodeA));
	}

	public ComplexListNode clone(ComplexListNode head) {
		cloneNodes(head);
		System.out.println(head);
		connectSiblingNodes(head);
		System.out.println(head);
		return reConnectionNodes(head);
	}

	/**
	 * 3.将原链表和克隆后的链表分离开来
	 * @param head
	 * @return
	 */
	public ComplexListNode reConnectionNodes(ComplexListNode head) {
		ComplexListNode node = head;
		ComplexListNode cloneHead = null;
		ComplexListNode cloneNode = null;

		if (node != null) {
			cloneHead = cloneNode = node.next;
			node.next = cloneNode.next;
			node = node.next;
		}

		while (node != null) {
			cloneNode.next = node.next;
			cloneNode = cloneNode.next;
			node.next = cloneNode.next;
			node = node.next;
		}

		return cloneHead;
	}

	/**
	 * 2.将克隆的链表链接sibling
	 * @param head
	 */
	public void connectSiblingNodes(ComplexListNode head) {
		ComplexListNode node = head;
		while (node != null) {
			ComplexListNode clone = node.next;
			if (node.sibling != null) {
				// A`-> C -> C` => A` -> C`
				clone.sibling = node.sibling.next;
			}
			node = clone.next;
		}
	}

	/**
	 * 1.将克隆的每个节点的节点连接到他本身的后面
	 * 例如:A->A`->B->B`.....
	 * @param head
	 */
	public void cloneNodes(ComplexListNode head) {
		ComplexListNode node = head;
		while (node != null) {
			ComplexListNode clone = new ComplexListNode();
			clone.val = node.val;
			clone.next = node.next;
			clone.sibling = null;
			// 将A`连接到A的后面
			node.next = clone;
			// 继续下移
			node = clone.next;
		}
	}

}
