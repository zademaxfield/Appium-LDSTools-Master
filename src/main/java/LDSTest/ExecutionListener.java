package LDSTest;


import java.io.File;
//import java.io.IOException;

//import org.apache.xml.utils.URI;
import org.testng.IExecutionListener;



public class ExecutionListener implements IExecutionListener {
	//File myFile = new File("test-output/emailable-report.html");
	File myFile = new File("test-output/custom-report.html");
	
	//myFile = "test-output/emailable-report.html";
	
	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		//System.out.println("Test Started!");
	}
	
	@Override
    public void onExecutionFinish() {
		String[] args = null;
		System.out.println("Test is finished... Sending email");
		/*
		try {
			java.awt.Desktop.getDesktop().open(myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		SendFileEmail.main(args);
	}


}