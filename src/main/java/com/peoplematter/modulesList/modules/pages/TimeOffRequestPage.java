package com.peoplematter.modulesList.modules.pages;

import com.peoplematter.core.Application;
import com.peoplematter.pageObjects.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by sai.boju on 1/30/17.
 */
public class TimeOffRequestPage extends BasePage {

    private static final String TIMEOFFFRERQUEST_BUTTON = "//android.widget.TextView[@text='Time Off']";
    private static final String PLUS_BUTTON = "//android.widget.ImageView";
    private static final String STARTS_DAY = "com.peoplematter.android:id/start_date_button";
    private static final String END_DAY = "com.peoplematter.android:id/end_date_button";
    private static final String START_DATE_SELECTOR ="23 March 2017";
    private static final String END_DATE_SELECTOR ="24 March 2017";
    private static final String OK_BUTTON = "//android.widget.Button[@text='OK']";
    private static final String TIMEOFF_REQUESTED_TEXT = "//android.widget.TextView[@text='Time off requested!']";
    private static final String DOUG_BALDWIN_BUTTON = "//android.widget.TextView[@text='Doug Baldwin']";
    private static final String HOME = "//android.widget.TextView[@text='Home']";


    public TimeOffRequestPage clickOnTimeOffRequest(){
        getfd().element(By.xpath(TIMEOFFFRERQUEST_BUTTON)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }

    public TimeOffRequestPage clickOnPlusButton(){
        getfd().element(By.xpath(PLUS_BUTTON)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }

    public TimeOffRequestPage clickOnStartsDate(){
        getfd().element(By.id(STARTS_DAY)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }
    public TimeOffRequestPage clickOnEndDate(){
        getfd().element(By.id(END_DAY)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }


    public TimeOffRequestPage clickOnOKButton(){
        getfd().element(By.xpath(OK_BUTTON)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }

    public TimeOffRequestPage verifyTimeOffRequestedText(){
        getfd().element(By.xpath(TIMEOFF_REQUESTED_TEXT)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().isDisplayed();
        return this;
    }

    public TimeOffRequestPage scrollUntilStartDate() {


        MobileElement abc = (MobileElement) Application.getAndroidDriver()
                .findElement(By.className("android.view.View"));

        abc.swipe(SwipeElementDirection.UP, 3000);
        getfd().element(By.name(START_DATE_SELECTOR)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();

     return this;

    }

    public TimeOffRequestPage clickOnEndDateSelector() {

        getfd().element(By.name(END_DATE_SELECTOR)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();

        return this;

    }

    public static boolean checkElementExist()
    {
        try{

            WebElement ele=Application.getAndroidDriver().findElement(By.id("//test"));
            System.out.println("If time off bubble is displayed");
            return true;

        }catch(Throwable e){}
        System.out.println("Time Off Bubble Is not displayed");
        return false;
    }


    public TimeOffRequestPage clickOnDougBaldwinButton(){
        getfd().element(By.name(DOUG_BALDWIN_BUTTON)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }

    public TimeOffRequestPage clickOnHomeButton(){
        getfd().element(By.xpath(HOME)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }

}
