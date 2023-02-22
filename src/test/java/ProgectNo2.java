import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.github.javafaker.Faker;

import static org.testng.AssertJUnit.assertEquals;

public class ProgectNo2 {
        public static void main(String[] args) throws InterruptedException {

            // Launch Chrome browser
            WebDriver driver = new ChromeDriver();

            // Navigate to the login page
            driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

            // Login using username Tester and password test
            driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
            driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
            driver.findElement(By.id("ctl00_MainContent_login_button")).click();

            // Click on Order link
            driver.findElement(By.linkText("Order")).click();

            // Generate a random product quantity between 1 and 100
            int quantity = new Random().nextInt(100) + 1;
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(String.valueOf(quantity));

            // Click on Calculate and verify that the Total value is correct
            driver.findElement(By.cssSelector("input[type='submit'][value='Calculate']")).click();
            WebElement totalPriceElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_lblTotal"));
            double totalPrice = Double.parseDouble(totalPriceElement.getText().replace("$", ""));
            double expectedPrice = quantity * 100 * (quantity >= 10 ? 0.92 : 1);
            assert totalPrice == expectedPrice : "Total price is incorrect.";

            // Generate random customer information using the Faker library
            Faker faker = new Faker();
            faker.address();
            Thread.sleep(1000);

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String streetAddress = faker.address().streetAddress();
            String city = faker.address().city();
            String state = faker.address().state();
            String zipCode = faker.address().zipCode();

            // Enter customer information
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(firstName + " " + lastName);
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(streetAddress);
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipCode);

            // Fill out payment information
            WebElement cardTypeField = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
            cardTypeField.click();
            //Select the card type randomly. On each run your script should select a random type.
            // (You need to put all checkboxes into a list and retrieve a random element from the list and click it)
            List<WebElement> cardTypeCheckboxes = driver.findElements(By.cssSelector("input[name='ctl00$MainContent$fmwOrder$cardList']"));
            cardTypeCheckboxes.get(new Random().nextInt(cardTypeCheckboxes.size())).click();

            //Enter the random card number:
            //      If Visa is selected, the card number should be a visa number that starts with 4.
            //      If MasterCard is selected, card number should be a mastercard number that starts with 5.
            //      If American Express is selected, card number should be an amex number that starts with 3.

            String cardNumber = "";
            if (cardTypeField.getAttribute("value").equals("Visa")) {
                cardNumber = "4" + faker.number().digits(15);
            } else if (cardTypeField.getAttribute("value").equals("MasterCard")) {
                cardNumber = "5" + faker.number().digits(15);
            } else if (cardTypeField.getAttribute("value").equals("American Express")) {
                cardNumber = "3" + faker.number().digits(14);
            }
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);

            // Enter a random expiration date
            String expirationDate = "0" + (new Random().nextInt(9) + 1) + "/" + (new Random().nextInt(89) + 10);
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expirationDate);

            // Click on Process button to submit the order
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();


            // 14. Click on Process
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

            // 15. Verify that “New order has been successfully added” message appeared on the page
            String successMessage = "New order has been successfully added.";
            String actualMessage = driver.findElement(By.xpath("//strong")).getText();
            if (successMessage.equals(actualMessage)) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
                System.out.println("Expected: " + successMessage);
                System.out.println("Actual: " + actualMessage);
            }


            // 16. Click on View all orders link
            driver.findElement(By.linkText("View all orders")).click();
            // Verify that the placed order details match the previously entered information
            List<WebElement> orderRows = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr"));
            WebElement firstOrderRow = orderRows.get(1); // The first row after the header row
            String actualName = firstOrderRow.findElement(By.xpath(".//td[2]")).getText();
            String actualProduct = firstOrderRow.findElement(By.xpath(".//td[3]")).getText();
            String actualQuantity = firstOrderRow.findElement(By.xpath(".//td[4]")).getText();
            String actualPricePerUnit = firstOrderRow.findElement(By.xpath(".//td[5]")).getText();
            String actualTotal = firstOrderRow.findElement(By.xpath(".//td[6]")).getText();

            String expectedName = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).getAttribute("value");
            String expectedProduct = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")).getAttribute("value");
            String expectedQuantity = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).getAttribute("value");
            String expectedPricePerUnit = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice")).getAttribute("value");
            String expectedTotal = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");


// Log out of the application
            driver.findElement(By.id("ctl00_logout")).click();

// Close the browser
            driver.quit();


        }}