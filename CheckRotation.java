package arrayString;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class CheckRotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "waterbottle";
		String b = "bottlewater";
		int j = 0;
		int s = a.length() / 2;
		String k = a.substring(s, a.length());
		System.out.println(k);

		Queue<Character> m = new PriorityQueue<Character>();

		char[] c = a.toCharArray();
		char[] d = b.toCharArray();
		for (int i = 0; i < d.length; i++) {
			if (c[c.length - 1] == d[i])
				j = i;
				break;
		}
		System.out.println("queue1"+Arrays.toString(d));
		for (int i = 0; i < d.length; i++) {
			m.add(d[i]);
		}
		System.out.println("queue1"+m);
		while (j < c.length - 1) {
			char i = m.poll();
			m.add(i);
			j++;
		}
		System.out.println("queue"+m);
	}

}
