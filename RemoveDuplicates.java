package linklist;

import java.util.Arrays;
import java.util.LinkedList;

class Node {
	Node next = null;
	int data;

	public Node(int d) {
		data = d;
	}

	void appendToTail(int d) {
		Node end = new Node(d);
		Node n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}

	Node deleteNode(Node head, int d) {
		Node n = head;
		if (n.data == d) {
			return head.next; /* moved head */
		}
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
				return head; /* head didn’t change */
			}
			n = n.next;
		}
		return head;
	}
}

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n = new Node(10);
		n.appendToTail(3);
		n.appendToTail(10);
		n.appendToTail(1);
		n.appendToTail(3);
		Node runner = n;
		Node runner1 = n;
		while (n.next != null) {
			runner=runner1;
			n = n.next;
			while (runner != n) {
				if (n.data == runner.data) {
					n=n.deleteNode(n, n.data);
				}
				runner = runner.next;
			}
			
		}
		while (n.next != null) {
		System.out.println("t"+n.data);
		}
	}

}
