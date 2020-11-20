package ps5;

import java.util.Arrays;

public class Problem5 {
	public static int removeDups(int[] arr) {
		int count = 0;
		for(int i = arr.length-1; i > 0; i--) {
			if(arr[i] == arr[i-1]) {
				for(int j = i; j < arr.length-1; j++)
					arr[j] = arr[j+1];
				count++;
			}
		}
		for(int i = arr.length-count; i < arr.length; i ++)
			arr[i] = 0;
		return arr.length-count;
	}
	
	private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
	
	/* partition - helper method for qSort */
    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last)/2];
        int i = first - 1;  // index going left to right
        int j = last + 1;   // index going right to left
        
        while (true) {
            // moving from left to right, find an element >= the pivot
            do {
                i++;
            } while (arr[i] < pivot);
            
            // moving from right to left, find an element <= the pivot
            do {
                j--;
            } while (arr[j] > pivot); 
            
            // If the indices still haven't met or crossed,
            // swap the elements so that they end up in the correct subarray.
            // Otherwise, the partition is complete and we return j.
            if (i < j) {
                swap(arr, i, j);
            } else {
                return j;     // index of last element in the left subarray
            }
        }
    }
    
    /* qSort - recursive method that does the work for quickSort */
    private static void qSort(int[] arr, int first, int last) {
        int split = partition(arr, first, last);
        if (first < split) {
            qSort(arr, first, split);      // left subarray
        }
        if (last > split + 1) {
            qSort(arr, split + 1, last);   // right subarray
        }
    }
    
    /** quicksort */
    public static void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1); 
    }
	
    /* merge - helper method for mergesort */
    private static void merge(int[] arr, int[] temp, 
      int leftStart, int leftEnd, int rightStart, int rightEnd)
    {
        int i = leftStart;    // index into left subarray
        int j = rightStart;   // index into right subarray
        int k = leftStart;    // index into temp
        
        while (i <= leftEnd && j <= rightEnd) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++; k++;
            } else {
                temp[k] = arr[j];
                j++; k++;
            }
        }
        
        while (i <= leftEnd) {
            temp[k] = arr[i];
            i++; k++;
        }
        while (j <= rightEnd) {
            temp[k] = arr[j];
            j++; k++;
        }
        
        for (i = leftStart; i <= rightEnd; i++) {
            arr[i] = temp[i];
        }
    }
    
    /** mSort - recursive method for mergesort */
    private static void mSort(int[] arr, int[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        
        int middle = (start + end)/2;
        mSort(arr, temp, start, middle);
        System.out.println("msort left side: " + Arrays.toString(arr));
        mSort(arr, temp, middle + 1, end);
        System.out.println("msort right side: " + Arrays.toString(arr));
        merge(arr, temp, start, middle, middle + 1, end);
        System.out.println("merged: " + Arrays.toString(arr));
        
    }
    
    /** mergesort */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mSort(arr, temp, 0, arr.length - 1);
    }
    
	public static void main(String args[]) {
		int[] arr1 = {2, 5, 5, 5, 10, 12, 12};
		int i = removeDups(arr1);
		System.out.println(Arrays.toString(arr1));
		System.out.println(i);
		
		int[] arr = {10, 18, 4, 24, 33, 40, 8, 3, 12};

        System.out.println("original: " + Arrays.toString(arr));
		mergeSort(arr);
	}
	
	
}
