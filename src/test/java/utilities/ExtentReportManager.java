package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentDate=df.format(dt);*/ //Alternative for time stamp.
		
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());   //time stamp generator
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); //specify the location of the report.
		
		
		//sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //Title of report
		sparkReporter.config().setReportName("Opencart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application name", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");  //paramenter provided in xml file.
		extent.setSystemInfo("operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroup=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroup.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroup.toString());
		}
		
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String impPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
			
		}catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.log(Status.SKIP, "Test case SKIPPED is"+result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		//Sending Report through email
		
		/*
		try {
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			//Create the email message 
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("itsram0204@gmail.com", "Spider#recon4"));
			email.setSSLOnConnect(false);
			email.setStartTLSEnabled(true);
			email.setFrom("itsram0204@gmail.com");  //sender
			email.setSubject("Test Result");
			email.setMsg("Please find attached test report.");
			email.addTo("ramchandra0204@gmail.com"); //receiver
			email.attach(url,"extent report","please check report");
			email.send();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		*/
	}
	

}
