package com.gfg;

import java.util.ArrayList;
import java.util.List;

public class GFGCourse {

    private List<String> courses;

    public GFGCourse() {
        courses = new ArrayList<>();
    }

    public void addCourse(String subject){
        boolean admin =false;
        /*

         */
        admin=true;
        if(admin){
            courses.add(subject);
        }
    }


    @Override
    public String toString() {
        return "GFGCourse{" +
                "courses=" + courses +
                '}';
    }
}
