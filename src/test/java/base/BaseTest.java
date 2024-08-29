package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import utils.Host;
import utils.RedisUtil;
import utils.WebDriverFactory;

import java.io.File;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String env;
    protected String baseUrl;
    protected String apiPath;
    protected String redisKey;
    protected String salesUrl;
    protected String adminAuthToken;

    @BeforeTest
    @Parameters({"env"})
    public void setUp(@Optional("stg2") String env) throws Exception {
        System.out.println("printing line 1");
        this.driver = WebDriverFactory.createDriver();

        this.env = env;
        this.baseUrl = Host.getServerConfig(env).getBaseUrl();
        this.salesUrl=Host.getServerConfig(env).getSalesUrl();
        this.apiPath = Host.getServerConfig(env).getApi();
//        this.redisKey = RedisUtil.getKey(env);
        this.adminAuthToken ="6082351515186501364";
        System.out.println("printing ");
    }

//    @AfterMethod
//    public void takeSS(ITestContext context)
//    {
//        try
//        {
//            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            int count= countImagesInAFolder("/home/yash/IdeaProjects/TMP/src/test/java/screenshots/");
//            FileUtils.copyFile(screenshotFile, new File("/home/yash/IdeaProjects/TMP/src/test/java/screenshots/SoftwareTestingMaterial"+count+".png"));
//        }
//        catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//
//    public int countImagesInAFolder(String path)
//    {
//        File f = new File(path);
//        int count = -1;
//        for (File file : f.listFiles()) {
//            if (file.isFile() && file.getName().endsWith(".jpg")) {
//                count++;
//            }
//        }
//        return count<0?0:count;
//    }
//
//    public void attachSS(String path)
//    {
//        System.out.println("Count of images is: "+ countImagesInAFolder(path));
//        File f= new File(path);
//        for(File file: f.listFiles())
//        {
//            System.out.println("YES");
//            if(file.isFile() && file.getName().endsWith(".png"))
//            {
//                System.out.println("It entered");
//                System.out.println("Filepath is: "+ file.getAbsolutePath());
//                Reporter.log("</br><img id='ErrorResult' src='" + file.getAbsolutePath() + "/>");
//                System.out.println("It exited");
//            }
//        }
//    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            System.out.println("The last ongoing url:\n"+driver.getCurrentUrl());
            driver.quit();
        }
    }
}