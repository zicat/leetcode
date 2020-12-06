package name.zicat.leetcode.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *  比较相邻两个元素,比较大小,不满足大小关系的情况下互换
 *
 *  一个冒泡可以让一个元素找到相应的位置
 *
 *  n个元素的排序,需要冒泡n次
 *
 *  平均最坏 时间复杂度O(n^2): 4,3,2,1
 *
 * 选择排序
 *  遍历所有的元素,选择最小的放在第一个位置
 *  遍历未排序所有元素,选择最小的放第2个位置
 *  重复 n次
 *  平均,最坏时间复杂度O(n^2):4,3,2,1
 *
 * 归并排序
 *  把数组分为两半,分别排序
 *  然后归并在一起
 *  按照同样的方法将分成两个部分 分别排序再合并
 *
 *  平均,最坏时间复杂度O(nlogn):4 3 2 1
 *  从递归的角度来分析的话：第一步：遍历所有的数，两两比较进行排序，这就完成了一趟操作。它的时间复杂度为n..
    第二步：递归完成上述操作,它的时间复杂度为logn,二分递归,所以总的就是nlogn它是稳定的算法
 *
 *   12 8 5 9
 *  先平分成两组
 * [12,8] [5,9]
 *
 * [8,12] [5,9]
 *
 * 再合并
 * [5,8,9,12]
 *
 * 快速排序
 * 随意挑选一个元素
 * 小于此元素的排在左边,大于此元素的排在右边
 * 对于左右两部分重复此操作
 *
 * 因为不是报纸挑选的元素是中值,最坏的时间复杂度是O(N^2)
 *
 * 平均时间复杂度 O(nlogn),正好取到的元素是 中值 就是归并,最大的数的话就是 冒泡排序
 *
 * 11 12 4 3 5 9 8  取8
 *
 * 4 3 5 [8] 11 12 9  取 4 和11
 *
 * 3 [4] 5 [8] 9 [11] 12
 *
 *
 * 堆排序
 *
 * 树形的选择排序
 *
 * 整体主要由构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成
 *
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，
 * 此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 *
 * nlogn。所以堆排序时间复杂度最好和最坏情况下都是O(nlogn)级。
 *
 *
 *
 */
public class Sort {

	public static void main(String[] args) {
		int[] arr = new int[]{4,6,8,5,9};
		int length = arr.length;
		//从最后一个非叶节点开始构建大顶堆
		for (int i = arr.length/2-1; i >=0; i--) {
			maximumHeap(i,arr,length);
		}
		//从最小的叶子节点开始与根节点进行交换并重新构建大顶堆
		for (int i = arr.length-1; i >=0; i--) {
//            System.out.println(Arrays.toString(arr));
			swap(arr,0,i);
			length--;
			maximumHeap(0,arr,length);
		}
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 归并排序
	 */
	public void mergeSort(int[] input){
		devide(input,0,input.length-1);

	}

	private void devide(int[] input, int low, int high) {
	if(low < high){
		int mid =(low+high)/2;
		devide(input,low,mid);//left half
		devide(input,mid+1,high);
		merge(input,low,mid,high);
	}

	}

	private void merge(int[] input, int low, int mid, int high) {
		int[] helper = new int[input.length];
		for(int i= low;i<=high;i++){
			//对input进行修改之前 进行copy
          helper[i] = input[i];
	   }
		/**
		 * 分治完以后
		 */
		int left =low;
		int right = mid +1;
		int i =low;
		/**
		 * 对比两个头元素,最小的 放到 input[i]的位置
		 */
		while(left<=mid && right<=high){
          if(helper[left] <=helper[right]){
          	input[i++] = helper[left++];
          	//然后 i再++,在移动 left和 right的位置
		  } else {
          	input[i++] = helper[right++];
		  }
		}

		/**
		 * left和 right 可能没有走到最后的元素
		 * right部分不需要copy,因为 helper就是copy的input 对应位置上的元素就是相等的
		 */
		if(left<mid){
          for(int r=0;r<=mid-left;r++){
          	input[i+r]= helper[left+r];
		  }
		}

	}

	/**
	 * 快速排序的实现
	 */

	public void quickSort(int[] input,int left,int right){
		int pivot = devideQuickSort(input,left,right);
		if(left < pivot -1){
			quickSort(input,left, pivot-1);
		}
		if(pivot <right){
			quickSort(input,pivot ,right);
		}

	}

	private int devideQuickSort(int[] input, int left, int right) {
		/**
		 * 选择中间元素
		 */
		int pivot = input[(left+right)/2];
		/**
		 *
		 */
		while(left < right){
			while(input[left]<pivot){
				/**
				 * 已经在正确的位置 ++即可
				 */
				left ++;
			}
			while(input[right] >pivot){
				/**
				 * 已经在正确的位置 --即可
				 */
				right --;
			}
			if(left <=right){
				/**
				 * 如果不是 进行交换
				 */
				int temp = input[left];
				input[left] =input[right];
				input[right] = temp;
				left ++;
				right --;
			}
		}
		return left;
	}


	//构建大顶堆
	public static void maximumHeap(int i,int[] arr,int length){
		int temp = arr[i];
		for (int j = i*2+1; j < length; j=j*2+1) {
			//如果右孩子大于做孩子，则指向右孩子
			if(j+1<length && arr[j+1]>arr[j]){
				j++;
			}
			//如果最大的孩子大于当前节点，则将大孩子赋给当前节点，修改当前节点为其大孩子节点，再向下走。
			if(arr[j]>temp){
				arr[i] = arr[j];
				i = j;
			}else{
				break;
			}
		}
		//将temp放到最终位置
		arr[i] = temp;
	}
	//交换
	public static void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}




}
