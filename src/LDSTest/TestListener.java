package LDSTest;

import java.util.Set;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

public class TestListener implements ITestListener {
	LDSTools myLDSTools = new LDSTools();
	String os;
	String fileName;
	String testDevice;
	

    @Override
	public void onFinish(ITestContext context) {
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
	}
  
    public void onTestStart(ITestResult result) {   }
  
    public void onTestSuccess(ITestResult result) {   }
  
    public void onTestFailure(ITestResult result) {   }

    @Parameters({"os", "fileName", "testDevice"})
    public void onTestSkipped(ITestResult result) {  
    	System.out.println("SKIP found!");
    	System.out.println("OS: " + os);
    	System.out.println("File Name: " + fileName);
    	System.out.println("Test Device: " + testDevice);
    	
    	myLDSTools.driver.quit();
    	myLDSTools.myAppiumService.stop();
    	try {
			myLDSTools.setUp(os, fileName, testDevice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }
}  
