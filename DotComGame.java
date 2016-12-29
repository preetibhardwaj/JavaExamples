import java.util.*;

/*The computer will prompt you to enter a guess (a cell), that you 'U type at the command-line as "A1","C5", etc.). In response
to your guess, you'll see a result at the command- line, either "Hit", "Miss". When you've sent all three Dot Cams  you win*/
public class DotComGame{
	
	public static void main(String[] args){
	
	//Array to build game with three dot com values
	ArrayList<String> gridRowA = new ArrayList<String>(3);  
	ArrayList<String> gridRowC = new ArrayList<String>(3); 
	ArrayList<String> gridRowE = new ArrayList<String>(3);
	
	gridRowA.add("A1");
	gridRowA.add("A2");
	gridRowA.add("A3");
	
	gridRowC.add("C4");
	gridRowC.add("C5");
	gridRowC.add("C6");
	
	gridRowE.add("E3");
	gridRowE.add("E4");
	gridRowE.add("E5");
	
	//play game make a guess
	while(!gridRowA.isEmpty() || !gridRowC.isEmpty() || !gridRowE.isEmpty())
	{
		System.out.println("Enter your guess");
		System.out.println("To terminate press Q");
		
		Scanner sc=new Scanner(System.in);
		String userGuess=sc.next();
		
		if(userGuess.equals("Q")){
			System.exit(0);
		}	
		
		else{
			if (gridRowA.contains(userGuess) || gridRowC.contains(userGuess) || gridRowE.contains(userGuess)){
				System.out.println("hit");
				int indexA=gridRowA.indexOf(userGuess);
				int indexC=gridRowC.indexOf(userGuess);
				int indexE=gridRowE.indexOf(userGuess);
				
				if(indexA!=-1)
					gridRowA.remove(indexA);
				if(indexC!=-1)
					gridRowC.remove(indexC);
				if(indexE!=-1)
					gridRowE.remove(indexE);
			}
			else{
				System.out.println("miss");
			}
			
			}
		}
	}
	
}