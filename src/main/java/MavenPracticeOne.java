import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MavenPracticeOne {
    protected static WebDriver driver;

    @Before
    public void BeforeMethod() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\BrowserDriver\\chromedriver.exe");
        //Open The Browser
        driver = new ChromeDriver();
        //Maximise the browser Window
        driver.manage().window().fullscreen();
        //set implicitly for driver object.
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        ////Open the website
        driver.get("https://demo.nopcommerce.com");
    }
    @After
     public void AfterMethod()
    {
       //Drive will close browser.
        driver.quit();
    }

    public String generateEmail(String startValue) {
        String email = startValue.concat(new Date().toString());
        return email;
    }

    public static String rendomDate() {
        DateFormat format = new SimpleDateFormat("ddMMyyHHMMss");
        return format.format(new Date());
    }

    @Test
    public void UsershouldbeabletoRegistration() {
        //Click on Registration Button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //Enter First Name
        driver.findElement(By.id("FirstName")).sendKeys("seema");

        //Enter LastName
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("tanna");

        //Enter Email
        driver.findElement(By.name("Email")).sendKeys(("abcxyz" + rendomDate() + "@gmail.com"));


        //Enter password
        driver.findElement(By.name("Password")).sendKeys("abc123");

        //Enter Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("abc123");

        //Click on register button
        driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();
        //Store expected result in String variable.
        String expectgedMessage = "Your registration not completed";
        //store actual result with location in string variable.
        String actualMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(actualMessage, expectgedMessage);
    }

    @Test
    public void UserreferProductToFriend() {
        //Click on Registration Button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //Enter First Name
        driver.findElement(By.id("FirstName")).sendKeys("seema");
        //Enter LastName
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("tanna");
        //Enter Email
        driver.findElement(By.name("Email")).sendKeys(("abcxyz" + rendomDate() + "@gmail.com"));
        //Enter password
        driver.findElement(By.name("Password")).sendKeys("abc123");
        //Enter Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("abc123");
        //Click on register button
        driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();
        // to click on nopp comm logo
        driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).click();
        //Click on Mac Product
        driver.findElement(By.xpath("//h2//a[@href=\"/apple-macbook-pro-13-inch\"]")).click();
        //Send Email to friend
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //Enter friend's email Address
        driver.findElement(By.xpath("//input[@class='friend-email']")).sendKeys("seemasoftware19@gmail.com");
        //Enter Your Email
        driver.findElement(By.xpath("//input[@id='YourEmailAddress']")).click();
        //Enter your message
        driver.findElement(By.id("PersonalMessage")).sendKeys("please check this out");
        // click on send email button
        driver.findElement(By.xpath("//input[@class=\"button-1 send-email-a-friend-button\"]")).click();
        // Save expected Message in String
        String expectedReferMsg = "Email already send";
        // Save Actual Message in String
        String actualReferMsg = driver.findElement(By.xpath("//*[@class='result' and contains(text(),'Your message has been sent.')]")).getText();
        // Comparing Actual and Expected
        Assert.assertEquals(expectedReferMsg, actualReferMsg);
    }

    @Test
    public void UserAbleToNavigateCameraAndPhoto() {
        //Navigate to Electronics
        driver.findElement(By.xpath("//h2/a[@title='Show products in category Electronics']")).click();
        //Navigate to Camera & Photo
        driver.findElement(By.xpath("//h2/a[@title='Show products in category Camera & photo']")).click();
        //Save expected in actualString
        String ExpectedTile = "Camera & photo";
        //Save actual in actualString
        String ActualTitle = driver.findElement(By.xpath("//div[@ class=\"page-title\"]")).getText();
        //Comparing actual and expected
        Assert.assertEquals(ExpectedTile, ActualTitle);
    }

    @Test
    public void UserShouldBeAbleToFilterJwellery() {
        //Navigate to Jewelry Category
        driver.findElement(By.linkText("Jewelry")).click();
        //Filter Jewelery In 700-500 range
        driver.findElement(By.xpath("//a[@ href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]")).click();
        //Save expected title in String variable.
        String ExpectedTitle = "$700.00 - $3,000.00";
        //Save actual title by locator
        String ActualTitle = driver.findElement(By.xpath("//span[@class = 'item']")).getText();
        //Comparing actual and actual
        Assert.assertEquals(ExpectedTitle, ActualTitle);
        // Store Actual price in String
        String ActualPrice= driver.findElement(By.xpath("//span[@class=\"price actual-price\"]")).getText();
        //Print ActualPrice
        System.out.println(ActualPrice);
        // Store Minimum Value
        String MinimumValue = driver.findElement(By.xpath("//span[@class=\"PriceRange\" and  text()='$700.00']")).getText();
        // Print Minimum Value
        System.out.println(MinimumValue);
        //Store MaximumValue
        String MaximumValue = driver.findElement(By.xpath("//span[@class=\"PriceRange\" and text()='$3,000.00']")).getText();
        // Print MaximumValue
        System.out.println(MaximumValue);
        //Convert String ActualPrice to float
        float ap = Float.parseFloat(ActualPrice.replace(",","").substring(1));
        //Print ap to check
        System.out.println(ap);
        //Convert String MaximumPrice to float
        float max = Float.parseFloat(MaximumValue.replace(",","").substring(1));
        //Print Max to check
        System.out.println(max);
        //Convert String MinimumPrice to float
        float min =Float.parseFloat(MinimumValue.substring(1));
        System.out.println(min);
        // Checking actual price between minimum and Maximum Range
        Assert.assertTrue(ap>=min && ap<=max);
    }

    @Test
    public void UserShouldBeAbleToAddTwoProductInShoppingCart() {
        //Click on on books category.
        driver.findElement(By.linkText("Books")).click();
        //Click book name "First Prize Pies".
        driver.findElement(By.linkText("First Prize Pies")).click();
        //Click on "Add to cart".
        driver.findElement(By.xpath("//input[@id='add-to-cart-button-38']")).click();
        //Make Chromedriver Wait Until first product adds into basket.
        driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.SECONDS);
        //Click on book name "Fahrenheit 451 by Ray Bradbury".
        driver.findElement(By.linkText("Fahrenheit 451 by Ray Bradbury")).click();
        //Click on "Add to cart".
        driver.findElement(By.xpath("//input[@id='add-to-cart-button-37']")).click();
        //Make Chromedriver Wait Until second product adds into basket.
        driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.SECONDS);
        //Click on "Shopping cart".
        driver.findElement(By.className("ico-cart")).click();
        //Actual Quantity is (2).
         String Actualresult = driver.findElement(By.className("cart-qty")).getText();
         //Convert (2) into String 2.
         String ActualQuantity = Actualresult.replace("("," ").replace(")"," ").trim();
         //Declaring Expected String Quantity.
        String ExpectedQuantity = "2";
        //Comparing with Actual Quantity by locator.
         Assert.assertEquals(ActualQuantity,ExpectedQuantity);

    }
}