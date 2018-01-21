package Model;

import java.io.Serializable;

/**
 * Created by anh khoa on 11/7/2017.
 */

public class Location implements Serializable{
    public String id;
    public String ten;
    public String diachi;
    public String log;
    public String lat;
    public String img;
    public String thongtincoban;
    public String matinh;

    public Location() {
    }

    public Location(String id, String ten, String diachi, String log, String lat, String img, String thongtincoban, String matinh) {
        this.id = id;
        this.ten = ten;
        this.diachi = diachi;
        this.log = log;
        this.lat = lat;
        this.img = img;
        this.thongtincoban = thongtincoban;
        this.matinh = matinh;
    }

    public Location(String ten, String diachi, String img) {
        this.ten = ten;
        this.diachi = diachi;
        this.img = img;
    }

    public Location(String id, String ten, String diachi, String img) {
        this.id = id;
        this.ten = ten;
        this.diachi = diachi;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThongtincoban() {
        return thongtincoban;
    }

    public void setThongtincoban(String thongtincoban) {
        this.thongtincoban = thongtincoban;
    }

    public String getMatinh() {
        return matinh;
    }

    public void setMatinh(String matinh) {
        this.matinh = matinh;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", ten='" + ten + '\'' +
                ", diachi='" + diachi + '\'' +
                ", log='" + log + '\'' +
                ", lat='" + lat + '\'' +
                ", img='" + img + '\'' +
                ", thongtincoban='" + thongtincoban + '\'' +
                ", matinh='" + matinh + '\'' +
                '}';
    }
}
