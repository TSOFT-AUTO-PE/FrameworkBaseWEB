package com.tsoft.bot.frontend.listener;

import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.FileHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener {

    private static boolean SE_BORRARON_FILES = false;

    public void onTestStart(String nombre) {
        try {
            ExtentReportUtil.INSTANCE.createTest(nombre);
        } catch (Exception e) {
            System.out.println("[ERROR CRL-4213] Error en onTestStart: " + e.getMessage());
        }
    }

    public void onStart() {
        if (!SE_BORRARON_FILES) {
            FileHelper.borrarElementosFolder("/img");
            SE_BORRARON_FILES = true;
        }
    }
    public void onFinish() {
        ExtentReportUtil.INSTANCE.flushReport();
    }
}
