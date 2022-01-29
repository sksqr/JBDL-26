package com.gfg.colections;

import java.util.Objects;

public class Employee {
    String name;
    int exp;

    public Employee(String name, int exp) {
        this.name = name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", exp=" + exp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
