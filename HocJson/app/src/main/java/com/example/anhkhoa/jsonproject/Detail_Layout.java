package com.example.anhkhoa.jsonproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AdapterRecyclerViewInfor;
import Model.DetailLocation;
import Model.Location;

public class Detail_Layout extends AppCompatActivity {
    TabHost tabHost;
    RecyclerView ViewInfor,ViewCmmt;

    AdapterRecyclerViewInfor adapterRecyclerViewInfor;
    ArrayList<DetailLocation>listDetail;
    ImageView imgTile;
    TextView txtTile;

    ProgressDialog progressDialog;
    DetailLocation detailLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackground(new ColorDrawable(Color.TRANSPARENT));


        addcontrol();
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("imgLocation")).into(imgTile);
        txtTile.setText(getIntent().getStringExtra("tenLocation"));
        setTabColor(tabHost);
        getDataLocationDetail(getIntent().getStringExtra("idlocation"));

    }

    public void getDataLocationDetail(String lastid) {
        progressDialog=new ProgressDialog(Detail_Layout.this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://hiwhereami.000webhostapp.com/getchitietdulich.php/?iddulich=";
        String newurl=url.concat(lastid);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, newurl, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    detailLocation = new DetailLocation();
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    String madiadiem = jsonObject.getString("madiadiemdulich");
                                    String thongtin = jsonObject.getString("thongtin");
                                    String img = jsonObject.getString("img");
                                    String id =jsonObject.getString("id");
                                    detailLocation.setId(id);
                                    detailLocation.setInforDetail(thongtin);
                                    detailLocation.setIdDetail(madiadiem);
                                    detailLocation.setImg(img);
                                    listDetail.add(detailLocation);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ViewInfor.setAdapter(adapterRecyclerViewInfor);
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            AlertDialog.Builder thongbao=new AlertDialog.Builder(getApplicationContext())
                                    .setMessage("Lỗi kết nối mạng")
                                    .setTitle("Thông báo")
                                    .setPositiveButton("Kết nối lại", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            getDataLocationDetail(getIntent().getStringExtra("idlocation"));
                                        }
                                    });

                            thongbao.create().show();
                        }
                    });
            requestQueue.add(jsonObjectRequest);


    }

    public void addcontrol() {
        imgTile=findViewById(R.id.imageTitleDetail);
        txtTile=findViewById(R.id.txtTitleDetail);
        ViewInfor=findViewById(R.id.RecyclerViewInfor);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Detail_Layout.this,LinearLayoutManager.VERTICAL,false);
        ViewInfor.setLayoutManager(layoutManager);
        listDetail=new ArrayList<>();
        adapterRecyclerViewInfor=new AdapterRecyclerViewInfor(Detail_Layout.this,listDetail);

        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tab = tabHost.newTabSpec("t1");
        tab.setContent(R.id.tab1);
        tab.setIndicator("Thông tin ");
        tabHost.addTab(tab);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Bình Luận");
        tabHost.addTab(tab2);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(tabHost);
            }
        });

    }
    public void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.white); // unselected
        }
        tabhost.getTabWidget().setCurrentTab(0);
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.color.green); // selected
        // //have
        // to
        // change
    }

}
