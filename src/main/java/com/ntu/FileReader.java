package com.ntu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    Map<String, String> files  = new Utils().files;

    public FileReader() {

        for (Map.Entry<String, String> entry : files.entrySet()) {

            try {
                File file = new File(entry.getKey().substring(0, entry.getKey().lastIndexOf("/")));
                if (!file.exists()) {
                    file.mkdirs();
                }
                BufferedInputStream in = new BufferedInputStream(new URL(entry.getValue()).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(entry.getKey());

                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (
                    IOException e) {
                System.out.println("ERROR! while working with file reader: " + e.getMessage());
            }
        }

    }

}
