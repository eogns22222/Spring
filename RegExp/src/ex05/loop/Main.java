package ex05.loop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		Pattern p = null;
		
		System.out.println("x+ : x 가 한번 이상 반복 될 때");
		p = Pattern.compile("x+");
		System.out.println("x : " + p.matcher("x").find());
		System.out.println("xxx : " + p.matcher("xxx").find());
		System.out.println("myxlist : " + p.matcher("myxlist").find());
		System.out.println();
		
		System.out.println("x{n} : x 가 n 번 반복");
		p = Pattern.compile("[0-9]{3}"); // 3 자리 이상이면 3 자리 까지 끊어서 가져온다.
		print("1 23 456 7890", p.matcher("1 23 456 7890"));
		// 딱 3 자리 숫자를 가져오려면?
		p = Pattern.compile("\\b\\d{3}\\b");
		print("1 23 456 7890", p.matcher("1 23 456 7890"));
		System.out.println();
		
		System.out.println("x{n,m} : x 가 최소 n번, 최대 m번");
		p = Pattern.compile("\\b[0-9]{3,4}\\b");
		print("1 23 456 7890 55555", p.matcher("1 23 456 7890 55555"));
		System.out.println();
		
		System.out.println("x{n,} : x 가 최소 n번 반복");
		p = Pattern.compile("[0-9]{3,}");
		print("1 23 456 7890 55555", p.matcher("1 23 456 7890 55555"));
		System.out.println();
		
		// 같은 문자가 연속으로 반복 될 경우(2글자 부터)
		System.out.println("([0-9a-z])\\1 : 같은 숫자가 1번 이상 반복되면");
		p = Pattern.compile("([0-9a-z])\\1");
		print("두글자 이상", p.matcher("1bbccccddd01223334444"));
		System.out.println();
		
		System.out.println("([\\w])\\1{3,} : 같은 문자가 3번 이상 반복되면");
		p = Pattern.compile("([\\w])\\1{3,}");
		print("두글자 이상", p.matcher("1bbccccdddd01223334444"));
		System.out.println();
		
		String[] phones = { // 2-3, 3-4, 4
				"032-234-5678",
				"02-234-5678",
				"0-234-5678",
				"010-1234-5678",
				"010-1234-567"
		};
		
		p = Pattern.compile("^\\d{2,3}-\\d{3,4}-\\d{4}$");
		
		for (int i = 0; i < phones.length; i++) {
			System.out.println(phones[i] + " 은 정상적인 번호 인가? " + p.matcher(phones[i]).find());
		}
		
		
	}
	
	public static void print(String target, Matcher m) {
		 
		System.out.print(target + " : ");
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
	}

}



















