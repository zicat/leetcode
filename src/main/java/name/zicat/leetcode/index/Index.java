package name.zicat.leetcode.index;

import name.zicat.leetcode.array.ListNode;

import java.util.HashSet;

/**
 * 双指针和滑动窗口
 *
 * 快慢指针 链表的问题,链表是否有环
 * 初始情况,快慢指针 都 在head
 *
 * 左右指针  数组或者字符串中的问题,比如二分查找
 *
 * 左右指针是 维护一个区间, 主要再二分查找,翻转数组,滑动窗口
 *
 *
 * 滑动窗口 是 子字符串匹配的问题
 *
 */
public class Index {

	/**
	 * 环型链表  是否有 环,并且环的入口是啥
	 * 设两指针 fast，slow 指向链表头部 head，fast 每轮走 22 步，slow 每轮走 11 步；
	 * 第一种结果： fast 指针走过链表末端，说明链表无环，直接返回 null；
	 * 输入 head =[3,2,0,-4],pos =1
	 * 输出
	 *
	 * 第二种结果： 当fast == slow时， 两指针在环中第一次相遇 。
	 *
	 * 1.设链表共有 a+b个节点，其中链表头部到链表入口有a个节点（不计链表入口节点,链表环有b个节点
	 * fast 走的步数是slow步数的2倍，即 f = 2s
	 * fast 比slow多走了 n 个环的长度，即 f = s + nb（ 解析： 双指针都走过 a 步，然后在环内绕圈直到重合，重合时 fast 比 slow 多走 环的长度整数倍 ）；
	 * 以上两式相减得：f = 2nb，s = nb，即fast和slow 指针分别走了 2n，n 个 环的周长 。
	 *
	 * 如果让指针从链表头部一直向前走并统计步数k，那么所有 走到链表入口节点时的步数 是：k=a+nb（先走 aa 步到入口节点，之后每绕 11 圈环（ bb 步）都会再次到入口节点）。
	 * 而目前，slow 指针走过的步数为 nbnb 步。因此，我们只要想办法让 slow 再走 aa 步停下来，就可以到环的入口。
	 * 但是我们不知道 a 的值，该怎么办？依然是使用双指针法。我们构建一个指针，此指针需要有以下性质：此指针和slow 一起向前走 a 步后，两者在入口节点重合。那么从哪里走到入口节点需要 aa 步？答案是链表头部head
	 *
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head){
		ListNode fast = head, slow = head;
		//判断 是否有环
		while (true) {
			if (fast == null || fast.next == null) return null;
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) break;
		}

		//有环的话,把fast 重置到 head, 来走 a步寻找到 环的入口
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;

	}
	/**
	 * 无重复字符的最长子串
	 * 滑动窗口 来做
	 *
	 * 定义 两个指针和一个 set
	 *
	 *算法思路：
	 * windows是滑窗内字符的集合，初始化为空。从前向后移动滑窗，同时更新curr_len和max_len。
	 * 如果ch已存在windows中，那么从滑窗的左端起删除字符，直到删除ch。每删除一个字符cur_len减1。
	 * 将ch添加到windows中，cur_len加1。
	 * 更新max_len
	 *
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s == null){
			return 0;
		}
		HashSet<Character> window = new HashSet<Character>();
		int left = 0, cur_len = 0, max_len = 0;
		char[] sc = s.toCharArray();
		for (char ch: sc){
			// 从前向后删除，直到删除了ch
			while (window.contains(ch)){
				window.remove(sc[left]);
				left ++;
				cur_len --;
			}
			// 添加ch
			window.add(ch);
			cur_len ++;
			// 更新长度
			max_len = Math.max(max_len, cur_len);
		}
		return max_len;
	}


}
