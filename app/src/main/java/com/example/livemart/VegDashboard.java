package com.example.livemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VegDashboard extends AppCompatActivity {

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_dashboard);

        List<Items> itemsList = new ArrayList<>();
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "Karela", 60, true,1));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "Brinjal", 60, false,1));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "apple", 60, true,1));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "apple", 60, true,1));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "apple", 60, true,1));

//        Intent i=getIntent();
//        JSONObject jsonObject = new JSONObject(i.getStringExtra("key"));
//        Toast.makeText(Another_Activity.this, ""+jsonObject.get("Your JSON VALUE"), Toast.LENGTH_SHORT).show();

        GridView gridView = findViewById(R.id.grid_view);
        customAdapter = new CustomAdapter(this, R.layout.custom_item, itemsList);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),Item_view.class);
                intent.putExtra("item",customAdapter.items_list.get(position));
                startActivity(intent);
            }
        });

        //Adding new item intent -retailer side
        Intent intent=getIntent();
        if(intent.getExtras()!=null && intent.getExtras().size()==2)
        {
            String name=intent.getStringExtra("name");
            int cost=intent.getIntExtra("cost",0);
            itemsList.add(new Items(R.drawable.ic_baseline_search_24, name,cost, true,1));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_dashboard, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.search)
        {
            return true;
        }
        else if(id==R.id.cart_icon)
        {
            Intent i=new Intent(VegDashboard.this,CartActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}