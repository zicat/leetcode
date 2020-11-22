package name.zicat.leetcode.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockBoundedBlockingQueue {

	private final ReentrantLock lock = new ReentrantLock();
	//控制阻塞等待,代替传统的wait和notify实现线程间的协作
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	private final LinkedList<Integer> que = new LinkedList<>();
	private final int capacity;

	/**
	 * 每个Condition会有自己单独的等待队列，调用await方法，会放到对应的等待队列中。当调用某个Condition的signalAll/signal方法，则只会唤醒对应的等待队列中的线程。
	 * 唤醒的粒度变小了，且更具针对性。如果只使用一个Condition的话，有些线程即使被唤醒并取得锁，其依然有可能并不满足条件而浪费了机会，产生时间损耗，相当于notEmpty的Condition唤醒了
	 * notFull的队列线程。
	 * @param capacity
	 */

	public LockBoundedBlockingQueue(int capacity){
		this.capacity = capacity;
	}

	public void enqueue(int element) throws InterruptedException {
		lock.lock();
		try {
			while (que.size() == capacity) {
                 //当前arraylist是满的,会放到对应的等待队列中,
				notFull.await();
			}
			que.add(element);
			//已经放入数据 不是空的了,唤醒队列中的线程
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public int dequeue() throws InterruptedException {
		lock.lock();

		while(que.size() ==0){
			//arraylist是空的,线程放到对应的等待队列中
			notEmpty.await();
		}
		int val = que.poll();
		//已经取出数据,不是满的了,唤醒 队列中的线程
		notFull.signal();
		return val;
	}


}
