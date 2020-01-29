
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/features/ui/mobile/ios"},
        tags = {"@ios"},
        glue = {"os.StepDefinitions"},
        dryRun = false,
        format = {"pretty"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/MobileReports/IOS.html"}
        , monochrome = true)


public class IOSTestRunner {
    static AppiumDriverLocalService service;
    static String service_url;

    @BeforeClass
    public static void appiumServer() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }


    @org.junit.AfterClass
    public static void writeExtentReport() throws IOException {
        service.stop();
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+ "/extent-ios-config.xml"));
        copyLatestExtentReport();
        Reporter.loadXMLConfig("extent-ios-config.xml");
    }

    /***EXTENT REPORT****************************************************************/

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static void copyLatestExtentReport() throws IOException {
        String timestamp = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime()).replaceAll(":", "-");
        File source = new File(System.getProperty("user.dir") + "/target/cucumber-reports/MobileReports/IOS.html");
        File dest = new File(System.getProperty("user.dir") + "/target/cucumber-reports/MobileReports/IOS_"+ timestamp + ".html");
        copyFileUsingStream(source, dest);
    }
}
