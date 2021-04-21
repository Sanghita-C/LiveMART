package com.example.livemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RetailerAddItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_add_item);

        EditText category = findViewById(R.id.additem_category);
        EditText name = findViewById(R.id.additem_name);
        EditText cost = findViewById(R.id.additem_cost);

        String ret_category,ret_name,ret_cost;

        ret_category= category.getText().toString();
        ret_name= name.getText().toString().toLowerCase();
        ret_cost= cost.getText().toString().toLowerCase();

        System.out.println(ret_category);

        Button Btn=findViewById(R.id.retailer_add_button);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                System.out.println(ret_name.isEmpty());
//                if(ret_category.equals(ret_category))
//                {
//                    intent=new Intent(getApplicationContext(),VegDashboard.class);
//                    intent.putExtra("item", ret_name);
//                    intent.putExtra("cost", ret_cost);
//                    startActivity(intent);
//                }
//                else if(ret_category.equals("fruits"))
//                {
//                    intent=new Intent(getApplicationContext(),VegDashboard.class);
//                    intent.putExtra("item", ret_name);
//                    intent.putExtra("cost", ret_cost);
//                    startActivity(intent);
//                }
//                else
//                {
//                    Log.v("tag",ret_category);
//                }
            }
        });

    }
}