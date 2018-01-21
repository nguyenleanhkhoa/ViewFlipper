package com.example.anhkhoa.appchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Adapter.FirebaseChatListAdpater;
import model.message;

public class Main2Activity extends AppCompatActivity {
    private static int SIGN_IN_REQUEST_CODE=1;
    RecyclerView recyclerView;
    LinearLayout activity;
    EditText edtMsgContent;
    Calendar calendar=Calendar.getInstance();
    int day=calendar.get(Calendar.DAY_OF_WEEK);
    int month=calendar.get(Calendar.MONTH);
    int year=calendar.get(Calendar.YEAR);
    String timeOfMessage=day+"/"+month+"/"+year;

    FirebaseListAdapter<message>adapter;


    DateFormat df = new SimpleDateFormat(" d MMM yyyy, HH:mm");
    String date = df.format(Calendar.getInstance().getTime());
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK){
                    Snackbar.make(activity,"Successfully signed in.Welcome!",Snackbar.LENGTH_SHORT).show();
                    displayChatMessage();
            }else
            {
                Snackbar.make(activity,"We couldn't sign in you in .Please try again later!",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MsgContent=edtMsgContent.getText().toString();
                FirebaseDatabase.getInstance().getReference("Chat Message").push().setValue(new message(MsgContent,FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), date));
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }else{
            Snackbar.make(activity,"welcome : "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
        }
        //load content
        displayChatMessage();
    }
    public void init(){
        edtMsgContent=findViewById(R.id.edtMsgContent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.btnsignout){
                AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(activity,"you have been signed out!",Snackbar.LENGTH_SHORT).show();
                            finish();
                    }
                });
        }
        return true;
    }

    private void displayChatMessage() {

    }
    public void addControl(){
        recyclerView=findViewById(R.id.listChat);

    }


}
