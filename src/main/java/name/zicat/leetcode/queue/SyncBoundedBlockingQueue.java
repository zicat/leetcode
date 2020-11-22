package name.zicat.leetcode.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SyncBoundedBlockingQueue {

	private final ReentrantLock lock = new ReentrantLock();
	//控制阻塞等待
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	private final LinkedList<Integer> que = new LinkedList<>();
	private final int capacity;

	public SyncBoundedBlockingQueue(int capacity){
		this.capacity = capacity;
	}

	public void enqueue(int element) throws InterruptedException {
		lock.lock();
		try {
			while (que.size() == capacity) {

				/**
				 * 已经满的情况下,调用wait释放锁,当前线程进入该对象的等待队列,
				 * 等待被其他线程环形,被唤醒并且获得锁后,线程恢复运行
				 */
				wait();
			}
			que.add(element);
			/**
			 * 唤醒其他等待该对象锁的线程
			 */
			notifyAll();
		} finally {
			lock.unlock();
		}
	}

	public int dequeue() throws InterruptedException {
		lock.lock();
		int val;
         try {
			 while (que.size() == 0) {
				 /**
				  * 调用wait 释放锁,当前线程进入当前对象锁的
				  * 等待队列,等待被其他线程唤醒,被唤醒并且获得锁之后,线程恢复运行
				  */
				 wait();
			 }
			  val = que.poll();
		 } finally{
         	lock.unlock();
		 }
         //唤醒其他线程
		notifyAll();
		return val;
	}


}
