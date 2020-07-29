package name.zicat.leetcode.array;


/**
 * @author zhangjun
 * Date 2020/7/29
 */
public class ArrayUtils {

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
}
