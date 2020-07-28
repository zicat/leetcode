package name.zicat.leetcode.string;

import java.util.*;

/**
 * @author zhangjun
 * Date 2020/7/28
 */
public class StringUtils {

    /**
     *
     * @param s
     * @return
     */
    public static String maxUnrepeatedSubString(String s) {

        if(s == null)
            throw new NullPointerException("string is null");

        if(s.length() == 0)
            return s;

        Set<Character> set = new HashSet<Character>();
        int leftPoint = 0;
        int rightPoint = 0;
        int maxSize = 0;
        int maxSizeStart = 0;
        while (rightPoint < s.length()) {
            char c = s.charAt(rightPoint);
            if(set.contains(c)) {
                //recompute the maxsize
                if(rightPoint - leftPoint > maxSize) {
                    maxSize = rightPoint - leftPoint;
                    maxSizeStart = leftPoint;
                }
                //remove all the chars that is front the c
                while (leftPoint < rightPoint && s.charAt(leftPoint) != c) {
                    set.remove(s.charAt(leftPoint));
                    leftPoint ++;
                }
                //point the c + 1
                leftPoint++;
            }
            set.add(c);
            rightPoint++;
        }
        //recompute the maxsize, avoid all chars are different
        if (rightPoint - leftPoint > maxSize) {
            maxSize = rightPoint - leftPoint;
            maxSizeStart = leftPoint;
        }

        return s.substring(maxSizeStart, maxSizeStart + maxSize);
    }
}
