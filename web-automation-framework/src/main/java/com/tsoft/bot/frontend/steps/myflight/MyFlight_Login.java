package com.tsoft.bot.frontend.steps.myflight;

import com.tsoft.bot.frontend.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import com.tsoft.bot.frontend.utility.Sleeper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

import static com.tsoft.bot.frontend.pageobject.myflight.MyFlightPageObject.*;


public class MyFlight_Login extends BaseClass {

    private static final String EXCEL_WEB = "excel/MyFlight.xlsx";
    private static final String LOGIN_WEB = "Login";
    private static final String COLUMNA_URL = "URL";
    private static final String COLUMNA_USUARIO = "Usuario";
    private static final String COLUMNA_CONTRASENIA = "Contraseña";
    private static GenerateWord generateWord = new GenerateWord();
    private WebDriver driver;

    public MyFlight_Login() {
        this.driver = Hook.getDriver();
    }
    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, LOGIN_WEB);
    }

    @Given("^Ingreso a la url \"([^\"]*)\"$")
    public void ingresoALaUrl(String casoDePrueba) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try {
            int myflight = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(myflight).get(COLUMNA_URL);
            driver.get(url);
            sleep(3500);
            stepPass(driver, "Se inició correctamente la página MyFlight");
            generateWord.sendText("Se inició correctamente la página MyFlight");
            generateWord.addImageToWord(driver);
        }catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @When("^Ingreso el usuario \"([^\"]*)\"$")
    public void ingresoElUsuario(String casoDePrueba) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try {
            int myflight = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.name(TXT_USUARIO)).clear();
            String user = getData().get(myflight).get(COLUMNA_USUARIO);
            sendKeyValue(driver,"name",TXT_USUARIO,user);
            //Assert.assertEquals(user, getText(driver,"name", TXT_USUARIO));
            stepPass(driver, "Se ingresó el usuario : " + user);
            generateWord.sendText("Se ingresó el Usuario ");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @And("^La contraseña \"([^\"]*)\"$")
    public void laContraseña(String casoDePrueba) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try {
            int myflight = Integer.parseInt(casoDePrueba) - 1;
            driver.findElement(By.name(TXT_CONTRASENIA)).clear();
            String pass = getData().get(myflight).get(COLUMNA_CONTRASENIA);
            driver.findElement(By.name(TXT_CONTRASENIA)).sendKeys(pass);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó la contraseña : " + pass);
            generateWord.sendText("Se ingresó la Contraseña ");
            generateWord.addImageToWord(driver);
        }catch (Exception e){
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        }
    }

    @Then("^se da clic en el boton Sing-IN ingresando correctamente$")
    public void seDaClicEnElBotonSingINIngresandoCorrectamente() throws Exception {
        try {
            //driver.findElement(By.name(BTN_SIGNIN)).click();
            click(driver, "name", BTN_SIGNIN);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se dió clic en el botón SignIN");
            generateWord.sendText("Se dió clic en el botón SignIN ");
            generateWord.addImageToWord(driver);
        }catch (Exception e) {
            ExcelReader.writeCellValue(EXCEL_WEB, LOGIN_WEB, 1, 19, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}