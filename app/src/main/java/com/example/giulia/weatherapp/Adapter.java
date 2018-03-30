package com.example.giulia.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Giulia on 19/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;


        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        for (int i=0; i<3;i++){
            holder.tv.setText(Singleton.getInstance().getItemList().get(position).getNome());
        }

        holder.position=position;


    }

    @Override
    public int getItemCount() {
        return Singleton.getInstance().getItemList().size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv;
        public ImageView iv;
        private int position;
        private Context context;
        public ViewHolder(View v) {
            super(v);
            tv = (TextView) v.findViewById(R.id.txv);
            iv = (ImageView) v.findViewById(R.id.img);
            context=v.getContext();
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,DetailActivity.class);
            intent.putExtra("name",Singleton.getInstance().getItemList().get(position).getNome());
            context.startActivity(intent);
        }
    }
}
