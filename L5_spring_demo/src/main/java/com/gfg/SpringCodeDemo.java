package com.gfg;



public class SpringCodeDemo {

    public static void main(String[] args) {

//        Car car1 = new Car();
//        car1.run();


        Engine engine = new Engine("New-Revetron",1500);
        //dependency injection for loose coupling
        Car car2 = new Car("Nexon", engine);
        car2.run();

        Car car3 = new Car("Hexon", new Engine("MegaPower",2000));
        car3.run();



    }
}
