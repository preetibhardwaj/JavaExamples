package stackQueues;

import java.util.Stack;

public class SortStack {

	private static Stack<Integer> s1 = new Stack<Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortStack s = new SortStack();
		Stack<Integer> s1 = new Stack<Integer>();
		s1.add(1);
		s1.add(8);
		s1.add(3);
		s1.add(4);
		s1.add(1);
		System.out.println(s.sort(s1));
	}

	public Stack sort(Stack<Integer> s1) {
		Stack<Integer> temp = new Stack<Integer>();
		while (!s1.isEmpty()) {
			int data = s1.pop();
			while (!temp.isEmpty() && data < temp.peek()) {
				s1.push(temp.pop());
			}
			temp.push(data);
		}
		return temp;
	}
}
