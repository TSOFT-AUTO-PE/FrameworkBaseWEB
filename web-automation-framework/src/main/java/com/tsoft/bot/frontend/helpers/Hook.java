package com.tsoft.bot.frontend.helpers;

import com.tsoft.bot.frontend.listener.Listener;
import com.tsoft.bot.frontend.utility.FileHelper;
import com.tsoft.bot.frontend.utility.GenerateWord;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

//import static com.tsoft.bot.frontend.BaseClass.stepFailNoShoot;

public class Hook extends Listener {

	private static final String GECKO_KEY 		= "webdriver.gecko.driver";
	private static final String GECKO_DRIVER 	= "/src/main/resources/driver/firefox/0.26/geckodriver.exe";
	private static final String CHROME_KEY 		= "webdriver.chrome.driver";
	private static final String CHROME_DRIVER 	= "/src/main/resources/driver/chrome/79.0/chromedriver.exe";
	private static final String IE_KEY 			= "webdriver.ie.driver";
	private static final String IE_DRIVER 		= "/src/main/resources/driver/ie/3.5/IEDriverServer.exe";
	private static final long DELAY = 10;
	private static WebDriver driver;

	static GenerateWord generateWord = new GenerateWord();

	@Before
	public void Scenario(Scenario scenario){
		onTestStart(scenario.getName());
	}

	@Before
	public void setUpWeb() {

		System.setProperty(CHROME_KEY, FileHelper.getProjectFolder() + CHROME_DRIVER);

		ChromeOptions chromeOptions = new ChromeOptions();

		chromeOptions.addArguments(
				"--verbose",
//							"--headless",
				"--disable-web-security",
				"--ignore-certificate-errors",
				"--allow-running-insecure-content",
				"--allow-insecure-localhost",
				"--no-sandbox",
				"--disable-gpu"
		);

		driver = new ChromeDriver(chromeOptions);

		getDriver().manage().window().maximize();

		getDriver().manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);

		generateWord.startUpWord();

	}

	@After
	public void tearDown() throws IOException {

		onFinish();

		generateWord.endToWord();

		getDriver().quit();
	}

	public static WebDriver getDriver() { return driver; }

}