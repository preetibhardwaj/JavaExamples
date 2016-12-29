import java.util.*;
public class Testing{

public static void main(String args[]){

Scanner sc= new Scanner(System.in);
Calculator calc= new Calculator();
int userinput;
Stack<Saveclass> stack1=new Stack<Saveclass>();
//List<Saveclass> lastFive=new ArrayList<Saveclass>();
Queue<Saveclass> lastFive=new LinkedList<Saveclass>();
do{
Saveclass obj;

//Menu
System.out.println("1. ADD");
System.out.println("2. Subtract");
System.out.println("3. Multiply");
System.out.println("4. Divide");
System.out.println("5. Exit");
System.out.println("6. view last 5 results");

//Give input for 1st integer
System.out.println("Enter first number");
int a= sc.nextInt();

//Give input for 2nd integer
System.out.println("Enter second number");
int b= sc.nextInt();

System.out.println("Enter your choice");
userinput= sc.nextInt();

int d = 0;

switch(userinput){
		case 1:  d= calc.add(a,b);
				 obj = new Saveclass(a,b,"addition",d);
			     stack1.push(obj);
			     System.out.println(stack1);
				 break;
				 
		case 2:  d= calc.subtract(a,b);
			     stack1.push(new Saveclass(a,b,"substraction",d));
			     System.out.println(stack1);
				 break;
				 
		case 3:  d = calc.multiply(a,b);
			 	 obj= new Saveclass(a,b,"mult",d);
				 stack1.push(obj);
				 System.out.println(stack1);
				 break;
				 
		case 4:  d = calc.divide(a,b);
				 obj= new Saveclass(a,b,"divide",d);
			     stack1.push(obj);
			     System.out.println(stack1);
		         break;
		
		case 5:  System.exit(0);
		case 6:  System.out.println(stack1);
			while(!stack1.empty()){
			lastFive.add(stack1.pop());
			System.out.println(lastFive);
			} 
	}
}
while(userinput!=5);
}

}