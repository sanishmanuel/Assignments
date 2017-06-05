package com.myndful.elapseddays.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.myndful.elapseddays.DateDiffFinder;

public class DateDiffFinderTest {

	private static DateDiffFinder dateDiffFinder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dateDiffFinder = new DateDiffFinder();
	}

	@Test
	public void testValidDates() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("2/1/2001");
		validInputs.add("21/1/2001");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertNotNull(dateDiffFinder.userInputsErrors);
	}

	@Test
	public void testingErrorListSize() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("29/02/2000");
		validInputs.add("21/1/2001");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingForExtraFields() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("2/1/2001");
		validInputs.add("21/1/2001");
		validInputs.add("22/1/2001");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(1, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingForInSufficientdata() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("29/02/2000");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(1, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingForTwoInvalidDates() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("29/22/2010");
		validInputs.add("32/02/2000");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(2, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingForLeapYear() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("29/02/2000");
		validInputs.add("30/03/2000");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingForLeapFailiure() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("29/02/2001");
		validInputs.add("30/03/2000");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(1, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingBoundaries() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("01/01/1901");
		validInputs.add("01/01/2999");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingBoundariesfail() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("01/01/1899");
		validInputs.add("01/01/3000");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(2, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingDatesInReversOrder() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("01/01/1910");
		validInputs.add("01/01/1901");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingtwoValidDates1() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("03/01/1989");
		validInputs.add("03/08/1983");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingtwoValidDates2() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("04/07/1984");
		validInputs.add("25/12/1984");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingInvalidStrings() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("dsgfg");
		validInputs.add("25/12/1984");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(1, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingInvalidStrings1() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("dsgfg");
		validInputs.add("dfdsgfg");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(2, dateDiffFinder.userInputsErrors.size());
	}

	@Test
	public void testingEmptyinput() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("");
		validInputs.add("");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(2, dateDiffFinder.userInputsErrors.size());
	}
	

	@Test
	/** To test the full range of dates against a date 
	 *  error list should be null for the successful execution .
	 */
	public void testingEmptyinputForRange() {
		String testDate ="25/12/1984";
		for(int i = 1901;i<=2999;i++){		
			for(int j=1;j<=12;j++){			
				for (int k=1;k<=31;k++){				
					List<String> validInputs = new ArrayList<String>();
					validInputs.add(testDate);					
					Calendar tmpCal = getCalendar (i,j,k);	
					int year = tmpCal.get(Calendar.YEAR);
					int month = tmpCal.get(Calendar.MONTH);
					int day = tmpCal.get(Calendar.DATE);
					month+=1;
					String tmpDateStr = day+"/"+month+"/"+year;
					System.out.println("From test >>>> "+tmpDateStr);
					validInputs.add(tmpDateStr);
					dateDiffFinder.processUserInputs(validInputs);
					Assert.assertEquals(0,dateDiffFinder.userInputsErrors.size());
				}
			}	
		}
	}
	
	@Test
	public void testingtwoValidDates3() {
		List<String> validInputs = new ArrayList<String>();
		validInputs.add("25/12/1984");
		validInputs.add("31/12/2999");
		dateDiffFinder.processUserInputs(validInputs);
		Assert.assertEquals(0, dateDiffFinder.userInputsErrors.size());
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
		// return new GregorianCalendar(year,month,day);
	}
}

