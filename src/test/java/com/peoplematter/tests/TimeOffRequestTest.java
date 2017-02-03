package com.peoplematter.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peoplematter.BaseTest;
import com.peoplematter.core.Application;
import com.peoplematter.modulesList.LoginPage;
import com.peoplematter.modulesList.modules.pages.*;
import com.peoplematter.modulesList.modules.pojos.Manage;
import com.peoplematter.utils.dataProvider.DataProviderArguments;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by sai.boju on 1/30/17.
 */

public class TimeOffRequestTest extends BaseTest {


    public static final String FILE_PATH = "/testData/Template.xls";
    ObjectMapper mapper = new ObjectMapper();

    AppiumDriver driver ;


    //59201 - complete
    @Test(dataProviderClass = com.peoplematter.utils.dataProvider.ExcelDataProvider.class, dataProvider = "excel")
    @DataProviderArguments(filePath = FILE_PATH, sheetName = "T8")
    public void TimeOffCreated(Map<String, String> testData) throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage();
        Homepage homepage = new Homepage();
        SchedulePage schedulePage = new SchedulePage();
        MBULearnPage mbuLearnPage = new MBULearnPage();
        TimeOffRequestPage timeOffRequestPage = new TimeOffRequestPage();
        Manage manage = mapper.readValue(testData.get("data"), Manage.class);
        loginPage.enterUserNameAndPassword(manage.getUserName(),
                manage.getPassword()).clickOnNavigateUpButton();
        timeOffRequestPage.clickOnTimeOffRequest().clickOnPlusButton().clickOnStartsDate().scrollUntilStartDate()
                .clickOnOKButton().clickOnEndDate().clickOnEndDateSelector().clickOnOKButton();
        schedulePage.enterAComment().clickOnSendButton();
        timeOffRequestPage.verifyTimeOffRequestedText().clickOnOKButton();

    }

//106042
@Test(dataProviderClass = com.peoplematter.utils.dataProvider.ExcelDataProvider.class, dataProvider = "excel")
@DataProviderArguments(filePath = FILE_PATH, sheetName = "T8")
public void noTimeOffBubble(Map<String, String> testData) throws IOException, InterruptedException {
    LoginPage loginPage = new LoginPage();
    Homepage homepage = new Homepage();
    SchedulePage schedulePage = new SchedulePage();
    TimeOffRequestPage timeOffRequestPage = new TimeOffRequestPage();
    ManagePage managePage = new ManagePage();
    MBULearnPage mbuLearnPage = new MBULearnPage();
    Manage manage = mapper.readValue(testData.get("data"), Manage.class);
    loginPage.enterUserNameAndPassword(manage.getUserName(),
            manage.getPassword());
    timeOffRequestPage.checkElementExist();

}
}
