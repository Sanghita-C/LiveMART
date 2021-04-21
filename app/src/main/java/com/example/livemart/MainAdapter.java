package com.example.livemart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends ArrayAdapter<MainItems> implements Filterable {
    Context active;
    List<MainItems> items_list = new ArrayList<>();
    List<MainItems> items_list_filtered=new ArrayList<>();
    int custom_layout_id;
    public MainAdapter(Context context, int resource, List<MainItems> objects)
    {
        super(context, resource, objects);
        active=context;
        items_list = objects;
        items_list_filtered=objects;
        custom_layout_id = resource;
    }

    @Override public int getCount()
    {
        return items_list_filtered.size();
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null) {

            // getting reference to the main layout and
            // initializing
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(custom_layout_id, null);
        }

        // initializing the image, title, price, whether in stock or not

        ImageView img = v.findViewById(R.id.VegImage);
        TextView title = v.findViewById(R.id.VegTitle);

        // get the item using the position param
        MainItems item = items_list_filtered.get(position);

        img.setImageResource(item.getImage_id());
        title.setText(item.getTitle());

        return v;
    }

    //Filtering grid view and returning search results
    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint==null | constraint.length()==0)
                {
                    filterResults.count=items_list.size();
                    filterResults.values=items_list;
                }
                else
                {
                    String query=constraint.toString().toLowerCase();
                    List<MainItems> resultData=new ArrayList<>();
                    for(MainItems item:items_list)
                    {
                        if(item.getTitle().toLowerCase().contains(query))
                        {
                            resultData.add(item);
                        }
                        filterResults.count=resultData.size();
                        filterResults.values=resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                items_list_filtered=(List<MainItems>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
