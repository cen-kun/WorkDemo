package com.kunc.uploaddemo.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class UnzipUtils {

    /**
     * 将单个的zip文件解压
     * @param file
     * @throws ZipException
     */
    public static void unZip(File file) throws ZipException {
        //将zip解压
        ZipFile zipFile = new ZipFile(file);
        String destDir = "G:\\Download\\extra";
        File newDestDir = new File(destDir);
        zipFile.extractAll(destDir);


    }

}
