package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.*;

public class TestScripts {

    public WebDriver driver;

    @BeforeTest

    public void setUp(){

        System.out.println("##### Starting Chrome Browser ############");
        //WebDriverManager.chromedriver().driverVersion(res.getXMLData("ChromeVersion")).setup();
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/mithunroy/Downloads/BrowserDrivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.google.com");

    }

    @Test
    public void test_Google_App(){


        Assert.assertTrue(driver.findElement(By.xpath("//img[@class='lnXdpd']")).isDisplayed());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

    }

    @AfterClass

    public void tearDown(){

        driver.quit();
    }





}
