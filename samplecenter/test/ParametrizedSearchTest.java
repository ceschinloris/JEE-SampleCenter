/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ParametrizedSearchTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    private String pattern;
    private String result;
    
    public ParametrizedSearchTest(String pattern, String result) {
        this.pattern = pattern;
        this.result = result;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
    
    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {"africa", "1 results"},
            {"kick", "3 results"},
            {"asdfasdfasdf", "12 results"}
        });
    }
    
    @Test
    public void test() throws InterruptedException {
        driver.get(baseUrl + "/samplecenter/faces/index.xhtml");
        driver.findElement(By.id("j_idt11:searchbar")).clear();
        driver.findElement(By.id("j_idt11:searchbar")).sendKeys(pattern);
        Thread.sleep(1000);
        driver.findElement(By.name("j_idt11:j_idt12")).click();
        Thread.sleep(1000);
        assertEquals(result, driver.findElement(By.id("resultcount")).getText());
    }
}
