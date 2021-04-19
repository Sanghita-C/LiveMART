package com.example.livemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Item_view extends AppCompatActivity {
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        androidx.appcompat.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
//            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Item");
        }

        ImageView image=findViewById(R.id.ItemImage);
        TextView title=findViewById(R.id.ItemTitle);
        TextView price=findViewById(R.id.ItemCost);
        TextView stock=findViewById(R.id.ItemStock);
        Button addItem=findViewById(R.id.add_to_cart);

        Intent intent=getIntent();


            item=(Items) intent.getSerializableExtra("item");
            image.setImageResource(item.getImage_id());
            title.setText(item.getTitle());
            price.setText(String.format("Rs %s", String.valueOf(item.getPrice())));
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

        //For add to cart button
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getInStock())
                {
                    if(CustomAdapter.selecteditems.contains(item)){
                        Toast.makeText(getApplicationContext(),
                                "Item present in cart. Please go to cart.",
                                Toast.LENGTH_LONG)
                                .show();
                    }else{
                        //List does not contains item you can add item here.
                        CustomAdapter.selecteditems.add(item);
                        Toast.makeText(getApplicationContext(),
                                "Item added to cart",
                                Toast.LENGTH_LONG)
                                .show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Item not in stock.Check again after a few days",
                            Toast.LENGTH_LONG)
                            .show();
                }

            }
        });


    }
    //For adding to cart


}