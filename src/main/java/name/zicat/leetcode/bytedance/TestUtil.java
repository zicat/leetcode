package name.zicat.leetcode.bytedance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestUtil {

	public static void main(String[] args) {
	}
	/**
	 * 
	 */

	public static String[] findWords(String[] words) {
      char[] first = {'Q','W','E','R','T','Y','U','I','O','P'};
      char[] second = {'A','S','D','F','G','H','J','K','L'};
      char[] third = {'Z','X','C','V','B','N','M'};
      HashSet firstSet = new HashSet<Character>();
      HashSet secondSet = new HashSet<Character>();
      HashSet thirdSet = new HashSet<Character>();
      
      for(char firstStr: first){
      	firstSet.add(firstStr);
	  }
		for(char secondStr: second){
			secondSet.add(secondStr);
		}
		for(char thirdStr: third){
			thirdSet.add(thirdStr);
		}
		List<String> arrayList = new ArrayList<>();
		for(String word:words){
			boolean firstBool = true;
			boolean secondBool = true;
			boolean thirdBool = true;
			for(int i=0;i<word.length();i++){
				
				if(firstBool &&!firstSet.contains(Character.toUpperCase(word.charAt(i)))) {
					firstBool = false;
				}
				if(secondBool &&!secondSet.contains(Character.toUpperCase(word.charAt(i)))) {
					secondBool = false;
				}
				if(thirdBool &&!thirdSet.contains(Character.toUpperCase(word.charAt(i)))) {
					thirdBool = false;
				}
				if(!firstBool && ! secondBool && !thirdBool){
					break;
				}
				if(i == word.length()-1){
					arrayList.add(word);
				}
				
			}

		}
		return arrayList.toArray(new String[arrayList.size()]);
		
	}
}
