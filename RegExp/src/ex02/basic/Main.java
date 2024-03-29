package ex02.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		
		Pattern p = null;
		Matcher m = null;
		boolean result;
		
		// ^x : x 로 시작하는... (문자열의 시작)
		p = Pattern.compile("^x");
		m = p.matcher("xlist");
		result = m.find();
		System.out.println("^x");
		System.out.println("xlist : " + result);
		System.out.println("listx : " + p.matcher("listx").find());
		System.out.println("myxlist : " + p.matcher("myxlist").find());
		System.out.println();
		
		// x$ : x 로 끝나는...(문자열 종료)
		System.out.println("x$");
		p = Pattern.compile("x$");
		System.out.println("listx : " + p.matcher("listx").find());
		System.out.println("myxlist : " + p.matcher("myxlist").find());
		System.out.println();
		
		System.out.println("x* : x 가 있을 수도 있지만 없을 수도 있고 반복할수도 있다.");
		p = Pattern.compile("x*");
		System.out.println("y : " + p.matcher("y").find());
		System.out.println("x : " + p.matcher("x").find());
		System.out.println("xxx : " + p.matcher("xxx").find());
		System.out.println("myxlist : " + p.matcher("myxlist").find());
		System.out.println();
		
		System.out.println("x? : x 가 있을 수도 있지만 없을 수도 있다.");
		p = Pattern.compile("x?y"); // x 는 있을 수도 있고 없을 수도 있지만 y 는 있어야 한다.
		System.out.println("xy : " + p.matcher("xy").find());
		System.out.println("y : " + p.matcher("y").find());
		System.out.println("x : " + p.matcher("x").find());
		System.out.println();
		
		System.out.println("x|y : x 또는 y 가 존재한다.");
		p = Pattern.compile("two|three");
		m = p.matcher("two or three");
		while(m.find()) {
			// index 값을 이용해서 substring 등에 활용할 수 있다.
			System.out.println(m.group() + " : " + m.start() + " ~ " + m.end());
		}
		System.out.println();
		
		System.out.println("x.y : x 와 y 사이에 임의의 한글자가 올수 있다.");
		p = Pattern.compile("x.y");
		System.out.println("xzy : " + p.matcher("xzy").find());
		System.out.println("x0y : " + p.matcher("x0y").find());
		System.out.println("xaay : " + p.matcher("xaay").find());
		System.out.println("xy : " + p.matcher("xy").find());
		System.out.println();
		
		
	}

}
























