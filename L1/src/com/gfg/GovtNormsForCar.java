package com.gfg;

public interface GovtNormsForCar extends RTOContract, EnvironmentalContact {

     String getRC();
    String getInsuranceDoc();
     default void getGPSLocation(){

    }

}
