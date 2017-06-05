
package com.myndful.elapseddays;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sanish
 *
 */
public class DateValidationHelper {

	private static final String VALID_DATE_EXPRESSION = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|(2[0-9]))\\d\\d)";
	
	/**
	 * validation method for evaluating the date field 
	 */
	public boolean doValidate(String dateField) {

		Pattern pattern = Pattern.compile(VALID_DATE_EXPRESSION);
		Matcher matcher = pattern.matcher(dateField);
		if (!matcher.matches()) {
			return false;
		}
		return isAValidDate(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)));
	}

	/**
	 * validating if a given day,month and year in the date fields are matching.
	 */
	
	private boolean isAValidDate(int day, int month, int year) {		
		boolean isValidDate = true;
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 30) {
				isValidDate = false;
			}
			break;
		case 2:
			if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
				if (day > 29) {
					isValidDate = false;
				}
			} else {
				if (day > 28) {
					isValidDate = false;
				}
			}
			break;
		default:
			break;
		}
		return isValidDate;
	}
}
