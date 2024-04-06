package ex06.look;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test02 {

	public static void main(String[] args) {
		
		Solution sol = new Solution();
		String result = "";
		
		result = sol.solution("bus");
		System.out.println("bs==" + result);
		
		result = sol.solution("nice to meet you");
		System.out.println("nc t mt y==" + result);
		
	}

}


class Solution {
    public String solution(String my_string) {
        String answer = "";
//        String[] str = {"a", "e", "i", "o", "u"};
//        
//        for (String s : str) {
//        	my_string = my_string.replace(s, "");
//        	
//		}
//        answer = my_string;
        
//        Pattern p = Pattern.compile("[aeiou]");
//        Matcher m = p.matcher(my_string);
//        
//		while (m.find()) {
//			System.out.println(m.group());
//			
//		}
		
		return my_string.replaceAll("[aeiou]", "");
        
    }
}





















