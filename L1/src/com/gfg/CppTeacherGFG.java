package com.gfg;
public interface CppTeacherGFG {
    void teachCpp();
    default void speakEnglish(){
        System.out.println("");
    }
}
