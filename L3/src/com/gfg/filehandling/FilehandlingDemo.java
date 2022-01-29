package com.gfg.filehandling;


import java.io.*;
import java.util.Scanner;

class Person implements Serializable{
    static private final long serialVersionUID=45l;
    private String name;
    private int age;
    private int height;
    public Person(String name, int age) {
        System.out.println("Creating Person Object");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

}

public class FilehandlingDemo {

    public static void main(String[] args) {

        Person p1 = new Person("Rohan",25);
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("testfile.txt"));
            objectOutputStream.writeObject(p1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("testfile.txt"));
            Person p2 = null;
            p2 = (Person) objectInputStream.readObject();
            System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String data ="शशिकांत";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("textData.txt");
            fileOutputStream.write(data.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream("textData.txt");
//            while (fileInputStream.available()>0){
//                System.out.print((char)fileInputStream.read());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("textData.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String readData = scanner.nextLine();
        System.out.println(readData);
    }
}
