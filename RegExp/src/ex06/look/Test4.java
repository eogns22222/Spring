package ex06.look;

public class Test4 {
	
	public static void main(String[] args) {
		
		String[] numbers = {"01033334444", "027778888"};
		
		// 개인정보 보호를 위해서 마지막 4자리를 제외한 나머지는 * 처리 하세요.
		for (String num : numbers) {
			// \\d+(?=[0-9]{4}) <- 숫자 대상
			// .(?=[0-9]{4}) <- 임으의 문자 대상
			
			num = num.replaceAll(".(?=[0-9]{4})", "*");
			
			System.out.println(num);
			
		}
		
		
	}
	
}
