package com.example.bulut.mychat.Yoklama;
import java.io.Serializable;

public class Yoklamalar implements Serializable {

    String code = null;
    String name = null;
    boolean selected = false;

    public Yoklamalar(String code, String name, boolean selected) {
        this.code = code;
        this.name = name;
        this.selected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}