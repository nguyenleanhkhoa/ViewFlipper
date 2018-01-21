package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anhkhoa.appchat.R;

import java.util.ArrayList;
import java.util.List;

import model.message;

/**
 * Created by anh khoa on 12/21/2017.
 */

public class FirebaseChatListAdpater extends RecyclerView.Adapter<FirebaseChatListAdpater.ViewHolder>  {
    List<message> listMessage;
    Context context;

    public FirebaseChatListAdpater(List<message> listMessage, Context context) {
        this.listMessage = listMessage;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtMsgContent.setText(listMessage.get(position).getMessageContent());
        holder.txtMsgTime.setText((int) listMessage.get(position).getMessageTime());
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMsgContent,txtMsgTime;
        ImageView imageUser;
        public ViewHolder(View itemView) {
            super(itemView);
            txtMsgContent=itemView.findViewById(R.id.msgContent);
            txtMsgTime=itemView.findViewById(R.id.msgTime);
            imageUser=itemView.findViewById(R.id.imgCircleUser);
        }
    }
}
