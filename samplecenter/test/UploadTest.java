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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author loris
 */

@RunWith(Parameterized.class)
public class UploadTest {
    private WebDriver driver;
    private String baseUrl;
    private String title;
    private String tag;
    private String file;
    // FOLDER IS BULLSHIT
    private String fkAuthor;
    private String outputString;
    
    
    @Parameterized.Parameters
    public static Iterable<Object[]>data(){
        return Arrays.asList(new Object[][]
        {
            {"test 1", "tag 1", "/home/loris/Desktop/test_samples/acoustic-kick.wav", "samplecenter.User[ iduser=1 ]", "Sample was successfully created."},  // OK
            {"test 2", "tag 2", "/home/loris/Desktop/test_samples/picture.jpg", "samplecenter.User[ iduser=1 ]", "The file isnt an audio file"},             // WRONG FILE TYPE
            {"test 3", "tag 3", "", "samplecenter.User[ iduser=1 ]", "The file field is required."},                                                     // NO FILE
            {"test 4", "tag 4", "/home/loris/Desktop/test_samples/acoustic-kick.wav", "---", "The FkAuthor field is required."},           // NO FKAUTHOR
        });
    }
    
    public UploadTest(String title, String tag, String file, String fkAuthor, String outputString) {
        this.title = title;
        this.tag = tag;
        this.file = file;
        this.fkAuthor = fkAuthor;
        this.outputString = outputString;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    System.setProperty("webdriver.chrome.driver", "/home/loris/Desktop/chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.setBinary("/usr/bin/google-chrome-stable");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void uploadOK(){
        driver.get(baseUrl + "/samplecenter/faces/index.xhtml");
        driver.findElement(By.linkText("Upload")).click();
        driver.findElement(By.id("j_idt21:title")).clear();
        driver.findElement(By.id("j_idt21:title")).sendKeys(title);
        driver.findElement(By.id("j_idt21:tag")).clear();
        driver.findElement(By.id("j_idt21:tag")).sendKeys(tag);
        driver.findElement(By.id("j_idt21:file")).clear();
        driver.findElement(By.id("j_idt21:file")).sendKeys(file);
        driver.findElement(By.xpath("//li[@id='j_idt21:j_idt27:0']/span/span")).click();
        driver.findElement(By.xpath("//li[@id='j_idt21:j_idt27:0_1']/span/span[3]")).click();
        new Select(driver.findElement(By.id("j_idt21:fkAuthor"))).selectByVisibleText(fkAuthor);
        driver.findElement(By.linkText("Save")).click();
        if( outputString.equalsIgnoreCase(driver.findElement(By.cssSelector("td")).getText()))
        {
            System.out.println("Test OK");
        }
        else
        {
            System.out.println(driver.findElement(By.cssSelector("td")).getText());
            System.out.println("Test KO");
        }
    }
  
}
