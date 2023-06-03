package com.example.demo;

import com.example.demo.Constants.Constants;

import java.io.*;

public class FileInput {
    public FileInput() {
    }

    public void writeFile(String str){
        try {
            FileWriter writer = new FileWriter(Constants.FILE);
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String readFile(){
        char[] buffer = new char[4];
        String result = null;
        try {
            FileReader reader = new FileReader(Constants.FILE);
            reader.read(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
