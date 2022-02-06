package com.gfg;

public class TimeUtil {

//    static private TimeUtil instance = new TimeUtil();
    static private TimeUtil instance;

    public long getCurrentTimeStamp(){
        return System.currentTimeMillis();
    }

    private TimeUtil() {
    }

    public  static synchronized TimeUtil getInstance(){
        if(instance == null){
            instance = new TimeUtil();
        }
        return instance;
    }

// if creating at class loading.
//    public  static TimeUtil getInstance(){
//        return instance;
//    }




}
