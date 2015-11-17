package LDSTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Dictionary;
//import java.util.HashMap;
import java.util.List;
//import java.net.URL;
import java.util.Properties;




















import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.selendroid.SelendroidCapabilities;
//import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidKeys;
//import io.selendroid.exceptions.NoSuchElementException;





















import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
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
	AppiumSwipeableDriver driver;
	TouchActions touch;

	
	/** Setup Appium driver
	 * Note the difference between the classpathRoot on Windows vs Mac
	 * 
	 * @throws Exception
	 */
	@Parameters({"os", "fileName"})
	@BeforeMethod(alwaysRun = true)
	public void setUp(String os, String fileName) throws Exception {
		System.out.println("OS: " + os );
		System.out.println("File Name: " + fileName);
		
		
		//Android Setup
		if (os.equals("android")) {
			 // set up appium
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        //File appDir = new File(classpathRoot, "..\\..\\..\\..\\Selenium");
	        //MAC Path
	        File appDir = new File(classpathRoot, "../../../Selenium");
	        File app = new File(appDir, "ldstools-release-20151102-1936.apk");
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	        capabilities.setCapability("platformName", "Android");
	        //Samsung Galaxy Tab
	        //capabilities.setCapability("deviceName","41031b0b89e93163");
	        //HTC Nexus 9
	        //capabilities.setCapability("deviceName","HT4ASJT02851");
	        //Nexus 5
	        //capabilities.setCapability("deviceName","03aadbed215c8e5f");
	        //Samsung Tab
	        //capabilities.setCapability("deviceName","42f7920b622d9fa3");
	        // Android Emulator
	        //capabilities.setCapability("deviceName","Android Emulator");
	        //Samsung Galaxy Note 4
	        //capabilities.setCapability("deviceName","751bc6f2");
	        //Motorola 
	        capabilities.setCapability("deviceName","ZX1D327RHD");
	        
	        
	        
	        capabilities.setCapability("automationName","selendroid");
	        capabilities.setCapability("newCommandTimeout","600");
	        capabilities.setCapability("platformVersion", "5.1");
	        capabilities.setCapability("app", app.getAbsolutePath());
	        capabilities.setCapability("appPackage", "org.lds.ldstools");
	        //capabilities.setCapability("appActivity", "org.lds.ldstools.ui.StartupActivity");
	        //driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	        driver = new AppiumSwipeableDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
	        touch = new TouchActions(driver);
	        //System.out.println("Setup Complete!");
		}
		
		//Setup for iOS
		if (os.equals("ios")) {
	        // set up appium
	        File classpathRoot = new File(System.getProperty("user.dir"));
	        //File appDir = new File(classpathRoot, "..\\..\\..\\..\\Selenium");
	        //MAC Path
	        File appDir = new File(classpathRoot, "../../../Selenium");
	        File app = new File(appDir, "LDS Tools.app");
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("platformName", "iOS");
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
	        capabilities.setCapability(CapabilityType.VERSION, "9.0");
	        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
	        capabilities.setCapability("platformVersion", "9.1");
	        //capabilities.setCapability("deviceName","iPhone 5");
	        capabilities.setCapability("deviceName","iPhone 6");

	        capabilities.setCapability("automationName","appium");
	        capabilities.setCapability("newCommandTimeout","600");

	        capabilities.setCapability("app", app.getAbsolutePath());
	        capabilities.setCapability("appPackage", "org.lds.ldstools.dev");
	        capabilities.setCapability("sendKeysStrategy", "setValue");
	        
	        
	        //capabilities.setCapability("appActivity", "org.lds.ldstools.ui.StartupActivity");
	        //driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	        driver = new AppiumSwipeableDriver(new URL("http://127.0.0.1:4445/wd/hub"),capabilities);
	        touch = new TouchActions(driver);
		}
       
    }	

	
    
	@Parameters({"os"})
	@Test (groups= {"jft"})
	public void simpleTest(String os) throws Exception {
		Thread.sleep(4000);
		//justForTesting();	

		//LeaderNonBishopric("LDSTools17", "High Priest Group", os);
		//under18HeadofHouse(os);	
		//bishopricCounselorAndWardClerk(os);
		//bishopMemberOfSeparateStake(os);	
		//editCurrentUser(os);	
		editCurrentUserCancel(os);
		//editOtherUser(os);
		
		//editOtherUserInvalidPhone(os);	
		//editOtherUserInvalidEmail(os);	
		
		//editVisibility(os);	
		
		//editVisibiltyPersonal(os);
		//editVisibiltyHousehold(os);
		//invalidLoginCheck(os);	

	
		
		
		//Header Check
		//ChristieWhiting();
		//CliffHigby();
		//KevinPalmer();
		//PatriarchOtherWards();
		//TravisLyman();
		//ElderKacher();
		//TerryBallard(); //Check to see Tim and Jessica Beck
		//AdminUnit(); //Not working in 2.5.0
		//WardStakeCouncilor();

	}
	
	@Parameters({"os"})
	@Test
	public void justForTesting(String os) throws Exception {

		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//This works
		checkDirectoryUser(true, true, true, true, true, true);
		
		
		//Check Drawer Items - If leader there should be a Reports item
		checkDrawerItems(true);

		//Thread.sleep(1000);	
		//Check various callings - all users should be able to access this information
		checkCallings();

		//Thread.sleep(1000);
		//Check Missionary drawer items - all user access
		checkMissionary();

		//Thread.sleep(1000);
		//Check the reports - leadership only - true for bishopric rights, false for leaders and remove
		//checkReports for non-leaders
		checkReports(true, true);
		
		//Thread.sleep(1000);
		//Check Home Teaching - Visiting Teaching
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		checkHTVTBasic("Bishopric");
		
		Thread.sleep(1000);
		//Check Home Teaching - Visiting Teaching Household - Sisters and Filters
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		checkHTVTHouseholds("Bishopric");
		
			

	}
		

	
	@Parameters({"os"})
	@Test (groups= {"smoke", "high priest"})
	public void HighPriestsGroupLeader(String os) throws Exception {
		LeaderNonBishopric("LDSTools16", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"})
	public void HighPriestsGroupFirstAssistant(String os) throws Exception {
		LeaderNonBishopric("LDSTools17", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"})
	public void HighPriestsGroupSecondAssistant(String os) throws Exception {
		LeaderNonBishopric("LDSTools18", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"})
	public void HighPriestsGroupSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools19", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"high priest"})
	public void HighPriestsGroupAssistantSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools20", "High Priest Group", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "elders quorum"})
	public void EldersQuorumPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools21", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"})
	public void EldersQuorumFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools22", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"})
	public void EldersQuorumSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools23", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"})
	public void EldersQuorumSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools24", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"elders quorum"})
	public void EldersQuorumAssistantSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools25", "Elders Quorum Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "relief society"})
	public void ReliefSocietyPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools26", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"})
	public void ReliefSocietyFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools27", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"})
	public void ReliefSocietySecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools28", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"})
	public void ReliefSocietySecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools29", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"relief society"})
	public void ReliefSocietyAssistantSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools30", "Relief Society Pres", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "young men"})
	public void YoungMenPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools31", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"})
	public void YoungMenFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools32", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"})
	public void YoungMenSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools33", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young men"})
	public void YoungMenSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools34", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "young women"})
	public void YoungWomenPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools35", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"})
	public void YoungWomenFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools36", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"})
	public void YoungWomenSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools37", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"young women"})
	public void YoungWomenSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools38", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "sunday school"})
	public void SundaySchoolPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools39", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"sunday school"})
	public void SundaySchoolFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools40", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"sunday school"})
	public void SundaySchoolSecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools41", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"sunday school"})
	public void SundaySchoolSecretary(String os) throws Exception {
		LeaderNonBishopric("LDSTools42", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "primary"})
	public void PrimaryPresident(String os) throws Exception {
		LeaderNonBishopric("LDSTools43", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"})
	public void PrimaryFirstCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools44", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"})
	public void PrimarySecondCounselor(String os) throws Exception {
		LeaderNonBishopric("LDSTools45", "Ward Council", os);
	}
	
	@Parameters({"os"})
	@Test (groups= {"primary"})
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
	@Test (groups= {"noCalling"})
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

		pageSource = getSourceOfPage();
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkNoCaseList("Tools, LDS6", pageSource));

		Assert.assertTrue(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource));
		Assert.assertTrue(checkNoCaseList("888-0028-7066", pageSource));
		
		pressBackKey();
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		checkDirectoryUser(false, false, false, false, false, false );
		
		
	}
	
	/** bishopricCounselorAndWardClerk()
	 * This will test a user that is a member of the Bishopric and a Ward Clerk
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"smoke", "bishopric"})
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
	
		Thread.sleep(1000);
		
		//Check the reports - leadership only
		checkReports(true, false);
		
		checkHTVTBasic("Bishopric");
		checkHTVTHouseholds("Bishopric");
		

	}
	
	/** bishopMemberOfSeparateStake()
	 * Bishop that is a member of a separate stake
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"bishopric"})
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
		
		pageSource = getSourceOfPage();	
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkNoCaseList("Tools, LDS2", pageSource));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource));
		Assert.assertTrue(checkNoCaseList("888-0028-7023", pageSource));
		Assert.assertTrue(checkNoCaseList("RECORD NUMBER", pageSource));
		if (getRunningOS().equals("mac")){
			Assert.assertTrue(checkNoCaseList("Jan 1, 1980 (35)", pageSource));
		} else {
			Assert.assertTrue(checkNoCaseList("January 1, 1980 (35)", pageSource));
		}
		Assert.assertTrue(checkNoCaseList("BIRTH DATE", pageSource));
		
		//Check Ordinances
		clickButtonByXpathTitleName("Ordinances");
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Baptism", pageSource));
		Assert.assertTrue(checkNoCaseList("May 5, 2005", pageSource));
		Assert.assertTrue(checkNoCaseList("Confirmation", pageSource));
		Assert.assertTrue(checkNoCaseList("May 5, 2005", pageSource));
		pressBackKey();
		

		/* Not sure why this isn't working. 
		//Check Other Information
		clickButtonByXpathTitleName("Other Information");
		Thread.sleep(3000);
		displayAllTextViewElements();
		
		Assert.assertTrue(checkElementTextViewReturn("Gender"));
		Assert.assertTrue(checkElementTextViewReturn("Male"));
		Assert.assertTrue(checkElementTextViewReturn("Birth Country"));
		Assert.assertTrue(checkElementTextViewReturn("United States"));
		pressBackKey();
		*/
		
		pressBackKey();
		//Collapse the search 
		Thread.sleep(1000);
		clickButtonByXpath("SearchCollapse");
		
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
		
		//Check the reports - leadership only
		checkReports(true, true);
		
		checkHTVTBasic("Bishopric");
		checkHTVTHouseholds("Bishopric");
		
		
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
	
	/** editCurrentUser()
	 * Login as LDSTools100 and edit own information
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"smoke", "editSetings"})
	public void editCurrentUser(String os) throws Exception {
		String pageSource;
		//Edit own information
		syncLogIn("LDSTools44", "password1", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		searchForUser("Tools, LDS44");
		
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS44"));
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource));
		
		pressBackKey();
		Thread.sleep(2000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS44");
		
		//Check the users name, address membership number etc...
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Tools, LDS44", pageSource));
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource));
		
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertFalse(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertFalse(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertFalse(checkNoCaseList("home@nospam.com", pageSource));
	}
	
	@Parameters({"os"})
	@Test (groups= {"editSetings"})
	public void editCurrentUserCancel(String os) throws Exception {
		String pageSource;
		//Edit own information
		syncLogIn("LDSTools44", "password1", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		searchForUser("Tools, LDS44");
		
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS44"));
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		
		//Need MenuCancel
		clickButtonByXpath("MenuCancel");
		checkForAlertOK();
		
		Thread.sleep(3000);
		
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertFalse(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertFalse(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertFalse(checkNoCaseList("home@nospam.com", pageSource));
	}
	
	/** editOtherUser()
	 * Edit a user that you are not logged in as. 
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"editSettings"})
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
		
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		
		
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource));
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		
		//This is just for testing
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		//Check the users name, address membership number etc...
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Tools, LDS41", pageSource));
		Assert.assertTrue(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertTrue(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertTrue(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertTrue(checkNoCaseList("home@nospam.com", pageSource));
		
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");
		Thread.sleep(3000);
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("1(801)240-0104", pageSource));
		Assert.assertFalse(checkNoCaseList("(801) 867-5309", pageSource));	
		Assert.assertFalse(checkNoCaseList("personal@nospam.com", pageSource));
		Assert.assertFalse(checkNoCaseList("home@nospam.com", pageSource));
				
	}
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"})
	public void editOtherUserInvalidPhone(String os) throws Exception {
		String pageSource;
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		searchForUser("Tools, LDS41");
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		
		
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		sendTextbyXpath("EditPersonalPhone", "######00000000000*****");
		sendTextbyXpath("EditHomePhone", "878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("######00000000000*****", pageSource));
		Assert.assertFalse(checkNoCaseList("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542", pageSource));	

		
		pressBackKey();
		
		//Collapse the search 
		Thread.sleep(1000);
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		//Not sure why this isn't showing up. 
		//Check the users name, address membership number etc...
		/*
		Assert.assertTrue(checkElementTextViewReturn("######00000000000*****"));
		Assert.assertTrue(checkElementTextViewReturn("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542"));	

		
		clickButtonByXpath("MenuEdit");
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");


		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		*/
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("######00000000000*****", pageSource));
		Assert.assertFalse(checkNoCaseList("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542", pageSource));	

	}
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"})
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
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		
		Thread.sleep(2000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		sendTextbyXpath("EditPersonalEmail", "thisisaninvalidemailaddress");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditPersonalEmail");
		
		
		sendTextbyXpath("EditHomeEmail", "thisisaninvalidemailaddress");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditHomeEmail");
		
		sendTextbyXpath("EditPersonalEmail", "!@#$%^&*()_+-=[]{}|");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditPersonalEmail");
		
		sendTextbyXpath("EditHomeEmail", "!@#$%^&*()_+-=[]{}|");
		clickButtonByXpath("MenuSave");
		Assert.assertTrue(checkElementTextViewReturnContains("valid email"));
		clickButtonByXpath("AlertOK");
		clearTextFieldXpath("EditHomeEmail");
		
		
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("thisisaninvalidemailaddress", pageSource));
		Assert.assertFalse(checkNoCaseList("!@#$%^&*()_+-=[]{}|", pageSource));	

		
		pressBackKey();
		Thread.sleep(2000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(2000);
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS41");
		
		pageSource = getSourceOfPage();
		Assert.assertFalse(checkNoCaseList("thisisaninvalidemailaddress", pageSource));
		Assert.assertFalse(checkNoCaseList("!@#$%^&*()_+-=[]{}|", pageSource));	
	}
	
	@Parameters({"os"})
	@Test (groups= {"smoke", "editSettings"})
	public void editVisibility(String os) throws Exception {
		boolean testForElement;
		//int myCheck = 0;
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		searchForUser("Tools, LDS5");
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		//This will reset the visibility back to Stake
		resetVisibility();
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("HouseholdVisibilityLimit");
			Thread.sleep(2000);
			clickButtonByXpathTitleNameContains("Private");
			Thread.sleep(1000);
		} else {
			scrollDown("Stake Visibility", -1000 );
			Thread.sleep(2000);
			clickButtonByXpathTitleName("Private—Leadership Only");
			Thread.sleep(1000);
		}
		

		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
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
		clickButtonByXpath("SearchCollapse");
		
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
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		
		Thread.sleep(3000);
		clickButtonByXpath("AlertOK");	
		
		
		Thread.sleep(1000);
		resetVisibility();
		//scrollDown("Private—Leadership Only", -1000 );
		//clickButtonByXpathTitleName("Stake Visibility");
		//Thread.sleep(1000);
		
		
		
		clickButtonByXpath("MenuSave");
		//Thread.sleep(1000);
		//clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		//pressBackKey();
		pressBackKey();
		
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
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
	@Test (groups= {"editSettings"})
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
		
		//Check user name and open the Edit menu
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		//This will reset the visibility back to Stake (just in case something went wrong)
		resetVisibility();

		
		//Set the Personal Settings to Private-Leadership Only
		clickButtonByXpath("PersonalSettings");
		clickButtonByXpath("EditAllVisibility");
		Thread.sleep(2000);
		clickButtonByXpath("PrivateVisibility");
		//clickButtonByXpathTitleNameContains("Private");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
		//Log out 
		drawerSignOut();

		
		//Login with LDSTools6 to check the Personal settings
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
	
		searchForUser("Tools, LDS5");
		
		//Check the users name Phone and email
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource));
		Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource));

		Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource));
		Assert.assertFalse(checkNoCaseList("1224589900887", pageSource));
		Assert.assertFalse(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("5551234555", pageSource));
		//This is showing up as household email or household phone
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
		Assert.assertFalse(checkNoCaseList("Z@z.com", pageSource));
		Assert.assertFalse(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("test@test.com", pageSource));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
		
		Thread.sleep(3000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		drawerSignOut();

		
		
		//Change the settings back to default
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		searchForUser("Tools, LDS5");
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		
		Thread.sleep(3000);
		clickButtonByXpath("PersonalSettings");
		Thread.sleep(2000);
		clickButtonByXpath("EditAllVisibility");
		Thread.sleep(2000);
		//clickButtonByXpathTitleName("Stake Visibility");
		clickButtonByXpath("StakeVisibility");
		
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		//pressBackKey();
		pressBackKey();
		
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
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
		
		//Check the users name, address membership number etc...
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource));
		Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource));

		Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource));
		Assert.assertTrue(checkNoCaseList("1224589900887", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("5551234555", pageSource));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
		Assert.assertTrue(checkNoCaseList("Z@z.com", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("test@test.com", pageSource));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
	}
	
	@Parameters({"os"})
	@Test (groups= {"editSettings"})
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
		
		//Check user name and open the Edit menu
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		Thread.sleep(2000);
		
		//This will reset the visibility back to Stake (just in case something went wrong)
		resetVisibility();
		
		//Set the Personal Settings to Private-Leadership Only
		clickButtonByXpath("HouseholdSettings");
		clickButtonByXpath("EditAllVisibility");
		Thread.sleep(2000);
		clickButtonByXpath("PrivateVisibility");
		//clickButtonByXpathTitleName("Private—Leadership Only");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
		//Log out 
		drawerSignOut();
		
		//Login with LDSTools6 to check the Personal settings
		syncLogIn("LDSTools6", "toolstester", "UAT", os );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
	
		searchForUser("Tools, LDS5");
		
		//Check the users name Phone and email
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource));
		Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource));

		Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource));
		Assert.assertTrue(checkNoCaseList("1224589900887", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertFalse(checkNoCaseList("5551234555", pageSource));
		//Assert.assertFalse(checkNoCaseList("HOUSEHOLD", pageSource));
		Assert.assertTrue(checkNoCaseList("Z@z.com", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertFalse(checkNoCaseList("test@test.com", pageSource));
		//Assert.assertFalse(checkNoCaseList("HOUSEHOLD", pageSource));
		
		Thread.sleep(3000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		drawerSignOut();
		
		
		//Change the settings back to default
		syncLogIn("LDSTools5", "toolstester", "UAT", os );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		searchForUser("Tools, LDS5");
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		Thread.sleep(1000);
		clickButtonByXpath("MenuEdit");
		
		Thread.sleep(3000);
		clickButtonByXpath("HouseholdSettings");
		clickButtonByXpath("EditAllVisibility");
		Thread.sleep(2000);
		//clickButtonByXpathTitleName("Stake Visibility");
		clickButtonByXpath("StakeVisibility");
		Thread.sleep(3000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		//pressBackKey();
		pressBackKey();
		
		Thread.sleep(1000);
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		Thread.sleep(1000);
		
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
		
		//Check the users name, address membership number etc...
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("LDS5 Tools", pageSource));
		Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource));

		Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource));
		Assert.assertTrue(checkNoCaseList("1224589900887", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("5551234555", pageSource));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
		Assert.assertTrue(checkNoCaseList("Z@z.com", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("test@test.com", pageSource));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
	}
	
	
	
	/** invalidLoginCheck()
	 * Test invalid logins to LDS Tools
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"smoke"})
	public void invalidLoginCheck(String os) throws Exception {
		String errorMessage;
		
		if (getRunningOS().equals("mac")) {
			errorMessage = "Sign-In Failed";
		} else {
			errorMessage = "Incorrect username or password";
		}
		
		//Invalid login test
		syncLogIn("LDSTools2", "<login>", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn(errorMessage));
		clickButtonByXpath("AlertOK");	
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		Thread.sleep(2000);
		syncLogIn("LDSTools2", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn(errorMessage));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn(errorMessage));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("LDSTools2", "test|test|test$$$$test|||||||test", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn(errorMessage));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("zmaxfield", "%%%test%%%% & ||||||| select * from household;", "Production", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn(errorMessage));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		/*
		syncLogIn("", "", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account (UAT)"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("LDSTools2", "", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account (UAT)"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("", "toolstester", "UAT", os );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account (UAT)"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		*/
	}
	
	
	public void ChristieWhiting() throws Exception {
		loginProxyData("3446450099",
				"/7u189715/5u511293/",
				"p1175/1151u1000047/:p143/7u189715/5u511293/",
				"Proxy - Test", "ChristieWhiting");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		
		Thread.sleep(2000);
		
		checkAllWardDirectories();
		
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerHELP");
		//Thread.sleep(2000);
		//clickButtonByXpath("About");
		//Assert.assertTrue(checkElementTextViewReturnContains("ChristieWhiting"));
		
		
		drawerSignOut();
	
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void CliffHigby(String os) throws Exception {
		//List<String> StakeWard = new ArrayList<String>();
		loginProxyData("295740465",
				"/7u191/5u504505/",
				"p428/467u376892/28u381772/:p1711/59u1004603/22u388300/:p1788/467u376892/28u381772/:p1680/32u1326376/:p789/8u1006967/1u563013/:p1887/14u1004816/467u376892/",
				"Proxy - Test", "CliffHigby");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		checkAllWardDirectories();
		
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerHELP");
		//Thread.sleep(2000);
		//clickButtonByXpath("About");
		//Assert.assertTrue(checkElementTextViewReturnContains("CliffHigby"));

		drawerSignOut();
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void KevinPalmer(String os) throws Exception {
		loginProxyData("3182767230",
				"/7u50482/5u511846/",
				"p222/7u50482/5u511846/:p39/3u2019809/1u790206/:p2/5u511846/1u790206/",
				"Proxy - Test", "KevinPalmer");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		checkAllWardDirectories();
		Thread.sleep(2000);

		drawerSignOut();
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void PatriarchOtherWards(String os) throws Exception {
		loginProxyData("3182767230",
				"/7u56030/5u524735/",
				"p13/5u524735/",
				"Proxy - Test", "TestPatriarch");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for a user that has children
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Faapili, Muipu");
		
		//Select the user
		//Check that the children are visible
		clickButtonByXpathTitleName("Faapili, Muipu & Baby");
		clickLastTextViewRoboReturn("Faapili, Muipu");
		Thread.sleep(1000);
		Assert.assertTrue(checkElementTextViewReturn("Muipu Faapili (32)"));
		Assert.assertTrue(checkElementTextViewReturn("Baby Faapili (35)"));
		Assert.assertTrue(checkElementTextViewReturn("Muipu Jnr Faapili (12)"));
		Assert.assertTrue(checkElementTextViewReturn("Tautinoga Faapili (10)"));
		Assert.assertTrue(checkElementTextViewReturn("Mapusaga Faapili (4)"));
		clickButtonByXpath("Back");
		clickButtonByXpath("SearchCollapse");
		//pressBackKey();
		
		//Change to another Ward
		//Check to see that the children are visible
		clickButtonByXpath("SpinnerNav");
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Fagamalo 2nd Ward");
		
		
		//Search for a user that has children
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Alofa, Pasi");
		
		//Select the user
		//Check that the children are visible
		clickButtonByXpathTitleName("Alofa, Pasi & Rowena");
		clickLastTextViewRoboReturn("Alofa, Pasi");
		Thread.sleep(1000);
		Assert.assertTrue(checkElementTextViewReturn("Pasi Alofa (32)"));
		Assert.assertTrue(checkElementTextViewReturn("Rowena Alofa (26)"));
		Assert.assertTrue(checkElementTextViewReturn("Rozarnah Alofa (4)"));
		Assert.assertTrue(checkElementTextViewReturn("Leativaosalafai Shaleen Alofa (2)"));
		//Assert.assertTrue(checkElementTextViewReturn("Pioneer Aumoto"));
		clickButtonByXpath("Back");
		Thread.sleep(1000);
		clickButtonByXpath("SearchCollapse");
		

		drawerSignOut();
	}
	
	
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void TravisLyman(String os) throws Exception {
		loginProxyData("309310780",
				"/7u1161164/5u427144/",
				"p222/7u170690/5u506508/",
				"Proxy - Test", "TravisLyman");
		//Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Thread.sleep(2000);
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Alcorn, Sarah"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		drawerSignOut();
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void ElderKacher(String os) throws Exception {
		loginProxyData("2178152043",
				"/7u253707/5u516244/",
				"p32/21u418951/:p32/1u563013/:p56370/1016u1004840/1u563013/",
				"Proxy - Test", "ElderKacher");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		Thread.sleep(2000);
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Anderson, Susan"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		

		drawerSignOut();
	}
	
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void TerryBallard(String os) throws Exception {
		loginProxyData("20904102494",
				"/7u25941/5u515124/",
				"p158/7u25941/5u515124/",
				"Proxy - Test", "TerryBallard");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ager, Brian"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Beck, Tim");
		Thread.sleep(2000);

		clickLastTextViewRoboReturnContains("Beck, Tim & Jessica");
		Thread.sleep(1000);
		clickLastTextViewRoboReturnContains("Beck, Tim");
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Tim Beck (41)"));
		Assert.assertTrue(checkElementTextViewReturn("Jessica Beck (38)"));
		
		Thread.sleep(1000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		

		drawerSignOut();
	}
	
	/*
	@Parameters({"os"})
	@Test (groups= {"header"})
	public void AdminUnit(String os) throws Exception {
		loginProxyData("7157852120",
				"/32u9990011/16u244449/",
				"",
				"Proxy - Test", "AdminUnit");
		
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
	@Test (groups= {"header"})
	public void WardStakeCouncilor(String os) throws Exception {
		loginProxyData("8999999998916734",
				"/7u56030/5u524735/",
				"p135/7u56030/5u524735/:p94/5u524735/",
				"Proxy - Test", "WardStakeCounilor");
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		//Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEleven, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		

		drawerSignOut();
	}
	
	
	/**loginCheck()
	 * Go through All LDSTools users to make sure they can login
	 * 
	 * @throws Exception
	 */
	@Parameters({"os"})
	@Test (groups= {"old"})
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
				clickButtonByXpath("SignOutOK");
			}
		}
	}
	
	//**************************************************************
	//**************** Start of Methods ****************************
	//**************************************************************
	
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
	

	
	
	/** checkTextByID(String textElement, String textToCheck )
	 * Find the element by ID using uiMap
	 * 
	 * @param textElement - Must map to the uiMap
	 * @param textToCheck - String of text to check
	 */
	private void checkTextByID(String textElement, String textToCheck ) {
		AssertJUnit.assertEquals(driver.findElement(By.id(this.prop.getProperty(textElement))).getText(),(textToCheck));
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
		List<WebElement> options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
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
		//List<WebElement> options= driver.findElements(By.xpath("//TextView[contains(@value,'" + textElement + "')]"));
		List<WebElement> options= driver.findElements(By.xpath("//*[contains(@value,'" + textElement + "')]"));
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
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
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
	
	
	private boolean checkNoCaseList(String textToCheck, String pageSource){
		Document doc = Jsoup.parse(pageSource);
		Elements myTest = doc.getAllElements();
		String foundText;
		String myOs;
		textToCheck = textToCheck.toLowerCase();
		myOs = getRunningOS();
		//System.out.println("My OS: " + myOs);
		if (myOs.equals("mac")){
			for (Element myElement : myTest ) {
				//System.out.println(myElement.attributes().get("name"));
				foundText = myElement.attributes().get("name");
				foundText = foundText.toLowerCase();
				//System.out.println("******************************");
				//System.out.println("Found Text:" + foundText);
				//System.out.println("Text To Check: " + textToCheck);
				//System.out.println("******************************");
				if (foundText.equals(textToCheck)) {
					return true;
				}
			}

		} else {
			for (Element myElement : myTest ) {
				//System.out.println(myElement.attributes().get("name"));
				if (myElement.attributes().get("shown").equals("true")) {
					foundText = myElement.attributes().get("value");
					foundText = foundText.toLowerCase();
					//System.out.println("******************************");
					//System.out.println("Found Text:" + foundText);
					//System.out.println("Text To Check: " + textToCheck);
					//System.out.println("******************************");
					if (foundText.equals(textToCheck)) {
						return true;
					}
				}
			}
		}
	    return false;
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
		return myOs;
	}
	
	private String getSourceOfPage() {
		String myString;
		myString = driver.getPageSource();
		return myString;
	}
	
	
	private List<String> getAllText() {
		List<WebElement> options= driver.findElements(By.xpath("//*"));
		List<String> allText = new ArrayList<String>();
		for (int i = 0 ; i < options.size(); i++ ) {
			//System.out.println(options.get(i).getText());
			allText.add(i, options.get(i).getText());
		}

		return allText;
	}
	

	
	/** clickLastTextViewRoboReturn(String textElement)
	 * This will match the last element found 
	 * 
	 * @param textElement - text of an element
	 */
	private void clickLastTextViewRoboReturn(String textElement) {
		int myCounter;
		//displayAllTextViewElements(textElement);
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@id='text1'][@value='" + textElement + "']"));
		List<WebElement> options= driver.findElements(By.xpath("//*[@value='" + textElement + "']"));
		myCounter = options.size() - 1;
		options.get(myCounter).click();
	
	}
	
	private void clickLastTextViewRoboReturnContains(String textElement) {
		int myCounter;
		//List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[contains(@value, '" + textElement + "')]"));
		List<WebElement> options= driver.findElements(By.xpath("//*[contains(@value, '" + textElement + "')]"));
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
		WebElement myElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='" + textElement + "']")));
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
	}
	
	
	private void clearTextFieldXpath(String textElement) {
		WebElement myElement = driver.findElement(By.xpath(this.prop.getProperty(textElement)));
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
	private void scrollDown(String textElement, int distanceMove){
		int myCheck;
		int myCounter = 0;
		textElement = "//*[@value='" + textElement + "']";
		//WebDriverWait wait = new WebDriverWait(driver, 20);

		//Scroll down if element is not found.
		myCheck = checkElementXpathReturn(textElement);
		//System.out.println("Before While loop - Check: " + myCheck);
		while ((myCheck == 0) && (myCounter < 8 )) {
			scrollDownDistance(distanceMove);
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
	private void scrollDownDistance(int scrollDistance ){
		TouchActions actions = new TouchActions(driver);
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
		actions.flick(driver.findElement(By.id("title")), 0, scrollDistance, 100);
		
		//actions.flick(0, -1000);
		//actions.scroll(0, scrollDistance);
		

		
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
		WebElement myElement = driver.findElement(By.xpath("//*[contains(@value, '" + textElement + "')]"));
		
		//This was doing a longpress on the wrong element
		//TouchActions longPress = new TouchActions(driver).longPress(myElement);
		//longPress.perform();
		TouchActions actions=new TouchActions(driver);
		Point p=myElement.getLocation();
		//System.out.println("X: " + p.x + "Y: " + p.y);
		actions.down(p.x, p.y);
		actions.pause(2000);
		actions.up(p.x, p.y);
		actions.perform();
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
		if (myOs.equals("android")){
			new Actions(driver).sendKeys(SelendroidKeys.BACK).perform();
			Thread.sleep(1000);
		} else {
			clickButtonByXpath("TopBack");
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
	 * @param chooseNetwork - Network to use "Production", "UAT", "Proxy - UAT", "Proxy - Test"
	 * @throws Exception
	 */
	private void syncLogIn(String loginName, String loginPassword, String chooseNetwork, String os )  throws Exception {
		//If the login is using any of the test networks we need to change it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy - Test"
		if (os.equals("android")) {
			if (!chooseNetwork.equals("Production")) {
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				clickButtonByXpath("Menu");
				clickButtonByXpathTitleName("Settings");
				//Thread.sleep(1000);
				//scrollDown("Sign Out", 40 );
				Thread.sleep(2000);
				scrollDown("Network Environment", -1000 );
				//Thread.sleep(2000);
				clickButtonByXpathTitleName(chooseNetwork);
				clickButtonByXpath("Back");
				Thread.sleep(5000);
			}

			sendTextbyXpath("LoginUsername", loginName);
			sendTextbyXpath("LoginPassword", loginPassword);
			clickButtonByXpath("SignInButton");
			Thread.sleep(4000);
			waitForTextToDisappear("SyncText", 500 );
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

				
				clickButtonByXpath("Environment");
				clickButtonByXpath("Stage");
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
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy - Test"
		if (getRunningOS().equals("android")) {
			if (!chooseNetwork.equals("Production")) {
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				Thread.sleep(1000);
				longPressByTextView("Sign in to your LDS Account");
				//Thread.sleep(1000);
				clickButtonByXpath("Menu");
				clickButtonByXpathTitleName("Settings");
				//Thread.sleep(1000);
				//scrollDown("Sign Out", 40 );
				Thread.sleep(2000);
				scrollDown("Network Environment", -1000 );
				//Thread.sleep(2000);
				clickButtonByXpathTitleName(chooseNetwork);
				Thread.sleep(2000);
				scrollDown("px_i", -1000 );
				Thread.sleep(2000);
				sendTextbyXpath("AlertEditText", IndividualId);
				clickButtonByXpath("AlertOK");
				Thread.sleep(2000);
				scrollDown("px_u", -1000 );
				Thread.sleep(2000);
				sendTextbyXpath("AlertEditText", units);
				clickButtonByXpath("AlertOK");
				Thread.sleep(2000);
				scrollDown("px_p", -1000 );
				Thread.sleep(2000);
				sendTextbyXpath("AlertEditText", positions);
				clickButtonByXpath("AlertOK");
				clickButtonByXpath("Back");
				Thread.sleep(5000);
				
				sendTextbyXpath("LoginUsername", userName);
				sendTextbyXpath("LoginPassword", "toolstester");
				clickButtonByXpath("SignInButton");
				Thread.sleep(4000);
				waitForTextToDisappear("SyncText", 500 );
				Thread.sleep(2000);
			}
		}
		if (getRunningOS().equals("mac")) {
			userName = "proxyt";
			chooseNetwork = "P-TEST";
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

				
				clickButtonByXpath("Environment");
				
				
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
				sendTextbyXpath2("LoginUsername", "proxyt" );
				sendTextbyXpath2("LoginPassword", "toolstester");
				
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
		int myCheck;
		boolean elementCheck;
		
		//Check to see if we are getting a warning
		myCheck = checkTextByXpathReturn("AlertMessage", "Warning");
		if (myCheck == 1) {
			System.out.println("Found Warning: " + myCheck);
			clickButtonByXpath("OK");
		}
		
		
		//If this is a non-leader account the PIN message will be different
		//myCheck = checkTextByXpathReturn("AlertMessage", "Please create a PIN to protect sensitive data available to leaders.");
		myCheck = alertCheck();
		if ((myCheck == 1) || (nonLeaderPin)){
			if (myCheck == 1) {
				clickButtonByXpath("AlertOK");
			} else {
				elementCheck = checkElementExistsByXpath("Yes");
				if (elementCheck == true) {
					clickButtonByXpath("Yes");
				} else {
					clickButtonByXpath("AlertOK");
				}
				Thread.sleep(1000);
			}

			Thread.sleep(2000);
			
			//Check for a warning
			elementCheck = checkElementExistsByXpath("AlertMessage");
			if (elementCheck == true) {
				clickButtonByXpath("OK");
			}
			
			//checkTextByXpath("PinTitle", "Choose your PIN");
			clickButtonByXpath("PinKey" + digit1);
			clickButtonByXpath("PinKey" + digit2);
			clickButtonByXpath("PinKey" + digit3);
			clickButtonByXpath("PinKey" + digit4);
			
			//checkTextByXpath("PinTitle", "Confirm PIN");
			clickButtonByXpath("PinKey" + digit1);
			clickButtonByXpath("PinKey" + digit2);
			clickButtonByXpath("PinKey" + digit3);
			clickButtonByXpath("PinKey" + digit4);
		} else {
			clickButtonByXpath("AlertNotNow");
		}
		Thread.sleep(2000);

	}
	
	private int alertCheck() {
		int myCheck = 0;
		myCheck = checkTextByXpathReturn("AlertMessage", "Please create a PIN to protect sensitive data available to leaders.");
		if (myCheck == 0 ) {
			myCheck = checkTextByXpathReturn("AlertMessage", "Passcode Required");
		}
		
		return myCheck;
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
		boolean testForElement;
		//List<String> checkReportText = new ArrayList<String>();
		String pageSource;
		//Search for logged in user
		searchForUser("Aaron, Jane");
		
		pageSource = getSourceOfPage();
		//System.out.println("Page Source: " + pageSource);
				
		//checkReportText = getAllText();
		Assert.assertTrue(checkNoCaseList("Jane Aaron", pageSource));
		Assert.assertTrue(checkNoCaseList("Fagamalo 1st Ward", pageSource));
		Assert.assertTrue(checkNoCaseList("CONTACT INFORMATION", pageSource));
		Assert.assertTrue(checkNoCaseList("555-555-5555", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		Assert.assertTrue(checkNoCaseList("555-555-1234", pageSource));
		//Assert.assertTrue(checkNoCaseList("HOUSEHOLD", pageSource));
		Assert.assertTrue(checkNoCaseList("no-reply@ldschurch.org", pageSource));
		//Assert.assertTrue(checkNoCaseList("PERSONAL", pageSource));
		

		//Leadership Should be able to see this information
		//Membership Information
		if (memberShipInfo == true ) {
			//Assert.assertTrue(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
			Assert.assertTrue(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource));
		} else {
			//Assert.assertFalse(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
			Assert.assertFalse(checkNoCaseList("MEMBERSHIP INFORMATION", pageSource));
		}
		
		//Full Name
		if (fullName == true){
			Assert.assertTrue(checkNoCaseList("AFPMisc, Member2", pageSource));
			Assert.assertTrue(checkNoCaseList("FULL NAME", pageSource));
			//Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member2"));
			//Assert.assertTrue(checkElementTextCustom("FULL NAME", "*"));
		} else {
			Assert.assertFalse(checkNoCaseList("AFPMisc, Member2", pageSource));
			Assert.assertFalse(checkNoCaseList("FULL NAME", pageSource));
			//Assert.assertFalse(checkElementTextViewReturn("AFPMisc, Member2"));
			//Assert.assertFalse(checkElementTextCustom("FULL NAME", "*"));
		}
		
		

		/*
		//Birth Date
		if (birthDate == true){
			Assert.assertTrue(checkElementTextViewReturn("November 11, 1960 (54)"));
			Assert.assertTrue(checkElementTextCustom("BIRTH DATE", "*"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("November 11, 1960 (54)"));
			Assert.assertFalse(checkElementTextCustom("BIRTH DATE", "*"));
		}
		*/
		
		
		
		
		
		//Record Number
		if (recordNumber == true ){
			Assert.assertTrue(checkNoCaseList("888-0028-4326", pageSource));
			Assert.assertTrue(checkNoCaseList("RECORD NUMBER", pageSource));
			//Assert.assertTrue(checkElementTextViewReturn("888-0028-4326"));
			//Assert.assertTrue(checkElementTextCustom("RECORD NUMBER", "*"));
		} else {
			Assert.assertFalse(checkNoCaseList("888-0028-4326", pageSource));
			Assert.assertFalse(checkNoCaseList("RECORD NUMBER", pageSource));
			//Assert.assertFalse(checkElementTextViewReturn("888-0028-4326"));
			//Assert.assertFalse(checkElementTextCustom("RECORD NUMBER", "*"));
		}

		//Check Ordinances
		if (ordinances == true ){
			clickButtonByXpathTitleName("Ordinances");
			pageSource = getSourceOfPage();
			Assert.assertTrue(checkNoCaseList("Baptism", pageSource));
			//Assert.assertTrue(checkNoCaseList("November 11, 1970", pageSource));
			Assert.assertTrue(checkNoCaseList("Confirmation", pageSource));
			//Assert.assertTrue(checkNoCaseList("November 11, 1970", pageSource));
			//Assert.assertTrue(checkElementTextViewReturn("Baptism"));
			//Assert.assertTrue(checkElementTextViewReturn("November 11, 1970"));
			//Assert.assertTrue(checkElementTextViewReturn("Confirmation"));
			//Assert.assertTrue(checkElementTextViewReturn("November 11, 1970"));
			pressBackKey();
		} else {
			Assert.assertFalse(checkNoCaseList("Ordinances", pageSource));
			//Assert.assertFalse(checkElementTextViewReturn("Ordinances"));
		}
		

		
		
		//Check Other Information
		if (otherInfo == true ) {
			clickButtonByXpathTitleName("Other Information");
			pageSource = getSourceOfPage();
			if (ordinances == true) {
				Assert.assertTrue(checkElementTextViewReturn("Gender"));
				Assert.assertTrue(checkElementTextViewReturn("Female"));
				Assert.assertTrue(checkElementTextViewReturn("Birth Country"));
				Assert.assertTrue(checkElementTextViewReturn("United States"));
			} else {
				Assert.assertTrue(checkElementTextViewReturn("Gender"));
				Assert.assertTrue(checkElementTextViewReturn("Female"));
				Assert.assertFalse(checkElementTextViewReturn("Birth Country"));
				Assert.assertFalse(checkElementTextViewReturn("United States"));
			}

			pressBackKey();
		}
		
		

		Thread.sleep(2000);
		pressBackKey();
		clickButtonByXpath("SearchCollapse");
		
		
	}
	
	/** checkDrawerItems (boolean leader)
	 * Check the drawer items - non leaders should not have the reports item
	 * 
	 * @param leader
	 * @throws Exception
	 */
	private void checkDrawerItems (boolean leader) throws Exception {
		//Check the Drawer items
		clickButtonByXpath("Drawer");
		Assert.assertTrue(checkElementTextViewReturn("Directory"));
		Assert.assertTrue(checkElementTextViewReturn("Organizations"));
		Assert.assertTrue(checkElementTextViewReturn("Missionary"));
		Assert.assertTrue(checkElementTextViewReturn("Lists"));
		Assert.assertTrue(checkElementTextViewReturn("Calendar"));
		Assert.assertTrue(checkElementTextViewReturn("Meetinghouses"));
		if (leader == true) {
			Assert.assertTrue(checkElementTextViewReturn("Reports"));
		} else {
			Assert.assertFalse(checkElementTextViewReturn("Reports"));
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
		clickButtonByXpath("DrawerOrganizations");
		Thread.sleep(1000);
		
		pageSource = getSourceOfPage();
		//System.out.println("Page Source: " + pageSource);
				
		Assert.assertTrue(checkNoCaseList("Bishopric", pageSource));
		Assert.assertTrue(checkNoCaseList("High Priests Group", pageSource));
		Assert.assertTrue(checkNoCaseList("Elders Quorum", pageSource));
		Assert.assertTrue(checkNoCaseList("Relief Society", pageSource));
		Assert.assertTrue(checkNoCaseList("Young Men", pageSource));
		Assert.assertTrue(checkNoCaseList("Young Women", pageSource));
		Assert.assertTrue(checkNoCaseList("Sunday School", pageSource));
		Assert.assertTrue(checkNoCaseList("Primary", pageSource));
		Assert.assertTrue(checkNoCaseList("Ward Missionaries", pageSource));
		Assert.assertTrue(checkNoCaseList("Other Callings", pageSource));

		
		
		//Bishopric
		clickButtonByXpathTitleName("Bishopric");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();

		Assert.assertTrue(checkNoCaseList("Bishop", pageSource));
		Assert.assertTrue(checkNoCaseList("Ami, Samu", pageSource));
		Assert.assertTrue(checkNoCaseList("Bishopric Second Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Faapili, Muipu", pageSource));
		Assert.assertTrue(checkNoCaseList("Ward Clerk", pageSource));
		Assert.assertTrue(checkNoCaseList("Tutunoa, Ualesi Junior, Jr", pageSource));
		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
		//High Priests Group
		clickButtonByXpathTitleName("High Priests Group");
		clickButtonByXpathTitleName("High Priests Group Leadership");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("High Priests Group Leader", pageSource));
		Assert.assertTrue(checkNoCaseList("Faamoe, Panapa Filifili", pageSource));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		//Elders Quorum
		clickButtonByXpathTitleName("Elders Quorum");
		clickButtonByXpathTitleName("Elders Quorum Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Elders Quorum President", pageSource));
		Assert.assertTrue(checkNoCaseList("Kitara, Peaulele", pageSource));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		
		//Relief Society
		clickButtonByXpathTitleName("Relief Society");
		clickButtonByXpathTitleName("Relief Society Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Relief Society President", pageSource));
		Assert.assertTrue(checkNoCaseList("Endemann, Lole", pageSource));
		Assert.assertTrue(checkNoCaseList("Relief Society First Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Faamoetauloa, Fiasili", pageSource));
		Assert.assertTrue(checkNoCaseList("Relief Society Second Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Puleiai, Siva", pageSource));
		Assert.assertTrue(checkNoCaseList("Relief Society Secretary", pageSource));
		Assert.assertTrue(checkNoCaseList("Patiole, Luafa", pageSource));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		Thread.sleep(1000);
		//Young Men
		clickButtonByXpathTitleName("Young Men");
		clickButtonByXpathTitleName("Young Men Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Men President", pageSource));
		Assert.assertTrue(checkNoCaseList("Kitara, Lafaele", pageSource));
		Assert.assertTrue(checkNoCaseList("Young Men First Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Poai, Mikaele", pageSource));
		Assert.assertTrue(checkNoCaseList("Young Men Second Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Faamoetauloa Panapa Jr, Panapa Jnr", pageSource));
		Assert.assertTrue(checkNoCaseList("Young Men Secretary", pageSource));
		Assert.assertTrue(checkNoCaseList("Venasio Fainuu, Fogavai", pageSource));
		pressBackKey();
		//Thread.sleep(1000);
		clickButtonByXpathTitleName("Priests Quorum");
		Thread.sleep(1000);
		if (getRunningOS().equals("mac")) {
			clickButtonByXpathTitleName("Priests Quorum Presidency");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Priests Quorum First Assistant", pageSource));
		Assert.assertTrue(checkNoCaseList("Tulia, Tiueni", pageSource));
		Assert.assertTrue(checkNoCaseList("Priests Quorum Second Assistant", pageSource));
		Assert.assertTrue(checkNoCaseList("Kitara, Tumua", pageSource));
		Assert.assertTrue(checkNoCaseList("Priests Quorum Secretary", pageSource));
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
		Assert.assertTrue(checkNoCaseList("Young Women President", pageSource));
		Assert.assertTrue(checkNoCaseList("Tutunoa, Lusi", pageSource));
		Assert.assertTrue(checkNoCaseList("Young Women First Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Kitara, Etevise", pageSource));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		
		//Sunday School
		clickButtonByXpathTitleName("Sunday School");
		clickButtonByXpathTitleName("Sunday School Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Sunday School President", pageSource));
		Assert.assertTrue(checkNoCaseList("Lealaiauloto, Uana Iosefa Sao", pageSource));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		
		//Primary
		clickButtonByXpathTitleName("Primary");
		clickButtonByXpathTitleName("Primary Presidency");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Primary President", pageSource));
		Assert.assertTrue(checkNoCaseList("Faamoe, Talalelagi", pageSource));
		Assert.assertTrue(checkNoCaseList("Primary First Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Fepuleai, Malele Seuamuli", pageSource));
		Assert.assertTrue(checkNoCaseList("Primary Second Counselor", pageSource));
		Assert.assertTrue(checkNoCaseList("Tulia, Faagalo", pageSource));
		//Assert.assertTrue(checkNoCaseList("Primary Secretary", pageSource));
		//Assert.assertTrue(checkNoCaseList("Samu, Luisa", pageSource));
		pressBackKey();
		Thread.sleep(1000);
		pressBackKey();
		

		//Ward Missionaries
		clickButtonByXpathTitleName("Ward Missionaries");
		Thread.sleep(2000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Ward Mission Leader", pageSource));
		Assert.assertTrue(checkNoCaseList("Kitara, Lafaele", pageSource));
		pressBackKey();
		
		
		//Other Callings
		clickButtonByXpathTitleName("Other Callings");
		clickButtonByXpathTitleName("Young Single Adult");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Single Adult Leader", pageSource));
		Assert.assertTrue(checkNoCaseList("Solomona, Solomona", pageSource));
		pressBackKey();
		clickButtonByXpathTitleName("Music");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Music Adviser", pageSource));
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
		//Missionary
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerMissionary");
		Thread.sleep(1000);
		//Assert.assertTrue(checkElementTextViewRoboReturn("Elder Dallin Fawcett"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samoa Apia Mission"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Elder Kawika Tupuola"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samoa Apia Mission"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Lafaele"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Mission Leader"));
		//pressBackKey();
	}
	
	/** checkReports()
	 * Check reports for leaders
	 * 
	 * @throws Exception
	 */
	private void checkReports(boolean newUnit, boolean bishop) throws Exception {
		//Reports
		//List<String> checkReportText = new ArrayList<String>();
		String pageSource;
		//boolean myTestbool;
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");
		Thread.sleep(2000);
		pageSource = getSourceOfPage();

		Assert.assertTrue(checkNoCaseList("Birthday List", pageSource));
		Assert.assertTrue(checkNoCaseList("Members Moved In", pageSource));
		Assert.assertTrue(checkNoCaseList("Members Moved Out", pageSource));
		Assert.assertTrue(checkNoCaseList("Members with Callings", pageSource));
		Assert.assertTrue(checkNoCaseList("New Members", pageSource));
		Assert.assertTrue(checkNoCaseList("Unit Statistics", pageSource));
		Assert.assertFalse(checkNoCaseList("Death Star Reports", pageSource));

		
		
		
		//Check the members moved out report
		//Should have a ( ) with the age by the birth date
		clickButtonByXpathTitleName("Members Moved Out");
		
		Thread.sleep(1000);
		pageSource = getSourceOfPage();

		Assert.assertTrue(checkNoCaseList("Betham, Scott", pageSource));

		//The new unit is only available for bishop
		if (bishop == true){
			Assert.assertTrue(checkNoCaseList("pheasant pointe 5th ward", pageSource));
		} else {
			Assert.assertFalse(checkNoCaseList("pheasant pointe 5th ward", pageSource));
		}
		Assert.assertFalse(checkNoCaseList("Solo, Han", pageSource));
		pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members Moved In
		//Thread.sleep(1000);
		clickButtonByXpathTitleName("Members Moved In");
		Thread.sleep(1000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Sa", pageSource));
		Assert.assertTrue(checkNoCaseList("Seti (55)", pageSource));
		Assert.assertTrue(checkNoCaseList("Head of household", pageSource));
		Assert.assertFalse(checkNoCaseList("Skywalker, Luke", pageSource));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members with Callings
		clickButtonByXpathTitleName("Members with Callings");
		Thread.sleep(2000);
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Ami, Christian", pageSource));
		Assert.assertTrue(checkNoCaseList("Beehive President (6 months)", pageSource));
		Assert.assertFalse(checkNoCaseList("Skywalker, Anakin", pageSource));
	
		
		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Organization");
		} else {
			clickButtonByXpathTitleName("ORGANIZATION");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Ward Clerk", pageSource));
		Assert.assertTrue(checkNoCaseList("Kitara, Lafaele (2 months)", pageSource));
		Assert.assertFalse(checkNoCaseList("Kenobi, Obi-Wan", pageSource));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Duration");
		} else {
			clickButtonByXpathTitleName("DURATION");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Ward Clerk", pageSource));
		Assert.assertTrue(checkNoCaseList("Tutunoa, Lusi", pageSource));
		Assert.assertFalse(checkNoCaseList("Amidala, Padme", pageSource));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Not Set Apart");
		} else {
			clickButtonByXpathTitleName("NOT SET APART");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Young Men First Counselor (6 months)", pageSource));
		Assert.assertTrue(checkNoCaseList("Poai, Mikaele", pageSource));
		Assert.assertFalse(checkNoCaseList("P0, C3", pageSource));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		//Members without Callings
		clickButtonByXpathTitleName("Members without Callings");
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("AFPEighteen, Member", pageSource));
		Assert.assertTrue(checkNoCaseList("AFPEleven, Member", pageSource));
		Assert.assertFalse(checkNoCaseList("D2, R2", pageSource));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Male");
		} else {
			clickButtonByXpathTitleName("MALE");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("AFPEleven, Member", pageSource));
		Assert.assertTrue(checkNoCaseList("AFPFifteen, Member", pageSource));
		Assert.assertFalse(checkNoCaseList("Binks, Jarjar", pageSource));

		if (getRunningOS().equals("mac")) {
			clickButtonByXpath("TopSort");
			clickButtonByName("Female");
		} else {
			clickButtonByXpathTitleName("FEMALE");
		}
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("AFPEighteen, Member", pageSource));
		Assert.assertTrue(checkNoCaseList("AFPFive, Wife", pageSource));
		Assert.assertFalse(checkNoCaseList("Organa, Leia", pageSource));

		Thread.sleep(1000);
		pressBackKey();
		//Thread.sleep(1000);
		
		//New Members
		clickButtonByXpathTitleName("New Members");
		pageSource = getSourceOfPage();
		Assert.assertTrue(checkNoCaseList("Joezmal, Loana", pageSource));
		Assert.assertTrue(checkNoCaseList("14", pageSource));
		Assert.assertTrue(checkNoCaseList("F", pageSource));
		if (newUnit == true){
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkNoCaseList("Mar 15, 2015", pageSource));
			} else {
				Assert.assertTrue(checkNoCaseList("March 15, 2015", pageSource));
			}
		} else {
			if (getRunningOS().equals("mac")) {
				Assert.assertFalse(checkNoCaseList("Mar 15, 2015", pageSource));
			} else {
				Assert.assertFalse(checkNoCaseList("March 15, 2015", pageSource));
			}
		}
		
		Assert.assertTrue(checkNoCaseList("Member", pageSource));
		Assert.assertFalse(checkNoCaseList("Hutt, Jabba", pageSource));

		Thread.sleep(1000);
		pressBackKey();
		Thread.sleep(1000);
		
		if (newUnit == true ) {
			//Temple Recommend Status
			clickButtonByXpathTitleName("Temple Recommend Status");
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertFalse(checkElementTextViewReturn("Ahsoka, Tano"));
			//Assert.assertTrue(checkElementTextViewReturn("Expired"));
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Active");
			} else {
				clickButtonByXpathTitleName("ACTIVE");
			}
			Assert.assertTrue(checkElementTextViewReturn("Ami, Samu"));
			//Assert.assertTrue(checkElementTextViewReturn("Jul 2016"));
			Assert.assertFalse(checkElementTextViewReturn("Maul, Darth"));
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Expiring");
			} else {
				clickButtonByXpathTitleName("EXPIRING");
			}
			Assert.assertTrue(checkElementTextViewReturn("Fepuleai, Moseniolo"));
			Assert.assertFalse(checkElementTextViewReturn("Windu, Mace"));
			
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Expired");
			} else {
				clickButtonByXpathTitleName("EXPIRED");
			}
			Assert.assertTrue(checkElementTextViewReturn("Tutunoa, Lusi"));
			Assert.assertFalse(checkElementTextViewReturn("Jinn, Qui-Gon"));
			
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("TopSort");
				clickButtonByName("Other");
			} else {
				clickButtonByXpathTitleName("OTHER");
			}
			Assert.assertTrue(checkElementTextViewReturn("Mene, Matagalu"));
			Assert.assertFalse(checkElementTextViewReturn("Calrissian, Lando"));
			Thread.sleep(1000);
			pressBackKey();
			Thread.sleep(1000);
		} else {
			Assert.assertFalse(checkElementTextViewReturn("Temple Recommend Status"));
		}

		
		//Unit Statistics
		clickButtonByXpathTitleName("Unit Statistics");
		//Thread.sleep(1000);
		clickButtonByXpath("AlertOK");
		Thread.sleep(1000);
		Assert.assertTrue(checkElementTextViewReturnContains("595"));
		Assert.assertTrue(checkElementTextViewReturnContains("268"));
		Assert.assertTrue(checkElementTextViewReturnContains("16"));
		Assert.assertFalse(checkElementTextViewReturnContains("8675309"));
		pressBackKey();
	}
	
	private void checkHTVTBasic(String userCalling ) throws Exception {
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		String pageSource;
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");
		Thread.sleep(2000);
		
		pageSource = getSourceOfPage();

		if (userCalling.equals("Bishopric")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource));
		}
		
		if ((userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource));
		}
		
		if (userCalling.equals("Relief Society Pres")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource));
		}
		
		if (userCalling.equals("Ward Council")) {
			Assert.assertTrue(checkNoCaseList("Home Teaching", pageSource));
			Assert.assertTrue(checkNoCaseList("Visiting Teaching", pageSource));
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
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member17"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member4"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member7"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			

			clickButtonByXpath("6Months");
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member17"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member4"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member7"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}

			clickButtonByXpath("3Months");
			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPEleven, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFifteen, Member"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("1Month");
			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPEleven, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFifteen, Member"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			pressBackKey();
			
			clickButtonByXpathTitleName("Unassigned Households");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFive, Wife"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Frank"));
			pressBackKey();
			
			clickButtonByXpathTitleName("Potential Home Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturn("Galuvao, Faafetai"));
				Assert.assertTrue(checkElementTextViewReturn("Faapili, Muipu"));
				Assert.assertTrue(checkElementTextViewReturn("Faamoetauloa, Ennie"));
			} else {
				Assert.assertTrue(checkElementTextViewReturn("Galuvao, Faafetai (60)"));
				Assert.assertTrue(checkElementTextViewReturn("Faapili, Muipu (32)"));
				Assert.assertTrue(checkElementTextViewReturn("Faamoetauloa, Ennie (35)"));
			}
			pressBackKey();
			
			//Elders Quorum
			clickLastTextViewRoboReturnContains("Households Not Visited");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("13 Months");
			}
			Assert.assertTrue(checkElementTextViewReturn("Sa, Seti"));
			Assert.assertTrue(checkElementTextViewReturn("Seu, Malaga"));
			Assert.assertTrue(checkElementTextViewReturn("Lavea, Muaau Alavaa"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("6Months");
			Assert.assertTrue(checkElementTextViewReturn("Sa, Seti"));
			Assert.assertTrue(checkElementTextViewReturn("Faamoe, Ueni"));
			Assert.assertTrue(checkElementTextViewReturn("Lavea, Muaau Alavaa"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("3Months");
			Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband"));
			Assert.assertTrue(checkElementTextViewReturn("Faamoe, Ueni"));
			Assert.assertTrue(checkElementTextViewReturn("Lavea, Muaau Alavaa"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("1Month");
			Assert.assertTrue(checkElementTextViewReturn("Faamoe, Filifili"));
			Assert.assertTrue(checkElementTextViewReturn("AFPSix, Husband"));
			Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			pressBackKey();
			
			clickLastTextViewRoboReturnContains("Unassigned Households");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband"));
			Assert.assertTrue(checkElementTextViewReturn("Lavea, Muaau Alavaa"));
			Assert.assertTrue(checkElementTextViewReturn("Isaako, Ioane"));
			pressBackKey();
			
			clickLastTextViewRoboReturnContains("Potential Home Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband"));
				Assert.assertTrue(checkElementTextViewReturn("Ami, Samu Junior"));
				Assert.assertTrue(checkElementTextViewReturn("Endemann, Eddie"));
			} else {
				Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband (55)"));
				Assert.assertTrue(checkElementTextViewReturn("Ami, Samu Junior (22)"));
				Assert.assertTrue(checkElementTextViewReturn("Endemann, Eddie (81)"));
			}

			pressBackKey();
			pressBackKey();
		}
		
		if ((userCalling.equals("Relief Society Pres")) || (userCalling.equals("Bishopric"))) {
			clickButtonByXpathTitleName("Visiting Teaching");
			Thread.sleep(2000);

			//Visiting Teaching
			clickButtonByXpathTitleName("Sisters Not Contacted");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				clickButtonByXpathTitleName("13 Months");
			}
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member14"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("6Months");
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member14"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("3Months");
			Assert.assertTrue(checkElementTextViewReturn("AFPFive, Wife"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			
			clickButtonByXpath("1Month");
			Assert.assertTrue(checkElementTextViewReturn("Afamasaga, Petala"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFive, Wife"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			if (getRunningOS().equals("mac")) {
				pressBackKey();
			}
			pressBackKey();
			
			clickButtonByXpathTitleName("Unassigned Sisters");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member14"));
			pressBackKey();
			
			clickButtonByXpathTitleName("Potential Visiting Teachers");
			Thread.sleep(2000);
			if (getRunningOS().equals("mac")) {
				Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
				Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member16"));
				Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			} else {
				Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member (55)"));
				Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member16 (55)"));
				Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12 (55)"));
			}

			pressBackKey();
			pressBackKey();

		}
		
		
		
	}
	
	private void checkHTVTHouseholds(String userCalling ) throws Exception {
		//userCalling: Bishopric, High Priest Group, Elders Quorum Pres, Relief Society Pres, Ward Council
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");
		Thread.sleep(2000);
		
		
		
		if ((userCalling.equals("Bishopric")) || (userCalling.equals("High Priest Group")) || (userCalling.equals("Elders Quorum Pres"))) {
			clickButtonByXpathTitleName("Home Teaching");
			Thread.sleep(2000);
			
			//High Priests
			clickButtonByXpathTitleName("Households");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPEleven, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFifteen, Member"));
			
			//Test Assigned Home Teachers
			clickButtonByXpath("MenuFilter");
			clickButtonByXpath("AssignedHomeTeachersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "Assigned Home Teachers");
			} else {
				pressBackKey();
			}

			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFifteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member16"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
		
			//Test NOT Assigned Home Teachers
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("AssignedHomeTeachersBox");
			}
			clickButtonByXpath("NotAssignedHomeTeachersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "Not Assigned Home Teachers");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member1"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member13"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member14"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}

			//New and Moved in Members
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("NotAssignedHomeTeachersBox");
			}
			clickButtonByXpath("NewAndMovedInMembersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "New & Moved-In Members");
			} else {
				pressBackKey();
			}
			//TODO this report is different between Web - iOS and Android
			//Need to fix
			//Assert.assertTrue(checkElementTextViewReturn("Joezmal, Loana"));
			//Assert.assertTrue(checkElementTextViewReturn("Lilotoe, Tapatasi"));
			//Assert.assertTrue(checkElementTextViewReturn("Sanele, Ana"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}

			
			//Single Sisters 18-30 years old
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("NewAndMovedInMembersBox");
			}
			clickButtonByXpath("SingleSisters1830");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 18-30 years old");
			Assert.assertTrue(checkElementTextViewReturn("Fiamatai, Solomua"));
			Assert.assertTrue(checkElementTextViewReturn("Ielv, Gasolo"));
			Assert.assertTrue(checkElementTextViewReturn("Lagaaia, Fofogafetalaililomaiava"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
		
			
			
			//Single Sisters 31 and over
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleSisters1830");
			}
			clickButtonByXpath("SingleSisters31over");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFive, Wife"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			
			//Single Brothers 18-30 years old
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleSisters31over");
			}
			clickButtonByXpath("SingleBrothers1830");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Brothers 18-30 years old");
			Assert.assertTrue(checkElementTextViewReturn("Anderson, Edward"));
			Assert.assertTrue(checkElementTextViewReturn("Etene, Max"));
			Assert.assertTrue(checkElementTextViewReturn("Faivaa, Tepa"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			//Single Brothers 31 and over
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleBrothers1830");
			}
			clickButtonByXpath("SingleBrohters31over");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementTextViewReturn("AFPEleven, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFifteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member1"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			//Need a test for Households not visisted slider.
			
			pressBackKey();
			
			//Elders Quorum
			clickLastTextViewRoboReturnContains("Households");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertTrue(checkElementTextViewReturn("Faamoeolo, Akisa"));
			Assert.assertTrue(checkElementTextViewReturn("Lavea, Muaau Alavaa"));
			
			//Test Assigned Home Teachers
			clickButtonByXpath("MenuFilter");
			clickButtonByXpath("AssignedHomeTeachersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "Assigned Home Teachers");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertTrue(checkElementTextViewReturn("AFPSix, Husband"));
			Assert.assertTrue(checkElementTextViewReturn("Faamoe, Filifili"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
		
			//Test NOT Assigned Home Teachers
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("AssignedHomeTeachersBox");
			}
			clickButtonByXpath("NotAssignedHomeTeachersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "Not Assigned Home Teachers");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband"));
			Assert.assertTrue(checkElementTextViewReturn("Faamoe, Ueni"));
			Assert.assertTrue(checkElementTextViewReturn("Isaako, Ioane"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}

			//New and Moved in Members
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("NotAssignedHomeTeachersBox");
			}
			clickButtonByXpath("NewAndMovedInMembersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "New & Moved-In Members");
			} else {
				pressBackKey();
			}
			Assert.assertFalse(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertFalse(checkElementTextViewReturn("AFPSix, Husband"));
			Assert.assertFalse(checkElementTextViewReturn("AFPTen, Husband"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}


			//Single Sisters 18-30 years old
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("NewAndMovedInMembersBox");
			}
			clickButtonByXpath("SingleSisters1830");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 18-30 years old");
			Assert.assertFalse(checkElementTextViewReturn("Fiamatai, Solomua"));
			Assert.assertFalse(checkElementTextViewReturn("Ielv, Gasolo"));
			Assert.assertFalse(checkElementTextViewReturn("Lagaaia, Fofogafetalaililomaiava"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
		
			
			//Single Sisters 31 and over
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleSisters1830");
			}
			clickButtonByXpath("SingleSisters31over");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertFalse(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertFalse(checkElementTextViewReturn("AFPFive, Wife"));
			Assert.assertFalse(checkElementTextViewReturn("AFPFourteen, Member"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			
			//Single Brothers 18-30 years old
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleSisters31over");
			}
			clickButtonByXpath("SingleBrothers1830");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Brothers 18-30 years old");
			Assert.assertTrue(checkElementTextViewReturn("Mene, Taavili Maalona"));
			Assert.assertFalse(checkElementTextViewReturn("Etene, Max"));
			Assert.assertFalse(checkElementTextViewReturn("Faivaa, Tepa"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			//Single Brothers 31 and over
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleBrothers1830");
			}
			clickButtonByXpath("SingleBrohters31over");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertTrue(checkElementTextViewReturn("AFPSix, Husband"));
			Assert.assertTrue(checkElementTextViewReturn("AFPTen, Husband"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			//Need a test for Households not visisted slider.
			
			
			pressBackKey();
			pressBackKey();
		
		}
		
		if ((userCalling.equals("Relief Society Pres")) || (userCalling.equals("Bishopric"))) {
			clickButtonByXpathTitleName("Visiting Teaching");
			Thread.sleep(2000);

			//Visiting Teaching
			clickButtonByXpathTitleName("Sisters");
			Thread.sleep(2000);
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member14"));

			//Test Assigned Visiting Teachers
			clickButtonByXpath("MenuFilter");
			clickButtonByXpath("AssignedHomeTeachersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "Assigned Visiting Teachers");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPTwo, Wife"));
			Assert.assertTrue(checkElementTextViewReturn("Moors, Eseta"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
		
			//Test NOT Assigned Visiting Teachers
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("AssignedHomeTeachersBox");
			}
			clickButtonByXpath("NotAssignedHomeTeachersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "Not Assigned Visiting Teachers");
			} else {
				pressBackKey();
			}
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member12"));
			Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member14"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}

			//New and Moved in Members
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("NotAssignedHomeTeachersBox");
			}
			clickButtonByXpath("NewAndMovedInMembersBox");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
				checkTextByXpath("HTVTFiltersApplied", "New & Moved-In Members");
			} else {
				pressBackKey();
			}
			Assert.assertFalse(checkElementTextViewReturn("AFPMisc, Member15"));
			Assert.assertFalse(checkElementTextViewReturn("AFPSix, Husband"));
			Assert.assertFalse(checkElementTextViewReturn("AFPTen, Husband"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}


			//Single Sisters 18-30 years old
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("NewAndMovedInMembersBox");
			}
			clickButtonByXpath("SingleSisters1830");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 18-30 years old");
			Assert.assertTrue(checkElementTextViewReturn("Aitusavali, Solofuti Saluatai"));
			Assert.assertTrue(checkElementTextViewReturn("Ami, Faleatafa"));
			Assert.assertTrue(checkElementTextViewReturn("Etene, Foketi Faamoe"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
		
			
			//Single Sisters 31 and over
			clickButtonByXpath("MenuFilter");
			if (getRunningOS().equals("mac")) {
				clickButtonByXpath("SingleSisters1830");
			}
			clickButtonByXpath("SingleSisters31over");
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTApply");
			} else {
				pressBackKey();
			}
			//checkTextByXpath("HTVTFiltersApplied", "Single Sisters 31 and over");
			Assert.assertTrue(checkElementTextViewReturn("AFPEighteen, Member"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFive, Wife"));
			Assert.assertTrue(checkElementTextViewReturn("AFPFourteen, Member"));
			if (getRunningOS().equals("android")) {
				clickButtonByXpath("HTVTRemoveFiltersButton");
			}
			
			
			//Need a test for Households not visisted slider.
			
			
			pressBackKey();
			pressBackKey();
			

		}
		
		
		
	}
	
	
	
	
	
	
	private void checkAllWardDirectories() throws Exception {
		List<String> StakeWard = new ArrayList<String>();
		List<WebElement> options = new ArrayList<WebElement>();
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
				clickButtonByXpathTitleName(StakeWardItem);
				//displayAllTextViewElements();
				
				//This will check to see if the first user has text.  
				Assert.assertTrue(checkFirstDirectoryUser());
				
				//Assert.assertTrue(checkElementTextViewReturnContains("e"));
				//Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
			}
		} else {
			clickButtonByXpath("SpinnerNav");
			//Get Stake and all Wards
			options= driver.findElements(By.xpath("//*[@id='title']"));
			for (int i = 0 ; i < options.size(); i++ ) {
				//System.out.println(options.get(i).getText());
				StakeWard.add(options.get(i).getText());
			}
			
			Thread.sleep(1000);
			pressBackKey();
			Thread.sleep(1000);
			
			//Go through each Stake and Ward to make sure it isn't blank
			for(String StakeWardItem : StakeWard){
				clickButtonByXpath("SpinnerNav");
				Thread.sleep(2000);
				clickButtonByXpathTitleName(StakeWardItem);
				//displayAllTextViewElements();
				
				//Should be a better way to do this. 
				Assert.assertTrue(checkElementTextViewReturnContains("e"));
				Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
			}
			
			
		}


	}
	
	private Boolean checkFirstDirectoryUser() {
		Boolean myReturnStatus;
		String myString = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]")).getText();
		if (myString.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	
	private void drawerSignOut() throws Exception {
		Thread.sleep(2000);
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		Thread.sleep(2000);
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		checkForAlert();
	}
	
	//TODO: Need to be able to select 1 to 12 units
	/** unitsToSync()
	 * If there are more than 12 units this will select the Savaii Stake
	 * @throws Exception
	 */
	private void unitsToSync() throws Exception {
		if (checkElementTextViewReturn("Select up to 12 units to sync.")) {
			clickButtonByXpathTitleName("Savaii Samoa Fagamalo Stake");
			clickButtonByXpath("SyncButton");
			Thread.sleep(2000);
		}
	}
	
	private void resetVisibility() throws Exception {
		int myCheck;
		int householdCheck;
		int personalCheck;
		
		//This is just in case something went wrong - put visibility back to Stake. 
		myCheck = checkTextByXpathContainsReturn("AlertMessageView", "Household visible to Private");
		//System.out.println("Alert Found: " + myCheck);
		householdCheck = checkTextByXpathContainsReturn("HouseholdSettings", "Private");
		personalCheck = checkTextByXpathContainsReturn("PersonalSettings", "Private");
		
		
		if ((myCheck == 1) || (householdCheck == 1) || (personalCheck == 1)) {
			if (myCheck ==1 ) {
				clickButtonByXpath("AlertOK");
			}
			
			
			if (getRunningOS().equals("mac")) {
				Thread.sleep(1000);
				clickButtonByXpath("HouseholdVisibilityLimit");
				clickButtonByXpath("StakeVisibility");
				//clickItemByXpathRoboTextContains("Stake");
				Thread.sleep(1000);
			} else {
				scrollDown("Private—Leadership Only", -1000 );
				Thread.sleep(2000);
				clickButtonByXpathTitleName("Stake Visibility");
				Thread.sleep(1000);
			}
			
			clickButtonByXpath("MenuSave");
			Thread.sleep(2000);
			clickButtonByXpath("MenuEdit");
			Thread.sleep(2000);
		}
	}
	
	
	private void searchForUser(String userToSearch) throws Exception {
		boolean testForElement;
		
		testForElement = checkElementExistsByID("MenuDefaultDirectory");
		//System.out.println("testForElement: " + testForElement);
		if (testForElement == true ) {
			clickButtonByID("MenuDefaultDirectory");
			clickButtonByXpathTitleName("Individuals");
			clickButtonByID("MenuSearch");
		}

		sendTextbyXpath("SearchArea", userToSearch );
		
		Thread.sleep(2000);
		clickLastTextViewRoboReturn(userToSearch);
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
		if (checkElementExistsByXpath("AlertMessageCheck") == true) {
			clickButtonByXpath("SignOutOK");
		}
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() throws Exception {
		File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile,new File("/Users/zmaxfield/Selenium/Screenshot/lastErrorScreenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		Thread.sleep(5000);
		
	}
	

	@Parameters({"os"})
	@BeforeMethod(alwaysRun = true)
	public void openGuiMap(String os) {
		FileInputStream fileInput = null;
		
		//Just for testing
		System.out.println("GUI Map OS: " + os );
		
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
