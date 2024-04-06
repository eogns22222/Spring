package ex06.look;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		String content = "현재 원 달러 환율은 $1 당 1279원 이다. 이 물건은 $10 이므로 12790원 이 된다.";
		
		Pattern p = null;
		Matcher m = null;
		
		// 일반 탐색 - "원" 도 포함해서 가져옴
		m = Pattern.compile("\\b\\d+([원])").matcher(content);
		System.out.println("일반 탐색");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		System.out.println();
		
		// 긍정형 전방 탐색(?=)
		// 앞에서 부터 탐색해 나가 특정 패턴이 있는 곳을 찾는다.(탐색에 사용된 패턴은 제외하고 반환)
		m = Pattern.compile("\\b\\d+(?=[원])").matcher(content);
		System.out.println("긍정형 전방 탐색");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		System.out.println();
		
		// 긍정형 후방 탐색(?<=)
		// 뒤에서 부터 탐색해 나가 특정 패턴이 있는 곳을 찾는다.(탐색에 사용된 패턴은 제외하고 반환)
		m = Pattern.compile("(?<=[$])\\d+\\b").matcher(content);
		System.out.println("긍정형 후방 탐색");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		System.out.println();
		
		
		content = "$2 가격의 물건을 10개 구매하려고 한다. 10 * 2 니깐 $20 되겠다.";
		// 부정형 전방 탐색(?!)
		// 앞에서 부터 탐색해 나가 특정 패턴이 있는 것은 빼고 반환한다.
		m = Pattern.compile("\\b\\d+(?![원])\\b").matcher(content);
		System.out.println("부정형 전방 탐색");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		System.out.println();
		
		// 부정형 전방 탐색(?<!)
		// 앞에서 부터 탐색해 나가 특정 패턴이 있는 것은 빼고 반환한다.
		m = Pattern.compile("\\b(?<![$])\\d+\\b").matcher(content);
		System.out.println("부정형 전방 탐색");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		System.out.println();
		
		
		
		
		
	}

}
























