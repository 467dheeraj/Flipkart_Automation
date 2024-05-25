package demo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



public class TestCases extends Wrapper {
    //static ChromeDriver driver;
    public TestCases()
    {
        super();
    }

    @AfterTest
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    @Test(priority=0, description="Search 'Washing Machine'. Sort by popularity and print the count of items with rating less than or equal to 4 stars.")
    public void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        Wrapper.navigate();
        Wrapper.sendKeys("Washing Machine");
        Wrapper.sort();
        int count=Wrapper.count(4.0);
        System.out.println("Count of items with less than or equal to 4 * ratings are: "+count);
        System.out.println("end Test case: testCase01");
    }

    @Test(priority = 1, description = "Search 'iPhone', print the Titles and discount % of items with more than 17% discount.")
    public void testCase02()
    {
        System.out.println("Start Test case: testCase02");
        Wrapper.navigate();
        Wrapper.sendKeys("iPhone");
        Wrapper.getiPhonediscountpercent1();
        System.out.println("end Test case: testCase02");
    }

    @Test(priority = 2, description = "Search 'Coffee Mug', select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews.")
    public void testCase03()
    {
        System.out.println("Start Test case: testCase03");
        Wrapper.navigate();
        Wrapper.sendKeys("Coffee Mug");
        try {
            Wrapper.selectFourStar();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Wrapper.printCup();
        System.out.println("end Test case: testCase03");
    }


}
