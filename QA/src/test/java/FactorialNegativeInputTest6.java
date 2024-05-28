import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FactorialNegativeInputTest6 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Optional: Run in headless mode
        driver = new ChromeDriver(options);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, 10);

        // Launch the application
        driver.get("http://localhost:6464/factorial");
    }

    @Test
    public void testFactorialWithNegativeInput() {
        // Locate the input element by its id
        WebElement numberInput = driver.findElement(By.id("number"));

        // Enter the number -5 into the input field
        numberInput.sendKeys("-5");

        // Locate the submit button and click it
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Wait for the result to be displayed and locate the result element
        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"resultDiv\"]")));

        // Extract the text from the result element
        String resultText = resultElement.getText();

        // Check if the result contains the expected error message
        Assert.assertTrue(resultText.contains("Error: Factorial of a negative number is undefined"),
                "Test failed: Expected error message but got " + resultText);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
