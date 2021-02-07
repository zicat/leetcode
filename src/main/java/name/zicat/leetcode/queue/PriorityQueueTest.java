package name.zicat.leetcode.queue;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 优先队列
 *
 * 1.元素被赋予优先级,优先级高的元素先被访问
 *
 * 2.int 类型弄人 最大值优先级高
 *
 * 3.可以自定义优先级
 *
 * 4.基于堆实现的优先队列的插入复杂度是O(logN)
 *
 * 堆是个 二叉堆 是一个完全二叉树
 *
 * 根节点总是大于左右子节点(大顶堆), 或者是小于左右子节点(小顶堆)
 *
 * 保证每次取出的元素都是队列中权值最小的，这里涉及到了大小关系，元素大小的评判可以通过元素自身的自然顺序（使用默认的比较器），也可以通过构造时传入的比较器
 *
 * leftNo = parentNo*2+1
 *
 * rightNo = parentNo*2+2
 *
 * parentNo = (nodeNo-1)/2
 */
public class PriorityQueueTest<T> {

	/**
	 * 默认初始容量
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 11;


	/**
	 * 维护一个队列,因为基于二叉堆来实现优先队列,queue[i] 的子节点为 queue[2*i +1]/queue[2*i +2]
	 */
	transient  Object[] queue;

	/**
	 * 优先级队列中的 数据条数
	 */
	private int size =0;

	/**
	 *
	 * @param data
	 */

	private final Comparator< ? super T> comparator;


	/**
	 * 被修改的次数
	 */
	transient  int modCount =0;

	/**
	 * The maximum size of array to allocate.
	 * Some VMs reserve some header words in an array.
	 * Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	public PriorityQueueTest(Comparator<? super T> comparator){
		this.queue = new Object[DEFAULT_INITIAL_CAPACITY];
		this.comparator = comparator;
	}

	public PriorityQueueTest(){
		this.comparator = null;
	}


	public boolean add(T data) {
		if(data ==null)
			throw new NullPointerException();
		modCount ++;
		int i = size;
		if(i>=queue.length)
			grow(i+1);
		size = i+1;
		if( i ==0)
			queue[0] = data;
		else
			siftUp(i,data);
		return true;
	}

	/**
	 * Inserts item x at position k, maintaining heap invariant by
	 * promoting x up the tree until it is greater than or equal to
	 * its parent, or is the root.
	 *
	 * To simplify and speed up coercions and comparisons. the
	 * Comparable and Comparator versions are separated into different
	 * methods that are otherwise identical. (Similarly for siftDown.)
	 *
	 * @param k the position to fill
	 * @param x the item to insert
	 */
	private void siftUp(int k, T x) {
			siftUpUsingComparator(k, x);
	}

	private void siftUpUsingComparator(int k, T x) {

		/**
		 * >>>表示不带符号向右移动二进制数，移动后前面统统补0；两个箭头表示带符号移动，
		 *
		 * 没有<<<这种运算符，因为左移都是补零，没有正负数的区别。
		 *
		 * 如 -12 的二进制为：1111  1111  1111  1111  1111  1111  1111  0100；
		 *
		 * -12 >> 3 即带符号右移3位，结果是：1111  1111  1111  1111  1111  1111  1111  1110，十进制为： -2；
		 *
		 * -12 >>> 3 就是右移三位，前面补零，为：0001  1111  1111  1111  1111  1111  1111  1110，十进制为：536870910
		 *
		 * 插入的数据 可能影响 大顶堆或者小顶堆,需要和 parent进行一个交换 保证顶点是最小的
		 */
		while (k > 0) {
			//先找到其parent的编号
			int parent = (k - 1) >>> 1;
			Object e = queue[parent];
			if (comparator.compare(x, (T) e) >= 0)
				break;
			queue[k] = e;
			k = parent;
		}
		queue[k] = x;

	}

	private void grow(int minCapacity) {
		int oldCapacity = queue.length;
		// Double size if small; else grow by 50%
		int newCapacity = oldCapacity + ((oldCapacity < 64) ?
				(oldCapacity + 2) :
				(oldCapacity >> 1));
		// overflow-conscious code
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		queue = Arrays.copyOf(queue, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
				MAX_ARRAY_SIZE;
	}

	public T  peek(){

		return (size == 0) ? null : (T) queue[0];
	}

	public T poll(){
		if (size == 0)
			return null;
		int s = --size;
		modCount++;
		T result = (T) queue[0];
		T x = (T) queue[s];
		queue[s] = null;
		if (s != 0)
			siftDown(0, x);
		return result;
	}

	public int size(){
		return size;
	}

	/**
	 * Removes a single instance of the specified element from this queue,
	 * if it is present.  More formally, removes an element {@code e} such
	 * that {@code o.equals(e)}, if this queue contains one or more such
	 * elements.  Returns {@code true} if and only if this queue contained
	 * the specified element (or equivalently, if this queue changed as a
	 * result of the call).
	 *
	 * @param o element to be removed from this queue, if present
	 * @return {@code true} if this queue changed as a result of the call
	 */
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (i == -1)
			return false;
		else {
			removeAt(i);
			return true;
		}
	}

	private int indexOf(Object o) {
		if (o != null) {
			for (int i = 0; i < size; i++)
				if (o.equals(queue[i]))
					return i;
		}
		return -1;
	}

	/**
	 * Removes the ith element from queue.
	 *
	 * Normally this method leaves the elements at up to i-1,
	 * inclusive, untouched.  Under these circumstances, it returns
	 * null.  Occasionally, in order to maintain the heap invariant,
	 * it must swap a later element of the list with one earlier than
	 * i.  Under these circumstances, this method returns the element
	 * that was previously at the end of the list and is now at some
	 * position before i. This fact is used by iterator.remove so as to
	 * avoid missing traversing elements.
	 */
	@SuppressWarnings("unchecked")
	private T removeAt(int i) {
		// assert i >= 0 && i < size;
		modCount++;
		int s = --size;
		if (s == i) // removed last element
			queue[i] = null;
		else {
			T moved = (T) queue[s];
			queue[s] = null;
			siftDown(i, moved);
			if (queue[i] == moved) {
				siftUp(i, moved);
				if (queue[i] != moved)
					return moved;
			}
		}
		return null;
	}

	private void siftDown(int k, T x) {
		int half = size >>> 1;
		while (k < half) {
			int child = (k << 1) + 1;
			Object c = queue[child];
			int right = child + 1;
			if (right < size &&
					comparator.compare((T) c, (T) queue[right]) > 0)
				c = queue[child = right];
			if (comparator.compare(x, (T) c) <= 0)
				break;
			queue[k] = c;
			k = child;
		}
		queue[k] = x;
	}


}
