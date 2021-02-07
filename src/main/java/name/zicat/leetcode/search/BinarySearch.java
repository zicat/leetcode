package name.zicat.leetcode.search;

/**
 * 二分搜索
 *
 * 1.针对排好序的数据集合
 * 跟中间元素比较
 *
 * 通过比较,把查找区缩小为之前的一半
 *
 * 直到找到元素
 *
 * 区间为0d的时候查找失败
 *
 * 时间复杂度为 O(logn) 查找的复杂度是 二叉树的高度
 */
public class BinarySearch {

	/**
	 * 二分法的查找
	 *
	 * 输入是个排好序的数组
	 *
	 * 返回 下标
	 */

	public int binarySearch(int[] input,int low,int high,int value){
		if(high >=low){
			/**
			 * 拿出中间值
			 */
			int mid = high+(high-low)/2;

			if(input[mid] ==value) return mid;

			/**
			 * 在 左半部分查找
			 */
			if(input[mid] > value){
				return binarySearch(input,low,mid -1,value);
			}

			return binarySearch(input,mid+1,high,value);
		}
		return -1;
	}

	/**
	 * 162.寻找峰值
	 *
	 * 输入 nums = [1,2,3,1]
	 * 3 是 峰值 返回 索引 2
	 *
	 * 输出 [1,2,1,3,5,6,4]
	 *
	 *  2, 6 是峰值
	 *
	 * 峰值的左边 是 递增的
	 *
	 * 峰值的右边 是 递减的
	 *
	 */

	public int findPeakElement(int[] nums){
       //查找边界条件
		if(nums == null || nums.length ==0|| nums.length ==1){
			return 0;
		}

		//起始点
		int start =0;
		int end = nums.length -1;

		while( start+1<end){
			int mid = start + (end-start)/2;
			/**
			 * 中间值 拿前一个比较,看 斜率,移动的是 start还是 end
			 */
			if(nums[mid] < nums[mid-1]){
				end =mid;
			} else {
				start =mid;
			}


		}
		if(nums[start] > nums[end]){
			return start;
		} else {
			return end;
		}
	}




}
