package stackQueues;

import java.util.Arrays;

public class ArrayToStacks {
	
	private static int size=10;
	private static int[] arr=new int[size*3];
	private static int[] stackpointer={0,0,0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		push(1,5);
		push(2,3);
		push(3,2);
		push(2,8);
		push(1,6);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(stackpointer));
		System.out.println(pop(1));
		System.out.println(peek(2));
		System.out.println(pop(1));
		System.out.println(isEmpty(1));
		System.out.println(Arrays.toString(arr));
	}
	
	private static void push(int stacknum,int value){
		arr[size*(stacknum-1)+stackpointer[stacknum-1]]=value;
		stackpointer[stacknum-1]++;
	}
	
	private static int pop(int stacknum){
		int index=stackpointer[stacknum-1];
		int data=arr[size*(stacknum-1)+index-1];
		arr[size*(stacknum-1)+index-1]=0;
		stackpointer[stacknum-1]--;
		return data;
	}
	
	private static int peek(int stacknum){
		int index=stackpointer[stacknum-1];
		int data=arr[size*(stacknum-1)+index-1];
		return data;
	}
	
	private static boolean isEmpty(int stacknum){
		return stackpointer[stacknum-1]==0;
	}
}
