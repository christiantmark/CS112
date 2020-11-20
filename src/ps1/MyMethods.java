package ps1;

public class MyMethods {

	public static void main(String[] args) {
		System.out.println(printDecreasing("method"));
		System.out.println(firstAndLast("Boston"));
		System.out.println(firstAndLast("University"));
		System.out.println(firstAndLast("a"));
		System.out.println("");
		System.out.println(lastIndexOf("Boston", 'o'));
		System.out.println(lastIndexOf("banana", 'a'));
		System.out.println(lastIndexOf("banana", 'b'));
		System.out.println(lastIndexOf("banana", 'x'));
		System.out.println("");
		System.out.println(repeat("Java", 3));
		System.out.println(repeat("oh!", 7));
	}
	
	public static String printDecreasing(String s) {
		for(int i = 0; i <= s.length()-1; i++) {
			System.out.println(s.substring(0,s.length()-i));
		}
		return "";
	}
	
	public static String firstAndLast(String str) {
		if(str.length() > 1)
			return str.substring(0,1) + str.substring(str.length()-1);
		return str;
	}
	
	public static int lastIndexOf(String str, char ch) {
		int i = 0;
		for(i = str.length()-1; i >= 0; i--) {
			if(str.charAt(i) == ch)
				return i;
		}
		return i;
	}
	
	public static String repeat(String str, int n) {
		String combine = "";
		for(int i = n; i > 0; i--) {
			combine = combine + str;
		}
		return combine;
	}

}
