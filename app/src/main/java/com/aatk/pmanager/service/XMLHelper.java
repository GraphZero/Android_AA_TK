package com.aatk.pmanager.service;

import android.os.Environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLHelper {
    public static List<String> getAllUsers(File filesDir){
        List<String> allUsers = new ArrayList<String>();
        String internalStorageCarFile = filesDir.getPath() + "/users_cars.xml";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        Document doc = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(new File(internalStorageCarFile));
            doc.getDocumentElement().normalize();

        } catch (ParserConfigurationException|SAXException|IOException e) {
            e.printStackTrace();
        }
        NodeList nodeList = doc.getElementsByTagName("userList");
        for (int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeList.item(i).getAttributes());
            Node node = nodeList.item(i);
            Element element = (Element) node;
            allUsers.add(element.getElementsByTagName("userName").item(0).getTextContent());
        }
        System.out.println(allUsers);
        return allUsers;
    }

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
