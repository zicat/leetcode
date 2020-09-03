package name.zicat.leetcode.sort;


import java.util.Arrays;

public class SortUtil {
	/**
	 * 215 数组中的第K个最大的元素
	 * 在未排序的数组中找到 第k个最大的元素
	 *
	 * 输入 [3,2,1,5,6,4] k=2
	 *
	 * 输出 5
	 *
	 * 输入 [3,2,1,2,4,5,5,6] k=4
	 *
	 * 输出 4
	 *
	 * 算法
	 * 长度为 7
	 * k =4,转化为找 第4个最小元素 (7-4+1)
	 *
	 * left->11 12 4 3 5 9 8<-right
	 *
	 * left->11 12 4 3 5<-right 9 8 [11,5]交换
	 *
	 * 5 left->12 4 3<-right 11 9 8 [12,3]交换
	 *
	 * 5 3 4 left->12<-right 11 9 8  left和 right重叠了 停止分割  12和8交换位置
	 *
	 * 5 3 4 8 11 9 12  k= left+1=4 ,所以8就是答案
	 *
	 * 如果不是 往左边 或者 右边继续找
	 *
	 *
	 *
	 * 用 快排 最优  nlogn 选择的数正好是中间的值 最坏的是 n^2 选择的数不是 最小就是最大
	 *
	 * pivot =8
	 * nums[left] <8 往中间移动left
	 * nums[right] >=8 往中间移动 right
	 */

	public int findKthLarges(int[] nums,int k){
		//[3,2,1,5,6,4]
		//k =2
		//nums.length -k +1 =5
		if(k<1 || nums == null) {
			return 0;
		}
		return getKth(nums.length -k +1,nums,0,nums.length -1)

	}

	/**
	 * 第K个最小的元素
	 * @param k
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	public int getKth(int k,int[] nums, int start, int end){
		int pivot = nums[end];
		int left = start;
		int right = end;

		while(true){
			while( nums[left]<pivot && left< right){
				left ++;
			}
			while(nums[right]>=pivot && left < right){
				right --;
			}
			if(left == right){
				break;
			}
			swap1(nums,left,right);
		}

		swap1(nums,left,end);
		if(k == left +1){
			return pivot;
		} else if( k<left +1){
			return getKth(k,nums,start,left -1);
		} else {
			return getKth(k,nums,left+1,end);
		}

	}

	private void swap1(int[] nums, int left, int right) {
	int tmp = nums[left];
	nums[left] = nums[right];
	nums[right] = tmp;
	}

	/**
	 * 280.摆动排序
	 * [3,5,2,1,6,4]
	 *
	 * nums[0] <=nums[1] >=nums[2] <
	 *
	 * 奇数 下标小于等于前一个数,
	 * odd 要>=前一个数
	 * even 要 <=前一个数
	 * 不符合条件的 和 前一个数交换即可,不会影响前面的排序顺序
	 * 3，5，  2,   1,  6,   4
	 *   odd even  odd even odd
	 *
	 */

	public void wiggleSort(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			// 需要交换的情况：奇数时nums[i] < nums[i - 1]或偶数时nums[i] > nums[i - 1]
			if ((i % 2 == 1 && nums[i] < nums[i-1]) ||
					(i % 2 == 0 && nums[i] > nums[i-1])) {
				int tmp = nums[i-1];
				nums[i-1] = nums[i];
				nums[i] = tmp;
			}
		}
	}
	/**
	 * 324 摆动排序 2
	 * 给定一个无序数组,将他重新排列
	 * nums[0] <nums[1] > nums[2] <nums[3]的属性
	 *
	 * 把数组分割成两部分,
	 * 分别从 两个部分 各取一个数
	 *
	 * 1 5 1 1 6 4
	 *
	 * 快排成
	 * 1 1 1 5 6 4
	 *
	 * 新开一个数组 初始化值
	 *
	 */

	public void wiggleSort2(int[] nums){
		int[] tem = new int[nums.length];
		for (int i=0; i< nums.length;i++){
			tem[i] = nums[i];
		}
		int mid = getKth(nums.length/2+1,tem,0,nums.length-1);
		int[] ans = new int[nums.length];
		for(int i=0;i<nums.length;i++){
			ans[i] = mid;
		}
		int l,r;
		if(nums.length %2 ==0){
			l =nums.length -2;
			r =1;
			for(int i=0;i<nums.length;i++){
				if(nums[i]<mid){
					ans[l] = nums[i];
					l-=2;
				} else if(nums[i] > mid){
					ans[r] = nums[i];
					r +=2;
				}
			}
		} else {
			l =0;
			r =nums.length -2;
			for(int i=0;i<nums.length;i++){
				if(nums[i] <mid){
					ans[l] =nums[i];
					l+=2;
				} else if (nums[i]>mid){
					ans[r] =nums[i];
					r-=2;
				}
			}
		}
		for(int i=0;i<nums.length ;i++){
			nums[i] =ans[i];
		}

	}

	/**
	 * 973.最接近原点的K个点
	 *
	 * 需要中找出 K个距离原点(0,0) 最近的点
	 *
	 * 优先队列可以用来排序
	 *
	 * 或者 用 快排 找到第 K个的最小的元素
	 *
	 * 输入 points=[[1,3],[-2,2]],K=1
	 *
	 * 输出 [[-2,2]]
	 *
	 *
	 */

	public int[][] kClosest(int[][] points,int K){
		int l=0,r=points.length -1;
		sort(points,l,r,K);
		return Arrays.copyOfRange(points,0,K);
	}

	private void sort(int[][] points, int l, int r, int k) {
		while(l <=r){
			int pivot = partition(points,l,r);
			if(pivot == k) break;
			if(pivot <k){
				l =pivot +1;
			} else {
				r = pivot -1;
			}

		}
	}

	private int partition(int[][] points, int l, int r) {
		int[] pivot = points[l];

		while(l< r){
			while(l<r && dist(points[r],pivot)>0 ) r--;
			points[l] = points[r];
			while(l < r && dist(points[l],pivot)<=0) l++;
			points[r] =points[l];
		}
		points[l] = pivot;
		return l;
	}

	/**
	 * 原点到原点的距离
	 * 是根号  x^2 + y^2
	 * 那判断 哪个点 离原点近 相减 >0即可
	 * @param point
	 * @param pivot
	 * @return
	 */
	private int dist(int[] point, int[] pivot) {
		return point[0] * point [0] + point[1] * point[1]
				- pivot[0]* pivot[0] - pivot[1] * pivot[1];
	}
}
