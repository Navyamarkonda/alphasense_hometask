package alphasense.assignment.com.common;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import alphasense.assignment.com.constants.Constants;

public class CommonUtility extends BasePage{

	public static void searchKeyword(String keyword) throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(Constants.Search_path)));
		WebElement webel = driver.findElement(By.xpath(Constants.Search_path));
		driver.findElement(By.className(Constants.Search_class)).click();
		webel.sendKeys(keyword);
		driver.findElement(By.xpath(Constants.Search_path)).sendKeys(Keys.ENTER);
		test.pass("AlphaSense keyword is successful");
		/*Alternate way
		driver.findElement(By.xpath("//pre[@class=' CodeMirror-line ']")).click();
		driver.findElement(By.cssSelector("textarea")).sendKeys("Alphasense",Keys.ENTER);
		 */		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(Constants.Search_container)));
		WebElement wel = driver.findElement(By.xpath(Constants.Search_container));
		Coordinates coordinate = ((Locatable) wel).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
		holdFlow(6000);
		List<WebElement> l = driver.findElements(By.xpath(
				Constants.Last_search_result));
		l.get(l.size() - 1).click();
		test.pass("selected last result");
		String selectedText = l.get(l.size() - 1).getText();
		WebDriverWait waitlast = new WebDriverWait(driver, 10);
		WebDriver d = driver.switchTo().frame(driver.findElement(By.id(Constants.Document_id)));
		waitlast.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.Highlighted_path)));

		List<WebElement> webelm = d.findElements(By.xpath(Constants.Highlighted_path));
		StringBuilder highlitedText = new StringBuilder();
		for (WebElement e : webelm) {
			highlitedText.append(e.getText());
		}
		/*Alternate way
		Assert.assertEquals(StringUtils.containsIgnoreCase( selectedText.replaceAll("\\s", ""), heighlitedText.toString().replaceAll("\\s", "")  ), true);*/
		if (StringUtils.containsIgnoreCase( selectedText.replaceAll("\\s", ""), highlitedText.toString().replaceAll("\\s", "")  )) {
			test.pass("selected text and highlited test are same");
		}
		else                  {
			test.fail("selected text and highlited test are different");
		}
		holdFlow(3000);

	}

	private static void holdFlow(int timetosleep) {
		try {
			Thread.sleep(timetosleep);
		} catch (InterruptedException e) {

		}
	}
}
