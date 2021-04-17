package com.example.livemart;

import java.io.Serializable;

public class Items implements Serializable {
    private int image_id;
    private String text,price;
    private boolean inStock;

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

    public String getPrice()
    {
        return "Rs." +price;
    }

    public boolean  getInStock()
    {
        return inStock;
    }

    //Constructor
    Items(int img, String text, String price, boolean stock)
    {
        image_id = img;
        this.text = text;
        this.price=price;
        this.inStock=stock;
    }
}
