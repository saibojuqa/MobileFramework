package com.peoplematter.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Log4j
public class Application {

    protected static Properties properties;
    public static String basedir;
    private static String appConfigFileLocation;
    private static String confDir = ".";
    public static AndroidDriver driver;
    private static WebDriver webdriver;

    static {
        properties = new Properties();
        basedir = System.getProperty("basedir", ".");
        appConfigFileLocation = basedir + "/conf/application.properties";
        try {
            //noinspection Since15
            properties.load(new FileReader(appConfigFileLocation));
            File configFolder = new File(basedir + File.separator + "conf");
            if (!configFolder.exists()) {
                confDir = new File(new File("").getAbsolutePath()).toPath().getParent().toString();
            } else
                confDir = basedir;
            PropertyConfigurator.configure(confDir + "/conf/log4j.properties");
            log.info("Reading properties file " + appConfigFileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InitDriver() throws MalformedURLException {
        String os = properties.getProperty("appDir");
        switch (os) {
            case "Android":
                log.info("Initializing AndroidDriver .....");
                initAndroidDriver();
                break;
        }
    }

    public static AndroidDriver getAndroidDriver() {
        return driver;
    }

    protected AndroidDriver initAndroidDriver() throws MalformedURLException {
       File appDir = new File(properties.getProperty("appDir"));
        File app = new File(appDir, properties.getProperty("app"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", properties.getProperty("device"));
        capabilities.setCapability("deviceName", properties.getProperty("deviceName"));
        capabilities.setCapability("platformName", properties.getProperty("device"));
        capabilities.setCapability(CapabilityType.VERSION, properties.getProperty("version"));
        capabilities.setCapability("platformVersion", properties.getProperty("platformVersion"));
        capabilities.setCapability("app", app.getAbsolutePath());
   //     capabilities.setCapability("appium-version", "1.1");
          //capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
          //capabilities.setCapability("appPackage", "com.android.chrome");
         //capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        // The URL where the hub will start
        driver = new AndroidDriver(new URL(properties.getProperty("url")), capabilities);
        // need to remove below line
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getWebdriver() {
        return webdriver;
    }


    public void initWebDriver() {
        String browser = properties.getProperty("browser");
        if ("firefox".equals(browser)) {
            initFireFoxDriver();
        } else if ("chrome".equals(browser)) {
            initChromeDriver();

        } else if ("ie".equals(browser)) {
            initIEDriver();
        }
    }

    public void quitWebDiver() {
        webdriver.close();
        webdriver.quit();
    }

    public void initFireFoxDriver() {
        log.info("Initializing Firefox Driver");
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        webdriver = firefoxDriver;
    }

    public void initChromeDriver() {
        log.info("Initializing Chrome Driver");
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
        ChromeDriver chromeDriver = new ChromeDriver();
        webdriver = chromeDriver;

    }

    public void initIEDriver() {
        log.info("Initializing IE Driver");
        InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver();
        webdriver = internetExplorerDriver;
    }

    protected void initWebAndroidDriver() throws MalformedURLException {

        DesiredCapabilities capabilities=DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"1115fb45a8c01303");
        capabilities.setCapability(MobileCapabilityType.VERSION,"6.0.1");

        driver = new AndroidDriver(new URL(properties.getProperty("url")), capabilities);
        getAndroidDriver().get("https://qa-prod.peoplematter.com");

        //driver.get("https://qa-prod.peoplematter.com");

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        //return driver;
    }


    protected AndroidDriver initMobileAndroidDriver() throws MalformedURLException {



        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("BrowserName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformversion", "6.0");
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("app", "/Users/sai.boju/Desktop/all_apk/peopleMatter-debug-qa-prod.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return driver;

    }





}