package linklist;

public class NodeL {
		public NodeL next = null;
		public int data;

		public NodeL(int d) {
			data = d;
		}

		void appendToTail(int d) {
			NodeL end = new NodeL(d);
			NodeL n = this;
			while (n.next != null) {
				n = n.next;
			}
			n.next = end;
		}

		NodeL deleteNode(NodeL head, int d) {
			NodeL n = head;
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
