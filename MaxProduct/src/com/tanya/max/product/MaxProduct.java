package com.tanya.max.product;

import java.util.Scanner;

/**
 * this a function to find maximum product (multiplication) of two numbers in
 * an array. For example, array = [5, 2, 8, 14], max product result = 14 * 8 =
 * 112
 */
public class MaxProduct {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		inputArray();
	}

	public static void inputArray() {
		System.out.println("Enter number of elements in array: ");
		int n = scanner.nextInt();
		int array[] = new int[n];
		for (int i = 0; i < array.length; i++) {
			System.out.println("Please enter number at index [" + i + "]: ");
			array[i] = scanner.nextInt();
		}
		twoMaxProduct(array);
	}

	public static void twoMaxProduct(int[] array) {
		int maxOne = 0;
		int maxTwo = 0;
		for (int n : array) {
			if (maxOne < n) {
				maxTwo = maxOne;
				maxOne = n;
			} else if (maxTwo < n) {
				maxTwo = n;
			}
		}
		System.out.println("First Max Number: " + maxOne + "  and Second Max Number: " + maxTwo);
		System.out.println("Product of two max numbers: " + maxOne * maxTwo);
	}
}
