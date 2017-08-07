/* Sorts.java */

package homework10;

import java.util.Arrays;

public class Sorts {

	/**
	 * Place any final static fields you would like to have here.
	 **/

	/**
	 * countingSort() sorts an array of int keys according to the values of
	 * _one_ of the base-16 digits of each key. "whichDigit" indicates which
	 * digit is the sort key. A zero means sort on the least significant (ones)
	 * digit; a one means sort on the second least significant (sixteens) digit;
	 * and so on, up to a seven, which means sort on the most significant digit.
	 * 
	 * @param key
	 *            is an array of ints. Assume no key is negative.
	 * @param whichDigit
	 *            is a number in 0...7 specifying which base-16 digit is the
	 *            sort key.
	 * @return an array of type int, having the same length as "keys" and
	 *         containing the same keys sorted according to the chosen digit.
	 *
	 *         Note: Return a _newly_ created array. DO NOT CHANGE THE ARRAY
	 *         keys.
	 **/
	public static int[] countingSort(int[] keys, int whichDigit) {
		// Replace the following line with your solution.
		int counts[] = new int[keys.length];
		int keys2[] = new int[keys.length];
		for (int i = 0; i < keys.length; i++) {
			int temp = (keys[i] >> 4 * whichDigit) & 0xF;
			keys2[i] = temp;
		}

		// yell(keys2);
		for (int i = 0; i < keys.length; i++) {
			counts[keys2[i]]++;
		}

		int total = 0;
		for (int j = 0; j < counts.length; j++) {
			int c = counts[j];
			counts[j] = total;
			total = total + c;
		}

		int y[] = new int[keys.length];
		for (int i = 0; i < keys.length; i++) {
			y[counts[keys2[i]]] = keys[i];
			counts[keys2[i]]++;
		}
		return y;
	}

	/**
	 * radixSort() sorts an array of int keys (using all 32 bits of each key to
	 * determine the ordering).
	 * 
	 * @param key
	 *            is an array of ints. Assume no key is negative.
	 * @return an array of type int, having the same length as "keys" and
	 *         containing the same keys in sorted order.
	 *
	 *         Note: Return a _newly_ created array. DO NOT CHANGE THE ARRAY
	 *         keys.
	 **/
	public static int[] radixSort(int[] keys) {
		// Replace the following line with your solution.
		for (int i = 0; i < 8; i++) {
			keys = countingSort(keys, i);
		}
		return keys;
	}

	/**
	 * yell() prints an array of int keys. Each key is printed in hexadecimal
	 * (base 16).
	 * 
	 * @param key
	 *            is an array of ints.
	 **/
	public static void yell(int[] keys) {
		System.out.print("keys are [ ");
		for (int i = 0; i < keys.length; i++) {
			System.out.print(Integer.toString(keys[i], 16) + " ");
		}
		System.out.println("]");
	}

	/**
	 * main() creates and sorts a sample array. We recommend you add more tests
	 * of your own. Your test code will not be graded.
	 **/
	public static void main(String[] args) {

		int test[] = { 1, 2, 4, 3, 4, 7, 6, 3, 8 };
		test = countingSort(test, 0);
		yell(test);

		int[] keys = { Integer.parseInt("60013879", 16), Integer.parseInt("11111119", 16),
				Integer.parseInt("2c735010", 16), Integer.parseInt("2c732010", 16), Integer.parseInt("7fffffff", 16),
				Integer.parseInt("4001387c", 16), Integer.parseInt("10111119", 16), Integer.parseInt("529a7385", 16),
				Integer.parseInt("1e635010", 16), Integer.parseInt("28905879", 16), Integer.parseInt("00011119", 16),
				Integer.parseInt("00000000", 16), Integer.parseInt("7c725010", 16), Integer.parseInt("1e630010", 16),
				Integer.parseInt("111111e5", 16), Integer.parseInt("61feed0c", 16), Integer.parseInt("3bba7387", 16),
				Integer.parseInt("52953fdb", 16), Integer.parseInt("40013879", 16) };

		int[] sorts = keys;
		yell(keys);
		keys = radixSort(keys);
		yell(keys);
		System.out.println("Arrays.sort() method for test:");

		checkMethod(keys, sorts);

	}

	public static void checkMethod(int[] keys, int sort[]) {
		Arrays.sort(sort);
		boolean test = true;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != sort[i]) {
				test = false;
				break;
			}
		}
		if (!test) {
			System.out.println("Error arrays not equal");
		} else {
			System.out.println("Good Job!!!");
		}
	}
}