
    import org.openqa.selenium.By;
    import org.openqa.selenium.Keys;
    import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

    import java.util.List;

    public class warmUp2 {

        public static void main(String[] args) {
//            1.Navigate to dice.com
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.dice.com/");


//            2.Enter SDET to search field and click Search Jobs button
            driver.findElement(By.id("typeaheadInput")).sendKeys("SDET", Keys.ENTER);
            List<WebElement> jobTitles = driver.findElements(By.xpath("//a[@class='card-title-link bold']"));
            if(jobTitles.size() == 0){
               throw new RuntimeException("No results found");
            }else{
                System.out.println("Job titles found: " + jobTitles.size());
            }

            for (   WebElement jobTitle : jobTitles) {
                System.out.println(jobTitle.getText());

            }


            }
//
            }












