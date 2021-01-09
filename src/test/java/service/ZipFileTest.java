package service;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ZipFileTest {
    public static void main(String[] args) {
        //createZipFile();
        createSplitZipFile();
    }

    /**
     * 加密打包
     */
    public static void createZipFile() {
        try {
            long starttime = System.currentTimeMillis();
            ArrayList filesToAdd = new ArrayList();
            filesToAdd.add(new File("d:\\ziptest\\1.jpg"));
            filesToAdd.add(new File("d:\\ziptest\\a.mkv"));
            filesToAdd.add(new File("d:\\ziptest\\b.mkv"));
            filesToAdd.add(new File("d:\\ziptest\\c.mp4"));

            ZipParameters parameters = new ZipParameters();
            parameters.setEncryptFiles(true);//是否加密
            parameters.setEncryptionMethod(EncryptionMethod.AES);//加密算法
            parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256); //AES加密强度

            String passwd = "itcljTest@2020";

            // Initiate ZipFile object with the path/name of the zip file.
            ZipFile zipFile = new ZipFile("d:\\ziptest\\out\\ItcljZipFile.zip", passwd.toCharArray());
            zipFile.addFiles(filesToAdd, parameters);
            System.out.println("use time :" + (System.currentTimeMillis() - starttime));
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    /**
     * 分卷加密压缩打包
     */
    public static void createSplitZipFile() {
        try {
            long starttime = System.currentTimeMillis();
            ArrayList filesToAdd = new ArrayList();
            filesToAdd.add(new File("d:\\ziptest\\1.jpg"));
            filesToAdd.add(new File("d:\\ziptest\\a.mkv"));
            filesToAdd.add(new File("d:\\ziptest\\b.mkv"));
            filesToAdd.add(new File("d:\\ziptest\\c.mp4"));

            ZipParameters parameters = new ZipParameters();
            parameters.setEncryptFiles(true);//是否加密
            parameters.setEncryptionMethod(EncryptionMethod.AES);//加密算法
            parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256); //AES加密强度

            String passwd = "itcljTest@2020";

            // Initiate ZipFile object with the path/name of the zip file.
            ZipFile zipFile = new ZipFile("d:\\ziptest\\out\\ItcljZipFile.zip", passwd.toCharArray());
            //zipFile.addFiles(filesToAdd, parameters);

            zipFile.createSplitZipFile(filesToAdd, parameters, true, 1024 * 1024 * 500);
            List<File> fileList = zipFile.getSplitZipFiles();
            for(File file:fileList){
                System.out.println("file name:"+file.getName()+" file size:"+file.length());
            }
            System.out.println("create split file use time :" + (System.currentTimeMillis() - starttime));
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
