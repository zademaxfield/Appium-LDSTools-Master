package LDSTest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Before
    public void setUp() throws Exception {
        // set up Selenium
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		//System.setProperty("webdriver.chrome.driver", "/Users/zmaxfield/Selenium/chromedriver");
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		driver = new ChromeDriver(options);
		
		//driver = new FirefoxDriver();

    }	
	
	@Test
	public void simpleTest() throws Exception {
		List<String> myList = new ArrayList<String>();
		myList = getAllMembersOnPage("ReportsMenu", "Member List");
		for(String oneUser : myList){
			System.out.println("Found User: " + oneUser);
		}
		
	}

	
	public void openWebPage(String baseURL) {
		driver.get(baseURL);
		//Maximize the window
		driver.manage().window().maximize();
	}
	
	public void openPageLogIn(String url, String userName, String passWord) throws Exception {
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
	
	
	private void waitForTextToDisappear(String textElement, int myTimeOut){
		WebDriverWait wait = new WebDriverWait(driver, myTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(this.prop.getProperty(textElement))));
	}
	
	
	public List<String> getAllMembersOnPage(String menuItem, String myReport) throws Exception {
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
		waitForTextToDisappear("Loading", 500 );
		mySource = getSourceOfPage();
		foundUsers = getMembers(mySource);
		
		tearDown();
		
		return foundUsers;
		
	}
	
	public List<String> getAllMembersInOrganization(String menuItem, String myReport, String subReport, String userName, String passWord) throws Exception {
		openGuiMap();
		setUp();
		
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		Thread.sleep(4000);
		//openWebPage("https://uat.lds.org");
		openWebPage("https://uat.lds.org/mls/mbr/?lang=eng");
		
		//openWebPage("https://www.lds.org");
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys(userName);
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys(passWord);
		clickElement("SignIn", "id");
		
		Thread.sleep(4000);
		clickElement("IAgreeCheck", "id");
		clickElement("Agree and Continue", "text");

		//clickElement("ReportsMenu", "id");
		clickElement(menuItem, "id");
		Thread.sleep(4000);
		//clickElement("Member List", "linkText");
		clickElement(myReport, "linkText");
		Thread.sleep(1000);
		waitForTextToDisappear("Loading", 500 );
		Thread.sleep(1000);
		//clickElement(subReport, "linkText");
		//Thread.sleep(4000);
		waitForTextToDisappear("Loading", 500 );
		
		if (subReport.contains("Member")) {
			clickElement("Members", "text");
		} else {
			clickElement("All Organizations", "linkText");
		}
		
		Thread.sleep(2000);
		mySource = getSourceOfElement(subReport);
		//mySource = getSourceOfPage();
		foundUsers = getMembers(mySource);
		
		tearDown();
		
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
		waitForTextToDisappear("Loading", 500 );
		mySource = getSourceOfPage();
		foundUsers = getNameAndAge(mySource);
		
		tearDown();
		
		return foundUsers;
		
	}
	
	public List<String> getMemberDetails(String memberDetail, String userName, String passWord) throws Exception {
		openGuiMap();
		setUp();
		
		String mySource;
		String url = "https://uat.lds.org/mls/mbr/?lang=eng";
		List<String> foundUsers = new ArrayList<String>();
		
		openPageLogIn(url, userName, passWord);
		
		//Browse to the Membership page
		clickElement("Membership", "linkText");
		Thread.sleep(4000);
		clickElement("Member List", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500 );
		
		//Search for the user
		enterText("SearchForMember", "id", memberDetail);
		//Select the searched for user
		clickSearchedUser(memberDetail);
		
		//Need to refresh the page before the Individual Members page will be visible to Selenium
		driver.navigate().refresh();
		mySource = getSourceOfMember("MemberIndividualInfo");
		foundUsers = getMemberInfo(mySource, foundUsers);
		
		Thread.sleep(2000);
		
		clickElement("Household", "linkText");
		clickElement("Family", "linkText");
		mySource = getSourceOfMember("MemberIndividualHousehold");
		foundUsers = getMemberInfo(mySource, foundUsers);
		
		Thread.sleep(2000);
		
		clickElement("Ordinances", "linkText");
		mySource = getSourceOfMember("MemberIndividualOrdinances");
		foundUsers = getMemberInfo(mySource, foundUsers);
		
		Thread.sleep(2000);
		
		clickElement("Callings/Classes", "linkText");
		mySource = getSourceOfMember("MemberIndividualCallings");
		foundUsers = getMemberInfo(mySource, foundUsers);
		
		
		
		
		tearDown();
		
		return foundUsers;
		
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
