package com.ibm.filenet;

import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;
import filenet.vw.server.VWField;

public class WorkflowTester {
	public final String FILENET_URL = "http://yfilenet:9080/wsi/FNCEWS40MTOM/";
	public final String FILENET_USERNAME= "p8admin";
	public final String FILENET_PASSWORD = "123456";
	public final String FILENET_CP = "cp1";
	public final String FILENET_WORKFLOW_NAME = "Code1";
	
	public String[] cl = new String[]{};
	
	public VWSession session;
	
	public static void main(String[] args) {
		WorkflowTester tester = new WorkflowTester();
		tester.connect();
		System.out.println("PE Server Name" + tester.session.getPEServerName());
		
		//listWorkflows();
		
		tester.createVW();
		tester.logoff();
	}
	
	public void lisWorkflows() {
		System.out.println("Listing all workflows");
		for(String s: session.fetchWorkClassNames(true))
		{
			System.out.println(s);
		}
	}
	
	public void connect() {
		session = new VWSession(FILENET_USERNAME, FILENET_PASSWORD, FILENET_CP);
		session.setBootstrapPEURI(FILENET_URL);
		session.setBootstrapCEURI(FILENET_URL);
		session.logon(FILENET_USERNAME, FILENET_PASSWORD, FILENET_CP);
		//System.out.println("Session initiated");
		
	}

	public void createVW() {
		VWStepElement element = session.createWorkflow(FILENET_WORKFLOW_NAME);
		//System.out.println("Step NAME: " + element.getStepName()); //LaunchStep -> auto dispatch it
		element.doDispatch();
		System.out.println("Workflow Element created and dispatched");
	}
	
	public void logoff() {
		session.logoff();
		System.out.println("Session terminated");
	}
}
