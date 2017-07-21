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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class LDSWeb {
	WebDriver driver;
	private Properties prop;
	private String myWindow;
	private String driverRunning = null;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Before
    public void setUp() throws Exception {
		//Moved setUp stuff to openWebPage - seems to work better this way. 
		driverRunning = "Running";

	
		//driver = new FirefoxDriver();

    }	
	
	@Test
	public void simpleTest() throws Exception {
		
		/*
		String url = "https://uat.lds.org/directory/";
		String userName = "LDSTools2";
		String passWord = "toolstester";
		List<String> myList = new ArrayList<String>();
		
		openPageLogInDirectory(url, userName, passWord);
	
		Thread.sleep(10000);
		
		myList = getAllMembersOnPageDirectory("Ward Leaders", "High Priests Group", true);
		
		for(String oneUser : myList){
			System.out.println("Found User: " + oneUser);
		}
		*/
		
		/*
		List<String> myTempleList = new ArrayList<String>();
		String mySource;
		MyTemplePageLogIn("https://uat.lds.org/mls/mbr", "ldstools2", "toolstester");
		clickElement("Find a Temple", "linkText");
		
		mySource = getSourceOfMember("AllTemplesTable");
		myTempleList = getAllTemples(mySource);
		
		String myFileName = "ConfigFiles/AllTemples.csv";
		//List<String> foundUsers = new ArrayList<String>();
		
		try {
			FileWriter writer = new FileWriter(myFileName, true);
			for(String oneTemple : myTempleList){
				System.out.println("Temple: " + oneTemple);
				writer.append(oneTemple);
				writer.append('\n');
			}
			
		    writer.flush();
		    writer.close();
				
		} catch(IOException e) {
			 e.printStackTrace();
		}
		
		*/
		
		/*
		String url = "https://uat.lds.org/mls/mbr/?lang=eng";
		String userName = "LDSTools2";
		String passWord = "toolstester";
		
		openPageLogIn(url, userName, passWord);
	
		Thread.sleep(10000);
		*/
		
		
		setupAfterUATReset();
		//AreaBookSetup();

		/*
		populateFile();
		Thread.sleep(5000);
		System.out.println("READ FILE: ");
		readFile();
		
		
		String myUserName = "ldstools2";
		String myPassword = "toolstester";
		List<String> myList = new ArrayList<String>();
		myList = getAllMembersOnPage("ReportsMenu", "Member List", myUserName, myPassword);
		for(String oneUser : myList){
			System.out.println("Found User: " + oneUser);
		}
		*/
		
	}
	
	public void setupAfterUATReset() throws Exception {
		String url = "https://uat.lds.org/mls/mbr/?lang=eng";
		String userName = "LDSTools2";
		String passWord = "toolstester";
		
		openPageLogIn(url, userName, passWord);
		
		setupMembers();
		setupAJcalling();
		setupCallings();
		setupDistricts();
		addCompanionHousehold();
		addJaneAaronToClass();
	}
	
	public void addJaneAaronToClass() throws Exception {
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Sunday School", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Members", "linkText");
		clickElement("Gospel Doctrine", "linkText");
		
		
		addMemberToOrg("aaron");
	}
	
	public void addCompanionHousehold() throws Exception {
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("High Priests Group", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("HomeTeachingDropDown", "xpath");
		clickElement("Companionships", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("LoadingSpinner", 500, "xpath");
		Thread.sleep(2000);
		
		
		selectDistrictHTVT("District 1");
		
		// Create Companionship
		addCompanionshipHTVT("lds16");
		addCompanionshipHTVT("lds17");
		
		//Add Households to Companionship
		addHouseholdHTVT("aaron");
		addHouseholdHTVT("lds18");
		addHouseholdHTVT("lds19");
		
		clickDistrictSaveButton("District 1");
		//clickElement("MemberSave", "xpath");
		
		clickElement("QuorumAuxSelect", "xpath");
		clickElement("QuorumAuxReliefSociety", "xpath");
		Thread.sleep(4000);
		
		selectDistrictHTVT("District 1");
		
		// Create Companionship
		addCompanionshipHTVT("lds26");
		addCompanionshipHTVT("lds27");
		
		//Add Households to Companionship
		addHouseholdHTVT("lds38");
		addHouseholdHTVT("lds28");
		addHouseholdHTVT("lds29");
		addHouseholdHTVT("aaron");
		
		//clickElement("MemberSave", "xpath");
		clickDistrictSaveButton("District 1");
		
		selectDistrictHTVT("District 2");
		
		// Create Companionship
		addCompanionshipHTVT("lds35");
		addCompanionshipHTVT("lds36");
		
		//Add Households to Companionship
		addHouseholdHTVT("lds12");
		addHouseholdHTVT("lds26");
		addHouseholdHTVT("lds27");
		
		//clickElement("MemberSave", "xpath");
		clickDistrictSaveButton("District 2");
		
		
		clickElement("QuorumAuxSelect", "xpath");
		clickElement("QuorumAuxElders", "xpath");
		Thread.sleep(4000);
		
		selectDistrictHTVT("District 1");
		
		// Create Companionship
		addCompanionshipHTVT("lds21");
		addCompanionshipHTVT("lds22");
		
		//Add Households to Companionship
		addHouseholdHTVT("afpten");
		addHouseholdHTVT("lds23");
		addHouseholdHTVT("lds24");
		
		clickDistrictSaveButton("District 1");
		//clickElement("MemberSave", "xpath");
		
		Thread.sleep(2000);
		clickElement("LCRHomeButton", "xpath");
	}
	
	
	public void selectDistrictHTVT(String districtToSelect) throws Exception {
		driver.findElement(By.xpath("//span[contains(text(), '" + districtToSelect + "')]/../../following-sibling::div//a[contains(text(), 'Create new companionship')]")).click();
		Thread.sleep(2000);
	}
	
	public void clickDistrictSaveButton(String districtToSelect) throws Exception {
		driver.findElement(By.xpath("//span[contains(text(), '" + districtToSelect + "')]/../../following-sibling::div//button[.='Save']")).click();
		Thread.sleep(2000);
	}
	
	public void addCompanionshipHTVT(String companionToAdd) throws Exception {
		clickElement("AddNewCompanion", "xpath");
		enterText("SearchForTeacher", "xpath", companionToAdd);
		Thread.sleep(2000);
		clickElement("FoundMember", "xpath");
	}
	
	public void addHouseholdHTVT(String householdToAdd) throws Exception {
		clickElement("AddNewAssignment", "xpath");
		enterText("SearchForHousehold", "xpath", householdToAdd);
		Thread.sleep(2000);
		clickElement("FoundMember", "xpath");
	}
	
	public void setupDistricts() throws Exception {
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("High Priests Group", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("HomeTeachingDropDown", "xpath");
		clickElement("Companionships", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("LoadingSpinner", 500, "xpath");
		Thread.sleep(2000);

		addDistrict("District 1", "Tools, LDS50" );

		clickElement("QuorumAuxSelect", "xpath");
		clickElement("QuorumAuxReliefSociety", "xpath");
		Thread.sleep(4000);
		
		addDistrict("District 1", "Tools, LDS52" );
		addDistrict("District 2", "Tools, LDS53" );
		
		clickElement("QuorumAuxSelect", "xpath");
		clickElement("QuorumAuxElders", "xpath");
		Thread.sleep(4000);
		
		addDistrict("District 1", "Tools, LDS51" );
		
		Thread.sleep(2000);
		clickElement("LCRHomeButton", "xpath");
	}
	
	public void addMemberToCalling(String memberCalling, String memberToAdd) throws Exception {
		driver.findElement(By.xpath("//span[contains(text(), '"+ memberCalling +"')]/../following-sibling::td//button[.='Edit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), '"+ memberCalling +"')]/../following-sibling::td//input[@ng-model='c.memberLookup']")).sendKeys(memberToAdd);
		Thread.sleep(2000);
		clickElement("MemberMatch", "xpath");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), '"+ memberCalling +"')]/../following-sibling::td//button[contains(text(), 'Save')]")).click();
		Thread.sleep(4000);
	}
	
	public void setupCallings() throws Exception {
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("High Priests Group", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("High Priests Group Leadership", "linkText");
		
		
		addMemberToCalling("First Assistant", "lds17");
		addMemberToCalling("Second Assistant", "lds18");
		addMemberToCalling("Group Secretary", "lds19");
		clickElement("Clear Filters", "linkText");
		
	
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("High Priests Group", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Home Teaching District Supervisors", "linkText");
		
		addMemberToCalling("District Supervisor", "lds50");
		clickElement("Clear Filters", "linkText");
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Elders Quorum", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Elders Quorum Presidency", "linkText");
		
		addMemberToCalling("Elders Quorum President", "lds21");
		addMemberToCalling("First Counselor", "lds22");
		addMemberToCalling("Second Counselor", "lds23");
		addMemberToCalling("Quorum Secretary", "lds24");
		clickElement("Clear Filters", "linkText");
	
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Elders Quorum", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Home Teaching District Supervisors", "linkText");
		
		addMemberToCalling("District Supervisor", "lds51");
		clickElement("Clear Filters", "linkText");
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Relief Society", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Visiting Teaching", "linkText");
		
		addMemberToCalling("Teaching Coordinator", "lds52");
		addMemberToCalling("Teaching District Supervisor", "lds53");
		clickElement("Clear Filters", "linkText");
		
		Thread.sleep(2000);
		clickElement("HomeButton", "xpath");
	}
	
	public void setupAJcalling() throws Exception {
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Relief Society", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Music", "linkText");
		
		clickElement("EditReliefSocityPianist", "xpath");
		enterText("AddMemberToCallingPianist", "xpath", "aaron");
		Thread.sleep(2000);
		clickElement("MemberMatch", "xpath");
		clickElement("MemberSavePianist", "xpath");
		Thread.sleep(2000);
		
		//Add Aaron, Jane to Class
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Sunday School", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Members", "linkText");
		clickElement("Gospel Doctrine", "linkText");
		
		
		addMemberToOrg("aaron");
		
		Thread.sleep(2000);
		clickElement("HomeButton", "xpath");
		
	}
	
	
	public void setupMembers() throws Exception {
		//Browse to the Membership page
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("High Priests Group", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Members", "linkText");
		
		addMemberToOrg("lds16");
		addMemberToOrg("lds17");
		addMemberToOrg("lds18");
		addMemberToOrg("lds19");
		addMemberToOrg("lds20");
		addMemberToOrg("lds50");
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Elders Quorum", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Members", "linkText");
		
		addMemberToOrg("lds21");
		addMemberToOrg("lds22");
		addMemberToOrg("lds23");
		addMemberToOrg("lds24");
		addMemberToOrg("lds25");
		addMemberToOrg("lds51");
		addMemberToOrg("lds31");
		addMemberToOrg("lds32");
		
		
		clickElement("Organizations", "linkText");
		Thread.sleep(4000);
		clickElement("Relief Society", "linkText");
		Thread.sleep(2000);
		waitForTextToDisappear("Loading", 500, "id" );
		clickElement("Members", "linkText");
		
		addMemberToOrg("lds26");
		addMemberToOrg("lds27");
		addMemberToOrg("lds28");
		addMemberToOrg("lds29");
		addMemberToOrg("lds30");
		addMemberToOrg("lds52");
		addMemberToOrg("lds53");
		
		
		addMemberToOrg("aaron");
		
		addMemberToOrg("lds35");
		addMemberToOrg("lds36");
		addMemberToOrg("lds37");
		addMemberToOrg("lds38");
		
		Thread.sleep(2000);
		clickElement("HomeButton", "xpath");
		
	}

	public void addMemberToOrg(String addMember) throws Exception {
		
		scrollToElement("Add Member", "linkText");
		clickElement("Add Member", "linkText");
		
		enterText("AddMemberText", "xpath", addMember);
		Thread.sleep(4000);
		clickElement("MemberMatch", "xpath");
		clickElement("MemberSave", "xpath");
	}


	public void openWebPage(String baseURL) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		options.addArguments("--no-sandbox");

		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		driver = new ChromeDriver(options);
		
		driver.get("chrome://extensions-frame");
		WebElement checkbox = driver.findElement(By.xpath("//label[@class='incognito-control']/input[@type='checkbox']"));
		if (!checkbox.isSelected()) {
		    checkbox.click();
		}
		
		//driver.navigate().to(baseURL);
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
		//Check to see if the Beta agreement is displayed
		//There have been days when they have removed the beta agreement 
		if (checkElementExists("IAgreeCheck", "id")) {
			clickElement("IAgreeCheck", "id");
			clickElement("Agree and Continue", "text");
		}

		
		clickElement("HomeButton", "xpath");
		
		myWindow = driver.getWindowHandle();
	}
	
	public void openPageLogInDirectory(String url, String userName, String passWord) throws Exception {
		
		openGuiMap();
		setUp();
		
		Thread.sleep(4000);
		openWebPage(url);
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys(userName);
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys(passWord);
		clickElement("SignIn", "id");
		
		Thread.sleep(4000);
		//Check to see if the Beta agreement is displayed
		//There have been days when they have removed the beta agreement 
		if (checkElementExists("CloseButton", "xpath")) {
			clickElement("CloseButton", "xpath");
		}

		
	}
	
	
	
	public void WPRopenPageLogIn(String url, String userName, String passWord) throws Exception {
		
		url = "https://missionary-stage.lds.org/ward-missionary-tools";
		userName = "ab254";
		passWord = "password0";

		openGuiMap();
		setUp();
		
		Thread.sleep(1000);
		openWebPage(url);
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys(userName);
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys(passWord);
		clickElement("SignIn", "id");
		
		Thread.sleep(4000);
		clickElement("Progress Record", "linkText");
		Thread.sleep(4000);
		clickElement("Visit last 2 wks", "text");
	}
	
	public void MyTemplePageLogIn(String url, String userName, String passWord) throws Exception {
		openGuiMap();
		setUp();
		
		Thread.sleep(4000);
		openWebPage("https://uat.lds.org/mls/mbr");
		Thread.sleep(2000);
		
		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys(userName);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys(passWord);
		clickElement("SignIn", "id");
		clickElement("MyAccountAndWard", "id");
		clickElement("My Temple", "linkText");
	}
	
	
	public void ABopenPageLogIn(String url, String userName, String passWord) throws Exception {
		
		openGuiMap();
		setUp();
		
		Thread.sleep(1000);
		openWebPage(url);
		Thread.sleep(2000);

		driver.findElement(By.id(this.prop.getProperty("UserName"))).sendKeys(userName);
		//Thread.sleep(1000);
		driver.findElement(By.id(this.prop.getProperty("Password"))).sendKeys(passWord);
		clickElement("SignIn", "id");
		
		Thread.sleep(8000);

		//Login to Area book
		enterText("abUserName", "xpath", "ab254");
		enterText("abPassword", "xpath", "password0");
		Thread.sleep(2000);
		clickElement("abNext", "id");
		
		Thread.sleep(2000);
		

		//Enter password
		clickElement("one", "xpath");
		clickElement("one", "xpath");
		clickElement("three", "xpath");
		clickElement("three", "xpath");
		
		Thread.sleep(2000);

		//ReEnter password
		clickElement("one", "xpath");
		clickElement("one", "xpath");
		clickElement("three", "xpath");
		clickElement("three", "xpath");
		Thread.sleep(2000);
		waitForTextToDisappear("abSync", 500, "xpath");

		//This is to get rid of the Location Alert
		driver.navigate().refresh();
		clickElement("one", "xpath");
		clickElement("one", "xpath");
		clickElement("three", "xpath");
		clickElement("three", "xpath");
		Thread.sleep(2000);
		waitForTextToDisappear("abSync", 500, "xpath");
		//clickElement("abReadLater", "id");
		
		Thread.sleep(4000);
		if (checkElementExists("abReadLater", "id")) {
			clickElement("abReadLater", "id");
			Thread.sleep(2000);
		}

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
		if (elementFind == "textEqu") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[.='" + elementName + "']")));
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
	
	public void scrollToElement(String elementName, String elementFind ) throws Exception {
		WebElement myElement = null;
		
		if (elementFind == "id") {
			myElement = driver.findElement(By.id(this.prop.getProperty(elementName)));
		}
		if (elementFind == "xpath") {
			myElement = driver.findElement(By.xpath(this.prop.getProperty(elementName)));
		}
		if (elementFind == "className") {
			myElement = driver.findElement(By.className(this.prop.getProperty(elementName)));
		}
		if (elementFind == "linkText") {
			myElement = driver.findElement(By.linkText(elementName));
		}
		if (elementFind == "text") {
			myElement = driver.findElement(By.xpath("//*[contains(text(), '" + elementName + "')]"));
		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myElement);
		Thread.sleep(500); 
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
		WebElement myElement;
		//List<WebElement> options= driver.findElements(By.id(this.prop.getProperty("EditDistricts")));
		myElement = driver.findElement(By.id(this.prop.getProperty("EditDistricts"))); 
		//myElement.isDisplayed();
		
		
		if(myElement.isDisplayed()) {	
			clickElement("EditDistricts", "id");
		} else {
			clickElement("AddDistricts", "id");
		}
		
		Thread.sleep(3000);
		driver.findElement(By.id(this.prop.getProperty("NewDistrictTitle"))).clear();
		driver.findElement(By.id(this.prop.getProperty("NewDistrictTitle"))).sendKeys(districtName);
		Thread.sleep(1000);
		clickElement("AddDistrictButton", "xpath");
		Thread.sleep(3000);
		
		WebElement mySelect = driver.findElement(By.xpath("//input[contains(@value, '" + districtName + "')]/../following-sibling::td/select[@ng-model='district.districtLeader']"));		
		//WebElement mySelect = driver.findElement(By.xpath(this.prop.getProperty("DistrictSupervisorSelect")));
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
		
		List<WebElement> options= driver.findElements(By.xpath(this.prop.getProperty(elementName)));
		if (options.isEmpty()) {
			myString ="";
		} else {
			myElement = driver.findElement(By.xpath(this.prop.getProperty(elementName)));
			myString = myElement.getAttribute("innerHTML");
		}
	
		return myString;
	}
	
	private String getSourceOfMember(String elementName) {
		String myString;
		WebElement myElement = null;
		
		myElement = driver.findElement(By.id(this.prop.getProperty(elementName)));
		myString = myElement.getAttribute("innerHTML");

		return myString;
	}
	
	private List<String> getMembersDirectory(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		//Elements myTest = doc.getElementsByAttributeValueStarting("class", "member-card-remote");
		//Elements myTest = doc.getElementsByAttributeValueStarting("class", "ng-binding");
		Elements myTest = doc.getElementsByAttributeValueStarting("id", "leaderName");
		String outerHTML;

		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			//System.out.println("Raw Data:" + outerHTML);
			if (outerHTML.contains("&") || (outerHTML.contains("772"))) {
			//if (outerHTML.contains("&") || (Character.isDigit(outerHTML.charAt(0)))) {
			//if (outerHTML.contains("&")) {
				System.out.println("Skipping:" + outerHTML);
			} else {
				String[] parts = outerHTML.split(" ");
				for (String addname : parts ) {
					//System.out.println("Add Name: " + addname);
					foundUsers.add(addname);
				}
				
			}

		}

		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}

		return foundUsers;
		
	}
	
	private List<String> getMembers(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		//Elements myTest = doc.getElementsByAttributeValueStarting("class", "member-card-remote");
		Elements myTest = doc.getElementsByAttributeValueStarting("class", "ng-binding");
		String outerHTML;
		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			if (outerHTML.contains("&") || (outerHTML.contains("772"))) {
			//if (outerHTML.contains("&") || (Character.isDigit(outerHTML.charAt(0)))) {
			//if (outerHTML.contains("&")) {
				System.out.println("Skipping:" + outerHTML);
			} else {
				if (outerHTML.contains(",")) {
					if (outerHTML.contains("Jr")){
						outerHTML = outerHTML.replace(" Jr", ", Jr");
					}
					foundUsers.add(outerHTML);
				}
				//System.out.println("Outer HTML:" + outerHTML);
			}

		}

		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}

		return foundUsers;
		
	}
	
	private List<String> getAllTemples(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.select("a[href]");
		//Elements myTest = doc.getElementsByAttributeValueStarting("class", "ng-binding");
		String outerHTML;
		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			outerHTML = outerHTML.replace(",", "");
			outerHTML = outerHTML.trim();
			foundUsers.add(outerHTML);
		}

		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}

		return foundUsers;
		
	}
	
	
	
	private List<String> getMembersWithoutCallings(String pageSource){
		List<WebElement> options= driver.findElements(By.xpath("//tr[@class='ng-scope']//ng-transclude[@class='ng-binding']"));
		List<WebElement> options2= driver.findElements(By.xpath("//tr[@class='ng-scope alt']//ng-transclude[@class='ng-binding']"));
		
		List<String> foundUsers = new ArrayList<String>();
		String myText;
		for (int i = 0 ; i < options.size(); i++ ) {
			myText = options.get(i).getText();
			if (myText.contains("&")) {
				System.out.println("Skipping:" + myText);
			} else {
				if (myText.contains(",")) {
					if (myText.contains("Jr")){
						myText = myText.replace(" Jr", ", Jr");
					}
					foundUsers.add(myText);
				}
				//System.out.println("Outer HTML:" + outerHTML);
			}
			foundUsers.add(myText);
		}
		
		for (int i = 0 ; i < options2.size(); i++ ) {
			myText = options2.get(i).getText();
			if (myText.contains("&")) {
				System.out.println("Skipping:" + myText);
			} else {
				if (myText.contains(",")) {
					if (myText.contains("Jr")){
						myText = myText.replace(" Jr", ", Jr");
					}
					foundUsers.add(myText);
				}
				//System.out.println("Outer HTML:" + outerHTML);
			}
			foundUsers.add(myText);
		}


		for(String oneUser : foundUsers){
			System.out.println("Found User: " + oneUser);
			
		}

		return foundUsers;
		
	}
	
	
	private List<String> getUnitStatistics(String pageSource){
		List<WebElement> options = driver.findElements(By.xpath("//h4[@class='ng-binding']"));
		List<WebElement> options2 = driver.findElements(By.xpath("//td[@class='span5 ng-binding']"));
		
		List<String> foundUsers = new ArrayList<String>();
		String myText;
		String myNumber;
		String finalText;
		for (int i = 0 ; i < options.size(); i++ ) {
			//myText = options.get(i).getText();
			myText =  options.get(i).getAttribute("innerHTML");
			//System.out.println("ORIG Text: " + myText);
			//System.out.println("Outer HTML: " + options.get(i).getAttribute("outerHTML"));
			//System.out.println("Inner HTML: " + options.get(i).getAttribute("innerHTML"));
			//System.out.println("href: " + options.get(i).getAttribute("href"));
			//myText = myText.toLowerCase();		
			//myText = WordUtils.capitalize(myText);
			
			System.out.println("My Text: " + myText);
			//Get the Number that corresponds with the Unit Stat
			//myNumber = driver.findElement(By.xpath("//*[.='" + myText +"']/../..//*[@class='ng-binding ng-scope']")).getText();
			myNumber = driver.findElement(By.xpath("//*[.='" + myText +"']/../..//*[@class='ng-binding ng-scope']")).getText();
			finalText = myText + "," + myNumber;
			System.out.println("Unit Stats: " + finalText);
			foundUsers.add(finalText);
		}
		
		for (int i = 0 ; i < options.size(); i++ ) {
			myText = options2.get(i).getText();
			System.out.println("My Text: " + myText);
			//Get the Number that corresponds with the Unit Stat
			myNumber = driver.findElement(By.xpath("//*[.='" + myText +"']/..//*[@class='ng-binding ng-scope']")).getText();
			finalText = myText + "," + myNumber;
			System.out.println("Unit Stats: " + finalText);
			foundUsers.add(finalText);
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
			myElement = myElement.attr("title", "Delete teacher");
			outerHTML = myElement.text();
			System.out.println("Before Outer HTML:" + outerHTML);
			if (outerHTML.contains("Home")) {
				outerHTML = StringUtils.substringBefore(outerHTML, " Home Teachers");
			} else {
				outerHTML = StringUtils.substringBefore(outerHTML, " Visiting Teachers");
			}
			
			if (outerHTML.contains(",")) {
				if (outerHTML.contains("Jr")){
					outerHTML = outerHTML.replace(" Jr", ", Jr");
				}
				removeUsers.add(outerHTML);
			}
			System.out.println("After Outer HTML:" + outerHTML);
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
	
	
	private List<String> getTextFromSource(String pageSource, String elementTag){
		List<String> foundUsers = new ArrayList<String>();
		Document doc = Jsoup.parse(pageSource);
		
		//Document doc = Jsoup.parseBodyFragment(pageSource);
		//int myCounter = 1;
		//Elements myTest = doc.getElementsByAttributeValueStarting("class", "member-card-remote");
		//Elements myTest = doc.getAllElements();
		//Elements myTest = doc.getElementsByTag(elementTag);
		Elements myTest = doc.select(elementTag);
		
		String outerHTML;
		
		for (Element myElement : myTest ) {
			outerHTML = myElement.text();
			//System.out.println("Number: " + myCounter + " " + outerHTML);
			//myCounter++;
			if (!outerHTML.isEmpty()) {
				if (outerHTML.contains(",")) {
					String[] splitText = outerHTML.split(", ");
					String splitText1 = splitText[0];
					splitText1 = splitText1.trim();
					String splitText2 = splitText[1];
					splitText2 = splitText2.trim();
					foundUsers.add(splitText1);
					foundUsers.add(splitText2);
				} else {
					foundUsers.add(outerHTML);
				}
				
			}
			
		}

		for(String oneUser : foundUsers){
			System.out.println("TEXT: " + oneUser);
			
		}

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
	
	public List<String> getAllMembersOnPageDirectory(String menuItem, String myReport, Boolean newSession) throws Exception {		
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		

		System.out.println("Menu Item: " + menuItem);
	
		//clickElement("ReportsMenu", "id");
		Thread.sleep(4000);
		clickElement(menuItem, "linkText");
		Thread.sleep(4000);
		//clickElement("Member List", "linkText");
		clickElement(myReport, "linkText");
		Thread.sleep(2000);
		//waitForTextToDisappear("Loading", 500, "id" );
		mySource = getSourceOfPage();
		foundUsers = getMembersDirectory(mySource);
		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	
	public List<String> getAllMembersOnPage(String menuItem, String myReport, Boolean newSession) throws Exception {		
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		//driver.switchTo().window(myWindow);
		if (menuItem.equals("OrganizationsMenu")) {
			menuItem = "Organizations";
		}
		System.out.println("Menu Item: " + menuItem);
	
		//clickElement("ReportsMenu", "id");
		Thread.sleep(4000);
		clickElement(menuItem, "linkText");
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
		if (menuItem.equals("OrganizationsMenu")) {
			menuItem = "Organizations";
		}
		
		
		clickElement(menuItem, "linkText");
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
			Thread.sleep(2000);
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
		
		if (menuItem.equals("ReportsMenu")) {
			menuItem = "Reports";
		}
		
		Thread.sleep(2000);
		clickElement(menuItem, "linkText");
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
			
			switch(subReport) {
			case "MembersMovedOut":
				Thread.sleep(2000);
				//selectList ("NumberOfMonths", "12 Months", "xpath");
				clickElement("NumberOfMonths", "xpath");
				clickElement("12 Months", "text");
				Thread.sleep(3000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembersMovedOut(mySource);
				break;
				
			case "MembersMovedIn" :
				Thread.sleep(2000);
				clickElement("NumberOfMonthsMovedIn", "xpath");
				clickElement("12 Months", "text");
				Thread.sleep(3000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembers(mySource);
				break;
				
			case "MemberswithoutCallings" :
				Thread.sleep(2000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembersWithoutCallings(mySource);
				break;
				
			case "UnitStatistics" :
				Thread.sleep(2000);
				mySource = getSourceOfPage();
				foundUsers = getUnitStatistics(mySource);
				break;
	
			default:
				Thread.sleep(2000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembers(mySource);	
				break;
					
			}
			/*
			if (subReport.equals("MembersMovedOut")) {
				Thread.sleep(2000);
				//selectList ("NumberOfMonths", "12 Months", "xpath");
				clickElement("NumberOfMonths", "xpath");
				clickElement("12 Months", "text");
				Thread.sleep(3000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembersMovedOut(mySource);	
			}
			
			if (subReport.equals("MembersMovedIn")) {
				Thread.sleep(2000);
				clickElement("NumberOfMonthsMovedIn", "xpath");
				clickElement("12 Months", "text");
				Thread.sleep(3000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembers(mySource);	
			}
			
			
			
			if (subReport.equals("MemberswithCallings")) {
				Thread.sleep(2000);
				mySource = getSourceOfElement(subReport);
				foundUsers = getMembers(mySource);	
			}
			*/
			

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
	
	
	
	
	private List<String> getMembersWardProgressRecord(String pageSource){
		List<String> foundUsers = new ArrayList<String>();
		List<WebElement> options= driver.findElements(By.xpath("//div[@class=\"record ng-scope\"]//*[@class=\"contact-name ng-binding\"]"));
		String myText;
		
		for (int i = 0 ; i < options.size(); i++ ) {
			//System.out.println(options.get(i).getText());
			myText = options.get(i).getText();
			
			if (!myText.isEmpty() ) {
				foundUsers.add(myText);
				System.out.println("WPR USER: " + myText);
			}
			
		}

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
		//clickElement("OrganizationsMenu", "id");
		clickElement("Organizations", "linkText");
		
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
			Thread.sleep(4000);
			waitForTextToDisappear("LoadingSpinner", 500, "xpath");
			Thread.sleep(2000);
			myElementCheck = checkElementExists("AlertSomethingWrong", "xpath");
			if (myElementCheck == true ) {
				System.out.println(orgName + " " + myReport + "Page did not load... skipping");
				foundUsers.clear();
				tearDown();
				return foundUsers;
			}
			Thread.sleep(6000);
			
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
	
	public void AreaBookSetup() throws Exception {
		
		ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		ABSetupAutoTest();
		ABSync();
		tearDown();
		
		ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		ABSetupDebbieSmith();
		ABSync();
		tearDown();
		
		
		ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		ABSetupColinMacNeil();
		ABSync();
		tearDown();
		
		
		
		//ABSetEventColinMacNeil();
		//ABSync();
	}
	
	public void ABSelectUser(String elementName ) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = null;
		
		myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + elementName + "')][@class='name']")));
		myElement.click();

		//*[contains(text(), 'Auto Test')]
	}
	
	public void ABSync() throws Exception {
		Thread.sleep(2000);
		
		clickElement("abMenu", "xpath");
		clickElement("Sync Fagamalo", "text");
		
		Thread.sleep(2000);
		enterText("abSyncLogin", "xpath", "ab254");
		enterText("abSyncPassword", "xpath", "password0");
		clickElement("abSyncSignInButton", "id");
		
		Thread.sleep(10000);
		waitForTextToDisappear("abSync", 500, "xpath");
		
		clickElement("abDashboard", "xpath");
	}
	
	
	public void ABSetupAutoTest() throws Exception {
		//ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		clickElement("InvestigatorsAdd", "xpath");
		enterText("abFirstName", "xpath", "Auto");
		enterText("abLastName", "xpath", "Test");
		
		//Should check on what the toggle is first
		//clickElement("LocalOnlineToggle", "id");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberMobile", "xpath", "1 (801) 867-5309");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberHome", "xpath", "1-222-333-4444");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberWork", "xpath", "1-666-666-6666");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberOther", "xpath", "3853853858");
		
		clickElement("Add email address", "text");
		enterText("abAddEmailAddressPersonal", "xpath", "personal@test.com");
		Thread.sleep(2000);
		
		//I don't like the scroll to element crap but I don't have another way to do it right now. 
		scrollToElement("Add email address", "text");
		clickElement("Add email address", "text");
		enterText("abAddEmailAddressWork", "xpath", "work@test.com");
		
		scrollToElement("Add email address", "text");
		clickElement("Add email address", "text");
		enterText("abAddEmailAddressFamily", "xpath", "family@test.com");
		
		scrollToElement("Add email address", "text");
		clickElement("Add email address", "text");
		enterText("abAddEmailAddressOther", "xpath", "other@test.com");

		
		scrollToElement("abAddAddress", "xpath");
		enterText("abAddAddress", "xpath", "50 E N Temple St, Salt Lake City, UT 84150");
		
		//Add Household members
		clickElement("abAddHousehold", "xpath");
		enterText("adHouseholdFind", "xpath", "Darth Vader");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ab-personfield-name'][contains(text(), 'Darth Vader')]")).click();
		clickElement("abHouseholdDone", "xpath");
		
		//Add Fellowshippers
		clickElement("abAddFellowshippers", "xpath");
		enterText("adFellowshippersFind", "xpath", "Jane Fellowshipper");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ab-personfield-name'][contains(text(), 'Jane Fellowshipper')]")).click();
		clickElement("abFellowshippersDone", "xpath");
		
		//Enter in Background info
		enterText("abAddBackgroundInfo", "xpath", "Just some backgound info");
		
		//Add Preferred Language
		clickElement("abPreferredLanguage", "xpath");
		enterText("adPreferredLanguageFind", "xpath", "English");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ab-base-list-innerhtml']/p/span[contains(text(), 'English')]")).click();
		clickElement("abPreferredLanguageDone", "xpath");
		
		//Set Age
		clickElement("abAge", "xpath");
		clickElement("abAge31-50", "xpath");
		Thread.sleep(2000);
		
		//Set Gender
		clickElement("abGender", "xpath");
		clickElement("abGenderMale", "xpath");
		Thread.sleep(2000);
		

		//Set Unit
		//scrollToElement("Add Help Needed", "text");
		//clickElement("abUnit", "xpath");
		//clickElement("Fagamalo 1st Ward", "text");
		//clickElement("Fagamalo 1st Ward", "text");
		
		//Set Finding Method
		//clickElement("abFindingMethod", "xpath");
		//Thread.sleep(2000);
		//clickElement("Missionary Contact", "text");
		//Thread.sleep(2000);
		//clickElement("Church Tour", "text");
		//Thread.sleep(2000);
		
		//Set Finding Campaign
		//clickElement("abFindingCampaign", "xpath");
		//Thread.sleep(1000);
		//clickElement("Christmas 2015", "text");
		//Thread.sleep(2000);
		
		//Set Help Needed
		scrollToElement("abHelpNeeded", "xpath");
		enterText("abHelpNeeded", "xpath", "Needs help pulling weeds");
		
		clickElement("abSave", "id");
		
		Thread.sleep(4000);
		clickElement("abDashboard", "xpath");
		Thread.sleep(4000);
		

		


	}

	
	public void ABSetupDebbieSmith() throws Exception {
		//ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		clickElement("InvestigatorsAdd", "id");
		enterText("abFirstName", "xpath", "Debbie");
		enterText("abLastName", "xpath", "Smith");
		
		//Should check on what the toggle is first
		//clickElement("LocalOnlineToggle", "id");
		
		clickElement("Add phone number", "text");
		Thread.sleep(2000);
		enterText("abAddPhoneNumberMobile", "xpath", "111-111-1111");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberHome", "xpath", "222-222-2222");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberWork", "xpath", "333-333-3333");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberOther", "xpath", "444-444-4444");
		
		clickElement("Add email address", "text");
		enterText("abAddEmailAddressPersonal", "xpath", "debbitsmith@gmail.com");
		Thread.sleep(2000);
		
		
		scrollToElement("abAddAddress", "xpath");
		enterText("abAddAddress", "xpath", "13502 S Hamilton View Rd, Riverton, UT 84065");
		
		//Add Household members
		clickElement("abAddHousehold", "xpath");
		enterText("adHouseholdFind", "xpath", "Bob Smith");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ab-personfield-name'][contains(text(), 'Bob Smith')]")).click();
		clickElement("abHouseholdDone", "xpath");
		
		//Add Fellowshippers
		clickElement("abAddFellowshippers", "xpath");
		enterText("adFellowshippersFind", "xpath", "LDS32 Tools");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ab-personfield-name'][contains(text(), 'LDS32 Tools')]")).click();
		clickElement("abFellowshippersDone", "xpath");
		
		//Enter in Background info
		enterText("abAddBackgroundInfo", "xpath", "Background info here");
		
		//Add Preferred Language
		clickElement("abPreferredLanguage", "xpath");
		enterText("adPreferredLanguageFind", "xpath", "English");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ab-base-list-innerhtml']/p/span[contains(text(), 'English')]")).click();
		clickElement("abPreferredLanguageDone", "xpath");
		
		//Set Age
		clickElement("abAge", "xpath");
		clickElement("abAge31-50", "xpath");
		Thread.sleep(2000);
		
		//Set Gender
		clickElement("abGender", "xpath");
		clickElement("abGenderFemale", "xpath");
		Thread.sleep(2000);
		

		//Set Unit
		//scrollToElement("Add Help Needed", "text");
		//clickElement("abUnit", "xpath");
		//clickElement("Fagamalo 1st Ward", "text");
		//clickElement("Fagamalo 1st Ward", "text");
		
		//Set Finding Method
		/*
		scrollToElement("abFindingMethod", "xpath");
		clickElement("abFindingMethod", "xpath");
		Thread.sleep(2000);
		clickElement("Internet", "text");
		Thread.sleep(2000);
		clickElement("LDS.org", "text");
		Thread.sleep(2000);
		clickElement("Chat", "text");
		Thread.sleep(2000);
		*/
		
		//Set Help Needed
		scrollToElement("abHelpNeeded", "xpath");
		enterText("abHelpNeeded", "xpath", "Building a death ray");
		
		Thread.sleep(2000);
		clickElement("abSave", "id");
		Thread.sleep(4000);
		clickElement("abDashboard", "xpath");
		Thread.sleep(4000);

	}
	
	public void ABSetupColinMacNeil() throws Exception {
		//ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		clickElement("InvestigatorsAdd", "id");
		enterText("abFirstName", "xpath", "Colin");
		enterText("abLastName", "xpath", "MacNeil");
		
		//Should check on what the toggle is first
		//clickElement("LocalOnlineToggle", "id");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberMobile", "xpath", "801-111-1111");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberHome", "xpath", "385-222-2222");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberWork", "xpath", "505-333-3333");
		
		clickElement("Add phone number", "text");
		enterText("abAddPhoneNumberOther", "xpath", "723-444-4444");
		
		clickElement("Add email address", "text");
		enterText("abAddEmailAddressPersonal", "xpath", "colinmacneil@gmail.com");
		Thread.sleep(2000);
		
		
		scrollToElement("abAddAddress", "xpath");
		enterText("abAddAddress", "xpath", "13445 S 4500 W, Riverton, UT 84065");
		
		//Add Household members

		
		//Add Fellowshippers
		clickElement("abAddFellowshippers", "xpath");
		enterText("adFellowshippersFind", "xpath", "LDS44 Tools");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ab-personfield-name'][contains(text(), 'LDS44 Tools')]")).click();
		clickElement("abFellowshippersDone", "xpath");
		
		//Enter in Background info
		enterText("abAddBackgroundInfo", "xpath", "Moved from Scotland");
		
		//Add Preferred Language
		clickElement("abPreferredLanguage", "xpath");
		enterText("adPreferredLanguageFind", "xpath", "English");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ab-base-list-innerhtml']/p/span[contains(text(), 'English')]")).click();
		clickElement("abPreferredLanguageDone", "xpath");
		
		//Set Age
		clickElement("abAge", "xpath");
		clickElement("abAge18-30", "xpath");
		Thread.sleep(2000);
		
		//Set Gender
		clickElement("abGender", "xpath");
		clickElement("abGenderMale", "xpath");
		Thread.sleep(2000);
		

		//Set Unit
		//scrollToElement("Add Help Needed", "text");
		//clickElement("abUnit", "xpath");
		//clickElement("Fagamalo 1st Ward", "text");
		//clickElement("Fagamalo 1st Ward", "text");
		
		//Set Finding Method
		/*
		scrollToElement("abFindingMethod", "xpath");
		clickElement("abFindingMethod", "xpath");
		Thread.sleep(2000);
		clickElement("Advertising", "text");
		Thread.sleep(2000);
		clickElement("Broadcast", "text");
		Thread.sleep(2000);
		clickElement("TV", "text");
		Thread.sleep(2000);
		*/
		
		//Set Help Needed
		scrollToElement("abHelpNeeded", "xpath");
		enterText("abHelpNeeded", "xpath", "This is just a test. This is just a test. This is just a test. This is just a test. This is just a test. ");
		
		Thread.sleep(2000);
		clickElement("abSave", "id");
		Thread.sleep(4000);
		clickElement("abDashboard", "xpath");
		Thread.sleep(4000);

	}
	
	public void ABSetEventColinMacNeil() throws Exception {
		ABopenPageLogIn("https://missionary-stage.lds.org/areabook/", "ab254", "password0");
		Thread.sleep(1000);
		clickElement("abPeople", "xpath");
		Thread.sleep(1000);
		ABSelectUser("Colin MacNeil");
		Thread.sleep(3000);
		
		//****************** Message of the Restoration *******************************
		
		clickElement("Message of the Restoration", "text");
		clickElement("The gospel blesses families", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("God is our loving Heavenly Father", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("Gospel dispensations", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("earthly ministry", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("The Great Apostasy", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("Joseph Smith", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("The Book of Mormon", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Message of the Restoration", "text");
		clickElement("Holy Ghost", "text");
		clickElement("abEditSave", "xpath");
		
		//****************** Plan of Salvation *******************************
		
		clickElement("Plan of Salvation", "text");
		clickElement("Pre-earth life", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		clickElement("The Creation", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		clickElement("Agency and the Fall", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		clickElement("Our life on earth", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		clickElement("The Atonement", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		clickElement("The spirit world", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		clickElement("Resurrection", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Plan of Salvation", "text");
		scrollToElement("Kingdoms of glory", "text");
		clickElement("Kingdoms of glory", "text");
		clickElement("abEditSave", "xpath");
		
		Thread.sleep(3000);
		
		//****************** Gospel of Jesus Christ *******************************
		
		clickElement("Gospel of Jesus Christ", "text");
		clickElement("Cleansed from sin", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Gospel of Jesus Christ", "text");
		clickElement("Faith in Jesus Christ", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Gospel of Jesus Christ", "text");
		clickElement("Repentance", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Gospel of Jesus Christ", "text");
		clickElement("Baptism, our first covenant", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Gospel of Jesus Christ", "text");
		clickElement("The gift of the Holy Ghost", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Gospel of Jesus Christ", "text");
		clickElement("Endure to the end", "text");
		clickElement("abEditSave", "xpath");
		
		Thread.sleep(3000);
		
		//****************** Commandments *******************************
		
		clickElement("Commandments", "text");
		clickElement("Obedience", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Commandments", "text");
		clickElement("Pray often", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Commandments", "text");
		clickElement("Study the scriptures", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Commandments", "text");
		clickElement("Keep the Sabbath day holy", "text");
		clickElement("abEditSave", "xpath");
		
		/*
		clickElement("Commandments", "text");
		scrollToElement("Baptism and confirmation", "text");
		clickElement("Baptism and confirmation", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Commandments", "text");
		scrollToElement("Follow the prophet", "text");
		clickElement("Follow the prophet", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Commandments", "text");
		scrollToElement("Keep the Ten Commandments", "text");
		clickElement("Keep the Ten Commandmentst", "text");
		clickElement("abEditSave", "xpath");
		
		clickElement("Commandments", "text");
		//scrollToElement("Live the law of chastity", "text");
		clickElement("Live the law of chastity", "text");
		clickElement("abEditSave", "xpath");
		*/
		Thread.sleep(8000);
		clickElement("abBackButton", "id");

	}
	
	
	
	public List<String> WPRgetUsers(String subReport, Boolean newSession) throws Exception {
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		Thread.sleep(5000);

		if (!subReport.equals("none")) {
			clickElement("WPRIndividualStatus", "xpath");
			Thread.sleep(1000);
			clickElement(subReport, "text");
			Thread.sleep(2000);
			mySource = getSourceOfElement("WPRVisibleUsers");
			//mySource = getSourceOfPage();
			foundUsers = getMembersWardProgressRecord(mySource);
			clickElement(subReport, "text");
			Thread.sleep(2000);
			clickElement("WPRIndividualStatus", "xpath");
		} else {
			//mySource = getSourceOfElement("WPRVisibleUsers");
			mySource = getSourceOfPage();
			foundUsers = getMembersWardProgressRecord(mySource);
		}
		

		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	public List<String> WPRgetUsersVisits(String subReport, Boolean newSession) throws Exception {
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		Thread.sleep(5000);

		clickElement("WPRVisits", "xpath");
		Thread.sleep(1000);
		clickElement(subReport, "xpath");
		Thread.sleep(2000);
		mySource = getSourceOfElement("WPRVisibleUsers");
		//mySource = getSourceOfPage();
		foundUsers = getMembersWardProgressRecord(mySource);
		clickElement(subReport, "xpath");
		Thread.sleep(2000);
		clickElement("WPRVisits", "xpath");

		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	public List<String> WPRgetSacMeeting(String subReport, Boolean newSession) throws Exception {
		String mySource;
		List<String> foundUsers = new ArrayList<String>();
		
		Thread.sleep(5000);

		clickElement("WPRSacramentMeetingAttendance", "xpath");
		Thread.sleep(1000);
		clickElement(subReport, "xpath");
		Thread.sleep(2000);
		mySource = getSourceOfElement("WPRVisibleUsers");
		//mySource = getSourceOfPage();
		foundUsers = getMembersWardProgressRecord(mySource);
		clickElement(subReport, "xpath");
		Thread.sleep(2000);
		clickElement("WPRSacramentMeetingAttendance", "xpath");

		
		if (newSession == true ) {
			tearDown();
		}
		
		return foundUsers;
		
	}
	
	public void killProcess(String processToKill) throws Exception {
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(new String[] {"/usr/bin/pkill", "-9", processToKill});
		//Process pr = run.exec(cmd);
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while ((line=buf.readLine())!=null) {
			System.out.println(line);
		}
	}
	
	public List<String> GetAllTemples() throws Exception {
		String mySource;
		List<String> allTemples = new ArrayList<String>();
		MyTemplePageLogIn("https://uat.lds.org/mls/mbr", "ldstools2", "toolstester");
		clickElement("Find a Temple", "linkText");
		
		mySource = getSourceOfMember("AllTemplesTable");
		allTemples = getAllTemples(mySource);
		
		return allTemples;
	}
	
	
	
	//Get the Temple Name
	public String TempleGetName() throws Exception {
		String templeName;
		templeName = getText("TempleTitle", "xpath");
		//System.out.println("Temple Name");
		//System.out.println(templeName);
		
		return templeName;
		
	}
	
	//Get the Temple Physical Address
	public List<String> TempleGetPhysicalAddress() throws Exception {
		String mySource;
		List<String> templePhysicalAddress = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TemplePhysicalAddress");
		templePhysicalAddress = getTextFromSource(mySource, "li");
		
		return templePhysicalAddress;
		
	}
	
	//Get the Temple Mailing Address
	public List<String> TempleGetMailingAddress() throws Exception {
		String mySource;
		List<String> templeMailingAddress = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleMailingAddress");
		templeMailingAddress = getTextFromSource(mySource, "li");
		
		return templeMailingAddress;
		
	}
	
	//Get the Temple Telephone
	public List<String> TempleGetTelephone() throws Exception {
		String mySource;
		List<String> templeTelephone = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleTelephone");
		templeTelephone = getTextFromSource(mySource, "li");
		
		return templeTelephone;
		
	}
	
	//Get the Temple Schedule
	public List<String> TempleGetSchedule() throws Exception {
		String mySource;
		List<String> templeSchedule= new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleSchedule");
		templeSchedule = getTextFromSource(mySource, "li");
		
		return templeSchedule;
		
	}
	
	//Get the Temple Family Name cards
	public List<String> TempleGetFamilyNameCards() throws Exception {
		String mySource;
		List<String> templeFamilyNameCards = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleFamilyNameCards");
		templeFamilyNameCards = getTextFromSource(mySource, "li");
		
		return templeFamilyNameCards;
		
	}
	
	//Get the Temple Group Attendance
	public List<String> TempleGetGroupAttendance() throws Exception {
		String mySource;
		List<String> templeGroupAttendance = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleGroupAttendance");
		templeGroupAttendance = getTextFromSource(mySource, "li");
		
		return templeGroupAttendance;
		
	}
	
	//Get the Temple Services
	public List<String> TempleGetServices() throws Exception {
		String mySource;
		List<String> templeServices = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleServices");
		templeServices = getTextFromSource(mySource, "li");
		
		return templeServices;
		
	}
	
	//Get the Temple Milestones
	public List<String> TempleGetMilestones() throws Exception {
		String mySource;
		List<String> templeMilestones = new ArrayList<String>();
		
		//System.out.println("Physical Address");
		mySource = getSourceOfElement("TempleMilestones");
		templeMilestones = getTextFromSource(mySource, "li");
		
		return templeMilestones;
		
	}
	
	public String isRunning() throws Exception {
		String runningStatus = null;

		runningStatus = driverRunning;
		System.out.println("Running Status: " + runningStatus);
		
		return runningStatus;
	}

	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		driverRunning = null;
		Thread.sleep(2000);
		//killProcess("chromedriver");
		//killProcess("\"Google Chrome\"");
		
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
