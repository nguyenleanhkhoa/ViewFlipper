package Model;

import java.io.Serializable;

/**
 * Created by anh khoa on 11/10/2017.
 */

public class DetailLocation implements Serializable {
    public String id;
    public String idDetail;
    public String inforDetail;
    public String img;

    public DetailLocation() {
    }

    public DetailLocation(String id, String idDetail, String inforDetail, String img) {
        this.id = id;
        this.idDetail = idDetail;
        this.inforDetail = inforDetail;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public String getInforDetail() {
        return inforDetail;
    }

    public void setInforDetail(String inforDetail) {
        this.inforDetail = inforDetail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
