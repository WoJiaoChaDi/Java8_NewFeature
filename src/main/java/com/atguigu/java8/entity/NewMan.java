package com.atguigu.java8.entity;

import java.util.Objects;
import java.util.Optional;

public class NewMan {
    //可能不存在的值，包装在Optional里面
    private Optional<Godness> godness = Optional.empty();

    public NewMan() {
        System.out.println("NewMan newMan()...");
    }

    public NewMan(Optional<Godness> godness) {
        System.out.println("NewMan newMan(Optional<Godness> godness)...");
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        System.out.println("NewMan getGodness()...");
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        System.out.println("NewMan setGodness(Optional<Godness> godness)...");
        this.godness = godness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewMan newMan = (NewMan) o;
        return Objects.equals(godness, newMan.godness);
    }

    @Override
    public int hashCode() {

        return Objects.hash(godness);
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
