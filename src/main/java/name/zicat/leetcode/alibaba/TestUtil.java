package name.zicat.leetcode.alibaba;

import name.zicat.leetcode.tree.TreeNode;

public class TestUtil {
	public static void main(String[] args) throws Exception{

	}


	/**
	 * 1035 不相交的线
	 *
	 * 基本思路就是：dp[i][j]表示A截止到i，B截止到j点，此时的最大连线数
	 *
	 * 转移方程为：
	 *
	 * 当A[i] == B[j]时: dp[i][j] = dp[i - 1][j - 1] + 1
	 * 当A[i] != B[j]时: dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
	 *
	 * 是个九宫图
	 *
	 */

	public static int maxUncrossedLines(int[] A,int[] B) {
		int[][] dp = new int[A.length+1][B.length+1];
		for(int i =0;i<A.length;i++){
			for(int j=0;j<B.length;j++){
				if(A[i]==B[j]){
					dp[i+1][j+1]= dp[i][j] +1;

				} else {
					dp[i+1][j+1] =Math.max(dp[i][j+1],dp[i+1][j]);
				}
			}
		}

		return dp[A.length][B.length];
	}

	/**
	 * 10.正则表达式匹配
	 *
	 * s 和 p 相互匹配的过程大致是，两个指针 i, j 分别在 s 和 p 上移动，如果最后两个指针都能移动到字符串的末尾，那么久匹配成功，反之则匹配失败
	 *
	 * 动态规划解法
	 *
	 * 定义一个 dp函数
	 *
	 * bool dp(String s,int i,String p,int j)
	 *
	 * 若 dp(s, i, p, j) = true，则表示 s[i..] 可以匹配 p[j..]；若 dp(s, i, p, j) = false，则表示 s[i..] 无法匹配 p[j..]。
	 *
	 */

	public static boolean isMatch(String s,String p){

		int m = s.length();
		int n = p.length();

		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;

		for(int i=0;i<=m;i++){
			for(int j=1;j<=n;j++){
				/**
				 *   * 代表可以匹配零个或多个前面的那一个元素
				 *   所以只要匹配*号前的元素是否相等即可
				 */
				if(p.charAt(j-1) == '*'){
					dp[i][j] = dp[i][j-2];
					if(matches(s,p,i,j-1)){
						dp[i][j] = dp[i][j] ||dp[i-1][j];
					}

				}
				else {
					/**
					 * 完全匹配 当前char
					 */
					if(matches(s,p,i,j)){
						dp[i][j] = dp[i-1][j-1];
					}
				}
			}

		}

		return dp[m][n];

	}

	public static boolean matches(String s,String p,int i, int j){
		if(i ==0){
			return false;
		}
		if(p.charAt(j -1 ) == '.'){
			return true;
		}
		return s.charAt(i-1) == p.charAt(j-1);
	}

	/**
	 * 968.  监控二叉树 贪心算法
	 *
	 * 主要是利用贪心思想来确定每个结点的状态
	 * 有三个状态:
	 * 0=>这个结点待覆盖
	 * 1=>这个结点已经覆盖
	 * 2=>这个结点上安装了相机
	 *
	 * 建立数学模型来描述问题
	 * 把求解的问题分成若干个子问题
	 * 对每个子问题求解，得到子问题的局部最优解
	 * 把子问题的解局部最优解合成原来问题的一个解
	 */
     static int res;
	public static int minCameraCover(TreeNode root){
		  if(lrd(root)==0){
		  	res ++;
		  }
		  return res;


	}

	public static int lrd(TreeNode node){
		if(node ==null){
			return 1;
		}

		int left = lrd(node.left);
		int right = lrd(node.right);
		/**
		 * 左孩子和右孩子的覆盖情况,都待覆盖的话
		 */
		if(left == 0|| right ==0){
			res ++;
			return 2;
		}
		/**
		 * 左右孩子 已经覆盖的情况,那当前节点 待覆盖
		 */
		if(left == 1 && right ==1){
			return 0;
		}
		/**
		 * 左右孩子中间 有一个 是 装了 相机,那该节点已经覆盖
		 */
		if(left + right >=3){
			return 1;
		}

		return -1;
	}


}
