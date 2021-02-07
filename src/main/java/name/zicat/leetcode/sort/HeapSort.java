package name.zicat.leetcode.sort;


import java.util.Arrays;

/**
 * 构建初始堆+交换堆顶元素和末尾元素并重建堆
 */
public class HeapSort {



	public static void main(String[] args) {

		int[] arr = new int[]{10,3,4,8,9,1,2,6,7};
        int length = arr.length;

        //build Initial max heap
		for (int i = length/2-1; i >=0; i--) {
			buildMaxHeap(i, arr,length);
		}

		System.out.println(Arrays.toString(arr));

		// swap last node with root node , and rebuild MaxHeap
		for (int i = arr.length-1; i >=0; i--) {
			swap(arr,0,i);
			length--;
			buildMaxHeap(0,arr,length);
		}
		System.out.println(Arrays.toString(arr));
	}



	//buildMaxHeap
	public static void buildMaxHeap(int i,int[] arr,int length){
		int temp = arr[i];
		for (int j = i*2+1; j < length; j=j*2+1) {
			//if right child > left child,
			if(j+1<length && arr[j+1]>arr[j]){
				j++;
			}
			//if child > current node
			if(arr[j]>temp){
				arr[i] = arr[j];
				i = j;
			}else{
				break;
			}
		}
		//put temp
		arr[i] = temp;
	}

	//swap
	public static void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


}
