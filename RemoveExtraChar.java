package arrayString;

public class RemoveExtraChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="abababa";
		char[] arr=new char[s.length()];
		arr=s.toCharArray();
		for(int i=0;i<arr.length-1;i++)
		{
			for(int j=i+1;j<arr.length;j++){
				if(arr[i]==arr[j]){
					arr[i]='X';
					//Why replace didn't work out
					//s.replace(s.charAt(i), 'X');
				}
			}
			System.out.println(arr);
		}
		// can use ASCII array of 256 characters too
		
	}

}
