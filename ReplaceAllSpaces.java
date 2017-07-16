package arrayString;

import java.util.Arrays;

public class ReplaceAllSpaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String h="harry has a daughter";
		char[] arr=new char[h.length()];
		arr=h.toCharArray();
		int count=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]==' '){
				count++;
			}
		}
		int len=arr.length+count*2;
		char[] arr1=new char[len];
		int j=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]==' '){
				
				arr1[j++]='%';
				arr1[j++]='2';
				arr1[j++]='0';
			}
			else{
			arr1[j++]=arr[i];
			}
		}
		System.out.println(arr1);
	}

}
