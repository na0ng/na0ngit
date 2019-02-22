package com.example.intent.item;

import java.io.Serializable;

public class GridItem implements Serializable {

    private Integer img;

    public GridItem(Integer img) {
        this.img = img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() { return img; }
}
