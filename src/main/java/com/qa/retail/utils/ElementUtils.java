package com.qa.retail.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.exceptions.FrameworkException;

public class ElementUtils {
	
	private WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}

	public By getBy(String locatorType, String locatorValue) {
		By by = null; // 8

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Wrong locator type is passed ... " + locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}
		return by;
	}

	public void doClick(By locators) {
		getElement(locators).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(getBy(locatorType, locatorValue)).click();
	}

	public String doElementGetText(By locators) {
		return getElement(locators).getText();
	}

	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(getBy(locatorType, locatorValue)).getText();
	}

	public String doGetElementAttribute(By locator, String value) {
		return getElement(locator).getAttribute(value);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>(); // PC = 0
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	public List<String> getElementsAttribute(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttributeList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attValue = e.getAttribute(attrName);
			eleAttributeList.add(attValue);
		}
		return eleAttributeList;
	}

	public void search(By searchFieldLocator, By suggestionLocator, String searchPhrase, String suggNameForClick) {
		getElement(searchFieldLocator).sendKeys(searchPhrase);
		try {
			Thread.sleep(2000);
		} catch (Exception e) {

		}
		List<WebElement> suggList = getElements(suggestionLocator);
		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggNameForClick)) {
				e.click();
				break;
			}
		}
	}

	public boolean checkingSingleElementPresent(By locator) {
		return getElements(locator).size() == 1 ? true : false;
	}

	public boolean checkingMultipleElementPresent(By locator) {
		return getElements(locator).size() >= 1 ? true : false;
	}

	public boolean checkingMultipleElementPresent(By locator, int totalElements) {
		return getElements(locator).size() == totalElements ? true : false;
	}

	// ***************** Select Class Utilities ********************
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByContainsVisibleText(By locator, String partialText) {
		Select select = new Select(getElement(locator));
		select.selectByContainsVisibleText(partialText);
	}

	public void selectDropDownOption(By locator, String dropDownValue) {
		Select select = new Select(getElement(locator));
		List<WebElement> countryList = select.getOptions();
		System.out.println("Total number of countries in the country DD: " + countryList.size());
		for (WebElement e : countryList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(dropDownValue)) {
				e.click();
				break;
			}
		}
	}

	public List<String> getDropDownOption(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<>();// PC = 0
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}

	public int getDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	public void selectDropDownValue(By locator, String valueForSelection) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.contains(valueForSelection)) {
				e.click();
				break;
			}
		}
	}

	public boolean isDropDownMultiple(By locator) {
		Select select = new Select(getElement(locator));
		return select.isMultiple() ? true : false;
	}

	public void selectDropDownMultipleValues(By locator, String... values) {
		Select select = new Select(getElement(locator));
		if (isDropDownMultiple(locator)) {
			for (String value : values) {
				select.selectByVisibleText(value);
			}
		}
	}

	/**
	 * This method is used to select the values from the select multiple drop down.
	 * It can select; 1: Single option 2: Multiple options 3: All the the options --
	 * pass "all" as the value to select all the drop down options
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param values
	 */
	public void selectDropDownMultipleValues(By locator, By optionsLocator, String... values) {
		Select select = new Select(getElement(locator));
		if (isDropDownMultiple(locator)) {
			if (values[0].equalsIgnoreCase("all")) {
				List<WebElement> optionsList = getElements(optionsLocator);
				for (WebElement e : optionsList) {
					e.click();
				}
			} else {
				for (String value : values) {
					select.selectByVisibleText(value);
				}
			}
		}
	}

	public void doMoveToElement(By parentMenuLocator, By childMenuLocator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).perform();
	}

	public void doDragAndDrop(By sourceLocator, By targetLocator) {
		Actions act = new Actions(driver);
		WebElement source = getElement(sourceLocator);
		WebElement target = getElement(targetLocator);
		act.dragAndDrop(source, target).perform();
	}

	public void multiMenuHandle(By parentMenuLocator, By firstChildMenuLocator, By secondChildMenuLocator,
			By fourthChildMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		doClick(parentMenuLocator);
		Thread.sleep(1000);
		act.moveToElement(getElement(firstChildMenuLocator)).perform();
		Thread.sleep(1000);
		act.moveToElement(getElement(secondChildMenuLocator)).perform();
		Thread.sleep(1000);
		doClick(fourthChildMenuLocator);
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void doActionsSendKeysWithPause(By locator, String value) {
		Actions act = new Actions(driver);
		char[] val = value.toCharArray();
		for (char c : val) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(300).perform();
		}
	}

	public void doDoubleClick(By locator) {
		Actions act = new Actions(driver);
		act.doubleClick(getElement(locator)).perform();
	}

	public void doScrollToSpecificElement(By locator) {
		Actions act = new Actions(driver);
		act.scrollToElement(getElement(locator)).perform();
	}

	public void doUploadFile(By locator, String filePath) {
		getElement(locator).sendKeys(filePath);
	}

	// ************************** Wait utils ***********
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void doSendKeysWithWait(By locator, String valueToSend, int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).sendKeys(valueToSend);
	}

	public String waitForTitleContains(String titlePartialValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleContains(titlePartialValue))) {
				return driver.getTitle();
			}
		} catch (RuntimeException e) {
			System.out.println("Page loading takes time and the title is not loaded yet.");
		}
		return titlePartialValue;
	}

	public String waitForTitleCompleteValue(String titleCompleteValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(titleCompleteValue))) {
				return driver.getTitle();
			}
		} catch (RuntimeException e) {
			System.out.println("Page loading takes time and the title is not loaded yet.");
		}
		return null;
	}

	public String waitForURLContains(String urlPartialValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlContains(urlPartialValue))) {
				return driver.getCurrentUrl();
			}
		} catch (RuntimeException e) {
			System.out.println("Page loading took too long, and the expected URL was not found.");
		}
		return null;
	}

	public String waitForURLCompleteURL(String urlCompleteURLVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlToBe(urlCompleteURLVal))) {
				return driver.getCurrentUrl();
			}
		} catch (RuntimeException e) {
			System.out.println("Page loading took too long, and the expected URL was not found.");
		}
		return null;
	}

	public Alert waitForJSAlerts(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJSAlert(int timeOut) {
		waitForJSAlerts(timeOut).accept();
	}

	public void dismissJSAlert(int timeOut) {
		waitForJSAlerts(timeOut).dismiss();
	}

	public void getJSAlertText(int timeOut) {
		waitForJSAlerts(timeOut).getText();
	}

	public void enterValueOnJSPrompt(int timeOut, String valueToSend) {
		waitForJSAlerts(timeOut).sendKeys(valueToSend);
	}

	public boolean checkNewWindowExist(int timeOut, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
				return true;
			}
		} catch (RuntimeException e) {
			System.out.println("The expected number of browsers windows were not reached within the specified time");
		}
		return false;
	}

	public void waitForFrameByLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameByIndex(By frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameByIdOrName(String IDOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void waitForFrameByWebElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime)).withMessage("Time is done -- Element is not found ...")
				.ignoring(TimeoutException.class).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForFrameWithFluentWait(String IDOrName, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime)).withMessage("Time is done -- Element is not found ...")
				.ignoring(TimeoutException.class).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public Alert waitForAlertsWithFluentWait(int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime)).withMessage("Time is done -- Element is not found ...")
				.ignoring(TimeoutException.class).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"))
				.toString();
		return Boolean.parseBoolean(flag);
	}

	public void setPageLoadTimeout(int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}
	
	public String getColorName(String hex) {
		switch(hex.toLowerCase().trim()) {
		case "#ffffff":
			return "White";
		case "#000000":
			return "Black";
		case "#563d7c":
			return "Purple";
		case "#FF0000":
			return "Red";
		case "#00FF00":
			return "Green";
		case "#0000FF":
			return "Blue";
		default:
			return "unknown color";
		}
	}
	
	public void clickElementByJS(By locator) {
	    WebElement element = waitForVisibilityOfElement(locator, AppConstants.MEDIUM_DEFAULT_WAIT);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}

}



