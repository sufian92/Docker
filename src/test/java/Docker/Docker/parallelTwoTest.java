package Docker.Docker;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class parallelTwoTest {
	
	DesiredCapabilities caps =  DesiredCapabilities.chrome();
	
	final String URL = "http://localhost:4444/wd/hub";
	
	ThreadLocal<WebDriver> driverThread =new ThreadLocal<WebDriver>();
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void initialize() throws IOException
	{
	
		driver =new RemoteWebDriver(new URL (URL),caps);
		driverThread.set(driver);

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
			
		driverThread.get().quit();
		
			
		}

}
