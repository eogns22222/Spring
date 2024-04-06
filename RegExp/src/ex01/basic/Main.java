package ex01.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		String str = "01020204093490349"; // 이 문자열에서 "0" 만 찾아볼 것이다.
		
		// 1. 패턴 정의
		Pattern p = Pattern.compile("0");
		// 2. 찾아달라고 요청 - 특정 패턴을 어느 문자열에 대입해서 찾을 것이낙?
		Matcher m = p.matcher(str);
		// 3. 찾은 내용을 확인
		while(m.find()) { // 찾은 내용이 있으면
			// group : 요청 패턴 그룹으로 찾은 내용을 반환
			// start : 찾은 문자열의 시작 인덱스
			// end : 찾은 문자열의 끝 인덱스
			System.out.println(m.group() + " : " + m.start() + " ~ " + m.end());
		}
		
	}

}
