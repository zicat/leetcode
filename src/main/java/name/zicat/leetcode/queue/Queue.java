package name.zicat.leetcode.queue;

/**
 * 先进先出的数据结构
 *
 * 从 尾部 追加,头部 取出
 */
public class Queue<T> {

	private static class QueueNode<T> {
		private T data;
		private QueueNode<T> next;

		public QueueNode(T data){this.data = data}
	}

	private QueueNode first;
	private QueueNode last;

	/**
	 * 从队首取出元素
	 * @return
	 */
	public T remove(){
		if(first == null)  throw EmptyQueueException();

		T value = first.data;
		first = first.next;

		/**
		 * 如果只有一个元素,取出来就空了,需要把 last也设置成 null
		 */
		if(first == null) last =null;
		return value;
	}

	/**
	 * 加元素
	 */

	public void add(T item) {
	QueueNode<T>  node = new QueueNode<T>(item);
	if(last!=null) last.next=node;
	last= node;
	if(first==null) first =last;

	}

	/**
	 * 从队首取元素,不会 remove 元素
	 * @return
	 */
	public T peek(){
		if(first == null) throw new EmptyQueueException();
		return first.data;
	}

	public boolean isEmpty(){
		return first == null;
	}
}
