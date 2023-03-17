package stepDef;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.testng.annotations.Parameters;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;
import reusable.Reusable;
import pageObject.CartflowObject;

public class testNG_Pizzaflow {


    //helps to generate the logs in the test report.

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;
    private WebDriver driver;
    private Reusable resObj;
    private CartflowObject pageObj;


    @BeforeClass
    public void setUp(){

        // Creating an object for Page Object Model & Reusable

        resObj = new Reusable();
        pageObj = new CartflowObject();

        // initialize the HtmlReporter

        // Create an object of Extent Reports
        extent = new ExtentReports();

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/pizzahutCheckout.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "StarHealth Application - Home Plan");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Test Team");
        spark.config().setDocumentTitle("pizzahutCheckout Application QA ");
        // Name of the report
        spark.config().setReportName("pizzahutCheckout Application Using Selenium testNG ");
        // Dark Theme
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Validate pizzahutCheckout Application Using Selenium testNG");


        // System Property for Chrome Driver

        try {
            System.out.println("##### Starting Chrome Browser ############");

            WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(resObj.read_data_from_XL("URL"));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            logger.createNode("User can Successfully launch the Chrome Browser");
            Thread.sleep(20000);
        }
        catch(Exception e){logger.fail("Instantiate of the Chrome Browser Failed");}
    }

    @Test
    @Parameters({"address" , "name" , "email" , "mobile"})
    public void pizzaAddItemToBasketFlow(String address , String name , String mobile , String email){

        // A black color auto pop up screen should display , close it.

        
        try {
        	WebElement CloseButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(pageObj.BlackScreenCloseBTN()));
            CloseButton.click();
            logger.pass("User wait for auto location black pop up screen , closed it");
        }
        catch(Exception e){
            logger.warning("black pop up screen not displayed");
        }

        // Then user delivery location as Lulu mall bangalore.

        try {
            Assert.assertTrue(driver.findElement(pageObj.DeliveryLocationTextBox()).isDisplayed());
            logger.pass("Enter Location For Delivery Text box is present");
            driver.findElement(pageObj.DeliveryLocationTextBox()).sendKeys(address);
            Thread.sleep(2000);
            driver.findElement(pageObj.DeliveryLocationText()).click();
            Thread.sleep(5000);
            try{ driver.findElement(pageObj.selectMallFinally()).click();Thread.sleep(5000);}catch(Exception e){}
            try{ driver.findElement(pageObj.StartYourOrderWithTime()).click();}catch(Exception e){}
            logger.pass("Delivery Location Selected Successfully");
            Thread.sleep(10000);
        }
        catch(Exception e){
            logger.fail("Enter Location For Delivery Text box is missing");
        }

        //User is now Deals page , validate URL has text as ‘deals’.

        try {
            Assert.assertTrue(driver.findElement(pageObj.DealsBTN()).isDisplayed());
            Assert.assertTrue(driver.getCurrentUrl().contains("deals"));

            logger.pass("User landed to Deals page successfully");
        }
        catch(Exception e){
            logger.fail("User is not into Deals Page");
        }

        //GO to sides and add any item which is below 200 /-

        try {
            driver.findElement(pageObj.SidesMenu()).click();
            Thread.sleep(5000);
            driver.findElement(pageObj.cheeseBread_Sides()).click();
            Thread.sleep(5000);
            logger.pass("Side Menu Cheese Bread added successfully");
        }
        catch(Exception e){
            logger.fail("Side Menu Cheese Bread not added");
        }

        //Validate product is added under Basket but checkout button price item is still now showing.

        float TotalBasketPrice = Float.valueOf(driver.findElement(pageObj.TotalPriceAmount()).getText().replaceAll("₹" , ""));

        boolean flag = resObj.isElementPresent(driver,pageObj.PriceOptionUnderCheckout("₹"+String.valueOf(TotalBasketPrice)));
        if(!flag){
            logger.pass("Price tag not displayed in Checkout button below 200/- as expected");
        }
        else {
            logger.fail("Price tag displayed in Checkout button below 200/-");
        }

        //Navigate to Drinks page

        driver.findElement(pageObj.DrinksMenu()).click();

        //Add any 2 drink item so that total cart pricing should be more than 200/-

        try {
            Thread.sleep(5000);
            driver.findElement(pageObj.Pepsi_Drinks()).click();
            Thread.sleep(2000);
            driver.findElement(pageObj.Mirinda_Drinks()).click();
            Thread.sleep(2000);

            logger.pass("User added 2 Drinks to Your Basket");
        }
        catch(Exception e){
            logger.fail("2 Drinks not added to Your Basket");
        }


        //Click on Checkout button and user navigated to checkout page

        try {
            driver.findElement(pageObj.CheckoutBTN()).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(pageObj.SecureCheckoutTitle()).isDisplayed());
            logger.pass("User Clicks on Checkout button , moved to Checkout Page");
        }
        catch(Exception e){
            logger.fail("Checkout Page navigation not working");
        }

        //Validate Online Payment radio button is selected by default

        try {
            Assert.assertTrue(driver.findElement(pageObj.OnlinePayment_Default()).isDisplayed());
            logger.pass("User see Online payment option selected by default");
        }
        catch(Exception e){
            logger.fail("User don't see Online payment option selected by default");
        }

        //Change Payment option to Cash

        driver.findElement(pageObj.CashPaymentOption()).click();

        //Validate I agree checkbox is checked by default

        try {
            Assert.assertTrue(driver.findElement(pageObj.IAgreeCheckBox()).getAttribute("value").equals("true"));
            logger.pass("I Agree Check box is checked by default");
        }
        catch(Exception e){
            logger.fail("I Agree Check box is not checked by default");
        }

        //Enter name , mobile , email

        driver.findElement(pageObj.name()).sendKeys(name);
        driver.findElement(pageObj.phone()).sendKeys(mobile);
        driver.findElement(pageObj.email()).sendKeys(email);
        logger.pass("Typed name as ==>"+name);
        logger.pass("Typed phone as ==>"+mobile);
        logger.pass("Typed email as ==>"+email);

        //Click on Apply Gift Card link
        //A pop up should appear , click on Voucher

        try {
            driver.findElement(pageObj.GiftCardLink()).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(pageObj.VoucherOption()).isDisplayed());
            logger.pass("User Clicks on Gift Card , Gift Card Page opens");
            driver.findElement(pageObj.VoucherOption()).click();
            Thread.sleep(3000);
        }
        catch(Exception e){
            logger.fail("Gift Card page not working");
        }


        //Give Voucher code as 12345 and submit

        driver.findElement(pageObj.VoucherInput()).clear();
        driver.findElement(pageObj.VoucherInput()).sendKeys("12345");
        driver.findElement(pageObj.ReedemBTN()).click();

        //Validate error is coming that the number is incorrect

        try {
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(pageObj.ErrorVoucher()).isDisplayed());
            logger.pass("Wrong Voucher is giving error text as expected");
        }
        catch(Exception e){
            logger.fail("Wrong Voucher is not giving error text");
        }

        //Close the voucher pop up
        //User should again navigate back to your Basket page

        try {
            driver.findElement(pageObj.CancelVoucher()).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(pageObj.YourBasketTitle()).isDisplayed());
            logger.pass("User Clicks on Gift Card Cancel , Your Basket Page opens");
            driver.findElement(pageObj.VoucherOption()).click();
            Thread.sleep(3000);
        }
        catch(Exception e){
            logger.pass("User Clicks on Gift Card Cancel , Your Basket Page not opens");
        }
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
        logger.pass("User Successfully closed the driver session");
        extent.flush();
    }
}
