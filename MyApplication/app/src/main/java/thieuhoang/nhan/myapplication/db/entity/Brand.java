package thieuhoang.nhan.myapplication.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Entity(tableName = "brand")
public class Brand implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private long idBrand;

    private String nameBrand;

    private byte[] imageBrand;







    public Brand(String nameBrand, byte[] imageBrand) {
        this.nameBrand = nameBrand;
        this.imageBrand = imageBrand;
    }

    public Brand() {
    }


    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public byte[] getImageBrand() {
        return imageBrand;
    }

    public void setImageBrand(byte[] imageBrand) {
        this.imageBrand = imageBrand;
    }



    @Override
    public String toString() {
        return "Brand{" +
                "idBrand=" + idBrand +
                ", nameBrand='" + nameBrand + '\'' +
                ", imageBrand=" + Arrays.toString(imageBrand) +
                '}';
    }
}


