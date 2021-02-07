package name.zicat.leetcode.divideAndConquer;

/**
 * 分治算法
 *
 * 分而治之
 *
 * 归并排序和 递归排序
 *
 * 把问题 分成2个 子问题,再近一步分解子问题
 *
 * 再 合并 子问题的解
 *
 * 通过递归实现
 *
 * 二分搜索
 *
 * 分治的步骤
 *
 * 分解:复杂问题分解为独立,相同形式的小问题
 * 进一步分解子问题,或者计算子问题
 *
 * 合并解
 *
 *
 * 二分搜索
 *
 */
public class DivideAndConquer {
	/**
	 * 1292 元素和小于等于阈值的正方形的最大边长
	 * 给一个大小为 m x n的矩阵mat 和一个整数阈值threshold
	 * 请返回元素总和小于或等于阈值的正方形区域的最大边长
	 * 没有则返回0
	 *
	 * 输入 mat =[[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]]
	 *
	 * threshold =4
	 *
	 * 总和小于 4的 正方形 最大边长 为 2
	 *
	 * 怎么 算正方形
	 * 先固定 左上角的值 比如 [0,0], 并计算边长 为1，2，3的正方形
	 *
	 */

	/**
	 * 二分查找
	 * 给定一个n个元素有序的升序整形数组 和一个目标值 target，如果目标值存在 返回下标
	 *
	 */
	public int search(int[] nums,int target){
		return searchBinaryDivide(nums,0,nums.length-1,target);
	}

	public int searchBinaryDivide(int[] nums, int start, int end,int target){
		if(start> end){
			return -1;
		}


		int mid = start + (end-start)/2;
		if(nums[mid] > target){
           return searchBinaryDivide(nums,start,mid-1,target);
		} else if(nums[mid]< target){
           return searchBinaryDivide(nums,mid+1,end,target);
		} else {
			return mid;
		}
	}
}

