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

public class FactorialTest7 {
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
    public void testFactorialOfPositiveNumber() {
        // Test case TC-001-01
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("7");
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"resultDiv\"]")));
        String resultText = resultElement.getText();
        Assert.assertTrue(resultText.contains("The factorial of 7 is: 5040"), "Expected 5040 but got " + resultText);
    }

    @Test
    public void testFactorialOfZero() {
        // Test case TC-001-02
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("0");
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"resultDiv\"]")));
        String resultText = resultElement.getText();
        System.out.println("fdfdddddd" +resultText);
        Assert.assertTrue(resultText.contains("The factorial of 0 is: 1"), "Expected 1 but got " + resultText);

    }

    @Test
    public void testFactorialOfNegativeNumber() {
        // Test case TC-001-03
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("-5");
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"resultDiv\"]")));
        String resultText = resultElement.getText();
        Assert.assertTrue(resultText.contains("Error: Factorial of a negative number is undefined"),
                "Expected error message but got " + resultText);
    }

    @Test
    public void testFactorialOfNonIntegerInput() {
        // Test case TC-001-04
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.sendKeys("abc");
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"resultDiv\"]")));
        String resultText = resultElement.getText();
        Assert.assertTrue(resultText.contains("Please enter an integer"));
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
