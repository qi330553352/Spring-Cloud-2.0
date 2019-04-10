package com.qixin.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 创 建 时 间: 2019/4/11
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
public class ZipUtils {

    /**
     * zip解压
     * @param srcFile zip源文件
     * @param destDirPath 解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024*2];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        }finally {
            if(zipFile != null){
                try{
                    zipFile.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args){
        File srcFile = new File("D:\\example\\beam-manage-system.zip");
        String destDirPath = "D:\\360Downloads";
        ZipUtils.unZip(srcFile,destDirPath);
    }
}
