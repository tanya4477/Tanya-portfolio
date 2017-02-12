package tanya.string.reversal;

import java.util.Scanner;

public class StringReversal {
	private static Scanner scanner = new Scanner(System.in);
	private static final String GOOD_BYE_MSG = "Thank you for using dictionary service. Bye !!";

	public static void main(String[] args) {
		System.out.println("Welcome to dictionary service !!");
		System.out.println("----------------------------------");
		System.out.println("1. Start dictionary\n2. Exit dictionary");

		startMenu();
	}

	public static void startMenu(){
		String choice = scanner.next();
		switch (choice) {
		case "1":
			startProgram();
			break;
		case "2":
			System.out.println(GOOD_BYE_MSG);
			System.exit(0);
			break;
		default:
			System.out.println("Please enter option 1 or 2 !!");
			startMenu();
			break;
		}
	}
	
	private static void startProgram() {
		System.out.print("Enter Earthling word : ");
		String inputString = scanner.next();
		if (isValidString(inputString)) {
			StringBuilder stringBuilder = new StringBuilder(inputString);
			System.out.println("Martian word is : " + stringBuilder.reverse());

			System.out.println("Do you want to continue ? (press y):");
			String str = scanner.next();
			if (str.equalsIgnoreCase("Y")) {
				startProgram();
			} else {
				System.out.println(GOOD_BYE_MSG);
				System.exit(0);
			}
		} else {
			System.out.println("Please enter valid alphabetic string !");
			startProgram();
		}
	}

	/**
	 * string validity check
	 * 
	 * @param inputString
	 * @return
	 */
	private static boolean isValidString(String inputString) {
		if (inputString == null) {
			return false;
		} else if (inputString.matches("^[a-zA-Z ]{1,50}$")) {
			return true;
		} else {
			return false;
		}
	}
}
