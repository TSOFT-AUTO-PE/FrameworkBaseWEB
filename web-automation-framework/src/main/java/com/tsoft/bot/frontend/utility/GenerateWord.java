package com.tsoft.bot.frontend.utility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class GenerateWord {

    public static XWPFDocument document ;
    public static XWPFParagraph paragraph ;
    public static XWPFRun run;
    public static FileOutputStream fileOutputStream;

    public void startUpWord() throws IOException, InvalidFormatException {

        File fileUnique = new File(FileHelper.getProjectFolder() + "/src/main/resources/doc/Evidencia.docx");

        copyExistentWord(fileUnique);

        document        = new XWPFDocument();

        paragraph       = document.createParagraph();

        run             = paragraph.createRun();

        fileOutputStream = new FileOutputStream("C:/Users/admin/AppData/Local/Temp/Evidencia.docx");

        InputStream insertTemplate = new FileInputStream(FileHelper.getProjectFolder() + "/src/main/resources/doc/Plantila.png");

        run.addPicture(insertTemplate, Document.PICTURE_TYPE_PNG, "1", Units.toEMU(440), Units.toEMU(640));

        run.addBreak();


    }

    public void copyExistentWord(File file) {

        InputStream inputStream = null;

        OutputStream outputStream = null;

        try {

            File fileUnique = new File(file.getPath());

            File copyFile = new File("C:/Users/admin/AppData/Local/Temp/Evidencia.docx");

            inputStream = new FileInputStream(fileUnique);

            outputStream = new FileOutputStream(copyFile);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = inputStream.read(buffer)) > 0) { outputStream.write(buffer, 0, length); }

            inputStream.close();

            outputStream.close();

        } catch (IOException r) {

            r.printStackTrace();

        }
    }

    public void addImageToWord(WebDriver driver) {

        try {
            TakesScreenshot screenshot  = ((TakesScreenshot)driver);
            File source                 = screenshot.getScreenshotAs(OutputType.FILE);
            InputStream pic = new FileInputStream(source);
            run.addPicture(pic, Document.PICTURE_TYPE_PNG, "1", Units.toEMU(480), Units.toEMU(280));
            run.addBreak();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void sendText(String texto)  {
        run.setText(texto);
        run.setFontSize(20);
    }

    public void endToWord() throws IOException   {
        try {
            document.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
