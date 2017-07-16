package stackQueues;

import java.util.ArrayList;
import java.util.Stack;

public class SetOfStacks extends Stack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SetOfStacks ss = new SetOfStacks(3);
		ss.push(4);
		ss.push(3);
		ss.push(5);
		ss.push(6);
		ss.push(3);
		ss.push(8);
		ss.push(9);
		ss.push(6);
		ss.push(11);
		ss.push(2);
		System.out.println(ss.stacks);
		ss.popatindex(1);
		System.out.println(ss.stacks);
	}

	private int capacity;
	private ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
	private int i = 0;

	public SetOfStacks(int capacity) {
		this.capacity = capacity;
	}

	public void push(int data) {
		if (stacks.isEmpty()) {
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(data);
			stacks.add(stack);

		} else if (stacks.get(i).size() != capacity) {
			stacks.get(i).push(data);
		} else {
			i++;
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(data);
			stacks.add(stack);
		}
	}

	public Object pop() {
		int data = stacks.get(i).pop();
		if (stacks.get(i).isEmpty()) {
			stacks.remove(i);
			i--;
		}
		return data;
	}

	public Object popatindex(int index) {
		int newindex = index / capacity;
		int data = stacks.get(newindex).remove(index % capacity);
		leftshift(newindex);
		return data;
	}

	public void leftshift(int newindex) {
		if(stacks.size()==newindex+1){
			if(stacks.get(newindex).isEmpty()){
				stacks.remove(newindex);
			}
			return;
		}
		Stack<Integer> temp = new Stack<Integer>();
		while (!stacks.get(newindex + 1).isEmpty()) {
			temp.push(stacks.get(newindex + 1).pop());
		}
		stacks.get(newindex).push(temp.pop());
		while(!temp.isEmpty()){
			stacks.get(newindex+1).push(temp.pop());	
		}
		leftshift(newindex+1);
	}

}
