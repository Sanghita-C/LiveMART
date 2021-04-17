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
import android.widget.GridView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class FruitsDashboard extends AppCompatActivity {

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_dashboard);

        List<Items> itemsList = new ArrayList<>();
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","60",true));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_2","70",false));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_3","60",true));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","100",false));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","60",true));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","290",true));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","60",false));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","450",true));
        itemsList.add(new Items(R.drawable.ic_baseline_search_24, "image_1","60",true));



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
        return super.onOptionsItemSelected(item);
    }

}