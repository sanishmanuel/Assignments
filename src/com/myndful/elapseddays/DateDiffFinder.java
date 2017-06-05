package com.myndful.elapseddays;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sanish
 *
 */
public class DateDiffFinder {

	private final static BigDecimal ONE_DAY = new BigDecimal(1000 * 60 * 60 * 24);
	private final static String welcomeMessage = "\n\nPlease enter dates in the 'dd/MM/yyyy' format seperated by a space :\n";
	private final static String exitMessage = "\n***Please enter 'exit' to leave from the system at any time***\n";
	private final static String completionLine = "\n-------------------------------------------------------------------------------------\n";
	private final static String errorMessageHeader = "\n*********************************Error in input**************************************";
	private final static String resultMessage = "\nElapsed number of days : ";
	private final static String invalidDateError = "\n\nDate entered is not valid, please change the date ";
	private final String inSufficentData = "\n Not a valid entry for elapsed date calculation , please enter valid dates";

	private Scanner scanner = null;
	public List<String> userInputsErrors;

	/**
	 * main method
	 */
	public static void main(String as[]) {
		DateDiffFinder dateDiffcheck = new DateDiffFinder();
		dateDiffcheck.acceptInput();
	}

	/**
	 * Method to start accepting input from user
	 *
	 * @return
	 */
	private void acceptInput() {

		List<String> userInputs = new ArrayList<String>();
		try {
			System.out.println(welcomeMessage);
			System.out.println(exitMessage);
			scanner = new Scanner(System.in);
			while (true) {
				String userEnterValues[] = scanner.nextLine().split(" ");
				for (String tmpValue : userEnterValues) {
					if (!tmpValue.isEmpty())
						userInputs.add(tmpValue);
				}
				canQuit(userInputs);
				processUserInputs(userInputs);
				System.out.println(completionLine);
				userInputs.clear();
				System.out.println(welcomeMessage);
			}
		} catch (Exception ex) {
			System.out.println("\n Error in execution, please try again \n");
		}
	}

	/**
	 * Method to give difference between two dates in days
	 *
	 * @param userInputs
	 * @return
	 */
	public void processUserInputs(List<String> userInputs) {
		List<String> valErrors = validateDateFileds(userInputs);
		if (valErrors.isEmpty()) {
			// Successful output
			long elapsedDays = getElapsedTime(userInputs);
			System.out.println(resultMessage + elapsedDays);
		} else {
			// Error in input handling scenario
			System.out.println(errorMessageHeader);
			for (String errors : valErrors) {
				System.out.println(errors);
			}
		}
	}

	/**
	 * Method to exit from the system
	 *
	 * @param userInputs
	 */
	private void canQuit(List<String> userInputs) {
		for (String inputfield : userInputs) {
			if ("exit".equalsIgnoreCase(inputfield)) {
				scanner.close();
				System.exit(0);
			}
		}
	}

	/**
	 * Method to give difference between two dates in days
	 *
	 * @param firstDate
	 *
	 * @param secondDate
	 * @return
	 */
	private List<String> validateDateFileds(List<String> userInputs) {
		userInputsErrors = new ArrayList<String>();
		DateValidationHelper validationHelper = new DateValidationHelper();
		if (userInputs.size() != 2) {
			userInputsErrors.add(inSufficentData);
			return userInputsErrors;
		}
		for (String dateField : userInputs) {
			if (!validationHelper.doValidate(dateField)) {
				userInputsErrors.add(invalidDateError + dateField);
			}
		}
		return userInputsErrors;
	}

	/**
	 * Method to get the elapsed days
	 *
	 * @param userInputs
	 * @return
	 */
	private long getElapsedTime(List<String> userInputs) {

		String[] date1Array = userInputs.get(0).split("/");
		String[] date2Array = userInputs.get(1).split("/");
		Calendar firstDate = getCalendar(Integer.parseInt(date1Array[2]), Integer.parseInt(date1Array[1]),
				Integer.parseInt(date1Array[0]));
		Calendar secodndDate = getCalendar(Integer.parseInt(date2Array[2]), Integer.parseInt(date2Array[1]),
				Integer.parseInt(date2Array[0]));
		return getDifference(firstDate, secodndDate);
	}

	/**
	 * Method to give difference between two dates in days
	 *
	 * @param firstDate
	 *
	 * @param secondDate
	 * @return
	 */
	private long getDifference(Calendar firstDate, Calendar secondDate) {
		long diff = secondDate.getTimeInMillis() - firstDate.getTimeInMillis();
		BigDecimal difference = new BigDecimal(diff);
		BigDecimal c = difference.divide(ONE_DAY, BigDecimal.ROUND_HALF_UP);
		long days = c.longValue();
		if (days < 0) {
			days = days * -1;
		}
		if (days > 0) {
			days = days - 1;
		}
		return days;
	}

	/**
	 * Method to get Calendar Object set with passed year ,month and day
	 *
	 * @return
	 */
	private Calendar getCalendar(int year, int month, int day) {
		Calendar temp = Calendar.getInstance();
		temp.clear();
		temp.set(Calendar.YEAR, year);
		temp.set(Calendar.MONTH, month - 1);
		temp.set(Calendar.DATE, day);
		return temp;
	}
}
