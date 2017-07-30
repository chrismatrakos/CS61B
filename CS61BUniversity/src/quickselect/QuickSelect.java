package quickselect;

public class QuickSelect {

	public static void main(String[] args){
		int[] a={1,2,7,3,6,8,12,4,13,15,};
		System.out.println(quickSelect(a,0,a.length-1,10));
		
	}
	
	public static int quickSelect(int[]a,int low,int high,int k){
		if(low>=high){
			return a[low];	
		}
	
	
	int rand= new java.util.Random().nextInt((high - low) + 1) + low;
	//	int rand=(low+high)/2;
			int pivot=a[rand];
			swap(a,high,rand);
			int i=low;
			int j=high;
			
			while(i<j){
				while(a[i]<pivot){
					i++;
				}
				while(a[j]>=pivot && j>low){
					j--;
				}
				if(i<j){
					swap(a,i,j);
				}
			}
			
			a[high]=a[i];
			a[i]=pivot;
	
			//k-1 because indexing start at 0 not 1
			if(k-1<i){
				
				return quickSelect(a,low,i-1,k); 
			}
			else if(k-1==i){
				
				 return a[i];
			}
			else{
			return	quickSelect(a,i+1,high,k);  
			}
			
		
	}
	
	public static void swap(int a[],int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
