Analyze leetcode questions and Giving java code
=========================================
Question 1: Max Unrepeated Substring
---------------------

### Description: Input a string, output the max substring that not contains repeated chars.
### Solution: Sliding windows. 
1. Define two points left-point&&right-point than point to the beginning of the string.
2. Using right-point to foreach string, add each char to the set.
3. Before add the char to the set, checking whether it already exist in the set.
4. If the char in the set, recompute the max-size-substring and removing all char that is font the char(including the char) and left-point point to the char-index + 1.
5. After the end of foreach, recompute the max-size-substring again.

### Code: [StringUtils.maxUnrepeatedSubString(String s)](/src/main/java/name/zicat/leetcode/string/StringUtils.java)