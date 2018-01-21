package Adapter;

import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhkhoa.jsonproject.MainActivity;
import com.example.anhkhoa.jsonproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.Location;

/**
 * Created by anh khoa on 11/7/2017.
 */

public class AdapterRecyclerViewLoc extends RecyclerView.Adapter<AdapterRecyclerViewLoc.ViewHolder> {
    Context context;
    ArrayList<Location>listLocation;

    private ClickListener clickListener;
    public AdapterRecyclerViewLoc(Context context, ArrayList<Location> listLocation) {
        this.context = context;
        this.listLocation = listLocation;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View row=layoutInflater.inflate(R.layout.itemloction,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txttendiadiem.setText(listLocation.get(position).getTen());
        holder.txtdiadiem.setText(listLocation.get(position).getDiachi());
        Picasso.with(context).load(listLocation.get(position).getImg()).into(holder.imageViewLoc);
    }

    @Override
    public int getItemCount() {
        return listLocation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewLoc;
        TextView txttendiadiem,txtdiadiem;
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewLoc=itemView.findViewById(R.id.imageLoction);
            txttendiadiem=itemView.findViewById(R.id.txttenlocation);
            txtdiadiem=itemView.findViewById(R.id.txtdiadiemloction);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener!=null){
                clickListener.itemClick(view,getPosition());
            }
        }
    }
    public interface ClickListener{
        public void itemClick(View view,int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
