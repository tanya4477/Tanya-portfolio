package tanya.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Calendar {
	private static Scanner scanner;
	private static String year;
	private static String fromDate;
	private static String toDate;

	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		startProgram();
	}

	/**
	 * Inputs year
	 */
	private static void startProgram() {
		scanner = new Scanner(System.in);

		System.out.println("Enter arbitrary year:  ");
		year = scanner.next();
		if (isValidYear(year)) {
			startFromDate();
		} else {
			System.out.println("Please enter valid year in the range of 1000 till 2199 !!");
			startProgram();
		}
	}

	/**
	 * Inputs from_date
	 */
	private static void startFromDate() {
		System.out.println("Enter from date in MM-dd format:  ");
		fromDate = scanner.next();
		fromDate = year.concat("-" + fromDate);
		if (isValidDate(fromDate)) {
			startToDate();
		} else {
			System.out.println("Please enter valid from date in MM-dd format !! ");
			startFromDate();
		}
	}

	/**
	 * Inputs to_date
	 */
	private static void startToDate() {
		System.out.println("Enter to date in MM-dd format:  ");
		toDate = scanner.next();
		toDate = year.concat("-" + toDate);
		if (isValidDate(toDate)) {
			displayList(fromDate, toDate);
		} else {
			System.out.println("Please enter valid to date in MM-dd format !! ");
			startToDate();
		}
	}

	/** displays list of weekdays */
	public static void displayList(String s, String e) {
		String sunday = "Sun";
		String saturday = "Sat";
		LocalDate start = LocalDate.parse(s);
		LocalDate end = LocalDate.parse(e);
		List<LocalDate> totalDates = new ArrayList<>();

		if (start.isAfter(end)) {
			System.out.println("The from date can not be later than the to date !!");
			startToDate();
		}
		else{
			System.out.println("List of weekdays in " + fromDate + " to " + toDate + " excluding Saturdays and Sundays:-");
			while (!start.isAfter(end)) {
				totalDates.add(start);
				if (!sunday.equals(dateStringToDay(start.toString()))
						&& !saturday.equals(dateStringToDay(start.toString()))) {
					System.out.println(start + " is " + dateStringToDay(start.toString()));
				}
				start = start.plusDays(1);
			}
		}
	}

	/**
	 * converts date to day
	 * 
	 * @param dt
	 * @return
	 */
	public static String dateStringToDay(String dt) {
		Date date;
		String day = null;
		DateFormat df = new SimpleDateFormat("EEE", Locale.ENGLISH);
		try {
			date = dateFormat.parse(dt);
			day = df.format(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return day;
	}

	/**
	 * validity check for year
	 * 
	 * @param year
	 * @return
	 */
	private static boolean isValidYear(String year) {
		if (year == null) {
			return false;
		} else if (year.matches("^(?=[1-2][0-9]+)\\d{4}$")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * validity check for date
	 * 
	 * @param inDate
	 * @return
	 */
	private static boolean isValidDate(String inDate) {
		dateFormat.setLenient(false);
		inDate = inDate.trim();
		try {
			if (null == inDate || inDate.isEmpty() || inDate.length() != 10) {
				return false;
			}
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
}