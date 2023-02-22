import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.github.javafaker.Faker;
import org.testng.Assert;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Automation2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ziyod\\Downloads\\Telegram Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to login page
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        // Login using username Tester and password test
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

        // Click on Order link
        driver.findElement(By.linkText("Order")).click();

        // Enter a random product quantity between 1 and 100
        int quantity = new Random().nextInt(100) + 1;
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(String.valueOf(quantity));

        // Click on Calculate and verify that the Total value is correct.
        driver.findElement(By.cssSelector("input[value='Calculate']")).click();
        double expectedTotal;
        if (quantity >= 10) {
            expectedTotal = quantity * 100 * 0.92; // 8% discount
        } else {
            expectedTotal = quantity * 100;
        }
        int actualTotal = Integer.parseInt(driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value"));
        if (actualTotal == expectedTotal) {
            System.out.println("Total value is correct: " + actualTotal);
        } else {
            System.out.println("Total value is incorrect: " + actualTotal);
        }

        // Use Faker class and mockaroo.com to enter random customer information

        Faker faker = new Faker();
        faker.address();
        Thread.sleep(1000);
       //  String[] customerData = readFromCSV("C:\\Users\\ziyod\\IdeaProjects\\Automation1\\src\\test\\java\\CustomerData.csv");
         String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String streetAddress = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();

//        public static String[] readFromCSV(String filePath) throws IOException {
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//            String line = reader.readLine();
//            String[] data = line.split(",");
//            reader.close();
//            return data;



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

        //Verify that “New order has been successfully added” message appeared on the page.
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='buttons_process']/strong"));
        assertTrue(successMessage.isDisplayed());

        // Click on View All Orders link.
        driver.findElement(By.linkText("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Default.aspx")).click();

        //The placed order details appears on the first row of the orders table. Verify that the entire information contained on the row
        // (Name, Product, Quantity, etc) matches the previously entered information in previous steps.
//           WebElement name = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[2]"));
//        WebElement product = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[3]"));
//        WebElement quantity1 = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[4]"));
//        WebElement price = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[5]"));
//        WebElement discount = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[6]"));
//        WebElement total = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[7]"));
//        WebElement card = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[8]"));
//        WebElement cardNumber1 = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[9]"));
//        WebElement expirationDate1 = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[10]"));

//        assertEquals(name.getText(), firstName + " " + lastName);
//        assertEquals(product.getText(), "MyMoney");
//        assertEquals(quantity1.getText(), "2");
//        assertEquals(price.getText(), "$100");
//        assertEquals(discount.getText(), "8%");
//        assertEquals(total.getText(), "$184");
//        assertEquals(card.getText(), "Visa");
//        assertEquals(cardNumber1.getText(), cardNumber);
//        assertEquals(expirationDate1.getText(), expirationDate);

        WebElement successMessage1 = driver.findElement(By.xpath("//div[@class='buttons_process']/strong"));
        String expectedMessage = "New order has been successfully added.";
        String actualMessage = successMessage.getText();

        Assert.assertEquals(actualMessage, expectedMessage);

        driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td[1]/ul/li[1]/a")).click();

        driver.findElement(By.id("ctl00_logout")).click();

        //Log out of the application.
        //  driver.findElement(By.linkText("javascript:__doPostBack('ctl00$logout','')))").click();



        // Close the browser
       // driver.quit();
    }}









