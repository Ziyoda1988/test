import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class SeleniumDemo {

        public static void main(String[] args) {



            WebDriver driver = new ChromeDriver(); //
            // Navigate to google.com
            driver.get("https://www.google.com/");
            // Locate the element
            WebElement searchBox = driver.findElement(By.name("q"));

            // Enter a search term
            String searchTerm = "chatGPT";
            String expectedTitle =  searchTerm + " - Google Search";
            searchBox.sendKeys(searchTerm, Keys.ENTER);

            // In the results page, verify the title contains the search term

            String actualTitle = driver.getTitle();// returns the title of the current page

//        if(actualTitle.equals(expectedTitle)){
//            System.out.println("TEST PASSED.");
//        }else {
//            System.out.println("TEST FAILED. The current title is " + actualTitle + " The expected was " + expectedTitle );
//        }


            Assert.assertEquals(actualTitle, expectedTitle);


        }


    }


