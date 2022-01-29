package com.gfg.filehandling;

import java.io.*;

public class FileClassDemo {

    public static void main(String[] args) {
        String data1 ="Welcome to GFG JBLD-26";
//        File dir = new File("/home/shashi/gfg/JBDL-26/L3");
//        for(String name : dir.list()){
//            System.out.println(name);
//        }

        File file = new File("newtextData.txt");
        System.out.println(file.exists());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.exists());
        if(file.canWrite()){
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append(data1);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(file.canWrite()){
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                while (fileReader.ready()){
                    System.out.print((char)fileReader.read());

                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
