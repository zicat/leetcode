package name.zicat.leetcode.tree;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jessica
 * Date 2020/7/29
 */
public class TreeUtils {
	/**
	 * BST 中序遍历是有序的
	 */

	/**
	 * https://visualgo.net/zh/bst
	 * 前序遍历  中左右
	 * def preorder(self,root)
	 * if root:
	 * self.traverse_path.append(root.val)
	 * self.preorder(root.left)
	 * self.preorder(root.right)
	 *
	 * 中序遍历  左中右
	 *
	 * def inorder(self,root):
	 * if root
	 * self.inorder(root.left)
	 * self.traverse_path append(root.val)
	 * self inorder(root.right)
	 *
	 * 后序遍历
	 *
	 * def postorder(self,root)
	 * if root:
	 * self.postorder(root.left)
	 * self.postorder(root.right)
	 * self.traverse_path append(root.val)
	 */

	/**
	 *  二叉树 vs 二叉搜索树
	 *
	 *  二叉搜索树 是 二叉树的一种
	 *
	 *  二叉搜索树节点大小规律:所有左边的节点<=node.value <=所有右边的节点
	 *  如果左子树不为空,那么其上的所有节点值都小于根节点的值
	 *  如果右子树不为空,那么其上所有节点值都大于跟节点的值
	 *  左右子树也分别为二叉查找树
	 *   8  二叉搜索树      8  二叉树
	 *  / \               /\
	 * 4  11             4  11
	 * /\  \            /\   \
	 *2 6  18          2 20  18
	 *平衡二叉树的查找效率是O(logN),类似于二分法
	 * 完全不平衡二叉树的查找效率是O(N)
	 */


	/**
	 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal
	 * input [1,null,2,3]
	 *
	 */
	public static int  inOrderTraversal(TreeNode root){

       return 0;
	}

	/**
	 * 297 二叉树的序列化与反序列化
	 * 示例
	 *  1
	 * /\
	 *2 3
	 *  /\
	 * 4 5
	 *
	 * 序列化为[1,2,3,null,null,4,5] null为了 在反序列的时候可以重新生成树
	 *
	 * 深度优先和广度优先遍历
	 *
	 * 深度优先遍历
	 * 先遍历根结点,再遍历左子树,最后再遍历右子树
	 *
	 * 广度优先遍历
	 * 需要靠队列
	 * 根节点入队 出队 跟节点 左右子节点分别入队
	 *
	 * 出队 左节点, 入队 左节点的子左右节点
	 *
	 *
	 */

	/**
	 * 深度遍历 递归,时间复杂度 O(N)
	 */

	public String serialize(TreeNode root){
		return serializeHelper(root,"");
	}

	/**
	 * 序列化 时间复杂度  遍历了 所有的节点 是 O(N)
	 * 空间复杂度 O(N)
	 * @param root
	 * @param str
	 * @return
	 */
	private String serializeHelper(TreeNode root,String str) {
		//递归处理
		if(root ==null){
			str +="null,";
		} else {
			//前序遍历
			str += root.val + ",";
			str = serializeHelper(root.left,str);
			str = serializeHelper(root.right,str);
		}
		return str;
	}

	//反序列化
	public TreeNode deserialize(String data){
		String[] data_array = data.split(",");
		List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
		return deserializeHelper(data_list);
	}

	private TreeNode deserializeHelper(List<String> data_list) {
		//递归处理,从字符串首元素开始处理
		if(data_list.get(0).equals("null")) {
			data_list.remove(0);
			return null;
		}
		//不是null建立新的 treenode
		TreeNode root = new TreeNode(Integer.valueOf(data_list.get(0)));
		data_list.remove(0);
		//递归处理先反序列化左子树,再反序列化右子树
		root.left = deserializeHelper(data_list);
		root.right = deserializeHelper(data_list);
		return root;
	}

	/**
	 * 广度优先 遍历
	 */

	/**
	 * 广度优先遍历 序列化  时间复杂度 O(N)
	 * @param root
	 * @return
	 */
	public static String serializeG(TreeNode root){
		if(root ==null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		LinkedList<TreeNode> queue = new LinkedList<>();
		//队列里面加 跟节点
		queue.add(root);

		while(!queue.isEmpty()){
			//出queue的操作
			TreeNode t =queue.poll();
			if(t!=null){
				sb.append(t.val+",");
				//左右节点 加入 queue中
				queue.add(t.left);
				queue.add(t.right);
			} else {
				sb.append("#,");
			}
		}

		sb.deleteCharAt(sb.length() -1);
		return sb.toString();

	}
	/**
	 * 广度优先遍历 反序列化 时间复杂度 O(N)
	 */

	public static TreeNode deserializeG(String data){
		if(data==null || data.length()==0){
			return null;
		}
		String[] arr = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
		LinkedList<TreeNode> queue = new LinkedList<>();

		queue.add(root);
		//建立zhizhne
		int i=1;
		while(!queue.isEmpty()){
			TreeNode t =queue.poll();
			if(t ==null){
				continue;
			}
			/**
			 * 先还原 左子树
			 */
			if(!arr[i].equals("#")){
				t.left = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(t.left);
			} else {
				// # 号是 null value
				t.left = null;
				queue.offer(null);
			}
			i++;

			/**
			 * 再还原 右子树
			 */
			if(!arr[i].equals("#")){
				t.right = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(t.right);
			} else {
				t.right =null;
				queue.offer(null);
			}
			i++;
		}
		return root;

	}

	/**
	 * 450.删除二叉搜索树中的节点
	 * 给定一个二叉搜索树的根节点 root和一个值key，
	 * 删除二叉搜索树中的key对应的节点,并保证二叉搜索树的性质不变
	 * 返回二叉搜索树(会被更新)的根节点的引用
	 *
	 * 一般来说,删除节点可分为两个步骤:
	 *
	 * 首先找到需要删除的节点:
	 * 如果找到了,删除它
	 *
	 * 要求算法时间复杂度为O(h),h为树的高度
	 *
	 * root [5,3,6,2,4,null,7]
	 * key =3
	 *           5
	 *          /\
	 *         3  6
	 *        /\  \
	 *       2 4  7
	 *
	 *
	 * 结果是
	 *         5
	 *        /\
	 *       4 6
	 *      /   \
	 *     2    7
	 *
	 * [5,4,6,2,null,7]
	 *
	 * 思路:
	 * 如果 key > root.val 递归右子树,删除节点 root.right = deleteNode(root.right, key)
	 * 如果 key < root.val 递归左子树,删除节点 root.left = deleteNode(root.left,key)
	 *
	 * 如果  key == root.val
	 *  1.node 是叶子节点,直接删除: root =null
	 *  2.node 不是叶子节点,并且有 右子树 ,用前驱节点(successor) 代替该节点
	 *  后继节点是 右子树的最小值
	 *
	 *  3.node不是叶子节点 并且有 左子树 ,用(predecessor) 后继节点代替 该节点
	 * 前驱节点 是左子树的最小值
	 *
	 *
	 **/
	public TreeNode deleteNode(TreeNode root,int key){
		//递归终止条件
		if(root==null){
			return null;
		}
		if(key > root.val){
			//在右子树 查找 该节点
			root.right =deleteNode(root.right,key);
		} else if(key < root.val){
			//在左子树 查找该节点
			root.left = deleteNode(root.left,key);
		} else {
			//直接删除该节点
			if(root.left == null && root.right ==null){
				root =null;
			} else if(root.right!=null){
				//替换 右子树的最小值,再删除 该节点
				root.val = rightMin(root);
				root.right = deleteNode(root.right,root.val);
			} else if(root.left!=null){
				//替换 左子树的最大值,再删除该节点
				root.val = leftMax(root);
				root.left = deleteNode(root.left,root.val);
			}

		}
		return root;
	}

	/**
	 * 找到以某个结点为根节点的右子树的最小值
	 * @param root
	 * @return
	 */
	public int rightMin(TreeNode root){
		root = root.right;
		while(root.left!=null) root = root.left;
		return root.val;
	}

	/**
	 * 找到某个节点为根节点的 左子树的最大值
	 */
	public int leftMax(TreeNode root){
		root = root.left;
		while(root.right!=null) root= root.right;
		return root.val;
	}

	/**
	 * 951.翻转等价二叉树
	 *
	 * 树的子结构是 数,所以适合递归
	 *
	 *
	 */

	public boolean flipEquiv(TreeNode root1,TreeNode root2){

		if(root1==null && root2==null){
			return true;
		}
		if(root1==null||root2==null ||root1.val!=root2.val){
			return false;
		}
		/**
		 * 两种情况
		 */
		return (flipEquiv(root1.left,root2.left)&&flipEquiv(root1.right,root2.right)||
				flipEquiv(root1.left,root2.right)&& flipEquiv(root1.right,root2.left));
	}

	/**
	 * 1008 先序遍历
	 * Input: 8 5 1 7 10 12
	 *
	 * lowerBound: Integer.Min
	 * UpperBound: Integer.Max
	 * 值是不是在 [lowerBound,uppserBound]的范围内 是的话 upperBound为当前值
	 *  时间复杂度 O(N) 遍历数组
	 *  空间复杂度 O(N) 存储二叉树
	 **/

     int index =0;
     int[] preorder;
     int n;
	public TreeNode bstFromPreOrder (int[] preorder) {
		this.preorder = preorder;
		n = preorder.length;

       return helper(Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	/**
	 * 通过下限和上限来控制指针移动的范围
	 * @param lowerBound
	 * @return
	 */
	private TreeNode helper(int lowerBound,int upperBound){
       //所有元素都加到 二叉树中
		if(index == n)
		return null;

		int cur = preorder[index];

		if(cur < lowerBound || cur >upperBound) return null;
		index ++;
		TreeNode root = new TreeNode(cur);
		/**
		 * 递归组成 其做子树和右子树
		 */
		root.left = helper(lowerBound,cur);
		root.right = helper(cur,upperBound);
		return root;
	}
















}
