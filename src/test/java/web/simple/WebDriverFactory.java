package web.simple;

import com.google.common.io.Resources;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import web.simple.config.TestConfigFactory;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebDriverFactory {
    private static TestConfigFactory config = TestConfigFactory.getInstance();
    static Logger log = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver getWebDriver(){
        log.info("getting browser");
        switch (config.getWebConfig().getBrowser()){
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
            default:
                return getChromeDriver();
        }
    }

    public enum Browser{
        CHROME, FIREFOX
    }

    private static ChromeDriver getChromeDriver(){
//        if(System.getProperty("webdriver.chrome.driver").isEmpty()) {
            String chromeBinaryName;
        log.info("OS is " + System.getProperty("os.name"));

        switch (System.getProperty("os.name").toLowerCase()) {
                case "mac os x":
                    chromeBinaryName = "chromedriver";
                    break;
                case "windows 7":
                    chromeBinaryName = "chromedriver.exe";
                    break;
                case "linux":
                default:
                    chromeBinaryName = "chromedriver_linux";
                    break;
            }

        log.info("chromedriver binary is " + chromeBinaryName);

        System.setProperty("webdriver.chrome.driver", Resources.getResource( chromeBinaryName).getPath());
        log.info("webdriver.chrome.driver is set");

//        }

//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox");

        return new ChromeDriver();//chromeOptions);
    }
}
