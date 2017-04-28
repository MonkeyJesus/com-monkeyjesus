package com.monkeyjesus.entry;

/**
 * Created by MacheNike on 2017/4/25.
 */
public class Picture extends Object {
    private String pictureName;

    private Integer width;

    private Integer height;

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureName='" + pictureName + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
