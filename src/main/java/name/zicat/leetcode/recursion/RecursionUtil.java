package name.zicat.leetcode.recursion;

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


}
