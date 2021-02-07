package name.zicat.leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 前缀树
 *
 * 又成字典树,Trie
 * Search
 * 用来存储 查找字符串,特别适合字符串前缀查找
 *
 * 字段串的公共前缀只保存一次,节省空间
 *
 * 存储字符串 组成的字典,对字符串进行前缀查找,实现剪枝的时间优化
 */
public class Trie {
	private TrieNode root;
	String[] words;

	public Trie(){
		root = new TrieNode();
	}
	/**
	 * root为 /
	 *
	 *       /
	 *     H F A
	 *    E  R  I
	 *   L   E   R
	 * P  L  E
	 *     O
	 */


	public void insert(String word,int index){
		HashMap<Character,TrieNode> child = root.children;
		//对word的字母进行循环
		for(int i=0; i< word.length();i++){

			char c = word.charAt(i);
			TrieNode next;
			//是否在子节点中
			if(child.containsKey(c)){
				//拿出来用来插入 下一个字母
				next = child.get(c);
			} else {
				//新建一个 trieNode
				next = new TrieNode(c);
				next.end = index;
				//更新子节点
				child.put(c,next);
			}

			child = next.children;

			if(i == (word.length() -1)){
				//查找的时候就知道是 前缀还是 完整的字符串
				next.isWord =true;
			}
		}

	}

	/**
	 * 查找和前缀查找,看 isWord是 true还是 false
	 * @return
	 */
	public boolean search(String word){
      TrieNode t = searchNode(word);
      if(t!=null && t.isWord){
      	return true;
	  }
      return false;
	}

	public boolean startsWith(String prefix){
      if(searchNode(prefix) == null){
      	return false;
	  }
      return true;
	}

	public TrieNode searchNode(String str){
		Map<Character,TrieNode> children = root.children;
		TrieNode cur =null;
		for(int i=0;i < str.length();i++){
			char c = str.charAt(i);
			if(children.containsKey(c)){
				cur = children.get(c);
				children = cur.children;
			} else {
				return null;
			}
		}
		return cur;

	}
	public String dfs() {
		String ans = "";
		Stack<TrieNode> stack = new Stack();
		stack.push(root);
		while (!stack.empty()) {
			TrieNode node = stack.pop();
			if (node.end > 0 || node == root) {
				if (node != root) {
					String word = words[node.end - 1];
					if (word.length() > ans.length() ||
							word.length() == ans.length() && word.compareTo(ans) < 0) {
						ans = word;
					}
				}
				for (TrieNode nei: node.children.values()) {
					stack.push(nei);
				}
			}
		}
		return ans;
	}

}

class TrieNode {
	char c;
	HashMap<Character,TrieNode> children = new HashMap<>();
	int end;
	/**
	 * 标注节点是不是 字符串的结尾
	 */
	public boolean isWord = false;
	public TrieNode(char c) {
		this.c =c;
	}

	public TrieNode(){

	}

}
