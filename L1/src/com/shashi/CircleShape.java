package com.shashi;

import com.shashi.AreaCalculator;

public class CircleShape implements AreaCalculator {
    int radius;

    public CircleShape(int radius) {
        this.radius = radius;
        System.out.println("Creating Object");
    }

    static {
        System.out.println("Executing static block");
    }

    @Override
    public double getArea() {
        return pi*radius*radius;
    }
}
