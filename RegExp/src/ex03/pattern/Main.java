package ex03.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		Pattern p = null;
		
		// [xy] : x|y 와 같음
		p = Pattern.compile("[to]");
		print("[to] t-world", p.matcher("t-world"));
		
		// [^xy] : x 와 y 를 제외하고
		p = Pattern.compile("[^cd]"); // c, d 를 제외하고
		print("[^cd] abcdefg", p.matcher("abcdefg"));
		
		// [a-z] : a 부터 z 까지
		p = Pattern.compile("[a-z]");
		print("[a-z] 123abc456", p.matcher("123abc456"));
		
		// [0-9] : 0 부터 9 까지
		p = Pattern.compile("[0-9]");
		print("[0-9] 123abc456", p.matcher("123abc456"));
		
		// 특수표현
		// \d : 숫자[0-9] 대신 사용
		p = Pattern.compile("[\\d]");
		print("[\\d] 123abc456", p.matcher("123abc456"));
		
		// \w : 알파벳, 숫자, _ 중 한문자
		p = Pattern.compile("[\\w]");
		print("[\\w] zer0-box_main@naver.com", p.matcher("zer0-box_main@naver.com")); // - . @ 는 가져오지 않는다.
		
		// \b : 문자와 공백 사이 문자
		// 패턴 뒤에 a-z 까지의 문자가 아니면 공백으로 인정해준다.
		// 가져올 때 공백은 가져오지 않는다.
		p = Pattern.compile("[s]\\b"); // s 라는 패턴 뒤에 공백이 있으면 가져온다.
		print("[s]\\b words charcters, styles. lists! ", p.matcher("words charcters, styles. lists! "));
		
		// \s : 공백 - 공백 뒤에 뭐가 붙으면 안된다.
		// 가져올 때 공백도 포함해서 가져온다.
		p = Pattern.compile("[s]\\s"); // s 라는 패턴 뒤에 공백이 있으면 가져온다.
		print("[s]\\s words charcters, styles. lists! ", p.matcher("words charcters, styles. lists! "));
		
	}
	
	public static void print(String target, Matcher m) {
		 
		System.out.print(target + " : ");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
	}

}




























