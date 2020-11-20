package ps4;

import java.util.Arrays;

/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }
    
    public BigInt(int[] arr) {
    	if(arr.length <= 0 || arr.length > SIZE)
    		throw new IllegalArgumentException("null value");
    	boolean valid = true;
    	for(int i = 0; i < arr.length; i++) {						//checks to make sure each space in the array only has single digit numbers
    		if(arr[i] > 9 || arr[i] < 0) {
    			valid = false;
    			i = arr.length;
    		}
    	}
    	if(!valid)
    		throw new IllegalArgumentException("null");
    	this.digits = new int[SIZE];
    	for(int i = 0; i < arr.length; i++) {						//fills in the instance field with the same numbers as array
    		this.digits[SIZE-arr.length+i] = arr[i];
    	}
    	boolean exists = false;										//checks to see if there is anything inside the instance array now
    	for(int i = 0; i < SIZE; i++) {
    		if(digits[i] != 0) {
    			i = SIZE;
    			exists = true;
    		}
    	}
    	int count = 0;
    	while(this.digits[count] == 0 && count < 20 && exists) {
    		count++;
    	}
    	this.numSigDigits = SIZE - count;
    }
    
    public BigInt(int n) {
    	String hold = Integer.toString(n);
    	String[] stringArr = hold.split("");
    	int[] intArr = new int[stringArr.length];
    	for(int i = 0; i < stringArr.length; i++)
    		intArr[i] = Integer.parseInt(stringArr[i]);
    	this.digits = new int[SIZE];
    	for(int i = 0; i < intArr.length; i++) {
    		this.digits[SIZE-intArr.length+i] = intArr[i];
    	}
    	int count = 0;
    	while(this.digits[count] == 0 && count < SIZE-1) {
    		count++;
    	}
    	this.numSigDigits = SIZE - count;
    }
    
    public int getNumSigDigits() {
    	return this.numSigDigits;
    }
    
    public String toString() {
    	String s = new String("");
    	for(int i = 0; i < SIZE; i++) {
    		s += Integer.toString(digits[i]);
    	}
    	int i = 0;
    	while(i < 20 && digits[i]==0)
    		i++;
    	if(i == 20)
    		return "0";
    	return s.substring(i);
    }
    
    public int compareTo(BigInt other) {
    	int num = 0;
    	for(int i = 0; i < SIZE; i++) {
    		if(this.digits[i] < other.digits[i])
    			return -1;
    		else if(this.digits[i] > other.digits[i])
    			return 1;
    	}
    	return num;
    }
    
    //assumes that there is 1 to minus
    public void minus1() {
    	for(int i = SIZE-1; i > 0; i--) {
    		if(digits[i]!=0) {
    			digits[i]--;
    			i = 0;
    		}
    		else {
    			digits[i]=9;
    		}
    	}
    }
    
    public BigInt add(BigInt other) {
    	BigInt hold;
    	int[] modify = new int[digits.length];
    	for(int i = 0; i < digits.length; i++) {
    		modify[i] = digits[i];
    	}
    	for(int i = SIZE-1; i >= 0; i--) {
    		int sum = modify[i]+other.digits[i];
    		if(sum < 10) 
    			modify[i] = sum;
    		else {
        		int carryOver = sum/10;
    			if(i < 1)																	//tests if largest decimal place will overflow
    				throw new ArithmeticException("result overflow");
    			else if(i==1 && (modify[0]+other.digits[0]+carryOver)/10 != 0)				//tests if adding to largest decimal place will overflow
    			    throw new ArithmeticException("result overflow");
    			else {
    				modify[i] = sum%10;
    				modify[i-1]+= carryOver;
    			}
    		}
    	}
    	hold = new BigInt(modify);
    	return hold;
    }
    
    public static BigInt addO(BigInt num, int numOs) {
    	int[] hold = new int[num.digits.length];
    	for(int i = 0; i < num.digits.length; i++) {
    		hold[i] = num.digits[i];
    	}
    	for(int i = 0; i < numOs; i++) {
	    	if(hold[0] == 0) {
		    	for(int j = 0; j < hold.length-1; j++) {
		    		hold[j] = hold[j+1];
		    	}
	    	}
	    	else
	    		throw new ArithmeticException("result overflow");
	    	hold[hold.length-1] = 0;
    	}
    	return new BigInt(hold);
    }
    
    //traditional method
   	public BigInt mul(BigInt other) {
    	if(this.digits[0]*other.digits[other.digits.length-1] >= 10)
    		throw new ArithmeticException("result overflow");
    	if(this.numSigDigits == 0 || other.numSigDigits == 0) {
    		BigInt zero = new BigInt(0);
    		return zero;
    	}
    	if(this.numSigDigits == 1 && digits[SIZE-1] == 1)
    		return other;
    	else if(other.getNumSigDigits() == 1 && other.digits[SIZE-1] == 1) {
    		BigInt r = new BigInt(digits);
    		return r;
    	}
    	BigInt smaller;
    	BigInt bigger;
    	if(this.compareTo(other) == 1) {
    		smaller = other;
    		bigger = new BigInt(digits);
    	}
    	else {
    		smaller = new BigInt(digits);
    		bigger = other;
    	}
    	BigInt result = new BigInt(0);
    	for(int i = SIZE-1; i >= SIZE-smaller.numSigDigits; i--) {
    		BigInt temp = new BigInt(0);
    		for(int j = 0; j < smaller.digits[i]; j++)
        		temp = temp.add(bigger);
    		result = result.add(addO(temp,SIZE-1-i));
    	}
    	return result;
    }
    
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();
        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */
        ///*
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();
        
        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
		System.out.println();

	}
}
