package name.zicat.leetcode.array;

import java.util.HashMap;

/**
 * 146.
 * LRU,最近最少使用缓存机制
 * 1.固定大小,限制内存占用
 * 2. 插入和查找的效率要高, 最好是O(1)
 * 3. 缓存超过界限后删除不常用的
 *
 * HashMap + 双链表来实现
 * HashMap保存 key和数据的地址,双链表保存数据的值
 * 最近更新的数据 存在双链表的 链表头
 *
 * put操作 没超过容量的情况下 put存在双链表的头
 *
 * 容量变小的话,  remove end  并且 setHeader
 *
 * Get操作
 *  get K3,并把该值移到 链表的头
 *
 *获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 首先插入删除两个操作都需要在O(1)O(1)的时间复杂度中完成，根据这点就已经排除了选择数组来作为存储结构的可能，基本的方向就是使用链表。
 *
 * 然后使用单链表双链表其实都可以，只是若使用单链表的话，需要通过前驱来访问目标结点才能够进行删除/插入操作，不太直观，所以我更推荐使用双链表。
 *
 * 但是链表仍有存在一个问题，访问某个元素不能做到O(1)O(1)，即不支持随机访问。为此，可以结合哈希表进行辅助。因为出现key冲突的情况，按照题目要求只需要进行值的覆盖，所以哈希表处理冲突的方式只需要覆盖原值，故随机访问的速度不会退化，一直是O(1)O(1)。
 *
 * 作者：beney-2
 * 链接：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-beney-2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LRUCache {
	/**
	 *  key 和 节点 这个 节点 是在一个 双向链链表里面,有 pre和 next
	 *  value ,Node的地址
	 */
	HashMap<Integer,Node>  map = new HashMap<>();

	int capacity =0;
	/**
	 *  头节点插入
	 *
	 *  尾节点删除
	 */
	Node head =null;
	Node end = null;

   public LRUCache(int capacity){
   	this.capacity = capacity;
   }

	/**
	 * get 操作
	 * @param key
	 * @return
	 */
   public int get(int key){
   	 if(map.containsKey(key)){
   	 	Node node = map.get(key);
   	 	//链表中 去掉 当前节点,并且 移到链表头
		 remove(node);
		 setHeader(node);
		 return node.value;
   	 } else {
   	 	return -1;
	 }

   }


	/**
	 * put 操作
	 * @param key
	 * @param value
	 */
   public void put(int key,int value){
   	//存在该节点
      if(map.containsKey(key)){
      	Node node = map.get(key);
      	//更新 改node节点 value的值
		  node.value = value;
		  remove(node);
		  setHeader(node);
	//不存在 该节点
      } else {
      	Node newNode = new Node(key,value);
      	//判断 map的大小是否超过 capacity
      	if(map.size() >= capacity){
      	//删掉最后的节点 key和 node
			map.remove(end.key);
			remove(end);
      	}
      	//
		setHeader(newNode);
      	map.put(key,newNode);
	  }
   }

	/**
	 * reset 双链表的头节点,容易丢指针,
	 * @param node
	 */
	private void setHeader(Node node) {
		/**
		 * 看不懂
		 */
		node.pre = null;
		node.next = head;
		if( head!=null){
			head.pre = node;
		}

		head = node;
		if(end == null) end = head;

	}

	/**
	 * 删除 双链表的 节点
	 * @param node
	 */
	private void remove(Node node) {

		/**
		 *  头节点和尾节点的特殊处理
		 */
		if(node.pre ==null){
			head = node.next;
		} else {
			node.pre.next = node.next;
		}

		if(node.next == null){
			end = node.pre;
		} else {
			node.next.pre = node.pre;
		}
	}



}
