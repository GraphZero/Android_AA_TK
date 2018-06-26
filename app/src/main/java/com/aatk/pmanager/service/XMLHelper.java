package com.aatk.pmanager.service;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XMLHelper {
    public static String findCar(String user){
        File sdCardDirectory = Environment.getExternalStorageDirectory();
        File file = new File(sdCardDirectory + "/cars.txt");
        try{
            if (file.exists()) {
                String carLine = findCarForUser(file, user);
                if (carLine != null){
                    return carLine;
                }
            }
            else {
                file.createNewFile();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "undefined";
    }

    public static String findCarForUser(File file, String user){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = "";
            while((readLine = bufferedReader.readLine()) != null){
                String userName = user;
                if (readLine.contains(userName)){
                    return readLine;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
