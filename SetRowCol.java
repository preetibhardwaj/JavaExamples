package arrayString;

import java.util.Arrays;

public class SetRowCol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=0,n=0;
		int[][] arr= new int[][]{{1,2,3,4},
								{3,4,5,6},
								{6,0,4,3},
								{1,4,3,2}
		};
		
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				if(arr[i][j]==0){
					m=i;n=j;
					break;
				}
			}
		}
		for(int i=0;i<arr.length;i++){
			arr[m][i]=0;
		}
		for(int i=0;i<arr[0].length;i++){
			arr[i][n]=0;
		}
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				System.out.println(arr[i][j]);
			}
		}
	}
}
