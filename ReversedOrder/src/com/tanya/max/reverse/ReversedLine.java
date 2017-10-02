package com.tanya.max.reverse;

import java.util.Scanner;

/**
 * the function should transform the string " Do or do not, there is no try." to
 * "try.no is there not, do or Do"
 */
public class ReversedLine {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Enter the input string: ");
		String s = scanner.nextLine();
		reverseOrder(s);
	}

	public static void reverseOrder(String s) {
		String[] str = s.split(" ");
		StringBuffer buffer = new StringBuffer();
		for (int i = str.length - 1; i >= 0; i--) {
			buffer.append(str[i]);
			buffer.append(" ");
		}
		System.out.println("Original string: " + s);
		System.out.println("Reverse word string: " + buffer.toString());
	}
}
