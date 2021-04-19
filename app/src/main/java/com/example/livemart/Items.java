package com.example.livemart;

import java.io.Serializable;

public class Items implements Serializable {
    private int image_id,Quantity,price;
    private String text;
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

    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }

    public boolean  getInStock()
    {
        return inStock;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }



    //Constructor
    Items(int img, String text, int price, boolean stock,int quantity)
    {
        image_id = img;
        this.text = text;
        this.price=price;
        this.inStock=stock;
        this.Quantity=quantity;
    }
}
