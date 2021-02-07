package name.zicat.leetcode.sort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
	 *
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
		return getKth(nums.length -k +1,nums,0,nums.length -1);

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

	/**
	 * 按奇偶排序数组
	 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
	 *
	 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
	 *
	 * 你可以返回任何满足上述条件的数组作为答案
	 * 输入：[4,2,5,7]
	 * 输出：[4,5,2,7]
	 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
	 */
	public static int[] sortArrayByParity2(int[] A){
		int odd =1;
		Integer notoddIndex =null;
		Integer noteventIndex =null;
		int even =0;
		while(odd < A.length){
			if(notoddIndex==null){
				if(A[odd] %2 !=1){
					notoddIndex = odd;
				} else {
					odd = odd + 2;
				}
			}

			if(noteventIndex ==null && even < A.length){
				if(A[even] % 2 !=0){
					noteventIndex = even;
				} else {
					even = even + 2;
				}
			}

			if(noteventIndex!=null && notoddIndex!=null){
				int temp = A[notoddIndex];
				A[notoddIndex] = A[noteventIndex];
				A[noteventIndex] = temp;
				noteventIndex = null;
				notoddIndex = null;
			}

		}
		return A;

	}

	/**
	 * 按奇偶排序数组
	 * 双指针 正解
	 * 时间复杂度： O(N)，其中 N 是 A 的长度。
	 */
	public static int[] sortArrayByParity3(int[] A) {
		int j = 1;
		for (int i = 0; i < A.length; i += 2)
			if (A[i] % 2 == 1) {
				while (A[j] % 2 == 1)
					j += 2;

				// Swap A[i] and A[j]
				int tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;
			}

		return A;
	}

	/**
	 * 将矩阵按对角线排序
	 * 对角线 是 横坐标和 纵纵坐标+1
	 * @param
	 */
	public int[][] diagonalSort(int[][] mat) {
		// 行数
		int m = mat.length;
		// 列数
		int n = mat[0].length;
		// 主对角线的条数
		int dLen = m + n - 1;

		// 每一条对角线都创建一个动态数组
		ArrayList<Integer>[] diagonal = new ArrayList[dLen];
		for (int i = 0; i < dLen; i++) {
			diagonal[i] = new ArrayList<>(m);
		}

		// 遍历原始矩阵，把原始矩阵中的元素放进对应的动态数组中
		// 主对角线上元素的特点是：纵坐标 - 横坐标 = 定值
		// 加上偏移 m - 1 是为了能够放进数组中
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				diagonal[j - i + (m - 1)].add(mat[i][j]);
			}
		}

		// 对每一个对角线上的动态数组分别进行升序排序
		for (int i = 0; i < dLen; i++) {
			Collections.sort(diagonal[i]);
		}

		int[][] res = new int[m][n];

		// 对角线数组上还未取出的元素的下标，初始化的时候均为 0
		int[] next = new int[dLen];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 对角线的坐标
				int index = j - i + (m - 1);
				// 记录结果
				res[i][j] = diagonal[index].get(next[index]);
				// 维护 next 数组的值
				next[index]++;
			}
		}
		return res;
	}


	/**
	 * 字典序的第K小数字
	 *
	 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字
	 *
	 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
	 *
	 *
	 *              1
	 *     10    11    12    13
	 * 100...109 110..119
	 *
	 *      2
	 *
	 *      3
	 *
	 * @param
	 */

	public static int findKthNumber(int n, int k) {
		int cur = 1;
		k = k - 1;//扣除掉第一个0节点
		while(k>0){

			int num = getNodeNum(n,cur,cur+1);

			if(num<=k){//第k个数不在以cur为根节点的树上
				cur+=1;//cur在字典序数组中从左往右移动
				//并且k 去掉 以cur为根节点的 数值
				k-=num;
			}else{//在子树中
				cur*=10;//cur在字典序数组中从上往下移动
				//从 以10为root开始搜索
				k-=1;//刨除根节点
			}
		}
		return cur;
	}

	/**
	 * rootNode 为1
	 *   1
	 * 10 11 12.。。19  [10..19] 9个值  19<20 就是 20-
	 *
	 *
	 * @param n
	 * @param first
	 * @param last
	 * @return
	 */
	public static int getNodeNum(int n, long first, long last){
		int num = 0;
		while(first <= n){
			/**
			 *  rootNode 为1
			 *  第二层是 last(20,200,2000) - first(10,100,1000)
			 *
			 *  rootNode 为 10
			 *
			 * 第二层是 last(11,110)  - first(10,100)
			 *
			 */
			num += Math.min(n+1,last) - first;//比如n是195的情况195到100有96个数
			first *= 10;
			last *= 10;
		}
		return num;
	}


	public static void main(String[] args) {
		//int[] A = {3,1,3,2,2,1,1,1,2,0,0,4,0,1,0,1,1,1,2,2};
		//System.out.println("A = " + Arrays.toString(sortArrayByParity2(A)));;
		findKthNumber(2000,1000);
	}
}
