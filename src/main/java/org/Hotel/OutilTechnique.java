package org.Hotel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;

public class OutilTechnique {

	static WebDriver driver ;
	public static WebDriver choisirNavigateur(ENavigateur nav) {
		switch(nav) {
		case firefox:
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			return driver;
		case chrome:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		case ie:
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			return driver;
		default: return null;	
		}	
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		OutilTechnique.driver = driver;
	}

	public static void switchFrame(WebDriver driver, int a) {
		driver.switchTo().frame(a);
	}
	public static void remplirChamp(WebElement e, String s) {
		e.clear();
		e.sendKeys(s);
	}

}


