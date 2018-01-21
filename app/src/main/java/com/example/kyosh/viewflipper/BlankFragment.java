package com.example.kyosh.viewflipper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;


public class BlankFragment extends android.app.Fragment {
    ViewFlipper viewFlipper;
    ListView listView;
    ArrayList<String>listString;
    ArrayAdapter<String> adapter;
    ArrayList<SetPage>listPage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        addControl(view);
        return view;

    }

    private void addControl(View view) {
        viewFlipper=view.findViewById(R.id.viewFlipper1);
        listView=view.findViewById(R.id.listview1);
        listString=new ArrayList<>();
        listPage=new ArrayList<>();
        addData();
        addEvent(viewFlipper);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        listView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event


    private void addData() {
        for(int i=1;i<23;i++){
            listString.add("room"+i);
        }
    }

    private void addEvent(ViewFlipper viewFlipper) {
        ArrayList<String> temp=new ArrayList<>();
        try{
            for(int i=1;i<=listString.size();i++){
                temp.add("room"+i);
                if(i%5==0){
                    SetPage setpage=new SetPage(getActivity(),temp,new ListView(getActivity()));
                    listPage.add(setpage);
                    temp.clear();
                }
                if(listString.size()==i && !listString.isEmpty()){
                    SetPage setpage=new SetPage(getActivity(),temp,new ListView(getActivity()));
                    listPage.add(setpage);
                    temp.clear();
                }
                for(SetPage page : listPage){
                    viewFlipper.removeView(page.getListView());
                    viewFlipper.addView(page.getListView());
                }

            }
        }catch (NullPointerException e){

        }
    }


}
