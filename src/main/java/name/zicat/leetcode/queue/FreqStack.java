package name.zicat.leetcode.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *实现 FreqStack，模拟类似栈的数据结构的操作的一个类。
 *
 * FreqStack 有两个函数：
 *
 * push(int x)，将整数 x 推入栈中。
 * pop()，它移除并返回栈中出现最频繁的元素。
 * 如果最频繁的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 */
public class FreqStack {
	/**
	 * 时间复杂度：对于 push 和 pop 操作，O(1)O(1)。
	 * 空间复杂度：O(N)O(N)，其中 N 为 FreqStack 中元素的数目。
	 */




	/**
	 *  key: 3,value :频率
	 */
	Map<Integer,Integer> freq;
	/**
	 * key: 频率 ,value是 Stack 里面存 相同频率的 key的信息,靠近 栈顶的元素 相对更新一点
	 */
	Map<Integer, Stack<Integer>> group;
	int maxfreq;

	public FreqStack(){
		freq = new HashMap<>();
		group = new HashMap<>();
		maxfreq =0;
	}


	public void push(int x ){
	int f = freq.getOrDefault(x,0) +1;
	freq.put(x, f);
	if(f > maxfreq){
	  maxfreq = f;
	}

	group.computeIfAbsent(f, z->new Stack<>()).push(x);
	}

	public int pop(){
		int x = group.get(maxfreq).pop();
		freq.put(x, freq.get(x) -1);
		if(group.get(maxfreq).size()==0){
			maxfreq--;
		}
		return x;
	}

	}
