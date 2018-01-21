package com.example.anhkhoa.jsonproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import Adapter.AdapterRecyclerViewLoc;
import Model.Location;


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Location> listLocation;
    AdapterRecyclerViewLoc adapterRecyclerViewLoc;
    Location location;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
        getDataLocation(MainActivity.this);
    }

    public void getDataLocation(final Context context) {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://hiwhereami.000webhostapp.com/getdiadiemdulich.php";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                location = new Location();
                                JSONObject jsonObject = response.getJSONObject(i);
                                String ten = jsonObject.getString("ten");
                                String diachi = jsonObject.getString("diachi");
                                String img = jsonObject.getString("img");
                                location.setTen(ten);
                                location.setDiachi(diachi);
                                location.setImg(img);
                                listLocation.add(0,location);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerView.setAdapter(adapterRecyclerViewLoc);
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "lỗi", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }


    private void addEvent() {

    }

    private void addControl() {
        listLocation = new ArrayList<>();
        adapterRecyclerViewLoc = new AdapterRecyclerViewLoc(MainActivity.this, listLocation);
        recyclerView = findViewById(R.id.recyclerLocation);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, true));
    }


}
