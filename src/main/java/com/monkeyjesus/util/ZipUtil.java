package com.monkeyjesus.util;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MonkeyJesus on 2017/4/18.
 */
public class ZipUtil {

    /**
     * 解压7z压缩包
     * @param zipPath
     * @param targetDir
     * @return
     */
    public static boolean de7z(String zipPath, String targetDir) {
        File zipFile = new File(zipPath);
        if (!zipFile.exists() || !zipFile.getName().endsWith(".7z")){
//            throw new Exception("this file is not zip file");
            return false;
        }
        try {
            SevenZFile archive = new SevenZFile(zipFile);
            SevenZArchiveEntry entry = null;
            while ((entry = archive.getNextEntry()) != null) {
                File a = new File(targetDir+"/"+entry.getName());
                if (entry.isDirectory()) {
                    a.mkdirs();
                } else {
                    if (!a.getParentFile().exists()){
                        a.getParentFile().mkdirs();
                    }
                    FileOutputStream os = null;
                    entry.getWindowsAttributes();
                    byte[] b = new byte[(int)entry.getSize()];
                    try {
                        os=new FileOutputStream(a);
                        archive.read(b);
                        os.write(b);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 解压zip压缩包
     * @param zipPath
     * @param targetPath
     * @return
     */
    public static boolean deZip(String zipPath , String targetPath){
        File zipFile = new File(zipPath);
        if (!zipFile.exists() || !zipFile.getName().endsWith(".zip")){
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
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(zipInput);
        }
        return true;
    }


    public static void main(String[] args){
        String filePath = "D:\\77\\zipTest.zip";
        String targetPath = "D:\\7\\zipTestZ";
        deZip(filePath,targetPath);
    }

}
