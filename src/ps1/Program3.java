package ps1;

public class Program3 {
    public static int callMe(int a, int b) {
        b -= 2;
        a = a - b;
        System.out.println(a + " " + b);
        return a;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 4;
        System.out.println(a + " " + b);
        a = callMe(a, b);
        System.out.println(a + " " + b);
        b = 2 * callMe(b, a) + 1;
        System.out.println(a + " " + b);
        System.out.println(callMe(a, a));
        System.out.println(a + " " + b);
        
        System.out.println(average(3,9));
        System.out.println(average(10,20));
    }
    
    public static double average(int a, int b) {
    	double avg = ((double)a + b)/2;
    	return avg;
    }
}
