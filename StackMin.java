package stackQueues;

import java.util.Stack;
//can also do by creating a different stack which keeps pushing only minimum value

public class StackMin extends Stack {
	int value;
	int min;

	public StackMin(int value, int min) {
		this.value = value;
		this.min = min;
		super.push(this);
	}

	@SuppressWarnings("unchecked")
	private void push(int data) {
		int newmin = data;
		if (this != null && this.min <data) {
			newmin=this.min;
		}
		super.push(new StackMin(data,newmin));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackMin s=new StackMin(3,3);
		s.push(4);
		System.out.println("stack"+s.value);
	}

}
