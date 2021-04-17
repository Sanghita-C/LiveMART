package com.example.livemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.SearchManager;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class MainDashboard extends AppCompatActivity {

    MainAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        List<MainItems> itemsList = new ArrayList<>();
        itemsList.add(new MainItems(R.drawable.ic_baseline_search_24, "vegetables"));
        itemsList.add(new MainItems(R.drawable.ic_baseline_search_24, "fruits"));
        itemsList.add(new MainItems(R.drawable.ic_baseline_search_24, "apple"));
        itemsList.add(new MainItems(R.drawable.ic_baseline_search_24, "apple"));
        itemsList.add(new MainItems(R.drawable.ic_baseline_search_24, "apple"));
        itemsList.add(new MainItems(R.drawable.ic_baseline_search_24, "apple"));



        GridView gridView = findViewById(R.id.maingrid_view);
        customAdapter = new MainAdapter(this, R.layout.custom_maindashboard, itemsList);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                MainItems item=customAdapter.items_list_filtered.get(position);
                String query=item.getTitle().toLowerCase();
                Intent intent;
                if(query=="vegetables")
                {
                    intent=new Intent(getApplicationContext(),VegDashboard.class);
                    startActivity(intent);
                }
                else if(query=="fruits")
                {
                    intent=new Intent(getApplicationContext(),FruitsDashboard.class);
                    startActivity(intent);
                }

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