package com.caij.codehub.utils;

import java.io.File;

/**
 * Created by Caij on 2015/11/3.
 */
public class FileUtil {

    public static long getFileLength(File file) {
        File[] files = file.listFiles();
        long size = 0;
        for (File tempFile : files){
            if (tempFile.isFile()) {
                size += tempFile.length();
            }else if (tempFile.isDirectory()) {
                size += getFileLength(tempFile);
            }
        }
        return size;
    }

    public static void clearFile(File file) {
        File[] files = file.listFiles();
        for (File tempFile : files){
            if (tempFile.isFile()) {
                file.delete();
            }else if (tempFile.isDirectory()) {
                clearFile(tempFile);
            }
        }
    }

    public static String formatFileLength(long length) {
        float kb = length / 1024f;
        if (kb <= 0) {
            return 0 + "K";
        }
        if (kb < 1000) {
            return getResult(kb) + "K";
        }else {
            float mb = kb / 1024f;
            if (mb < 1000) {
                return getResult(mb) + "M";
            }
            else {
                float gb = mb /1024f;
                return getResult(gb) + "G";
            }
        }
    }
    private static String getResult(float space) {
        String spaceString = String.valueOf(space);
        int len = spaceString.length();
        if (spaceString.contains(".")) {
            if (len > spaceString.indexOf(".") + 2) {
                spaceString = spaceString.substring(0, spaceString.indexOf(".") + 2);
            }
        }
        return spaceString;
    }


}
