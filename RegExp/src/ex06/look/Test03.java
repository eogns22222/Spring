package ex06.look;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test03 {

	public static void main(String[] args) {
		
		Solution2 sol = new Solution2();
		int result = 0;
		
		result = sol.solution("aAb1B2cC34oOp");
		System.out.println("10==" + result);
		
		result = sol.solution("1a2b3c4d123");
		System.out.println("16==" + result);
		
	}

}


class Solution2 {
    public int solution(String my_string) {
        int answer = 0;
        
        // 1. 정규표현식 + replaceAll
//        String intStr = my_string.replaceAll("[^0-9]", "");
//        String[] str = intStr.split("");
//        
//        for (String s : str) {
//			answer += Integer.parseInt(s);
//		}
        
        // 2. 정규표현식
        Matcher m = Pattern.compile("[0-9]").matcher(my_string);
        while(m.find()) {
        	answer += Integer.parseInt(m.group());
        }
        
        
        return answer;
    }
}





















