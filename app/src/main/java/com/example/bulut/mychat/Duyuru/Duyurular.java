package com.example.bulut.mychat.Duyuru;

import java.io.Serializable;

public class Duyurular implements Serializable {
    private String baslik;
    private String icerik;

    public Duyurular() {
    }

    public Duyurular(String baslik, String icerik) {
        this.baslik = baslik;
        this.icerik = icerik;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
}
