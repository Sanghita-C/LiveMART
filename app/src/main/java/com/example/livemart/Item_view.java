package com.example.livemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Item_view extends AppCompatActivity {
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        ImageView image=findViewById(R.id.ItemImage);
        TextView title=findViewById(R.id.ItemTitle);
        TextView price=findViewById(R.id.ItemCost);
        TextView stock=findViewById(R.id.ItemStock);

        Intent intent=getIntent();


            item=(Items) intent.getSerializableExtra("item");
            image.setImageResource(item.getImage_id());
            title.setText(item.getTitle());
            price.setText(item.getPrice());
            boolean inStock=item.getInStock();
            if(inStock)
            {
                stock.setText("IN STOCK");
                stock.setTextColor(Color.parseColor("#00FF00"));
            }
            else
            {
                stock.setText("NOT IN STOCK");
                stock.setTextColor(Color.parseColor("#FF0000"));
            }


    }

}