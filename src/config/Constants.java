package config;

public class Constants {
	//This is the list of our variables
    //Declared as 'public', so that it can be used in other classes of this project
    //Declared as 'static', so that we do not need to instantiate a class object
    //Declared as 'final', so that the value of this variable cannot be changed
    // 'String' & 'int' are the two data type for storing a type of value	
	public static final String URL = "http://www.store.demoqa.com";
	public static final String Path_TestData  = "E:\\hong\\workspace\\Practice Exercise\\src\\dataEngine\\DataEngine.xlsx";
	public static final String Path_logXml = "log4j.xml";
	public static final String Path_OR  = "E:\\hong\\workspace\\Practice Exercise\\src\\config\\OR.txt"; 
	public static final String File_TestData = "DataEngine.xlsx";
 
	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1 ;
	public static final int Col_PageObject =4 ;
	public static final int Col_ActionKeyword =5 ;
	public static final int Col_DataSet = 6;

	// New entry in Constant variable
	public static final int Col_RunMode = 2;
	
	// Two new constants variables to mark Fail or Pass
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
	// Define two new result column
	public static final int Col_CaseResult = 3;
	public static final int Col_TestStepResult = 7;
 
	//List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	// New entry in Constant variable
	public static final String Sheet_TestCases = "Test cases";

}
