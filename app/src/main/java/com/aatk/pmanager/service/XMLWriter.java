package com.aatk.pmanager.service;

import com.aatk.pmanager.db.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class XMLWriter {

    public void writeToFile(Users users, File file) throws IOException {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(xmlModule);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String xmlOutput = objectMapper.writeValueAsString(users);
        System.out.println(xmlOutput);

        FileOutputStream stream = new FileOutputStream(file);
        stream.write(xmlOutput.getBytes());
        stream.close();
    }

//    public String findACar(File fileWhereToFind){
//
//    }
}
