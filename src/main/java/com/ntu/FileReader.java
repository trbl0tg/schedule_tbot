package com.ntu;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileReader {

//    private static final String = "";

    public FileReader(){
        try(
                BufferedInputStream in = new BufferedInputStream(new URL("http://vstup.ntu.edu.ua/rozklad/dyst/isp-ftit-4.xlsx").openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("schedule_4g_exams.xlsx")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (
                IOException e) {
            // handle exception
        }

    }

}
