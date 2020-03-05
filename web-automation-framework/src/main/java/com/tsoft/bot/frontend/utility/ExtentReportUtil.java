package com.tsoft.bot.frontend.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExtentReportUtil {

    public static final ExtentReportUtil INSTANCE = new ExtentReportUtil();
    private boolean automatico;
    private boolean multithread;
    private final ExtentReports extent;
    private Map<String, ExtentTest> threadTestMap;
    private String currentReportName;

    private ExtentReportUtil() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("frontend-reporte.html");
        htmlReporter.loadXMLConfig("src/main/resources/config/extent-config.xml");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("User", "Abraham Hernandez");
        // por default
        automatico = false;
        multithread = false;
        currentReportName = StringUtils.EMPTY;
    }

    public synchronized void createTest(String nombrePorCaso) throws IOException {
        String threadTestID = multithread ? getCurrentThreadID() : nombrePorCaso;
        currentReportName = nombrePorCaso;
        getThreadTestMap().put(threadTestID, extent.createTest(nombrePorCaso, StringUtils.EMPTY));
        String carpetaCreada = StringHelper.createFileNameFixed(threadTestID, 10);
        String carpeta = FileHelper.getProjectFolder() + "/img/" + carpetaCreada;
        FileUtils.forceMkdir(new File(carpeta));
    }

    public synchronized void stepPass(WebDriver driver, String descripcion) throws Exception {
        if (automatico) return;
        String nombreFichero = "sec" + generarSecuencia() + "-" + StringHelper.createFileNameFixed(descripcion, 10) + ".png";
        String carpetaCreada = getCarpetaGenerada(null);
        String ficheroGeneradoPath = takeScreenShot(driver,carpetaCreada, nombreFichero);
        getCurrentTest(null).pass(descripcion, MediaEntityBuilder.createScreenCaptureFromPath(ficheroGeneradoPath).build());
    }
    public synchronized void stepFail(WebDriver driver, String descripcion) throws Exception {
        if (automatico) return;
        String nombreFichero = "sec" + generarSecuencia() + "-" + StringHelper.createFileNameFixed(descripcion, 10) + ".png";
        String carpetaCreada = getCarpetaGenerada(null);
        String ficheroGeneradoPath = takeScreenShot(driver,carpetaCreada, nombreFichero);
        getCurrentTest(null).fail(descripcion, MediaEntityBuilder.createScreenCaptureFromPath(ficheroGeneradoPath).build());
    }

    private synchronized String getCurrentThreadID() {
        return Thread.currentThread().getName();
    }

    private synchronized Map<String, ExtentTest> getThreadTestMap() {
        if (threadTestMap == null) {
            threadTestMap = new LinkedHashMap<>();
        }
        return threadTestMap;
    }

    private synchronized String getScenarioName(Scenario scenario) {
       return scenario.getName();
    }

    private synchronized ExtentTest getCurrentTest(Scenario scenario) {
        if (multithread) {
            return getThreadTestMap().get(getCurrentThreadID());
        }

        if (scenario != null) {
            return getThreadTestMap().get(getScenarioName(scenario));
        }

        return getThreadTestMap().get(currentReportName);
    }

    private synchronized String getCarpetaGenerada(Scenario scenario) {
        if (multithread) {
            return StringHelper.createFileNameFixed(getCurrentThreadID(), 10);
        }

        if (scenario != null) {
            return StringHelper.createFileNameFixed(getScenarioName(scenario), 10);
        }

        return StringHelper.createFileNameFixed(currentReportName, 10);
    }

    private synchronized String takeScreenShot(WebDriver driver, String nombreCarpeta, String nombreFichero) throws IOException {

        String carpeta = FileHelper.getProjectFolder() + "/img/" + StringUtils.trimToEmpty(nombreCarpeta);

        FileUtils.forceMkdir(new File(carpeta));

        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        String rutaArchivoGenerado = carpeta + "/" + nombreFichero;

        FileUtils.copyFile(source, new File(rutaArchivoGenerado));

        return "./img/" + StringUtils.trimToEmpty(nombreCarpeta) + "/" + nombreFichero;
    }

    private synchronized String generarSecuencia() {
        return new SimpleDateFormat("MMddHHmmssSSSS").format(new Date());
    }

    public synchronized void flushReport() {
        extent.flush();
    }

    public synchronized void stepWarning(WebDriver driver, String descripcion) throws Exception {
        if (automatico) return;
        String nombreFichero = "sec" + generarSecuencia() + "-" + StringHelper.createFileNameFixed(descripcion, 10) + ".png";
        String carpetaCreada = getCarpetaGenerada(null);
        String ficheroGeneradoPath = takeScreenShot(driver,carpetaCreada, nombreFichero);
        getCurrentTest(null).warning(descripcion, MediaEntityBuilder.createScreenCaptureFromPath(ficheroGeneradoPath).build());
    }

    public synchronized void stepError(WebDriver driver, String descripcion) throws Exception {
        if (automatico) return;
        String nombreFichero = "sec" + generarSecuencia() + "-" + StringHelper.createFileNameFixed(descripcion, 10) + ".png";
        String carpetaCreada = getCarpetaGenerada(null);
        String ficheroGeneradoPath = takeScreenShot(driver,carpetaCreada, nombreFichero);
        getCurrentTest(null).error(descripcion, MediaEntityBuilder.createScreenCaptureFromPath(ficheroGeneradoPath).build());
    }

    public synchronized void stepInfo(WebDriver driver, String descripcion) throws Exception {
        if (automatico) return;
        String nombreFichero = "sec" + generarSecuencia() + "-" + StringHelper.createFileNameFixed(descripcion, 10) + ".png";
        String carpetaCreada = getCarpetaGenerada(null);
        String ficheroGeneradoPath = takeScreenShot(driver,carpetaCreada, nombreFichero);
        getCurrentTest(null).info(descripcion, MediaEntityBuilder.createScreenCaptureFromPath(ficheroGeneradoPath).build());
    }
}
