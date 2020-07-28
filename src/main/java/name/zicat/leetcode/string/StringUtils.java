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
                if(rightPoint - leftPoint > maxSize) {
                    maxSize = rightPoint - leftPoint;
                    maxSizeStart = leftPoint;
                }
                while (leftPoint < rightPoint && s.charAt(leftPoint) != c) {
                    set.remove(s.charAt(leftPoint));
                    leftPoint ++;
                }
                leftPoint++;
            }
            set.add(c);
            rightPoint++;
        }

        if (rightPoint - leftPoint > maxSize) {
            maxSize = rightPoint - leftPoint;
            maxSizeStart = leftPoint;
        }

        return s.substring(maxSizeStart, maxSizeStart + maxSize);
    }
}
