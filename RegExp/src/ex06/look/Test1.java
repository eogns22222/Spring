package ex06.look;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

	public static void main(String[] args) {
		// aya, ye, woo, ma
		String[] babbling = {"aya", "yee", "u", "maa", "ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
		
		Pattern p = Pattern.compile("^(aya(?!aya)|ye(?!ye)|woo(?!woo)|ma(?!ma))+$");
		Matcher m = null;
		int cnt = 0;
		
		for (String str : babbling) {
			m = p.matcher(str);
			if(m.find()) {
				System.out.println(m.group());
				cnt++;
			}
		}
		System.out.println("cnt : " + cnt);
	}

}
