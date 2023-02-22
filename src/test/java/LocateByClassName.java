import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;

public class LocateByClassName {


    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

//        WebElement link = driver.findElement(By.className("MV3Tnb"));
//        link.click();

//        List<WebElement> allLinks = driver.findElements(By.className("MV3Tnb"));

        driver.findElement(By.name("q")).sendKeys("ChatGPT", Keys.ENTER);

        // locate all elements with a classname LC20lb (all search results have the same class)
        List<WebElement> allSearchResults = driver.findElements(By.className("LC20lb"));

        for (WebElement result : allSearchResults) {

            System.out.println(result.getText());
            if(! result.getText().isEmpty()) {
                Assert.assertTrue(result.getText().contains("ChatGPT"));
            }
    }
}

}
