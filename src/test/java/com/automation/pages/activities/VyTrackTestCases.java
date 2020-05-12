package com.automation.pages.activities;

import com.automation.pages.BasePage;
import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class VyTrackTestCases extends BasePage {
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    @FindBy(xpath = "//td[text()='Testers Meeting']//following-sibling::td//a[text()='...']")
    public WebElement threeDots;

    @FindBy(xpath = "//div/ul/li/ul/li/*[@href='/calendar/event/view/1846']")
    public WebElement view;

    @FindBy(xpath = "///div/ul/li/ul/li/*[@href='/calendar/event/update/1846']")
    public WebElement edit;

    @FindBy(xpath = "(//div/ul/li/ul/li/*[@href='#'])[2]")
    public WebElement delete;

    public void hoverOverDots(){
        Actions actions =new Actions(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(threeDots));
        actions.moveToElement(threeDots).pause(4).perform();
        BrowserUtilities.wait(4);

    }
    @FindBy(xpath = "//*[@title=\"Grid Settings\"]")
    public WebElement gridIcon;

    @FindBy(xpath = "//span[text()='Title']")
    public WebElement titleColumn;

    @FindBy(xpath = "(//*[@data-toggle='dropdown'])[4]")
    public WebElement saveAndCloseDropDown;

    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and Close')]")
    public WebElement saveAndClose1;
    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and New')]")
    public WebElement saveAndNews2;
    @FindBy(xpath = "(//ul//li//button[contains(text(),Save)])[3]")
    public WebElement save3;
    @FindBy(xpath = "//*[@title='Cancel']")
    public WebElement cancelButton;

    @FindBy(className = "oro-subtitle")
    public WebElement allCalendarEvents;

    public Integer timeDiff(){
        String startTime = calendarEventPage.getStartTime();
        String endTime =calendarEventPage.getEndTime();

        String [] start =startTime.split(":");
        int startValue = Integer.parseInt(start[0]);

        String [] end =endTime.split(":");
        int endValue = Integer.parseInt(end[0]);
        if (startValue==12){
            startValue-=12;
        }

        return endValue-startValue;
    }
    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;
    @FindBy(xpath = "//li[text()='9:00 PM']")
    public WebElement time900Pm;

    @FindBy (css ="[id^='oro_calendar_event_form_allDay']")
    public WebElement allDayCheckBox;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(css ="[id^='recurrence-repeat-view']")
    public WebElement repeatCheckBox;

    @FindBy(css ="[id^='recurrence-repeats-view']")
    public WebElement repeatsSelector;

    @FindBy(xpath = "//input[@checked='checked']")
    public WebElement repeatEveryDays;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement endsNever;

    @FindBy(xpath = "//label[text()='Summary:']")
    public WebElement summary;
    @FindBy(xpath = "//div[@data-name='recurrence-summary']//div/span[1]")
    public WebElement summary1;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement endsAfter;

    @FindBy(xpath = "(//*[@class=\"recurrence-subview-control__number\"])[7]")
    public WebElement afterOccurrences;

    @FindBy ( xpath= "//div[@data-name='recurrence-summary']//div/span[2]")
    public WebElement summary2;

    @FindBy(xpath = "(//input[@type=\"radio\"])[5]")
    public WebElement byDatePicker;
    @FindBy(xpath = "//*[@class='datepicker-input hasDatepicker']")
    public WebElement datePicker;

    @FindBy(className = "ui-datepicker-month")
    public WebElement datePickerMonth;
    @FindBy(className = "ui-datepicker-year")
    public WebElement datePickerYear;


    public WebElement datePickerDay(String dayNum){
        String xpath = "//a[text()='"+dayNum+"']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//div//span[text()='Daily every 1 day']/following-sibling::span")
    public WebElement byDatePickerOccurrence;

    public WebElement weekDayCheckBox(String firstLetterOfDay){
        return Driver.getDriver().findElement(By.xpath("//span[text()='"+firstLetterOfDay+"']"));
    }
}
