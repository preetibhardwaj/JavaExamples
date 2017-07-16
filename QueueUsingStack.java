package stackQueues;

import java.util.Stack;

public class QueueUsingStack {
	private static Stack<Integer> s1 = new Stack<Integer>();
	private static Stack<Integer> s2 = new Stack<Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueueUsingStack q=new QueueUsingStack();
		q.add(2);
		q.add(5);
		q.add(6);
		q.add(9);
		System.out.println(q.remove());
		System.out.println(q.remove());
	}

	public void add(int data) {
		s1.push(data);
	}

	public int peek() {
		if (!s2.isEmpty()) {
			return s2.peek();
		}
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		return s2.peek();
	}

	public int remove() {
		if (!s2.isEmpty()) {
			return s2.pop();
		}
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		return s2.pop();
	}

}
