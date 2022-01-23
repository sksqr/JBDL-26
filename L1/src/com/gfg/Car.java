package com.gfg;

import com.EUNormsForCar;

public abstract class Car implements GovtNormsForCar, EUNormsForCar {

    private String rcNumber;
    private Engine engine;

    public Car(String rcNumber, Engine engine) {
        this.rcNumber = rcNumber;
        this.engine = engine;
    }

    public void startCar(){
        engine.run();
    }

}
