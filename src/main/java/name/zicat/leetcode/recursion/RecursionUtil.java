package name.zicat.leetcode.recursion;

import name.zicat.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author jessica
 */
public class RecursionUtil {
	/**
	 * 递归模板
	 *
	 * 写递归的 要点  leetcode 国际站 most votes看一遍
	 *
	 *
	 * public void recur(int level,int param){
	 *  //terminator
	 *  if(level > MAX_LEVEL){
	 *     //process result
	 *     return;
	 *     }
	 *  //process current logic
	 *  process(level,param);
	 *
	 *  //drill down
	 *  recur(level,level+1,newParam)
	 *
	 *
	 *  //restore current status
	 *
	 * }
	 *
	 */

	/**
	 * 递归 找重复性
	 *
	 */

	/**
	 * 爬楼梯  leetcode 70
	 */
	public static void climbStairs(int n){

	}

	public static void main(String[] args) {
		generateParenthesis(3);
	}


	/**
	 * 括号生成 leetcode 22
	 * 输入：n = 3
	 * 输出：[
	 *        "((()))",
	 *        "(()())",
	 *        "(())()",
	 *        "()(())",
	 *        "()()()"
	 *      ]
	 */
	public static List<String> generateParenthesis(int n){
		//_generate(0,2 * n,"");

		_generate(0,0,2* n,"");

		return null;

	}

	private static void _generate(int level, int max, String s) {
		//terminator
		if(level >=max){
			//filter s is invalid  只能 有 n个 左括号 ,right 必须出现 左括号的后面

			System.out.println("s = " + s);
			return;
		}

		//process

		String s1= s + "(";
		String s2= s + ")";

		//drill down

		_generate(level+1,max,s1);
		_generate(level+1,max,s2);

		//reverse states


	}

	private static void _generate(int left, int right,int n, String s) {
		//terminator
		if(left == n && right == n){
			//filter s is invalid  只能 有 n个 左括号 ,right 必须出现 左括号的后面

			System.out.println("s = " + s);
			return;
		}

		//process

		String s1= s + "(";
		String s2= s + ")";

		//drill down
           if(left < n) {
			   _generate(left + 1, right,n, s1);
		   }
           if(left > right) {
			   _generate(left, right + 1, n, s2);
		   }


		//reverse states


	}

	/**
	 *  斐波那契数列  非递归法
	 * @param n
	 * @return
	 */
	public static int fib(int n){
         int first =0;
         int second =1;
         while (n -->0){
         	int temp = first + second;
         	first = second;

         	second = temp;
		 }

         return first;

	}

	/**
	 * 140. 单词拆分
	 * 记忆化搜索
	 * 给定一个非空字符串 s和一个包含非空单词列表的字典
	 *
	 * s ="catsanddog"
	 *
	 * wordDict = ["cat","cats","and","sand","dog"]
	 *
	 * wordDict = ["cats and dog","cat sand dog"]
	 *
	 * wordBreak("catsanddog") =
	 * "cat" + "" + wordBreak("sanddog") -> "cat sand" + "" +wordBreak("dog");
	 * 或者 "cats" + "" + wordBreak("anddog") -> "cats and" + "" + wordBreak("dog");
	 *
	 * 注意 "dog" 已经被检测过,不用重复计算,我们可以把它的结果存起来
	 * 记忆化搜索, 中间结果保存在 数据结构中,遇到重复的不需要再计算

	 */

	public List<String> wordBreak(String s,List<String> wordDict){
		return helper(s, wordDict, new HashMap<String, ArrayList<String>>());
	}

	//返回所有s对应的满足条件的结果
	// s 为子串 比如 sanddog
	private List<String> helper(String s, List<String> wordDict, HashMap<String, ArrayList<String>> prevRes) {
	  //记忆化搜索的精髓,不进行重复计算
		if(prevRes.containsKey(s)){
			return prevRes.get(s);
		}

		ArrayList<String> result = new ArrayList<>();
		//容易错,不要忘记base case  定义基础解
		if(s.length() ==0){
			result.add("");
			return result;
		}

		for(String word:wordDict){
			if(s.startsWith(word)){
				//递归调用剩下的部分
				List<String> subRes = helper(s.substring(word.length()),wordDict,prevRes);
				//append 到后面
				for(String sub:subRes){
					result.add(word + (sub.isEmpty()?"": " ") + sub);
				}
			}
		}
		prevRes.put(s,result);
		return result;
	}
	/**
	 * 687.最长同值路径
	 * 给定一个二叉树 找到最长的路径,这个路径每个节点具有相同的值
	 * 递推方程 : helper(root) 表示以root为顶点的最大同值边长
	 * 基础值: root ==null，返回 0
	 * 每次function call 记录全局最大路径
	 * 
	 * 时间 O(n) n= number of tree nodes
	 */
	int ans =0;
	public int longestUnivaluePath(TreeNode root){
		if(root ==null){
			return 0;
		}
		helperTree(root);
		return ans;
	}

	private int helperTree(TreeNode root) {
		//terminal
		if(root ==null){
			return 0;
		}
		int left = helperTree(root.left);
		int	right = helperTree(root.right);
		int pleft =0;
		int pright =0;
		//左子树的最大边长+1
		if(root.left!=null&& root.val==root.left.val) pleft =left+1;
		//右子树的最大边长+1
		if(root.right!=null && root.val==root.right.val) pright =right +1;
		//
		this.ans = Math.max(this.ans,pleft+pright);
		//左右中的最大值
		return Math.max(pleft,pright);

	}

	/**
	 * 783 二叉搜索树 结点最小距离
	 *
	 *
	 */
	Integer prev,ans1;
	public int minDiffInBST(TreeNode root){
		ans = Integer.MAX_VALUE;
		prev = null;
		inOrder(root);
		return ans1;
	}
	//一定是升序的
	public void inOrder(TreeNode node){
		if(node==null) return;
		//先处理左子树
		inOrder(node.left);
		//处理当前的节点
		if(prev!=null){
			ans1 = Math.min(ans,node.val-prev);
		}
		prev =node.val;
		//再处理 右子树
		inOrder(node.right);
	}

	/**
	 * 1137 第N个 泰波那契数
	 * n=4
	 * 输出4
	 * T_3 =0 +1 +1 =2
	 * T_4 = 1+ 1+ 2 =4
	 *
	 */
	HashMap<Integer,Integer> cache = new HashMap<>();
	public int tribonacci(int n){
		if(cache.containsKey(n)){
			return cache.get(n);
		}
		if(n ==0){
			cache.put(n,0);
			return 0;
		}
		if(n==1 || n==2){
			cache.put(n,1);
			return 1;
		}
		int result = tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
		cache.put(n,result);
		return result;

	}


}
