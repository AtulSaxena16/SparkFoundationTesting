package SparkFoundation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SparkTest2 {

	ChromeDriver driver;
	String url1="https://www.thesparksfoundationsingapore.org/";
	
	public void invokeBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "D:/@@@/Atul/Softwares/JARS/Selenium setup/Chromedriver//chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//Maximum time selenium waits for a page to load successfully on a browser
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		driver.get(url1);
	}
	public void mouseHover()
	{

		//actions methods are used for mouse controlling
		Actions action=new Actions(driver);
		
		//Step-1 Checking Logo of the website
		WebElement logo = driver.findElement(By.className("navbar-brand").tagName("img"));
		System.out.println("\n");
		System.out.println("------------------------------------------------");
		System.out.print("1. Checking LOGO image: ");
		if (logo != null) 
		{
			System.out.println("Image is present!");
			//xpath for The Spark Foundation icon - for clicking into icon
			driver.findElement(By.xpath("//a[@class='col-md-6 navbar-brand']")).click();
		} else 
		{
			System.out.println("image is NOT present!");
		}
		
		//Step-2 Checking Navigation Bar
		WebElement navbar = driver.findElement(By.tagName("nav"));
		System.out.println("\n");
		System.out.println("------------------------------------------------");
		System.out.print("2. Checking Navbar: ");
		if (navbar != null) {
			System.out.println("Navigation bar is present!");
		} else {
			System.out.println("Navigation bar is NOT present!");
		}
		
		System.out.println("\n");
		System.out.println("Clicking on About Us option in Homepage !");
		//WebElement represents HTML elements - Operations
		WebElement about=driver.findElement(By.xpath("//a[text()='About Us']"));
				
		// action method for moving mouse cursor to About Us and clicking that option
		action.moveToElement(about).click().build().perform();
		
		System.out.println("\n");
		System.out.println("Now click on Vision,Mission and Values option in About Us:");
		
		//clicking Vision, Mission and Values option in the About Us option
		driver.findElement(By.xpath("//a[@href='/about/vision-mission-and-values/']")).click();
		
		
		System.out.println("\n");
		System.out.println("------------------------------------------------");
		//Checking for broken links in website
		System.out.println("3. Checking all the links(a-tags) in the Website: ");
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) 
		{

			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) 
			{
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith(url1)) 
			{
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try 
			{
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) 
				{
					System.out.println(url + " is a broken link");
				} else 
				{
					System.out.println(url + " is a valid link");
				}
			} 
			catch (MalformedURLException e) 
			{

				e.printStackTrace();

			}
			catch (IOException e) 
			{

				e.printStackTrace();

			}
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("\n");
		System.out.println("Going back to home page of Spark Foundation !");
		
		Actions action1=new Actions(driver);
	
		//Going back to Main page by clicking the Logo of Spark Foundation which was at the end of the page
		driver.findElement(By.xpath("//h6/a[@href='/']")).click();
		
		System.out.println("\n");
		System.out.println("Clicking on Policies and Code option: ");
		driver.findElement(By.xpath("(//ul[1]//a[@href='#'])[2]")).click();
		
		System.out.println("\n");
		System.out.println("Now clickin on Policies in Policies and Code!");
		
		//Actions action1=new Actions(driver);
		WebElement action2=driver.findElement(By.xpath("//a[@href='/policies-and-code/policies/']"));
		action1.moveToElement(action2).click().build().perform();
		
		System.out.println("\n");
		System.out.println("---------------------------------------------------------");
		System.out.println("\n");
		//Going back to home
		System.out.println("Going back to home");
		driver.findElement(By.xpath("//h6/a[@href='/']")).click();
}
}