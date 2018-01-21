package com.example.anhkhoa.jsonproject;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AdapterRecyclerViewLoc;
import Model.Location;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_discover extends Fragment implements AdapterRecyclerViewLoc.ClickListener {
    RecyclerView recyclerView;
    ArrayList<Location> listLocation;
    AdapterRecyclerViewLoc adapterRecyclerViewLoc;
    Location location;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataLocation(getContext());
    }

    public void getDataLocation(final Context context) {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                                String id =jsonObject.getString("id");
                                location.setId(id);
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
                        Toast.makeText(context, "lỗi", Toast.LENGTH_SHORT).show();

                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        AlertDialog.Builder thongbao=new AlertDialog.Builder(getContext())
                                .setMessage("Lỗi kết nối mạng")
                                .setTitle("Thông báo")
                                .setPositiveButton("Kết nối lại", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        getDataLocation(context);
                                    }
                                });

                        thongbao.create().show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_discover, container, false);
        listLocation = new ArrayList<>();
        adapterRecyclerViewLoc = new AdapterRecyclerViewLoc(getContext(), listLocation);
        adapterRecyclerViewLoc.setClickListener(this);
        recyclerView = view.findViewById (R.id.recyclerLocation);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        return view ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void itemClick(View view, int position) {
        String loc=listLocation.get(position).getId();
        Intent intent=new Intent(getContext(),Detail_Layout.class);
        intent.putExtra("idlocation",loc);
        intent.putExtra("tenLocation",listLocation.get(position).getTen());
        intent.putExtra("imgLocation",listLocation.get(position).getImg());
        startActivity(intent);


    }



}
