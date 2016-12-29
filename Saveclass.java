public class Saveclass{

	int num1;
	int num2;
	String val;
	int ans;

	public Saveclass(int a,int b,String cont,int c){
		num1 = a;
		num2 = b;
		val = cont;
		ans = c;
	}
	
	public String toString(){
	
	return "a: "+num1+"b: "+num2+" selected opt: "+val+" output :"+ans;
	
	}
}