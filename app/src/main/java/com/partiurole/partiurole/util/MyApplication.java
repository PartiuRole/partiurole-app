package com.partiurole.partiurole.util;

import android.os.Build;
import android.os.Environment;

import androidx.multidex.MultiDexApplication;

import com.partiurole.partiurole.DataBaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class MyApplication extends MultiDexApplication {

    private static MyApplication instance;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public static File getImagesDirectory() {
        File directory = getInstance().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        directory.mkdirs();
        return directory;
    }

    public static File getCacheDirectory(String subDirectory) {
        File directory = getInstance().getApplicationContext().getCacheDir();

        if (subDirectory != null && !subDirectory.isEmpty())
            directory = new File(directory, subDirectory);

        directory.mkdirs();
        return directory;
    }

    public static File getFilesDirectory(String subDirectory) {
        File directory = getInstance().getApplicationContext().getFilesDir();

        if (subDirectory != null && !subDirectory.isEmpty())
            directory = new File(directory, subDirectory);

        directory.mkdirs();
        return directory;
    }

    public static File getBackupDB() {
        File backupDB = null;
        try {
            File sd = getFilesDirectory("Backup");
            if (sd.canWrite()) {
                File currentDB = getInstance().getDatabasePath(DataBaseHelper.NOME_BANCO_DADOS);
                backupDB = new File(sd, "PartiuRole.bak");
                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return backupDB;
    }

    public static int getLollipop() {
        return Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static int getKitKat(){
        return Build.VERSION_CODES.KITKAT;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }
}