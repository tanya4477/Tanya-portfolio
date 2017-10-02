package com.tanya.nonrecursive;

import java.util.Scanner;

public class NonRecursiveSearch {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		NonRecursiveSearch bin = new NonRecursiveSearch();
		int array[] = { 2, 8, 12, 14, 16, 19, 24, 28, 31, 33, 45, 50, 63, 69, 77, 82, 88, 89, 94, 96, 97 };
		System.out.println("Choose a number from array: ");
		int num = scanner.nextInt();
		int ans = bin.binarySearch(array, num);
		if (ans == -1)
			System.out.println("Element not found");
		else
			System.out.println("Element found at index " + ans);
	}

	// binarySeach: non-recursive
	public int binarySearch(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == x)
				return mid;
			else if (a[mid] < x)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}
}
