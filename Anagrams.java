package arrayString;

public class Anagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="preeti";
		String x="tireep";
		int[] arr=new int[256];
		for(int i=0;i<s.length();i++){
			int val=s.charAt(i);
			arr[val]+=1;
		}
		for(int i=0;i<x.length();i++){
			int val=x.charAt(i);
			arr[val]-=1;
		}
		
		for(int i=0;i<s.length();i++){
			int val=s.charAt(i);
			if(arr[val]!=0){
				System.out.println(" not anagrams");
			}
		}
	}

}
