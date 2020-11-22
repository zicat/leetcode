package name.zicat.leetcode.microsoft;

public class TestUtil {

	public static void main(String[] args) throws Exception{

		ListNode head = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(3);
		ListNode listNode4 = new ListNode(4);
		ListNode listNode5 = new ListNode(5);

		head.next = listNode2;

		listNode2.pre = head;
		listNode2.next = listNode3;

		listNode3.pre = listNode2;
		listNode3.next = listNode4;

		listNode4.pre = listNode3;
		listNode4.next = listNode5;
		listNode5.pre = listNode4;
		ListNode result = reverseMethod(head);
		System.out.println("head = " + result);

	}

	public static ListNode  reverseMethod(ListNode head){
		//
		if(head == null){
			return head;
		}

		ListNode first = head;
		ListNode end = head;

		//end 指向最后一个 node
		while (end.next!=null){

			end = end.next;
		}

		//从头进行遍历

		//双数的情况 直接遍历到最后？
		int count =0;
		while(first.next!=null){
			count ++;
			if(count %2 ==0) {
				continue;
			}
			ListNode endBefore = end.pre;
			ListNode temp = first.next;
			first.next = end;
			first.next.pre = first;
			first.next.next = temp;
			temp.pre = first.next;

			//并且end 往前 移动一位

			end = endBefore;
			// 链表是个单数的情况 ,退出
			if(first.next == end){
				break;
			}

			//往下遍历
			first= first.next;

		}


     return first;

	}
}
