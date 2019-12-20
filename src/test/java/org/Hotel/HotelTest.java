package org.Hotel;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.Hotel.ENavigateur;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class HotelTest {
	
	//JDD
	int a = 1;
	String s = "resa 1";
	WebDriver driver;
	long pause = 7000;
	String path_to_file = "C:\\Users\\formation\\Documents\\SquashTA-1.10.0-RELEASE-workspace\\Hotel\\src\\test\\resources\\NewFile.xml";
	String path_to_file1 = "C:\\Users\\formation\\Documents\\SquashTA-1.10.0-RELEASE-workspace\\Hotel\\src\\test\\resources\\nettoyage.xml";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Hotel";
	private static final String USER = "username";
	private static final String PASSWORD = "password";
	String table = "reservations";

	@Before
	public void setup() throws Exception {
		driver = OutilTechnique.choisirNavigateur(ENavigateur.chrome);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// JDD insert réservation
		DBConnec.insertData(path_to_file);
		
		//nettoyage BDD (jdd delete reservation)
		DBConnec.deleteAllData(path_to_file1);
		
		IDataSet dataset = DBConnec.readDataSet(path_to_file);
		IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataset);
		databaseTester.onSetup();
		}
	

	@After
	public void tearDown() throws Exception{
		driver.quit();
		//nettoyage BDD (jdd delete reservation)
		DBConnec.deleteAllData(path_to_file1);
	}
	
	@Test
	
	public void ConnectHotel() throws SQLException, Exception {
		driver.get("http://localhost:80/TutorialHtml5HotelPhp/");
				
		Accueil accueil1 = PageFactory.initElements(driver, Accueil.class);
		
		assertTrue("Ca fonctionne pas", accueil1.title.isDisplayed());
		accueil1.cel1.click();
		
		driver.switchTo().frame(0);
		assertEquals("La popup n'est pas apparue", "New Reservation", accueil1.titre.getText());
		
		OutilTechnique.remplirChamp(Accueil.champ_name, s);
		Accueil.btn_save.click();
		
		driver.switchTo().defaultContent();
		
		assertTrue(Accueil.resacell.getText().contains("resa 1"));
		
		//compare JDD resa 1
		
		Actions a = new Actions(driver);
		a.clickAndHold(Accueil.resacell2).moveToElement(Accueil.resacell1).release().build().perform();
		
		//assertTrue(Accueil.update_message.getText().contains("Update successful"));
		//assertTrue(Accueil.update_message.isDisplayed());
		
		Thread.sleep(pause);
		
		assertFalse(Accueil.update_message.isDisplayed());	
		
		//compare les BDD
		DBConnec.compareData(table, path_to_file, "id");
	
		//*[@id="dp"]/div[3]/div[3]/div/div[10]/div/div[1]
	
	
	}
	
}
