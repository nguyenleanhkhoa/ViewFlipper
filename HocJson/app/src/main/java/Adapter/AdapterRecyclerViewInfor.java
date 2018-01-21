package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anhkhoa.jsonproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

import Model.DetailLocation;

/**
 * Created by anh khoa on 11/10/2017.
 */

public class AdapterRecyclerViewInfor extends RecyclerView.Adapter<AdapterRecyclerViewInfor.ViewHolder> {
    Context context;
    ArrayList<DetailLocation>ArrayContent;

    public AdapterRecyclerViewInfor(Context context, ArrayList<DetailLocation> arrayContent) {
        this.context = context;
        ArrayContent = arrayContent;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_information,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textcontent.setText(ArrayContent.get(position).getInforDetail());
        Picasso.with(context).load(ArrayContent.get(position).getImg()).into(holder.imgInfor);
    }

    @Override
    public int getItemCount() {
        return ArrayContent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textcontent;
        ImageView imgInfor;
        public ViewHolder(View itemView) {
            super(itemView);
            imgInfor=itemView.findViewById(R.id.imginfor);
            textcontent=itemView.findViewById(R.id.txtContent);
        }
    }
}
