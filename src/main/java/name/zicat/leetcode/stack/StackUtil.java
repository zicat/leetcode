package name.zicat.leetcode.stack;

/**
 *
 */
public class StackUtil {
	/**
	 * 配对问题 用 栈
	 */

	/**
	 * 84 柱状图中最大的矩形面积。
	 *
	 * 输入 [2,1,5,6,2,3]
	 *
	 * 输出 10  ,5和6 最大矩阵组合是 10
	 *
	 * 某个柱子 向左右扩展,遇到比自己矮的柱子,停止
	 *
	 * 给定一个数 找到 左边和右边  第一个 比自己小的数的位置
	 *
	 * 单调栈里面的元素 是 单调递增或者 单调递减
	 *
	 * 如果 入栈的数 比自己小,就可以知道出栈的元素左右的比自己小的数,即计算出栈的元素的面积
	 *
	 */
	public static int  largestRectangleArea(int[] heights) {
		/**
		 * 处理边界情况
		 */
		if( heights == null || heights.length ==0) {
			return 0;
		}

		int l =heights.length;
		/**
		 * 维护成单调递增的栈
		 */
		Stack<Integer> stack = new Stack<>();
		int result =0 ;
		for (int i=0;i<=l;i++){
			/**
			 * 出栈的时候 才会计算 柱子为高度的矩阵,所以最后一个元素的时候要 push进去个-1
			 */
			int cur = i == l ? -1:heights[i];

			/**
			 * 如果小于 栈订端的元素,即找到 顶端元素 左边和右边最小的数 的index停止即可
			 */
			while(!stack.isEmpty() && cur<=heights[stack.peek()]) {
				//高就是要出栈的元素
             int height = heights[stack.pop()];
				/**
				 * 单调递增的  left 基本上就是自己的index位置
				 */
				int left = stack.isEmpty()? 0: (stack.peek()+1);

				/**
				 * 右边就是
				 */
				int right = i-1;
				int erea = height * (right - left +1);
				result = Math.max(result,erea);

			}
			/**
			 * push 索引位置
			 */
			stack.push(i);
		}
		return result;
	}


	/**
	 * 1249 移除无效的括号 (medium)
	 *
	 * 左括号对应 入栈操作, 右括号出栈操作
	 *
	 * input s= "lee(t(c)o)de)"
	 *
	 * output leet()
	 *
	 */

	public static String  minRemoveToMakeValid(String s) {
		if(s == null){
			return null;
		}
		// 保存 左括号的下标
		Stack<Integer> stack = new Stack<>();

		// 默认值是 false，数组中值为 true的数组下标对应的值需要移除
		boolean[] checkExist = new boolean[s.length()];

		for(int i=0;i< s.length();i++){

			if(s.charAt(i) == '('){
				//左括号的话入栈
				stack.push(i);
				//并且此时 暂无匹配的 右括号
				checkExist[i] =true;
			}

			/**
			 * 栈不是空的 ,说明有匹配
			 */
			if(s.charAt(i) == ')' && !stack.isEmpty()){
				//有匹配
				checkExist[stack.pop()] =false;

			}
			else if (s.charAt(i) ==')' && stack.isEmpty()){
				//无匹配
				checkExist[i] = true;
			}

		}

		StringBuilder result = new StringBuilder();
		for(int i=0;i< s.length();i++){
			if(checkExist[i] == true){
				continue;
			}
			result.append(s.charAt(i));
		}
		return result.toString();
	}

	/**
	 * 42.接雨水
	 *
	 * 输入 [0,1,0,2,1,0,1,3,2,1,2,1]
	 *
	 * 输出 6
	 *
	 * 递减时不断入栈
	 *
	 * 出现高出前面的柱子,可以计算 接水槽
	 *
	 * [4 3 2 5]
	 * 出栈 第三个元素 2
	 * 计算 5 与 3之间的积水
	 *
	 * 积水槽的面积 (3(5的下标)-1(3的下标)-1 )* [min(3,5)-2]
	 *
	 * 计算 5与 4之间的积水
	 * (3(5的下标)-0(4的下标)-1)*(4-3)
	 *
	 * O(N) 每个柱子都最多进栈一次,出栈一次
	 *
	 */

	public static int trap(int[] height) {
		Stack<Integer> stack = new Stack<>();
		int area =0;
		int index =0;
		while(index < height.length){
			while(!stack.isEmpty()&& height[index] > height[stack.peek()]) {
				int top = stack.pop();
				if(stack.isEmpty()){
					break;
				}
				int distance = index - stack.peek() -1;
				int height2= Math.min(height[index],height[stack.peek()]) -height[top];
                area += distance * height2;

			}
			stack.push(index);
			index++;
		}
		return area;

	}

	/**
	 * 85 最大矩形
	 *
	 * [1,0,1,0,0]
	 * [1,0,1,1,1]
	 * [1,1,1,1,1]
	 * [1,0,0,1,0]
	 *
	 * 替换成柱状图 求 柱状图的最大面积
	 *
	 *
	 * 没看懂
	 *
	 *
	 */



	public static int maxArea(int[] heights) {
		if(heights == null || heights.length ==0){
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int max =0;
		int i=0;
		while(i<heights.length){
			if(stack.isEmpty() || heights[stack.peek()] <=heights[i]) {
				stack.push(i++);
			} else {
				int h =heights[stack.pop()];
				int width =stack.isEmpty()?i:i-stack.peek()-1;
				max =Math.max(max,h*width);
			}
		}
		//与雨水提的区别
		while(!stack.isEmpty()){
			int height = heights[stack.pop()];
			int width = stack.isEmpty() ? i:i-stack.peek()-1;
			max = Math.max(max, height*width);
		}
		return max;
	}

	// Get the maximum area in a histogram given its heights
	public static int leetcode84(int[] heights) {
		Stack < Integer > stack = new Stack < > ();
		stack.push(-1);
		int maxarea = 0;
		for (int i = 0; i < heights.length; ++i) {
			while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
				maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
			stack.push(i);
		}
		while (stack.peek() != -1)
			maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
		return maxarea;
	}

	public static int maximalRectangle(char[][] matrix) {

		if (matrix.length == 0) return 0;
		int maxarea = 0;
		int[] dp = new int[matrix[0].length];

		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {

				// update the state of this row's histogram using the last row's histogram
				// by keeping track of the number of consecutive ones

				dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
			}
			// update maxarea with the maximum area from this row's histogram
			maxarea = Math.max(maxarea, leetcode84(dp));
		} return maxarea;
	}








}
