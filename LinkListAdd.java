package linklist;

public class LinkListAdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NodeL a=new NodeL(3);
		a.appendToTail(1);
		a.appendToTail(5);
		
		NodeL b=new NodeL(5);
		b.appendToTail(9);
		b.appendToTail(6);
		b.appendToTail(9);
		NodeL head2;
		head2=new NodeL((a.data+b.data)%10);
		int carry=0;
		while(a.next!=null && b.next!=null){
			a= a.next;
			b=b.next;
			head2.appendToTail((a.data+b.data+carry)%10);
			carry=(a.data+b.data+carry)/10;
		}
		while(b.next!=null){
			b=b.next;
			head2.appendToTail((b.data+carry)%10);
			carry=(b.data+carry)/10;
		}
		while(a.next!=null){
			a=a.next;
			head2.appendToTail((a.data+carry)%10);
			carry=(a.data+carry)/10;
		}
		head2.appendToTail(carry);
		while (head2 != null) {
			System.out.println("t"+head2.data);
			head2=head2.next;
			}
		
	}

}
