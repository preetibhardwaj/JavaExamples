package arrayString;

public class ReverseCString {
	public static void main(String[] args) {
		// c-style string which has null character in the end
		String a="abcd\0";
		int i=0,j=a.length()-1;
		char[] arr=new char[a.length()];
		arr=a.toCharArray();
		//arr[j]='\0';
		while(i<j){
			char c=arr[i];
			arr[i]=arr[j];
			arr[j]=c;
			i++;
			j--;
		}
		System.out.println(arr);
	}
}
