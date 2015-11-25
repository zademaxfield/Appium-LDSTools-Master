package LDSTest;

import org.testng.IExecutionListener;

public class ExecutionListener implements IExecutionListener {
	
	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		System.out.println("Test Started!");
	}
	
	@Override
    public void onExecutionFinish() {
		String[] args = null;
		System.out.println("Notify by mail, TestNG is finished");
		SendFileEmail.main(args);
	}


}