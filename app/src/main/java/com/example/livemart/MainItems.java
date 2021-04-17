package com.example.livemart;

import java.io.Serializable;

public class MainItems implements Serializable {
    private int image_id;
    private String text;

    public int getImage_id()
    {
        return image_id;
    }

    public void setImage_id(int image_id)
    {
        this.image_id = image_id;
    }

    public String getTitle()
    {
        return text;
    }
    public void setTitle(String text)
    {
        this.text = text;
    }

    //Constructor
    MainItems(int img, String text)
    {
        image_id = img;
        this.text = text;
    }
}
