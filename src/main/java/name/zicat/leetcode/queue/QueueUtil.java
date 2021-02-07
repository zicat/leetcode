package name.zicat.leetcode.queue;

import name.zicat.leetcode.array.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class QueueUtil {
	/**
	 * 239.滑动窗口最大值 (hard)
	 *
	 * 滑动窗口
	 *
	 * 双端队列
	 * Deque 中 队首 永远都是 滑动窗口的最大值
	 * [1,3,-1,-3,5,3]
	 * [1] Deque 1 Result
	 * [1,3] Deque 3 Result
	 * [1,3,-1] Deque 3 -1 Result 3(Deque)
	 * [1,3,-1,-3] Deque 3 -1 -3 Result 3 3
	 * [1,3,-1,-3 5] Deque 5 (3,-1,-3出队并且滑动窗口的size大于k) Result 3 3 5
	 * [1,3,-1,-3 5 3] Deque 5 3 Result 3 3 5 5
	 *
	 */

	public int[] maxSlidingWindow(int[] nums,int k){
		if(nums==null || nums.length ==0){
			return new int[0];
		}
		int[] result = new int[nums.length-k+1];
		/**
		 * 存放 最大值的索引
		 */
		LinkedList<Integer> deque = new LinkedList<Integer>();
		for(int i=0;i<nums.length;i++){
			//滑动窗口的size >k的情况
			if(!deque.isEmpty() && deque.peekFirst()==i-k){
				//需要队首移除 元素
				deque.poll();
			}
			/**
			 * 新入栈的元素大于 队尾的元素的话,把元素移除,保证队首是最大值
			 */
			while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
				deque.removeLast();
			}
			deque.offer(i);
			/**
			 * 从k-1索引位开始统计
			 */
			if(i+1>=k){
				result[i+1-k] = nums[deque.peek()];
			}
		}
		return result;
	}
	/**
	 * 23.合并k个排序链表
	 * 用优先队列  优先队列需要重新实现一遍
	 *
	 */

	public ListNode mergeKList(ListNode[] lists){
		// 边界情况

		if(lists==null|| lists.length==0){
			return null;
		}

		PriorityQueueTest<ListNode> queue = new PriorityQueueTest<>();
		//定义带头人
		ListNode fakeHead = new ListNode(0);
		ListNode p = fakeHead;
		//遍历排好序的lists 的首节点,放在队列中
		for(ListNode list:lists){
			if(list!=null){
				queue.add(list);
			}
		}
		//
		while(queue.size() >0){
			ListNode n = queue.poll();
			p.next = n;
			//最小节点的下一个节点入队
			if(n.next!=null){
				queue.add(n.next);
			}
		}

		return fakeHead.next;

	}

	/**
	 * 时间复杂度：考虑优先队列中的元素不超过 kk 个，那么插入和删除的时间代价为 O(\log k)O(logk)，这里最多有 knkn 个点，对于每个点都被插入删除各一次，故总的时间代价即渐进时间复杂度为 O(kn \times \log k)O(kn×logk)。
	 * 空间复杂度：这里用了优先队列，优先队列中的元素不超过 kk 个，故渐进空间复杂度为 O(k)O(k)
	 *
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val) return -1;
				else if (o1.val == o2.val) return 0;
				else return 1;
			}
		});
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;
		for (ListNode node : lists) {
			if (node != null) queue.add(node);
		}
		while (!queue.isEmpty()) {
			p.next = queue.poll();
			p = p.next;
			if (p.next != null) queue.add(p.next);
		}
		return dummy.next;

	}

	/**
	 * 621.任务调度器
	 *
	 * 在选择每一轮中的任务时，我们也可以用优先队列（堆）来代替排序。在一开始，我们把所有的任务加入到优先队列中。在每一轮，我们从优先队列中选择最多 n + 1 个任务，把它们的数量减去 1，再放回堆中（如果数量不为 0），直到堆为空。
	 *  输入：tasks = ["A","A","A","B","B","B"], n = 2
	 * 输出：8
	 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
	 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
	 *
	 */

	public static int leastInterval(char[] tasks,int n) {
		int[] map = new int[26];
		for (char c: tasks)
			map[c - 'A']++;
		PriorityQueue < Integer > queue = new PriorityQueue <> (26, Collections.reverseOrder());
		for (int f: map) {
			if (f > 0)
				queue.add(f);
		}
		int time = 0;
		while (!queue.isEmpty()) {
			int i = 0;
			List< Integer > temp = new ArrayList< >();
			while (i <= n) {
				if (!queue.isEmpty()) {
					if (queue.peek() > 1)
						temp.add(queue.poll() - 1);
					else
						queue.poll();
				}
				time++;
				if (queue.isEmpty() && temp.size() == 0)
					break;
				i++;
			}
			for (int l: temp)
				queue.add(l);
		}
		return time;

	}





}
