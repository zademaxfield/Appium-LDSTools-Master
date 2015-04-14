import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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














import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Not used yet
//import org.openqa.selenium.WebElement;

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
    @Before
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        //File appDir = new File(classpathRoot, "..\\..\\..\\..\\Selenium");
        //MAC Path
        File appDir = new File(classpathRoot, "../../Selenium");
        File app = new File(appDir, "ldstools-alpha-20150414-1906.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        //Samsung Galaxy Tab
        capabilities.setCapability("deviceName","41031b0b89e93163");
        //HTC Nexus 9
        //capabilities.setCapability("deviceName","HT4ASJT02851");
        //Nexus 5
        //capabilities.setCapability("deviceName","03aadbed215c8e5f");
        // Android Emulator
        //capabilities.setCapability("deviceName","Android Emulator");
        //Samsung Galaxy Note 4
        //capabilities.setCapability("deviceName","");
        
        
        capabilities.setCapability("automationName","selendroid");
        capabilities.setCapability("newCommandTimeout","600");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "org.lds.ldstools");
        capabilities.setCapability("appActivity", "org.lds.ldstools.ui.StartupActivity");
        //driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        driver = new AppiumSwipeableDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
        touch = new TouchActions(driver);

    }	

	
    /*
	@Test
	public void simpleTest() throws Exception {
		//firstPages();
		justForTesting();	
	}
	*/
	

	public void justForTesting() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools5", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS5");
		clickLastTextViewRoboReturn("Tools, LDS5");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		scrollDown("Stake Visibility", -1000 );
		//clickButtonByXpath("EditVisibiltySpinner");

		clickButtonByXpath("EditAllVisibility");
		//displayAllTextViewElements();
		clickButtonByXpathPopoutMenu("Private—Leadership Only");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		
		syncLogIn("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		Assert.assertTrue(checkElementTextViewRoboReturn("Tools, LDS5"));

		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		
		syncLogIn("LDSTools5", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS5");
		clickLastTextViewRoboReturn("Tools, LDS5");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		scrollDown("Private—Leadership Only", -1000 );
		//clickButtonByXpath("EditVisibiltySpinner");

		clickButtonByXpath("EditAllVisibility");
		//displayAllTextViewElements();
		clickButtonByXpathPopoutMenu("Stake Visibility");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		
		syncLogIn("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		Assert.assertTrue(checkElementTextViewRoboReturn("Tools, LDS5"));
		
		

	}
		
	
	
	
    @Rule
    public Retry retry = new Retry(3);
	
   
	@Test
	public void under18HeadofHouseTest() throws Exception {
		under18HeadofHouse();	
	}
	
	
	@Test
	public void bishopricCounselorAndWardClerkTest() throws Exception {
		bishopricCounselorAndWardClerk();	
	}
	
	
	@Test
	public void bishopMemberOfSeparateStakeTest() throws Exception {
		bishopMemberOfSeparateStake();	
	}
	
	@Test
	public void editCurrentUserTest() throws Exception {
		editCurrentUser();	
	}
	
	@Test
	public void editOtherUserTest() throws Exception {
		editOtherUser();	
	}
	
	@Test
	public void editOtherUserInvalidPhoneTest() throws Exception {
		editOtherUserInvalidPhone();	
	}
	
	@Test
	public void editOtherUserInvalidEmailTest() throws Exception {
		editOtherUserInvalidEmail();	
	}
	
	@Test
	public void editVisibilityTest() throws Exception {
		editVisibility();	
	}
	
	@Test
	public void invalidLoginCheckTest() throws Exception {
		invalidLoginCheck();	
	}
	
	//@Test
	//public void loginCheckTest() throws Exception {
	//	loginCheck();	
	//}
	
	
	
	
	
	
//**************************************************************
//**************** Start of tests ******************************
//**************************************************************

	/** under18HeadofHouse()
	 * Test the settings for a Head of House under 18 years of age
	 * @throws Exception
	 */
	public void under18HeadofHouse() throws Exception {
		syncLogIn("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS6");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS6");
		clickLastTextViewRoboReturn("Tools, LDS6");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS6"));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("888-0028-7066"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Aaron, Jane");
		
		//Directory items that should not be visible
		clickItemByXpathRoboText("Aaron, Jane");
		clickLastTextViewRoboReturn("Aaron, Jane");
		
		
		Assert.assertTrue(checkElementTextViewReturn("Jane Aaron"));
		Assert.assertTrue(checkElementTextViewReturn("Fagamalo 1st Ward"));
		Assert.assertTrue(checkElementTextCustom("CONTACT INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("555-555-5555"));
		Assert.assertTrue(checkElementTextCustom("HOUSEHOLD", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("no-reply@ldschurch.org"));
		Assert.assertTrue(checkElementTextCustom("PERSONAL", "TextView"));
		//Assert.assertTrue(checkElementTextViewReturn("2778 E Saddle Rock Rd Eagle Mountain, Utah 84005"));
		Assert.assertTrue(checkElementTextCustom("HOUSEHOLD MEMBERS", "CapitalizedTextView"));
		
		Assert.assertTrue(checkElementTextViewReturn("Jane Aaron"));

		
		//Assert.assertFalse(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
		Assert.assertFalse(checkElementTextViewReturn("Allen, Bradley Jay"));
		Assert.assertFalse(checkElementTextCustom("FULL NAME", "CapitalizedTextView"));
		Assert.assertFalse(checkElementTextViewReturn("000-3597-284A"));
		Assert.assertFalse(checkElementTextCustom("RECORD NUMBER", "CapitalizedTextView"));
		Assert.assertFalse(checkElementTextViewReturn("June 24, 1979 (35)"));
		Assert.assertFalse(checkElementTextCustom("BIRTH DATE", "CapitalizedTextView"));
		Assert.assertFalse(checkElementTextViewReturn("Elder"));
		Assert.assertFalse(checkElementTextCustom("PRIESTHOOD OFFICE", "CapitalizedTextView"));
		Assert.assertFalse(checkElementTextViewReturn("Oct 2014 (Expired)"));
		Assert.assertFalse(checkElementTextCustom("TEMPLE RECOMMEND", "CapitalizedTextView"));
		Assert.assertFalse(checkElementTextViewReturn("Ordinances"));
		Assert.assertFalse(checkElementTextViewReturn("Marriage"));
		Assert.assertFalse(checkElementTextViewReturn("Other Information"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		
		//Check the Drawer items
		clickButtonByXpath("Drawer");
		Assert.assertTrue(checkElementTextViewReturn("Directory"));
		Assert.assertTrue(checkElementTextViewReturn("Callings"));
		Assert.assertTrue(checkElementTextViewReturn("Missionary"));
		Assert.assertTrue(checkElementTextViewReturn("Lists"));
		Assert.assertTrue(checkElementTextViewReturn("Calendar"));
		Assert.assertTrue(checkElementTextViewReturn("Meetinghouses"));
		Assert.assertFalse(checkElementTextViewReturn("Reports"));
		//clickButtonByXpath("DrawerReports");
		
		//Assert.assertTrue(checkElementTextViewReturn("Action and Interview List"));
		//Assert.assertFalse(checkElementTextViewReturn("Screaming Goats"));

	}
	
	/** bishopricCounselorAndWardClerk()
	 * This will test a user that is a member of the Bishopric and a Ward Clerk
	 * 
	 * @throws Exception
	 */
	public void bishopricCounselorAndWardClerk() throws Exception {
		//int myCheck;
		//LDSTools3 is Bishopric Counselor and Ward Clerk
		syncLogIn("LDSTools3", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS3");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS3");
		clickLastTextViewRoboReturn("Tools, LDS3");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS3"));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("888-0028-7031"));
		Assert.assertTrue(checkElementTextCustom("RECORD NUMBER", "TextView"));
		//Assert.assertTrue(checkElementTextViewReturn("January 1, 1980 (35)"));
		//Assert.assertTrue(checkElementTextCustom("BIRTH DATE", "TextView"));
		//clickButtonByXpathTitleName("Ordinances");
		//Need to test the Ordinances
		pressBackKey();
		
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Make sure that the directory is on Ash Point Ward
		clickButtonByXpath("SpinnerNav");
		scrollDownDistance(1000);
		clickButtonByXpathTitleName("Ash Point Ward");
		Thread.sleep(1000);
		
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Allen, Brad");
		
		
		//Directory items that should not be visible
		clickItemByXpathRoboText("Allen, Brad & Melissa");
		clickLastTextViewRoboReturn("Allen, Brad");
		
		
		Assert.assertTrue(checkElementTextViewReturn("Brad Allen"));
		Assert.assertTrue(checkElementTextViewReturn("Ash Point Ward"));
		Assert.assertTrue(checkElementTextViewReturn("Primary Teacher"));
		Assert.assertTrue(checkElementTextCustom("CONTACT INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("916-501-4412"));
		Assert.assertTrue(checkElementTextCustom("HOUSEHOLD", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("bradleyjayallen@yahoo.com"));
		Assert.assertTrue(checkElementTextCustom("PERSONAL", "TextView"));
		//Assert.assertTrue(checkElementTextViewReturn("2778 E Saddle Rock Rd Eagle Mountain, Utah 84005"));
		Assert.assertTrue(checkElementTextCustom("HOUSEHOLD MEMBERS", "CapitalizedTextView"));
		
		Assert.assertTrue(checkElementTextViewReturn("Brad Allen (35)"));
		Assert.assertTrue(checkElementTextViewReturn("Melissa Allen (32)"));
		Assert.assertTrue(checkElementTextViewReturn("Elizabeth Allen (2)"));
		Assert.assertTrue(checkElementTextViewReturn("Maggie June Allen (0)"));
		
		//Assert.assertFalse(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("Allen, Bradley Jay"));
		Assert.assertTrue(checkElementTextCustom("FULL NAME", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("000-3597-284A"));
		Assert.assertTrue(checkElementTextCustom("RECORD NUMBER", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("June 24, 1979 (35)"));
		Assert.assertTrue(checkElementTextCustom("BIRTH DATE", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("Elder"));
		Assert.assertTrue(checkElementTextCustom("PRIESTHOOD OFFICE", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("Oct 2014 (Expired)"));
		Assert.assertTrue(checkElementTextCustom("TEMPLE RECOMMEND", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("Ordinances"));
		Assert.assertTrue(checkElementTextViewReturn("Marriage"));
		Assert.assertTrue(checkElementTextViewReturn("Other Information"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		Thread.sleep(1000);
		//Check the Drawer items
		clickButtonByXpath("Drawer");
		Assert.assertTrue(checkElementTextViewReturn("Directory"));
		Assert.assertTrue(checkElementTextViewReturn("Callings"));
		Assert.assertTrue(checkElementTextViewReturn("Missionary"));
		Assert.assertTrue(checkElementTextViewReturn("Lists"));
		Assert.assertTrue(checkElementTextViewReturn("Calendar"));
		Assert.assertTrue(checkElementTextViewReturn("Meetinghouses"));
		Assert.assertTrue(checkElementTextViewReturn("Reports"));
		
		//Reports
		clickButtonByXpath("DrawerReports");
		//Assert.assertTrue(checkElementTextViewReturn("Action and Interview List"));
		Assert.assertTrue(checkElementTextViewReturn("Birthday List"));
		Assert.assertTrue(checkElementTextViewReturn("Members Moved In"));
		Assert.assertTrue(checkElementTextViewReturn("Members Moved Out"));
		Assert.assertTrue(checkElementTextViewReturn("Members with Callings"));
		Assert.assertTrue(checkElementTextViewReturn("Members without Callings"));
		Assert.assertTrue(checkElementTextViewReturn("New Members"));
		Assert.assertTrue(checkElementTextViewReturn("Temple Recommend Status"));
		Assert.assertTrue(checkElementTextViewReturn("Unit Statistics"));
		Assert.assertFalse(checkElementTextViewReturn("Death Star Reports"));
		
		
		//Check the members moved out report
		//Should have a ( ) with the age by the birth date
		clickButtonByXpathTitleName("Members Moved Out");
		Assert.assertTrue(checkElementTextViewReturn("Warehime, John"));
		//Birth Date
		//TODO need to have the age calculated
		Assert.assertTrue(checkElementTextViewReturn("August 8, 1969 (45)"));
		Assert.assertTrue(checkElementTextViewReturn("March 29, 2015"));
		Assert.assertFalse(checkElementTextViewReturn("Solo, Han"));
		
		pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members Moved In
		clickButtonByXpathTitleName("Members Moved In");
		Assert.assertTrue(checkElementTextViewReturn("Jeffs"));
		Assert.assertTrue(checkElementTextViewReturn("Travis (39)"));
		Assert.assertTrue(checkElementTextViewReturn("Head of household"));

		
		pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members with Callings
		clickButtonByXpathTitleName("Members with Callings");
		Assert.assertTrue(checkElementTextViewReturn("Allen, Brad"));
		Assert.assertTrue(checkElementTextViewReturn("Primary Teacher (2 years)"));
		clickButtonByXpathTitleName("ORGANIZATION");

	}
	
	/** bishopMemberOfSeparateStake()
	 * Bishop that is a member of a separate stake
	 * 
	 * @throws Exception
	 */
	public void bishopMemberOfSeparateStake() throws Exception {
		//int myCheck;
		//LDSTools2 Bishop that is a member of a separate stake
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Check to see if the user can view the directory
		Assert.assertTrue(checkElementTextViewRoboReturn("Aaron, Jane"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Vader, Darth"));
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS2");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS2");
		clickLastTextViewRoboReturn("Tools, LDS2");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS2"));
		//clickButtonByXpathTitleName("Show Record Number");
		Assert.assertTrue(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("888-0028-7023"));
		Assert.assertTrue(checkElementTextCustom("RECORD NUMBER", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("January 1, 1980 (35)"));
		Assert.assertTrue(checkElementTextCustom("BIRTH DATE", "TextView"));
		
		//Check Ordinances
		clickButtonByXpathTitleName("Ordinances");
		Assert.assertTrue(checkElementTextViewReturn("Baptism"));
		Assert.assertTrue(checkElementTextViewReturn("May 5, 2005"));
		Assert.assertTrue(checkElementTextViewReturn("Confirmation"));
		Assert.assertTrue(checkElementTextViewReturn("May 5, 2005"));
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
		
		//pressBackKey();
		//Collapse the search 
		clickButtonByXpath("Back");
		clickButtonByXpath("SearchCollapse");
		

		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Aaron, Jane");
		
		
		//Directory items that should not be visible
		clickItemByXpathRoboText("Aaron, Jane");
		clickLastTextViewRoboReturn("Aaron, Jane");
		
		
		Assert.assertTrue(checkElementTextViewReturn("Jane Aaron"));
		Assert.assertTrue(checkElementTextViewReturn("Fagamalo 1st Ward"));

		Assert.assertTrue(checkElementTextCustom("CONTACT INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("555-555-5555"));
		Assert.assertTrue(checkElementTextCustom("PERSONAL", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("555-555-1234"));
		Assert.assertTrue(checkElementTextCustom("HOUSEHOLD", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("no-reply@ldschurch.org"));
		Assert.assertTrue(checkElementTextCustom("PERSONAL", "TextView"));
		//Assert.assertTrue(checkElementTextViewReturn("2778 E Saddle Rock Rd Eagle Mountain, Utah 84005"));
		
		Assert.assertTrue(checkElementTextCustom("MEMBERSHIP INFORMATION", "CapitalizedTextView"));
		Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member2"));
		Assert.assertTrue(checkElementTextCustom("FULL NAME", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("888-0028-4326"));
		Assert.assertTrue(checkElementTextCustom("RECORD NUMBER", "TextView"));
		Assert.assertTrue(checkElementTextViewReturn("November 11, 1960 (54)"));
		
		//Check Ordinances
		clickButtonByXpathTitleName("Ordinances");
		Assert.assertTrue(checkElementTextViewReturn("Baptism"));
		Assert.assertTrue(checkElementTextViewReturn("November 11, 1970"));
		Assert.assertTrue(checkElementTextViewReturn("Confirmation"));
		Assert.assertTrue(checkElementTextViewReturn("November 11, 1970"));
		pressBackKey();
		
		/*
		//Check Other Information
		clickButtonByXpathTitleName("Other Information");
		Assert.assertTrue(checkElementTextViewReturn("Gender"));
		Assert.assertTrue(checkElementTextViewReturn("Female"));
		Assert.assertTrue(checkElementTextViewReturn("Birth Country"));
		Assert.assertTrue(checkElementTextViewReturn("United States"));
		pressBackKey();
		*/

		Assert.assertTrue(checkElementTextCustom("HOUSEHOLD MEMBERS", "CapitalizedTextView"));
		
		Assert.assertTrue(checkElementTextViewReturn("Jane Aaron (54)"));

		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		Thread.sleep(1000);
		//Check the Drawer items
		clickButtonByXpath("Drawer");
		Assert.assertTrue(checkElementTextViewReturn("Directory"));
		Assert.assertTrue(checkElementTextViewReturn("Callings"));
		Assert.assertTrue(checkElementTextViewReturn("Missionary"));
		Assert.assertTrue(checkElementTextViewReturn("Lists"));
		Assert.assertTrue(checkElementTextViewReturn("Calendar"));
		Assert.assertTrue(checkElementTextViewReturn("Meetinghouses"));
		Assert.assertTrue(checkElementTextViewReturn("Reports"));

		
		//Callings
		//clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerCallings");
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishopric"));
		Assert.assertTrue(checkElementTextViewRoboReturn("High Priests Group"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Elders Quorum"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ward Missionaries"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Other Callings"));
		
		//Bishopric
		clickItemByXpathRoboText("Bishopric");
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishop"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ami, Samu"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishopric First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Atia, Aviata Seualuga"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Bishopric Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faapili, Muipu"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ward Executive Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sitivi, Sitivi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ward Clerk"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tutunoa, Ualesi Junior, Jr"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ward Assistant Clerk"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sitivi, Tama Kiliona"));
		pressBackKey();
		
		//High Priests Group
		clickItemByXpathRoboText("High Priests Group");
		clickItemByXpathRoboText("High Priests Group Leadership");
		Assert.assertTrue(checkElementTextViewRoboReturn("High Priests Group Leader"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faamoe, Panapa Filifili"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		//Elders Quorum
		clickItemByXpathRoboText("Elders Quorum");
		clickItemByXpathRoboText("Elders Quorum Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Elders Quorum President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Peaulele"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		
		//Relief Society
		clickItemByXpathRoboText("Relief Society");
		clickItemByXpathRoboText("Relief Society Presidency");
		//displayAllTextViewElements();
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society President"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Frost, Sato'a"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faamoetauloa, Fiasili"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Faapili, Baby"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Relief Society Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Patiole, Luafa"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		//Young Men
		clickItemByXpathRoboText("Young Men");
		clickItemByXpathRoboText("Young Men Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Lavea, Vaelaa"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Peaulele"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Men Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Venasio Fainuu, Fogavai"));
		pressBackKey();
		clickItemByXpathRoboText("Priests Quorum");
		clickItemByXpathRoboText("Priests Quorum Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Priests Quorum President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ami, Samu"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		
		//Young Women
		clickItemByXpathRoboText("Young Women");
		clickItemByXpathRoboText("Young Women Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Tutunoa, Lusi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Lavea, Meise"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Women Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Lavea, Lonise"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		
		//Sunday School
		clickItemByXpathRoboText("Sunday School");
		clickItemByXpathRoboText("Sunday School Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Lealaiauloto, Uana Iosefa Sao"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Mene, Sitivi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Sunday School Second Counselor"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Apofasa, Sasa'a"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		//Primary
		clickItemByXpathRoboText("Primary");
		clickItemByXpathRoboText("Primary Presidency");
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary President"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Tutulu"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary First Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Ami, Lealofi"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary Second Counselor"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Fepuleai, Malele Seuamuli"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Primary Secretary"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samu, Luisa"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		
		//Ward Missionaries
		clickItemByXpathRoboText("Ward Missionaries");
		Assert.assertTrue(checkElementTextViewRoboReturn("Mission Leader"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Lafaele"));
		pressBackKey();
		
		
		//Other Callings
		clickItemByXpathRoboText("Other Callings");
		clickItemByXpathRoboText("Young Single Adult");
		Assert.assertTrue(checkElementTextViewRoboReturn("Young Single Adult Leader"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Solomona, Solomona"));
		pressBackKey();
		clickItemByXpathRoboText("Music");
		Assert.assertTrue(checkElementTextViewRoboReturn("Music Adviser"));
		//Assert.assertTrue(checkElementTextViewRoboReturn("Frost,Sato'a"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Organist or Pianist"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Betham, Maria"));
		pressBackKey();
		Thread.sleep(2000);
		pressBackKey();
		
		//Missionary
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerMissionary");
		Assert.assertTrue(checkElementTextViewRoboReturn("Elder Tearoa Tuala"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samoa Apia Mission"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Elder Wyatt Wallwork"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Samoa Apia Mission"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Kitara, Lafaele"));
		Assert.assertTrue(checkElementTextViewRoboReturn("Mission Leader"));
		//pressBackKey();
		
		
		//Reports
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerReports");
		//Assert.assertTrue(checkElementTextViewReturn("Action and Interview List"));
		Assert.assertTrue(checkElementTextViewReturn("Birthday List"));
		Assert.assertTrue(checkElementTextViewReturn("Members Moved In"));
		Assert.assertTrue(checkElementTextViewReturn("Members Moved Out"));
		Assert.assertTrue(checkElementTextViewReturn("Members with Callings"));
		Assert.assertTrue(checkElementTextViewReturn("Members without Callings"));
		Assert.assertTrue(checkElementTextViewReturn("New Members"));
		Assert.assertTrue(checkElementTextViewReturn("Temple Recommend Status"));
		Assert.assertTrue(checkElementTextViewReturn("Unit Statistics"));
		Assert.assertFalse(checkElementTextViewReturn("Death Star Reports"));
		
		
		//Check the members moved out report
		//Should have a ( ) with the age by the birth date
		clickButtonByXpathTitleName("Members Moved Out");
		Assert.assertTrue(checkElementTextViewReturn("Fosi-Seuoti, Malologa"));
		//Birth Date
		//TODO need to have the age calculated
		Assert.assertTrue(checkElementTextViewReturn("April 16, 1990 (24)"));
		Assert.assertTrue(checkElementTextViewReturn("February 21, 2015"));
		Assert.assertTrue(checkElementTextViewReturn("Fagamalo  2nd Ward"));
		Assert.assertFalse(checkElementTextViewReturn("Solo, Han"));
		
		pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members Moved In
		clickButtonByXpathTitleName("Members Moved In");
		Assert.assertTrue(checkElementTextViewReturn("Eubank"));
		Assert.assertTrue(checkElementTextViewReturn("Kent (41)"));
		Assert.assertTrue(checkElementTextViewReturn("Head of household"));
		Assert.assertFalse(checkElementTextViewReturn("Skywalker, Luke"));

		
		pressBackKey();
		//clickButtonByXpath("Drawer");
		//clickButtonByXpath("DrawerReports");
		
		//Members with Callings
		clickButtonByXpathTitleName("Members with Callings");
		Assert.assertTrue(checkElementTextViewReturn("Ami, Lealofi"));
		Assert.assertTrue(checkElementTextViewReturn("Primary First Counselor (1 year, 1 month)"));
		Assert.assertFalse(checkElementTextViewReturn("Skywalker, Anakin"));
		
		clickButtonByXpathTitleName("ORGANIZATION");
		Assert.assertTrue(checkElementTextViewReturn("Bishop"));
		Assert.assertTrue(checkElementTextViewReturn("Ami, Samu (1 year, 6 months)"));
		Assert.assertFalse(checkElementTextViewReturn("Kenobi, Obi-Wan"));
		
		clickButtonByXpathTitleName("DURATION");
		Assert.assertTrue(checkElementTextViewReturn("Sunday School President"));
		Assert.assertTrue(checkElementTextViewReturn("Lealaiauloto, Uana Iosefa Sao"));
		Assert.assertFalse(checkElementTextViewReturn("Amidala, Padme"));
		
		clickButtonByXpathTitleName("NOT SET APART");
		Assert.assertTrue(checkElementTextViewReturn("Ward Assistant Clerk (3 years, 1 month)"));
		Assert.assertTrue(checkElementTextViewReturn("Sitivi, Tama Kiliona"));
		Assert.assertFalse(checkElementTextViewReturn("P0, C3"));
		pressBackKey();
		
		//Members without Callings
		clickButtonByXpathTitleName("Members without Callings");
		//displayAllTextViewElements();
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEleven, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("D2, R2"));
		
		clickButtonByXpathTitleName("MALE");
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEleven, Member"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPFifteen, Member"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Binks, Jarjar"));
		
		clickButtonByXpathTitleName("FEMALE");
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPEighteen, Member"));
		Assert.assertTrue(checkElementTextViewRoboReturn("AFPFive, Wife"));
		Assert.assertFalse(checkElementTextViewRoboReturn("Organa, Leia"));
		
		pressBackKey();
		
		
		//New Members
		clickButtonByXpathTitleName("New Members");
		Assert.assertTrue(checkElementTextViewReturn("Joezmal, Loana"));
		Assert.assertTrue(checkElementTextViewReturn("13"));
		Assert.assertTrue(checkElementTextViewReturn("F"));
		Assert.assertTrue(checkElementTextViewReturn("March 15, 2015"));
		Assert.assertTrue(checkElementTextViewReturn("Member"));
		Assert.assertFalse(checkElementTextViewReturn("Hutt, Jabba"));
		pressBackKey();
		
		//Temple Recommend Status
		clickButtonByXpathTitleName("Temple Recommend Status");
		Assert.assertTrue(checkElementTextViewReturn("AFPMisc, Member15"));
		Assert.assertFalse(checkElementTextViewReturn("Ahsoka, Tano"));
		//Assert.assertTrue(checkElementTextViewReturn("Expired"));
		
		clickButtonByXpathTitleName("ACTIVE");
		Assert.assertTrue(checkElementTextViewReturn("Betham, Maria"));
		Assert.assertTrue(checkElementTextViewReturn("Jul 2016"));
		Assert.assertFalse(checkElementTextViewReturn("Maul, Darth"));
		
		clickButtonByXpathTitleName("EXPIRING");
		Assert.assertTrue(checkElementTextViewReturn("Ami, Lealofi"));
		Assert.assertFalse(checkElementTextViewReturn("Windu, Mace"));
		
		clickButtonByXpathTitleName("EXPIRED");
		Assert.assertTrue(checkElementTextViewReturn("Alavaa, Toetoe"));
		Assert.assertFalse(checkElementTextViewReturn("Jinn, Qui-Gon"));
		
		clickButtonByXpathTitleName("OTHER");
		Assert.assertTrue(checkElementTextViewReturn("Mene, Matagalu"));
		Assert.assertFalse(checkElementTextViewReturn("Calrissian, Lando"));
		pressBackKey();
		
		//Unit Statistics
		clickButtonByXpathTitleName("Unit Statistics");
		Assert.assertTrue(checkElementTextViewReturn("631"));
		Assert.assertTrue(checkElementTextViewReturn("286"));
		Assert.assertTrue(checkElementTextViewReturn("14"));
		Assert.assertFalse(checkElementTextViewReturn("8675309"));
		
	}
	
	
	/** editCurrentUser()
	 * Login as LDSTools100 and edit own information
	 * 
	 * @throws Exception
	 */
	public void editCurrentUser() throws Exception {
		//Edit own information
		syncLogIn("LDSTools100", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS100");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS100");
		clickLastTextViewRoboReturn("Tools, LDS100");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS100"));
		clickButtonByXpath("MenuEdit");
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS100");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS100");
		clickLastTextViewRoboReturn("Tools, LDS100");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS100"));
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		clickButtonByXpath("MenuEdit");
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertFalse(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertFalse(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertFalse(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertFalse(checkElementTextViewReturn("home@nospam.com"));
	}
	
	/** editOtherUser()
	 * Edit a user that you are not logged in as. 
	 * 
	 * @throws Exception
	 */
	public void editOtherUser() throws Exception {
		//Edit other user
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		clickButtonByXpath("MenuEdit");
		
		sendTextbyXpath("EditPersonalPhone", "1(801)240-0104");
		sendTextbyXpath("EditHomePhone", "(801) 867-5309");
		sendTextbyXpath("EditPersonalEmail", "personal@nospam.com");
		sendTextbyXpath("EditHomeEmail", "home@nospam.com");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		Assert.assertTrue(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertTrue(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertTrue(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertTrue(checkElementTextViewReturn("home@nospam.com"));
		
		clickButtonByXpath("MenuEdit");
		
		clearTextFieldXpath("EditPersonalPhone");
		clearTextFieldXpath("EditHomePhone");
		clearTextFieldXpath("EditPersonalEmail");
		clearTextFieldXpath("EditHomeEmail");

		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertFalse(checkElementTextViewReturn("1(801)240-0104"));
		Assert.assertFalse(checkElementTextViewReturn("(801) 867-5309"));	
		Assert.assertFalse(checkElementTextViewReturn("personal@nospam.com"));
		Assert.assertFalse(checkElementTextViewReturn("home@nospam.com"));
				
	}
	
	
	public void editOtherUserInvalidPhone() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		clickButtonByXpath("MenuEdit");
		
		sendTextbyXpath("EditPersonalPhone", "######00000000000*****");
		sendTextbyXpath("EditHomePhone", "878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542");
		clickButtonByXpath("MenuSave");
		
		Thread.sleep(3000);
		
		Assert.assertTrue(checkElementTextViewReturn("######00000000000*****"));
		Assert.assertTrue(checkElementTextViewReturn("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542"));	

		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
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
		
		Assert.assertFalse(checkElementTextViewReturn("######00000000000*****"));
		Assert.assertFalse(checkElementTextViewReturn("878974131648413216421321165484789798461321314644444244624424524245244545644644856465784967465456464144134424342446244323644524452344623446542326342542"));	

	}
	
	public void editOtherUserInvalidEmail() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools2", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS41"));
		clickButtonByXpath("MenuEdit");

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
		
		Assert.assertFalse(checkElementTextViewReturn("thisisaninvalidemailaddress"));
		Assert.assertFalse(checkElementTextViewReturn("!@#$%^&*()_+-=[]{}|"));	

		
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSYNC");
		clickButtonByXpath("AlertOK");
		
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS41");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS41");
		clickLastTextViewRoboReturn("Tools, LDS41");
		Thread.sleep(1000);

		Assert.assertFalse(checkElementTextViewReturn("thisisaninvalidemailaddress"));
		Assert.assertFalse(checkElementTextViewReturn("!@#$%^&*()_+-=[]{}|"));	
	}
	
	
	public void editVisibility() throws Exception {
		//Edit other user with invalid data - phone
		syncLogIn("LDSTools5", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS5");
		clickLastTextViewRoboReturn("Tools, LDS5");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		scrollDown("Stake Visibility", -1000 );
		//clickButtonByXpath("EditVisibiltySpinner");

		clickButtonByXpath("EditAllVisibility");
		//displayAllTextViewElements();
		clickButtonByXpathPopoutMenu("Private—Leadership Only");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		
		syncLogIn("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		Assert.assertTrue(checkElementTextViewRoboReturn("Tools, LDS5"));

		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		
		syncLogIn("LDSTools5", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		//Select the user
		clickItemByXpathRoboText("Tools, LDS5");
		clickLastTextViewRoboReturn("Tools, LDS5");
		Thread.sleep(1000);
		
		//Check the users name, address membership number etc...
		Assert.assertTrue(checkElementTextViewReturn("Tools, LDS5"));
		clickButtonByXpath("MenuEdit");
		Thread.sleep(1000);
		scrollDown("Private—Leadership Only", -1000 );
		//clickButtonByXpath("EditVisibiltySpinner");

		clickButtonByXpath("EditAllVisibility");
		//displayAllTextViewElements();
		clickButtonByXpathPopoutMenu("Stake Visibility");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(1000);
		clickButtonByXpath("MenuSave");
		Thread.sleep(3000);
		pressBackKey();
		pressBackKey();
		
		//Collapse the search 
		clickButtonByXpath("SearchCollapse");
		
		//Log out 
		clickButtonByXpath("Drawer");
		clickButtonByXpath("DrawerSETTINGS");
		
		clickButtonByXpathTitleName("Sign Out");
		clickButtonByXpath("SignOutOK");
		
		syncLogIn("LDSTools6", "toolstester", "UAT" );
		Thread.sleep(2000);
		
		//true will setup ping for a non-leader
		pinPage("1", "1", "3", "3", true);
		Thread.sleep(2000);
		
		//Search for logged in user
		clickButtonByID("MenuSearch");
		sendTextbyXpath("SearchArea", "Tools, LDS5");
		
		Assert.assertTrue(checkElementTextViewRoboReturn("Tools, LDS5"));
	}
	
	/** invalidLoginCheck()
	 * Test invalid logins to LDS Tools
	 * 
	 * @throws Exception
	 */
	public void invalidLoginCheck() throws Exception {
		//Invalid login test
		syncLogIn("LDSTools2", "<login>", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Incorrect username or password"));
		clickButtonByXpath("AlertOK");	
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("LDSTools2", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Incorrect username or password"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Incorrect username or password"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("LDSTools2", "test|test|test$$$$test|||||||test", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Incorrect username or password"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("zmaxfield", "%%%test%%%% & ||||||| select * from household;", "Production" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Incorrect username or password"));
		clickButtonByXpath("AlertOK");
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		
		syncLogIn("", "", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("LDSTools2", "", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
		
		syncLogIn("", "toolstester", "UAT" );
		Thread.sleep(2000);
		Assert.assertTrue(checkElementTextViewReturn("Sign in to your LDS Account"));
		
		//Clear the login and password fields
		clearTextFieldXpath("LoginUsername");
		clearTextFieldXpath("LoginPassword");
	}
	
	
	/**loginCheck()
	 * Go through All LDSTools users to make sure they can login
	 * 
	 * @throws Exception
	 */
	public void loginCheck() throws Exception {
		String password1 = "toolstester";
		String password2 = "password1";
		boolean pinCheck = true;
		
		
		for (int myCounter = 2 ; myCounter <= 47; myCounter++ ){
			System.out.println("USER: LDSTools" + myCounter);
			if (myCounter <= 15){
				syncLogIn("LDSTools" + myCounter, password1, "UAT" );
			} else {
				syncLogIn("LDSTools" + myCounter, password2, "UAT" );
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
		Assert.assertEquals(driver.findElement(By.xpath(this.prop.getProperty(textElement))).getText(),(textToCheck));	
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
		Assert.assertEquals(driver.findElement(By.id(this.prop.getProperty(textElement))).getText(),(textToCheck));
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
		List<WebElement> options= driver.findElements(By.xpath("//TextView[@value='" + textElement + "']"));
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
		List<WebElement> options= driver.findElements(By.xpath("//TextView[contains(@value,'" + textElement + "')]"));
		if (options.isEmpty()) {
			myReturnStatus = false;	
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
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
		List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@value='" + textElement + "']"));
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
		List<WebElement> options= driver.findElements(By.xpath("//" + customText + "[@value='" + textElement + "']"));
		if (options.isEmpty()) {
			myReturnStatus = false;
		} else {
			myReturnStatus = true;
		}
		
		return myReturnStatus;
	}
	
	/** displayAllTextViewElements()
	 * For debugging - will display the text of the element. 
	 * 
	 */
	private void displayAllTextViewElements() {
		List<WebElement> options= driver.findElements(By.xpath("//CheckedTextView"));
		for (int i = 0 ; i < options.size(); i++ ) {
			System.out.println(options.get(i).getText());
		}
		

	}

	
	/** clickLastTextViewRoboReturn(String textElement)
	 * This will match the last element found 
	 * 
	 * @param textElement - text of an element
	 */
	private void clickLastTextViewRoboReturn(String textElement) {
		int myCounter;
		List<WebElement> options= driver.findElements(By.xpath("//RobotoTextView[@value='" + textElement + "']"));
		myCounter = options.size() - 1;
		options.get(myCounter).click();
	
	}
	

	/** clickButtonByID(String textElement )
	 * Click an element that by ID
	 * 
	 * @param textElement - ID of element must be found if uiMap
	 */
	private void clickButtonByID(String textElement ) {
		driver.findElement(By.id(this.prop.getProperty(textElement))).click();
	}
	

	/** clickButtonByXpath(String textElement)
	 * Click an element that by Xpath
	 * 
	 * @param textElement - Xpath of element must be found if uiMap
	 */
	private void clickButtonByXpath(String textElement) {
		driver.findElement(By.xpath(this.prop.getProperty(textElement))).click();
	}
	
	
	
	
	/** clickButtonByXpathTitleName(String textElement )
	 * Click the button that has the xpath of //TextView[@value" TEXT OF ELEMENT "]
	 * 
	 * @param textElement - text of element
	 */
	private void clickButtonByXpathTitleName(String textElement ) {
		//WebElement element;
		
		driver.findElement(By.xpath("//TextView[@value='" + textElement + "']")).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** clickItemByXpathRoboText(String textElement )
	 * Click the button that has the xpath of //RobotoTextView[@value" TEXT OF ELEMENT "]
	 * 
	 * @param textElement - text of element
	 */
	private void clickItemByXpathRoboText(String textElement ) {
		//WebElement element;
		
		driver.findElement(By.xpath("//RobotoTextView[@value='" + textElement + "']")).click();
		
		//I don't really like this sleep but it seems to be needed 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	
	/**clickButtonByXpathPopoutMenu(String textElement )
	 * Click elements by xpath on the popout menu
	 * 
	 * @param textElement - text of the element
	 */
	private void clickButtonByXpathPopoutMenu(String textElement ) {
		//WebElement element;
		
		//System.out.println("Element: " + textElement );
		//driver.findElement(By.xpath("//TintCheckedTextView[@value='" + textElement + "']")).click();
		//driver.findElement(By.xpath("//CheckedTextView[@value='" + textElement + "']")).click();
		driver.findElement(By.xpath("//*[@value='" + textElement + "']")).click();
		
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
		driver.findElement(By.xpath(this.prop.getProperty(textElement))).sendKeys(textToSend);
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
		textElement = "//TextView[@value='" + textElement + "']";
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
		WebElement myElement = driver.findElement(By.xpath("//TextView[@value='" + textElement + "']"));
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
	private void pressBackKey() {
		new Actions(driver).sendKeys(SelendroidKeys.BACK).perform();
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
	private void syncLogIn(String loginName, String loginPassword, String chooseNetwork )  throws Exception {
		//If the login is using any of the test networks we need to chagne it. 
		//valid enteries "Production", "UAT", "Proxy - UAT", "Proxy - Test"
		if (!chooseNetwork.equals("Production")) {
			Thread.sleep(1000);
			longPressByTextView("Sign in to your LDS Account");
			Thread.sleep(1000);
			longPressByTextView("Sign in to your LDS Account");
			Thread.sleep(1000);
			clickButtonByXpath("Menu");
			clickButtonByXpathTitleName("Settings");
			//Thread.sleep(1000);
			//scrollDown("Sign Out", 40 );
			Thread.sleep(2000);
			scrollDown("Network Environment", -1000 );
			Thread.sleep(2000);
			clickButtonByXpathPopoutMenu("UAT");
			clickButtonByXpath("Back");
			Thread.sleep(5000);
		}
		//sendTextbyXpath("LoginUsername", "LDSTools14");
		//sendTextbyXpath("LoginPassword", "toolstester");
		sendTextbyXpath("LoginUsername", loginName);
		sendTextbyXpath("LoginPassword", loginPassword);
		Thread.sleep(1000);
		clickButtonByXpath("SignInButton");
		Thread.sleep(4000);
		waitForTextToDisappear("SyncText", 500 );
		Thread.sleep(2000);
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
		
		//If this is a non-leader account the PIN message will be different
		myCheck = checkTextByXpathReturn("AlertMessage", "Please create a PIN to protect sensitive data available to leaders.");
		if ((myCheck == 1) || (nonLeaderPin)){
			clickButtonByXpath("AlertOK");
			
			checkTextByXpath("PinTitle", "Choose your PIN");
			clickButtonByXpath("PinKey" + digit1);
			clickButtonByXpath("PinKey" + digit2);
			clickButtonByXpath("PinKey" + digit3);
			clickButtonByXpath("PinKey" + digit4);
			
			checkTextByXpath("PinTitle", "Confirm PIN");
			clickButtonByXpath("PinKey" + digit1);
			clickButtonByXpath("PinKey" + digit2);
			clickButtonByXpath("PinKey" + digit3);
			clickButtonByXpath("PinKey" + digit4);
		} else {
			clickButtonByXpath("AlertNotNow");
		}

	}
	

	@After
	public void teardown() {
		driver.quit();
	}
	
	
	@Before
	public void openGuiMap() {
		
		File file = new File("ConfigFiles/uiMap.properties");
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
