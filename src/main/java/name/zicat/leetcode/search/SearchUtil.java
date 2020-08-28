package name.zicat.leetcode.search;

public class SearchUtil {

	public static void main(String[] args) {
		int[] nums ={0,1,2,3,4,5};
		int target = 7;
		System.out.println("target = " + binarySearchInsert(nums,target));;
	}

	/**
	 * 二分法,寻找 元素 插入的位置,时间复杂度 O(logN)
	 */

	public static int binarySearchInsert(int[] nums,int target) {
		if(nums == null) return 0;
		int start =0;
		int end = nums.length-1;
		while(start<=end){
			int mid = (start+end)/2;
			if(nums[mid] == target) return mid;
			else if(nums[mid] < target) start = mid +1;
			else end = mid-1;

		}

        return start;
	}
}
