package com.gfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExceptionHandlingDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("LKO");
        list.add("DLI");
        list.add("BLR");
        for(int i=0; i<list.size(); i++){
            try {
                // opening file and reading data
                print(list.get(i));
            }
            catch (NullPointerException ex){
                System.out.println("Exception occured");
            }
            catch (ArithmeticException ex){
                System.out.println("ArithmeticException occured");
            }
            finally {
                System.out.println("Release resource, closing file etc");
            }
        }


        try {
            String data = readData();
        } catch (IOException e) {
//            e.printStackTrace();
        }

        String name = "ravi";

        try {
            createUserName(name);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }

        Object obj = new Object();




    }

    public static void print  (String city) {
//        int c = 4/0;
        System.out.println(city.length());
    }

    private static String readData()  throws IOException {
        throw new IOException();
    }

    private static void createUserName(String name)  throws UserAlreadyExistException {
        if(name.equals("shashi")){
            throw new UserAlreadyExistException("name not available");
        }
    }

}
