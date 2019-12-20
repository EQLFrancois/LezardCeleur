package org.Hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accueil {

	@FindBy (xpath = "//div[@class='scheduler_default_cell'][1]")
	WebElement cel1;

	@FindBy (xpath = "//div[@class='bg-help']")
	WebElement title;

	@FindBy (xpath = "//input[@id='name']")
	static
	WebElement champ_name;	

	@FindBy (xpath = "//input[@type='submit']")
	static
	WebElement btn_save;
	
	@FindBy (xpath = "//form/h1")
	WebElement titre;
	
	@FindBy (xpath = "//*[@class='scheduler_default_event_inner']") 
	static
	WebElement resacell;
	
	@FindBy (xpath = "//*[@class='scheduler_default_cell scheduler_default_cell_business']")
	static
	WebElement resacell1;
	
	@FindBy (xpath = "//*[@class='scheduler_default_event scheduler_default_event_line0']")
	static
	WebElement resacell2;

	@FindBy (xpath ="//div[@class='scheduler_default_message']")
	static
	WebElement update_message;
	
	
}