package arrayString;

import java.util.ArrayList;

public class AllUniqueChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="abhpds";
		// By sorting the array
		/*ArrayList<Character> arr=new ArrayList<Character>();
		for(Character i:a.toCharArray()){
		arr.add(i);
		}
		arr.sort(null);
		for(int i=0;i<arr.size()-1;i++){
			if(arr.get(i)==arr.get(i+1)){
				System.out.println("not unique");
			}
		}
		*/
		//By ASCII array
		boolean[] char_arr=new boolean[256];
		for(int i=0;i<a.length();i++){
			int val=a.charAt(i);
			System.out.println(val);
			if(char_arr[val]){
				System.out.println("not unique");
			}
			else{
				char_arr[val]=true;
			}
			
		}
		
	}

}
