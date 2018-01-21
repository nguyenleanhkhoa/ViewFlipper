package com.example.anhkhoa.testrecyclerviewhorizontal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterItemRecyclerView;
import Adapter.AdapterRecyclerView;
import Model.Lop;
import Model.Student;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerView.ClickListener,AdapterItemRecyclerView.ClickListener{
    RecyclerView recyclerView;
    AdapterRecyclerView adapterRecyclerView;
    AdapterItemRecyclerView adapterItemRecyclerView;
    ArrayList<Student>listStudent;
    ArrayList<Lop>lopList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Addcontrol();
    }

    private void Addcontrol() {
        recyclerView=findViewById(R.id.recyclerDemoHorizoltal);
        listStudent=new ArrayList<>();
        lopList=new ArrayList<>();

        lopList.add(new Lop("14dth04"));
        lopList.add(new Lop("14Dth03"));

        listStudent.add(new Student("anh khoa","14dth04"));
        listStudent.add(new Student("Hong lan","14dth04"));
        listStudent.add(new Student("Hong dang","14dth04"));
        listStudent.add(new Student("Nguyen ","14dth03"));
        listStudent.add(new Student("Danh","14dth03"));

        adapterItemRecyclerView=new AdapterItemRecyclerView(listStudent,getApplicationContext());
        adapterRecyclerView=new AdapterRecyclerView(lopList,getApplicationContext(),listStudent,adapterItemRecyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapterRecyclerView);

    }

    @Override
    public void itemClick(View view, int position) {

    }
}
