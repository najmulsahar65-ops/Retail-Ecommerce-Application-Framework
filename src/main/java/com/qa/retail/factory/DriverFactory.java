package com.qa.retail.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.retail.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("The Browser is:  " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Please pass the right browser name! " + browserName);
			throw new FrameworkException("No browser found...");

		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}

	public Properties initProp() {
		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("Environment name is: " + envName);

		try {

			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;

				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;

				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Please pass the right environment name: " + envName);
					throw new FrameworkException("Wrong environment has passed..." + envName);
				}
			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Establish the connection wiht config.properties
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return prop;

	}

	
}
