package com.money.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    private static String TMP_FOLDER;

    static {
        File targetFolder = new File(System.getProperty("java.io.tmpdir"));
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        TMP_FOLDER = targetFolder.getPath();
    }


    public static String saveToFile(String folder, String content, String fileName) throws IOException {
        folder = TMP_FOLDER + "/" + folder;
        File targetFolder = new File(folder);
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        String fullPath = String.format("%s/%s.txt", folder, fileName);
        try (FileOutputStream fos = new FileOutputStream(fullPath, true)) {
            fos.write(content.getBytes());
        }
        return fullPath;
    }
}
