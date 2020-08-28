package name.zicat.leetcode.array;


import sun.misc.OSEnvironment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhangjun
 * Date 2020/7/29
 */
public class ArrayUtils {

    public static void main(String[] args) {

       /* ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        removeNthFromEnd(listNode1,2);*/
       int[] sum = {1,1,1,2,2,3};
        int len = removeDuplicateN2(sum);
        for(int i=0;i< len;i++){
            System.out.println(sum[i]);
        }



        //subArraySumK(sum,2);
    }

    /**
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     * @param a
     * @param b
     * @return
     */
    public static double medianValueIn2AscendingOrderArray(int[] a, int[] b) {
        if((a.length + b.length) % 2 == 0)
            return (double) ((get2AscendingOrderArrayPreKValue(a, b, (a.length + b.length) / 2) + get2AscendingOrderArrayPreKValue(a, b, (a.length + b.length) / 2 + 1))) / 2;
        else
            return get2AscendingOrderArrayPreKValue(a, b, (a.length + b.length) / 2 + 1);
    }

    /**
     * E.g. a = {1, 2, 3, 5} b = {1, 6, 7, 9}
     *      if k = 3 return 3
     *         k = 1 return 1
     *         k = 8 return 9
     * @param a
     * @param b
     * @param k
     * @return
     */
    public static int get2AscendingOrderArrayPreKValue(int[] a, int[] b, int k) {

        if(a == null && b == null)
            throw new IllegalArgumentException("arrays is null");

        if(a == null) {
            if(k > b.length)
                throw new IllegalArgumentException("k is out of array size");
            return b[k - 1];
        }

        if(b == null) {
            if (k > a.length)
                throw new IllegalArgumentException("k is out of array size");
            return a[k - 1];
        }

        int aOffset = 0;
        int aSize = a.length;
        int bOffset = 0;
        int bSize = b.length;

        while (true) {

            if(aSize == 0)
                return b[bOffset + k - 1];
            if(bSize == 0)
                return a[aOffset + k - 1];

            int aMiddle = aOffset + (aSize / 2);
            //find the count of Value in b less then aMiddle
            int countLessThenAMiddleInBArray = countInAscendingOrderArrayLessThenK(b, bOffset, bSize, a[aMiddle]);

            if(aSize == 1)
                return countLessThenAMiddleInBArray == (k -1)? a[aMiddle]: countLessThenAMiddleInBArray < (k - 1)?b[bOffset + k - 2]: b[bOffset + k - 1];

            int allCountLessThenOrEqualsMiddle = countLessThenAMiddleInBArray + (aMiddle - aOffset);
            if(allCountLessThenOrEqualsMiddle >= k) {
                aSize = aMiddle - aOffset;
                bSize = countLessThenAMiddleInBArray;
            } else {
                k -= allCountLessThenOrEqualsMiddle;
                aSize -= (aMiddle - aOffset);
                aOffset = aMiddle;
                bSize -= countLessThenAMiddleInBArray;
                bOffset += countLessThenAMiddleInBArray;
            }
        }
    }

    /**
     * the count of elements in ascending order array that is less then k
     * @param a
     * @param k
     * @return
     */
    public static final int countInAscendingOrderArrayLessThenK(int[] a, int offset, int size, int k) {

        if(a == null)
            throw new NullPointerException("array is null");

        if(size == 0 || k <= a[offset])
            return 0;

        if(k > a[offset + size -1])
            return size;

        int left = offset;
        int right = size + offset;
        while (left < right) {
            int middle = (left + right) / 2;
            if(a[middle] < k)
                left = middle + 1;
            else
                right = middle;
        }
        return left - offset;
    }

    /**
     *
     * @param a
     * @param k
     * @return
     */
    public static final int countInAscendingOrderArrayLessThenK(int[] a, int k) {
        return countInAscendingOrderArrayLessThenK(a, 0, a.length, k);
    }

    /**
     * https://leetcode-cn.com/problems/move-zeroes
     * move 0 to the end of array,
     * inputs [0,1,0,3,12]
     * outputs[1,3,12,0,0]
     *
     * @return
     */
    public static final int[] moveZeroes (int[] nums){
        /**
         * 记录下一个非0元素的位置
         */
        int j=0;
        for (int i=0;i< nums.length;i++) {
            /**
             * 不是 0的话 把元素  放到 j的位置
             */
            if(nums[i]!=0){

                nums[j] = nums[i];
                /**
                 * i!=j的话 说明 有 0的元素,那当前的 i的位置 放0
                 */
                if(i!=j){
                    nums[i] =0;
                }
                /**
                 * j指针+1
                 */
                j++;
            }
        }

        return nums;
    }

    /**
     * 209长度最小的子数组. 子数组中的值累加 必须 >n.
     */
    public static final int minLengthArray(int[] nums, int n){

        int first =0;

        int second = -1;

        int minLen =Integer.MAX_VALUE;

        int subArraySum =0;


        while(first < nums.length ){
            subArraySum = subArraySum + nums[first];

            if(subArraySum >= n){
                int len = first - second;
                if(minLen > len){
                    minLen = len;
                }

                second ++;
                subArraySum = subArraySum - nums[second]-nums[first];
            } else {

                first ++;
            }

        }

        if(minLen !=Integer.MAX_VALUE){
            return minLen;
        } else {
            return 0;
        }
    }

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        int start = -1, i = 0, localSum = 0;

        while (i < nums.length) {
            localSum += nums[i];
            /**
             * 窗口 滑动的方法,直到 比
             */
            while (localSum >= s && start < i){
                result = Math.min(result, i - start);
                localSum -= nums[++start];
            }
            i++;
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     *  26.删除排序数组中的重复项
     */
    public static int removeDuplicate(int[] nums) {
        int i=0;
        int j=0;

        while( i< nums.length) {

            if(nums[i] == nums[j]){
                i++;
            } else {
                ++j;
                nums[j] = nums[i];
                i++;
            }

        }

        return  j+1;
    }

    /**
     * 560.和为K的子数组
     * sub[1,3] =sub[4]-sub[1]
     * subSum[i,j] =preSum[j+1] - preSum[i]
     *
     * input [1,1,1] 2
     *
     * output [1,1] [1,1] 2
     *
     * 想办法判断它的值减去K的值是否存在于前缀和数组中即可。
     */

    public static int subArraySumK(int[] nums,int k) {
        int n = nums.length;
        int result =0;

        int[] preSum = new int[n+1];

        int sum =0;

        for(int i=0;i< n;i++){
            preSum[i] =sum;
            sum +=nums[i];

        }

        preSum[n] =sum;


        /**
         * 想办法判断它的值减去K的值是否存在于前缀和数组中即可
         */

        HashMap<Integer,Integer> hashMap=new HashMap<Integer, Integer>();

        for(int preSubSum: preSum){
            if(hashMap.containsKey(preSubSum-k)){
                result = result + hashMap.get(preSubSum-k);
            }

            hashMap.put(preSubSum, hashMap.getOrDefault(preSubSum,0)+1);
        }

        return result;

     /*   for(int i=0;i<=n;i++){

            for(int j= i+1;j<=n ;j++){
               if(preSum[j]- preSum[i] ==k){
                   result ++;
               }

            }

        }

        return result;
*/

    }


    /**
     * 25.以k个节点为一组进行翻转
     * 链表 1->2->3->4->5
     *
     * k=2 返回 2->1->4->3->5
     *
     * k =3 返回 3->2->1->4->5
     *
     * 如果 节点不是 k的整数倍,最后剩余的节点保持原有顺序
     *
     * fakehead带队人  新链表的第一个元素
     *
     *
     */

    public static ListNode reverseKGroup(ListNode head, int k){
        /**
         * 定义带队人
         */

        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;

        ListNode prev = fakeHead;

        int count=0;
        /**
         * 先 获取 k个元素
         */
        ListNode end = head;
        while(end!=null){
            count++;
            if(count%k !=0){
                end = end.next;
                continue;
            }
            /**
             * 获取k个元素的 头和 尾进行翻转
             */
            prev = reverseLinkedList(prev,end.next);
            /**
             * 翻转后 第一个节点 是最后一个了,end指向 翻转后最后的节点
             */
            end = prev.next;
        }

        return fakeHead.next;

    }

    /**
     * 链表反转
     * prev是反转后的前一个节点
     *
     * next是反转后的 后一个节点
     * @param prev
     * @param next
     * @return
     */
    public static ListNode reverseLinkedList(ListNode prev,ListNode next){
        /**
         * 带队人 prev  插入到 后面 节点的是 current
         */
        /**
         * last是 current的前一个节点,last最终指向反转好的头节点
         */
        ListNode last = prev.next;

        ListNode cur= last.next;

        /**
         * current 把节点 插到 带对人的后面
         *  pre-> last(该节点最终变成 last节点)- current-temp
         *
         * 1. 先把 current的 next节点先保存
         *
         * 2.current的 next节点变成 prev的 后面的节点
         *
         * 3.prev的next的节点变成 current
         *
         * 4.last的next变成 temp
         */
        while(cur!=next){
           ListNode temp = cur.next;
           cur.next = prev.next;
           prev.next = cur;
           last.next = temp;
           cur= temp;
        }
        return last;
    }


    /**
     * 328.奇偶链表 节点编号的奇偶
     * input 1->2->3->4->5->NULL
     * output 1->3->5->2->4->NULL
     *
     * input 2->1->3->5->6->4->7->NULL
     * output 2->3->6->7->1->5->4->NULL
     *
     * 用原地算法 即用 带头人来做
     */
    public static ListNode oddEvenList(ListNode head) {

        if(head == null){
            return null;
        }

        ListNode odd = head;

        ListNode even = head.next;
        /**
         * 偶数的带头人
         */
        ListNode evenHead = even;

        /**
         * even偶数一定是 先 遍历完的
         */
        while(even!=null && even.next!=null){
            /**
             *  奇数index的下一个节点 是 偶数index的下一个节点
             */
            odd.next =even.next;
            /**
             * odd index移到 后一个odd index节点
             */
            odd = odd.next;
            /**
             * 同理  偶数的 index下一个节点是 奇数的next
             */
            even.next = odd.next;
            /**
             * even index移到 后一个 even节点
             */
            even = even.next;
        }

        /**
         * 奇数 连接 偶数的head
         */
        odd.next = evenHead;
        return head;
    }


    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     */

    public static ListNode removeNthFromEnd(ListNode head,int n ){

        ListNode findnode =null ;
        ListNode findnodePre = null;
        ListNode end = head;
        ListNode temp = null;
       int count =0;
        while (end != null){
            count ++;
            end = end.next;
            if(count == n){
                findnode = head;

            } else if(count > n){
                temp = findnode;
                findnodePre = temp;
                findnode = temp.next;
            }
        }

        //删除元素

        if(findnodePre!=null) {
            findnodePre.next = findnode.next;
            return head;
        } else {
           return findnode.next;
        }

    }

    /**
     * 删除 排序数组中的重复项,使得每个元素 最多出现2次
     * nums = [1,1,1,2,2,3]
     *
     *
     */

    public static int removeDuplicateN2(int[] nums){
        //
        // Initialize the counter and the second pointer.
        //
        int j = 1, count = 1;

        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        for (int i = 1; i < nums.length; i++) {

            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            //
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            //
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 删除 排序链表中的重复项
     * input [1->2->3->3->4->4->5]
     * output [1->2->5]
     */

    public static ListNode deleteDuplicates(ListNode head) {

        ListNode dummpy = new ListNode(-1);
        dummpy.next = head;
        ListNode first = dummpy;
        ListNode second = head;



        while(second!=null &&second.next !=null){
            /**
             * 如果不相等 两个指针 都 往后 走
             */
            if(first.next.val!=second.next.val){
                first = first.next;
                second = second.next;
            }
            /**
             * 如果相等,second往后走 直到 不相等为止,不相等后 first next指正 指向 second
             */
            else {
                while(second!=null && second.next!=null&& first.next.val==second.next.val){
                    second = second.next;
                }
                first.next = second.next;
                second = second.next;
            }

        }
        return dummpy.next;

    }

    /**
     * 42
     */


    /**
     * 80
     */

    /**
     * 19
     *
     *
     */

    /**
     * 82
     */

    /**
     *
     * 1171
     */




}
