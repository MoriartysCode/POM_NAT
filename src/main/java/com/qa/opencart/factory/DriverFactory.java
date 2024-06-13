package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.bnp.opencart.exception.FrameWorkException;

public class DriverFactory {
	public static String highlight;
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is initializing the driver on the basis of given Browser name
	 * 
	 * @param browserName
	 * @return- this method return the driver
	 */
	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		String browserName = prop.getProperty("browser").trim().toLowerCase();

		if (browserName.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.edge.driver",
			// "C:\\Projects\\JavaBasics\\PktPOM\\Jars\\msedgedriver.exe");
			// driver = new ChromeDriver(optionsManager.getChromeOprions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOprions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// System.setProperty("webdriver.edge.driver",
			// "C:\\Projects\\JavaBasics\\PktPOM\\Jars\\geckodriver.exe");
			// driver = new FirefoxDriver(optionsManager.getFirefoxOprions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOprions()));
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Projects\\JavaBasics\\PktPOM\\Jars\\msedgedriver.exe");
			// driver = new EdgeDriver(optionsManager.getEdgeOprions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOprions()));
		} else {
			System.out.println("Please pass the correct browser");
			throw new FrameWorkException("Wrong Browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	/*
	 * Get the local thread copy of the driver
	 */

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is reading the properties
	 * 
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="QA"
		String envName = System.getProperty("env");
		try {
			//System.out.println("This is the name of environment passed------" + envName.toLowerCase().trim());
			if (envName == null) {
				// By default run on QA environment
				System.out.println("No environment is passed");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				
				 default:
				      System.out.println("Wrong environment passed No need to run the testCases");
				      throw new FrameWorkException("Wrong Environment");
				  
				 

				}
		} catch (FileNotFoundException e) {

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

		/*
		 * prop = new Properties(); try { FileInputStream ip = new
		 * FileInputStream("./src/test/resources/config/config.properties");
		 * //FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+
		 * "src\\test\\resources\\config\\config.properties"); prop.load(ip); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } return prop;
		 */
	}

	/***
	 * Take ScreenShot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

}
