/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Boh
 */
public class SearchTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    
    public SearchTest() {
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
    
    @Test
    public void test() throws InterruptedException {
        driver.get(baseUrl + "/samplecenter/faces/index.xhtml");
        driver.findElement(By.id("j_idt11:searchbar")).clear();
        driver.findElement(By.id("j_idt11:searchbar")).sendKeys("yolo");
        Thread.sleep(1000);
        driver.findElement(By.name("j_idt11:j_idt12")).click();
        Thread.sleep(1000);
        assertEquals("1 results", driver.findElement(By.id("resultcount")).getText());
        
    }
}
