package ps4;

public class StringRecursion {
	public static void printReverse(String str) {
		if(str == null || str == "") {}
		else if(str.length() == 1)
			System.out.print(str);
		else {
			System.out.print(str.substring(str.length()-1));
			printReverse(str.substring(0, str.length()-1));
		}
	}
	
	public static String trim(String str) {
		if(str.length() == 0 || str == null) {
			return "";
		}
		else if(str.substring(str.length()-1).equals(" "))
			return trim(str.substring(0,str.length()-1));
		else if(str.substring(0,1).equals(" "))
			return trim(str.substring(1));
		else
			return str;
	}
	
	public static int find(char ch, String str) {
		if(str.equals("") || str == null) {
			return -1;
		}
		else {
			int i = find(ch,str.substring(1));
			if(str.charAt(0)==ch) {
				return 0;
			}
			else if(i == -1) {
				return -1;
			}
			return ++i;
		}
	}
	
	public static void main(String[] args) {
		printReverse("Terriers");
		System.out.println("");
		System.out.println(trim(" hello world    "));
		System.out.println(trim("recursion  "));
		System.out.println(find('b', "Rabbit"));
		System.out.println(find('P', "Rabbit"));
	}
}
