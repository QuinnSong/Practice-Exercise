package utility;
import org.apache.log4j.Logger;

/*
 * http://www.codejava.net/coding/how-to-configure-log4j-as-logging-mechanism-in-java
 */

public class Log {
	// Initiate Log4j logs
	private static Logger Log = Logger.getLogger(Log.class);
	// this is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase(String sTestCaseName) {
		   Log.info("+--------------------------------------------------------------------------------------+");
		   Log.info("|                                                                                      |");
		   Log.info("|                                     " + sTestCaseName );
		   Log.info("|                                                                                       ");
	}
	
	//This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName){
		 Log.info("|                                                                                       ");
		 Log.info("+                                      " + "-E---N---D-");
		 Log.info("+--------------------------------------------------------------------------------------+");
	   Log.info("+");
	   Log.info("+");
	   Log.info("+");
	   Log.info("+");
 
	   }
	
	// The following methods are created so that they can be called
	public static void info(String message) {
		Log.info(message);
		}

	public static void warn(String message) {
	   Log.warn(message);
	   }

	public static void error(String message) {
	   Log.error(message);
	   }

	public static void fatal(String message) {
	   Log.fatal(message);
	   }

	public static void debug(String message) {
	   Log.debug(message);
	   }

}
