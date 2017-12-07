package com.mytaxi.test.zalandocarttest;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverHelper {
    private static final String CHROMEDRIVER_LINUX_PATH = "/src/test/resources/com/mytaxi/test/zalandocarttest/chromedriver/chromedriver_linux64";
    private static final String CHROMEDRIVER_MACOS_PATH = "/src/test/resources/com/mytaxi/test/zalandocarttest/chromedriver/chromedriver_macos64";
    private static final String CHROMEDRIVER_WINDOWS_PATH = "/src/test/resources/com/mytaxi/test/zalandocarttest/chromedriver/chromedriver.exe";
    private static boolean is_running;
    private static WebDriver driver;
    private static ChromeDriverService service;

    public static WebDriver getDriver() throws IOException {
        if (is_running) {
            return driver;
        } else {
            return startGoogleChromeDriver();
        }
    }

    private static WebDriver startGoogleChromeDriver() throws IOException {

        String chromeDriverPath = "";
        if (SystemUtils.IS_OS_LINUX) {
            chromeDriverPath = System.getProperty("user.dir") + CHROMEDRIVER_LINUX_PATH;
        }
        if (SystemUtils.IS_OS_MAC_OSX) {
            chromeDriverPath = System.getProperty("user.dir") + CHROMEDRIVER_MACOS_PATH;
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            chromeDriverPath = System.getProperty("user.dir") + CHROMEDRIVER_WINDOWS_PATH;
        }
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeDriverPath))
                .usingAnyFreePort()
                .build();
        service.start();

        WebDriver driver = new RemoteWebDriver(service.getUrl(),
                createCapabitilies());
        is_running = true;
        return driver;
    }

    private static DesiredCapabilities createCapabitilies() {
        Map<String, Object> prefs = new HashMap<>();
        // avoid notifications dialog
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    public static void dispose() {
        if (driver == null) {
            return;
        }
        driver.quit();
        service.stop();
        driver = null;
    }
}
