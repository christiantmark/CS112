package ps2;
import java.util.*;

public class problem1 {
	public static void main(String[] args) {
		int[] values = {1,3,5,7,9,11,13};
		mystery(values);
		System.out.println(Arrays.toString(values));
	}
	public static void mystery(int[] arr) {
	    for (int i = 0; i < arr.length - 2; i++) {
	        int val1 = arr[i + 1];
	        int val2 = arr[i + 2];
	        arr[i] = val1 + val2;
	        // What values do the variables have here,
	        // for each value of the loop variable `i`?
	    }
	}
}
