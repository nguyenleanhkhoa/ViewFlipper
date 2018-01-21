package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anhkhoa.testrecyclerviewhorizontal.R;

import java.util.ArrayList;
import java.util.List;

import Model.Lop;
import Model.Student;

/**
 * Created by anh khoa on 11/20/2017.
 */

public class AdapterItemRecyclerView extends RecyclerView.Adapter<AdapterItemRecyclerView.ViewHolder> {
    ArrayList<Student> liststudent;
    Context context;
    ArrayList<Lop>listlop;
    private ClickListener clickListener;
    public AdapterItemRecyclerView() {
    }

    public AdapterItemRecyclerView(ArrayList<Student> liststudent, Context context) {
        this.liststudent = liststudent;
        this.context = context;
    }
    public void setData(ArrayList<Student> data) {
        if (liststudent != data) {
            liststudent = data;
            notifyDataSetChanged();
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View row=layoutInflater.inflate(R.layout.itemhorizontal,null,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtten.setText(liststudent.get(position).getTen());
        holder.txtlop.setText(liststudent.get(position).getLop());
    }

    @Override
    public int getItemCount() {
        return liststudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtten,txtlop;
        public ViewHolder(View itemView)     {
            super(itemView);
            txtten=itemView.findViewById(R.id.txtten1);
            txtlop=itemView.findViewById(R.id.txtlop1);
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
