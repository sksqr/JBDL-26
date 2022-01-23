package com.shashi;

public class StaticDemo {
    public static void main(String[] args) {

        CircleShape circleShape = new CircleShape(2);
        // first the class will be loaded
        // constructor

        SystemClass obj = new SystemClass("GFGSystem",SystemStatus.RUNNING);
        System.out.println(obj);
        obj.setStatus(SystemStatus.STOPED);
        System.out.println(obj);

    }

}

class SystemClass{
    private String name;
    SystemStatus status;

    public SystemClass(String name, SystemStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemStatus getStatus() {
        return status;
    }

    public void setStatus(SystemStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SystemClass{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

enum SystemStatus{
    RUNNING,STOPED;
}



enum OrderStatus{

    SHIPPED("SHIPPED"),DILIVERED("DILIVERED"),REFUNDED("REFUNDED"), RETURN("RETURN");

    String value;

     OrderStatus(String value) {
        this.value = value;
    }
}