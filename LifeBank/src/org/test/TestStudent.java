package org.test;

public class TestStudent{
	public static void main(String[] args) {
		//为数组分配内存空间
		int[] st = new int[15];
		//赋值
		int st1[] = {10,11,12,13,14,15,1,2,3,4,5,6,7,8,9};
		int N = st1.length;
		int temp = 0;
		for(int i=1;i<N;++i){
            for(int j=0;j<N-i;++j){
                if(st1[j]>st1[j+1]){
                    temp=st1[j];        
                    st1[j]=st1[j+1];
                    st1[j+1]=temp;
                }
            }
        }
         
        for(int i=0;i<N;++i)         //输出排序后的结果
        {
            System.out.print(st1[i] + "  ");
        }
	}
}
