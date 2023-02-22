import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstProject {
    @Test
    public void test1() throws InterruptedException {

        //1. Navigate to http://duotify.us-east-2.elasticbeanstalk.com/register.php
        WebDriver driver = new ChromeDriver();
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        //2. Verify the title is "Welcome to Duotify!"
        Thread.sleep(1000);
        String expectedT = "Welcome to Duotify!";
        String actualT = driver.getTitle();

        if (actualT.equals(expectedT)){
            System.out.println("Title verification successful");
        }
        else {
            System.out.println("Title verification fail");
        }

        //3. Click on Signup here
        Thread.sleep(2000);
        WebElement signUp = driver.findElement(By.id("hideLogin"));
        signUp.click();

        //4. Fill out the form with the required info using Faker class
        Faker faker = new Faker();
        Thread.sleep(1000);
        String userName = faker.name().username();
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(userName);

        WebElement firstNaF = driver.findElement(By.id("firstName"));
        firstNaF.sendKeys(faker.name().firstName());

        WebElement lastNaF = driver.findElement(By.id("lastName"));
        lastNaF.sendKeys(faker.name().lastName());

        String email = faker.internet().emailAddress();
//email
        driver.findElement(By.id("email")).sendKeys(email);
//confermation
        driver.findElement(By.name("email2")).sendKeys(email);

        String passWord = faker.internet().password();
        //password
        driver.findElement(By.id("password")).sendKeys(passWord);
//confermation
        driver.findElement(By.id("password2")).sendKeys(passWord);

        //5. Click on Sign up
        Thread.sleep(1000);
        WebElement registerButton =  driver.findElement(By.name("registerButton"));
        registerButton.click();

//6. Once logged in to the application, verify that the URL is:
//http://duotify.us-east-2.elasticbeanstalk.com/browse.php?
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("http://duotify.us-east-2.elasticbeanstalk.com/browse.php?")){
            System.out.println("URL verification successful");
        } else {
            System.out.println("URL verification failed");
        }
//7. In the left navigation bar, verify that your first and last name is the same the first and last name that you
// used when signing up.
// (use getText() method to extract the text of the element)
        Thread.sleep(1000);
        WebElement nameElement = driver.findElement(By.id("nameFirstAndLast"));
        String verifyName = nameElement.getText();
        if (verifyName.equals(firstNaF + " " + lastNaF)) {
            System.out.println("S7 Name verification successful");
        } else {
            System.out.println("S7 Name verification failed");
        }
        //8. Click on the first and last name on the left navigation bar and verify the first and last name
        // on the main window is correct and then click logout.
        nameElement.click();
        Thread.sleep(2000);
        WebElement profileNameElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h1[1]"));
        String profileNameText = profileNameElement.getText();
        if (profileNameText.equals(firstNaF + " " + lastNaF)) {
            System.out.println("S8 Profile name verification successful");
        } else {
            System.out.println("S8 Profile name verification failed");
        }
        Thread.sleep(1000);
        WebElement logoutLink = driver.findElement(By.xpath("(//button[normalize-space()='LOGOUT'])[1]"));
        logoutLink.click();

        //9. Verify that you are logged out by verifying the URL is:
        //http://duotify.us-east-2.elasticbeanstalk.com/register.php
        Thread.sleep(1000);
        String currentUrl1 = driver.getCurrentUrl();
        if (currentUrl1.equals("http://duotify.us-east-2.elasticbeanstalk.com/register.php")){
            System.out.println("S9 URL verification successful");
        } else {
            System.out.println("S9 URL verification failed");
        }

        //10. Login using the same username and password when you signed up.
        Thread.sleep(1000);
        WebElement usernameLogin = driver.findElement(By.cssSelector("#loginUsername"));
        usernameLogin.sendKeys(userName);
        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("loginPassword"));
        password.sendKeys(passWord);
        Thread.sleep(1000);
        WebElement loginB = driver.findElement(By.name("loginButton"));
        loginB.click();

        //11. Verify successful login by verifying that the home page contains the text "You Might Also Like".
        Thread.sleep(1000);
        WebElement homeText = driver.findElement(By.xpath("//h1[normalize-space()='You Might Also Like']"));
        String homeTextContent = homeText.getText();
        if (homeTextContent.equals("You Might Also Like")) {
            System.out.println("Login verification passed.");
        } else {
            System.out.println("Login verification failed.");
        }

        //12. Log out once again and verify that you are logged out.
        Thread.sleep(1000);
        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        WebElement logoutLink1 = driver.findElement(By.xpath("(//button[normalize-space()='LOGOUT'])[1]"));
        logoutLink1.click();
        Thread.sleep(1000);
        String expectedUrl = "http://duotify.us-east-2.elasticbeanstalk.com/register.php";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Logout verification passed.");
        } else {
            System.out.println("Logout verification failed.");
        }

        driver.close();
    }
}


