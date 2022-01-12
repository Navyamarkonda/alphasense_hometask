package alphasense.assignment.com.common;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public static  WebDriver driver;
	public  static ExtentReports extent;
	public  static ExtentSparkReporter spark;
	public  static  ExtentTest test;
	public  static String ScreenshotPath; 


	// initializing the driver and reporting
	@BeforeSuite
	public void SetupDriver() {
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("target/Spark.html");
		spark.config().setDocumentTitle("AlphaSense");
		spark.config().setReportName("Home Assignment");
		extent.attachReporter(spark);	

	}

	@BeforeMethod
	public void startTest(Method m) {
		
		test= extent.createTest(m.getName()).assignAuthor("Navya");
		test.log(Status.INFO, "Start of Test");

	}
	//creating the tests
	@AfterMethod
	public void endTest() {

		test.log(Status.INFO, "End of Test");

	}
	// closing the driver
	@AfterSuite
	public void DestroyDriver() {
		
		driver.close();
		driver.quit();
		test.log(Status.INFO, "End of Test Suite");
		extent.flush();
	}

}
