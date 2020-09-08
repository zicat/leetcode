package name.zicat.leetcode.dynmaic.program;

/**
 * 动态规划 分类
 *
 *
 * 动态规划的 三要素
 *
 * 1.状态变量
 *
 * 2.递推方程
 *
 * 3.初始条件
 *
 * 动态规划分类
 *
 * 序列型 -关键词是 前 ,前i个的最大值最小值等
 *
 * 划分型
 *
 * 博弈题目
 *
 * 背包问题
 *
 */
public class DynamicProgramming {
/**
 * 序列型 动态规划
 * 198 打家劫舍
 *
 * 盗窃4个房屋 得到的金子 ,不可偷相邻的
 * 输入[1,2,3,1]
 * 输出 1+3 =4
 *
 * [2,7,9,3,1]
 *
 * 2+9+1 =12
 * 对于每个房屋是2种选择
 * 偷还是不偷
 *
 * 最后一个房子是偷还是不偷
 * 来定义转移方程
 *
 * 1.定义状态变量 dp[i] 表示盗窃前i家房子最多收益
 * 序列型 是 前i个最大值最小值 最多收益 等等
 * 从第一个开始 连续的序列
 *
 * 2.转移方程:
 * dp[i] =max(dp[i-2]+house[i],dp[i-1])
 *
 * dp[i-2] + house[i] 表示 盗窃房间i的钱,加上盗窃前i-2房子最多收益
 *
 * dp[i-1] 表示 不盗窃房间i,盗窃i-1家房子最多收益
 *
 * 3.初始状态:
 * dp[0] =0
 * dp[1] =house[1] 前1个房子的最多收益 就是 偷取第一个房子的钱
 *
 */

public int rob(int[] nums){
	/**
	 * 边界情况
	 */
	if(nums==null || nums.length ==0){
		return 0;
	}

	if(nums.length ==1){
		return nums[0];
	}

	int[] dp = new int[nums.length+1];
	dp[0] =0;
	dp[1] =nums[0];

	//转移方程
	for(int i=2;i<= nums.length;i++){
		dp[i] = Math.max(dp[i-2]+nums[i-1],dp[i-1]);
	}
	return dp[nums.length];
}

}

/**
 * 序列型 动态规划
 * 264,粉刷房子2
 * [[1,5,3],[2,9,4]]
 *
 * 从最后一步入手
 * 粉刷到最后一个房子 要保证 颜色和前面的不同,并且花费最小
 * 罗列最后的房子 粉刷的颜色,同事求出 前一个房子 不同颜色的情况下 加上最后的房子的最小值
 *
 * 1.状态变量:dp[i][j] 表示粉刷前i个房子,第i个房子的颜色 j有k种可能性,
 * 代表粉刷了i个房子,并且颜色为j的最小花费
 *
 * 2.转移方程: dp[i][j]=min( x=0...k)(dp[i-1][x] + costs[i-1][j]|x!=j) 粉刷前i-1的花费和最后一个房子的最小值
 * x!=j 因为 相邻的房子  颜色不同
 *
 * 3. 初始状态
 * dp[0][j] =0 前0个房子 粉刷各种颜色的最小收益 是0
 * dp[1][j] =cost[0][j] 前1个房子各种颜色的最小花费
 */
//没有滚动数组的情况下
public int minCost(int[][] costs){
	if(costs.length == 0) return 0;
	int n = costs.length;
	int k = costs[0].length;
	int result = Integer.MAX_VALUE;
	//另外开一个维度的数组 来保存颜色
	int[][] dp = new int[n+1][k];
	//对他进行初始化
	for(int i=0;i<k;i++){
		dp[0][i] =0;
		dp[1][i] =costs[0][i];
	}

	for(int i=2;i<=n ;i++){
		for(int j=0;j<k;j++){
			int min =Integer.MAX_VALUE;
			for(int x=0;x <k;x++){
				if(j!=x){
					min =Math.min(min,costs[i-1][j]+dp[i-1][x]);
				}
			}
			dp[i][j] =min;
		}
	}

	for(int i=0;i<k;i++){
		result =Math.min(result,dp[n][i]);
	}
	return result;
}

	/**
	 * 滚动数组
	 * @param costs
	 * @return
	 */
	public int minCostGArray(int[][] costs){
		if(costs.length == 0) return 0;
		int n = costs.length;
		int k = costs[0].length;
		int result = Integer.MAX_VALUE;
		//另外开一个维度的数组 来保存颜色
		//前i个房子 只和 前i-1有关,相关的只有 2维没有必要开个 n+1的维度,每次做个交替
		//int[][] dp = new int[n+1][k];
		int[][] dp = new int[2][k];
		//对他进行初始化
		for(int i=0;i<k;i++){
			dp[0][i] =0;
			dp[1][i] =costs[0][i];
		}
        int m=1;
		for(int i=2;i<=n ;i++){
			m =1-m;
			for(int j=0;j<k;j++){
				int min =Integer.MAX_VALUE;
				for(int x=0;x <k;x++){
					if(j!=x){
						min =Math.min(min,costs[i-1][j]+dp[1-m][x]);
					}
				}
				dp[m][j] =min;
			}
		}

		for(int i=0;i<k;i++){
			result =Math.min(result,dp[m][i]);
		}
		return result;
	}

	/**
	 * 划分型,动态规划
	 *
	 * 132 分割回文串
	 *
	 * 回文串是一个正读和反读都一样的字符串
	 *
	 * aab
	 * [aa,b]
	 *
	 * 状态变量:dp[i]表示 前i个字符最少可以划分成回文串个数
	 *
	 * 转移方程:dp[i] =min(j=0....i-1)(dp[j]+1|substring(j,i-1)) 是回文串
	 *
	 *
	 * 初始状态
	 * dp[0] =0
	 */

	public int minCut(String s){
		int len = s.length();
		/**
		 * 从 i到 j的子串是不是 回文串,是的话返回
		 */
		boolean[][] palin = allPalins(s,len);
        int[] dp = new int[len+1];
        dp[0]=0;
        for(int i=1;i<=len;i++){
        	dp[i] =Integer.MAX_VALUE;
        	for(int j=0;j<i;j++){
        		//判断是否是 回文串
        		if(palin[j][i-1]){
        			dp[i] = Math.min(dp[i],dp[j]+1);
				}
			}

		}
		return dp[len]-1;

	}

	/**
	 * 找出 所有回文串的子串
	 */

	public boolean[][] allPalins(String s,int len){
      boolean[][] palins = new boolean[len][len];
      int i,j,mid;
      for(mid =0;mid <len;mid++){
      	i=j=mid;
      	while(i>=0 && j<len&& s.charAt(i) ==s.charAt(j)){
      		palins[i][j] =true;
      		i--;
      		j++;
		}
      	i=mid -1;j=mid;
      	while(i>=0 && j<len&& s.charAt(i)==s.charAt(j)){
      		palins[i][j]=true;
      		i--;
      		j++;
		}
	  }
      return palins;
	}

	/**
	 * 博弈型  区间型 动态规划
	 * 486 预测赢家
	 * [1,5,2]
	 *
	 * False
	 *
	 * 1+2=3 <5
	 *
	 * [1,5,233,7]
	 *
	 * 求出 所有的组合中 玩家1可以拿到的最大值,并且和数组和一半的关系
	 *
	 * 状态变量 dp[i][j] 玩家在i到j能拿到的最大分组
	 *
	 * 转移方程
	 *
	 * dp[i] =max(left,right)
	 * left = min(dp[i+1][j-1],dp[j+2][j]+nums[i])
	 *
	 * right = min(dp[i][j-2],dp[i+1][j-1])+nums[j]
	 *
	 * 初始状态
	 *
	 * dp[i][i] = nums[i] (i=0...number)
	 */

	public boolean predictTheWinner(int[] nums){
		int[][] dp = new int[nums.length][nums.length];
		for(int i=0;i<nums.length;i++){
			dp[i][i] =nums[i];
		}

		for(int j=0;j<nums.length-1;j++){
			dp[j][j+1] =Math.max(nums[j],nums[j+1]);
		}
		int sum=0;
		for(int i=0;i<nums.length;i++){
			sum +=nums[i];
		}
		for(int l=3;l<=nums.length;l++){
			for(int i=0;i+l-1<nums.length;i++){
				int j=i+l-1;
				int left = Math.min(dp[i+1][j-1],dp[i+2][j])+nums[i];
				int right = Math.min(dp[i][j-2],dp[i+1][j-1]) +nums[j];
				dp[i][j]=Math.max(left,right);
			}
		}

		return 2*dp[0][nums.length-1] >= sum;
	}

	/**
	 * 背包问题
	 *
	 * 416 分割等和子集
	 *
	 * [1,5,11,5]
	 * true
	 *
	 * 数组可以分割成[1,5,5]和[11]
	 *
	 * 放入背包和 不放入背包
	 *
	 * 数组下标 拼出来的 容积,数组长度 是 背包容积+1
	 *
	 * 分割等和自己
	 *
	 * 状态变量
	 *  dp[i]  是否可以拼出 和为 i
	 *
	 *  转移方程  dp[i]=dp[i]||dp[i-nums[j-1]]|i>=nums[j-1]
	 *
	 * 初始状态
	 * dp[0]=true
	 *
	 * 完全没听懂
	 */

	public boolean canPartition(int[] nums){
		int n=nums.length;
		int sum=0;
		for(int i=0;i<n;i++){
			sum+=nums[i];
		}
		if(sum%2!=0)return false;
		//作为背包的容积
		sum =sum/2;

		boolean dp[] =new boolean[sum+1];
		dp[0] = true;
		for(int num:nums){
			for(int j=sum;j>=num;j--){
				if(dp[j-num]) dp[j]=true;
			}
		}
		return dp[sum];
	}



}


