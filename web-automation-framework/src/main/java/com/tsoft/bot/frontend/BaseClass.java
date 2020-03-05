package com.tsoft.bot.frontend;

import com.tsoft.bot.frontend.exceptions.FrontEndException;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import com.tsoft.bot.frontend.utility.Sleeper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class BaseClass {

    static GenerateWord generateWord = new GenerateWord();
    private static final long MAX_WAIT_TIME_SECONDS = 90;
    private static final long NEXT_TRY_TIME_MILISECONDS = 200;

    public static void sendKeyValue(WebDriver driver, String key, String locator, String value) throws Throwable {
        try {

            switch (key.toLowerCase()) {

                case "id":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.id(locator))).sendKeys(value);
                    break;

                case "name":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.name(locator))).sendKeys(value);
                    break;

                case "css":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator))).sendKeys(value);
                    break;

                case "linktext":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator))).sendKeys(value);
                    break;

                case "xpath":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).sendKeys(value);
                    break;
            }

        } catch (Throwable t) {

            generateWord.sendText("No se encontró el elemento : " + locator);

            generateWord.addImageToWord(driver);

            handleError(driver, "", "No se encontro el elemento : " + locator);

            driver.close();

            throw t;
        }
    }

    public static void click(WebDriver driver, String key, String locator) throws Throwable {
        try {

            switch (key.toLowerCase()) {

                case "id":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.id(locator))).click();
                    break;

                case "name":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.name(locator))).click();
                    break;

                case "css":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator))).click();
                    break;

                case "linktext":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator))).click();
                    break;

                case "xpath":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
                    break;
            }

        } catch (Throwable t) {

            generateWord.sendText("Error : No se encontró el elemento : " + locator);

            generateWord.addImageToWord(driver);

            handleError(driver, "", "No se encontro el elemento : " + locator);

            driver.close();

            throw t;
        }
    }

    public static void clear(WebDriver driver, String key, String locator) throws Throwable {
        try {

            switch (key.toLowerCase()) {

                case "id":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.id(locator))).clear();
                    break;

                case "name":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.name(locator))).clear();
                    break;

                case "css":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator))).clear();
                    break;

                case "linktext":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator))).clear();
                    break;

                case "xpath":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).clear();
                    break;
            }
        } catch (Throwable t) {

            generateWord.sendText("Error : No se encontro el elemento : " + locator);

            generateWord.addImageToWord(driver);

            handleError(driver, "", "No se encontro el elemento : " + locator);

            driver.close();

            throw t;
        }
    }

    public static void selectByVisibleText(WebDriver driver, String locator, String value) throws Throwable {

        String found = value;

        Select select = new Select(driver.findElement(By.id(locator)));

        List<WebElement> options = select.getOptions();

        if (CollectionUtils.isNotEmpty(options)) {

            for (WebElement option : options) {

                if (StringUtils.equalsIgnoreCase(option.getText(), locator)) {

                    found = option.getText();

                    break;
                }
            }
        }

        if (found == null) {

            handleError(driver, "", "No se encontro el elemento : " + locator);

            generateWord.sendText("No se encontró el elemento : " + locator);

            generateWord.addImageToWord(driver);
        }

        select.selectByVisibleText(found);
    }

    public static String getNumberOfCaracter(String caracter) {

        char[] caracterArray = caracter.toCharArray();

        String number = StringUtils.EMPTY;

        for (int i = 0; i < caracterArray.length; i++) {

            if (Character.isDigit(caracterArray[i])) {

                number += caracterArray[i];

            }
        }
        return number;
    }

    public static Exception handleError(WebDriver driver, String codigo, String msg) throws Throwable {
        stepWarning(driver, msg);
        return new FrontEndException(StringUtils.trimToEmpty(codigo), msg);
    }

    public static String waitForElementNotVisible(WebDriver driver, String key, String locator) throws Throwable {
        if ((driver == null) || (locator == null) || locator.isEmpty()) { return "[Error : Mal uso del metodo waitForElementNotVisible()]"; }
        try {
            switch (key.toLowerCase()) {

                case "id":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
                    break;

                case "name":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.name(locator)));
                    break;

                case "css":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));
                    break;

                case "linktext":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(locator)));
                    break;

                case "xpath":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
                    break;
            }
            return null;
        } catch (TimeoutException e) {
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw e;
        }
    }

    public static void writeHowHuman(WebDriver driver, String locator, String text) {

        Random r = new Random();

        WebElement webElement = driver.findElement(By.id(locator));

        for (int i = 0; i < text.length(); i++) {

            try {

                Thread.sleep((int) (r.nextGaussian() * 12 + 110));

            } catch (InterruptedException ignored) {
            }

            String s = new StringBuilder().append(text.charAt(i)).toString();

            webElement.sendKeys(s);
        }
    }

    public static void selectByValue(WebDriver driver, String key, String locator, String value) throws Throwable {
        String found = value;
        Select select ;
        try {
            switch (key.toLowerCase()) {
                case "id":
                    select = new Select(driver.findElement(By.id(locator)));
                    break;
                case "name":
                    select = new Select(driver.findElement(By.name(locator)));
                    break;
                case "css":
                    select = new Select(driver.findElement(By.cssSelector(locator)));
                    break;
                case "linktext":
                    select = new Select(driver.findElement(By.linkText(locator)));
                    break;
                case "xpath":
                    select = new Select(driver.findElement(By.xpath(locator)));
                    break;
                default:
                    select = null;
            }
        } catch (Throwable t) {
            stepWarning(driver, "No encontró el elemento " + locator);
            throw new FrontEndException("BC-001", "No encontró el elemento " + locator);
        }


        select.selectByValue(found);
    }

    public String getText(WebDriver driver, String key, String locator) throws Throwable {
        try{
            WebElement webElement = null;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    new FrontEndException("BC-005", "No se encontro :" + webElement);
            }
            return StringUtils.trimToEmpty(webElement.getText());

        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            throw t;
        }
    }

    public Boolean isEnabled(WebDriver driver, String key, String locator) throws Throwable {
        try{
            WebElement webElement;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    webElement = null;
            }

            return webElement.isEnabled();
        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }

    public Boolean isDisplayed(WebDriver driver, String key, String locator) throws Throwable {
        try{
            WebElement webElement;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    webElement = null;
            }

            return webElement.isDisplayed();

        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }

    public Boolean isSelected(WebDriver driver, String key, String locator) throws Throwable {
        try{
            WebElement webElement;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    webElement = null;
            }

            return webElement.isSelected();

        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }

    public static void scrollBar(WebDriver driver, int size) {
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript("window.scrollBy(0, " + size + ")");
    }

    public static void scrollBarElement(WebDriver driver, String idElement, int size) {
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        System.out.println("document.getElementById('" + idElement + "').scrollBy(0, " + size + ")");
        ev.executeScript("document.getElementById('" + idElement + "').scrollBy(0, " + size + ")");
    }

    public static void sendTextJS(WebDriver driver, String elementId, String msg) {
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript("document.getElementById('" + elementId + "').setAttribute('value', '" + msg + "');");
    }

    public static void sleep(int milisegundos) {
        Sleeper.Sleep(milisegundos);
    }

    public static void stepPass(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepPass(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(t.getStackTrace());
            throw t;
        }
    }

    public static void stepWarning(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepWarning(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(t.getStackTrace());
            throw t;
        }
    }

//    public static void stepWarningNoShoot(String descripcion) throws Throwable {
//        try {
//            ExtentReportUtil.INSTANCE.stepWarningNoShoot(descripcion);
//        } catch (Throwable t) {
//            System.out.println(t.getStackTrace());
//            throw t;
//        }
//    }

//    public static void stepFailNoShoot(String descripcion) throws Throwable {
//        try {
//            ExtentReportUtil.INSTANCE.stepFailNoShoot(descripcion);
//        } catch (Throwable t) {
//            System.out.println(t.getStackTrace());
//            throw t;
//        }
//    }

    public static void stepFail(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepFail(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(t.getStackTrace());
            throw t;
        }
    }

    public static void stepError(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepError(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(t.getStackTrace());
            throw t;
        }
    }

    public static void stepInfo(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepInfo(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(t.getStackTrace());
            throw t;
        }
    }

//    public static void stepInfo(String descripcion) throws Throwable {
//        try {
//            ExtentReportUtil.INSTANCE.stepInfo(descripcion);
//        } catch (Throwable t) {
//            System.out.println(t.getStackTrace());
//            throw t;
//        }
//    }

}