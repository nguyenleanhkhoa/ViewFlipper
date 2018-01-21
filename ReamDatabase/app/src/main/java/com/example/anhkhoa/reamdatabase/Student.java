package com.example.anhkhoa.reamdatabase;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anh khoa on 11/23/2017.
 */

public class Student extends RealmObject {
    @PrimaryKey
    public String Name;
    public String Age;

    @Ignore
    public String ID;

    public Student() {
    }

    public Student(String name, String age, String ID) {
        Name = name;
        Age = age;
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
