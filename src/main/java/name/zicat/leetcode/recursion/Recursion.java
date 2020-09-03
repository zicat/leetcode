package name.zicat.leetcode.recursion;

import name.zicat.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Recursion {

	/**
	 * 894.所有可能的满二叉树
	 *
	 * 满二叉树是一类二叉树,其中每个结点恰好有0或2个子结点
	 *  n 如果是 偶数 是不可能为 二叉树的
	 *
	 *  依次递归 左子树和右子树,判断 左右子树 是不是 单数
	 *
	 */

	public List<TreeNode> allPossibleFBT(int N){
		List<TreeNode> result = new ArrayList<>();
		if(N%2 == 0){
			return result;
		}
		//base case
		if(N ==1){
			TreeNode node = new TreeNode(0);
			result.add(node);
			return result;
		}
		//去掉 root
		N-=1;
		for(int i=1;i<N;i+=2){
			/**
			 * 罗列 左子树可能的节点数 1，3,5
			 * 右边就是 N-i
			 * 子问题的解
			 */
			List<TreeNode> left = allPossibleFBT(i);
			List<TreeNode> right = allPossibleFBT(N-i);
			/**
			 * 把左右所有可能的组合和 root相结合
			 */
			for(TreeNode lnode:left){
				for(TreeNode rnode:right) {
					TreeNode root = new TreeNode(0);
					root.left = lnode;
					root.right = rnode;
					//整体解加在 root中 再返回
					result.add(root);
				}
			}
		}
		return result;

	}
}
