package Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhkhoa.*;
import com.example.anhkhoa.testrecyclerviewhorizontal.R;

import java.util.ArrayList;
import java.util.List;

import Model.Lop;
import Model.Student;

/**
 * Created by anh khoa on 11/20/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>  {
    ArrayList<Lop> listlop;
    Context context;
    ArrayList<Student>listStudent;
    AdapterItemRecyclerView adapterItemRecyclerView;
    RecyclerView HorizontalList;
    private AdapterItemRecyclerView.ClickListener clickListener;

    public AdapterRecyclerView(ArrayList<Lop> listlop, Context context, ArrayList<Student> listStudent, AdapterItemRecyclerView adapterItemRecyclerView) {
        this.listlop = listlop;
        this.context = context;
        this.listStudent = listStudent;
        this.adapterItemRecyclerView = adapterItemRecyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View row=layoutInflater.inflate(R.layout.itemlist,null,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtten.setText(listlop.get(position).getLop());
    }

    @Override
    public int getItemCount() {
        return listlop.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtten;
        AdapterItemRecyclerView adapterItemRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.txtten);
            HorizontalList=itemView.findViewById(R.id.listRecyclerViewitem);
            HorizontalList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            adapterItemRecyclerView=new AdapterItemRecyclerView(listStudent,context);
            HorizontalList.setAdapter(adapterItemRecyclerView);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface ClickListener{
        public void itemClick(View view,int position);
    }
    public void setClickListener(AdapterItemRecyclerView.ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
