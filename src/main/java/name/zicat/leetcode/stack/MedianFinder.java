package name.zicat.leetcode.stack;

import name.zicat.leetcode.queue.PriorityQueueTest;

/**
 * 295 数据流的中位数
 *
 * 有序列表
 *
 * 优先队列 维护最大值的优先队列,维护最小值的优先队列
 *
 * 奇数 拿 左队列的 第一个数,  偶数 拿 左右的数 做个平方
 *
 * addNum
 *
 * findMedian
 */
public class MedianFinder {

	/**
	 * 当前大顶堆和小顶堆的元素个数之和
	 */
	private int count;
	private PriorityQueueTest<Integer> maxheap;
	private PriorityQueueTest<Integer> minheap;

	/**
	 * initialize your data structure here.
	 */
	public MedianFinder() {
		count = 0;
		maxheap = new PriorityQueueTest<>((x, y) -> y - x);
		minheap = new PriorityQueueTest<>();
	}

	public void addNum(int num) {
		count += 1;
		maxheap.add(num);
		minheap.add(maxheap.poll());
		// 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
		if ((count & 1) != 0) {
			maxheap.add(minheap.poll());
		}
	}

	public double findMedian() {
		if ((count & 1) == 0) {
			// 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
			return (double) (maxheap.peek() + minheap.peek()) / 2;
		} else {
			// 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
			return (double) maxheap.peek();
		}
	}

}
