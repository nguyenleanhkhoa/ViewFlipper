package com.example.kyosh.viewflipper;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kyosh on 01/22/18.
 */

class SetPage {
    Context applicationContext;
    ArrayList<String> listString;
    ListView listView;
    ArrayAdapter<String>arrayAdapter;
    public SetPage(Activity applicationContext, ArrayList<String> listString, ListView listView) {
        this.applicationContext=applicationContext;
        this.listString= (ArrayList<String>) listString.clone();
        this.listView=listView;
        setUpAdpater(listView);
    }
    public void setUpAdpater(ListView listView){
        arrayAdapter=new ArrayAdapter<String>(applicationContext,android.R.layout.simple_list_item_1,listString);
        listView.setAdapter(arrayAdapter);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
