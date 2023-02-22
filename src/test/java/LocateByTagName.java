import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;




    public class LocateByTagName {


        public static void main(String[] args) {

            WebDriver driver = new ChromeDriver();
            driver.get("https://www.duotech.io/");

            System.out.println(  driver.findElement(By.tagName("h1")).getText() );
//        System.out.println(  driver.findElement(By.tagName("h2")).getText() );

            // Locate all links on the page and print their text

            List<WebElement> links = driver.findElements(By.tagName("a"));

            System.out.println("The number of links on the page: " + links.size());
            for (WebElement link : links) {
                System.out.println(link.getText());
            }

            // find all paragraphs on the page
            List<WebElement> paragraphs = driver.findElements(By.tagName("p"));

            for (WebElement paragraph : paragraphs) {
                System.out.println(paragraph.getText());
            }


        }
    }
