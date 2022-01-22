package com.gfg;

import com.EUNomsForCar;

public abstract class Car implements GovtNomsForCar, EUNomsForCar {

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
