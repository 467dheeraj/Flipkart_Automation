package demo;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrapper {

    static WebDriver driver;
    static String URL="https://www.flipkart.com/";

    public Wrapper() {
        //TODO Auto-generated constructor stub
        System.out.println("Constructor: TestCases");
        ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
        Wrapper.driver = new ChromeDriver(options);
        driver.manage().window().maximize(); 
        PageFactory.initElements(driver, this);    
    }

    @FindBy(xpath = "//input[@class='Pke_EE']")
    static WebElement searchBoxElement;

    @FindBy(xpath = "//div[text()='Popularity']")
    static WebElement sortByElement;

    // @FindBy(xpath = "//div[@class='XQDdHH']")
    // static List<WebElement> ratingListElement;

    @FindBy(xpath = "//div[@class='yKfJKb row']")
    static List<WebElement> iphoneElement;

    @FindBy(xpath = "//div[@class='XqNaEv']")
    static WebElement fourStarElement;


    //open flipkart page
    public static void navigate()
    {
        if(!driver.getCurrentUrl().equals(URL))
        {
            driver.get(URL);
        }
    }

    //send washing machine into search bar
    public static void sendKeys(String keys)
    {
        searchBoxElement.clear();
        searchBoxElement.sendKeys(keys);
        searchBoxElement.sendKeys(Keys.ENTER);
    }

    //sort the results by Popularity
    public static void sort() throws InterruptedException
    {
        WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Popularity']")));
        sortByElement.click();
        Thread.sleep(2000);
        }

    //count the items with rating less than or equal to 4 stars.
    public static int count(Double rating) throws InterruptedException
    {
        int count=0;
        List<WebElement>ratingListElement= driver.findElements(By.xpath("//div[@class='XQDdHH']"));
        // WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
        // w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='XQDdHH']")));

            for(WebElement e: ratingListElement)
            {
            if(Double.valueOf(e.getText())<=rating)
            {
                count=count+1;
            }
            }
        return count; 
    }
    
//get iPhone discount percent and remove extra string and convert it into number for comparison and if discount greater than 22% print iPhone model and discount
    public static void getiPhonediscountpercent1()
    {
        for(WebElement e: iphoneElement)
        {
            WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
            w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='UkUFwK']/span")));
            String text=e.findElement(By.xpath(".//div[@class='UkUFwK']/span")).getText();
            String numericString= text.replaceAll("[^0-9]","");
            int number= Integer.parseInt(numericString);
            WebElement modelElement=e.findElement(By.xpath(".//div[@class='KzDlHZ']"));
            String modelName=modelElement.getText();
            if(number>17)
            {
                System.out.println("Model: "+modelName+" Discount: "+ number+"%");
            }    
        }
    }

    //select 4*
    public static void selectFourStar() throws InterruptedException
    {  
        Thread.sleep(3000);
        fourStarElement.click();
        Thread.sleep(3000);
    }

    //printing the Title and image URL of the 5 items with highest number of reviews
    public static void printCup()
    {
        ArrayList<Integer> list= new ArrayList<>();
        List<WebElement> reviewsCountEle=driver.findElements(By.xpath("//span[@class='Wphh3N']"));
        for(WebElement e: reviewsCountEle)
        {
            String text =e.getText();
            String numericString= text.replaceAll("[^\\d]", "");
            int number= Integer.valueOf(numericString);
            list.add(number);
        }

        Collections.sort(list, Collections.reverseOrder()); 
        for(int i=0;i<5;i++)
        {
            String formated="";
            DecimalFormat form= new DecimalFormat("#,###");
            formated=form.format(list.get(i));
            System.out.println(formated);
            WebElement titleEle=driver.findElement(By.xpath("//span[@class='Wphh3N' and contains(text(),'"+formated+"')]/../../a[2]"));
            WebElement imgEle=driver.findElement(By.xpath("//span[@class='Wphh3N' and contains(text(),'"+formated+"')]/../../a[1]/div/div/div/img"));
            System.out.println("Cup Name: "+titleEle.getText());
            System.out.println("Cup Image URL: "+imgEle.getAttribute("src"));
        }
        

    }


    


}
