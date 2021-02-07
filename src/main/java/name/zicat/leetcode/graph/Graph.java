package name.zicat.leetcode.graph;

import java.util.Arrays;

/**
 * 1.图的定义
 * 图是由顶点的有穷非空集合和顶点之间的边的集合组成,
 * 通常表示为G(V,E),其中G表示一个图,
 * V表示图中顶点的集合,E是图中边的集合
 *
 * 无向图:顶点之间边没有方向
 * 有向图:顶点之间边有方向
 * 权:图中边相关的数据,用来表示从一个顶点到另一个顶点的距离或者耗时
 *
 * 2.树是一种特殊的图 树是没有环的图
 *
 * 3.图的深度优先和广度优先遍历
 *
 * 4.并查集在图中的应用
 *
 * 5.拓扑排序
 */
public class Graph {
	/**
	 * 785.判断二分图
	 * 邻接表
	 * graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值
	 *  输入:[[1,3],[0,2],[1,3],[0,2]]
	 *  0 [1,3]  与节点0 相邻的节点是 1和3
	 *  1 [0,2]  与节点1 相邻的节点是 0和2
	 *  2 [1,3]  与节点2 相邻的节点是 1和3
	 *  3 [0,2]  与节点3 相邻的节点是 0和2
	 *  0 -- 1
	 *  |    |
	 *  |    |
	 *  3 -- 2
	 *  输出: true
	 *
	 *  可以将节点分成两组 {0,2}和{1,3}
	 *  同理
	 *  输入:[[1,2,3],[0,2],[0,1,3],[0,2]]
	 *  0 —— 1
	 *  | \  |
	 *  3 —— 2
	 *  输出: false
	 *
	 *  方法:相邻的节点 的颜色不一样
	 *   时间复杂度是 O(N+E)
	 *
	 *
	 */

	public boolean isBipartite(int[][] graph){
		/**
		 * 表示 相邻节点的颜色,初始值为0,表示没有着色
		 */
		int[] colors = new int[graph.length];
		//1和 -1表示颜色分组
		//O(N)
		for(int i=0;i<colors.length;i++){
           if(colors[i] ==0 && !dfs(graph,i,colors,1))
           	return false;
		}
       return true;
	}

	/**
	 * 深度优先遍历的实现,通过recursion的方法
	 *
	 * @param graph
	 * @param cur
	 * @param colors
	 * @param color
	 * @return
	 */
	public boolean dfs(int[][] graph,int cur, int[] colors,int color){
    //给节点进行 着色
		colors[cur] = color;
		/**
		 * 遍历 当前节点的相邻节点
		 * O(E)
		 */
		for(int next:graph[cur]){
			//如果遇到相邻节点的颜色相同,返回 false;
			if(colors[next] ==color){
				return false;
			}
			//如果下一个节点没有着色,进行递归着色查找
			//并用 -1作为相邻节点的颜色
			if(colors[next] ==0&& !dfs(graph,next,colors,-color)){
				return false;
			}
		}
      return true;
	}

	/**
	 * 并查集
	 * 应用:看图中有没有环
	 * 合并集合和 查询集合
	 * 合并是把所有的父节点连接起来
	 *
	 * 查询是 在集合中 查找 是否是同一个根节点,如果是的话,就会形成环
	 *
	 * 每个节点 给parent -1
	 * 对边进行遍历
	 * 0->1 边  1 选择parent 为0
	 * 2->4  4 选择 parent 为2
	 * 如果 1——2 因为在两个集合里面,需要合并 1-2这两个集合
	 * 1-2  需要把父节点都 连接起来, 0，2中选择 一个 作为 parent
	 * 那0作为 parent
	 * 1——>4 这个边  查找 是不是同一个 parent,他们的parent的都是0
	 * 如果 再加这个边,那么一定会 形成环
	 */

	/**
	 * 261.以图判树
	 *
	 * 树:
	 * 不包含环
	 * 树所有顶点是联通的,所以边数是 顶点数-1
	 *
	 * 输入 n=5,边列表 edges =[[0,1],[0,2],[0,3],[1,4]]
	 * 输出 true
	 *
	 * 输入 n=5 edges =[[0,1],[1,2],[2,3],[1,3],[1,4]]
	 * 输出 false
	 */

	public boolean validTree(int n,int[][] edges){
		/**
		 * 用来保存 每个点集合的代表点
		 */
		int[] parent = new int[n];
		Arrays.fill(parent,-1);
		for(int[] edge:edges){
			int x = edge[0];
			int y = edge[1];
			//查找 这个集合代表是不是相等的
			x = find(parent,x);
			y = find(parent,y);
			if(x == y){
				return false;
			}
			/**
			 * 不相等的话就是个 union操作
			 */
			parent[x]=y;
		}

     /**
      * 树 的第二个条件 边的 数量 等于节点数-1
      */
     return edges.length == n-1;
	}

	public int find(int[] parent, int i){
		/**
		 * 如果自己是 代表节点 返回 i
		 */
		if(parent[i] == -1)
			return i;
		/**
		 * 查找他的 parent + 路径压缩的操作
		 */
		return parent[i] = find(parent,parent[i]);
	}




}
