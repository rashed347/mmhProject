package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

public class DatePicker extends BaseComponent {

    private WebElement parentElement;

    private final By datePicker = By.cssSelector("div.datepicker");
    private final By daysView = By.cssSelector(".datepicker-days");
    private final By monthsView = By.cssSelector(".datepicker-months");
    private final By yearsView = By.cssSelector(".datepicker-years");
    private final By previousIcon = By.cssSelector("thead th.prev");
    private final By nextIcon = By.cssSelector("thead th.next");
    private final By datePickerSwitch = By.cssSelector("thead th.datepicker-switch");

    public DatePicker(MmhHtmlElementManager elementManager) {
        super(elementManager);
    }

    public DatePicker setParentElement(WebElement parentElement) {
        this.parentElement = parentElement;
        return this;
    }

    public DatePicker clickDateField() {
        this.parentElement.findElement(By.xpath(".//input")).click();
        elementManager.waitForAnimation();
        return this;
    }

    public void setDate(String date) {

        Matcher dateFormatMatch = elementManager.matchPattern(date, "(\\d{2})/(\\d{2})/(\\d{4})");
        Assert.assertTrue(dateFormatMatch.find(), "Please enter correct date format. Expected Format: mm/dd/yyyy");

        int month = Integer.parseInt(dateFormatMatch.group(1));
        String monthName = new DateFormatSymbols().getMonths()[month - 1];
        String shortMonthName = new DateFormatSymbols().getShortMonths()[month - 1];

        String day = dateFormatMatch.group(2);
        int year = Integer.parseInt(dateFormatMatch.group(3));
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        Assert.assertTrue(hasMonthYearInHeader(currentDate), "Current month and year are missing from calendar header");

        if (currentYear != year) {
            clickDatePickerSwitch(daysView);
            hasCorrectCalendarView(monthsView);
            clickDatePickerSwitch(monthsView);
            hasCorrectCalendarView(yearsView);

            int currentDecade = currentYear - currentYear % 10;
            int givenDecade = year - year % 10;

            if (currentDecade != givenDecade) {
                By direction = currentDecade > givenDecade ? previousIcon : nextIcon;
                int decadeNumber = Math.abs(currentDecade - givenDecade) / 10;
                while (decadeNumber > 0) {
                    WebElement directionElement = getCalendar(yearsView).findElement(direction);
                    //Webelement click is not working for the direction icon in demographic info page
                    elementManager.applyJavaScriptClick(directionElement);
                    elementManager.waitForAnimation();
                    decadeNumber--;
                }
            }
            clickOnCalendarTime(yearsView, Integer.toString(year));
        }

        if (month != currentDate.getMonthValue() + 1) {
            if (getCalendar(daysView).getAttribute("style").equals("display: block;")) {
                clickDatePickerSwitch(daysView);
            }
            clickOnCalendarTime(monthsView, shortMonthName);
        }
        clickOnCalendarTime(daysView, day);
    }

    private Boolean hasMonthYearInHeader(LocalDate date) {

        hasCorrectCalendarView(daysView);

        String currentMonthYear = getDateTimeString(date, "MMMM yyyy");

        return getCalendar(daysView)
                .findElement(datePickerSwitch)
                .getText().trim().equals(currentMonthYear);
    }

    private void hasCorrectCalendarView(By dayMonthYearView) {
        String calendarViewName = dayMonthYearView == daysView ? "Day" : dayMonthYearView == monthsView ? "Month" : "Year";
        Assert.assertTrue(getCalendar(dayMonthYearView).getAttribute("style").equals("display: block;"),
                calendarViewName + " view is not showing");
    }

    private WebElement getCalendar(By dayMonthYearView) {

        return elementManager.findElement(datePicker)
                .findElement(dayMonthYearView);
    }

    private void clickDatePickerSwitch(By dayMonthYearView) {
        getCalendar(dayMonthYearView)
                .findElement(datePickerSwitch).click();
        elementManager.waitForAnimation();
    }


    private String getDateTimeString(LocalDate date, String datePattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        return date.format(dateTimeFormatter);
    }

    private void clickOnCalendarTime(By dayMonthYearView, String time) {
        String calendarViewName = dayMonthYearView == daysView ? "day" : dayMonthYearView == monthsView ? "month" : "year";
        getCalendar(dayMonthYearView).findElement(By.xpath(
                ".//table/tbody//*[@class='" + calendarViewName + "' and text()='" + time + "']")).click();
        elementManager.waitForAnimation();
    }
}
////"https://emma.injurycloud.com/intake/info?wroomid=2d7e36f9-9571-eb11-a812-00224809a25b&invid=5e575edf-9571-eb11-a812-00224809a25b",