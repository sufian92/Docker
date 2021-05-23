package Docker.Docker;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class parallelOneTest {
	
	DesiredCapabilities caps =  DesiredCapabilities.chrome();
	ThreadLocal<WebDriver> driverThread =new ThreadLocal<WebDriver>();
	
	
	final String URL = "http://localhost:4444/wd/hub";
	
	WebDriver driver;
	
	@BeforeMethod
	public void initialize() throws IOException
	{
	
		driver =new RemoteWebDriver(new URL (URL),caps);
		driverThread.set(driver);

	}
	
  
  @Test
  public void parallelOne() {
	  
	    driverThread.get().get("https://rahulshettyacademy.com/AutomationPractice/");
		
		WebElement option2CheckBox = driverThread.get().findElement(By.id("checkBoxOption2"));
		
		option2CheckBox.click();
		
		Select s = new Select(driverThread.get().findElement(By.id("dropdown-class-example")));
		
		s.selectByValue(option2CheckBox.getAttribute("value"));
		
		driverThread.get().findElement(By.id("name")).sendKeys(option2CheckBox.getAttribute("value"));
		
		driverThread.get().findElement(By.id("alertbtn")).click();
		
		String alertMsg = driverThread.get().switchTo().alert().getText();
		
		driverThread.get().switchTo().alert().accept();
		
		if (alertMsg.contains(option2CheckBox.getAttribute("value"))) {
			
			System.out.print("alert contains the right option");
			
		}else {
			
			System.out.print("alert does not contain the right option");
		}
		
  }
  
  @Test
  public void paralleltwo() {
	  
	  	driverThread.get().get("https://the-internet.herokuapp.com/");
		
		driverThread.get().findElement(By.cssSelector("a[href = '/windows']")).click();
		
		driverThread.get().findElement(By.cssSelector("a[href = '/windows/new']")).click();
		
		Set<String> ids = driverThread.get().getWindowHandles();
		
		String parentId = ids.iterator().next();
		
		String childId = ids.iterator().next();
		
		driverThread.get().switchTo().window(childId);
		
		System.out.print(driverThread.get().findElement(By.tagName("h3")).getText());
		
		driverThread.get().switchTo().window(parentId);
		
		System.out.print(driverThread.get().findElement(By.tagName("h3")).getText());
		
  }

  
  @AfterMethod
	public void teardown()
	{
		
	driverThread.get().quit();;
	
		
	}
}
