package com.automation.tests.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventPage;
import com.automation.pages.activities.VyTrackTestCases;
import com.automation.tests.TestBase;
import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CalendarEventTest extends TestBase {
    LoginPage loginPage = new LoginPage();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
VyTrackTestCases vyTrackTestCases=new VyTrackTestCases();
    /**
     * Test case#1
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Hover on three dots “...” for “Testers meeting” calendar event
     * 5. Verify that “view”, “edit” and “delete” options are available
     */
    @Test
    public void Test1() {
        test = report.createTest("Verify that “view”, “edit” and “delete” options");
        loginPage.login();
        calendarEventPage.navigateTo("Activities", "Calendar Events");

        vyTrackTestCases.hoverOverDots();
        Assert.assertTrue(vyTrackTestCases.view.isDisplayed());
        Assert.assertTrue(vyTrackTestCases.edit.isDisplayed());
        Assert.assertTrue(vyTrackTestCases.delete.isDisplayed());

    }

    /**
     * Test Case#2
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Grid Options” button
     * 5. Deselect all options except “Title”
     * 6. Verify that “Title” column still displayed
     */

    @Test
    public void test2(){
loginPage.login();
        test = report.createTest("Verify that “Title” column still displayed");

        calendarEventPage.navigateTo("Activities","Calendar Events");
        vyTrackTestCases.gridIcon.click();
        List<WebElement> gridOptions = Driver.getDriver().findElements(By.xpath("//input[@data-role='renderable']"));
        for (int i = 1; i < gridOptions.size(); i++) {
            gridOptions.get(i).click();
            BrowserUtilities.wait(1);
        }
        System.out.println(gridOptions.size());
        BrowserUtilities.wait(3);
        Assert.assertTrue(vyTrackTestCases.titleColumn.isDisplayed());
        test.pass("Title column verified");
    }

    /**
     * Test Case #3
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Expand “Save And Close” menu
     * 6. Verify that “Save And Close”, “Save And New”
     * and “Save” options are available
     */

    @Test
    public void test3(){
       loginPage.login();
        test = report.createTest("Verify that “Save And Close”, “Save And New  and “Save” ");


        calendarEventPage.navigateTo("Activities","Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.saveAndCloseDropDown));
        vyTrackTestCases.saveAndCloseDropDown.click();

        Assert.assertEquals(vyTrackTestCases.saveAndClose1.getText().trim(),"Save And Close");
        Assert.assertEquals(vyTrackTestCases.saveAndNews2.getText().trim(),"Save And New");
        Assert.assertEquals(vyTrackTestCases.save3.getText().trim(),"Save");

        test.pass("“Save And Close”, “Save And New  and “Save” verified");
    }

    /**
     * Test Case #4
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Then, click on “Cancel” button
     * 6. Verify that “All Calendar Events” page subtitle is
     * displayed
     */


    @Test
    public void test4(){
        loginPage.login();
        calendarEventPage.navigateTo("Activities","Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.cancelButton));
        vyTrackTestCases.cancelButton.click();
        Assert.assertTrue(vyTrackTestCases.allCalendarEvents.isDisplayed());

    }
    /**
     * Test Case #5
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Verify that difference between end and start time
     * is exactly 1 hour
     */

    @Test
    public void test5(){
       loginPage.login();
        calendarEventPage.navigateTo("Activities","Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        Assert.assertTrue(vyTrackTestCases.timeDiff()==1);
    }
    /**
     * Test Case #6
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “9:00 PM” as a start time
     * 6. Verify that end time is equals to “10:00 PM”
     */

    @Test
    public void test6(){
        loginPage.login();

        calendarEventPage.navigateTo("Activities","Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.startTime));
        vyTrackTestCases.startTime.click();
        Actions actions = new Actions(Driver.getDriver());
        BrowserUtilities.wait(4);
        actions.moveToElement(vyTrackTestCases.time900Pm).click().perform();
        BrowserUtilities.wait(2);
        Assert.assertEquals(calendarEventPage.getEndTime(),"10:00 PM");
    }

    /**
     * Test Case #7
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “All-Day Event” checkbox
     * 6. Verify that “All-Day Event” checkbox is selected
     * 7. Verify that start and end time input boxes are
     * not displayed
     * 8. Verify that start and end date input boxes are
     * displayed
     */

    @Test
    public void test7() {
        loginPage.login();
        calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.allDayCheckBox));
        vyTrackTestCases.allDayCheckBox.click();
        Assert.assertTrue(vyTrackTestCases.allDayCheckBox.isSelected());
        Assert.assertTrue(vyTrackTestCases.startTime.isDisplayed());
        Assert.assertTrue(vyTrackTestCases.endTime.isDisplayed());
        Assert.assertTrue(vyTrackTestCases.startDate.isEnabled());
        Assert.assertTrue(vyTrackTestCases.endDate.isEnabled());
    }
    /**
     * Test Case #8
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Daily” is selected by default and
     * following options are available in
     * “Repeats” drop-down:
     */

    @Test
    public void test8(){
        loginPage.login();
        calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.repeatCheckBox));
        vyTrackTestCases.repeatCheckBox.click();

        Assert.assertTrue(vyTrackTestCases.repeatCheckBox.isSelected());

        Select select = new Select(vyTrackTestCases.repeatsSelector);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Daily");
        List<WebElement> repeatsOptions = select.getOptions();

        Assert.assertEquals(repeatsOptions.get(0).getText(),"Daily");
        Assert.assertEquals(repeatsOptions.get(1).getText(),"Weekly");
        Assert.assertEquals(repeatsOptions.get(2).getText(),"Monthly");
        Assert.assertEquals(repeatsOptions.get(3).getText(),"Yearly");

    }

    /**
     * Test Case #9
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Repeat Every” radio button is
     * selected
     * 8. Verify that “Never” radio button is selected as an
     * “Ends” option.
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day”
     */

    @Test
    public void test9(){
        loginPage.login();
        calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        vyTrackTestCases.repeatCheckBox.click();

        Assert.assertTrue(vyTrackTestCases.repeatCheckBox.isSelected());
        Assert.assertTrue(vyTrackTestCases.repeatEveryDays.isSelected());
        Assert.assertTrue(vyTrackTestCases.endsNever.isSelected());
        String expected ="Summary: Daily every 1 day";
        String actual = vyTrackTestCases.summary.getText()+" "+vyTrackTestCases.summary1.getText();
        Assert.assertEquals(actual,expected);

    }
    /**
     * Test Case #10
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “After 10 occurrences” as an “Ends”
     * option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end
     * after 10 occurrences”
     */

    @Test
    public void test10(){
        loginPage.login();
        test = report.createTest("Verify Summary: Daily every 1 day, end after 10 occurrences");


        calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        vyTrackTestCases.repeatCheckBox.click();

        vyTrackTestCases.endsAfter.click();
        vyTrackTestCases.afterOccurrences.sendKeys("10", Keys.ENTER);

        String expected ="Summary: Daily every 1 day, end after 10 occurrences";
        String actual = vyTrackTestCases.summary.getText()+
                " "+vyTrackTestCases.summary1.getText()+
                vyTrackTestCases.summary2.getText();
        Assert.assertEquals(actual,expected);

        test.pass("Verified");
    }
    /**
     * Test Case #11
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “By Nov 18, 2021” as an “Ends” option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end by
     * Nov 18, 2021”
     */


    @Test
    public void test11(){
       loginPage.login();
        calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.clickToCreateCalendarEvent();
        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.repeatCheckBox));
        vyTrackTestCases.repeatCheckBox.click();
wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.byDatePicker));
        vyTrackTestCases.byDatePicker.click();
        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.datePicker));
        vyTrackTestCases.datePicker.click();
        Select selectMonth = new Select(vyTrackTestCases.datePickerMonth);
        selectMonth.selectByVisibleText("Nov");
        Select selectYear = new Select(vyTrackTestCases.datePickerYear);
        selectYear.selectByVisibleText("2021");
        vyTrackTestCases.datePickerDay("18").click();

        String expected ="Summary: Daily every 1 day, end by Nov 18, 2021";

        String actual =         vyTrackTestCases.summary.getText()+
                " "+    vyTrackTestCases.summary1.getText()+
                vyTrackTestCases.byDatePickerOccurrence.getText();

        Assert.assertEquals(actual,expected);
    }
    /**
     * Test Case #12
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “Weekly” options as a “Repeat” option
     * 7. Select “Monday and Friday” options as a
     * “Repeat On” options
     * 8. Verify that “Monday and Friday” options are
     * selected
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Weekly every 1 week on
     * Monday, Friday”
     */

    @Test
    public void test12(){
        loginPage.login();
        calendarEventPage.navigateTo("Activities", "Calendar Events");

        wait.until(ExpectedConditions.elementToBeClickable(vyTrackTestCases.repeatCheckBox));
        vyTrackTestCases.repeatCheckBox.click();
        Select select = new Select(vyTrackTestCases.repeatsSelector);
        select.selectByVisibleText("Weekly");
        vyTrackTestCases.weekDayCheckBox("M").click();
        vyTrackTestCases.weekDayCheckBox("F").click();
        String expected = "Summary: Weekly every 1 week on Monday, Friday";
        String actual = vyTrackTestCases.summary.getText()
                +" " +vyTrackTestCases.summary1.getText();
        Assert.assertEquals(actual,expected);


    }
}

