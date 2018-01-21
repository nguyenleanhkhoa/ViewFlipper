package com.example.anhkhoa.reamdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WriteObjtoDB();
        ReadFromDB();

    }

    private void ReadFromDB() {
        Realm realm=Realm.getInstance(getApplicationContext());
        RealmResults<Student> student=realm.where(Student.class).findAll();
        Log.d("DatabaseRealm",""+student.get(1));
    }

    private void WriteObjtoDB() {
        Realm realm=Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        Student student=realm.createObject(Student.class);
        student.setName("Nguyễn Lê Anh Khoa");
        student.setAge("21");
        student.setID("1");
        realm.commitTransaction();
    }
}
