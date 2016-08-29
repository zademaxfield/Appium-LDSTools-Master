package LDSTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Dictionary;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.HideKeyboardStrategy;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.selendroid.SelendroidCapabilities;
//import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidKeys;
//import io.selendroid.exceptions.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Wait;
//Not used yet
//import org.openqa.selenium.WebElement;

//import com.opera.core.systems.scope.protos.SelftestProtos.SelftestResult.Result;




/** LDSTools class - suite of tests to test LDSTools app
 * 
 *@author Zade Maxfield
 *
 */
public class LDSTools {
	private Properties prop;
	AppiumDriver<WebElement> driver;
	LDSWeb myWeb = new LDSWeb();
	//AppiumDriver driver;
	//AppiumDriver driver;
	TouchActions touch;
	//private SoftAssert softAssert = new SoftAssert();
	public ErrorCollector collector = new ErrorCollector();
	
	private String myUserName = "Not Set";
	private String myPassword = "Not Set";

	
	/** Setup Appium driver
	 * Note the difference between the classpathRoot on Windows vs Mac
	 * 
	 * @throws Exception
	 */
	@Parameters({"os", "fileName", "testDevice"})
	@BeforeMethod(alwaysRun = true)
	public void setUp(String os, String fileName, String testDevice) throws Exception {
		//System.out.println("OS: " + os );
		//System.out.println("File Name: " + fileName);
		
		
		//Android Setup
		if (os.equals("android")) {
			 // set up appium
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        //File appDir = new File(classpathRoot, "..\\..\\..\\..\\Selenium");
	        //MAC Path
	        File appDir = new File(classpathRoot, "../../../Selenium");
	        //File app = new File(appDir, "ldstools-release-20151102-1936.apk");
	        File app = new File(appDir, fileName);
	        
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        
	        capabilities.setCapability("deviceName", testDevice);
	        capabilities.setCapability("platformName", "Android");
	        //capabilities.setCapability("automationName","selendroid");
	        capabilities.setCapability("newCommandTimeout", 600);
	        //capabilities.setCapability("platformVersion", "5.1.1");
	        //capabilities.setCapability("fullReset", true);
	        //capabilities.setCapability("noReset", true);
	        capabilities.setCapability("app", app.getAbsolutePath());
	        if (fileName.contains("alpha")) {
	        	capabilities.setCapability("appPackage", "org.lds.ldstools.dev"); // *** ALPHA ***
	        } else {
	        	capabilities.setCapability("appPackage", "org.lds.ldstools"); //*** BETA and RELEASE ***
	        }
	        
	        //capabilities.setCapability("appActivity", "org.lds.ldstools.ui.StartupActivity");
	        //capabilities.setCapability("appActivity", "org.lds.ldstools.ui.activity.SignInActivity");
	        
	        capabilities.setCapability("unicodeKeyboard", "true");
	        capabilities.setCapability("resetKeyboard", "true");
	        
	        
	        
	        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	        //driver = new AppiumSwipeableDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
	        //driver = new AppiumDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
	        //touch = new TouchActions(driver);
	        //System.out.println("Setup Complete!");
	        //driver.context("NATIVE_APP");
	        
		}
		
		//Setup for iOS
		if (os.equals("ios")) {
			//IOSDriver driver;
	        // set up appium
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        //File appDir = new File(classpathRoot, "..\\..\\..\\..\\Selenium");
	        //MAC Path
	        File appDir = new File(classpathRoot, "../../../Selenium");
	        //File app = new File(appDir, "LDS Tools.app");
	        File app = new File(appDir, fileName);
	        DesiredCapabilities capabilities = new DesiredCapabilities();

	        //capabilities.setCapability(CapabilityType.VERSION, "9.0");
	        //capabilities.setCapability("deviceName","iPhone 5");
	        
	        
	        
	        capabilities.setCapability("platformName", "iOS");
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
	        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
	        capabilities.setCapability("deviceName",testDevice);
	        capabilities.setCapability("automationName","appium");
	        capabilities.setCapability("browserName","");
	        capabilities.setCapability("fullReset", true);
	        capabilities.setCapability("newCommandTimeout", 600);
	        capabilities.setCapability("app", app.getAbsolutePath());
	        //capabilities.setCapability("appPackage", "org.lds.ldstools.dev");
	        capabilities.setCapability("appPackage", "org.lds.ldstools.dev");
	        //capabilities.setCapability("sendKeysStrategy", "setValue");
	        capabilities.setCapability("sendKeysStrategy", "grouped");
	        capabilities.setCapability("launchTimeout", 90000);
	        
	        
	        //capabilities.setCapability("platformVersion", "9.1");
	        capabilities.setCapability("nativeInstrumentsLib", true);
	       
	        
	        //capabilities.setCapability("appActivity", "org.lds.ldstools.ui.StartupActivity");
	        //driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	        //driver = new AppiumSwipeableDriver(new URL("http://127.0.0.1:4445/wd/hub"),capabilities);
	        driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4445/wd/hub"),capabilities);
	       // touch = new TouchActions(driver);
		}
       
    }	
	
    
	@Parameters({"os"})
	@Test (groups= {"jft"})
	public void simpleTest(String os) throws Exception {
		Thread.sleep(4000);
		//justForTesting(os);	

		//LeaderNonBishopricTEST("LDSTools29", "Relief Society Pres", os);
		//LeaderNonBishopricTEST("LDSTools16", "High Priest Group", os);
		//under18HeadofHouse(os);	
		//LeaderBishopric("ngiBPC1", false, os); //Bishopric 1st Counselor  
		//LeaderBishopric("ngiBPC2", false, os); //Bishopric 2nd Counselor 
		//LeaderBishopric("ngiWB1", true, os); //Bishop shows Stake View - something wrong with the account
		LeaderBishopric("ngiMC1", true, os); //Assistant Ward Clerk - Membership
		//bishopMemberOfSeparateStake(os);	
		
		//editCurrentUser(os);	
		//editCurrentUserCancel(os);
		//editOtherUser(os);
		//editOtherUserInvalidPhone(os);
		//editOtherUserInvalidEmail(os);
		
		
		//editVisibility(os);
		//editVisibiltyPersonal(os);
		//editVisibiltyHousehold(os);
		
		//CheckUnitsToSync(os);
		
		//Works in IOS not in Android - need to fix the scrolling problem
		//checkAllUsersFromWeb(os);
		
		
		
		
		
		//Not clearing the username and password on iOS
		//invalidLoginCheck(os);	
		
		//searchForUsersFromWeb(os);

		//webCheckBishopric(os);
		//webCheckEldersQuorum(os);
		//webCheckReliefSociety(os); // Getting errors on the web page for Relief Society
		//webCheckHighPriestsGroup(os);
		
		//webCheckYoungMen(os);
		//webCheckYoungWomen(os); //Remove the skipping "Salvador"
		
		
		//RotateTest(os);
		//rerunSyncTest(os, 3);
		
		
		
		//Header Tests
		//ChristieWhiting(os);
		//CliffHigby(os);
		//KevinPalmer(os);
		//PatriarchOtherWards(os); //Not working!
		//TravisLyman(os);
		//ElderKacher(os); //Member of Second Quorum of the Seventy
		//TerryBallard(os); //Check to see Tim and Jessica Beck
		//AdminUnit(os); //Not working in 2.5.0
		//WardStakeCouncilor(os);
		//JustinKrebs(os);
		//BjornGabler(os);
		//AaronWeech(os);
		//BlakeHeyer(os);
		//ChadHarvey(os);
		//ChetThomas(os);
		//ChrissyKrebs(os);
		//DavidFrank(os);
		//DavidThorne(os);
		//DuplicateSS(os);
		//EdQueen(os);
		//GregCrowther(os);
		//HeberAllen(os);
		//JanetBencomo(os);
		//JesseGibbons(os);
		//JohnCarter(os);
		//JonPug(os);
		//KevinClawson(os);
		//KevinGPalmer(os);
		//LarkinPalmer(os);
		//LarryJensen(os);
		//RalphHowes(os);

	}

	
	
	public void justForTesting(String os) throws Exception {
		
		syncLogIn("LDSTools21", "password1", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		
		checkMissionaryProgressRecord();
		checkMissionaryProgressRecordVisits();
		checkMissionaryProgressRecordSacMeeting();
		
		
		
		/*
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		List<String> lastName = new ArrayList<String>();
		List<String> firstName = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		
		syncLogIn("LDSTools21", "password1", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		
		openReports();
		

		
		
		//Check web data vs LDS Tools
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		
		
		
		
		//Members Moved Out
		clickButtonByXpathTitleName("Members Moved Out");
		Thread.sleep(1000);
		myList = myWeb.getAllMembersInReport("ReportsMenu", "Members Moved Out", "MembersMovedOut", false);
		compareWebData(myList, androidList, true);
		pressBackKey();

		
		//Members Moved In
		clickButtonByXpathTitleName("Members Moved In");
		Thread.sleep(1000);
		myList = myWeb.getAllMembersInReport("ReportsMenu", "Members Moved In", "MembersMovedIn", false);
		for (int myCounter = 0 ; myCounter < myList.size(); myCounter++ ) {
			String[] parts = myList.get(myCounter).split(",");
			String part1 = parts[0];
			String part2 = parts[1];
			//Remove all whitespace
			part1 = part1.trim();
			part2 = part2.trim();
			lastName.add(part1);
			firstName.add(part2);
			//lastName.set(myCounter, part1);
			//firstName.set(myCounter, part2);
		}
		compareWebDataCheck(lastName, androidList, true);
		compareWebDataCheck(firstName, androidList, true);
		pressBackKey();
		
		
		//Members with Callings
		clickButtonByXpathTitleName("Members with Callings");
		Thread.sleep(1000);		
		myList = myWeb.getAllMembersInReport("Organizations", "Members with Callings", "MemberswithCallings", false);
		//But that Stake only members are displayed on the web but on on LDS Tools
		for ( int i = 0; i < myList.size(); i++ ) {
			if (myList.get(i).contains("Betham, Scott") || (myList.get(i).contains("Kitara, Sam") || (myList.get(i).contains("Lealaiauloto, Sefulu")
					|| (myList.get(i).contains("Leota, Polataia") || (myList.get(i).contains("Sitivi, Tama Kiliona") 
					|| (myList.get(i).contains("Wilson, Kesi Voli Joseph") || (myList.get(i).contains("Tools, LDS51") ))))))) {
				System.out.println("REMOVE NAME: " + myList.get(i));
				//myList.remove(i);
				myList.set(i, "");
			}
		}
		myList.removeAll(Collections.singleton(""));
		compareWebData(myList, androidList, true);
		pressBackKey();
		
		
		//Members without Callings
		clickButtonByXpathTitleName("Members without Callings");
		Thread.sleep(1000);		
		myList = myWeb.getAllMembersInReport("Organizations", "Members without Callings", "MemberswithoutCallings", false);
		compareWebData(myList, androidList, true);
		pressBackKey();
		
		//New Members
		clickButtonByXpathTitleName("New Members");
		Thread.sleep(1000);
		myList = myWeb.getAllMembersInReport("ReportsMenu", "New Member", "NewMember", false);
		compareWebData(myList, androidList, true);
		pressBackKey();
		

		//Unit Statistics
		
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Unit Statistics");
		} else {
			//scrollDownTEST(100);
			driver.scrollToExact("Unit Statistics");
			clickButtonByXpathTitleName("Unit Statistics");
		}
		Thread.sleep(1000);
		myList = myWeb.getAllMembersInReport("ReportsMenu", "Unit Statistics", "UnitStatistics", true);
		
		for (int myCounter = 0; myCounter < myList.size() ; myCounter ++) {
			String[] parts = myList.get(myCounter).split(",");
			String part1 = parts[0];
			String part2 = parts[1];
			lastName.add(part1);
			firstName.add(part2);
			//lastName.set(myCounter, part1);
			//firstName.set(myCounter, part2);
		}
		compareWebDataCheck(lastName, androidList, true);
		compareWebDataCheck(firstName, androidList, true);
		pressBackKey();
			
		
		
		
		//checkWebMemberInfo("LDSTools23", "password1", "Aaron, Jane");

	    */
		
		/*
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		//Login to LDS Toos
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");
		clickButtonByXpathTitleName("Birthday List");
		if (!getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Members");
			scrollToTheTop();	
		}
		Thread.sleep(1000);
		

		
		//Check web data vs LDS Tools
		myList = myWeb.getMembersAndAge("ReportsMenu", "Birthday List");
		compareWebData(myList, androidList, false);
		*/
	}
	
	
	
	
		
/*
	@Parameters({"os"})
	@Test (groups= {"count"})
	public void SucessTest1(String os) throws Exception {
		int myNumber = 10;
		Assert.assertEquals(10 , myNumber);
	}
	
	@Parameters({"os"})
	@Test (groups= {"count"})
	public void FailureTest1(String os) throws Exception {
		int myNumber = 11;
		Assert.assertEquals(10 , myNumber);
	}
	
	@Parameters({"os"})
	@Test (groups= {"count"})
	public void SucessTest2(String os) throws Exception {
		int myNumber = 10;
		Assert.assertEquals(10 , myNumber);
	}
	
	@Parameters({"os"})
	@Test (groups= {"count"})
	public void FailureTest2(String os) throws Exception {
		int myNumber = 11;
		Assert.assertEquals(10 , myNumber);
	}
*/	

	
	@Parameters({"os"})
	@Test (groups= {"smoke", "bishopric"}, priority = 1)
	public void Bishopric1stCounselor(String os) throws Exception {
		LeaderBishopric("ngiBPC1", false, os); //Bishopric 1st Counselor  
	}
	
	@Parameters({"os"})
	@Test (groups= {"bishopric"}, priority = 1)
	public void Bishopric2ndCounselor(String os) throws Exception {
		LeaderBishopric("ngiBPC2", false, os); //Bishopric 2nd Counselor  
	}
	
	/* ******** Bishop is showing Stake view first *************
	@Parameters({"os"})
	@Test (groups= {"smoke", "bishopric"}, priority = 1)
	public void BishopFagamalo(String os) throws Exception {
		LeaderBishopric("ngiWB1", true, os); //Bishop 
	}
	*/
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "bishopric"}, priority = 1)
	public void AssistantWardClerkMembership(String os) throws Exception {
		LeaderBishopric("ngiMC1", true, os); //Assistant Ward Clerk - Membership 
	}
	
	
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "high priest"}, priority = 1)
	public void HighPriestsGroupLeader(String os) throws Exception {
		LeaderNonBishopric("LDSTools16", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"}, priority = 2)
	public void HighPriestsGroupFirstAssistant(String os) throws Exception {
		LeaderNonBishopric("LDSTools17", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"}, priority = 2)
	public void HighPriestsGroupSecondAssistant(String os) throws Exception {
		LeaderNonBishopric("LDSTools18", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"}, priority = 2)
	public void HighPriestsGroupSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools19", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"}, priority = 2)
	public void HighPriestsGroupAssistantSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools20", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"}, priority = 1)
	public void EldersQuorumPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools21", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"}, priority = 2)
	public void EldersQuorumFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools22", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"}, priority = 2)
	public void EldersQuorumSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools23", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"}, priority = 2)
	public void EldersQuorumSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools24", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"}, priority = 2)
	public void EldersQuorumAssistantSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools25", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "relief society"}, priority = 1)
	public void ReliefSocietyPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools26", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"}, priority = 2)
	public void ReliefSocietyFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools27", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"}, priority = 2)
	public void ReliefSocietySecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools28", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"}, priority = 2)
	public void ReliefSocietySecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools29", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"}, priority = 2)
	public void ReliefSocietyAssistantSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools30", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"}, priority = 1)
	public void YoungMenPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools31", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"}, priority = 2)
	public void YoungMenFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools32", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"}, priority = 2)
	public void YoungMenSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools33", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"}, priority = 2)
	public void YoungMenSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools34", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"}, priority = 1)
	public void YoungWomenPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools35", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"}, priority = 2)
	public void YoungWomenFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools36", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"}, priority = 2)
	public void YoungWomenSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools37", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"}, priority = 2)
	public void YoungWomenSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools38", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "sunday school"}, priority = 1)
	public void SundaySchoolPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools39", "Ward Council", os);
	}
	
	
	
	public void LeaderBishopric(String leaderLogin, Boolean priorUnit, String os) throws Exception {

		syncLogIn(leaderLogin, "password1", "UAT", os );
		pinPage("1", "1", "3", "3", true);

		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, true, true, true);
		
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);
		
		Thread.sleep(1000);
		
		//Check various callings - all users should be able to access this information
		checkCallings();
		
		Thread.sleep(1000);
		
		//Check Missionary drawer items - all user access
		checkMissionary();
		
		//Check the Calendar - all user access
		Thread.sleep(1000);
		//checkCalendar();
	
		Thread.sleep(1000);
		
		//Check the reports - leadership only
		checkReports(true, priorUnit);
			
		checkHTVTBasic("Bishopric");
		checkHTVTHouseholds("Bishopric");
	}
	
	
	/* - Old method 
	@Parameters({"os"})
	@Test (groups= {"smoke", "bishopric"}, priority = 1)
	public void bishopricCounselorAndWardClerk(String os) throws Exception {
		//int myCheck;
		//LDSTools3 is Bishopric Counselor and Ward Clerk
		syncLogIn("ngiBPC1", "password1", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
	
		
		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, true, true, true);
		
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);
		
		Thread.sleep(1000);
		
		//Check various callings - all users should be able to access this information
		checkCallings();
		
		Thread.sleep(1000);
		
		//Check Missionary drawer items - all user access
		checkMissionary();
		
		//Check the Calendar - all user access
		Thread.sleep(1000);
		checkCalendar();
	
		Thread.sleep(1000);
		
		//Check the reports - leadership only
		checkReports(true, false);
		

		
		checkHTVTBasic("Bishopric");
		checkHTVTHouseholds("Bishopric");
		
	
	}
	*/



	@Parameters({"os"})
	@Test (groups= {"sunday school"}, priority = 2)
	public void SundaySchoolFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools40", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"sunday school"}, priority = 2)
	public void SundaySchoolSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools41", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"sunday school"}, priority = 2)
	public void SundaySchoolSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools42", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"}, priority = 1)
	public void PrimaryPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools43", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"}, priority = 2)
	public void PrimaryFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools44", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"}, priority = 2)
	public void PrimarySecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools45", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"}, priority = 2)
	public void PrimarySecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools46", "Ward Council", os);
	}



	
	
//**************************************************************
//**************** Start of tests ******************************
//**************************************************************

	/** under18HeadofHouse()
	 * Test the settings for a Head of House under 18 years of age
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"noCalling"}, priority = 2)
	public void under18HeadofHouse(String os) throws Exception {
		String pageSource;
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		searchForUser("Tools, LDS6");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS6 Tools (16)");
			Thread.sleep(2000);
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Tools", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("LDS6", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS6");
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS6", pageSource, "Equals"));
		}

		//Check the users name, address membership number etc...
		//Assert.assertTrue(checkNoCaseList("Tools, LDS6", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("LDS6 Tools", pageSource, "Contains"));

		Assert.assertTrue(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("888-0028-7066", pageSource, "Contains"));
		Thread.sleep(1000);
		backToDirectory();

		Thread.sleep(1000);
		checkDirectoryUser(false, false, false, false, false, false );
		
		
	}
	
	/** bishopMemberOfSeparateStake()
	 * Bishop that is a member of a separate stake
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"bishopric"}, priority = 2)
	public void bishopMemberOfSeparateStake(String os) throws Exception {
		String pageSource;
		//int myCheck;
		//LDSTools2 Bishop that is a member of a separate stake
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		
		//Search for logged in user
		searchForUser("Tools, LDS2");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS2 Tools (36)");
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Tools, LDS2", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS2");
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS2", pageSource, "Equals"));
		}
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("888-0028-7023", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("RECORD NUMBER", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("January 1, 1980", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("36", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("BIRTH DATE", pageSource, "Contains"));
		

		Assert.assertTrue(checkNoCaseList("Baptism", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("May 5, 2005", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Confirmation", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("May 5, 2005", pageSource, "Contains"));

		backToDirectory();

		
		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, true, true, true);
		
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);
		
		Thread.sleep(1000);
		
		//Check various callings - all users should be able to access this information
		checkCallings();
		
		Thread.sleep(1000);
		
		//Check Missionary drawer items - all user access
		checkMissionary();
	
		Thread.sleep(1000);
		
		//Check the Calendar - all user access
		//checkCalendar();
		Thread.sleep(1000);
		
		//Check the reports - leadership only
		checkReports(true, true);
		
		checkHTVTBasic("Bishopric");
		checkHTVTHouseholds("Bishopric");
		
		
	}
	
	
	//This is failing on iOS and android
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2, enabled = false)
	public void searchForUsersFromWeb(String os) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		boolean testForElement;
		int checkUser = 0;
		
		//Login as LDSTools2 - Bishiop
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		//Check to see if running iOS or Mac
		testForElement = checkElementExistsByID("MenuDefaultDirectory");
		//System.out.println("testForElement: " + testForElement);
		if (testForElement == true ) {
			clickButtonByID("MenuDefaultDirectory");
			clickButtonByXpathTitleName("Individuals");
		} else {
			clickButtonByXpath("DirectorySort");
			clickButtonByXpath("DirectoryIndividual");
		}
		
		
		//Go to web and get all users
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersOnPage("ReportsMenu", "Member List", true);
		


		
		if (getRunningOS().equals("mac")){
			for(String oneUser : myList){
				sendTextbyXpath("SearchArea", oneUser );
				clickButtonByXpath("SearchGo");
				checkUser = checkTextReturn("SearchResult", oneUser, "xpath", "xpath");
				if (checkUser == 0 ) {
					System.out.println("NOT FOUND: " + oneUser);
				}
				Assert.assertEquals(checkUser, 1);
				//Thread.sleep(2000);
				//Collapse the search 
				clickButtonByXpath("SearchCollapse");
			}
		} else {
			
			for(String oneUser : myList){
				//System.out.println("Found User: " + oneUser);
				clickButtonByID("MenuSearch");
				sendTextbyXpath("SearchArea", oneUser );
				
				Thread.sleep(2000);
				checkUser = checkTextReturn("SearchResult", oneUser, "xpath", "xpath");
				if (checkUser == 0 ) {
					System.out.println("NOT FOUND: " + oneUser);
				}
				Assert.assertEquals(checkUser, 1);
				Thread.sleep(2000);
				//Collapse the search 
				clickButtonByXpath("SearchCollapse");
			}
		}
	}
	
	//This is failing on iOS and android - There is an out of unit member showing on the web but not LDS Tools
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2, enabled = false)
	public void webCheckBishopric(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		String pageSource;
		//boolean testForElement;
		//int checkUser = 0;
		int pageSize;
		
		//Login as LDSTools2 - Bishiop
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		openOrgnizations();
		clickButtonByXpathTitleName("Bishopric");
		
		
		//Go to web and get all users
		myList = myWeb.getAllMembersOnPage("OrganizationsMenu", "Bishopric", true);
		compareWebData(myList, androidList, true);

	}
	
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2)
	public void webCheckHighPriestsGroup(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		//String pageSource;
		//boolean testForElement;
		//int checkUser = 0;
		//int pageSize;
		
		//Login as LDSTools2 - Bishiop
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		openOrgnizations();
		clickButtonByXpathTitleName("High Priests Group");
		clickButtonByXpathTitleName("High Priests Group Leadership");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "High Priests Group", "HighPriestGroupLeadership", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Home Teaching District Supervisors");

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "High Priests Group", "HighPriestGroupDistrictSupervisors", false);
		compareWebData(myList, androidList, true);

		pressBackKey();
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All High Priests Group Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "High Priests Group", "HighPriestGroupMembers", true);
		compareWebData(myList, androidList, true);
	}
	
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2, enabled = false)
	public void webCheckEldersQuorum(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		openOrgnizations();
		clickButtonByXpathTitleName("Elders Quorum");
		clickButtonByXpathTitleName("Elders Quorum Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Elders Quorum", "EldersQuorumPresidency", false);
		compareWebData(myList, androidList, true);
		
		//pressBackKey();
		//clickButtonByXpathTitleName("Home Teaching District Supervisors");

		//myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Elders Quorum", "EldersQuorumDistrictSupervisors", myUserName, myPassword);
		//compareWebData(myList, androidList, false);

		pressBackKey();
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Elders Quorum Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}


		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Elders Quorum", "EldersQuorumMembers", true);
		compareWebData(myList, androidList, true);
		
	}
	
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2, enabled = false)
	public void webCheckReliefSociety(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		openOrgnizations();
		clickButtonByXpathTitleName("Relief Society");
		clickButtonByXpathTitleName("Relief Society Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Relief Society", "ReliefSocietyPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Visiting Teaching");

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Relief Society", "VisitingTeachingSupervisors", false);
		compareWebData(myList, androidList, true);

		pressBackKey();
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Relief Society Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Relief Society", "ReliefSocietyMembers", true);
		compareWebData(myList, androidList, true);

	}
	
	
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2)
	public void webCheckYoungMen(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		//Login to LDS Toos
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		openOrgnizations();
		clickButtonByXpathTitleName("Young Men");
		clickButtonByXpathTitleName("Young Men Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "YoungMenPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Priests Quorum");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Priests Quorum Presidency");
		}


		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "PriestsQuorum", false);
		compareWebData(myList, androidList, true);

		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();
		
		clickButtonByXpathTitleName("Teachers Quorum");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Teachers Quorum Presidency");
		}
		

		
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "TeachersQuorum", false);
		compareWebData(myList, androidList, true);
		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();

		clickButtonByXpathTitleName("Deacons Quorum");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Deacons Quorum Presidency");
		}
		

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "DeaconsQuorum", true);
		compareWebData(myList, androidList, true);
	}
	
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2)
	public void webCheckYoungWomen(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		//Login to LDS Toos
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		openOrgnizations();
		clickButtonByXpathTitleName("Young Women");
		clickButtonByXpathTitleName("Young Women Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "YoungWomenPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Laurel");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Laurel Presidency");
		}


		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "Laurel", false);
		compareWebData(myList, androidList, true);

		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();
		
		clickButtonByXpathTitleName("Mia Maid");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Mia Maid Presidency");
		}
		

		
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "MiaMaid", false);
		compareWebData(myList, androidList, true);
		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();

		clickButtonByXpathTitleName("Beehive");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Beehive Presidency");
		}
		

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "Beehive", true);
		compareWebData(myList, androidList, true);
	}
	
	
	
	//This test will get the members from the web then check LDS Tools to see if all members are there. 
	//TODO: Need to deal with Jr and Junior LDS Tools has an extra comma.  
	@Parameters({"os"})
	@Test (groups= {"web"}, priority = 2)
	public void checkAllUsersFromWeb(String os ) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		String pageSource;
		boolean testForElement;
		//int checkUser = 0;
		int pageSize;
		
		//Login as LDSTools2 - Bishiop
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		//Check to see if running iOS or Mac
		testForElement = checkElementExistsByID("MenuDefaultDirectory");
		//System.out.println("testForElement: " + testForElement);
		if (testForElement == true ) {
			clickButtonByID("MenuDefaultDirectory");
			clickButtonByXpathTitleName("Individuals");
		} else {
			clickButtonByXpath("DirectorySort");
			clickButtonByXpath("DirectoryIndividual");
		}
		
		
		//Go to web and get all users
		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
		myList = myWeb.getAllMembersOnPage("Reports", "Member List", true);
		compareWebData(myList, androidList, false);
		
		/*
		for(String oneUser : myList) {
			//System.out.println("Found User: " + oneUser);
			if (!androidList.contains(oneUser)){
				System.out.println("NOT FOUND: " + oneUser);
			}
		}
		*/
	}
	

	public void LeaderNonBishopric(String leaderLogin, String userCalling, String os) throws Exception {

		syncLogIn(leaderLogin, "password1", "UAT", os );
		pinPage("1", "1", "3", "3", true);

		//Check to see if the user can view the directory
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));

		
		
		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, false, false, true);
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);

		Thread.sleep(1000);	
		//Check various callings - all users should be able to access this information
		checkCallings();

		Thread.sleep(1000);
		//Check Missionary drawer items - all user access
		checkMissionary();
		
		//Check the Calendar - all user access
		Thread.sleep(1000);
		//checkCalendar();
		

		Thread.sleep(1000);
		//Check the reports - leadership only - true for bishopric rights, false for leaders and remove
		//checkReports for non-leaders
		checkReports(false, false);
		
		
		Thread.sleep(1000);
		//Check Home Teaching - Visiting Teaching
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		checkHTVTBasic(userCalling);
		
		Thread.sleep(1000);
		//Check Home Teaching - Visiting Teaching Household - Sisters and Filters
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		checkHTVTHouseholds(userCalling);
	}
	
	
	
	public void LeaderNonBishopricTEST(String leaderLogin, String userCalling, String os) throws Exception {

		syncLogIn(leaderLogin, "password1", "UAT", os );
		pinPage("1", "1", "3", "3", true);

		//Check to see if the user can view the directory
		Thread.sleep(2000);

		/*
		//Check Directory user - should be able to view everything
		checkDirectoryUser(true, true, true, false, false, true);
		Thread.sleep(1000);
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);
		
		

		Thread.sleep(1000);	
		//Check various callings - all users should be able to access this information
		checkCallings();

		
		Thread.sleep(1000);
		//Check Missionary drawer items - all user access
		checkMissionary();
		
		
		//Check the Calendar - all user access
		//Thread.sleep(1000);
		//checkCalendar();
		
		
		
		Thread.sleep(1000);
		//Check the reports - leadership only - true for bishopric rights, false for leaders and remove
		//checkReports for non-leaders
		checkReports(false, false);
		
		 */
		
		Thread.sleep(1000);
		//Check Home Teaching - Visiting Teaching
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		checkHTVTBasic(userCalling);
		
		
		Thread.sleep(1000);
		//Check Home Teaching - Visiting Teaching Household - Sisters and Filters
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		checkHTVTHouseholds(userCalling);
		
	
	}
	
	private void sendTextToEditUser(String editField, String textToSend ) throws Exception {
		Capabilities cap = driver.getCapabilities();
		Map<String, ?> desired = (Map<String, ?>) cap.getCapability("desired");
		Object myAppPackage = desired.get("appPackage");

		if (myAppPackage.equals("org.lds.ldstools")) {
			editField = "Release" + editField;
		} 
		
		sendTextbyXpath(editField, textToSend);
		
	}
	
	private void ClearTextToEditUser(String editField ) throws Exception {
		Capabilities cap = driver.getCapabilities();
		Map<String, ?> desired = (Map<String, ?>) cap.getCapability("desired");
		Object myAppPackage = desired.get("appPackage");

		if (myAppPackage.equals("org.lds.ldstools")) {
			editField = "Release" + editField;
		} 
		
		clearTextFieldXpath(editField);
		
	}
	
	
	/** editCurrentUser()
	 * Login as LDSTools100 and edit own information
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"smoke", "editSetings"}, priority = 1)
	public void editCurrentUser(String os) throws Exception {
		String pageSource;
		//Edit own information
		syncLogIn("LDSTools44", "password1", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		searchForUser("Tools, LDS44");
		
		clearPhoneAndEmail();
		

		editUserOpen(); 
		Thread.sleep(2000);

		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		//myKeyboardClear();
		
		//sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextToEditUser("EditHomePhone", "(801) 867-5309");
		//myKeyboardClear();
		
		sendTextToEditUser("EditPersonalEmail", "personal@nospam.com");
		Thread.sleep(1000);
		//myKeyboardClear();;
		scrollDownTEST(400);

		sendTextToEditUser("EditHomeEmail", "home@nospam.com");
		clickButton("MenuSave", "id", "xpath");
		//myKeyboardClear();
		
		waitForTextToDisappear("EditSaving", 500);

		Thread.sleep(6000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS44 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
		} else {
			//clickButton("Tools, LDS44", "text", "text");
			pageSource = androidGetMemberInfo();
			//System.out.println(pageSource);

		}
		
		//pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource, "Contains"));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource, "Contains"));
		

		backToDirectory();
		
		
		runSync();
		
		
		
		//Search for logged in user
		searchForUser("Tools, LDS44");
		//clickButtonByXpathTitleName("LDS44 Tools");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS44 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS44 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS44", pageSource, "Contains"));
		}
		
		
		
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource, "Contains"));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource, "Contains"));
		
		Thread.sleep(2000);
		clearPhoneAndEmail();
		
		backToDirectory();
		
		//Search for logged in user
		searchForUser("Tools, LDS44");
		//clickButtonByXpathTitleName("LDS44 Tools");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS44 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS44 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS44", pageSource, "Contains"));
		}
		
		Thread.sleep(3000);
		Assert.assertFalse(checkNoCaseList("1(801)240-0104", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("(801) 867-5309", pageSource, "Contains"));	
		Assert.assertFalse(checkNoCaseList("personal@nospam.com", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("home@nospam.com", pageSource, "Contains"));
	}
	
	@Parameters({"os"})
	@Test (groups= {"editSetings"}, priority = 2)
	public void editCurrentUserCancel(String os) throws Exception {
		String pageSource;
		//Edit own information
		syncLogIn("LDSTools44", "password1", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		searchForUser("Tools, LDS44");
		
		clearPhoneAndEmail();
		
		Thread.sleep(2000);
		editUserOpen(); 
		Thread.sleep(2000);
		
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		//myKeyboardClear();
		
		sendTextToEditUser("EditHomePhone", "(801) 867-5309");
		//myKeyboardClear();
		
		sendTextToEditUser("EditPersonalEmail", "personal@nospam.com");
		//myKeyboardClear();
		
		scrollDownTEST(400);
		Thread.sleep(2000);
		
		sendTextToEditUser("EditHomeEmail", "home@nospam.com");
		//myKeyboardClear();

		
		if (getRunningOS().equals("mac")){
			pressBackKey();
		} else {
			clickButtonByXpath("MenuCancel");
		}
		
		checkForAlertOK();
		
		Thread.sleep(3000);

		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS44 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS44 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS44", pageSource, "Contains"));
		}
		Assert.assertFalse(checkNoCaseList("1(801)240-0104", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("(801) 867-5309", pageSource, "Equals"));	
		Assert.assertFalse(checkNoCaseList("personal@nospam.com", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("home@nospam.com", pageSource, "Equals"));
	}
	
	
	
	
	/** editOtherUser()
	 * Edit a user that you are not logged in as. 
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"editSettings"}, priority = 2)
	public void editOtherUser(String os) throws Exception {
		String pageSource;
		//Edit other user
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		clearPhoneAndEmail();
		
		Thread.sleep(2000);
		editUserOpen(); 
		Thread.sleep(1000);
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		//myKeyboardClear();
		
		sendTextToEditUser("EditHomePhone", "(801) 867-5309");
		//myKeyboardClear();
		
		sendTextToEditUser("EditPersonalEmail", "personal@nospam.com");
		//myKeyboardClear();
		scrollDownTEST(400);
		Thread.sleep(2000);
		
		sendTextToEditUser("EditHomeEmail", "home@nospam.com");
		//myKeyboardClear();
		
		clickButton("MenuSave", "id", "xpath");
		
		waitForTextToDisappear("EditSaving", 500);
		
		Thread.sleep(6000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS41 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
		} else {
			//clickButtonByXpathTitleName("Tools, LDS41");
			pageSource = androidGetMemberInfo();
		}
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource, "Contains"));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource, "Contains"));
		
		backToDirectory();
		
		runSync();
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS41 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS41 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource, "Contains"));
		}
		
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource, "Contains"));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource, "Contains"));
		
		Thread.sleep(1000);
		clearPhoneAndEmail();
		
		Thread.sleep(3000);
		
		/* Sometimes this works... sometimes you have to sync before everything is clear. 
		
		if (getRunningOS().equals("mac")) {
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS41 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource, "Contains"));
		}
		
		Assert.assertFalse(checkNoCaseList("1(801)240-0104", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("(801) 867-5309", pageSource, "Contains"));	
		Assert.assertFalse(checkNoCaseList("personal@nospam.com", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("home@nospam.com", pageSource, "Contains"));
		*/
				
	}
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"}, priority = 2)
	public void editOtherUserInvalidPhone(String os) throws Exception {
		String pageSource;
		int alertCheck;
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		searchForUser("Tools, LDS41");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS41 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS41 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource, "Contains"));
		}
		
		
		Thread.sleep(1000);
		clearPhoneAndEmail();
		
		
		Thread.sleep(2000);
		editUserOpen(); 
		Thread.sleep(2000);
		
		sendTextbyXpath("EditPersonalPhone", "######00000000000*****");
		//myKeyboardClear();
		sendTextToEditUser("EditHomePhone", "878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542");
		//myKeyboardClear();
		
		clickButton("MenuSave", "id", "xpath");
		Thread.sleep(2000);
		
		alertCheck = alertCheckInvalidInput();
		if (alertCheck == 1 ) {
			clickButton("AlertOK", "id", "xpath");
		}
		
		if (getRunningOS().equals("mac")){
			pressBackKey();
		} else {
			clickButton("MenuCancel", "xpath", "xpath");
			clickButton("AlertOK", "id", "xpath");
		}
		
		Thread.sleep(3000);
		
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("######00000000000*****", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542", pageSource, "Equals"));	

		backToDirectory();
		
		runSync();
		
		Thread.sleep(3000);
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS41 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS41 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource, "Contains"));
		}
		
		Assert.assertFalse(checkNoCaseList("######00000000000*****", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542", pageSource, "Equals"));	

	}
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"}, priority = 2)
	public void editOtherUserInvalidEmail(String os) throws Exception {
		String pageSource;
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		//Check the users name, address membership number etc...
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS41 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS41 Tools", pageSource, "Contains"));
		} else {
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource, "Contains"));
		}
		
		
		Thread.sleep(1000);
		clearPhoneAndEmail();
		
		
		Thread.sleep(2000);
		editUserOpen(); 
		Thread.sleep(2000);
		
		sendTextToEditUser("EditPersonalEmail", "thisisaninvalidemailaddress");
		invalidEmailCheck();
		ClearTextToEditUser("EditPersonalEmail");
		//myKeyboardClear();
		
		scrollDownTEST(400);
		
		sendTextToEditUser("EditHomeEmail", "thisisaninvalidemailaddress");
		//myKeyboardClear();
		invalidEmailCheck();
		Thread.sleep(2000);
		ClearTextToEditUser("EditHomeEmail");
		//myKeyboardClear();
		
		sendTextToEditUser("EditPersonalEmail", "!@#$%^&*()_+-=[]{}|");
		invalidEmailCheck();
		ClearTextToEditUser("EditPersonalEmail");
		//myKeyboardClear();
		
		scrollDownTEST(400);
		
		sendTextToEditUser("EditHomeEmail", "!@#$%^&*()_+-=[]{}|");
		//myKeyboardClear();
		invalidEmailCheck();
		Thread.sleep(2000);
		ClearTextToEditUser("EditHomeEmail");
		//myKeyboardClear();
		
		
		clickButton("MenuSave", "id", "xpath");
		
		Thread.sleep(3000);
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("thisisaninvalidemailaddress", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("!@#$%^&*()_+-=[]{}|", pageSource, "Equals"));	

		backToDirectory();
		
		runSync();

		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS41 Tools (36)");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS41 Tools", pageSource, "Contains"));
		} else {
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource, "Contains"));
		}
		
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("thisisaninvalidemailaddress", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("!@#$%^&*()_+-=[]{}|", pageSource, "Equals"));	
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "editSettings"}, priority = 1)
	public void editVisibility(String os) throws Exception {
		boolean testForElement;
		//String pageSource;

		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS5");

		//Check the users name, address membership number etc...
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		
		Thread.sleep(1000);
		//editUserOpen(); 
		editUserOpen(); 

		
		//This will reset the visibility back to Stake
		resetVisibility();
		
		if (getRunningOS().equals("mac")) {
			clickButton("HouseholdVisibilityLimit", "id", "xpath");
			Thread.sleep(2000);
			clickButtonByXpath("PrivatePopUp");
			//clickButtonByXpathTitleNameContains("Private");
			Thread.sleep(1000);
		} else {
			clickButtonByXpathTitleName("Privacy");
			clickButton("HouseholdVisibilityLimit", "id", "xpath");
			Thread.sleep(2000);
			clickButton("RadioPrivate", "id", "xpath");
			clickButton("SetLimit", "id", "xpath");
			Thread.sleep(1000);
		}
		

		clickButton("MenuSave", "id", "xpath");

		backToDirectory();
		
		//Log out 
		drawerSignOut();
		
		
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		testForElement = checkElementExistsByID("MenuDefaultDirectory");
		//System.out.println("testForElement: " + "Tools, LDS5");
		if (testForElement == true ) {
			clickButtonByID("MenuDefaultDirectory");
			clickButtonByXpathTitleName("Individuals");
			clickButtonByID("MenuSearch");
		}

		sendTextbyXpath("SearchArea", "Tools, LDS5 " );
		
		Thread.sleep(2000);
		
		Assert.assertFalse(checkElementTextViewRoboReturn("Tools, LDS5"));

		//Collapse the search 
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("SearchCollapse");
		}
		
		
		backToDirectory() ;
		
		//Log out 
		drawerSignOut();
		
		
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS5");
		
		//Check the users name, address membership number etc...
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		Thread.sleep(1000);
		editUserOpen(); 
		
		//Thread.sleep(3000);
		//clickButtonByXpath("AlertOK");	
		
		
		Thread.sleep(1000);
		resetVisibility();
		//scrollDown("Private—Leadership Only", -1000 );
		//clickButtonByXpathTitleName("Stake Visibility");
		//Thread.sleep(1000);
		
		
		
		clickButton("MenuSave", "id", "xpath");
		//Thread.sleep(1000);
		//clickButton("MenuSave", "id", "xpath");

		backToDirectory();
		//Log out 
		drawerSignOut();
		
		
		Thread.sleep(1000);
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS5");
		

	}
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"}, priority = 2)
	public void editVisibiltyPersonal(String os) throws Exception {
		//Set the PERSONAL phone and email to Private-Leadership Only
		//Change LDSTools5 then check the privacy settings with LDSTools6
		String pageSource;

	
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search and open Tools, LDS5
		searchForUser("Tools, LDS5");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5 - 36");
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		Thread.sleep(1000);
		editUserOpen(); 
		Thread.sleep(2000);
		
		//This will reset the visibility back to Stake (just in case something went wrong)
		resetVisibility();

		
		//Set the Personal Settings to Private-Leadership Only
		
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("PersonlVisibility");
			Thread.sleep(2000);
			clickButtonByXpath("EditAllVisibility");
			clickButtonByXpath("PrivateVisibility");
			Thread.sleep(1000);
			clickButtonByXpath("DoneButton");
			
		} else {
			clickButtonByXpathTitleName("Privacy");
			
			clickButton("PrivacyPersonalPhoto", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
			
			clickButton("PrivacyPersonalPhone", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
			
			clickButton("PrivacyPersonalEmail", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
		
		}

		clickButton("MenuSave", "id", "xpath");
		
		backToDirectory();
		
		//Log out 
		drawerSignOut();

		
		//Login with LDSTools6 to check the Personal settings
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
	
		searchForUser("Tools, LDS5");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		//Check the users name Phone and email
		//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseListDEBUG("Fagamalo 1st Ward", pageSource, "Contains"));

		//Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("1224589900887", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("PERSONAL", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("5551234555", pageSource, "Contains"));
		//This is showing up as household email or household phone
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("Z@z.com", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("PERSONAL", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("test@test.com", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
		
		backToDirectory();
		
		//Log out 
		drawerSignOut();

		
		
		//Change the settings back to default
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		searchForUser("Tools, LDS5");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5 - 36");
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		Thread.sleep(1000);
		editUserOpen(); 
		Thread.sleep(2000);
		
		//This will reset the visibility back to Stake (just in case something went wrong)
		resetVisibility();
		
		
		Thread.sleep(1000);
		clickButton("MenuSave", "id", "xpath");
		//Thread.sleep(1000);
		//clickButton("MenuSave", "id", "xpath");

		backToDirectory();
		
		//Log out 
		drawerSignOut();
		Thread.sleep(1000);
		
		
		//Make sure things are back to default
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS5");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		
		//Check the users name, address membership number etc...

		//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource, "Contains"));

		//Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("1224589900887", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("5551234555", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Z@z.com", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("test@test.com", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
	}
	
	
	
	
	
	
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"}, priority = 2)
	public void editVisibiltyHousehold(String os) throws Exception {
		String pageSource;
		//Set the HOUSEHOLD phone and email to Private-Leadership Only
		//Change LDSTools5 then check the privacy settings with LDSTools6
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search and open Tools, LDS5
		searchForUser("Tools, LDS5");
		
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		
		editUserOpen(); 
		Thread.sleep(2000);
		
		//This will reset the visibility back to Stake (just in case something went wrong)
		resetVisibility();
		
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("HouseholdVisibility");
			Thread.sleep(2000);
			clickButtonByXpath("EditAllVisibility");
			clickButtonByXpath("PrivateVisibility");
			Thread.sleep(1000);
			clickButtonByXpath("DoneButton");
			
		} else {
			clickButtonByXpathTitleName("Privacy");
			
			driver.scrollToExact("Show on Map");
			
			clickButton("PrivacyHouseholdPhoto", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdPhone", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdEmail", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdAddress", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdShowOnMap", "xpath", "xpath");
			clickButton("PrivacyPrivateVisibility", "xpath", "xpath");
		
		}
		
		
		
		clickButton("MenuSave", "id", "xpath");
		
		backToDirectory();
		//Log out 
		drawerSignOut();
		
		//Login with LDSTools6 to check the Personal settings
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
	
		searchForUser("Tools, LDS5");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		//Check the users name Phone and email
		//pageSource = getSourceOfPage();
		//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource, "Equals"));

		//Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("1224589900887", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("5551234555", pageSource, "Contains"));
		//Assert.assertFalse(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Z@z.com", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("test@test.com", pageSource, "Contains"));
		//Assert.assertFalse(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
		
		backToDirectory();
		
		//Log out 
		drawerSignOut();
		
		
		//Change the settings back to default
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		searchForUser("Tools, LDS5");
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools (36)");
			//iosExpandAllDirectory();
			//pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			//pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		editUserOpen(); 
		Thread.sleep(2000);
		
		
		resetVisibility();
		
		clickButton("MenuSave", "id", "xpath");
		Thread.sleep(2000);
		backToDirectory();
		
		//Log out 
		drawerSignOut();
		Thread.sleep(1000);
		
		
		//Make sure things are back to default
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS5");
		
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("LDS5 Tools");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Tools, LDS5");
			pageSource = androidGetMemberInfo();
			//Assert.assertTrue(checkNoCaseList("Tools, LDS5", pageSource, "Contains"));
		}
		
		//Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource, "Equals"));

		//Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("1224589900887", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("5551234555", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Z@z.com", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("test@test.com", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Equals"));
	}
	
	@Parameters({"os", "numberOfRetries"})
	@Test (groups= {"retrySync"}, priority = 2, enabled = false)
	public void rerunSyncTest(String os, int numberOfRetires) throws Exception {
		
		System.out.println("Number of Retries: " + numberOfRetires);
		
		for (int i = 1; i < numberOfRetires; i++) {
			System.out.println("Counter: " + i);
			syncLogIn("LDSTools14", "toolstester", "UAT", os );
			Thread.sleep(2000);
			pinPage("1", "1", "3", "3", true);
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
			drawerSignOut();
		}
		
		
		
		/*
		syncLogIn("LDSTools14", "toolstester", "UAT", os );
		Thread.sleep(2000);
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
	
		
		
		for (int i = 1; i < 50 ; i++) {
			System.out.println("Counter: " + i);
			runSync();
			Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		}
		
		*/
		
	}
	
	
	
	/** invalidLoginCheck()
	 * Test invalid logins to LDS Tools
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"smoke"}, priority = 1)
	public void invalidLoginCheck(String os) throws Exception {

		
		//Invalid login test
		syncLogIn("LDSTools2", "<login>", "UAT", os );
		Thread.sleep(2000);
		CheckInvalidAlert();
		
		clearLoginPassword();
		
		syncLogIn("LDSTools2", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&", "UAT", os );
		Thread.sleep(2000);
		CheckInvalidAlert();
		
		clearLoginPassword();
		
		syncLogIn("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "UAT", os );
		Thread.sleep(2000);
		CheckInvalidAlert();
		
		clearLoginPassword();
		
		
		/* For some reason the clearLoginPassword isn't working on the password
		syncLogIn("LDSTools2", "test|test|test$$$$test|||||||test", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementReturn(errorMessage, "textAtt", "value"));
		clickButtonByXpath("AlertOK");
		
		clearLoginPassword();*/
		
		syncLogIn("zmaxfield", "%%%test%%%% & ||||||| select * from household;", "Production", os );
		Thread.sleep(2000);
		CheckInvalidAlert();
		
		clearLoginPassword();
		
		/*
		syncLogIn("", "", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementReturn("Sign in to your LDS Account (UAT)", "textAtt", "value"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("LDSTools2", "", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementReturn("Sign in to your LDS Account (UAT)", "textAtt", "value"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("", "toolstester", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementReturn("Sign in to your LDS Account (UAT)", "textAtt", "value"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		*/
	}
	
	public void CheckInvalidAlert() throws Exception {
		
		String errorMessage;
		String errorMessageService;
		
		if (getRunningOS().equals("mac")) {
			errorMessage = "Sign-In Failed";
			errorMessageService = "Error";
		} else {
			errorMessage = "Incorrect username or password";
			errorMessageService = "Error";
		}
		
		if (checkElementReturn(errorMessageService, "textAtt", "value") == true) {
			Assert.assertTrue(checkElementReturn(errorMessageService, "textAtt", "value"));
		} else {
			Assert.assertTrue(checkElementReturn(errorMessage, "textAtt", "value"));
		}
		
		clickButton("AlertOK", "id", "xpath");	
		
		if (getRunningOS().equals("mac")) {
			clickButton("AlertOK", "id", "xpath");	
		}
	}
	
	//TODO: Need to check more pages
	public void RotateTest(String os) throws Exception {
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		
		//Check Organizations
		RotateOrgnazations();
		
		//Check Reports
		RotateReports();
		
		
	}
	
	
	
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void ChristieWhiting(String os) throws Exception {
		loginProxyData("3446450099",
				"/7u189715/5u511293/",
				"p1175/1151u1000047/:p143/7u189715/5u511293/",
				"Proxy", "ChristieWhiting");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		
		Thread.sleep(2000);
		
		checkAllWardDirectories();
	
	}
	
	@Parameters({"os"})
	@Test (groups= {"header", "smoke"}, priority = 1)
	public void CliffHigby(String os) throws Exception {
		//List<String> StakeWard = new ArrayList<String>();
		loginProxyData("295740465",
				"/7u191/5u504505/",
				"p428/467u376892/28u381772/:p1711/59u1004603/22u388300/:p1788/467u376892/28u381772/:p1680/32u1326376/:p789/8u1006967/1u563013/:p1887/14u1004816/467u376892/",
				"Proxy", "CliffHigby");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		checkAllWardDirectories();
		
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void KevinPalmer(String os) throws Exception {
		loginProxyData("3182767230",
				"/7u50482/5u511846/",
				"p222/7u50482/5u511846/:p39/3u2019809/1u790206/:p2/5u511846/1u790206/",
				"Proxy", "KevinPalmer");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		checkAllWardDirectories();

	}
	

	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3, enabled = false)
	public void PatriarchOtherWards(String os) throws Exception {
		loginProxyData("888000028749A",
				"/7u56030/5u524735/",
				"p13/7u56030/",
				"Proxy", "TestPatriarch");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		//Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for a user that has children
		searchForUser("Faapili, Muipu & Baby");
		
		//Select the user
		//Check that the children are visible
		//clickButtonByXpathTitleName("Faapili, Muipu & Baby");
		//clickLastTextViewRoboReturn("Faapili, Muipu");
		Thread.sleep(1000);
		/*
		Assert.assertTrue(checkElementTextViewReturn("Muipu Faapili (32)"));
		Assert.assertTrue(checkElementTextViewReturn("Baby Faapili (35)"));
		Assert.assertTrue(checkElementTextViewReturn("Muipu Jnr Faapili (12)"));
		Assert.assertTrue(checkElementTextViewReturn("Tautinoga Faapili (10)"));
		Assert.assertTrue(checkElementTextViewReturn("Mapusaga Faapili (4)"));
		*/
		
		Assert.assertTrue(checkElementReturn("Muipu Faapili", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Baby Faapili", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Muipu Jnr Faapili", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Tautinoga Faapili", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Mapusaga Faapili", "textAtt", "value"));
		pressBackKey();
		clickButtonByXpath("SearchCollapse");
		//pressBackKey();
		
		//Change to another Ward
		//Check to see that the children are visible
		if (getRunningOS().equals("mac")){
			clickButtonByXpath("SpinnerSubTitle");
		} else {
			clickButtonByID("SpinnerNav");
		}
		
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Fagamalo 2nd Ward");
		
		
		//Search for a user that has children
		searchForUser("Alofa, Pasi & Rowena");

		
		//Select the user
		//Check that the children are visible
		//clickButtonByXpathTitleName("Alofa, Pasi & Rowena");
		//clickLastTextViewRoboReturn("Alofa, Pasi");
		Thread.sleep(1000);
		/*
		Assert.assertTrue(checkElementTextViewReturn("Pasi Alofa (32)"));
		Assert.assertTrue(checkElementTextViewReturn("Rowena Alofa (26)"));
		Assert.assertTrue(checkElementTextViewReturn("Rozarnah Alofa (4)"));
		Assert.assertTrue(checkElementTextViewReturn("Leativaosalafai Shaleen Alofa (2)"));
		*/
		
		Assert.assertTrue(checkElementReturn("Pasi Alofa", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Rowena Alofa", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Rozarnah Alofa", "textAtt", "value"));
		Assert.assertTrue(checkElementReturn("Leativaosalafai Shaleen Alofa", "textAtt", "value"));
		//Assert.assertTrue(checkElementReturn("Pioneer Aumoto", "textAtt", "value"));
		pressBackKey();
		Thread.sleep(1000);
		clickButtonByXpath("SearchCollapse");
		

	}
	
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void TravisLyman(String os) throws Exception {
		loginProxyData("309310780",
				"/7u1161164/5u427144/",
				"p222/7u170690/5u506508/",
				"Proxy", "TravisLyman");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Thread.sleep(2000);
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Alcorn, Sarah"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 2)
	public void JustinKrebs(String os) throws Exception {
		loginProxyData("309310780",
				"/7u1161164/5u427144/",
				"p58/7u1618172/5u481599/:p94/5u481599/5u481599/:p53/5u481599/5u481599/",
				"Proxy", "JustinKrebs");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		//checkAllWardDirectories();
		checkDirectoryForUser();
		Thread.sleep(2000);
	}
	
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void BjornGabler(String os) throws Exception {
		loginProxyData("23088869144",
				"/7u165743/5u515442/",
				"p208/7u165743/5u515442/:p205/7u165743/5u515442/:p1394/7u165743/5u515442/:p1559/7u165743/5u515442/",
				"Proxy", "BjornGabler");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void AaronWeech(String os) throws Exception {
		loginProxyData("2207033566",
				"/7u243108/5u500593/",
				"p1395/7u243108/5u500593/:p136/7u243108/5u500593/",
				"Proxy", "AaronWeech");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void BlakeHeyer(String os) throws Exception {
		loginProxyData("3858858902",
				"/7u103985/5u521191/",
				"p57/7u103985/5u521191/",
				"Proxy", "BlakeHeyer");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void ChadHarvey(String os) throws Exception {
		loginProxyData("933086209",
				"/7u102733/5u505757/",
				":p222/7u187569/5u524956/:p222/8u219363/6u524956/:p222/7u268941/5u524956/"
				+ ":p222/7u237213/5u524956/:p222/8u237280/6u524956/:p222/7u162299/5u524956/",
				"Proxy", "ChadHarvey");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void ChetThomas(String os) throws Exception {
		loginProxyData("3649387500",
				"/7u20664/5u506044/",
				"p772/7u20664/5u506044/:p4/7u20664/5u506044/",
				"Proxy", "ChetThomas");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void ChrissyKrebs(String os) throws Exception {
		loginProxyData("652270633",
				"/7u1618172/5u481599/",
				"p1384/5u481599/5u481599/",
				"Proxy", "ChrissyKrebs");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void DavidFrank(String os) throws Exception {
		loginProxyData("3273750761",
				"/7u9415/5u506249/",
				"p227/7u9415/5u506249/:p140/7u9415/5u506249/",
				"Proxy", "DavidFrank");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		//checkDirectoryForUser();
		checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void DavidThorne(String os) throws Exception {
		loginProxyData("900162590",
				"/7u27642/5u516600/",
				"p55/7u27642/5u516600/",
				"Proxy", "DavidThorne");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void DuplicateSS(String os) throws Exception {
		loginProxyData("895728277",
				"/7u1618172/5u481599/",
				"p58/7u1618172/5u481599/:p53/5u481599/1u481599/:p94/5u481599/1u481599/",
				"Proxy", "DuplicateSS");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void EdQueen(String os) throws Exception {
		loginProxyData("2334797025",
				"/7u374792/5u516090/",
				"p1395/7u374792/5u516090/:p133/7u374792/5u516090/",
				"Proxy", "EdQueen");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void GregCrowther(String os) throws Exception {
		loginProxyData("1056095880",
				"/7u430463/5u517356/",
				"p55/7u430463/5u517356/",
				"Proxy", "GregCrowther");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	//Option 2 test
	//TODO: Need to test for option 2 info
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void HeberAllen(String os) throws Exception {
		loginProxyData("3402770655225469",
				"/7u100617/5u1940635/",
				"p216/7u100617/5u1940635/",
				"Proxy", "HeberAllen");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	//Visiting teaching coordinator not seeing Visiting Teaching reports
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void JanetBencomo(String os) throws Exception {
		loginProxyData("2862227641",
				"/7u36668/5u501301/",
				"p152/7u36668/5u501301/:p151/7u36668/5u501301/",
				"Proxy", "JanetBencomo");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void JesseGibbons(String os) throws Exception {
		loginProxyData("2753900058",
				"/7u208892/5u514012/",
				"p222/7u296791/5u521779/:p222/7u35130/5u521779/:p222/7u208892/5u514012/",
				"Proxy", "JesseGibbons");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void JohnCarter(String os) throws Exception {
		loginProxyData("3029591255",
				"/7u43818/5u513164/",
				"p160/7u43818/5u513164/",
				"Proxy", "JohnCarter");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void JonPug(String os) throws Exception {
		loginProxyData("627660245",
				"/7u7935/5u516325/",
				"p57/7u7935/5u516325/:p767/7u7935/5u516325/",
				"Proxy", "JonPug");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	//TODO: Need to update for large number of units to sync. 
	/*
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void KevinClawson(String os) throws Exception {
		loginProxyData("3334886480",
				"/7u82570/5u517615/",
				"p9/3u537233/5u537233/",
				"Proxy", "KevinClawson");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();
		Thread.sleep(2000);
		
		drawerSignOut();
	}
	*/
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void KevinGPalmer(String os) throws Exception {
		loginProxyData("3182767230",
				"/7u50482/5u511846/",
				"p222/7u50482/5u511846/:p39/3u2019809/1u790206/:p2/5u511846/1u790206/",
				"Proxy", "KevinGPalmer");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void LarkinPalmer(String os) throws Exception {
		loginProxyData("746277872",
				"/8u210374/5u277037/",
				"p1559/8u210374/5u277037/:p208/8u210374/5u277037/:p789/8u210374/5u277037/",
				"Proxy", "LarkinPalmer");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3, enabled = false)
	public void LarryJensen(String os) throws Exception {
		loginProxyData("3449149679326998",
				"/7u6033/5u504181/",
				"p1278/8u267244/5u514209/:p789/8u267244/5u514209/",
				"Proxy", "LarryJensen");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3, enabled = false)
	public void RalphHowes(String os) throws Exception {
		loginProxyData("3393724786796776",
				"/7u1010824/5u2055031/",
				"p13/5u481599/1u790117/:p13/5u2055031/1u790117/",
				"Proxy", "RalphHowes");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	
	//TODO: Cannot see Freetown Stake and Temple Recommend Status - Active problems
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void ElderKacher(String os) throws Exception {
		loginProxyData("2178152043",
				"/7u253707/5u516244/",
				"p32/21u418951/:p32/1u563013/:p56370/1016u1004840/1u563013/",
				"Proxy", "ElderKacher");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Baker, Joseph"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void TerryBallard(String os) throws Exception {
		loginProxyData("20904102494",
				"/7u25941/5u515124/",
				"p158/7u25941/5u515124/",
				"Proxy", "TerryBallard");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		checkDirectoryForUser();
		//checkAllWardDirectories();

	}
	
	/*
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void AdminUnit(String os) throws Exception {
		loginProxyData("7157852120",
				"/32u9990011/16u244449/",
				"",
				"Proxy", "AdminUnit");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		//Thread.sleep(5000);
		Assert.assertTrue(checkElementTextViewRoboReturn("Alcorn, Sarah"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		

		drawerSignOut();
	}
	*/
	
	@Parameters({"os"})
	@Test (groups= {"header"}, priority = 3)
	public void WardStakeCouncilor(String os) throws Exception {
		loginProxyData("8999999998916734",
				"/7u56030/5u524735/",
				"p135/7u56030/5u524735/:p94/5u524735/",
				"Proxy", "WardStakeCounilor");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEleven, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));

	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "setings"}, priority = 1)
	public void CheckUnitsToSync(String os) throws Exception {
		String loginName = "LDSTools2";
		String loginPassword = "toolstester";
		String chooseNetwork = "UAT";
		
		List<String> StakeWard = new ArrayList<String>();
		List<WebElement> options = new ArrayList<WebElement>();
		Thread.sleep(2000);
		String pageSource;
		
		
		//If the login is using any of the test networks we need to change it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy"
		if (os.equals("android")) {
			System.out.println("Select Units - Not implemented yet on Android");
			/*
			System.out.println("Orientation: " + driver.getOrientation().value());
			if (driver.getOrientation().value() == "landscape") {
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
			
			
			if (!chooseNetwork.equals("Production")) {
				//Just for testing
				Thread.sleep(10000);
				
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				
				
				clickButtonByXpath("Menu");
				Thread.sleep(1000);
				clickButtonByXpath("OverflowSettings");
				Thread.sleep(2000);
				
				
				
				driver.scrollToExact("Network Environment").click();
				//scrollDown("Network Environment", 35 );
				
				clickButtonByXpathTitleName(chooseNetwork);
				clickButtonByXpath("Back");
				Thread.sleep(5000);
			}

			sendTextbyID("LoginUsername", loginName);
			sendTextbyID("LoginPassword", loginPassword);
			clickButtonByXpath("SignInButton");
			Thread.sleep(4000);
			waitForTextToDisappearID("SyncText", 500 );
			Thread.sleep(2000);
			*/
		}
		
		if (os.equals("ios")) {
			if (!chooseNetwork.equals("Production")) {
				Thread.sleep(1000);
				clickButtonByXpath("TopHelp");
				
				
				if (checkElementExistsByXpath("DeveloperSettings") == true) {
					clickButtonByXpath("DeveloperSettings");
				} else {
					//New way to enable dev settings
					for (int x = 1; x <= 5; x++ ) {
						clickButtonByXpath("EnableDevSettings");
					}
				}

				
				clickButtonByXpathTitleNameContains("Environment");
				clickButtonByXpath("Proxy");
				clickButtonByXpath(chooseNetwork);
				clickButtonByXpath("TopDeveloper");
				
				clickButtonByXpathTitleNameContains("Set Max Units");
				sendTextbyXpath("SetMaxUnits", "3");
				clickButton("OK", "xpath", "xpath");
				

				clickButtonByXpath("TopHelp");
				clickButtonByXpath("TopSignIn");
				
			}
			
			sendTextbyXpath2("LoginUsername", loginName);
			sendTextbyXpath2("LoginPassword", loginPassword);
			clickButtonByXpath("DoneButton");
			Thread.sleep(4000);
			
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Select up to 3", pageSource, "Contains"));
			clickButton("Fagamalo  1st Ward", "value", "value");
			clickButton("Fagamalo  2nd Ward", "value", "value");
			clickButton("Fatuvalu Ward", "value", "value");
			
			clickButton("SyncButton", "xpath", "xpath");
			
			
			waitForTextToDisappear("DownloadingSync", 500 );
			Thread.sleep(2000);
			pinPage("1", "1", "3", "3", true);
			Thread.sleep(2000);
			
			clickButtonByXpath("SpinnerSubTitle");
			//Get Stake and all Wards
			options= driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell/UIAStaticText"));
			for (int i = 0 ; i < options.size(); i++ ) {
				//System.out.println(options.get(i).getText());
				StakeWard.add(options.get(i).getText());
			}
			clickButtonByXpath("TopCancel");
			
			Assert.assertTrue(StakeWard.size() == 3);
			Assert.assertTrue(StakeWard.contains("Fagamalo 1st Ward"));
			Assert.assertTrue(StakeWard.contains("Fagamalo 2nd Ward"));
			Assert.assertTrue(StakeWard.contains("Fatuvalu Ward"));
			
			
			//Go through each Stake and Ward to make sure it isn't blank
			for(String StakeWardItem : StakeWard){
				clickButtonByXpath("SpinnerSubTitle");
				Thread.sleep(2000);
				//System.out.println("To Click: " + StakeWardItem);	

				clickButtonByXpathTitleName(StakeWardItem);
				//displayAllTextViewElements();
				Thread.sleep(2000);
				//This will check to see if the first user has text.  
				Assert.assertTrue(checkFirstDirectoryUser());

			}
			
			
		}
	}
	
	
	/**loginCheck()
	 * Go through All LDSTools users to make sure they can login
	 * 
	 * @throws Exception
	 */

	public void loginCheck(String os) throws Exception {
		String password1 = "toolstester";
		String password2 = "password1";
		boolean pinCheck = true;
		
		
		for (int myCounter = 2 ; myCounter <= 47; myCounter++ ){
			//System.out.println("USER: LDSTools" + myCounter);
			if (myCounter <= 15){
				syncLogIn("LDSTools" + myCounter, password1, "UAT", os );
			} else {
				syncLogIn("LDSTools" + myCounter, password2, "UAT", os );
			}
			
			Thread.sleep(2000);
			//Assert.assertFalse(checkElementTextViewReturn("LDS Tools Services are unavailable. Please try again later."));
			
			//displayAllTextViewElements();
			pinCheck = checkElementTextViewReturnContains("PIN");
			
			if (pinCheck == false){
				System.out.println("Login failed!");
			} else {
				//true will setup pin for a non-leader
				pinPage("1", "1", "3", "3", true);
				
				clickButtonByXpath("Drawer");
				clickButtonByXpath("DrawerSETTINGS");
				
				clickButtonByXpathTitleName("Sign Out");
				clickButton("SignOutOK", "id", "xpath");
			}
		}
	}
	
	//**************************************************************
	//**************** Start of Methods ****************************
	//**************************************************************
	

	
	private void checkText(String textElement, String textToCheck, String andEle, String iosEle ) {
		String findElement;
		if (getRunningOS().equals("mac")) {
			findElement = iosEle;
		} else {
			findElement = andEle;
		}
		
		if (findElement == "id") {
			AssertJUnit.assertEquals(driver.findElement(By.id(this.prop.getProperty(textElement))).getText(),(textToCheck));
		}
		if (findElement == "xpath") {
			AssertJUnit.assertEquals(driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText(),(textToCheck));	
		}
		if (findElement == "className") {
			AssertJUnit.assertEquals(driver.findElement(By.className(this.prop.getProperty(textElement))).getText(),(textToCheck));
		}
		if (findElement == "linkText") {
			AssertJUnit.assertEquals(driver.findElement(By.linkText(this.prop.getProperty(textElement))).getText(),(textToCheck));
		}
		if (findElement == "text") {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//*[contains(text(), '" + textElement + "')]")).getText(),(textToCheck));
		}

	}
	
	private int checkTextReturn(String textElement, String textToCheck, String andEle, String iosEle  ) {
		String myText = "";
		int myReturn = 0;
		String findElement;
		if (getRunningOS().equals("mac")) {
			findElement = iosEle;
		} else {
			findElement = andEle;
		}
		
		
		if (findElement == "id") {
			myText = driver.findElement(By.id(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "xpath") {
			myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();	
		}
		if (findElement == "className")  {
			myText = driver.findElement(By.className(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "linkText") {
			myText = driver.findElement(By.linkText(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "text")  {
			myText = driver.findElement(By.xpath("//*[contains(text(), '" + textElement + "')]")).getText();
		}
		
		if (myText.equals(textToCheck) ) {
			myReturn = 1;
		}
		
		return myReturn;
	}
	
	private int checkTextContainsReturn(String textElement, String textToCheck, String andEle, String iosEle  ) {
		String myText = null;
		int myReturn = 0;
		
		String findElement;
		if (getRunningOS().equals("mac")) {
			findElement = iosEle;
		} else {
			findElement = andEle;
		}
		
		//myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();
		
		if (findElement == "id"){
			myText = driver.findElement(By.id(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "xpath") {
			myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();	
		}
		if (andEle == "className") {
			myText = driver.findElement(By.className(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "linkText")  {
			myText = driver.findElement(By.linkText(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "text") {
			myText = driver.findElement(By.xpath("//*[contains(text(), '" + textElement + "')]")).getText();
		}
		
		if (myText.contains(textToCheck)) {
			myReturn = 1;
		}
		
		return myReturn;
	}
	
	
	private Boolean checkElementReturn(String textElement, String andEle, String iosEle) {
		Boolean myReturnStatus;
		List<WebElement> options = null;
		String findElement;
		if (getRunningOS().equals("mac")) {
			findElement = iosEle;
		} else {
			findElement = andEle;
		}
		
		if (findElement == "id") {
			options = driver.findElements(By.id(this.prop.getProperty(textElement)));
		}
		
		if (findElement == "xpath")  {
			options = driver.findElements(By.xpath(this.prop.getProperty(textElement)));	
		}
		
		if (findElement == "className")  {
			options = driver.findElements(By.className(this.prop.getProperty(textElement)));
		}

		if (findElement == "linkText") {
			options = driver.findElements(By.linkText(this.prop.getProperty(textElement)));
		}
		
		if (findElement == "text") {
			options = driver.findElements(By.xpath("//*[contains(text(), '" + textElement + "')]"));
		} 
		
		if (findElement == "value") {
			options = driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		} 
		
		if (findElement == "name") {
			options = driver.findElements(By.xpath("//*[@name='" + textElement + "']"));
		} 
		
		if (findElement == "textAtt") {
			options = driver.findElements(By.xpath("//*[@text='" + textElement + "']"));
		} 
		
		if (findElement == "contValue") {
			options= driver.findElements(By.xpath("//*[contains(@value,'" + textElement + "')]"));
		} 

		
	
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
		
		
	}





	
	private Boolean checkElementNameReturn(String textElement) {
		Boolean myReturnStatus;
		List<WebElement> options= driver.findElements(By.xpath("//*[@name='" + textElement + "']"));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	/** checkElementTextViewReturnContains(String textElement)
	 * Check the //TextView element for text in the "value"
	 * 
	 * @param textElement - Text to search for
	 * @return - true if element is found, false if not
	 */
	private Boolean checkElementTextViewReturnContains(String textElement) {
		Boolean myReturnStatus;
		List<WebElement> options;
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[contains(@value,'" + textElement + "')]"));
		if (getRunningOS().equals("mac")) {
			options= driver.findElements(By.xpath("//*[contains(@value,'" + textElement + "')]"));
		} else {
			options= driver.findElements(By.xpath("//*[contains(@text,'" + textElement + "')]"));
		}
	
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	private int checkTextByXpathContainsReturn(String textElement, String textToCheck ) {
		String myText;
		int myReturn = 0;
		Boolean myElementBool = driver.findElements(By.xpath(this.prop.getProperty(textElement))).size() > 0;
		if (myElementBool == true ) {
			myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();
			if (myText.contains(textToCheck) ) {
				myReturn = 1;
			}
		}
		return myReturn;
	}

	
	/** checkElementTextViewRoboReturn(String textElement)
	 * Check to see if the element is found using the xpath of //RobotoTextView[@value="SOME NAME HERE"]
	 * This is common for text elements in the app
	 * 
	 * @param textElement - text of an element
	 * @return - false if the element is not found true if it is found. 
	 */
	private Boolean checkElementTextViewRoboReturn(String textElement) {
		Boolean myReturnStatus;
		List<WebElement> options;
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@value='" + textElement + "']"));
		if (getRunningOS().equals("mac")) {
			options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		} else {
			options= driver.findElements(By.xpath("//*[@text='" + textElement + "']"));
		}

		if (options.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	/** checkElementTextCustom(String textElement, String customText)
	 * Check to see if the element is found using the xpath of // CUSTOM TEXT [@value="SOME NAME HERE"]
	 * You could use this method instead of checkElementTextViewRoboReturn or checkElementTextViewReturn
	 * 
	 * @param textElement - text of an element
	 * @param customText - Part of the xpath of the element - valid settings so far: RobotoTextView, TextView, CapitalizedTextView
	 * @return - false if the element is not found true if it is found. 
	 */
	private Boolean checkElementTextCustom(String textElement, String customText) {
		Boolean myReturnStatus;
		
		//[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'test')]
		List<WebElement> options= driver.findElements(By.xpath("//" + customText + "[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," + textElement + "']"));
		
		//List<WebElement> options= driver.findElements(By.xpath("//" + customText + "[@value='" + textElement + "']"));
		if (options.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	private Boolean checkElementExistsByXpath(String textElement) {
		Boolean myReturnStatus;
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath(this.prop.getProperty(textElement)));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}

		//Check to see if the element is displayed
		for (int i = 0 ; i < options.size(); i++ ) {
			//System.out.println(options.get(i).getText());
			myReturnStatus = options.get(i).isDisplayed();
			//System.out.println(i + " My Return Status: " + myReturnStatus);
			//allText.add(i, options.get(i).getText());
		}
		
		
		return myReturnStatus;
	}
	
	private Boolean checkElementExistsByID(String textElement) {
		Boolean myReturnStatus;
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.id(this.prop.getProperty(textElement)));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	private boolean checkNoCaseList(String textToCheck, String pageSource, String containEqual){
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getAllElements();
		String foundText;
		String myOs;
		textToCheck = textToCheck.toLowerCase();
		myOs = getRunningOS();
		//System.out.println("My OS: " + myOs);
		if (myOs.equals("mac")){
			for (Element myElement : myTest ) {
				//if (myElement.attributes().get("shown").equals("true")) {
					//System.out.println("Name: ");
					//System.out.println(myElement.attributes().get("name"));
					//System.out.println("Value: ");
					///System.out.println(myElement.attributes().get("value"));
					foundText = myElement.attributes().get("name");
					foundText = foundText.toLowerCase();
					//System.out.println("******************************");
					//System.out.println("   Found Text: " + foundText);
					//System.out.println("Text To Check: " + textToCheck);
					//System.out.println("******************************");
					if (containEqual.equals("Equals")) {
						if (foundText.equals(textToCheck)) {
							return true;
						}
					} else {
						if (foundText.contains(textToCheck)) {
							return true;
						}
					}


				//}
			}

		} else {
			for (Element myElement : myTest ) {
				//System.out.println(myElement.attributes().get("value"));
				if (!myElement.attributes().get("text").equals("")) {	
					//foundText = myElement.attributes().get("value");
					foundText = myElement.attributes().get("text");
					foundText = foundText.toLowerCase();
					//System.out.println("******************************");
					//System.out.println("Found Text:" + foundText);
					//System.out.println("Text To Check: " + textToCheck);
					//System.out.println("******************************");
					if (containEqual.equals("Equals")) {
						if (foundText.equals(textToCheck)) {
							return true;
						}
					} else {
						if (foundText.contains(textToCheck)) {
							return true;
						}
					}
				}
			}
		}
	    return false;
	  }
	
	
	private boolean checkNoCaseListDEBUG(String textToCheck, String pageSource, String containEqual){
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getAllElements();
		String foundText;
		String myOs;
		textToCheck = textToCheck.toLowerCase();
		myOs = getRunningOS();
		//System.out.println("My OS: " + myOs);
		if (myOs.equals("mac")){
			for (Element myElement : myTest ) {
				//if (myElement.attributes().get("shown").equals("true")) {
					//System.out.println("Name: ");
					//System.out.println(myElement.attributes().get("name"));
					//System.out.println("Value: ");
					///System.out.println(myElement.attributes().get("value"));
					foundText = myElement.attributes().get("name");
					foundText = foundText.toLowerCase();
					System.out.println("******************************");
					System.out.println("   Found Text: " + foundText);
					System.out.println("Text To Check: " + textToCheck);
					System.out.println("******************************");
					if (containEqual.equals("Equals")) {
						if (foundText.equals(textToCheck)) {
							return true;
						}
					} else {
						if (foundText.contains(textToCheck)) {
							return true;
						}
					}


				//}
			}

		} else {
			for (Element myElement : myTest ) {
				//System.out.println(myElement.attributes().get("text"));
				//if (myElement.attributes().get("shown").equals("true")) {
				if (!myElement.attributes().get("text").equals("")) {	
					foundText = myElement.attributes().get("text");
					foundText = foundText.toLowerCase();
					System.out.println("******************************");
					System.out.println("Found Text:" + foundText);
					System.out.println("Text To Check: " + textToCheck);
					System.out.println("******************************");
					if (containEqual.equals("Equals")) {
						if (foundText.equals(textToCheck)) {
							return true;
						}
					} else {
						if (foundText.contains(textToCheck)) {
							return true;
						}
					}
				}
			}
		}
	    return false;
	  }
	
	private List<String> createUserList(List<String> userList, String pageSource){
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getAllElements();
		String foundText;
		
		for (Element myElement : myTest ) {
			//System.out.println("Searching for user: ");
			//System.out.println(myElement.attributes().get("value"));
			if (!myElement.attributes().get("text").equals("")) {
				foundText = myElement.attributes().get("text");
				//foundText = foundText.toLowerCase();
				if ((!foundText.isEmpty()) && (foundText.contains(","))){			
					if ((foundText.contains("2015")) || (foundText.contains("2016"))){
						//System.out.println("Date? :" + foundText);
					} else {
						userList.add(foundText);
						System.out.println("Adding User: " + foundText);
					}
						
				}
				
				
				//System.out.println("******************************");
				//System.out.println("Found Text:" + foundText);
				//System.out.println("Text To Check: " + textToCheck);
				//System.out.println("******************************");
			}
		}
		return userList;
	}
	
	
	
	/** displayAllTextViewElements()
	 * For debugging - will display the text of the element. 
	 * 
	 */
	private void displayAllTextViewElements(String textElement) {
		List<WebElement> options= driver.findElements(By.xpath("//" + textElement ));
		for (int i = 0 ; i < options.size(); i++ ) {
			System.out.println(options.get(i).getText());
		}
	}
	
	private String getRunningOS() {
		String myOs;
		myOs = driver.getCapabilities().getPlatform().toString();
		myOs = myOs.toLowerCase();
		if (myOs.equals("any")) {
			myOs = "android";
		}
		return myOs;
	}
	
	private String getSourceOfPage() {
		String myString;
		myString = driver.getPageSource();
		
		//System.out.println("****************************************************");
		//System.out.println("Page Source: " + myString);
		//System.out.println("****************************************************");
		
		return myString;
	}
	
	
	
	private List<String> getAllText() {
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[@enabled='true']"));
		List<WebElement> options= driver.findElements(By.xpath("//*[contains(@value, ',')]"));
		
		List<String> allText = new ArrayList<String>();
		String myText;
		for (int i = 0 ; i < options.size(); i++ ) {
			//System.out.println(options.get(i).getText());
			myText = options.get(i).getText();
			System.out.println("ALL TEXT: " + myText);
			allText.add(myText);
			//allText.add(i, options.get(i).getText());
		}

		return allText;
	}
	
	private String getTextXpath(String textElement) {
		String returnText;
		returnText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();
		return returnText;
	}
	
	private String getText(String textElement, String andEle, String iosEle ) {
		String returnText = null;
		String findElement;
		if (getRunningOS().equals("mac")) {
			findElement = iosEle;
		} else {
			findElement = andEle;
		}
		
		if (findElement == "id") {
			returnText = driver.findElement(By.id(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "xpath") {
			returnText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();	
		}
		if (findElement == "className") {
			returnText = driver.findElement(By.className(this.prop.getProperty(textElement))).getText();
		}
		if (findElement == "linkText") {
			returnText = driver.findElement(By.linkText(this.prop.getProperty(textElement))).getText();
		}

		
		return returnText;
	}
	
	
	
	public void getBishopricInfo() throws Exception {

		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		clickButtonByXpathTitleName("Bishopric");
		
		//Go to web and get all users
		myList = myWeb.getAllMembersOnPage("OrganizationsMenu", "Bishopric", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		Thread.sleep(1000);
		

	}

	public void getHighPriestsGroupInfo() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		

		clickButtonByXpathTitleName("High Priests Group");
		clickButtonByXpathTitleName("High Priests Group Leadership");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "High Priests Group", "HighPriestGroupLeadership", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Home Teaching District Supervisors");

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "High Priests Group", "HighPriestGroupDistrictSupervisors", false);
		compareWebData(myList, androidList, true);

		pressBackKey();
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All High Priests Group Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "High Priests Group", "HighPriestGroupMembers", false);
		compareWebData(myList, androidList, true);
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		pressBackKey();
		Thread.sleep(1000);
		
	}
	

	public void getEldersQuorum() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();

		clickButtonByXpathTitleName("Elders Quorum");
		clickButtonByXpathTitleName("Elders Quorum Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Elders Quorum", "EldersQuorumPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		//clickButtonByXpathTitleName("Home Teaching District Supervisors");

		//myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Elders Quorum", "EldersQuorumDistrictSupervisors", false);
		//compareWebData(myList, androidList, false);

		//pressBackKey();
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Elders Quorum Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}


		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Elders Quorum", "EldersQuorumMembers", false);
		compareWebData(myList, androidList, true);
		
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		pressBackKey();
		Thread.sleep(1000);
		
	}
	

	public void getReliefSociety() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		
		String ReliefSocietyName = "Relief Society";
		
		if (checkElementReturn("Relief Society", "textAtt", "value") == false) {
			ReliefSocietyName = "Relief Society 1";
		}

		clickButtonByXpathTitleName(ReliefSocietyName);
		clickButtonByXpathTitleName("Relief Society Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", ReliefSocietyName, "ReliefSocietyPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Visiting Teaching");

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", ReliefSocietyName, "VisitingTeachingSupervisors", false);
		compareWebData(myList, androidList, true);

		pressBackKey();
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Relief Society Members");
		} else {
			clickButtonByXpathTitleName("All Members");
			
			
		}

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", ReliefSocietyName, "ReliefSocietyMembers", false);
		compareWebData(myList, androidList, true);
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		pressBackKey();
		Thread.sleep(1000);

	}

	
	public void getYoungMenInfo() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();

		clickButtonByXpathTitleName("Young Men");
		clickButtonByXpathTitleName("Young Men Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "YoungMenPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Priests Quorum");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Priests Quorum Presidency");
		}


		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "PriestsQuorum", false);
		compareWebData(myList, androidList, true);

		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();
		
		clickButtonByXpathTitleName("Teachers Quorum");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Teachers Quorum Presidency");
		}

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "TeachersQuorum", false);
		compareWebData(myList, androidList, true);
		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();

		clickButtonByXpathTitleName("Deacons Quorum");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Deacons Quorum Presidency");
		}
		

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Men", "DeaconsQuorum", false);
		compareWebData(myList, androidList, true);
		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
	}

	
	
	public void getYoungWomenInfo() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();

		clickButtonByXpathTitleName("Young Women");
		clickButtonByXpathTitleName("Young Women Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "YoungWomenPresidency", false);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		clickButtonByXpathTitleName("Laurel");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Laurel Presidency");
		}


		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "Laurel", false);
		compareWebData(myList, androidList, true);

		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();
		
		clickButtonByXpathTitleName("Mia Maid");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Mia Maid Presidency");
		}
		

		
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "MiaMaid", false);
		compareWebData(myList, androidList, true);
		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();

		clickButtonByXpathTitleName("Beehive");
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Beehive Presidency");
		}
		

		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Young Women", "Beehive", false);
		compareWebData(myList, androidList, true);
		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
	}
	
	
	public void getSundaySchoolInfo() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();

		clickButtonByXpathTitleName("Sunday School");
		clickButtonByXpathTitleName("Sunday School Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Sunday School", "SundaySchoolPresidency", false);
		compareWebData(myList, androidList, true);
		pressBackKey();
		
		sundaySchoolClassSub("Gospel Doctrine", "GospelDoctrine");
		sundaySchoolClassSub("Course 17", "Course17");
		sundaySchoolClassSub("Course 16", "Course16");
		sundaySchoolClassSub("Course 15", "Course15");
		sundaySchoolClassSub("Course 14", "Course14");
		sundaySchoolClassSub("Course 13", "Course13");
		sundaySchoolClassSub("Course 12", "Course12");

		
		Thread.sleep(1000);
		pressBackKey();
	
	
	}
	
	public void sundaySchoolClassSub(String className, String subReport) throws Exception {
		List<String> myList = new ArrayList<String>();
		//Data from android list
		List<String> androidList = new ArrayList<String>();
		String macAllMembers = "All " + className + " Members";
		String classAllMembers = subReport + "Members";
		
		
		clickButtonByXpathTitleName(className);
		
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Sunday School", subReport, false);
		compareWebData(myList, androidList, true);
		
	
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName(macAllMembers);
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		
		
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Sunday School", classAllMembers, false);
		compareWebData(myList, androidList, true);
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();
		
		
	}
	
	public void getPrimaryInfo() throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();

		clickButtonByXpathTitleName("Primary");
		clickButtonByXpathTitleName("Primary Presidency");
		Thread.sleep(1000);
		
		
		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInOrganization("OrganizationsMenu", "Primary", "PrimaryPresidency", true);
		compareWebData(myList, androidList, true);
		
		pressBackKey();
		
		//Need to get info on each of the classes....
		//Right now iOS and Android are showing the data differently
		//If there is no teacher the class is not showing up in Android
		
		
		
		Thread.sleep(1000);
		pressBackKey();
	
	
	}
	
	
	public void getHTVTReport(String org, String htvtReport, String leaderShip) throws Exception {
		//LDSWeb myWeb = new LDSWeb();
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		
		//Data from android list
		List<String> androidList = new ArrayList<String>();

		
		Thread.sleep(2000);
		if (htvtReport.equals("HouseholdsNotVisited")) {
			clickButtonByXpath("3Months");
		}
		

		//Check web data vs LDS Tools
		myList = myWeb.getAllMembersInHTVTReport(org, htvtReport, myUserName, myPassword, leaderShip);
		if(getRunningOS().equals("mac")) {
			compareWebData(myList, androidList, false);
		} else {
			compareWebData(myList, androidList, true);
		}
		
		
	}
	

	/** clickLastTextViewRoboReturn(String textElement)
	 * This will match the last element found 
	 * 
	 * @param textElement - text of an element
	 */
	private void clickLastTextViewRoboReturn(String textElement) {
		int myCounter;
		List<WebElement> options;
		//displayAllTextViewElements(textElement);
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@id='text1'][@value='" + textElement + "']"));
		if (getRunningOS().equals("mac")) {
			options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		} else {
			options= driver.findElements(By.xpath("//*[@text='" + textElement + "']"));
		}
		
		myCounter = options.size() - 1;
		options.get(myCounter).click();
	
	}
	
	private void clickLastTextViewRoboReturnContains(String textElement) {
		int myCounter;
		List<WebElement> options;
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[contains(@value, '" + textElement + "')]"));
		
		if (getRunningOS().equals("mac")) {
			options= driver.findElements(By.xpath("//*[contains(@value, '" + textElement + "')]"));
		} else {
			options= driver.findElements(By.xpath("//*[contains(@text, '" + textElement + "')]"));
		}
		
		
		//List<WebElement> options= driver.findElements(By.xpath("//*[contains(@value, '" + textElement + "')]"));
		//driver.findElement(By.xpath("//RobotoTextView[contains(@value='" + textElement + "')]")).click();
		myCounter = options.size() - 1;
		options.get(myCounter).click();
	
	}
	
	
	

	/** clickButtonByID(String textElement )
	 * Click an element that by ID
	 * 
	 * @param textElement - ID of element must be found if uiMap
	 */
	private void clickButtonByID(String textElement ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.prop.getProperty(textElement))));
		myElement.click();

		//driver.findElement(By.id(this.prop.getProperty(textElement))).click();
	}
	
	private void clickButton(String textElement, String andEle, String iosEle  ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = null;
		String findElement;
		if (getRunningOS().equals("mac")) {
			//IOSElement myElement = null;
			findElement = iosEle;
		} else {
			//AndroidElement myElement = null;
			findElement = andEle;
		}
		
		if (findElement == "id") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.prop.getProperty(textElement))));
		}
		
		if (findElement == "xpath")  {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(this.prop.getProperty(textElement))));	
		}
		
		if (findElement == "className")  {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.className(this.prop.getProperty(textElement))));
		}

		if (findElement == "linkText") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(this.prop.getProperty(textElement))));
		}
		
		if (findElement == "text") {
			//myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + textElement + "')]")));
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text, '" + textElement + "')]")));
		} 
		
		if (findElement == "nameContains") {
			//myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + textElement + "')]")));
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name, '" + textElement + "')]")));
		} 
		
		if (findElement == "value") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='" + textElement + "']")));
		} 
		
		if (findElement == "name") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='" + textElement + "']")));
		} 
		
		if (findElement == "textAtt") {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='" + textElement + "']")));
		} 

		myElement.click();

	}
	

	/** clickButtonByXpath(String textElement)
	 * Click an element that by Xpath
	 * 
	 * @param textElement - Xpath of element must be found if uiMap
	 */
	private void clickButtonByXpath(String textElement) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(this.prop.getProperty(textElement))));
		myElement.click();
		//driver.findElement(By.xpath(this.prop.getProperty(textElement))).click();
	}
	
	
	
	/** clickButtonByXpathTitleName(String textElement )
	 * Click the button that has the xpath of //TextView[@value" TEXT OF ELEMENT "]
	 * 
	 * @param textElement - text of element
	 */
	private void clickButtonByXpathTitleName(String textElement ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement; 
		//Changing this for Android 6.0 and later
		if(getRunningOS().equals("mac")) {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='" + textElement + "']")));
		} else {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='" + textElement + "']")));
		}

		myElement.click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void clickButtonByName(String textElement ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='" + textElement + "']")));
		myElement.click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	


	
	private void clickButtonByXpathTitleNameContains(String textElement ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement;
		if (getRunningOS().equals("mac")){
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name, '" + textElement + "')]")));
		} else {
			myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@value, '" + textElement + "')]")));
		}
		myElement.click();
		

		//driver.findElement(By.xpath("//*[contains(@value, '" + textElement + "')]")).click();

		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void clickItemByXpathRoboTextContains(String textElement ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name, '" + textElement + "')]")));
		myElement.click();
		//WebElement element;
		//System.out.println("TEXT ELEMENT: " + textElement);
		//driver.findElement(By.xpath("//RobotoTextView[contains(@value, '" + textElement + "')]")).click();
		//driver.findElement(By.xpath("//*[contains(@name, '" + textElement + "')]")).click();

		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void clickDirectoryUserIOS(String textElement ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//UIATableCell/UIAStaticText[contains(@name, '" + textElement + "')]")));
		myElement.click();

		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

	/** sendTextbyID(String textElement, String textToSend )
	 * 
	 * @param textElement - Element by ID must be in the uiMap
	 * @param textToSend - Text to send to the element
	 */
	private void sendTextbyID(String textElement, String textToSend ) {
		driver.findElement(By.id(this.prop.getProperty(textElement))).sendKeys(textToSend);
	}
	
	/** sendTextbyXpath(String textElement, String textToSend )
	 * 
	 * @param textElement - Element by Xpath must be in the uiMap
	 * @param textToSend - Text to send to the element
	 */
	private void sendTextbyXpath(String textElement, String textToSend ) {
		//System.out.println("Text to send: " + textToSend);
		driver.findElement(By.xpath(this.prop.getProperty(textElement))).sendKeys(textToSend);
	}
	
	private void sendTextbyXpath2(String textElement, String textToSend) {
		clickButtonByXpath(textElement);
		driver.executeScript("target.frontMostApp().keyboard().typeString('" + textToSend + "')");
		
		//IOSElement myElement = (IOSElement) driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		//myElement.setValue(textToSend);
		
		//WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		//myElement.sendKeys(textToSend);
	}
	
	
	private void clearTextFieldXpath(String textElement) throws Exception {
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		if (getRunningOS().equals("mac")) {
			myElement.click();
			myElement.clear();
			
		} else {
			myElement.click();
			myElement.clear();
		}
		Thread.sleep(1000);
		
	}
	
	private void clearTextField(String textElement, String andEle, String iosEle  ) {

		WebElement myElement = null;
		String findElement;
		if (getRunningOS().equals("mac")) {
			findElement = iosEle;
		} else {
			findElement = andEle;
		}
		
		if (findElement == "id") {
			myElement = driver.findElement(By.id(this.prop.getProperty(textElement)));
		}
		
		if (findElement == "xpath") {
			myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		}
		
		if (findElement == "className")  {
			myElement = driver.findElement(By.className(this.prop.getProperty(textElement)));
		}

		if (findElement == "linkText") {
			myElement = driver.findElement(By.linkText(this.prop.getProperty(textElement)));
		}
		
		if (findElement == "text") {
			myElement = driver.findElement(By.xpath("//*[contains(text(), '" + textElement + "')]"));
		} 
		
		if (findElement == "value") {
			myElement = driver.findElement(By.xpath("//*[@value='" + textElement + "']"));
		} 
		
		if (findElement == "name") {
			myElement = driver.findElement(By.xpath("//*[@name='" + textElement + "']"));
		} 
		
		if (findElement == "textAtt") {
			myElement = driver.findElement(By.xpath("//*[@text='" + textElement + "']"));
		} 

		
		myElement.click();
		myElement.clear();
		
		
	}
		
	
	

	/** waitForTextToDisappear(String textElement, int myTimeOut)
	 * This will wait for an element to disappear 
	 * Used to wait for the sync to finish
	 * 
	 * @param textElement - element to check by xpath in uiMap
	 * @param myTimeOut - number of seconds to wait before giving up
	 */
	private void waitForTextToDisappear(String textElement, int myTimeOut){
		WebDriverWait wait = new WebDriverWait(driver, myTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.prop.getProperty(textElement))));
	}
	
	private void waitForTextToDisappearID(String textElement, int myTimeOut){
		WebDriverWait wait = new WebDriverWait(driver, myTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(this.prop.getProperty(textElement))));
	}
	
	/** flickRightToLeft()
	 * Flick or swipe from right to left
	 * 
	 */
	private void flickRightToLeft(){
		WebElement pages = driver.findElement(By.id("pager"));
		//Smaller Devices
		//TouchActions flick = new TouchActions(driver).flick(pages, -1500, 0, 0);
		//Larger devices - seems to work for larger and smaller devices
		TouchActions flick = new TouchActions(driver).flick(pages, -2500, 0, 0);
		flick.perform();
	}
	
	/** flickLeftToRight()
	 * Flick or swipe from left to right
	 */
	private void flickLeftToRight(){
		WebElement pages = driver.findElement(By.id("pager"));
		TouchActions flick = new TouchActions(driver).flick(pages, 5500, 0, 0);
		flick.perform();
	}
	
	/** flickUpOrDown(int yNumber)
	 * Flick up is a -500 down is 500
	 * 
	 * @param yNumber Number of pixel to swipe up or down
	 */
	private void flickUpOrDown(int yNumber){
		WebElement pages = driver.findElement(By.id("pager"));
		TouchActions flick = new TouchActions(driver).flick(pages, 0, yNumber, 0);
		flick.perform();
	}
	
	/** moveSlider(String textElement, int xCords, int yCords, int moveSpeed )
	 * Move a slider 
	 * 
	 * @param textElement - xpath element in uiMap
	 * @param xCords - x cords can be positive or negative
	 * @param yCords - y cords can be positive or negative
	 * @param moveSpeed - speed of movement
	 */
	private void moveSlider(String textElement, int xCords, int yCords, int moveSpeed ) {
        WebElement objSlider = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
        TouchActions drag = new TouchActions(driver).flick(objSlider, xCords, yCords, moveSpeed);
        drag.perform();
	}

	
	
	/** scrollDown(String textElement, int distanceMove)
	 * This will select an element by //TextView then scroll down
	 * 
	 * @param textElement - Text of an element
	 * @param distanceMove - Distance to move 
	 */
	/*
	private void scrollDown(String textElement, int distanceMove){
		WebElement myElement = driver.findElement(By.xpath("//TextView[@value='" + textElement + "']"));
		TouchActions actions = new TouchActions(driver);
		Point p=myElement.getLocation();
		actions.down(p.x, p.y);
		actions.move(p.x, p.y - distanceMove);
		actions.up(p.x, p.y);
		actions.perform();
	}
	*/
	
	/** scrollDown(String textElement, int distanceMove)
	 * This will select an element by //TextView then scroll down
	 * 
	 * @param textElement - Text of an element
	 * @param distanceMove - Distance to move 
	 */
	private void scrollDown(String textElement, int distanceMove) throws Exception {
		int myCheck;
		int myCounter = 0;
		//textElement = "//*[@value='" + textElement + "']";
		textElement = "//*[@text='" + textElement + "']";
		//WebDriverWait wait = new WebDriverWait(driver, 20);

		//Scroll down if element is not found.
		myCheck = checkElementXpathReturn(textElement);
		//System.out.println("Before While loop - Check: " + myCheck);
		//System.out.println("Distance to move: " + distanceMove);
		while ((myCheck == 0) && (myCounter < 8 )) {
			//scrollDownDistance(distanceMove);
			scrollDownTEST(distanceMove);
			myCheck = checkElementXpathReturn(textElement);
			myCounter++;
			//System.out.println(textElement);
			//System.out.println("Counter " + myCounter);
			//System.out.println("My Check: " + myCheck);
		}
		
		driver.findElement(By.xpath(textElement)).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	
	

	/** checkElementXpathReturn(String textElement)
	 * 
	 * @param textElement
	 * @return - found = 1 or not found = 0
	 */
	private int checkElementXpathReturn(String textElement) {
		int myReturnStatus = 0;
		List<WebElement> options= driver.findElements(By.xpath(textElement));
		if (options.isEmpty()) {
			myReturnStatus = 0;
			return myReturnStatus;
		}
		myReturnStatus = 1;
		return myReturnStatus;
	}
	
	
	/** scrollDownDistance(int scrollDistance )
	 * Select the first TextView then scroll down
	 * 
	 * @param scrollDistance - Distance to scroll
	 */
	private void scrollDownDistance(int scrollDistance ) throws Exception {
		TouchActions actions = new TouchActions(driver);
		String lastLinearLayout;
		
		/*
		Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		
		System.out.println("Trying to move!");
		System.out.println("Width: " + screenWidth);
		System.out.println("Height: " + screenHeight);
		
		screenWidth = screenWidth - 10;
		screenHeight = screenHeight - 10;
		
		actions.down(screenWidth, screenHeight);
		actions.pause(3000);
		actions.move(screenWidth, screenHeight - scrollDistance);
		actions.pause(2000);
		actions.up(screenWidth, screenHeight - scrollDistance);
		*/
		lastLinearLayout = getLastIcon();
		//actions.moveToElement(driver.findElement(By.xpath(lastLinearLayout)));
		actions.scroll(driver.findElement(By.xpath(lastLinearLayout)), 0, 1000);
		//actions.flick(driver.findElement(By.xpath(lastLinearLayout)), 0, scrollDistance, 100);
		
		
		//actions.flick(driver.findElement(By.id("title")), 0, scrollDistance, 100);

		
		//actions.flick(0, -1000);
		//actions.scroll(0, scrollDistance);
		

		
		actions.perform();
		
	}
	
	
	private void scrollDownTEST(int scrollDistance ) throws Exception {
		//TouchActions actions = new TouchActions(driver);
		//WebElement myElement = driver.findElement(By.xpath("//CheckableLinearLayout[10]/RelativeLayout/LinearLayout"));
		//actions.flick(driver.findElement(By.xpath("//LinearLayout")), 0, scrollDistance, 100);

		Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		//int screenUp;
		
		//System.out.println("Trying to move!");
		//System.out.println("Width: " + screenWidth);
		//System.out.println("Height: " + screenHeight);
		
		screenWidth = screenWidth / 3;
		//screenWidth = screenWidth - 75;
		//screenHeight = screenHeight / 2;
		screenHeight = screenHeight - 100;
		//scrollDistance = screenHeight - scrollDistance;
		
		//System.out.println("Width: " + screenWidth);
		//System.out.println("Height: " + screenHeight);
		//System.out.println("Distance: " + scrollDistance);
		TouchAction actions = new TouchAction(driver);
		//actions.press(screenWidth, screenHeight).moveTo(0, -scrollDistance).release().perform();

		actions.press(screenWidth, screenHeight).moveTo(0, -scrollDistance).waitAction(2000).release().perform();
		
		/*
		actions.down(screenWidth, screenHeight);
		//actions.pause(1000);
		//actions.move(screenWidth, screenHeight -100 );
		actions.move(screenWidth, screenHeight - scrollDistance );
		//actions.pause(3000);
		//actions.up(screenWidth, screenUp);
		actions.up(screenWidth, 0);
	
		actions.perform();
		*/
		
		Thread.sleep(5000);
		
	}
	
	private void scrollDownSlow(int scrollDistance) throws Exception {
		Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();

		
		screenWidth = screenWidth / 3;
		screenHeight = screenHeight - 70;
		scrollDistance = screenHeight - scrollDistance;
	
		
		driver.swipe(screenWidth, screenHeight, screenWidth, scrollDistance, 2000);

		Thread.sleep(2000);
		
	}
	
	
	
	
	private void scrollDownIOS() throws Exception {
	   JavascriptExecutor js = (JavascriptExecutor) driver;
	   HashMap<String, String> scrollObject = new HashMap<String, String>();
	   scrollObject.put("direction", "down");
	   js.executeScript("mobile: scroll", scrollObject);
           
	}
	
	private void scrollToElementIOS(String myText) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//List<WebElement> options= driver.findElements(By.xpath("//*[@name='" + myText + "']"));
		WebElement element = driver.findElement(By.xpath("//*[@label='" + myText + "']"));

		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);


	}
	
	private void scrollToLastElementIOS(String myText) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//List<WebElement> options= driver.findElements(By.xpath("//*[@name='" + myText + "']"));
		List<WebElement> options= driver.findElements(By.xpath("//*[@label='" + myText + "']"));
		WebElement element = options.get(options.size() -1 ) ;

		//WebElement element = driver.findElement(By.xpath("//*[@name='" + myText + "']"));
		//HashMap<String, String> scrollToObject = new HashMap<String, String>();
		//scrollToObject.put("element",((RemoteWebElement) element).getId());
		//js.executeScript("mobile: scrollTo", scrollToObject);
		//js.executeScript("mobile: scroll", scrollToObject);
		

		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);

		
		//element.click();

	}
	
	
	

	
	private void scrollUpIOS() throws Exception {
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   HashMap<String, String> scrollObject = new HashMap<String, String>();
		   scrollObject.put("direction", "up");
		   js.executeScript("mobile: scroll", scrollObject);
	           
		}
	
	private void scrollToTopDirectoryIOS() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[3]"));;
		HashMap<String, String> scrollToObject = new HashMap<String, String>();
		scrollToObject.put("element",((RemoteWebElement) element).getId());
		js.executeScript("mobile: scrollTo", scrollToObject);

	}
	
	
	
	
	
	
	private void scrollDownPerPage(int scrollDistance ) throws Exception {
		TouchActions actions = new TouchActions(driver);
		//WebElement myElement = driver.findElement(By.xpath("//CheckableLinearLayout[10]/RelativeLayout/LinearLayout"));
		//actions.flick(driver.findElement(By.xpath("//LinearLayout")), 0, scrollDistance, 100);

		Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		int screenUp;
		
		
		screenWidth = screenWidth / 2;
		screenHeight = screenHeight -10;
		screenUp = screenHeight  - 5;
		scrollDistance = -5;
	
		
		System.out.println("Width: " + screenWidth);
		System.out.println("Height: " + screenHeight);
		System.out.println("Up: " + screenUp);
		
		actions.down(screenWidth, screenHeight);
		//actions.pause(1000);
		actions.move(screenWidth, scrollDistance );
		//actions.pause(3000);
		actions.up(screenWidth, screenUp);

		actions.perform();
		
		Thread.sleep(5000);
		
	}
	
	
	private void scrollUpTEST(int scrollDistance ) throws Exception {
		//TouchActions actions = new TouchActions(driver);
		//WebElement myElement = driver.findElement(By.xpath("//CheckableLinearLayout[10]/RelativeLayout/LinearLayout"));
		//actions.flick(driver.findElement(By.xpath("//LinearLayout")), 0, scrollDistance, 100);

		Dimension dimensions = driver.manage().window().getSize();
		int screenWidth = dimensions.getWidth();
		int screenHeight = dimensions.getHeight();
		
		//System.out.println("Trying to move!");
		//System.out.println("Width: " + screenWidth);
		//System.out.println("Height: " + screenHeight);
		
		screenWidth = screenWidth / 2;
		//screenWidth = screenWidth - 75;
		screenHeight = (int) (screenHeight / 1.5);
		
		TouchAction actions = new TouchAction(driver);
		actions.press(screenWidth, screenHeight).moveTo(0, scrollDistance).release().perform();
		
		/*
		actions.down(screenWidth, screenHeight);
		actions.pause(1000);
		actions.move(screenWidth, scrollDistance);
		actions.pause(1000);
		actions.up(screenWidth, 10);
		
		actions.perform();
		*/
	}
	
	
	private void scrollUpFromElement( String textElement, int scrollDistance ) throws Exception {
		TouchActions actions = new TouchActions(driver);
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		//actions.flick(myElement, 0, scrollDistance, 100);
		actions.scroll(myElement, 0, scrollDistance);
		actions.perform();
	}
	
	
	
	
	/** longPressByXpath(String textElement)
	 * This will perform a long press on element
	 * 
	 * @param textElement - xpath element must be in uiMap
	 */
	private void longPressByXpath(String textElement) {
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
		//This was doing a longpress on the wrong element
		//TouchActions longPress = new TouchActions(driver).longPress(myElement);
		//longPress.perform();
		TouchActions actions=new TouchActions(driver);
		Point p=myElement.getLocation();
		actions.down(p.x, p.y);
		actions.pause(2000);
		actions.up(p.x, p.y);
		actions.perform();
	}
	
	/** longPressByTextView(String textElement)
	 * This will perform a long press on //TextView text
	 * 
	 * @param textElement - text of an element
	 */
	private void longPressByTextView(String textElement) {
		//WebElement myElement = driver.findElement(By.xpath("//*[@value='" + textElement + "']"));
		//WebElement myElement = driver.findElement(By.xpath("//*[contains(@value, '" + textElement + "')]"));
		WebElement myElement = driver.findElement(By.xpath("//*[contains(@text, '" + textElement + "')]"));
		//WebElement myElement = driver.findElement(By.id("signin_instructions"));
		//WebElement muElements = (WebElement) driver.findElements(By.id("signin_instructions"));

		//This was doing a longpress on the wrong element
		//TouchActions longPress = new TouchActions(driver).longPress(myElement);
		//longPress.perform();
		TouchAction action = new TouchAction(driver);
		action.longPress(myElement).release().perform();
		
		
		//driver.tap(2, myElement, 3);

		//TouchActions actions = new TouchActions(driver);
		//actions.longPress(myElement).release().perform();
		
		//actions.longPress(myElement).release().perform();
		
		/*
		Point p=myElement.getLocation();
		//System.out.println("X: " + p.x + "Y: " + p.y);
		actions.down(p.x, p.y);
		actions.pause(2000);
		actions.up(p.x, p.y);
		actions.perform();
		*/
		
	}

	/** pressMenuKey()
	 * Press the menu key
	 * 
	 */
	private void pressMenuKey() {
		new Actions(driver).sendKeys(SelendroidKeys.MENU).perform();
	}
	
	/** pressBackKey()
	 * Press the back key
	 * 
	 */
	private void pressBackKey() throws Exception {
		Thread.sleep(1000);
		String myOs;
		//capabilities.setCapability("platformName", "Android");
		myOs = getRunningOS();
		if (myOs.equals("mac")){
			if (checkElementExistsByXpath("TopBack").equals(true)) {
				clickButtonByXpath("TopBack");
			}	
		} else {
			driver.navigate().back();
			//new Actions(driver).sendKeys(SelendroidKeys.BACK).perform();
			Thread.sleep(1000);
		}

	}
	
	private void pressEnterKey() {
		new Actions(driver).sendKeys(SelendroidKeys.ENTER).perform();
	}
	
	private void pressSearchKey() {
		new Actions(driver).sendKeys(SelendroidKeys.SEARCH).perform();
	}
	
	/** pressHomeKey()
	 * Press the home key
	 * 
	 */
	private void pressHomeKey() {
		new Actions(driver).sendKeys(SelendroidKeys.ANDROID_HOME).perform();
	}
	
	
	//************************************************************
	//*************** Start of command sequences *****************
	//************************************************************
	
	
	
	/** syncLogIn(String loginName, String loginPassword, String chooseNetwork )
	 * Log into LDS tools
	 * 
	 * @param loginName - login name
	 * @param loginPassword - login password
	 * @param chooseNetwork - Network to use "Production", "UAT", "Proxy - UAT", "Proxy"
	 * @throws Exception
	 */
	private void syncLogIn(String loginName, String loginPassword, String chooseNetwork, String os )  throws Exception {
		int pageSize;
		
		myUserName = loginName;
		myPassword = loginPassword;
		
		//If the login is using any of the test networks we need to change it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy"
		if (os.equals("android")) {
			System.out.println("Orientation: " + driver.getOrientation().value());
			if (driver.getOrientation().value() == "landscape") {
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
			
			
			if (!chooseNetwork.equals("Production")) {
				//Just for testing
				Thread.sleep(10000);
				
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				
				
				clickButtonByXpath("Menu");
				Thread.sleep(1000);
				clickButtonByXpath("OverflowSettings");
				Thread.sleep(2000);
				
				driver.scrollToExact("Network Environment").click();
				//scrollDown("Network Environment", 35 );
				
				clickButtonByXpathTitleName(chooseNetwork);
				clickButtonByXpath("Back");
				Thread.sleep(5000);
			}

			//driver.resetApp();
			//Thread.sleep(3000);
			
			//driver.closeApp();
			//driver.launchApp();
			//Thread.sleep(3000);
			
			//sendTextbyXpath("LoginUsername", loginName);
			//sendTextbyXpath("LoginPassword", loginPassword);
			sendTextbyID("LoginUsername", loginName);
			sendTextbyID("LoginPassword", loginPassword);
			clickButtonByID("SignInButton");
			//clickButtonByXpath("SignInButton");
			Thread.sleep(4000);
			waitForTextToDisappearID("SyncText", 500 );
			Thread.sleep(2000);
		}
		
		if (os.equals("ios")) {
			if (!chooseNetwork.equals("Production")) {
				Thread.sleep(1000);
				clickButtonByXpath("TopHelp");
				
				
				if (checkElementExistsByXpath("DeveloperSettings") == true) {
					clickButtonByXpath("DeveloperSettings");
				} else {
					//New way to enable dev settings
					for (int x = 1; x <= 5; x++ ) {
						clickButtonByXpath("EnableDevSettings");
					}
				}

				
				clickButtonByXpathTitleNameContains("Environment");
				clickButtonByXpath("Proxy");
				clickButtonByXpath(chooseNetwork);
				clickButtonByXpath("TopDeveloper");
				clickButtonByXpath("TopHelp");
				clickButtonByXpath("TopSignIn");
			}
			
			//sendTextbyXpath("LoginUsername", "LDSTools14");
			//sendTextbyXpath("LoginPassword", "toolstester");
			sendTextbyXpath2("LoginUsername", loginName);
			sendTextbyXpath2("LoginPassword", loginPassword);
			
			//Thread.sleep(1000);
			clickButtonByXpath("DoneButton");
			//clickButtonByXpath("SignInButton");
			//Thread.sleep(1000);
			//clickButtonByXpath("SignInButton");
			Thread.sleep(4000);
			
			
			unitsToSync();
			
			waitForTextToDisappear("DownloadingSync", 500 );
			Thread.sleep(8000);
		}
	}
	
	private void loginProxyData(String IndividualId, String units, String positions, String chooseNetwork, String userName )  throws Exception {
		//If the login is using any of the test networks we need to chagne it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy"
		System.out.println("User Name: " + userName);
		if (!getRunningOS().equals("mac")) {
			if (!chooseNetwork.equals("Production")) {
				Thread.sleep(10000);
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				//Thread.sleep(1000);
				clickButtonByXpath("Menu");
				Thread.sleep(1000);
				clickButtonByXpathTitleName("Settings");
				//clickButtonByXpath("OverflowSettings");
				//Thread.sleep(1000);
				//scrollDown("Sign Out", 40 );
				Thread.sleep(2000);
				driver.scrollToExact("Network Environment").click();
				//scrollDown("Network Environment", 100 );
				//Thread.sleep(2000);
				clickButtonByXpathTitleName(chooseNetwork);
				Thread.sleep(2000);
				
				driver.scrollToExact("px_i").click();
				//scrollDown("px_i", 130 );
				Thread.sleep(2000);
				sendTextbyID("AlertEditText", IndividualId);
				clickButton("AlertOK", "id", "xpath");
				Thread.sleep(2000);
				
				//driver.scrollToExact("px_u").click();
				//scrollDown("px_u", 130 );
				clickButton("px_u", "textAtt", "xpath");
				Thread.sleep(2000);
				sendTextbyID("AlertEditText", units);
				clickButton("AlertOK", "id", "xpath");
				Thread.sleep(2000);
				
				//driver.scrollToExact("px_p").click();
				//scrollDown("px_p", 130 );
				clickButton("px_p", "textAtt", "xpath");
				Thread.sleep(2000);
				sendTextbyID("AlertEditText", positions);
				clickButton("AlertOK", "id", "xpath");
				clickButtonByXpath("Back");
				Thread.sleep(2000);
				
				//driver.closeApp();
				//driver.launchApp();
				//Thread.sleep(2000);
				
				sendTextbyID("LoginUsername", userName);
				sendTextbyID("LoginPassword", "toolstester");
				//clickButtonByXpath("SignInButton");
				clickButtonByID("SignInButton");
				Thread.sleep(4000);
				waitForTextToDisappearID("SyncText", 500 );
				Thread.sleep(2000);

			}
		}
		if (getRunningOS().equals("mac")) {
			userName = "paigekrebs";
			chooseNetwork = "Proxy";
			if (!chooseNetwork.equals("Production")) {
				Thread.sleep(1000);
				clickButtonByXpath("TopHelp");
				
				
				if (checkElementExistsByXpath("DeveloperSettings") == true) {
					clickButtonByXpath("DeveloperSettings");
				} else {
					//New way to enable dev settings
					for (int x = 1; x <= 5; x++ ) {
						clickButtonByXpath("EnableDevSettings");
					}
				}

				
				clickButtonByXpathTitleNameContains("Environment");
				
				
				clickButtonByXpath(chooseNetwork);
				
				clickButtonByXpath("TopDeveloper");
				//Thread.sleep(2000);
				
				//Set the ID
				clickButtonByXpath("Id");
				sendTextbyXpath("HeaderAlertTextId", IndividualId );
				clickButtonByXpath("HeaderOK");
				
				//Set the Positions
				clickButtonByXpath("Units");
				sendTextbyXpath("HeaderAlertTextUnits", units );
				clickButtonByXpath("HeaderOK");
				
				//Set the Positions
				clickButtonByXpath("Positions");
				sendTextbyXpath("HeaderAlertTextPositions", positions );
				clickButtonByXpath("HeaderOK");
				

				
				clickButtonByXpath("TopHelp");
				//Thread.sleep(4000);
				clickButtonByXpath("TopSignIn");
				
				//sendTextbyXpath("LoginUsername", "LDSTools14");
				//sendTextbyXpath("LoginPassword", "toolstester");
				sendTextbyXpath2("LoginUsername", "paigekrebs" );
				sendTextbyXpath2("LoginPassword", "sweets2005");
				
				//Thread.sleep(1000);
				clickButtonByXpath("DoneButton");
				//Thread.sleep(1000);
				//clickButtonByXpath("SignInButton");
				Thread.sleep(4000);
				
				
				unitsToSync();
				
				waitForTextToDisappear("DownloadingSync", 500 );
				Thread.sleep(8000);
			}
		}
	}

	
	/** pinPage(String digit1, String digit2, String digit3, String digit4, Boolean nonLeaderPin )
	 * Enter a pin 
	 * 
	 * @param digit1 - pin 0-9
	 * @param digit2
	 * @param digit3
	 * @param digit4 
	 * @param nonLeaderPin - false - only for non-leader will skip the pin entry , true - enter pin
	 * @throws Exception
	 */
	private void pinPage(String digit1, String digit2, String digit3, String digit4, Boolean nonLeaderPin ) throws Exception {
		int myCheck = 0;
		String myAlertText;
		boolean elementCheck;
		
		//Check to see if we are getting a warning
		if (checkElementReturn("AlertMessage", "id", "xpath").equals(true)) {
			myCheck = checkTextReturn("AlertMessage", "Warning", "id", "xpath");
		}
		//Check to see if we are getting a warning
		if (checkElementExistsByXpath("AlertCalendarMessage").equals(true)) {
			System.out.println("Calendar Message!");
			clickButton("AlertOK", "id", "xpath");
			Thread.sleep(2000);
		}
		
		

		if (myCheck == 1) {
			//myAlertText = getTextXpath("AlertMessageView");
			myAlertText = getText("AlertMessageView", "id", "xpath");
			System.out.println("Found Warning: " + myAlertText);
			
			//Make sure we are not getting the Action and Interview warning
			Assert.assertFalse(myAlertText.contains("Action and Interview"));
			
			clickButtonByXpath("OK");
			Thread.sleep(5000);
		}
		
		
		//If this is a non-leader account the PIN message will be different
		//myCheck = checkTextByXpathReturn("AlertMessage", "Please create a PIN to protect sensitive data available to leaders.");
		myCheck = alertCheck();
		if ((myCheck == 1) || (nonLeaderPin)){
			if (myCheck == 1) {
				clickButton("AlertOK", "id", "xpath");
			} else {
				elementCheck = checkElementExistsByXpath("Yes");
				if (elementCheck == true) {
					clickButton("Yes", "id", "xpath");
				} else {
					clickButton("AlertOK", "id", "xpath");
				}
				Thread.sleep(1000);
			}

			Thread.sleep(2000);
			
			//Check for a warning
			elementCheck = checkElementReturn("AlertMessage", "id", "xpath");
			if (elementCheck == true) {
				//clickButtonByXpath("OK");
				clickButton("AlertOK", "id", "xpath");
			}

			//checkTextByXpath("PinTitle", "Choose your PIN");
			clickButton("PinKey" + digit1, "id", "xpath");
			clickButton("PinKey" + digit2, "id", "xpath");
			clickButton("PinKey" + digit3, "id", "xpath");
			clickButton("PinKey" + digit4, "id", "xpath");
			
			//checkTextByXpath("PinTitle", "Confirm PIN");
			clickButton("PinKey" + digit1, "id", "xpath");
			clickButton("PinKey" + digit2, "id", "xpath");
			clickButton("PinKey" + digit3, "id", "xpath");
			clickButton("PinKey" + digit4, "id", "xpath");

		} else {
			clickButton("AlertNotNow", "id", "xpath");
		}
		Thread.sleep(2000);

	}
	
	private int alertCheck() {
		int myCheck = 0;
		if (checkElementReturn("AlertMessage", "id", "xpath").equals(true)) {
			myCheck = checkTextReturn("AlertMessage", "Please create a PIN to protect sensitive data available to leaders.", "id", "xpath");
			if (myCheck == 0 ) {
				myCheck = checkTextReturn("AlertMessage", "Passcode Required", "id", "xpath");
			}
		}

		return myCheck;
	}
	
	private int alertCheckInvalidInput() {
		int myCheck = 0;
		myCheck = checkTextContainsReturn("AlertMessageMember", "Save failed", "id", "xpath");
		if (myCheck == 0 ) {
			myCheck = checkTextContainsReturn("AlertMessageMember", "Invalid", "id", "xpath");
		}
		
		return myCheck;
	}
	
	
	private void invalidEmailCheck() throws Exception{
		int alertCheck;
		Thread.sleep(2000);
		if (getRunningOS().equals("mac")) {
			clickButton("MenuSave", "id", "xpath");
			alertCheck = alertCheckInvalidInput();
			if (alertCheck == 1 ) {
				clickButtonByXpath("AlertOK");
			}
		} else {
			Assert.assertTrue(checkElementTextViewReturnContains("valid email address"));
		}
	}
	
	

	
	/**  checkDirectoryUser(boolean memberShipInfo, boolean fullName, boolean birthDate, boolean recordNumber, boolean ordinances )
	 * Check the directory user "Aaron, Jane"
	 * 
	 * All params are boolean - true item is displayed 
	 * @param memberShipInfo
	 * @param fullName
	 * @param birthDate
	 * @param recordNumber
	 * @param ordinances
	 * @throws Exception
	 */
	private void checkDirectoryUser(boolean memberShipInfo, boolean fullName, boolean birthDate, boolean recordNumber, boolean ordinances, boolean otherInfo ) throws Exception {
		//boolean testForElement;
		//List<String> checkReportText = new ArrayList<String>();
		String pageSource;
		//Search for logged in user
		searchForUser("Aaron, Jane");
		
		if (getRunningOS().equals("mac")) {
			if (fullName == false ) {
				clickButtonByXpathTitleName("Jane Aaron");
			} else {
				clickButtonByXpathTitleName("Jane Aaron (55)");
			}
			
			//clickButton("Jane Aaron", "text", "text");
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			//Assert.assertTrue(checkNoCaseList("Aaron, Jane", pageSource, "Contains"));
		} else {
			//clickButtonByXpathTitleName("Aaron, Jane");
			pageSource = androidGetMemberInfo();
			//System.out.println(pageSource);
			Assert.assertTrue(checkNoCaseList("Aaron, Jane", pageSource, "Contains"));
		}


		//Assert.assertTrue(checkNoCaseList("Jane Aaron", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource, "Equals"));		
		//Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("555-555-5555", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("555-555-1234", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("no-reply@ldschurch.org", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource, "Contains"));
		


		
		
		
		

		//Leadership Should be able to see this information
		//Membership Information
		if (memberShipInfo == true ) {
			Assert.assertTrue(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource, "Contains"));
		} else {
			if (getRunningOS().equals("mac")) {
				Assert.assertFalse(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource, "Contains"));
			}else {
				Assert.assertFalse(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource, "Contains"));
			}
			
		}
		
		//Full Name
		if (fullName == true){
			Assert.assertTrue(checkNoCaseList("AFPMisc, Member2", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("FULL NAME", pageSource, "Contains"));
			
			
			//Callings and Classes - New in 3.0.0
			Assert.assertTrue(checkNoCaseList("Relief Society Pianist", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Organization - Music", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Class Assignments", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Gospel Doctrine", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Relief Society", pageSource, "Contains"));

			//Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource, "Contains"));
			//Assert.assertTrue(checkNoCaseList("January 17, 2016", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Sustained", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Set Apart", pageSource, "Contains"));

			
		} else {
			Assert.assertFalse(checkNoCaseList("AFPMisc, Member2", pageSource, "Equals"));
			Assert.assertFalse(checkNoCaseList("FULL NAME", pageSource, "Equals"));
			
			//Callings and Classes - New in 3.0.0
			//Assert.assertFalse(checkNoCaseList("Relief Society Pianist", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Organization - Music", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Class Assignments", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Gospel Doctrine", pageSource, "Contains"));
			//Assert.assertFalse(checkNoCaseList("Relief Society", pageSource, "Contains"));
			
			//Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource, "Contains"));
			//Assert.assertTrue(checkNoCaseList("January 17, 2016", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Sustained", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Set Apart", pageSource, "Contains"));
			
		}
		
		

		
		//Birth Date
		if (birthDate == true){
			Assert.assertTrue(checkNoCaseList("November 11, 1960", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Birth Date", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("55", pageSource, "Contains"));
		} else {
			Assert.assertFalse(checkNoCaseList("November 11, 1960", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Birth Date", pageSource, "Contains"));
			//Assert.assertFalse(checkNoCaseList("55", pageSource, "Contains"));
		}

		
		//Record Number
		if (recordNumber == true ){
			Assert.assertTrue(checkNoCaseList("888-0028-4326", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("RECORD NUMBER", pageSource, "Contains"));
		} else {
			Assert.assertFalse(checkNoCaseList("888-0028-4326", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("RECORD NUMBER", pageSource, "Contains"));
		}

		//Check Ordinances
		if (ordinances == true ){
			Assert.assertTrue(checkNoCaseList("Baptism", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("Confirmation", pageSource, "Contains"));
			Assert.assertTrue(checkNoCaseList("November 11, 1970", pageSource, "Contains"));

		} else {
			Assert.assertFalse(checkNoCaseList("Ordinances", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Baptism", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Confirmation", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("November 11, 1970", pageSource, "Contains"));
		}
		

		//Check Other Information
		if (otherInfo == true ) {
			//clickButtonByXpathTitleName("Other Information");
			//pageSource = getSourceOfPage();
			if (ordinances == true) {
				Assert.assertTrue(checkNoCaseList("Gender", pageSource, "Contains"));
				Assert.assertTrue(checkNoCaseList("Female", pageSource, "Contains"));
				Assert.assertTrue(checkNoCaseList("Birth Country", pageSource, "Contains"));
				Assert.assertTrue(checkNoCaseList("United States", pageSource, "Contains"));
			} else {
				Assert.assertTrue(checkNoCaseList("Gender", pageSource, "Contains"));
				Assert.assertTrue(checkNoCaseList("Female", pageSource, "Contains"));
				Assert.assertFalse(checkNoCaseList("Birth Country", pageSource, "Contains"));
				Assert.assertFalse(checkNoCaseList("United States", pageSource, "Contains"));
			}

			//pressBackKey();
		}

		
		if (getRunningOS().equals("mac")) {
			pressBackKey();
			//pressBackToRoot();
			//clickButtonByXpath("SearchCollapse");
		} else {
			pressBackToRoot();
			clickButtonByXpath("SearchCollapse");
			clickButton("CollapseButton", "xpath", "xpath");
		}

		
		
		
	}
	
	/** checkDrawerItems (boolean leader)
	 * Check the drawer items - non leaders should not have the reports item
	 * 
	 * @param leader
	 * @throws Exception
	 */
	private void checkDrawerItems (boolean leader) throws Exception {
		if (getRunningOS().equals("mac")){
			Assert.assertTrue(checkElementNameReturn("Directory"));
			Assert.assertTrue(checkElementNameReturn("Calendar"));
			
			if (leader == true) {
				Assert.assertTrue(checkElementNameReturn("Reports"));
			} else {
				Assert.assertFalse(checkElementNameReturn("Reports"));
			}
			Assert.assertTrue(checkElementNameReturn("Organizations"));
			clickButtonByXpath("DrawerMore");
			
			//Check to see if the sync page is displayed
			if (checkElementNameReturn("Sync Now") == true) {
				pressBackKey();
			}
			
			
			Assert.assertTrue(checkElementNameReturn("Missionaries"));
			Assert.assertTrue(checkElementNameReturn("Lists"));
			Assert.assertTrue(checkElementNameReturn("Meetinghouses"));
			Assert.assertTrue(checkElementNameReturn("Sync"));
			Assert.assertTrue(checkElementNameReturn("Settings"));
			Assert.assertTrue(checkElementNameReturn("Help"));
			clickButtonByXpath("DrawerDirectory");
		} else {
			//Check the Drawer items
			clickButtonByXpath("Drawer");
			if (checkElementReturn("Later", "textAtt", "value") == true ) {
				clickButtonByXpathTitleName("Later");
			}
			Assert.assertTrue(checkElementReturn("Directory", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Organizations", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Missionaries", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Lists", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Calendar", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Meetinghouses", "textAtt", "value"));
			if (leader == true) {
				Assert.assertTrue(checkElementReturn("Reports", "textAtt", "value"));
			} else {
				Assert.assertFalse(checkElementReturn("Reports", "textAtt", "value"));
			}
			clickButtonByXpath("DrawerDirectory");
		}
	
	}
	
	/** checkCallings()
	 * Check the callings all users should have access to this
	 * 
	 * @throws Exception
	 */
	private void checkCallings() throws Exception {
		//Callings
		//List<String> checkReportText = new ArrayList<String>();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerCallings");
		String pageSource;
		//LDSWeb myWeb = new LDSWeb();
		
		openOrgnizations();
		
		Thread.sleep(1000);
		if (getRunningOS().equals("mac")) {
			pageSource = getSourceOfPage();
		}else {
			pageSource = androidGetInfoScroll();
		}
		//System.out.println("Page Source: " + pageSource);
				
		Assert.assertTrue(checkNoCaseList("Bishopric", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("High Priests Group", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Elders Quorum", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Relief Society", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Young Men", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Young Women", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Sunday School", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Primary", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Ward Missionaries", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Other Callings", pageSource, "Equals"));

		myWeb.openPageLogIn("https://uat.lds.org/mls/mbr/?lang=eng", myUserName, myPassword);
	
		getBishopricInfo();

		getHighPriestsGroupInfo();
		
		getEldersQuorum();
		
		getReliefSociety();
		
		getYoungMenInfo();
		
		getYoungWomenInfo();
		
		getSundaySchoolInfo();
		
		getPrimaryInfo();

		
		/*
		pageSource = getSourceOfPage();

		Assert.assertTrue(checkNoCaseList("Bishop", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Ami, Samu", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Bishopric Second Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Tutunoa, Ualesi Junior, Jr", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Ward Executive Secretary", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Albert", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Ward Assistant Clerk", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Lafaele", pageSource, "Contains"));
		
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
				
		//High Priests Group
		clickButtonByXpathTitleName("High Priests Group");
		clickButtonByXpathTitleName("High Priests Group Leadership");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("High Priests Group Leader", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Faamoe, Panapa Filifili", pageSource, "Contains"));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		
		//Elders Quorum
		clickButtonByXpathTitleName("Elders Quorum");
		clickButtonByXpathTitleName("Elders Quorum Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Elders Quorum President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Peaulele", pageSource, "Contains"));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
				
		//Relief Society
		clickButtonByXpathTitleName("Relief Society");
		Thread.sleep(1000);
		clickButtonByXpathTitleName("Relief Society Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Relief Society President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Endemann, Lole", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Relief Society First Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Faamoetauloa, Fiasili", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Relief Society Second Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Puleiai, Siva", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Relief Society Secretary", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Patiole, Luafa", pageSource, "Contains"));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		Thread.sleep(1000);
		//Young Men
		clickButtonByXpathTitleName("Young Men");
		clickButtonByXpathTitleName("Young Men Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Men President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Lafaele", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Young Men First Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Poai, Mikaele", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Young Men Second Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Faamoetauloa Panapa Jr, Panapa Jnr", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Young Men Secretary", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Venasio Fainuu, Fogavai", pageSource, "Contains"));
		pressBackKey();
		//Thread.sleep(1000);
		clickButtonByXpathTitleName("Priests Quorum");
		Thread.sleep(1000);
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Priests Quorum Presidency");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Priests Quorum First Assistant", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Tulia, Tiueni", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Priests Quorum Second Assistant", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Tumua", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Priests Quorum Secretary", pageSource, "Contains"));
		if (getRunningOS().equals("mac")) {
			pressBackKey();
		}
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		//Thread.sleep(2000);
		//pressBackKey();
	

		
		//Young Women
		clickButtonByXpathTitleName("Young Women");
		clickButtonByXpathTitleName("Young Women Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Women President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Tutunoa, Lusi", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Young Women First Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Etevise", pageSource, "Contains"));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		//Sunday School
		clickButtonByXpathTitleName("Sunday School");
		clickButtonByXpathTitleName("Sunday School Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Sunday School President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Lealaiauloto, Uana Iosefa Sao", pageSource, "Contains"));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
				
		//Primary
		clickButtonByXpathTitleName("Primary");
		clickButtonByXpathTitleName("Primary Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Primary President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Faamoe, Talalelagi", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Primary First Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Fepuleai, Malele Seuamuli", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Primary Second Counselor", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Tulia, Faagalo", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Primary Secretary", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Samu, Luisa", pageSource, "Equals"));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		
		*/

		



		

		//Ward Missionaries
		if (getRunningOS().equals("mac")){
			clickButtonByXpathTitleName("Ward Missionaries");
		} else {
			//scrollDownTEST(100);
			driver.scrollToExact("Ward Missionaries");
			clickButtonByXpathTitleName("Ward Missionaries");
		}
		
		Thread.sleep(2000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Ward Mission Leader", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Lafaele", pageSource, "Contains"));
		pressBackKey();
		
		
		//Other Callings
		Thread.sleep(1000);
		if (getRunningOS().equals("mac")){
			clickButtonByXpathTitleName("Other Callings");
		} else {
			//scrollDownTEST(100);
			driver.scrollToExact("Other Callings");
			clickButtonByXpathTitleName("Other Callings");
		}
		
		Thread.sleep(1000);
		clickButtonByXpathTitleName("Young Single Adult");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Single Adult Leader", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Solomona, Solomona", pageSource, "Contains"));
		pressBackKey();
		clickButtonByXpathTitleName("Music");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Music Adviser", pageSource, "Contains"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
	}
	
	/** checkMissionary()
	 * Check the missionary drawer items
	 * 
	 * @throws Exception
	 */
	private void checkMissionary() throws Exception {
		String pageSource;
		openMissionary();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			pageSource = getSourceOfPage();
		} else {
			pageSource = androidGetMissionariesInfo();
		}
		
		//Assert.assertTrue(checkNoCaseList("Elder Chad Faleali'i Samaseia", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Elder Faauila Ekuasi", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Elder Lopeti Brown", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Elder Conlan Schuyler Galvez", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Kitara, Lafaele", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Elder Trent Barrett Powelson", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Elder Tama Kiliona Sitivi", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Idaho Pocatello", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Elder Olo Young Yen Junior", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Australia Perth", pageSource, "Contains"));

	}
	
	private void checkCalendar() throws Exception {
		openCalendar();
		
		String pageSource;

		if (getRunningOS().equals("mac")) {
			//clickButton("6:00 PM", "text", "name");
			//driver.scrollTo("Worldwide Devotional for Young Adults");
			driver.swipe(50, 500, 50, 100, 750);
			//driver.scrollToExact("Worldwide Devotional for Young Adults");
			clickButton("Worldwide Devotional for Young Adults", "text", "nameContains");
		} else {
			driver.scrollTo("Worldwide");
			clickButton("Worldwide", "text", "text");
		}

		Thread.sleep(2000);
		pageSource = getSourceOfPage();

		Assert.assertTrue(checkNoCaseList("Worldwide Devotional for Young Adults", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Sunday, September 11", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("6:00", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("7:00", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("View at a local", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Learn more at", pageSource, "Contains"));
		
		pressBackKey();
	}
	
	
	/** checkReports()
	 * Check reports for leaders
	 * 
	 * @throws Exception
	 */
	private void checkReports(boolean newUnit, boolean bishop) throws Exception {
		//Reports
		//List<String> checkReportText = new ArrayList<String>();
		String pageSource = null;
		//boolean myTestbool;
		
		openReports();
		Thread.sleep(2000);
		
		if (getRunningOS().equals("mac")) {
			pageSource = getSourceOfPage();
		} else {
			pageSource = pageSource + getSourceOfPage();
			scrollDownTEST(800);
			pageSource = pageSource + getSourceOfPage();
		}


		Assert.assertTrue(checkNoCaseList("Birthday List", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Members Moved In", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Members Moved Out", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Members with Callings", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Missionary Progress Record", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("New Members", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Unit Statistics", pageSource, "Equals"));
		Assert.assertFalse(checkNoCaseList("Death Star Reports", pageSource, "Equals"));

		
		
		
		//Check the members moved out report
		//Should have a ( ) with the age by the birth date
		clickButtonByXpathTitleName("Members Moved Out");
		
		Thread.sleep(1000);
		pageSource = getSourceOfPage();

		Assert.assertTrue(checkNoCaseList("Vui, Line", pageSource, "Contains"));

		//The new unit is only available for bishop
		if (bishop == true){
			Assert.assertTrue(checkNoCaseList("New Unit", pageSource, "Contains"));
		} else {
			Assert.assertFalse(checkNoCaseList("New Unit", pageSource, "Contains"));
		}
		Assert.assertFalse(checkNoCaseList("Solo, Han", pageSource, "Equals"));
		pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members Moved In
		//Thread.sleep(1000);
		clickButtonByXpathTitleName("Members Moved In");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Endemann", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Eddie", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Skywalker, Luke", pageSource, "Equals"));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members with Callings
		clickButtonByXpathTitleName("Members with Callings");
		Thread.sleep(2000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Aaron, Jane", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Relief Society", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("11 months", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Skywalker, Anakin", pageSource, "Equals"));
	
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Organization");
		} else {
			clickButtonByXpathTitleName("Organization");
		}
		pageSource = getSourceOfPage();
		//Assert.assertTrue(checkNoCaseList("Ward Clerk", pageSource, "Equals"));
		//Assert.assertTrue(checkNoCaseList("Kitara, Lafaele (3 months)", pageSource, "Equals"));
		Assert.assertTrue(checkNoCaseList("Bishop", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Kitara, Sam", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("2 years, 4 months", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Kenobi, Obi-Wan", pageSource, "Equals"));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Duration");
		} else {
			clickButtonByXpathTitleName("Duration");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Women President", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Tutunoa, Lusi", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Amidala, Padme", pageSource, "Contains"));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Not Set Apart");
		} else {
			clickButtonByXpathTitleName("Not Set Apart");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Ward Executive Secretary", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("6 months", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("Mene, Taavili Maalona", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("P0, C3", pageSource, "Contains"));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
		
		//Members without Callings
		clickButtonByXpathTitleName("Members without Callings");
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("AFPEighteen, Member", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("AFPEleven, Member", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("D2, R2", pageSource, "Contains"));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Male");
		} else {
			clickButtonByXpathTitleName("Male");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("AFPEleven, Member", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("AFPFifteen, Member", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Binks, Jarjar", pageSource, "Contains"));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Female");
		} else {
			clickButtonByXpathTitleName("Female");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("AFPEighteen, Member", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("AFPFive, Wife", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Organa, Leia", pageSource, "Contains"));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
		if (myUserName.equalsIgnoreCase("LDSTools20") || (myUserName.equalsIgnoreCase("LDSTools25") || (myUserName.equalsIgnoreCase("LDSTools30")))) {
			Assert.assertFalse(checkNoCaseList("Missionary Progress Record", pageSource, "Contains"));
		} else {
			checkMissionaryProgressRecord();
			checkMissionaryProgressRecordVisits();
			checkMissionaryProgressRecordSacMeeting();
		}
		

		
		
		//New Members
		clickButtonByXpathTitleName("New Members");
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Lilotoe, Tapatasi", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("17", pageSource, "Contains"));
		Assert.assertTrue(checkNoCaseList("M", pageSource, "Contains"));
		if (newUnit == true){
			Assert.assertTrue(checkNoCaseList("October 15, 2015", pageSource, "Contains"));
		} else {
			Assert.assertFalse(checkNoCaseList("October 15, 2015", pageSource, "Contains"));
		}
		
		Assert.assertTrue(checkNoCaseList("Member", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Hutt, Jabba", pageSource, "Contains"));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
		if (newUnit == true ) {
			//Temple Recommend Status
			clickButtonByXpathTitleName("Temple Recommend Status");
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("AFPMisc, Member15", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Ahsoka, Tano", pageSource, "Contains"));
			//Assert.assertTrue(checkElementTextViewReturn("Expired"));
			
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Active");
			} else {
				//clickButtonByXpathTitleName("Active");
				clickButtonByXpath("ActiveMenu");
			}
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Ami, Lealofi", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Maul, Darth", pageSource, "Contains"));
			//Assert.assertTrue(checkElementTextViewReturn("Ami, Samu"));
			//Assert.assertTrue(checkElementTextViewReturn("Jul 2016"));
			//Assert.assertFalse(checkElementTextViewReturn("Maul, Darth"));
			

			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Expiring");
			} else {
				clickButtonByXpathTitleName("Expiring");
			}
			pageSource = getSourceOfPage();
			Assert.assertFalse(checkNoCaseList("Windu, Mace", pageSource, "Contains"));
			//Assert.assertFalse(checkElementTextViewReturn("Sitivi, Tama Kiliona"));
			//Assert.assertFalse(checkElementTextViewReturn("Windu, Mace"));

			
			
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Expired");
			} else {
				clickButtonByXpathTitleName("Expired");
			}
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Mene, Sau", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Jinn, Qui-Gon", pageSource, "Contains"));
			//Assert.assertTrue(checkElementTextViewReturn("Tutunoa, Lusi"));
			//Assert.assertFalse(checkElementTextViewReturn("Jinn, Qui-Gon"));
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Other");
			} else {
				clickButtonByXpathTitleName("Other");
			}
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Lavea, Lonise", pageSource, "Contains"));
			Assert.assertFalse(checkNoCaseList("Calrissian, Lando", pageSource, "Contains"));
			//Assert.assertTrue(checkElementTextViewReturn("Mene, Matagalu"));
			//Assert.assertFalse(checkElementTextViewReturn("Calrissian, Lando"));
			Thread.sleep(1000);
			pressBackKey();
			Thread.sleep(1000);
		} else {
			Assert.assertFalse(checkElementReturn("Temple Recommend Status", "textAtt", "value"));
		}

		
		//Unit Statistics
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Unit Statistics");
		} else {
			//scrollDownTEST(100);
			driver.scrollToExact("Unit Statistics");
			clickButtonByXpathTitleName("Unit Statistics");
		}
		
		Thread.sleep(1000);
		//clickButtonByXpath("AlertOK");
		checkForAlertOK();
		Thread.sleep(1000);
		//Assert.assertTrue(checkElementTextViewReturnContains("611"));
		Assert.assertTrue(checkElementTextViewReturnContains("17"));
		//Assert.assertTrue(checkElementTextViewReturnContains("51"));
		Assert.assertFalse(checkElementTextViewReturnContains("8675309"));
		pressBackKey();
	}
	
	private void checkMissionaryProgressRecord() throws Exception {
		//*************************************************************************************
		//********************* Missionary Progress Record ************************************
		//*************************************************************************************
		String pageSource;
		List<String> myList = new ArrayList<String>();
		List<String> androidList = new ArrayList<String>();
		
		openReports();
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Missionary Progress Record");
		pageSource = getSourceOfPage();
		//Assert.assertTrue(checkNoCaseList("Potential Investigator", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Malcolm Reynolds", pageSource, "Contains"));
		
		myWeb.WPRopenPageLogIn("https://missionary-stage.lds.org/ward-missionary-tools", "ab067", "password0");
		myList = myWeb.WPRgetUsers("none", false);	
		myList = swapLastName(myList);
	
		
		compareWebData(myList, androidList, false);

		//Investigators with Baptism Date
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpInvestigatorsWithBaptismDate", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "Investigators with Baptism Date", "id", "xpath");
		myList = myWeb.WPRgetUsers("Investigators with Baptism Date", false);
		myList = swapLastName(myList);
		
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		//Assert.assertTrue(checkNoCaseList("Baptism Goal", pageSource, "Contains"));
		//Assert.assertFalse(checkNoCaseList("Skywalker", pageSource, "Contains"));
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//Progressing Investigators
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpProgressingInvestigators", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "Progressing Investigators", "id", "xpath");
		myList = myWeb.WPRgetUsers("Progressing Investigators", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		//Assert.assertTrue(checkNoCaseList("Solo", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Han", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Baptism Goal", pageSource, "Contains"));
		Assert.assertFalse(checkNoCaseList("Skywalker", pageSource, "Contains"));
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//New Investigators
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpNewInvestigators", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "New Investigators", "id", "xpath");
		myList = myWeb.WPRgetUsers("New Investigators", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		//Assert.assertTrue(checkNoCaseList("Skywalker", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("Luke", pageSource, "Contains"));
		//Assert.assertTrue(checkNoCaseList("New Investigator", pageSource, "Contains"));
		//Assert.assertFalse(checkNoCaseList("James", pageSource, "Contains"));
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//Other Investigators
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpOtherInvestigators", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "Other Investigators", "id", "xpath");
		myList = myWeb.WPRgetUsers("Other Investigators", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//Potential Investigators
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpPotentialInvestigators", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "Potential Investigators", "id", "xpath");
		myList = myWeb.WPRgetUsers("Potential Investigators", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//Recent Converts
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpRecentConverts", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "Recent Converts", "id", "xpath");
		myList = myWeb.WPRgetUsers("Recent Converts", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//Members Being Taught
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpMembersBeingTaught", "id", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		checkText("mpFilterText", "Members Being Taught", "id", "xpath");
		myList = myWeb.WPRgetUsers("Members Being Taught", true);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		
		
		
	
	

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
		//*************************************************************************************
		//*************************************************************************************
		//*************************************************************************************
	}
	
	
	private void checkMissionaryProgressRecordVisits() throws Exception {
		
		String pageSource;
		List<String> myList = new ArrayList<String>();
		List<String> androidList = new ArrayList<String>();

		openReports();
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Missionary Progress Record");
		pageSource = getSourceOfPage();
		
		myWeb.WPRopenPageLogIn("https://missionary-stage.lds.org/ward-missionary-tools", "ab067", "password0");
		
		//******************
		//Visits - Last Week
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpReceivedAVisit", "id", "xpath");
		clickButton("mpLastWeek", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetUsersVisits("WPRLastWeek", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//******************
		//Visits - Last 2 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpReceivedAVisit", "id", "xpath");
		clickButton("mpLast2Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetUsersVisits("WPRLast2Weeks", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//******************
		//Visits - Last 3 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpReceivedAVisit", "id", "xpath");
		clickButton("mpLast3Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetUsersVisits("WPRLast3Weeks", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		
		//******************
		//Visits - Last 4 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpReceivedAVisit", "id", "xpath");
		clickButton("mpLast4Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetUsersVisits("WPRLast4Weeks", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");

		//******************
		//Visits - Last 5 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		clickButton("mpReceivedAVisit", "id", "xpath");
		clickButton("mpLast5Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetUsersVisits("WPRLast5Weeks", true);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
	}
	
	private void scrollToSacMeetingAttendance() throws Exception {
		if (!getRunningOS().equals("mac")) {
			driver.scrollTo("Attended Sacrament");
		}
	}
	
	
	private void checkMissionaryProgressRecordSacMeeting() throws Exception {
		String pageSource;
		List<String> myList = new ArrayList<String>();
		List<String> androidList = new ArrayList<String>();

		openReports();
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Missionary Progress Record");
		//pageSource = getSourceOfPage();
		
		myWeb.WPRopenPageLogIn("https://missionary-stage.lds.org/ward-missionary-tools", "ab067", "password0");
		
		//******************
		//Sacrament Meeting Attendance - Last Week
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		scrollToSacMeetingAttendance();
		clickButton("mpAttendedSacrament", "id", "xpath");
		clickButton("mpLastWeek", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetSacMeeting("WPRSacLastSunday", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		//pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//******************
		//Sacrament Meeting Attendance - Last 2 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		scrollToSacMeetingAttendance();
		clickButton("mpAttendedSacrament", "id", "xpath");
		clickButton("mpLast2Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetSacMeeting("WPRSacLast2Weeks", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		//pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		//******************
		//Sacrament Meeting Attendance - Last 3 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		scrollToSacMeetingAttendance();
		clickButton("mpAttendedSacrament", "id", "xpath");
		clickButton("mpLast3Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetSacMeeting("WPRSacLast3Weeks", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		//pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		
		//******************
		//Sacrament Meeting Attendance - Last 4 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		scrollToSacMeetingAttendance();
		clickButton("mpAttendedSacrament", "id", "xpath");
		clickButton("mpLast4Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetSacMeeting("WPRSacLast4Weeks", false);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		//pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");

		//******************
		//Sacrament Meeting Attendance - Last 5 weeks
		//******************
		clickButton("MissionaryProgressFilter", "id", "xpath");
		scrollToSacMeetingAttendance();
		clickButton("mpAttendedSacrament", "id", "xpath");
		clickButton("mpLast5Weeks", "xpath", "xpath");
		if (!getRunningOS().equals("mac")) {
			clickButton("mpMenuSave", "id", "xpath");
			clickButton("mpExpandFilter", "id", "xpath");
		} else {
			pressBackKey();
		}
		//checkText("mpFilterText", " Visits     Last Week", "id", "xpath");
		myList = myWeb.WPRgetSacMeeting("WPRSacLast5Weeks", true);
		myList = swapLastName(myList);
		compareWebData(myList, androidList, false);
		//pageSource = getSourceOfPage();
		clickButton("mpRemoveFilterButton", "id", "xpath");
		
		
		
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
	}
	

	private void checkHTVTBasic(String userCalling ) throws Exception {
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		String pageSource;
		openReports();
		Thread.sleep(2000);
		
		pageSource = getSourceOfPage();
		
		if (getRunningOS().equals("mac")) {
			pageSource = getSourceOfPage();
		} else {
			pageSource = getSourceOfPage();
			scrollDownTEST(100);
			pageSource = pageSource + getSourceOfPage();
		}

		if (userCalling.equals("Bishopric")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		if ((userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		if (userCalling.equals("Relief Society Pres")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		if (userCalling.equals("Ward Council")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		
		if ((userCalling.equals("Bishopric")) || (userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			
			clickButtonByXpathTitleName("Home Teaching");
			if (getRunningOS().equals("mac")) {
				clickButton("HPGHouseHoldsNotVisisted", "xpath", "xpath");
			} else {
				clickButtonByXpathTitleName("Households Not Visited");
			}
			

			getHTVTReport("High Priests Group", "HouseholdsNotVisited", userCalling);
			
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}

			Thread.sleep(1000);
			pressBackKey();

			
			
			if (getRunningOS().equals("mac")) {
				clickButton("HPGUnassignedHouseholds", "xpath", "xpath");
			} else {
				clickButtonByXpathTitleName("Unassigned Households");
			}
			Thread.sleep(2000);
			getHTVTReport("High Priests Group", "NotAssignedHomeTeachers", userCalling);
			
			pressBackKey();
			Thread.sleep(1000);
			pressBackKey();
			
			
			//Elders Quorum Households Not Visited
			clickButtonByXpathTitleName("Home Teaching");
			if (getRunningOS().equals("mac")) {
				clickButton("EldersHouseholdsNotVisited", "xpath", "xpath");
			} else {
				clickLastTextViewRoboReturnContains("Households Not Visited");
			}

			getHTVTReport("Elders Quorum" , "HouseholdsNotVisited", userCalling);
			
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			Thread.sleep(1000);
			pressBackKey();

			
			if (getRunningOS().equals("mac")) {
				clickButton("EldersUnassignedHouseholds", "xpath", "xpath");
			} else {
				Thread.sleep(1000);
				scrollDownTEST(100);
				Thread.sleep(1000);
				clickLastTextViewRoboReturnContains("Unassigned Households");
			}
			getHTVTReport("Elders Quorum", "NotAssignedHomeTeachers", userCalling);
			
			pressBackKey();
			
			//Need Potential Home Teacher Report 

			pressBackKey();
		}
		
		if ((userCalling.equals("Relief Society Pres")) || (userCalling.equals("Bishopric"))) {
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("Visiting Teaching");
			} else {
				//scrollDownTEST(100);
				driver.scrollToExact("Visiting Teaching");
				clickButtonByXpathTitleName("Visiting Teaching");
			}
			Thread.sleep(2000);
			//Visiting Teaching
			clickButtonByXpathTitleName("Sisters Not Contacted");
			//if (!getRunningOS().equals("mac")) {
			//	clickButtonByXpath("3Months");
			//}
			getHTVTReport("Relief Society" , "HouseholdsNotVisited", userCalling);
			
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			Thread.sleep(1000);
			pressBackKey();			
			
			clickButtonByXpathTitleName("Unassigned Sisters");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member14", "textAtt", "value"));
			pressBackKey();
			
			clickButtonByXpathTitleName("Potential Visiting Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturnContains("AFPFourteen, Member"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member14"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member12"));
			} else {
				Assert.assertTrue(checkElementTextViewReturnContains("AFPFourteen, Member"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member14"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member12"));
			}

			pressBackKey();
			pressBackKey();

		}	
		
	}
	
	
	
	private void checkHTVTBasicOLD(String userCalling ) throws Exception {
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		String pageSource;
		openReports();
		Thread.sleep(2000);
		
		pageSource = getSourceOfPage();
		
		if (getRunningOS().equals("mac")) {
			pageSource = getSourceOfPage();
		} else {
			pageSource = getSourceOfPage();
			scrollDownTEST(100);
			pageSource = pageSource + getSourceOfPage();
		}

		if (userCalling.equals("Bishopric")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		if ((userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		if (userCalling.equals("Relief Society Pres")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		if (userCalling.equals("Ward Council")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource, "Equals"));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource, "Equals"));
		}
		
		
		if ((userCalling.equals("Bishopric")) || (userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			clickButtonByXpathTitleName("Home Teaching");
			//Thread.sleep(2000);
			
			//High Priests
			clickButtonByXpathTitleName("Households Not Visited");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("13 Months");
			}
			Assert.assertTrue(checkElementReturn("AFPMisc, Member17", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member4", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member7", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			

			clickButtonByXpath("6Months");
			Assert.assertTrue(checkElementReturn("AFPMisc, Member17", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member4", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member7", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}

			clickButtonByXpath("3Months");
			Assert.assertTrue(checkElementReturn("AFPMisc, Frank", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member1", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member13", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("1Month");
			Assert.assertTrue(checkElementReturn("AFPMisc, Frank", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member1", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member13", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			pressBackKey();
			
			clickButtonByXpathTitleName("Unassigned Households");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Frank", "textAtt", "value"));
			pressBackKey();
			
			clickButtonByXpathTitleName("Potential Home Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturnContains("Galuvao, Faafetai"));
				Assert.assertTrue(checkElementTextViewReturnContains("Faapili, Muipu"));
				Assert.assertTrue(checkElementTextViewReturnContains("Faamoetauloa, Ennie"));
			} else {
				Assert.assertTrue(checkElementReturn("Galuvao, Faafetai - 60", "textAtt", "value"));
				Assert.assertTrue(checkElementReturn("Faapili, Muipu - 33", "textAtt", "value"));
				Assert.assertTrue(checkElementReturn("Faamoetauloa, Ennie - 36", "textAtt", "value"));
			}
			pressBackKey();
			
			//Elders Quorum
			clickLastTextViewRoboReturnContains("Households Not Visited");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("13 Months");
			}
			Assert.assertTrue(checkElementReturn("Sa, Seti", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Seu, Malaga", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Lavea, Muaau Alavaa", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("6Months");
			Assert.assertTrue(checkElementReturn("Sa, Seti", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Faamoe, Ueni", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Lavea, Muaau Alavaa", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("3Months");
			Assert.assertTrue(checkElementReturn("AFPTen, Husband", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Faamoe, Ueni", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Lavea, Muaau Alavaa", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("1Month");
			Assert.assertTrue(checkElementReturn("Faamoe, Filifili", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPSix, Husband", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPTen, Husband", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			pressBackKey();
			
			if (getRunningOS().equals("mac")) {
				clickLastTextViewRoboReturnContains("Unassigned Households");
			} else {
				Thread.sleep(1000);
				scrollDownTEST(100);
				Thread.sleep(1000);
				clickLastTextViewRoboReturnContains("Unassigned Households");
			}
			
			//clickLastTextViewRoboReturnContains("Unassigned Households");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("Abel, Chad Dennis", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Faamoe, Filifili", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Faamoe, Ueni", "textAtt", "value"));
			pressBackKey();
			
			clickLastTextViewRoboReturnContains("Potential Home Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturnContains("AFPTen, Husband"));
				Assert.assertTrue(checkElementTextViewReturnContains("Ami, Samu Junior"));
				Assert.assertTrue(checkElementTextViewReturnContains("Faamoe, Filifili"));
			} else {
				Assert.assertTrue(checkElementReturn("AFPTen, Husband - 55", "textAtt", "value"));
				Assert.assertTrue(checkElementReturn("Ami, Samu Junior - 22", "textAtt", "value"));
				Assert.assertTrue(checkElementReturn("Faamoe, Filifili - 46", "textAtt", "value"));
			}

			pressBackKey();
			pressBackKey();
		}
		
		if ((userCalling.equals("Relief Society Pres")) || (userCalling.equals("Bishopric"))) {
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("Visiting Teaching");
			} else {
				//scrollDownTEST(100);
				driver.scrollToExact("Visiting Teaching");
				clickButtonByXpathTitleName("Visiting Teaching");
			}
			
			Thread.sleep(2000);

			//Visiting Teaching
			clickButtonByXpathTitleName("Sisters Not Contacted");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("13 Months");
			}
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member14", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("6Months");
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member14", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("3Months");
			Assert.assertTrue(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("1Month");
			Assert.assertTrue(checkElementReturn("AFPEighteen, Member", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			pressBackKey();
			
			clickButtonByXpathTitleName("Unassigned Sisters");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member14", "textAtt", "value"));
			pressBackKey();
			
			clickButtonByXpathTitleName("Potential Visiting Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturnContains("AFPEighteen, Member"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member14"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member12"));
			} else {
				Assert.assertTrue(checkElementTextViewReturnContains("AFPEighteen, Member"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member14"));
				Assert.assertTrue(checkElementTextViewReturnContains("AFPMisc, Member12"));
			}

			pressBackKey();
			pressBackKey();

		}
		
		
		
	}
	
	private void checkHTVTHouseholds(String userCalling ) throws Exception {
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		openReports();
		Thread.sleep(2000);
		
		
		
		if ((userCalling.equals("Bishopric")) || (userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			String myOS;
			clickButtonByXpathTitleName("Home Teaching");
			Thread.sleep(2000);
			
			//High Priests
			if(getRunningOS().equals("mac")) {
				clickButton("HPGHouseholds", "xpath", "xpath");
			} else {
				clickButtonByXpathTitleName("Households");
			}
			
			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("AFPEighteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPEleven, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFifteen, Member", "textAtt", "value"));
			
			//Test Assigned Home Teachers
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			clickButton("AssignedHomeTeachersBox", "id", "xpath");
			myOS = getRunningOS();
			//System.out.println("Running OS: " + myOS);
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "Assigned Home Teachers", "id", "xpath");
			} else {
				pressBackKey();
			}
			Thread.sleep(1000);
			Assert.assertTrue(checkElementReturn("AFPEleven, Member", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("Aaron, Jane", "textAtt", "value"));
			//Assert.assertTrue(checkElementTextViewReturn("Aaron, Jane"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
		
			//Test NOT Assigned Home Teachers
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("AssignedHomeTeachersBox", "id", "xpath");
			}
			clickButton("NotAssignedHomeTeachersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "Not Assigned Home Teachers", "id", "xpath");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("AFPEleven, Member", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("AFPFifteen, Member", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}

			//New and Moved in Members
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("NotAssignedHomeTeachersBox", "id", "xpath");
			}
			clickButton("NewAndMovedInMembersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "New & Moved-In Members", "id", "xpath");
			} else {
				pressBackKey();
				Thread.sleep(2000);
			}

			//Need to fix
			Assert.assertTrue(checkElementTextViewReturn("John, Smith"));
			//Assert.assertTrue(checkElementTextViewReturn("Tuipoloa, Arieta"));
			//Assert.assertTrue(checkElementTextViewReturn("Sanele, Ana"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}

			
			//Single Sisters 18-30 years old
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("NewAndMovedInMembersBox", "id", "xpath");
			}
			clickButton("SingleSisters1830", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 18-30 years old");
			Assert.assertTrue(checkElementReturn("Fiamatai, Solomua", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Ielv, Gasolo", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Lagaaia, Fofogafetalaililomaiava", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
		
			
			
			//Single Sisters 31 and over
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleSisters1830", "id", "xpath");
			}
			clickButton("SingleSisters31over", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementReturn("AFPEighteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			
			//Single Brothers 18-30 years old
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleSisters31over", "id", "xpath");
			}
			clickButton("SingleBrothers1830", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Brothers 18-30 years old");
			Assert.assertTrue(checkElementReturn("Anderson, Edward", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Galo Meli, Mulivai", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Faivaa, Tepa", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			//Single Brothers 31 and over
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleBrothers1830", "id", "xpath");
			}
			clickButton("SingleBrohters31over", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementReturn("AFPEleven, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFifteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member1", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			//Need a test for Households not visisted slider.
			
			pressBackKey();
			
			
			//Elders Quorum
			if (getRunningOS().equals("mac")) {
				clickButton("EldersHouseholds", "xpath", "xpath");
			} else {
				scrollDownTEST(100);
				clickLastTextViewRoboReturnContains("Households");
			}

			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("AFPMisc, Member15", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPTen, Husband", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPSix, Husband", "textAtt", "value"));
			
			//Test Assigned Home Teachers
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			clickButton("AssignedHomeTeachersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "Assigned Home Teachers", "id", "xpath");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementReturn("AFPTen, Husband", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Tools, LDS24", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Tools, LDS47", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
		
			//Test NOT Assigned Home Teachers
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("AssignedHomeTeachersBox", "id", "xpath");
			}
			clickButton("NotAssignedHomeTeachersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "Not Assigned Home Teachers", "id", "xpath");
			} else {
				pressBackKey();
			}
			//Assert.assertTrue(checkElementReturn("Apofasa, Sasa'a", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPSix, Husband", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member15", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}

			//New and Moved in Members
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("NotAssignedHomeTeachersBox", "id", "xpath");
			}
			clickButton("NewAndMovedInMembersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "New & Moved-In Members", "id", "xpath");
			} else {
				pressBackKey();
			}
			Assert.assertFalse(checkElementReturn("AFPMisc, Member15", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("AFPSix, Husband", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("AFPTen, Husband", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}


			//Single Sisters 18-30 years old
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("NewAndMovedInMembersBox", "id", "xpath");
			}
			clickButton("SingleSisters1830", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 18-30 years old");
			Assert.assertFalse(checkElementReturn("Fiamatai, Solomua", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("Ielv, Gasolo", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("Lagaaia, Fofogafetalaililomaiava", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
		
			
			//Single Sisters 31 and over
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleSisters1830", "id", "xpath");
			}
			clickButton("SingleSisters31over", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertFalse(checkElementReturn("AFPEighteen, Member", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			
			//Single Brothers 18-30 years old
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleSisters31over", "id", "xpath");
			}
			clickButton("SingleBrothers1830", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Brothers 18-30 years old");
			Assert.assertTrue(checkElementReturn("Mene, Taavili Maalona", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("Etene, Max", "textAtt", "value"));
			Assert.assertFalse(checkElementReturn("Faivaa, Tepa", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			//Single Brothers 31 and over
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleBrothers1830", "id", "xpath");
			}
			clickButton("SingleBrohters31over", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementReturn("AFPMisc, Member15", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPSix, Husband", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPTen, Husband", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			//Need a test for Households not visisted slider.
			
			
			pressBackKey();
			pressBackKey();
		
		}
		
		if ((userCalling.equals("Relief Society Pres")) || (userCalling.equals("Bishopric"))) {
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("Visiting Teaching");
			} else {
				//scrollDownTEST(200);
				//Thread.sleep(1000);
				driver.scrollToExact("Visiting Teaching");
				clickButtonByXpathTitleName("Visiting Teaching");
			}
			Thread.sleep(2000);

			//Visiting Teaching
			clickButtonByXpathTitleName("Sisters");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member14", "textAtt", "value"));

			//Test Assigned Visiting Teachers
			//clickButtonByXpath("MenuFilter");
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			clickButton("AssignedHomeTeachersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "Assigned Visiting Teachers", "id", "xpath");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementReturn("Galuvao, Logo", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Tools, LDS44", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Aaron, Jane", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
		
			//Test NOT Assigned Visiting Teachers
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("AssignedHomeTeachersBox", "id", "xpath");
			}

			clickButton("NotAssignedHomeTeachersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "Not Assigned Visiting Teachers", "id", "xpath");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member12", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPMisc, Member14", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
 
			//New and Moved in Members
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("NotAssignedHomeTeachersBox", "id", "xpath");
			}
			clickButton("NewAndMovedInMembersBox", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
				checkText("HTVTFiltersApplied", "New & Moved-In Members", "id", "xpath");
			} else {
				pressBackKey();
			}
			//Assert.assertTrue(checkElementReturn("Smith, Ferila", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("Tuipoloa, Arieta", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("Tuipoloa, Taimi", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			Thread.sleep(1000);

			//Single Sisters 18-30 years old
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("NewAndMovedInMembersBox", "id", "xpath");
			}
			clickButton("SingleSisters1830", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 18-30 years old");
			Assert.assertTrue(checkElementReturn("Aitusavali, Solofuti Saluatai", "textAtt", "value"));
			//Assert.assertTrue(checkElementReturn("Ami, Faleatafa", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("Etene, Foketi Faamoe", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
		
			
			//Single Sisters 31 and over
			clickButton("MenuFilter", "id", "xpath");
			Thread.sleep(1000);
			if (getRunningOS().equals("mac")) {
				clickButton("SingleSisters1830", "id", "xpath");
			}
			clickButton("SingleSisters31over", "id", "xpath");
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTApply", "id", "xpath");
				clickButton("HTVTExpand", "id", "xpath");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementReturn("AFPEighteen, Member", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFive, Wife", "textAtt", "value"));
			Assert.assertTrue(checkElementReturn("AFPFourteen, Member", "textAtt", "value"));
			if (!getRunningOS().equals("mac")) {
				clickButton("HTVTRemoveFiltersButton", "id", "xpath");
			}
			
			
			//Need a test for Households not visisted slider.
			
			
			pressBackKey();
			pressBackKey();
			

		}
		
		
		
	}
	
	
	
	
	
	
	private void checkAllWardDirectories() throws Exception {
		List<String> StakeWard = new ArrayList<String>();
		List<WebElement> options = new ArrayList<WebElement>();
		int pageSize;
		int myCounter = 1;
		Thread.sleep(2000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("SpinnerSubTitle");
			//Get Stake and all Wards
			options= driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell/UIAStaticText"));
			for (int i = 0 ; i < options.size(); i++ ) {
				//System.out.println(options.get(i).getText());
				StakeWard.add(options.get(i).getText());
			}
			clickButtonByXpath("TopCancel");
			
			
			//Go through each Stake and Ward to make sure it isn't blank
			for(String StakeWardItem : StakeWard){
				//clickButtonByXpath("SpinnerNav");
				clickButtonByXpath("SpinnerSubTitle");
				Thread.sleep(2000);
				//System.out.println("To Click: " + StakeWardItem);	
				/*
				if (myCounter > 1 ) {
					System.out.println("Scroll Down: " + StakeWardItem);
					//pageSize = driver.manage().window().getSize().getHeight();
					//pageSize = -pageSize;
					//scrollDownTEST(pageSize);
					driver.scrollTo("//*[@name=\'" + StakeWardItem + "\']");
				}
				*/
				clickButtonByXpathTitleName(StakeWardItem);
				//displayAllTextViewElements();
				Thread.sleep(6000);
				//This will check to see if the first user has text.  
				Assert.assertTrue(checkFirstDirectoryUser());
				
		         if(myCounter == 5){
		             break; // Don't like this need a better solution. 
		         }
				
				myCounter++;
				//Assert.assertTrue(checkElementTextViewReturnContains("e"));
				//Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
			}
		} else {
			clickButtonByID("SpinnerNav");
			//Get Stake and all Wards
			//options = driver.findElements(By.xpath("//*[@id='list_item']/*[@id='text1']"));
			options = driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='org.lds.ldstools.dev:id/list_item']/android.widget.TextView[@resource-id='org.lds.ldstools.dev:id/text1']"));
			//options = driver.findElements(By.xpath("//android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.TextView"));
			for (int i = 0 ; i < options.size(); i++ ) {
				//System.out.println(options.get(i).getText());
				StakeWard.add(options.get(i).getText());
			}
			Thread.sleep(1000);
			clickButtonByID("SpinnerNav");
			
			//Thread.sleep(1000);
			//pressBackKey();
			Thread.sleep(1000);
			
			//Go through each Stake and Ward to make sure it isn't blank
			for(String StakeWardItem : StakeWard){
				clickButtonByID("SpinnerNav");
				Thread.sleep(2000);
				clickButtonByXpathTitleName(StakeWardItem);
				//displayAllTextViewElements();
				

				Assert.assertTrue(checkFirstDirectoryUser());
				//Assert.assertTrue(checkElementTextViewReturnContains("e"));
				Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
			}
			
			
		}


	}
	
	
	private void checkDirectoryForUser() throws Exception {
		if (getRunningOS().equals("mac")) {
			Assert.assertTrue(checkFirstDirectoryUser());

		} else {
			Assert.assertTrue(checkElementTextViewReturnContains("e"));
		}
	}
	
	
	
	private Boolean checkFirstDirectoryUser() {
		Boolean myReturnStatus;
		String myString;
		//String myString = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).getText();
		if (getRunningOS().equals("mac")) {
			myString = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).getText();
		} else {
			//myString = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='org.lds.ldstools.dev:id/text1'][1]")).getText();
			myString = driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='org.lds.ldstools.dev:id/top_layout']//android.widget.TextView")).getText();
		}
		
													   

		if (myString.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
			//System.out.println("FOUND USER: " + myString);
		}
		
		return myReturnStatus;
	}
	
	private void RotateAndCheckText() throws Exception{
		List<String> origText = new ArrayList<String>();
		List<String> textToCheck = new ArrayList<String>();
		
		Thread.sleep(2000);
		//System.out.println("PORTRAIT MODE: ");
		origText = GetTextForElements();
		int myCounter = 3;
		
		//Thread.sleep(2000);
		driver.rotate(ScreenOrientation.LANDSCAPE);
		Thread.sleep(2000);
		//System.out.println("LANDSCAPE MODE: ");
		
		textToCheck = GetTextForElements();
		if (textToCheck.size() < 3 ) {
			myCounter = textToCheck.size();
		}
		
		System.out.println("*****************************************");
		for (int i = 0 ; i < myCounter; i++ ) {
			System.out.println("Text To Check: " + textToCheck.get(i) + "   Original Text: " + origText.get(i));
			Assert.assertEquals(textToCheck.get(i), origText.get(i));
		}
		System.out.println("*****************************************");
		
		driver.rotate(ScreenOrientation.PORTRAIT);
		Thread.sleep(1000);
		//System.out.println("PORTRAIT MODE: ");

		textToCheck = GetTextForElements();
		if (textToCheck.size() < 3 ) {
			myCounter = textToCheck.size();
		}
		
		for (int i = 0 ; i < myCounter; i++ ) {
			Assert.assertEquals(textToCheck.get(i), origText.get(i));
		}
		
		Thread.sleep(2000);
													   
	}
	
	private List<String> GetTextForElements() {
		List<WebElement> options;
		List<String> allText = new ArrayList<String>();

		if (getRunningOS().equals("mac")) {
			//options = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
			options = driver.findElements(By.xpath("//UIAApplication/UIAWindow/UIATableView/UIATableCell/UIAStaticText"));

		} else {
			//myString = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='org.lds.ldstools.dev:id/text1'][1]")).getText();
			options = driver.findElements(By.xpath("//android.widget.RelativeLayout[@resource-id='org.lds.ldstools.dev:id/top_layout']//android.widget.TextView"));
		}
		
		
		String myText;
		for (int i = 0 ; i < options.size(); i++ ) {
			//System.out.println(options.get(i).getText());
			myText = options.get(i).getText();
			//System.out.println("DEBUG Text: " + myText);
			if (!myText.isEmpty()) {
				allText.add(myText);
			}
			
			//allText.add(i, options.get(i).getText());
		}
		
		return allText;
	}
	
	
	
	private void drawerSignOut() throws Exception {
		Thread.sleep(2000);
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerMore");
			//Check to see if the sync page is dispalyed
			if (checkElementNameReturn("Sync Now") == true) {
				pressBackKey();
			}
			clickButtonByXpath("DrawerSETTINGS");
		}else {
			clickButtonByXpath("Drawer");
			if (checkElementReturn("Later", "textAtt", "value") == true ) {
				clickButtonByXpathTitleName("Later");
			}
			//scrollDown("Settings", -5 );
			clickButtonByXpath("DrawerSETTINGS");
		}
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Sign Out");
		clickButton("SignOutOK", "id", "xpath");
		checkForAlert();
		if (getRunningOS().equals("mac")) {
			driver.resetApp();
		}
		


	}
	
	//TODO: Need to be able to select 1 to 12 units
	/** unitsToSync()
	 * If there are more than 12 units this will select the Savaii Stake
	 * @throws Exception
	 */
	private void unitsToSync() throws Exception {
		if (checkElementReturn("Select up to 12 units to sync.", "textAtt", "value")) {
			clickButtonByXpathTitleName("Savaii Samoa Fagamalo Stake");
			clickButtonByXpath("SyncButton");
			Thread.sleep(2000);
		}
	}
	
	private void resetVisibility() throws Exception {
		int myCheck;
		String textCheck;
		
		
		if (getRunningOS().equals("mac")) {
			textCheck = getTextXpath("VisibilityHVL");
			if (!textCheck.contains("Stake")) {
				clickButton("HouseholdVisibilityLimit", "id", "xpath");
				Thread.sleep(2000);
				clickButtonByXpath("StakePopUp");
				//clickButtonByXpathTitleNameContains("Stake Visibility");
				Thread.sleep(1000);
			}
			
			textCheck = getTextXpath("VisibilityPersonal");
			if (!textCheck.contains("Stake")) {
				clickButtonByXpath("VisibilityPersonal");
				Thread.sleep(2000);
				clickButtonByXpath("EditAllVisibility");
				clickButtonByXpath("StakeVisibility");
				Thread.sleep(1000);
				clickButtonByXpath("DoneButton");
			}
			
			textCheck = getTextXpath("VisibilityHousehold");
			if (!textCheck.contains("Stake")) {
				clickButtonByXpath("VisibilityHousehold");
				Thread.sleep(2000);
				clickButtonByXpath("EditAllVisibility");
				clickButtonByXpath("StakeVisibility");
				Thread.sleep(1000);
				clickButtonByXpath("DoneButton");
			}
			
		} else {
			myCheck = checkTextByXpathContainsReturn("HouseholdVisibilityLimit", "Private");
			
			//Force myCheck to be 1 to always change privacy	
			myCheck = 1;

			if ((myCheck == 1)) {
				clickButtonByXpathTitleName("Privacy");
				//clickButtonByXpathTitleName("Household Visibility Limit");
				clickButton("HouseholdVisibilityLimit", "id", "xpath");
				Thread.sleep(2000);
				clickButton("RadioStake", "id", "xpath");
				clickButton("SetLimit", "id", "xpath");
				Thread.sleep(1000);
			}
			

			clickButton("PrivacyPersonalPhoto", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			clickButton("PrivacyPersonalPhone", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			clickButton("PrivacyPersonalEmail", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			driver.scrollToExact("Show on Map");
			
			clickButton("PrivacyHouseholdPhoto", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdPhone", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdEmail", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdAddress", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
			
			clickButton("PrivacyHouseholdShowOnMap", "xpath", "xpath");
			clickButton("PrivacyStakeVisibility", "xpath", "xpath");
	
			
		}
		

		clickButton("MenuSave", "id", "xpath");
		Thread.sleep(2000);
		editUserOpen(); 


	}
	
	
	private void searchForUser(String userToSearch) throws Exception {
		Boolean checkForElement; 
		List<WebElement> options = null;
		int myCounter = 1;
		WebElement myElement = null;
		
		String lowerCaseSearch = userToSearch.toLowerCase();
		
		if (getRunningOS().equals("mac")) {
			sendTextbyXpath("SearchArea", userToSearch);
			//sendTextbyXpath2("SearchArea", userToSearch);
			//driver.findElement(By.xpath("//UIAButton[@label='Search']")).click();
			Thread.sleep(2000);
			//driver.tap(1, 10, 10, 1000);
			//driver.findElementByXPath("//UIAStaticText[@label='" + userToSearch + "']").click();
			//driver.findElementByXPath("//UIAStaticText[contains(@label, '" + userToSearch + "')]").click();
			myElement = driver.findElement(By.xpath("//UIATableCell/UIAStaticText[contains(@label, '" + userToSearch + "')]"));
			Point myPoint = myElement.getLocation();
			driver.tap(1, myPoint.x, myPoint.y, 700);
			
		} else {
			clickButtonByID("MenuDefaultDirectory");
			clickButtonByXpathTitleName("Individuals");
			clickButtonByID("MenuSearch");
			sendTextbyXpath("SearchArea", lowerCaseSearch + " ");
			
			adbPressSearch();
			
			Thread.sleep(2000);
			
			//driver.scrollTo(userToSearch);
			driver.scrollToExact(userToSearch);
		
			/*
			do {
				options = driver.findElements(By.xpath("//android.widget.TextView[@text='" + userToSearch + "']"));
				if (options.isEmpty()) {
					checkForElement = false;	
					scrollDownTEST(40);
				} else {
					checkForElement = true;
				}
				myCounter++;
				
			} while ((checkForElement = false) || (myCounter < 12));
			
			*/
			driver.findElement(By.xpath("//android.widget.TextView[@text='" + userToSearch + "']")).click();
			//clickLastTextViewRoboReturnContains(userToSearch);
			
		}
		
		Thread.sleep(2000);
	}
	
	private void checkForAlert() throws Exception {
		//Check to see if we are getting a warning
		if (checkElementExistsByXpath("AlertMessageCheck") == true) {
			clickButtonByXpath("OK");
		}
	}
	
	private void checkForAlertOK() throws Exception {
		//Check to see if we are getting a warning
		if (getRunningOS().equals("mac")) {
			if (checkElementExistsByXpath("AlertMessageCheck") == true) {
				clickButton("AlertOK", "id", "xpath");
			}
		} else {
			if (checkElementExistsByID("AlertMessageCheck") == true) {
				clickButton("AlertOK", "id", "xpath");
			}
		}

	}
	private void checkWebMemberInfo(String loginName, String passWord, String userToCheck) throws Exception {
		String pageSource;
		//Data from Web page
		List<String> myList = new ArrayList<String>();
		String os;
		String userSwitch;
		
		if (getRunningOS().equals("mac")) {
			os = "ios";
		} else {
			os = "android";
		}
		
		String[] parts = userToCheck.split(" ");
		String part1 = parts[0];
		part1 = part1.replace(",", "");
		String part2 = parts[1];
		userSwitch = part2 + " " + part1;
		
		//Login to LDS Tools
		syncLogIn(loginName, passWord, "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);	
		
		//LDSWeb myWeb = new LDSWeb();
		myList = myWeb.getMemberDetails(userToCheck, loginName, passWord);
		
		
		//Search for logged in user
		searchForUser(userToCheck);
		
		if (getRunningOS().equals("mac")) {
			//clickButtonByXpathTitleName(userSwitch);
			iosExpandAllDirectory();
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList(userToCheck, pageSource, "Equals"));
		} else {
			clickButtonByXpathTitleName(userToCheck);
			pageSource = androidGetMemberInfo();
			Assert.assertTrue(checkNoCaseList(userToCheck, pageSource, "Equals"));
		}
		
		for(String oneUser : myList){
			System.out.println("USER: " + oneUser);
			Assert.assertTrue(checkNoCaseList(oneUser, pageSource, "Contains"));
		}
	}
	
	private void compareWebData(List<String> myList, List<String> androidList, Boolean onePage) throws Exception {
		String pageSource = null;
		int pageSize;
		String lastMember;
		String lastMemberCheck;
		
		String memberToSelect;
		
		if (getRunningOS().equals("mac")){
			pageSource = getSourceOfPage();
			for(String oneUser : myList){
				System.out.println("USER: " + oneUser);
				//TODO: When "Out of Unit" bug is fixed remove the check
				if ((oneUser.contains("Jr")) || (oneUser.contains("Salvador")) || (oneUser.contains("Junior") || (oneUser.contains("Farley")
						|| (oneUser.contains("Raymundo") || (oneUser.contains("Sarwar") ||(oneUser.contains("Dylan") || (oneUser.contains("Siteni") || (oneUser.contains("Ah Kam")
						|| (oneUser.contains("Peterson") || (oneUser.contains("Latu") ||(oneUser.contains("Morgan") ||(oneUser.contains("Wilson, Tina"))))))))))))){
					System.out.println("Skipping: " + oneUser);
				} else {
					Assert.assertTrue(checkNoCaseList(oneUser, pageSource, "Contains"));
				}
				
			}

		} else {
			
			if (onePage == false ) {
				pageSize = driver.manage().window().getSize().getHeight();
				//System.out.println("Page Size: " + pageSize);
				//pageSize = pageSize - 20;
				//System.out.println("Orig Page Size: " + pageSize);
				pageSize = pageSize / 2;
				//System.out.println("Half Page Size: " + pageSize);
				pageSize = (pageSize / 2 ) + pageSize;
				//System.out.println("3/4 Page Size: " + pageSize);
				//pageSize = -pageSize;
				
				
				
				//This will scroll through the android pages and get all of the data. 
				do {
					pageSource = getSourceOfPage();
					androidList = createUserList(androidList, pageSource);
					if (androidList.size() == 0) {
						lastMember = "none";
						lastMemberCheck = "none";
					} else {
						lastMember = androidList.get(androidList.size() - 1 );

						scrollDownSlow(pageSize);
						Thread.sleep(1000);
						pageSource = pageSource + getSourceOfPage();
						androidList = createUserList(androidList, pageSource);
						lastMemberCheck = androidList.get(androidList.size() - 1 );
						System.out.println("Last Member: " + lastMember);
						System.out.println("Last Member Check: " + lastMemberCheck);
					}

				} while (!lastMember.equals(lastMemberCheck));
				
				
				//System.out.println("***************************");
				//pageSource = getSourceOfPage();
				//androidList = createUserList(androidList, pageSource);
				//System.out.println("***************************");

				for(String oneUser : myList) {
					System.out.println("USER: " + oneUser);
					if ((oneUser.contains("Jr")) || (oneUser.contains("Salvador")) || (oneUser.contains("Junior") || (oneUser.contains("Farley")
							|| (oneUser.contains("Raymundo") || (oneUser.contains("Dylan") || (oneUser.contains("Siteni") 
							|| (oneUser.contains("Morgan") || (oneUser.contains("Wilson, Tina"))))))))){
						System.out.println("Skipping: " + oneUser);
					} else {
						Assert.assertTrue(androidList.contains(oneUser));
					}
				}
			} else {
				//driver.getPageSource();
				pageSource = getSourceOfPage();
				androidList.clear();
				androidList = createUserList(androidList, pageSource);
				//androidList = getAllText();
				/*
				for(String oneUser : androidList) {
					System.out.println("USER: " + oneUser);
					if ((oneUser.contains("Jr")) || (oneUser.contains("Salvador")) || (oneUser.contains("Junior"))){
						System.out.println("Skipping: " + oneUser);
					} else {
						Assert.assertTrue(myList.contains(oneUser));
					}
				}
				*/
				System.out.println("****** Android Users ******");
				if(!myList.isEmpty()) {
					for(int myCounter = 0 ; myCounter < androidList.size() ; myCounter++) {
						if ((myList.get(myCounter).contains("Jr")) || (myList.get(myCounter).contains("Salvador")) 
								|| (myList.get(myCounter).contains("Joseph")) || (myList.get(myCounter).contains("Junior"))) {
							System.out.println("Skipping: " + myList.get(myCounter));
						} else {
							System.out.println("Android USER: " + myList.get(myCounter));
							Assert.assertTrue(myList.contains(androidList.get(myCounter)));
						}
					}
				}	
				System.out.println("****** Done Android Users ******");
			}
			

		}	
	}
	
	private void compareWebDataCheck(List<String> myList, List<String> androidList, Boolean onePage) throws Exception {
		String pageSource = null;
		int pageSize;
		String lastMember;
		String lastMemberCheck;
		boolean userFound;
		
		String memberToSelect;
		
		if (getRunningOS().equals("mac")){
			pageSource = getSourceOfPage();
			for(String oneUser : myList){
				//System.out.println("USER: " + oneUser);
				//TODO: When "Out of Unit" bug is fixed remove the check
				if ((oneUser.contains("Jr")) || (oneUser.contains("Salvador")) || (oneUser.contains("Junior") || (oneUser.contains("Farley")
						|| (oneUser.contains("Raymundo") || (oneUser.contains("Sarwar") ||(oneUser.contains("Dylan") || (oneUser.contains("Siteni") || (oneUser.contains("Ah Kam")
						|| (oneUser.contains("Peterson") || (oneUser.contains("Latu") ||(oneUser.contains("Morgan") ||(oneUser.contains("Wilson, Tina"))))))))))))){
					System.out.println("Skipping: " + oneUser);
				} else {
					userFound = checkNoCaseList(oneUser, pageSource, "Contains");
					if (userFound == false) {
						System.out.println("USER NOT FOUND: " + oneUser);
					}
				}
				
			}

		} else {
			
			if (onePage == false ) {
				pageSize = driver.manage().window().getSize().getHeight();
				//System.out.println("Page Size: " + pageSize);
				//pageSize = pageSize - 20;
				pageSize = pageSize - 100;
				//pageSize = -pageSize;
				
				
				
				//This will scroll through the android pages and get all of the data. 
				do {
					pageSource = getSourceOfPage();
					androidList = createUserList(androidList, pageSource);
					lastMember = androidList.get(androidList.size() - 1 );
					//memberToSelect = androidList.get(androidList.size() / 2 );
					
					//System.out.println("Member To Select: " + memberToSelect);
					//scrollDownPerPage(pageSize);
					scrollDownSlow(pageSize);
					//scrollDownTEST(pageSize);
					//flickUpOrDown(pageSize);
					Thread.sleep(1000);
					pageSource = getSourceOfPage();
					androidList = createUserList(androidList, pageSource);
					lastMemberCheck = androidList.get(androidList.size() - 1 );
					System.out.println("Last Member: " + lastMember);
					System.out.println("Last Member Check: " + lastMemberCheck);
				} while (!lastMember.equals(lastMemberCheck));
				
				
				//System.out.println("***************************");
				//pageSource = getSourceOfPage();
				//androidList = createUserList(androidList, pageSource);
				//System.out.println("***************************");

				for(String oneUser : myList) {
					System.out.println("USER: " + oneUser);
					if ((oneUser.contains("Jr")) || (oneUser.contains("Salvador")) || (oneUser.contains("Junior") || (oneUser.contains("Farley")
							|| (oneUser.contains("Raymundo") || (oneUser.contains("Dylan") || (oneUser.contains("Siteni") 
							|| (oneUser.contains("Morgan") ||(oneUser.contains("Wilson, Tina"))))))))){
						System.out.println("Skipping: " + oneUser);
					} else {
						userFound = checkNoCaseList(oneUser, pageSource, "Contains");
						if (userFound == false) {
							System.out.println("USER NOT FOUND: " + oneUser);
						}
					}
				}
			} else {
				//driver.getPageSource();
				pageSource = getSourceOfPage();
				androidList.clear();
				androidList = createUserList(androidList, pageSource);
				//androidList = getAllText();
				/*
				for(String oneUser : androidList) {
					System.out.println("USER: " + oneUser);
					if ((oneUser.contains("Jr")) || (oneUser.contains("Salvador")) || (oneUser.contains("Junior"))){
						System.out.println("Skipping: " + oneUser);
					} else {
						Assert.assertTrue(myList.contains(oneUser));
					}
				}
				*/
				if(!myList.isEmpty()) {
					for(int myCounter = 0 ; myCounter < androidList.size() ; myCounter++) {
						//for(String oneUser : myList) {
							System.out.println("USER: " + myList.get(myCounter));
							Assert.assertTrue(myList.contains(androidList.get(myCounter)));
							//Assert.assertTrue(androidList.contains(myList.get(myCounter)));
					}
				}	
			}
			

		}	
	}
	
	private void scrollToTheTop() throws Exception {
		int pageSize;
		pageSize = driver.manage().window().getSize().getHeight();
		//System.out.println("Page Size: " + pageSize);
		pageSize = pageSize - 20;
		//pageSize = -pageSize;
		
		for (int x = 0; x < 10 ; x++ ) {
			scrollUpTEST(pageSize);
			//pageSize = pageSize + 10;
		}
		
	}
	
	
	private void openOrgnizations() throws Exception {
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerOrganizations");
		} else {
			clickButtonByXpath("Drawer");
			clickButtonByXpath("DrawerOrganizations");
		}
	}
	
	private void openMissionary() throws Exception {
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerMore");
			clickButtonByXpath("DrawerMissionary");
		} else {
			clickButtonByXpath("Drawer");
			clickButtonByXpath("DrawerMissionary");
		}
	}
	
	private void openReports() throws Exception {
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerReports");
		} else {
			clickButtonByXpath("Drawer");
			clickButtonByXpath("DrawerReports");
		}
	}
	
	private void openCalendar() throws Exception {
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerCalendar");
		} else {
			clickButtonByXpath("Drawer");
			clickButtonByXpath("DrawerCalendar");
		}
	}
	
	
	private void openLists() throws Exception {
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerMore");
			clickButtonByXpath("DrawerLists");
		} else {
			clickButtonByXpath("Drawer");
			clickButtonByXpath("DrawerLists");
		}
	}
	
	private void runSync() throws Exception {
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("DrawerMore");
			
			//Check to see if the sync page is dispalyed
			if (checkElementNameReturn("Sync Now") == true) {
				pressBackKey();
			}
			
			clickButtonByXpath("DrawerUpdate");
			
			//This will probably change
			Thread.sleep(1000);
			//clickButtonByXpath("SignInButton");
			driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[@name='Sync Now']")).click();
			//clickButtonByName("Sync Now");
			Thread.sleep(6000);
			waitForTextToDisappear("SyncText", 500 );
			//Thread.sleep(2000);
			//pinPage("1", "1", "3", "3", true);
			
		} else {
			clickButtonByXpath("Drawer");
			if (checkElementReturn("Later", "textAtt", "value") == true ) {
				clickButtonByXpathTitleName("Later");
			}

			scrollDown("Sync", -5 );
			//Thread.sleep(4000);
			//clickButtonByXpath("DrawerSYNC");
			Thread.sleep(4000);
			clickButton("AlertOK", "id", "xpath");
			
			Thread.sleep(4000);
			waitForTextToDisappearID("SyncText", 500 );
			Thread.sleep(2000);
		}

		checkForAlert();
	}
	
	private String getLastIcon() throws Exception {
		int listSize;
		String returnString;
		List<WebElement> options= driver.findElements(By.xpath("//RecyclerView/LinearLayout"));
		listSize = options.size();
		//listSize = listSize + 1;
		//returnString = "//RecyclerView/LinearLayout[" + listSize +"]/LinearLayout/*[@id='icon']";
		returnString = "//RecyclerView/LinearLayout[" + listSize +"]/RelativeLayout/*[@id='title']";
		
		//RecyclerView/LinearLayout[5]/RelativeLayout/*[@id='title']
		
		System.out.println("Element to click: " + returnString);
		return returnString;
	}
	
	private String androidGetMemberInfo() throws Exception {
		String myPageSource;
		boolean myCheck;
		
		//Contact Tab
		Thread.sleep(1000);
		myPageSource = getSourceOfPage();
		
		scrollDownTEST(800);
		
		clickButtonByXpath("TabHousehold");
		Thread.sleep(1000);
		myPageSource = myPageSource + getSourceOfPage();
		
		clickButtonByXpath("TabCallings");
		Thread.sleep(1000);
		myPageSource = myPageSource + getSourceOfPage();
		
		
		myCheck = checkElementExistsByXpath("TabHomeTeaching");
		if (myCheck == true) {
			clickButtonByXpath("TabHomeTeaching");
			Thread.sleep(1000);
			myPageSource = myPageSource + getSourceOfPage();
		}
		
		myCheck = checkElementExistsByXpath("TabMembership");
		if (myCheck == true) {
			clickButtonByXpath("TabMembership");
			Thread.sleep(1000);
			myPageSource = myPageSource + getSourceOfPage();
			scrollDownTEST(800);
			myPageSource = myPageSource + getSourceOfPage();
		}
		
		myCheck = checkElementExistsByXpath("TabHomeTeaching");
		if (myCheck == true) {
			clickButtonByXpath("TabHomeTeaching");
			Thread.sleep(1000);
		}
		
		clickButtonByXpath("TabCallings");
		clickButtonByXpath("TabHousehold");
		clickButtonByXpath("TabContact");

		return myPageSource;
	}
	
	private String androidGetInfoScroll() throws Exception {
		String myPageSource;
		
		//Contact Tab
		Thread.sleep(1000);
		myPageSource = getSourceOfPage();
		//scrollDownTEST(100);

		myPageSource = myPageSource + getSourceOfPage();
		
		//scrollToTheTop();
		return myPageSource;
	}
	
	
	private void iosExpandAllDirectory() throws Exception {
		//scrollDownIOS();
		//Thread.sleep(1000);
		boolean checkArrowDown;
		boolean checkForLabel;
		
		scrollToLastElementIOS("Open Drawer");
		//scrollToLastElementIOS("\u25BC");
		
		checkForLabel = checkElementReturn("OpenHouseholdMembers", "xpath", "xpath");
		if (checkForLabel == true) {
			clickButton("OpenHouseholdMembers", "xpath", "xpath");
			checkForLabel = false;
		}
		
		checkForLabel = checkElementReturn("OpenHTVT", "xpath", "xpath");
		if (checkForLabel == true) {
			clickButton("OpenHTVT", "xpath", "xpath");
			checkForLabel = false;
		}
				
		checkForLabel = checkElementReturn("OpenCallingsAndClasses", "xpath", "xpath");
		if (checkForLabel == true) {
			clickButton("OpenCallingsAndClasses", "xpath", "xpath");
			checkForLabel = false;
		}		
		
		checkForLabel = checkElementReturn("OpenMembershipInfo", "xpath", "xpath");
		if (checkForLabel == true) {
			clickButton("OpenMembershipInfo", "xpath", "xpath");
			checkForLabel = false;
		}	

		/*
		//checkArrowDown = checkElementTextViewRoboReturn("\u25BC");
		checkArrowDown = checkElementReturn("OpenDrawer", "xpath", "xpath");
		
		if (checkArrowDown == true ) {
			while(checkArrowDown == true ) {
				//scrollToLastElementIOS("Open Drawer");
				scrollToElementIOS("Open Drawer");
				clickButton("OpenDrawer", "xpath", "xpath");
				Thread.sleep(1000);
				//checkArrowDown = checkElementTextViewRoboReturn("\u25BC");
				checkArrowDown = checkElementReturn("OpenDrawer", "xpath", "xpath");
			}
		}
		
		
		boolean checkArrowDown;
		checkArrowDown = checkElementTextViewRoboReturn("\u25BC");
		if (checkArrowDown == true ) {
			//scrollToElementIOS("\u25BC");
			scrollToLastElementIOS("\u25BC");
		}
		
		while(checkArrowDown == true ) {
			clickButtonByNameScroll("\u25BC");
			//scrollToTopDirectoryIOS();
			//scrollDownIOS();
			checkArrowDown = checkElementTextViewRoboReturn("\u25BC");
			if (checkArrowDown == true ) {
				//scrollToElementIOS("\u25BC");
				scrollToLastElementIOS("\u25BC");
				Thread.sleep(1000);
			}
		}
		*/
		
		
		
	}
	
	
	private String androidGetMissionariesInfo() throws Exception {
		String myPageSource;
		
		//Assigned Tab
		myPageSource = getSourceOfPage();
		
		clickButtonByXpath("MissWardTab");
		Thread.sleep(1000);
		myPageSource = myPageSource + getSourceOfPage();
		
		clickButtonByXpath("MissServingTab");
		Thread.sleep(1000);
		myPageSource = myPageSource + getSourceOfPage();

		
		return myPageSource;
	}
	
	private void clearLoginPassword() throws Exception {
		//Clear the login and password fields
		Thread.sleep(2000);
		clearTextField("LoginUsername", "id", "xpath");
		clearTextField("LoginPassword", "id", "xpath");
		Thread.sleep(2000);
	}
	
	private void clearPhoneAndEmail() throws Exception {	
		Capabilities cap = driver.getCapabilities();
		Map<String, ?> desired = (Map<String, ?>) cap.getCapability("desired");
		Object myAppPackage = desired.get("appPackage");
		
		Thread.sleep(4000);
		editUserOpen(); 
		Thread.sleep(2000);
		
		//sendTextbyXpath("EditPersonalPhone", "11");
		clearTextFieldXpath("EditPersonalPhone");
		//myKeyboardClear();
		
		//sendTextbyXpath("EditHomePhone", "11");
		//System.out.println(desired.get("appPackage"));
		if (myAppPackage.equals("org.lds.ldstools")) {
			clearTextFieldXpath("ReleaseEditHomePhone");
		} else {
			clearTextFieldXpath("EditHomePhone");
		}
		//myKeyboardClear();
		
		Thread.sleep(1000);
		//sendTextbyXpath("EditPersonalEmail", "aaa");
		if (myAppPackage.equals("org.lds.ldstools")) {
			clearTextFieldXpath("ReleaseEditPersonalEmail");
		} else {
			clearTextFieldXpath("EditPersonalEmail");
		}
		
		//myKeyboardClear();
		
		Thread.sleep(1000);
		//clickButton("EditHomeEmail", "xpath", "xpath");
		//sendTextbyXpath("EditHomeEmail", "aaa");
		if (myAppPackage.equals("org.lds.ldstools")) {
			clearTextFieldXpath("ReleaseEditHomeEmail");
		} else {
			clearTextFieldXpath("EditHomeEmail");
		}
		
		
		clickButton("MenuSave", "id", "xpath");
		Thread.sleep(2000);
	}
	
	private void pressBackToRoot() throws Exception {
		Boolean backButtonCheck;
		int myCounter = 1;
		backButtonCheck = checkElementExistsByXpath("TopBack"); 
		//System.out.println("Back Button Check: " + backButtonCheck);
		
		while ((backButtonCheck == true) && (myCounter < 5 ))  {
			//Thread.sleep(1000);
			pressBackKey();
			Thread.sleep(2000);
			backButtonCheck = checkElementExistsByXpath("TopBack");
			//System.out.println("Back Button Check 2: " + backButtonCheck);
			myCounter++;
		}
		
	}
	
	private void backToDirectory() throws Exception {
		Thread.sleep(2000);
		if (getRunningOS().equals("mac")) {
			pressBackToRoot();
			Thread.sleep(1000);
			//clickButtonByXpath("SearchCollapse");
		} else {
			pressBackToRoot();
			Thread.sleep(1000);
			clickButtonByXpath("SearchCollapse");
			Thread.sleep(1000);
			clickButton("CollapseButton", "xpath", "xpath");
		}
		Thread.sleep(2000);
	}
	
	
	private void editUserOpen() throws Exception {
		Boolean myErrorTest;
		int myCounter = 1;
		clickButton("MenuEdit", "id", "xpath");
		waitForTextToDisappear("Downloading", 500 );
		myErrorTest = checkElementReturn("Please connect to the Internet", "text", "contValue");
		
		
		while ((myErrorTest == true) && (myCounter < 4)) {
			System.out.println("Not connected to the Internet: " + myErrorTest + " Count: "+ myCounter);
			clickButton("AlertOK", "id", "xpath");
			clickButton("MenuEdit", "id", "xpath");
			waitForTextToDisappear("Downloading", 500 );
			myErrorTest = checkElementReturn("NotConnected", "xpath", "xpath");
			myCounter++;
		}
		
	}
	
	private void adbCommand(String myCommand) throws Exception {
		
		if (myCommand.equals("stopApp")) {
			//String cmd = "adb shell am force-stop org.lds.ldstools.dev";
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(new String[] {"/Users/zmaxfield/android-sdks/platform-tools/adb", "shell", "am", "force-stop", "org.lds.ldstools.dev"});
			//Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line=buf.readLine())!=null) {
				System.out.println(line);
			}
		}
	}
	
	private String adbGetPath() throws Exception {
		String myPath = "/Users/zmaxfield/android-sdks/platform-tools/adb";
		String currentDirectory;
		currentDirectory = System.getProperty("user.dir");
		//System.out.println("Current working directory : "+currentDirectory);
		String[] parts = currentDirectory.split("/");
		String part1 = parts[0];
		String part2 = parts[1];
		String part3 = parts[2];
		myPath = "/" + part2 + "/" + part3 + "/android-sdks/platform-tools/adb" ;
		//System.out.println("NEW PATH: " + myPath);
		return myPath;
	}
	
	private Boolean adbCheckForKeyboard() throws Exception {
		Boolean returnStatus = null;
		String adbPath = adbGetPath();
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(new String[] {adbPath, "shell", "dumpsys", "window", "InputMethod", "|", "grep", "mHasSurface"});
		//Process pr = run.exec(cmd);
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while ((line=buf.readLine())!=null) {
			//System.out.println(line);
			if(line.contains("false")) {
				returnStatus = false;
			} else {
				returnStatus = true;
			}
		}
		return returnStatus;
	}
	
	private void adbPressSearch() throws Exception {
		String adbPath = adbGetPath();
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(new String[] {adbPath, "shell", "input", "keyevent", "KEYCODE_SEARCH"});
		//Process pr = run.exec(cmd);
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while ((line=buf.readLine())!=null) {
			System.out.println(line);
		}
	}
	

	
	
	private void myKeyboardClear() throws Exception {
		if (!getRunningOS().equals("mac")){
			//if (adbCheckForKeyboard() == true) {
			//	driver.hideKeyboard();
			//}
		}
	}
	
	
	private List<String> swapLastName(List<String> listToSwitch) throws Exception {
		String userSwitch;
		
		for (int myCounter = 0; myCounter < listToSwitch.size(); myCounter++) {
			String[] parts = listToSwitch.get(myCounter).split(" ");
			String part1 = parts[0];
			//part1 = part1.replace(",", "");
			String part2 = parts[1];
			userSwitch = part2 + ", " + part1;
			System.out.println("SWITCH: " + userSwitch);
			listToSwitch.set(myCounter, userSwitch);
		}
		
		return listToSwitch;

	}
	
	private void RotateOrgnazations() throws Exception {
		//Check Organizations
		openOrgnizations();
		RotateAndCheckText();
		
		//Check Bishiopric
		clickButtonByXpathTitleName("Bishopric");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//******************** High Priests Group ***************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("High Priests Group");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("High Priests Group Leadership");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Home Teaching District Supervisors");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All High Priests Group Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);

		
		//*******************************************************************
		//************************* Elders Quorum ***************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Elders Quorum");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Elders Quorum Presidency");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Instructors");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Elders Quorum Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		
		//*******************************************************************
		//************************ Relief Society ***************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Relief Society");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Relief Society Presidency");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Visiting Teaching");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Music");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Relief Society Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//************************ Young Men ********************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Young Men");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Young Men Presidency");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Priests Quorum");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Teachers Quorum");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Deacons Quorum");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Young Men Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		
		//*******************************************************************
		//************************ Young Women ******************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Young Women");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Young Women Presidency");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Laurel");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Mia Maid");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Beehive");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Young Women Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		
		//*******************************************************************
		//********************** Sunday School ******************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Sunday School");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Sunday School Presidency");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Gospel Doctrine");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Gospel Doctrine Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}

		
		pressBackKey();
		Thread.sleep(1000);

		clickButtonByXpathTitleName("Course 17");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Course 17 Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Course 16");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Course 16 Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		
		clickButtonByXpathTitleName("Course 15");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Course 15 Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Course 14");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Course 14 Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}

		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Course 13");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Course 13 Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
	
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Course 12");
		RotateAndCheckText();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Course 12 Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//********************** Primary ************************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Primary");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Primary Presidency");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("All Primary Members");
		} else {
			clickButtonByXpathTitleName("All Members");
		}
		RotateAndCheckText();
		
		if(getRunningOS().equals("mac")) {
			pressBackKey();
			Thread.sleep(1000);
		}
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//********************** Ward Missionaries **************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Ward Missionaries");
		RotateAndCheckText();

		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//********************** Other Callings *****************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Other Callings");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Young Single Adult");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Music");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		pressBackKey();
		Thread.sleep(1000);
		
		
	}
	
	
	private void RotateReports() throws Exception {
		//Check Reports
		openReports();
		RotateAndCheckText();
		
		//*******************************************************************
		//***************** Action and Interview List ***********************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Action and Interview List");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Children Approaching Baptism Age");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Unbaptized Members");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//clickButtonByXpathTitleName("Overdue Aaronic Priesthood Ordinations");
		//RotateAndCheckText();
		
		//pressBackKey();
		//Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Young Men Approaching Mission Age");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Men Who Have Not Served a Mission");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Potential Missionary Couples");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//clickButton("Bishop\'s Youth Inverviews", "value", "text");
		//RotateAndCheckText();
		//pressBackKey();
		//Thread.sleep(1000);
		
		//clickButtonByXpathTitleName("Bishopric Counselor Youth Interviews");
		//RotateAndCheckText();
		//pressBackKey();
		//Thread.sleep(1000);
		
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Young Single Adult Interviews");
		} else {
			driver.scrollToExact("Young Single Adult Interviews").click();
		}
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		pressBackKey();
		Thread.sleep(1000);
		
		
		
		//*******************************************************************
		//******************** Birthday List **** ***************************
		//*******************************************************************
		
		/*
		clickButtonByXpathTitleName("Birthday List");
		RotateAndCheckText();
		
		if (!getRunningOS().equals("mac") ){
			clickButtonByXpathTitleName("All Members");
			RotateAndCheckText();
			
			pressBackKey();
			Thread.sleep(1000);

		}
		
		pressBackKey();
		Thread.sleep(1000);
		*/
		
		//*******************************************************************
		//************************* Home Teaching ***************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Home Teaching");
		RotateAndCheckText();
		
		clickButtonByXpathTitleName("Total Visits");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Households Not Visited");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Unassigned Households");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Households");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Companionships");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		clickButtonByXpathTitleName("Potential Home Teachers");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//********************* Members Moved In ****************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Members Moved In");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);

		//*******************************************************************
		//********************* Members Moved Out ***************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Members Moved Out");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//******************* Members with Callings *************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Members with Callings");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//******************* Members without Callings **********************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Members without Callings");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//**************** Missionary Progress Record ***********************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Missionary Progress Record");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//******************** New Members **********************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("New Members");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//****************** Temple Recommend Status ************************
		//*******************************************************************
		
		clickButtonByXpathTitleName("Temple Recommend Status");
		RotateAndCheckText();
		
		pressBackKey();
		Thread.sleep(1000);
		
		//*******************************************************************
		//******************** Unit Statistics ******************************
		//*******************************************************************
		
		//clickButtonByXpathTitleName("Unit Statistics");
		//Thread.sleep(1000);
		//checkForAlertOK();
		//Thread.sleep(1000);
		//RotateAndCheckText();
		
		//pressBackKey();
		//Thread.sleep(1000);	
		
	}
	
	// **************************************************************************************
	// **************************OLD METHODS ************************************************
	// **************************************************************************************
	
	/** checkTextByXpath(String textElement, String textToCheck )
	 * Find the element by xpath using the uiMap.properties
	 * 
	 * @param textElement - Must map to the uiMap.properties
	 * @param textToCheck - String of text to check
	 */
	private void checkTextByXpath(String textElement, String textToCheck ) {
		AssertJUnit.assertEquals(driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText(),(textToCheck));	
	}
	
	
	
	/** checkTextByXpathReturn(String textElement, String textToCheck )
	 * Find the element by xpath using uiMap then return if the text if found
	 * 
	 * @param textElement - Xpath element in uiMap.properties
	 * @param textToCheck - String of text to check
	 * @return return found = 1 or not found = 0
	 */
	private int checkTextByXpathReturn(String textElement, String textToCheck ) {
		String myText;
		int myReturn = 0;
		myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();
		if (myText.equals(textToCheck) ) {
			myReturn = 1;
		}
		return myReturn;
	}
	
	
	private int checkTextContainsByXpathReturn(String textElement, String textToCheck ) {
		String myText;
		int myReturn = 0;
		myText = driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText();
		//System.out.println("Alert Found: " + myText);
		if (myText.contains(textToCheck) ) {
			myReturn = 1;
		}
		return myReturn;
	}
	
	
	private void clickButtonByNameScroll(String textElement ) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='" + textElement + "']")));
		myElement.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/** checkElementTextViewReturn(String textElement)
	 * Check to see if the element is found using the xpath of //TextView[@value=" SOME TEXT "]
	 * This is common for text elements in the app
	 * 
	 * @param textElement - text of an element
	 * @return - false if the element is not found true if it is found. 
	 */
	private Boolean checkElementTextViewReturn(String textElement) {
		Boolean myReturnStatus;
		List<WebElement> options;
		if (getRunningOS().equals("mac")) {
			options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		} else {
			options= driver.findElements(By.xpath("//*[@text='" + textElement + "']"));
		}
		
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}

	
	
	
	// **************************************************************************************
	// **************************END OLD METHODS ********************************************
	// **************************************************************************************
	
	

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) throws Exception {
		

		// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus()) {
			takeScreenShot();
			//System.out.println("Orientation: " + driver.getOrientation().value());
			//if (driver.getOrientation().value() == "landscape") {
			//	driver.rotate(ScreenOrientation.PORTRAIT);
			//}
			
		}
		
		/*
		if (getRunningOS().equals("mac")) {

		} else {
			//adbCommand("stopApp");
			driver.removeApp("org.lds.ldstools.dev");

		}
		*/

		if (getRunningOS().equals("mac")) {
			driver.quit();
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(new String[] {"/usr/bin/pkill", "-9", "instruments"});
			//Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line=buf.readLine())!=null) {
				System.out.println(line);
			}
			
		} else {
			driver.quit();
		}
		

		
		Thread.sleep(2000);
		
	}
	
	public void takeScreenShot() throws Exception {
		
	    String imagesLocation = "screenshot/";
	    new File(imagesLocation).mkdirs(); // Insure directory is there
	    
	    try {
		    File srcFile=driver.getScreenshotAs(OutputType.FILE);
		    String filename=UUID.randomUUID().toString(); 
		    System.out.println("Screenshot File: " + filename);
		    File targetFile=new File(imagesLocation + filename +".png");
		    FileUtils.copyFile(srcFile,targetFile);
	    }
		catch(Exception e){
		     System.out.println("Warning: Some Other exception");
		}

	   
	    /*
		File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			//FileUtils.copyFile(screenshotFile,new File("/Users/zmaxfield/Selenium/Screenshot/lastErrorScreenshot.png"));
			FileUtils.copyFile(screenshotFile,new File("lastErrorScreenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	
	

	@Parameters({"os"})
	@BeforeMethod(alwaysRun = true)
	public void openGuiMap(String os) {
		FileInputStream fileInput = null;
		
		//Just for testing
		//System.out.println("GUI Map OS: " + os );
		
		if (os.equals("android")) {
			File file = new File("ConfigFiles/uiMap.properties");
			fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if (os.equals("ios")) {
			File file = new File("ConfigFiles/uiMapiOS.properties");
			fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		
		this.prop = new Properties();
		
		try {
			prop.load(fileInput);
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	//Need this class to get the touch stuff to work with Appium - Android
	public class AppiumSwipeableDriver extends AppiumDriver implements HasTouchScreen{
		 public RemoteTouchScreen touch;
		 public AppiumSwipeableDriver(URL URL, Capabilities Cap) {
			 super(URL, Cap);
			 touch = new RemoteTouchScreen(getExecuteMethod());
		}

		 @Override
		 public TouchScreen getTouch() {
			 return touch;
		 }

		@Override
		public MobileElement scrollTo(String arg0) {
			return null;
		}

		@Override
		public MobileElement scrollToExact(String arg0) {
			return null;
		}
	}
	
*/
	
	//Retry Test needed so the system will retry a failed test
    public class Retry implements TestRule {
	        private int retryCount;
	
	    public Retry(int retryCount) {
	        this.retryCount = retryCount;
	    }
	
	    public Statement apply(Statement base, Description description) {
	        return statement(base, description);
	    }
	
	    private Statement statement(final Statement base, final Description description) {
	        return new Statement() {
	            @Override
	            public void evaluate() throws Throwable {
	                Throwable caughtThrowable = null;
	
	                // implement retry logic here
	                for (int i = 0; i < retryCount; i++) {
	                    try {
	                        base.evaluate();
	                        return;
	                    } catch (Throwable t) {
	                        caughtThrowable = t;
	                        System.err.println(description.getDisplayName() + ": run " + (i+1) + " failed");
	                    }
	                }
	                System.err.println(description.getDisplayName() + ": giving up after " + retryCount + " failures");
	                throw caughtThrowable;
	            }
	        };
	    }
	    
    }
}
