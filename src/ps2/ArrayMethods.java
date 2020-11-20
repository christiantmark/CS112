package ps2;

import java.util.Arrays;

public class ArrayMethods {
	public static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	//1
	public static int getDayIndex(String day) {
		int count = 0;
		if (day == null)
			throw new IllegalArgumentException("check for valid parameters");
		else {
			for(int i = 0; i < DAYS.length; i ++) {
			if (DAYS[i].equalsIgnoreCase(day))
				return i;
			else
				count = -1;
			}
		}
		return count;
	}
	
	//2
	public static void swapAdjacent(int[] values) {
		if (values == null)
			throw new IllegalArgumentException("Check for valid parameters");
		else  {
			for (int i = 0; i < values.length-1; i +=2) {
				int hold = values[i];
				values[i] = values[i+1];
				values[i+1] = hold;
			}
		}
	}
	
	//3
	public static int[] copyCapped(int[] values, int cap) {
		int[] copy;
		if(values == null) 
			throw new IllegalArgumentException("check for valid parameters");
		else {
			copy = new int[values.length];
			for(int i = 0; i < values.length; i ++) {
				if(values[i] > cap)
					copy[i] = cap;
				else
					copy[i] = values[i];
			}
		}
		return copy;
	}
	
	//4
	public static int mostFrequentValue(int[] arr) {
		int highestnum = 0;
		int highestcount = 0;
		int count = 1;
		if(arr.length == 0 || arr == null)
			throw new IllegalArgumentException("Check for valid parameters");
		else {
			for (int i = 0; i < arr.length-1; i++) {
				if(arr[i] == arr[i+1])
					count++;
				else if(count > highestcount) {
					highestnum = arr[i];
					highestcount = count;
					count = 1;
				}
			}
		}
		return highestnum;
	}
	
	//5
	public static int indexOf(int[] arr1, int[] arr2) {
		int index = -1;
		if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0)
			throw new IllegalArgumentException("Check for valid parameters");
		else {
			int[] hold = new int[arr1.length];
			for (int i = 0; i <= arr2.length-arr1.length; i++) {
				for (int j = 0; j < arr1.length; j++) {
					hold[j] = arr2[j+i];
				}
				if(Arrays.equals(hold, arr1)) {
					return i;
				}
			}
		}
		return index;
			
	}
	
	public static void main(String[] args) {
		/*
		int[] a1 = {1, 2, 3, 4, 5, 6, 7};
		swapAdjacent(a1);
		System.out.println(Arrays.toString(a1));
		int[] something = copyCapped(a1, 4);
		System.out.println(Arrays.toString(something));
		int[] arr = {1, 2, 3, 3, 8, 8, 8, 8, 11, 11, 11, 14, 19, 19};
		System.out.println(mostFrequentValue(arr));
		int[] arrr = {1, 2, 3, 3, 8, 8, 8, 8, 11, 11, 11, 11, 14, 19, 19};
		System.out.println(mostFrequentValue(arrr));
		int[] arrrr = {1, 2, 3, 4, 5};
		System.out.println(mostFrequentValue(arrrr));
		*/
		int[] list1 = {1, 3, 6};
		int[] list2 = {1, 3, 6, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};
		System.out.println(indexOf(list1, list2));
	}
}
