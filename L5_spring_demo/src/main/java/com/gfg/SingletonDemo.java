package com.gfg;

public class SingletonDemo {




    public static void main(String[] args) {
        TimeUtil timeUtil = TimeUtil.getInstance();
        System.out.println(timeUtil.getCurrentTimeStamp());
    }
}
