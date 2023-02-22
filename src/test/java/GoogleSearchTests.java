import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTests {

        @Test  //makes this method a runnable test case
        public void testGoogleSearchResultsPageTitle(){



            WebDriver driver = new ChromeDriver(); //

            driver.get("https://www.google.com/");
            WebElement searchBox = driver.findElement(By.name("q"));


            String searchTerm = "chatGPT";
            String expectedTitle =  searchTerm + " - Google Search";
            searchBox.sendKeys(searchTerm, Keys.ENTER);


            String actualTitle = driver.getTitle()+"vdbh";// returns the title of the current page


            Assert.assertEquals(actualTitle, expectedTitle);  // compares the actual with the
            // expected and throws error if not equal
            // and marks the test method as failure


        }

        @Test
        public void testGoogleSearchResultsPageTitle2(){



            WebDriver driver = new ChromeDriver(); //

            driver.get("https://www.google.com/");
            WebElement searchBox = driver.findElement(By.name("q"));


            String searchTerm = "ai";
            String expectedTitle =  searchTerm + " - Google Search";
            searchBox.sendKeys(searchTerm, Keys.ENTER);


            String actualTitle = driver.getTitle();// returns the title of the current page


            Assert.assertEquals(actualTitle, expectedTitle);


        }

        @Test
        public void demoAssertionMethods(){

//        Assert.assertNotEquals("hello", "Hello");
//        Assert.assertTrue("Hello".contains("hi"));
//        Assert.assertFalse("Hello".contains("hi"));


//        Assert.assertEquals("vdhbf", null);
//        Assert.assertNull(null);
            Assert.assertNotNull(null);

//        System.out.println("Hello");


        }
    }


