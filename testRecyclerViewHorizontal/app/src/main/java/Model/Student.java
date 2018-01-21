package Model;

/**
 * Created by anh khoa on 11/20/2017.
 */

public class Student {
    public String ten;
    public String lop;

    public Student() {
    }

    public Student(String ten, String lop) {
        this.ten = ten;
        this.lop = lop;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
