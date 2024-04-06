package ex03.pattern;

import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		// 다음중 문자가 섞인 구성된 값의 인덱스를 찾으시오
		String[] test1 = {"123", "1d2", "456", "ddd4", "132.456", "3@2"};
		Pattern p = null;
		p = Pattern.compile("[^\\d]");
		for (int i = 0; i < test1.length; i++) {
			
			if(p.matcher(test1[i]).find()) {
				System.out.print(i);
			}
			
		}
		
		System.out.println();
		
		// 다음중 특수문자가 사용된 값의 인덱스를 찾으시오.
		String[] test2 = {"tester", "test!!", "master.id", "main_id"};
		p = Pattern.compile("[^\\w]");
		
		for (int i = 0; i < test2.length; i++) {
			
			if(p.matcher(test2[i]).find()) {
				System.out.print(i);
			}
			
		}
		
		
	}

}
















