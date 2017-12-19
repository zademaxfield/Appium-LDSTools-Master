package LDSTest;

import java.util.Iterator;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer {
    private int retryCount         = 0;
    private int maxRetryCount     = 2;   // retry a failed test 2 additional times

    /*
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
    */
    
    @Override
    public boolean retry(ITestResult result) {
    	if (!result.isSuccess()) {
    		if (retryCount < maxRetryCount) {
    			retryCount++;
				result.setStatus(ITestResult.FAILURE);
				Reporter.setCurrentTestResult(result);
			
				//ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
				//tc.getFailedTests().getAllMethods().remove(Reporter.getCurrentTestResult().getMethod());
				//result.setStatus(ITestResult.SKIP);
				//tc.getPassedTests().addResult(result , Reporter.getCurrentTestResult().getMethod());
			
				
				//String message = Thread.currentThread().getName() + ": Error in " + result.getName() + " Retrying "
				//		+ (maxRetryCount + 1 - retryCount) + " more times";
				//System.out.println(message);
				//Reporter.log(message);
				return true;
			} else {
				result.setStatus(ITestResult.FAILURE);
				Reporter.setCurrentTestResult(result);
				
				//ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
				//tc.getFailedTests().getAllMethods().remove(Reporter.getCurrentTestResult().getMethod());
				//result.setStatus(ITestResult.FAILURE);
				//tc.getFailedTests().addResult(result , Reporter.getCurrentTestResult().getMethod());
			}
    	}
    	
      return false;
    }  
    
}


