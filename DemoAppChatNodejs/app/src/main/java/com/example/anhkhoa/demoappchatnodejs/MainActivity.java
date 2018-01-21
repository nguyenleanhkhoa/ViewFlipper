package com.example.anhkhoa.demoappchatnodejs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketAddress;
import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    private RecyclerView recyclerView;
    EditText edtnhap;
    Button btnthem,btngui;
    ListView listuserl;
    ArrayList<String>user;
    ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
        showdata();
        listuserl.setAdapter(adapter);
    }

    private void showdata() {
        mSocket.on("server-send-data",onRetrieveUserdata);
    }

    private void addEvent() {
        try {
            mSocket= IO.socket("http://10.0.0.32:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtnhap.getText())){
                    mSocket.emit("client-register-user",edtnhap.getText().toString());
                    mSocket.on("server-send-user",onRetrieveUser);
                    mSocket.on("server-send-data",onRetrieveUserdata);
                }
            }
        });
    }
    private Emitter.Listener onRetrieveUserdata=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.clear();
                    JSONObject jsonObject= (JSONObject) args[0];
                    try {
                        JSONArray array=jsonObject.getJSONArray("danhsach");
                        for (int i=0;i<array.length();i++){

                            String ten=array.getString(i);
                            user.add(ten);
                        }

                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };
    private Emitter.Listener onRetrieveUser=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject= (JSONObject) args[0];
                    try {
                        boolean tontai = jsonObject.getBoolean("user");
                       if(tontai==true){
                           Toast.makeText(MainActivity.this, "đã tồn tại user", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(MainActivity.this, "Đăng ký thành công ", Toast.LENGTH_SHORT).show();
                       }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private void addControl() {
        listuserl=findViewById(R.id.listuser);
        user=new ArrayList<>();
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,user);
        recyclerView=findViewById(R.id.recyclerView);
        edtnhap=findViewById(R.id.edtContent);
        btnthem=findViewById(R.id.btnthem);
        btngui=findViewById(R.id.btngui);
    }
}
