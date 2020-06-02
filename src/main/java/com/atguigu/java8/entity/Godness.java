package com.atguigu.java8.entity;

import java.util.Objects;

public class Godness {
    private String name;

    public Godness() {
        System.out.println("Godness new Godness()...");
    }

    public Godness(String name) {
        System.out.println("Godness new Godness(String name)...");
        this.name = name;
    }

    public String getName() {
        System.out.println("Godness getName()...");
        return name;
    }

    public void setName(String name) {
        System.out.println("Godness setName(String name)...");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Godness godness = (Godness) o;
        return Objects.equals(name, godness.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Godness{" +
                "name='" + name + '\'' +
                '}';
    }
}
