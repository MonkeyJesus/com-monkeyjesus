package com.monkeyjesus.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MonkeyJesus on 2017/4/18.
 */
public class ZipUtil {
    public static boolean deZip(File zipFile , String targetPath){
        if (!zipFile.getName().endsWith(".zip")){
//            throw new Exception("this file is not zip file");
            return false;
        }

        ZipArchiveInputStream zipInput=null;
        try {
            zipInput = new ZipArchiveInputStream(new FileInputStream(zipFile),"UTF-8");
            ZipArchiveEntry entry = null;
            while ((entry = zipInput.getNextZipEntry()) != null) {
                File file = new File(targetPath, entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    if (!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();
                    }
                    FileOutputStream os = null;
                    try {
                        os=new FileOutputStream(file);
                        IOUtils.copy(zipInput,os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (IOException e) {
        }finally {
            IOUtils.closeQuietly(zipInput);
        }
        return true;
    }
}
