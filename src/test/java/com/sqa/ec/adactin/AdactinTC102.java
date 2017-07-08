/**
 * File Name: AdactinTC102.java<br>
 * Chu, Edwin<br>
 * Java Boot Camp Exercise<br>
 * Instructor: Jean-francois Nepton<br>
 * Created: Jul 7, 2017
 */
package com.sqa.ec.adactin;

import java.text.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import com.sqa.ec.helpers.*;

/**
 * AdactinTC102 //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public class AdactinTC102 extends BasicTest
{

	private String testName;

	public AdactinTC102()
	{
		super("http://adactin.com/HotelApp");
		this.testName = "Adactin PG ";
	}

	public void checkError()
	{
		// get error messages
		String checkInDateError = getDriver().findElement(By.id("checkin_span")).getText();
		String checkOutDateError = getDriver().findElement(By.id("checkout_span")).getText();
		// declare expected message
		String expectedCheckInError = "Check-In Date shall be before than Check-Out Date";
		String expectedCheckOutError = "Check-Out Date shall be after than Check-In Date";
		// Assert actual and expected error messages
		Assert.assertEquals(checkInDateError, expectedCheckInError);
		Assert.assertEquals(checkOutDateError, expectedCheckOutError);
	}

	@Test
	public void dateErrorTest()
	{
		login();
		selectLocation();
		selectHotel();
		selectRoomType();
		selectNumRooms();
		enterCheckInDate();
		enterCheckOutDate();
		search();
		checkError();
		System.out.println("Test Complete");
	}

	public void enterCheckInDate()
	{
		// enter current date + 7 days in check-in date field
		WebElement checkInDate = getDriver().findElement(By.id("datepick_in"));
		checkInDate.clear();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar checkIn = Calendar.getInstance();
		checkIn.setTime(new Date());	// today's date
		checkIn.add(Calendar.DATE, 7);	// add 7 days
		System.out.println(dateFormat.format(checkIn.getTime()));
		checkInDate.sendKeys(dateFormat.format(checkIn.getTime()));
	}

	public void enterCheckOutDate()
	{
		// enter current date + 5 days in check-out date field
		WebElement checkOutDate = getDriver().findElement(By.id("datepick_out"));
		checkOutDate.clear();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar checkOut = Calendar.getInstance();
		checkOut.setTime(new Date());
		checkOut.add(Calendar.DATE, 5);
		System.out.println(dateFormat.format(checkOut.getTime()));
		checkOutDate.sendKeys(dateFormat.format(checkOut.getTime()));
	}

	public void login()
	{
		AdactinHomePage homePage = new AdactinHomePage(this);
		System.out.println("Before login screenshot");
		this.takeScreenshot();
		homePage.login("sqabcjune12", "sqaadmin2017");
		this.takeScreenshot();
		System.out.println("After login screenshot");
	}

	public void search()
	{
		// click search button to search
		WebElement searchButton = getDriver().findElement(By.id("Submit"));
		searchButton.click();
	}

	public void selectHotel()
	{
		// select value "Hotel Creek" from hotels dropdown
		WebElement hotelsDropdown = getDriver().findElement(By.id("hotels"));
		hotelsDropdown.click();
		Select selectHotelsDropdown = new Select(hotelsDropdown);
		selectHotelsDropdown.selectByValue("Hotel Creek");
	}

	public void selectLocation()
	{
		// select value "Sydney" from location dropdown
		WebElement locationDropdown = getDriver().findElement(By.id("location"));
		locationDropdown.click();
		Select selectLocDropdown = new Select(locationDropdown);
		selectLocDropdown.selectByValue("Sydney");
	}

	public void selectNumRooms()
	{
		// select value "1" from number of rooms dropdown
		WebElement numRoomsDropdown = getDriver().findElement(By.id("room_nos"));
		numRoomsDropdown.click();
		Select selectNumRoomsDropdown = new Select(numRoomsDropdown);
		selectNumRoomsDropdown.selectByValue("1");
	}

	public void selectRoomType()
	{
		// select value "Standard" from room type dropdown
		WebElement roomTypeDropdown = getDriver().findElement(By.id("room_type"));
		roomTypeDropdown.click();
		Select selectRoomTypeDropdown = new Select(roomTypeDropdown);
		selectRoomTypeDropdown.selectByValue("Standard");
	}
}
