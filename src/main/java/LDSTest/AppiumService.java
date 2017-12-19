package LDSTest;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumService {
	AppiumDriverLocalService myAppiumService;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void startAppiumService(String os, int myPort) throws Exception {
		System.out.println("OS: " + os);
		System.out.println("PORT: " + myPort);

		System.out.println("Creating Appium Service");
		File appiumLogFile = new File("screenshot/myAppiumLog.txt");
		new FileOutputStream(appiumLogFile, false).close();
		myAppiumService = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingPort(myPort)
				.withIPAddress("127.0.0.1")
				.withLogFile(appiumLogFile)
				.withArgument(GeneralServerFlag.LOG_LEVEL, "error")
				.build();
		System.out.println("Starting Appium");
		myAppiumService.start();



		//Android Setup
		/*
		if (os.equals("android")) {
			System.out.println("Creating Android Appium Service");
			File appiumLogFile = new File("screenshot/myAppiumLog.txt");
			new FileOutputStream(appiumLogFile, false).close();
			myAppiumService = new AppiumServiceBuilder()
					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
					.usingPort(myPort)
					.withIPAddress("127.0.0.1")
					.withLogFile(appiumLogFile)
					.withArgument(GeneralServerFlag.LOG_LEVEL, "error")
					.build();
			System.out.println("Starting Appium Android");
			myAppiumService.start();
		} else {
			File appiumLogFile = new File("screenshot/myAppiumLog.txt");
			new FileOutputStream(appiumLogFile, false).close();
			
			myAppiumService = new AppiumServiceBuilder()
					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
					.usingPort(myPort)
					.withIPAddress("127.0.0.1")
					.withLogFile(appiumLogFile)
					.withArgument(GeneralServerFlag.LOG_LEVEL, "error")
					.build();
			
			myAppiumService.start();
		}*/
	}
	
	public boolean appiumServiceRunning() {
		boolean runningStatus = false;
		
		runningStatus = myAppiumService.isRunning();
		System.out.println("Appium Service Running: " + runningStatus);
		
		return runningStatus;
		
	}
	
	public String appiumServiceURL() {
		String stringURL;
		
		stringURL = myAppiumService.getUrl().toString();
		System.out.println("Appium Service URL: " + stringURL);
		
		return stringURL;
		
	}
	
	public void stopAppiumService() {
		myAppiumService.stop();
	}

}