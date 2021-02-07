package name.zicat.leetcode.stack;

import java.util.EmptyStackException;

/**
 * 先进 后出
 *
 * 总结
 * 括号的题目想到用栈
 *
 * 有配对关系的题目可以用栈
 *
 *
 */
public class Stack<T> {

	private static class StackNode<T> {
		private T data;
		private StackNode<T> next;

		public StackNode(T data){
			this.data =data;
		}
	}

	/**
	 * 对栈顶元素的操作
	 */
	private StackNode<T> top;

	/**
	 * 从栈顶取元素
	 * @return
	 */
	public T pop(){
		if(top == null) throw new RuntimeException();
		T value = top.data;
		/**
		 * 栈顶的指针指向 下一个
		 */
		top = top.next;
		return value;
	}

	/**
	 * 新的 node的
	 * @param item
	 */
	public void push(T item){

		StackNode<T> node = new StackNode<T>(item);
		node.next = top;
		top = node;

	}

	/**
	 * peek是 查看 栈顶元素的值, pop是取出元素
	 * @return
	 */
	public T peek(){
		if(top == null) throw  new EmptyStackException();
		return top.data;

	}

	public boolean isEmpty(){
		return top == null;
	}
}
