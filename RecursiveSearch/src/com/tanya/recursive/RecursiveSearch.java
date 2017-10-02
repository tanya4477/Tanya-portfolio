package com.tanya.recursive;

/** a recursive function to perform a binary search
 *  on a sorted array of integers to find the index of a given integer. 
 */
import java.util.Scanner;

public class RecursiveSearch {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int array[] = { 2, 8, 12, 14, 16, 19, 24, 28, 31, 33, 45, 50, 63, 69, 77, 82, 88, 89, 94, 96, 97 };
		System.out.println("Choose a number from array: ");
		int num = scanner.nextInt();
		int ans = findPos(array, num);
		if (ans == -1)
			System.out.println("Element not found");
		else
			System.out.println("Element found at index " + ans);
	}

	public static int findPos(int[] arr, int key) {
		int low = 0, high = 1;
		int val = arr[0];

		while (val < key) {
			low = high;
			high = 2 * high;
			val = arr[high];
		}
		return binarySearch(arr, low, high, key);
	}
	//binarySeach: recursive
	private static int binarySearch(int[] arr, int low, int high, int key) {
		if (high >= low) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == key)
				return mid;
			if (arr[mid] > key)
				return binarySearch(arr, low, mid - 1, key);
			return binarySearch(arr, mid + 1, high, key);
		}
		return -1;
	}
}
