package com.peoplematter.modulesList.modules.pages;


import com.peoplematter.pageObjects.BasePage;
import org.openqa.selenium.By;

/**
 * Created by sai.boju on 2/8/17.
 */
public class MessagePage extends BasePage{


    private static final String MESSAGE_ICON = "//android.widget.TextView[@text='Messages']";

   //private static final String LOCATION = "com.peoplematter.android:id/action_bar_custom_view_subtitle";


    public MessagePage clickOnMessageIcon()
    {
        getfd().element(By.xpath(MESSAGE_ICON)).waitUntil(MAX_TIME).ifElementIsNotDisplayed().click();
        return this;
    }

}
