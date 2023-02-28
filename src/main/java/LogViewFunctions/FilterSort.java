package LogViewFunctions;

import static MiscFunctions.ExtentVariables.Results;
import static MiscFunctions.ExtentVariables.Steps;
import static MiscFunctions.ExtentVariables.extent;
import static MiscFunctions.ExtentVariables.results;
import static MiscFunctions.ExtentVariables.steps;
import static MiscFunctions.ExtentVariables.test;
import static MiscFunctions.Methods.*;
import static MiscFunctions.Methods.getScreenshot;
import static MiscFunctions.Methods.waitElementInvisible;
import static PageObjects.BasePage.alertMessage;
import static PageObjects.BasePage.loading_cursor;

import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import Config.BaseTest;

public class FilterSort {

	@Test (enabled= true)
	public static void Sorting1(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		BaseTest base = new BaseTest();
		int totalNumberofColumns = size(By.cssSelector("#"+tablename+" th .log-header .mb-0")) + skipColumns;   //get total columns and skip irrelevant columns
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				SoftAssert softAssert = new SoftAssert();
				BaseTest driver = new BaseTest();

				if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0")) !=0) {
					WebElement column = driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					test = extent.createTest("AN-Sorting-"+i+": Apply Sorting on "+column.getText()+" column", "This test case will verify that user can apply sorting on "+column.getText()+ " column");
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					if (column.getText().contains("Time") || column.getText().contains("Date") || column.getText().contains("DATE") || column.getText().contains("CREATED")) {
						test.skip("Column sorted successfully");
						results.createNode("Column sorted successfully");
						base.saveResult(ITestResult.SKIP, null);
					}

					else {
						WebElement filter_scroll = column;
						((JavascriptExecutor)driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
						steps.createNode("1. Click on "+column.getText()+" column header");

						column.click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						if (size(By.cssSelector("#"+tablename+" .fa-sort-amount-down")) != 0) {
							softAssert.assertEquals(size(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_desc .log-header .mb-0")), 1, "Did not sorted in descending order");
							softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
							getScreenshot();
						}

						column.click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(1000);
						if (size(By.cssSelector("#"+tablename+" .fa-sort-amount-down")) != 0) {
							softAssert.assertEquals(size(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_asc .log-header .mb-0")), 1, "Did not sorted in descending order");
							softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
							getScreenshot();
						}
						softAssert.assertAll();
						test.pass("Column sorted successfully");
						results.createNode("Column sorted successfully");
						base.saveResult(ITestResult.SUCCESS, null);
					}
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to sort");
				results.createNode("Column failed to sort");
				base.saveResult(ITestResult.FAILURE, new Exception(er));
			}
		}
	}



	static int partition(String[] numArray, int low, int high)   {
		int pivot = Integer.parseInt(numArray[high]);
		// smaller element index
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {
			// check if current element is less than or equal to pivot
			if ((numArray[i].compareTo(String.valueOf(pivot)) <= 0)) {
				i++;
				// swap the elements
				int temp = Integer.parseInt(numArray[i]);
				numArray[i] = numArray[j];
				numArray[j] = String.valueOf(temp);
			}
		}
		// swap numArray[i+1] and numArray[high] (or pivot)
		int temp = Integer.parseInt(numArray[i + 1]);
		numArray[i + 1] = numArray[high];
		numArray[high] = String.valueOf(temp);
		return i + 1;
	}


	//sort the array using quickSort
	static void quickSort(String[] numArray, int low, int high)
	{
		//auxillary stack
		int[] intStack = new int[high - low + 1];

		// top of stack initialized to -1
		int top = -1;

		// push initial values of low and high to stack
		intStack[++top] = low;
		intStack[++top] = high;

		// Keep popping from stack while is not empty
		while (top >= 0) {
			// Pop h and l
			high = intStack[top--];
			low = intStack[top--];

			// Set pivot element at its correct position
			// in sorted array
			int pivot = partition(numArray, low, high);

			// If there are elements on left side of pivot,
			// then push left side to stack
			if (pivot - 1 > low) {
				intStack[++top] = low;
				intStack[++top] = pivot - 1;
			}

			// If there are elements on right side of pivot,
			// then push right side to stack
			if (pivot + 1 < high) {
				intStack[++top] = pivot + 1;
				intStack[++top] = high;
			}
		}
	}

	//////////////

	String names[];
	int length;

	void sort(String array[]) {
		if (array == null || array.length == 0) {
			return;
		}
		this.names = array;
		this.length = array.length;
		quickSort(0, length - 1);
	}

	void quickSort(int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;
		String pivot = this.names[lowerIndex + (higherIndex - lowerIndex) / 2];

		while (i <= j) {
			while (this.names[i].compareToIgnoreCase(pivot) < 0) {
				i++;
			}

			while (this.names[j].compareToIgnoreCase(pivot) > 0) {
				j--;
			}

			if (i <= j) {
				exchangeNames(i, j);
				i++;
				j--;
			}
		}
		//call quickSort recursively
		if (lowerIndex < j) {
			quickSort(lowerIndex, j);
		}
		if (i < higherIndex) {
			quickSort(i, higherIndex);
		}
	}

	void exchangeNames(int i, int j) {
		String temp = this.names[i];
		this.names[i] = this.names[j];
		this.names[j] = temp;
	}




	@Test (enabled= true)
	public static void Sorting(String tablename, String name, int skipColumns) throws InterruptedException, IOException {
		BaseTest base = new BaseTest();
		int totalNumberofColumns = size(By.cssSelector("#"+tablename+" th .log-header .mb-0")) + skipColumns;   //get total columns and skip irrelevant columns
		for (int i=1;i<=totalNumberofColumns; i++) {
			try {
				SoftAssert softAssert = new SoftAssert();
				BaseTest driver = new BaseTest();

				if (size(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0")) !=0) {
					WebElement column = driver.getDriver().findElement(By.cssSelector("#"+tablename+" th:nth-child("+i+") .log-header .mb-0"));
					test = extent.createTest("AN-Sorting-"+i+": Apply Sorting on "+column.getText()+" column", "This test case will verify that user can apply sorting on "+column.getText()+ " column");
					steps = test.createNode(Scenario.class, Steps);
					results = test.createNode(Scenario.class, Results);

					if (column.getText().contains("Time") || column.getText().contains("Date") || column.getText().contains("DATE") || column.getText().contains("CREATED")) {
						test.skip("Column sorted successfully");
						results.createNode("Column sorted successfully");
						base.saveResult(ITestResult.SKIP, null);
					}

					else {
						WebElement filter_scroll = column;
						((JavascriptExecutor)driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", filter_scroll);
						steps.createNode("1. Click on "+column.getText()+" column header");

						column.click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(500);

						if (size(By.cssSelector("#"+tablename+" .fa-sort-amount-down")) != 0) {
							softAssert.assertEquals(size(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_desc .log-header .mb-0")), 1, "Did not sorted in descending order");
							softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
							getScreenshot();
						}

						column.click();
						waitElementInvisible(loading_cursor);
						Thread.sleep(500);
						if (size(By.cssSelector("#"+tablename+" .fa-sort-amount-down")) != 0) {
							softAssert.assertEquals(size(By.cssSelector("#"+tablename+" th:nth-child("+i+").sort_asc .log-header .mb-0")), 1, "Did not sorted in descending order");
							softAssert.assertEquals(size(alertMessage), 0, "Exception message occured");
							getScreenshot();
						}

						//capture all web Elements into list
						List<WebElement> elementsList = driver.getDriver().findElements(By.xpath("//tr/td["+i+"]"));
						//capture text of all elements into new(original) list
						List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());
						//sorting on the original list and name it to sorted List
						//List<String> sortedList = originalList.stream().sorted(Collator.getInstance(Locale.UK)).collect(Collectors.toList());
						//Compare original list vs sorted list

						FilterSort s = new FilterSort();
						String[] words = new String[originalList.size()];

						s.sort(originalList.toArray(words));

						int z = 0;

						for (String x : words) {
							System.out.println(originalList.get(z) +"->"+ x );
							softAssert.assertTrue(originalList.get(z).equals(x), "Expected: "+x+ " but found \n" +originalList.get(z) + " in ascending order");
							z++;
						}

						softAssert.assertAll();
						test.pass("Column sorted successfully");
						results.createNode("Column sorted successfully");
						base.saveResult(ITestResult.SUCCESS, null);
					}
				}
			}
			catch(AssertionError er) {
				test.fail("Column failed to sort");
				results.createNode("Column failed to sort");
				base.saveResult(ITestResult.FAILURE, new Exception(er));
			}
		}
	}
}
