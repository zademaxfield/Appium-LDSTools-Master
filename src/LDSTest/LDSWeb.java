package LDSTest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class LDSWeb {
	private WebDriver driver;
	private Properties prop;
	private String myWindow;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Before
    public void setUp() throws Exception {
        // set up Selenium
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		System.setProperty("webdriver.chrome.driver", "chromedriver");

		
		driver = new ChromeDriver(options);
		
		//driver = new FirefoxDriver();

    }	
	
	@Test
	public void simpleTest() throws Exception {
		
		populateFile();
		Thread.sleep(5000);
		System.out.println("READ FILE: ");
		readFile();
		
		/*
		String myUserName = "ldstools2";
		String myPassword = "toolstester";
		List<String> myList = new ArrayList<String>();
		myList = getAllMembersOnPage("ReportsMenu", "Member List", myUserName, myPassword);
		for(String oneUser : myList){
			System.out.println("Found User: " + oneUser);
		}
		*/
		
	}

	
	public void openWebPage(String baseURL) {
		driver.get(baseURL);

		//Maximize the window
		Point targetPosition = new Point(0, 0);
		driver.manage().window().setPosition(targetPosition);

		Dimension targetSize = new Dimension(1680, 1050); //your screen resolution here
		driver.manage().window().setSize(targetSize);
		//driver.manage().window().maximize();
	}
	
	public void openPageLogIn(String url, String userName, String passWord) throws Exception {
		
		openGuiMap();
		setUp();
		
		Thread.sleep(4000);
		//openWebPage("https://uat.lds.org");
		openWebPage(url);
		
		//openWebPage("https://www.lds.org");
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys(userName);
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys(passWord);
		clickElement("SignIn", "id");
		
		Thread.sleep(4000);
		clickElement("IAgreeCheck", "id");
		clickElement("Agree and Continue", "text");
		
		clickElement("HomeButton", "xpath");
		
		myWindow = driver.getWindowHandle();
	}
	
	public void clickElement( String elementName, String elementFind) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = null;

		if (elementFind == "id") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.prop.getProperty(elementName))));
		}
		if (elementFind == "xpath") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(this.prop.getProperty(elementName))));
		}
		if (elementFind == "className") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.className(this.prop.getProperty(elementName))));
		}
		if (elementFind == "linkText") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementName)));
		}
		if (elementFind == "text") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + elementName + "')]")));
		}
		myElement.click();

	}
	
	
	private Boolean checkElementExists(String elementName, String elementFind ) {
		Boolean myReturnStatus;
		List<WebElement> options = null;

		
		if (elementFind == "id") {
			options = driver.findElements(By.id(this.prop.getProperty(elementName)));
		}
		if (elementFind == "xpath") {
			options = driver.findElements(By.xpath(this.prop.getProperty(elementName)));
		}
		if (elementFind == "className") {
			options = driver.findElements(By.className(this.prop.getProperty(elementName)));
		}
		if (elementFind == "linkText") {
			options = driver.findElements(By.linkText(elementName));
		}
		if (elementFind == "text") {
			options = driver.findElements(By.xpath("//*[contains(text(), '" + elementName + "')]"));
		}

		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	public void enterText( String elementName, String elementFind, String textToSend) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = null;

		if (elementFind == "id") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.prop.getProperty(elementName))));
		}
		if (elementFind == "xpath") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(this.prop.getProperty(elementName))));
		}
		if (elementFind == "className") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.className(this.prop.getProperty(elementName))));
		}
		if (elementFind == "linkText") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementName)));
		}
		if (elementFind == "text") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + elementName + "')]")));
		}
		myElement.clear();
		myElement.sendKeys(textToSend);

	}
	
	public String getText( String elementName, String elementFind) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = null;
		String myText;

		if (elementFind == "id") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.prop.getProperty(elementName))));
		}
		if (elementFind == "xpath") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(this.prop.getProperty(elementName))));
		}
		if (elementFind == "className") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.className(this.prop.getProperty(elementName))));
		}
		if (elementFind == "linkText") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementName)));
		}
		if (elementFind == "text") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + elementName + "')]")));
		}
		myText = myElement.getText();
		return myText;

	}
	
	public void selectList (String mainElement, String optionToSelect, String elementFind ){
		WebElement select = null;
		
		
		if (elementFind == "id") {
			select = driver.findElement(By.id(this.prop.getProperty(mainElement)));
		}
		if (elementFind == "xpath") {
			select = driver.findElement(By.xpath(this.prop.getProperty(mainElement)));
		}
		if (elementFind == "className") {
			select = driver.findElement(By.className(this.prop.getProperty(mainElement)));
		}
		if (elementFind == "linkText") {
			select = driver.findElement(By.linkText(mainElement));
		}
		if (elementFind == "text") {
			select = driver.findElement(By.xpath("//*[contains(text(), '" + mainElement + "')]"));
		}
		
		Select dropDown = new Select(select);
		String selected = dropDown.getFirstSelectedOption().getText();
		
		if (selected.equals(optionToSelect)) {
			//Item is all ready selected 
			System.out.println("Object is alreay selected!");
		} else {
			System.out.println("Trying to select: " + optionToSelect);
			List<WebElement> Options = dropDown.getAllSelectedOptions();
			for (WebElement option:Options) {
				System.out.println("Found Option: " + option.getText());
				if (option.getText().equals(optionToSelect)) {
					option.click();
				}
			}
		}
		
		
	}
	
	public void clickSearchedUser(String userName) throws Exception {
		clickElement(userName, "linkText");
		Thread.sleep(1000);
		clickElement("ViewMemberProfile", "xpath");
	}
	
	public void removeAllCompanionships() throws Exception {
		int myLoopStatus = 0;
		
		while (myLoopStatus == 0 ) {
			List<WebElement> options= driver.findElements(By.xpath(this.prop.getProperty("EditButton")));
			if (options.isEmpty()) {
				System.out.println("Button Not Found!");
				myLoopStatus = 1;	
			} else {
				clickElement("EditButton", "xpath");
				Thread.sleep(1000);
				clickElement("DeleteButton", "xpath");
				Thread.sleep(1000);
				clickElement("YesButton", "xpath");
				Thread.sleep(1000);
			}
		}
	}
	
	public void removeAllDistricts() throws Exception {
		int myLoopStatus = 0;
		
		while (myLoopStatus == 0 ) {
			List<WebElement> options= driver.findElements(By.xpath(this.prop.getProperty("FindDistricts")));
			if (options.isEmpty()) {
				System.out.println("District Not Found!");
				myLoopStatus = 1;	
			} else {
				clickElement("EditDistricts", "id");
				Thread.sleep(1000);
				clickElement("DeleteDistrict", "xpath");
				Thread.sleep(1000);
				clickElement("EditDistrictsDone", "id");
				Thread.sleep(1000);
			}
		}
	}
	
	public void addDistrict(String districtName, String districtSupervisor ) throws Exception {
		List<WebElement> options= driver.findElements(By.xpath(this.prop.getProperty("EditDistricts")));
		if (options.isEmpty()) {
			clickElement("AddDistricts", "id");	
		} else {
			clickElement("EditDistricts", "id");
		}
		
		Thread.sleep(3000);
		driver.findElement(By.id(this.prop.getProperty("NewDistrictTitle"))).clear();
		driver.findElement(By.id(this.prop.getProperty("NewDistrictTitle"))).sendKeys(districtName);
		Thread.sleep(1000);
		clickElement("AddDistrictButton", "xpath");
		Thread.sleep(3000);
		WebElement mySelect = driver.findElement(By.xpath(this.prop.getProperty("DistrictSupervisorSelect")));
		Select testSelect = new Select(mySelect);
		
		/*
		List<WebElement> options= testSelect.getOptions();
		for (int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i).getText());
		}
		*/
		
		//testSelect.selectByValue(districtSupervisor);
		testSelect.selectByVisibleText(districtSupervisor);
		clickElement("EditDistrictsDone", "id");
		Thread.sleep(3000);
	}
	
	private String getSourceOfPage() {
		String myString;
		myString = driver.getPageSource();
		return myString;
	}
	
	private String getSourceOfElement(String elementName) {
		String myString;
		WebElement myElement = null;
		
		myElement = driver.findElement(By.xpath(this.prop.getProperty(elementName)));
		myString = myElement.getAttribute("innerHTML");

		return myString;
	}
	
	private String getSourceOfMember(String elementName) {
		String myString;
		WebElement myElement = null;
		
		myElement = driver.findElement(By.id(this.prop.getProperty(elementName)));
		myString = myElement.getAttribute("innerHTML");

		return myString;
	}
	
	private List<String> getMembers(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getElementsByAttributeValueStarting("class", "member-card-remote");
		String outerHTML;
		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				foundUsers.add(outerHTML);
			}
			//System.out.println("Outer HTML:" + outerHTML);
		}
		
		
		
		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}
		
		
		return foundUsers;
		
	}
	
	private List<String> getMembersMovedOut(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getElementsByAttributeValueStarting("class", "fn name ng-binding");
		String outerHTML;
		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				foundUsers.add(outerHTML);
			}
			//System.out.println("Outer HTML:" + outerHTML);
		}
		
		
		
		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}
		
		
		return foundUsers;
		
	}
	
	
	private List<String> getMembersHTVT(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		List<String> removeUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		//Elements myTest1 = doc.getElementsByAttributeValueStarting("class", "member-list-body ng-scope odd");
		//Elements myTest2 = doc.getElementsByAttributeValueStarting("class", "member-list-body ng-scope even");
		Elements myTestRemove = doc.getElementsByAttributeValueStarting("class", "member-list-body ng-scope ng-hide");
		//Elements myTest3;
		
		Elements myTest = doc.getElementsByAttributeValueStarting("class", "teachee dropdown");

		String outerHTML;
		
		for (Element myElement : myTest ) {

			outerHTML = myElement.text();
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				foundUsers.add(outerHTML);
			}
			//System.out.println("Outer HTML:" + outerHTML);
		}
		
		
		for (Element myElement : myTestRemove ) {
			//myElement = myElement.attr("class", "ng-binding ng-isolate-scope popover-link");
			outerHTML = myElement.text();
			if (outerHTML.contains("Home")) {
				outerHTML = StringUtils.substringBefore(outerHTML, " Home");
			} else {
				outerHTML = StringUtils.substringBefore(outerHTML, " Visiting");
			}
			
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				removeUsers.add(outerHTML);
			}
			//System.out.println("Outer HTML:" + outerHTML);
		}
		
		for(String removeOneUser : removeUsers){
			System.out.println("Remove User: " + removeOneUser);
			foundUsers.remove(removeOneUser);
		}
		
		
		
		/*
		for (Element myElement : myTest1 ) {
			outerHTML = myElement.text();
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				foundUsers.add(outerHTML);
			}
			//System.out.println("Outer HTML:" + outerHTML);
		}
		
		for (Element myElement : myTest2 ) {
			outerHTML = myElement.text();
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				foundUsers.add(outerHTML);
			}
			//System.out.println("Outer HTML:" + outerHTML);
		}
		
		*/
		
		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}
		
		
		return foundUsers;
		
	}
	
	
	
	private List<String> getMemberInfo(String pageSource, List<String> foundUsers){
		//List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getElementsByAttributeValueEnding("class", "ng-binding");
		String outerHTML;
		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			if (outerHTML != null && !outerHTML.isEmpty()) {
				foundUsers.add(outerHTML);
				System.out.println("Outer HTML:" + outerHTML);
			}	
		}

		return foundUsers;
		
	}
	
	
	private List<String> getNameAndAge(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getElementsByAttributeValueStarting("class", "vcard ng-scope");
		Elements nameElement;
		Elements ageElement;
		
		
		for (Element myElement : myTest ) {
			nameElement = myElement.getElementsByAttributeValueContaining("class", "member-card-remote");
			ageElement = myElement.getElementsByAttributeValueContaining("class", "age ng-binding");
			
			//System.out.println("Name: " + nameElement.text());
			//System.out.println("Age: " + ageElement.text());
			foundUsers.add(nameElement.text() + " (" + ageElement.text() +")");

			//foundUsers.add(outerHTML);
			

		}
		
		
		/*
		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}
		*/
		
		
		return foundUsers;
	}
	
	
	private void waitForTextToDisappear(String textElement, int myTimeOut, String eleType ) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, myTimeOut);
		
		if (eleType.equals("id")) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(this.prop.getProperty(textElement))));
		}
		
		if (eleType.equals("xpath")) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.prop.getProperty(textElement))));
		}
		
		Thread.sleep(2000);
		
	}
	
	
	public List<String> getAllMembersOnPage(String menuItem, String myReport, Boolean newSession) throws Exception {		
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		//driver.switchTo().window(myWindow);
		System.out.println("Menu Item: " + menuItem);
	
		//clickElement("ReportsMenu", "id");
		Thread.sleep(2000);
		clickElement(menuItem, "id");
		Thread.sleep(4000);
		//clickElement("Member List", "linkText");
		clickElement(myReport, "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		mySource = getSourceOfPage();
		foundUsers = getMembers(mySource);
		
		clickElement("HomeButton", "xpath");
		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	public List<String> getAllMembersInOrganization(String menuItem, String myReport, String subReport, Boolean newSession) throws Exception {
		Boolean myElementCheck;
		Boolean myReportCheck;
		
		
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		Thread.sleep(2000);
		clickElement(menuItem, "id");
		Thread.sleep(4000);
		//clickElement("Member List", "linkText");
		myReportCheck = checkElementExists(myReport, "linkText");
		if(myReportCheck == false) {
			myReport = myReport + " 1";
			System.out.println("My Report: " + myReport	);
		}
		
		clickElement(myReport, "linkText");
		Thread.sleep(1000);
		waitForTextToDisappear("Loading", 500, "id" );
		Thread.sleep(3000);
		//clickElement(subReport, "linkText");
		//Thread.sleep(4000);
		//waitForTextToDisappear("Loading", 500, "id" );
		//Thread.sleep(1000);
		myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
		
		//Check to see if the page loaded
		//TODO: Need to have a few samples for each report or file with last know good data
		if (myElementCheck == true ) {
			System.out.println(myReport + " " + subReport +" Page did not load... Skipping");
			foundUsers.clear();
		} else {
			if (subReport.contains("Member")) {
				clickElement("Members", "linkText");
				if (myReport.contains("Sunday School")) {
					clickElement("All Organizations", "linkText");
				}
			} else {
				clickElement("All Organizations", "linkText");
			}
			
			Thread.sleep(2000);
			mySource = getSourceOfElement(subReport);
			//mySource = getSourceOfPage();
			foundUsers = getMembers(mySource);	
		}
		
		clickElement("HomeButton", "xpath");
		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	public List<String> getAllMembersInReport(String menuItem, String myReport, String subReport, Boolean newSession) throws Exception {
		Boolean myElementCheck;
		Boolean myReportCheck;
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		
		Thread.sleep(2000);
		clickElement(menuItem, "id");
		Thread.sleep(4000);
		//clickElement("Member List", "linkText");
		myReportCheck = checkElementExists(myReport, "linkText");
		if(myReportCheck == false) {
			myReport = myReport + " 1";
			System.out.println("My Report: " + myReport	);
		}
		
		clickElement(myReport, "linkText");
		Thread.sleep(1000);
		waitForTextToDisappear("Loading", 500, "id" );
		Thread.sleep(3000);
		myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
		
		//Check to see if the page loaded
		//TODO: Need to have a few samples for each report or file with last know good data
		if (myElementCheck == true ) {
			System.out.println(myReport + " " + subReport +" Page did not load... Skipping");
			foundUsers.clear();
		} else {
			if (subReport.equals("MembersMovedOut")) {
				Thread.sleep(2000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembersMovedOut(mySource);	
			}
			
			if (subReport.equals("MembersMovedIn")) {
				Thread.sleep(2000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembers(mySource);	
			}
			
			if (subReport.equals("MemberswithCallings")) {
				Thread.sleep(2000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembers(mySource);	
			}
			
			

		}
		
		clickElement("HomeButton", "xpath");
		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	
	
	public List<String> getMembersAndAge(String menuItem, String myReport) throws Exception {
		openGuiMap();
		setUp();
		
		
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		Thread.sleep(4000);
		//openWebPage("https://uat.lds.org");
		openWebPage("https://uat.lds.org/mls/mbr/?lang=eng");
		
		//openWebPage("https://www.lds.org");
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys("ldstools2");
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys("toolstester");
		clickElement("SignIn", "id");
		
		Thread.sleep(4000);
		clickElement("IAgreeCheck", "id");
		clickElement("Agree and Continue", "text");

		//clickElement("ReportsMenu", "id");
		clickElement(menuItem, "id");
		Thread.sleep(4000);
		//clickElement("Member List", "linkText");
		clickElement(myReport, "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		mySource = getSourceOfPage();
		foundUsers = getNameAndAge(mySource);
		
		tearDown();
		
		return foundUsers;
		
	}
	
	public List<String> getMemberDetails(String memberDetail, String userName, String passWord) throws Exception {
		
		String mySource;
		String url = "https://uat.lds.org/mls/mbr/?lang=eng";
		List<String> foundUsers = new ArrayList<String>();
		String memberName;
		String preferredName;
		//String birthDate;
		String updateString;
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
		SimpleDateFormat origFormat = new SimpleDateFormat("dd MMM yyyy");
		
		userName = "LDSTools2";
		passWord = "toolstester";
		
		openPageLogIn(url, userName, passWord);
		
		//Browse to the Membership page
		clickElement("Reports", "linkText");
		Thread.sleep(4000);
		clickElement("Member List", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		
		//Search for the user
		enterText("SearchForMember", "id", memberDetail);
		//Select the searched for user
		clickSearchedUser(memberDetail);
		
		//Need to refresh the page before the Individual Members page will be visible to Selenium
		driver.navigate().refresh();
		
		mySource = getSourceOfMember("MemberIndividualInfo");
		foundUsers = getMemberInfo(mySource, foundUsers);
		
		memberName = (getText("DetailsMemberName", "xpath"));
		preferredName = (getText("DetailsPreferredName", "xpath"));
		//birthDate = (getText("DetailsBirthDate", "xpath"));
		
		
		Thread.sleep(2000);
		
		/* Skipping Household for now - need to swap last and first names
		clickElement("Household", "linkText");
		if (checkElementExists("Family", "linkText") == true) {
			clickElement("Family", "linkText");
			mySource = getSourceOfMember("MemberIndividualHousehold");
			foundUsers = getMemberInfo(mySource, foundUsers);
			Thread.sleep(2000);
		}
		*/

		
		if (checkElementExists("Ordinances", "linkText") == true) {
			clickElement("Ordinances", "linkText");
			mySource = getSourceOfMember("MemberIndividualOrdinances");
			foundUsers = getMemberInfo(mySource, foundUsers);
			Thread.sleep(2000);
		}

		if (checkElementExists("Callings/Classes", "linkText") == true) {
			clickElement("Callings/Classes", "linkText");
			mySource = getSourceOfMember("MemberIndividualCallings");
			foundUsers = getMemberInfo(mySource, foundUsers);
			Thread.sleep(2000);
		}
		//System.out.println("Found Users Size: " + foundUsers.size());
		
		//Find the last name of the member
		//If the user has 3 names displayed this will break
		//Need to check for multiple names and Jr Junior etc...
		String[] myMemberName = memberName.split(" ");
		String memberFirstName = myMemberName[0];
		String memberLastName = myMemberName[1];
		
		
		for (int myCounter = 0; myCounter < foundUsers.size(); myCounter++ ) {
			//if (foundUsers.get(myCounter).contains(memberName)) {
			if (foundUsers.get(myCounter).contains(memberLastName)) {
				String[] parts = foundUsers.get(myCounter).split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				updateString = part2 + ", " + part1;
				System.out.println("Member Name: " + updateString);
				foundUsers.set(myCounter, updateString);
			}
			
			if (preferredName.equals("")) {
				//System.out.println("No Preferred Name");
			} else {
				if (foundUsers.get(myCounter).contains(preferredName)) {
					String[] parts = foundUsers.get(myCounter).split(" ");
					String part1 = parts[0];
					String part2 = parts[1];
					updateString = part2 + ", " + part1;
					System.out.println("Preferred Name: " + updateString);
					foundUsers.set(myCounter, updateString);
				}
			}

			
			//if (foundUsers.get(myCounter).contains(birthDate)) {
			if (foundUsers.get(myCounter).matches("[0-9]{1,2} [a-zA-Z]{3} [0-9]{4}")) {
				Date date = origFormat.parse(foundUsers.get(myCounter));
				updateString = format.format(date);
				System.out.println("NEW DATE: " + updateString);
				foundUsers.set(myCounter, updateString);
			}
			
			if (foundUsers.get(myCounter).matches("[0-9]{1,2} [a-zA-Z]{3} [0-9]{4} .*")) {
				updateString = "";
				foundUsers.set(myCounter, updateString);
			}
			
			if (foundUsers.get(myCounter).contains("Member Name") || (foundUsers.get(myCounter).contains("Preferred Name") ||  
					(foundUsers.get(myCounter).contains("Sunday School")))) {
				updateString = "";
				foundUsers.set(myCounter, updateString);
			}
			
			if (foundUsers.get(myCounter).contains("Fagamalo 1st Ward (56030)")) {
				updateString = foundUsers.get(myCounter).replace("Fagamalo 1st Ward (56030)", "Fagamalo  1st Ward");
				System.out.println("Unit Name: " + updateString);
				foundUsers.set(myCounter, updateString);
			}
			
			//System.out.println("My Counter: " + myCounter);
			
		}
		
		
		/*
		System.out.println(getText("DetailsMemberName", "xpath"));
		System.out.println(getText("DetailsPreferredName", "xpath"));
		System.out.println(getText("DetailsBirthDate", "xpath"));
		System.out.println(getText("DetailsAge", "xpath"));
		System.out.println(getText("DetailsGender", "xpath"));
		System.out.println(getText("DetailsEndowed", "xpath"));
		
		System.out.println(getText("DetailsIndividualPhone", "xpath"));
		System.out.println(getText("DetailsIndividualEmail", "xpath"));
		System.out.println(getText("DetailsHomePhone", "xpath"));
		System.out.println(getText("DetailsHomeEmail", "xpath"));
		System.out.println(getText("DetailsResAddress", "xpath"));
		System.out.println(getText("DetailsMailAddress", "xpath"));
		
		System.out.println(getText("DetailsBirthplace", "xpath"));
		System.out.println(getText("DetailsBirthCountry", "xpath"));
		System.out.println(getText("DetailsCurrentUnit", "xpath"));
		System.out.println(getText("DetailsHomeTeachers1", "xpath"));
		System.out.println(getText("DetailsHomeTeachers2", "xpath"));
		System.out.println(getText("DetailsVisitingTeachers1", "xpath"));
		System.out.println(getText("DetailsVisitingTeachers2", "xpath"));
		

		
		clickElement("Ordinances", "linkText");
		System.out.println("Ordinances: ");
		System.out.println(getText("DetialsBaptismDate", "xpath"));
		System.out.println(getText("DetailsConfirmationDate", "xpath"));

		clickElement("Callings/Classes", "linkText");
		System.out.println(getText("DetailsCalling", "xpath"));
		System.out.println(getText("DetailsSustained", "xpath"));
		System.out.println(getText("DetailsClassOrg", "xpath"));
		System.out.println(getText("DetailsClassClass", "xpath"));
		System.out.println(getText("DetailsClassOrg", "xpath"));
		System.out.println(getText("DetailsClassClass", "xpath"));
		
		*/

		

		
		
		
		tearDown();
		
		return foundUsers;
		
	}
	
	
	
	
	public List<String> getAllMembersInHTVTReport(String orgName, String myReport, String userName, String passWord, String leaderShip) throws Exception {
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		String url = "https://uat.lds.org/mls/mbr/?lang=eng";
		Boolean myElementCheck;
		
		userName = "LDSTools2";
		passWord = "toolstester";

		openPageLogIn(url, userName, passWord);

		//clickElement("ReportsMenu", "id");
		
		clickElement("OrganizationsMenu", "id");
		
		if (leaderShip.equals("High Priest Group") && (orgName.equals("High Priests Group")))  {
			

			clickElement(orgName, "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(2000);
			myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
			if (myElementCheck == true ) {
				System.out.println(orgName + " " + myReport + "Page did not load... skipping");
				foundUsers.clear();
				tearDown();
				return foundUsers;
			}
			clickElement("HomeTeachingDropDown", "xpath");
			clickElement("Households", "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(9000);
		}
		
		if (leaderShip.equals("High Priest Group") && (orgName.equals("Elders Quorum")))  {
			

			clickElement("High Priests Group", "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(2000);
			myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
			if (myElementCheck == true ) {
				System.out.println(orgName + " " + myReport + "Page did not load... skipping");
				foundUsers.clear();
				tearDown();
				return foundUsers;
			}
			clickElement("HomeTeachingDropDown", "xpath");
			clickElement("Households", "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(9000);
			
			//selectList("SelectHTAux", "Elders", "xpath");
			clickElement("SelectHTAux", "xpath");
			clickElement("SelectHTAuxElders", "xpath");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(9000);
		}
		
		if (leaderShip.equals("Elders Quorum Pres") && (orgName.equals("High Priests Group")))  {
			

			clickElement("Elders Quorum", "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(2000);
			myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
			if (myElementCheck == true ) {
				System.out.println(orgName + " " + myReport + "Page did not load... skipping");
				foundUsers.clear();
				tearDown();
				return foundUsers;
			}
			clickElement("HomeTeachingDropDown", "xpath");
			clickElement("Households", "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(9000);
			
			//selectList("SelectHTAux", "High Priests", "xpath");
			clickElement("SelectHTAux", "xpath");
			clickElement("SelectHTAuxHighPriests", "xpath");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(9000);
		}
		
		if (leaderShip.equals("Elders Quorum Pres") && (orgName.equals("Elders Quorum")))  {
			

			clickElement(orgName, "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(2000);
			myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
			if (myElementCheck == true ) {
				System.out.println(orgName + " " + myReport + "Page did not load... skipping");
				foundUsers.clear();
				tearDown();
				return foundUsers;
			}
			clickElement("HomeTeachingDropDown", "xpath");
			clickElement("Households", "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(9000);
		}
		
		if (leaderShip.equals("Bishopric") || (leaderShip.equals("Relief Society Pres"))) {
			

			clickElement(orgName, "linkText");
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(2000);
			myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
			if (myElementCheck == true ) {
				System.out.println(orgName + " " + myReport + "Page did not load... skipping");
				foundUsers.clear();
				tearDown();
				return foundUsers;
			}
			Thread.sleep(4000);
			
			if (orgName.contains("Relief")) {
				clickElement("VisitingTeachingDropDown", "xpath");
				clickElement("Households", "linkText");
				waitForTextToDisappear("LoadingSpinner", 500, "xpath");
				Thread.sleep(9000);
			} else {
				clickElement("HomeTeachingDropDown", "xpath");
				clickElement("Households", "linkText");
				waitForTextToDisappear("LoadingSpinner", 500, "xpath");
				Thread.sleep(9000);
			}
		}

		
		//This take a long time to load and doesn't have the loading tag
		Thread.sleep(2000);
		
		clickElement("OptionsButton", "xpath");
		clickElement(myReport, "id");

		Thread.sleep(5000);
		//mySource = getSourceOfElement(subReport);
		mySource = getSourceOfPage();
		foundUsers = getMembersHTVT(mySource);
		
		tearDown();
		
		return foundUsers;
		
	}
	
	public void populateFile() throws Exception {
		openGuiMap();
		setUp();

		Thread.sleep(4000);
		//openWebPage("https://uat.lds.org");
		openWebPage("https://uat.lds.org/mls/mbr/?lang=eng");
		
		//openWebPage("https://www.lds.org");
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys("LDSTools2");
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys("toolstester");
		clickElement("SignIn", "id");
		
		Thread.sleep(4000);
		clickElement("IAgreeCheck", "id");
		clickElement("Agree and Continue", "text");
		Thread.sleep(3000);
		
		
		//Get Organization Data
		//High Priests Group
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "High Priests Group", "HighPriestGroupLeadership");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "High Priests Group", "HighPriestGroupDistrictSupervisors");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "High Priests Group", "HighPriestGroupMembers");
		
		//Elders Quorum
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Elders Quorum", "EldersQuorumPresidency");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Elders Quorum", "EldersQuorumDistrictSupervisors");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Elders Quorum", "EldersQuorumMembers");
		
		//Relief Society
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Relief Society", "ReliefSocietyPresidency");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Relief Society", "VisitingTeachingSupervisors");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Relief Society", "ReliefSocietyMembers");

		//Young Men
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Men", "YoungMenPresidency");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Men", "PriestsQuorum");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Men", "TeachersQuorum");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Men", "DeaconsQuorum");
		
		//Young Women
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Women", "YoungWomenPresidency");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Women", "Laurel");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Women", "MiaMaid");
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Young Women", "Beehive");
		
		//Sunday School
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Sunday School", "SundaySchoolPresidency");
		
		//Primary 
		getAllMembersInOrganizationPopulate("OrganizationsMenu", "Primary", "PrimaryPresidency");
		
	}
	
	public void writeToFile(String menuItem, String myReport, String subReport, List<String> foundUsers) {
		
		String myFileName = "ConfigFiles/WebData.csv";
		//List<String> foundUsers = new ArrayList<String>();
		
		try {
			FileWriter writer = new FileWriter(myFileName, true);
			for(String oneUser : foundUsers){
				System.out.println("Found User: " + oneUser);
				writer.append(menuItem);
				writer.append(';');
				writer.append(myReport);
				writer.append(';');
				writer.append(subReport);
				writer.append(';');
				writer.append(oneUser);
				writer.append('\n');
			}
			
		    writer.flush();
		    writer.close();
				
		} catch(IOException e) {
			 e.printStackTrace();
		}
	}
	
    public void removeLineFromFile(String lineToRemove) {
    	String file = "ConfigFiles/WebData.csv";
		try {
			File inFile = new File(file);
			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}
			//Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			
			String line = null;
			//Read from the original file and write to the new
			//unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				if (!line.trim().contains(lineToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			
			pw.close();
			br.close();
			
			
			//Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			
			//Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile)){
				System.out.println("Could not rename file");
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void readFile() {
		String myFileName = "ConfigFiles/WebData.csv";
		BufferedReader br = null;
		String line = "";
		String splitBy = ";";
		
		try {
			br = new BufferedReader(new FileReader(myFileName));
			while ((line = br.readLine()) != null) {
				String[] myUser = line.split(splitBy);
				System.out.println("Zero: " + myUser[0]);
				System.out.println("One: " + myUser[1]);
				System.out.println("Two: " + myUser[2]);
				System.out.println("USER: " + myUser[3]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done!");
	}
	

	public void getAllMembersInOrganizationPopulate(String menuItem, String myReport, String subReport) throws Exception {
		Boolean myElementCheck;
		Boolean myReportCheck;
		String mySource;
		List<String> foundUsers = new ArrayList<String>();


		clickElement(menuItem, "id");
		Thread.sleep(4000);

		myReportCheck = checkElementExists(myReport, "linkText");
		if(myReportCheck == false) {
			myReport = myReport + " 1";
			System.out.println("My Report: " + myReport	);
		}
		
		clickElement(myReport, "linkText");
		Thread.sleep(1000);
		waitForTextToDisappear("Loading", 500, "id" );
		Thread.sleep(3000);
		myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
		
		//Check to see if the page loaded
		if (myElementCheck == true ) {
			System.out.println(myReport + " " + subReport +" Page did not load... Skipping");
			foundUsers.clear();
		} else {
			if (subReport.contains("Member")) {
				clickElement("Members", "linkText");
			} else {
				clickElement("All Organizations", "linkText");
			}
			
			Thread.sleep(2000);
			mySource = getSourceOfElement(subReport);
			//mySource = getSourceOfPage();
			foundUsers = getMembers(mySource);	
		}
		
		removeLineFromFile(subReport);
		writeToFile(menuItem, myReport, subReport, foundUsers);
		
		clickElement("HomeButton", "xpath");
		
	}

	
	
	
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Before
	public void openGuiMap() {
		
		File file = new File("ConfigFiles/webUIMap.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.prop = new Properties();
		
		try {
			prop.load(fileInput);
		} catch(IOException e) {
			e.printStackTrace();
		}

	}


}
