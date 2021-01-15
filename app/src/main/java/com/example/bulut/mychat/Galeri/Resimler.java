package com.example.bulut.mychat.Galeri;

import java.io.Serializable;

public class Resimler implements Serializable {

   private String photo_url;
   private String caption;

    public Resimler() {
    }

    public Resimler(String photo_url, String caption) {
        this.photo_url = photo_url;
        this.caption = caption;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
