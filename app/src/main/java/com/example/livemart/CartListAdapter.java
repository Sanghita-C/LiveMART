package com.example.livemart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private List<Items> callListResponses = new ArrayList<>();
    final List<Items> templist=new ArrayList<>();
    private Activity context;
    int lastPosition=0;

    public CartListAdapter(Activity context, List<Items> callListResponses)
    {
        super();
        this.context = context;
        this.callListResponses=callListResponses;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Items call = callListResponses.get(position);

        holder.itemname.setText(call.getTitle());
        holder.itemprice.setText(" Rs"+call.getPrice());
        holder.tv_quantity.setText(String.valueOf(call.getQuantity()+1));

    //        holder.cart_minus_img.setOnClickListener(new QuantityListener(context, holder.tv_quantity,call,false));
//        holder.cart_plus_img.setOnClickListener(new QuantityListener(context, holder.tv_quantity,call,true));
//        holder.img_deleteitem.setOnClickListener(new DeleteItemListener(context,call,this));
    }

    //Animating single element
//    private void setAnimation(View viewToAnimate, int position)
//    {
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_right_in);
//            viewToAnimate.startAnimation(animation);
//            lastPosition=position;
//        }
//        position++;
//    }

    @Override
    public int getItemCount() {
        //Log.d("Size List:",String.valueOf(callListResponses.size()));
        if(callListResponses!=null){
            return callListResponses.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemprice,itemname,tv_quantity;
        public ImageView cart_minus_img, cart_plus_img,img_deleteitem;


        public ViewHolder(View itemView) {
            super(itemView);
            cart_minus_img=(ImageView) itemView.findViewById(R.id.cart_minus_img);
            cart_plus_img=(ImageView) itemView.findViewById(R.id.cart_plus_img);
            img_deleteitem=(ImageView) itemView.findViewById(R.id.img_deleteitem);
            itemname=(TextView) itemView.findViewById(R.id.itemname);
            itemprice=(TextView) itemView.findViewById(R.id.itemprice);
            tv_quantity=(TextView) itemView.findViewById(R.id.item_quantity);

        }
    }



}
