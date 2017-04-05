package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;
import utility.Log;

/*
 * Issues & solutions:
 * http://stackoverflow.com/questions/26458806/noclassdeffounderror-org-slf4j-logger
 * http://stackoverflow.com/questions/25487116/log4j2-configuration-no-log4j2-configuration-file-found
 */
public class DriverScript {
	//This is a class object, declared as 'public static'
	//So that it can be used outside the scope of main[] method
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	//This is reflection class object, declared as 'public static'
	//So that it can be used outside the scope of main[] method
	public static Method method[];
	
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static boolean bResult;

	//Here we are instantiating a new object of class 'ActionKeywords'
	public static void InitDriverScript() throws NoSuchMethodException, SecurityException{
		actionKeywords = new ActionKeywords();
		//This will load all the methods of the class 'ActionKeywords' in it.
        //It will be like array of method, use the break point here and do the watch
		method = actionKeywords.getClass().getMethods();
	}

    public static void main(String[] args) throws Exception {
    	// initiate methods
    	InitDriverScript();

    	//Declaring the path of the Excel file with the name of the Excel file
    	String sPath = Constants.Path_TestData;    

    	//Here we are passing the Excel path and SheetName to connect with the Excel file
        //This method was created in the last chapter of 'Set up Data Engine' 		
    	ExcelUtils.setExcelFile(sPath);
    	
    	// This is the start of the log4j logging in the test case
    	DOMConfigurator.configure(Constants.Path_logXml);
    	
    	// Declaring String variable for storing Object Repository path
    	String Path_OR = Constants.Path_OR;
    	// Creating file system object for Object Repository text/property file
    	FileInputStream fs = new FileInputStream(Path_OR);
    	// Creating an Object of properties
    	OR = new Properties(System.getProperties());
    	// Loading all the properties from Object Repository property file in to OR object
    	OR.load(fs);
    	
    	DriverScript startEngine = new DriverScript();
    	startEngine.execute_TestCase();
    }
    
    private void execute_TestCase() throws Exception {
    	// Lets get the total number of test cases mentioned in the Test cases sheet
    	int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
    	// This loop will execute the number of times equal to total number of test cases
    	for (int iTestCase = 1; iTestCase<=iTotalTestCases; iTestCase++) {
    		// set to true at start execution of each test case
    		bResult = true;
    		// This is to get the test case name from the test cases sheet
    		sTestCaseID = ExcelUtils.getCellData(iTestCase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
    		// This is to get the value of the Run Mode column for the current test case
    		sRunMode = ExcelUtils.getCellData(iTestCase, Constants.Col_RunMode, Constants.Sheet_TestCases);
    		// This is the condition statement on RunMode value
    		if (sRunMode != null && sRunMode.equals("Yes")) {
    			// Only execute this part of code if the value of Run Mode is 'Yes'
    			iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
    			iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
    			Log.startTestCase(sTestCaseID);
    			// This loop will execute number of times equal to Total number of test steps
		    	// This is the loop for reading the values of the column (Action Keyword) row by row
				// It means this loop will execute all the steps mentioned for the test case in Test Steps sheet
	    		// set to true at start execution of each test step
	    		bResult = true;
		    	for (;iTestStep<=iTestLastStep;iTestStep++){
				    //This to get the value of column Action Keyword from the excel
		    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
		            //A new separate method as below is created with the name 'execute_Actions'
					//So this statement is doing nothing but calling that piece of code to execute
		    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
		    		execute_Actions();
		    		//check if result fails
		    		if (!bResult) {
		    			// if 'false' then write test result as FAIL
		    			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestCase, Constants.Col_CaseResult, Constants.Sheet_TestCases);
		    			break;
		    			}
		    		}
		    		//This will only execute after the last step of the test case
	    			if (bResult) {
	    				// if 'true' then write test case result as PASS
	    				ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestCase, Constants.Col_CaseResult, Constants.Sheet_TestCases);   					
	    			}
	    			Log.endTestCase(sTestCaseID);
		    		}
    	}
    }
	
	//This method contains the code to perform some action (test step)
	//As it is completely different set of logic, which revolves around the action only,
	//It makes sense to keep it separate from the main driver script
    private static void execute_Actions() throws Exception {
		//This is a loop which will run for the number of actions in the Action Keyword class 
		//method variable contain all the method and method.length returns the total number of methods
		String sResult; 
    	for(int i = 0;i < method.length;i++){
			//This is now comparing the method name with the ActionKeyword value got from excel
			if(method[i].getName().equals(sActionKeyword)){
				//In case of match found, it will execute the matched method
				// Passing 'Page Object' name and 'Action Keyword' as Arguments to this method
				// This code will pass three parameters to every invoke method
				method[i].invoke(actionKeywords, sPageObject, sData);
				sResult = bResult? Constants.KEYWORD_PASS : Constants.KEYWORD_FAIL;
				// Set PASS or FAIL to test steps result cell
				ExcelUtils.setCellData(sResult, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
				if (!bResult) {
					ActionKeywords.closeBrowser("", "");
				}
				//Once any method is executed, this break statement will take the flow outside of for loop
				break;
				}
			}
		}
 }