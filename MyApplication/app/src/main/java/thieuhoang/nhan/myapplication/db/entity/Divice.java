package thieuhoang.nhan.myapplication.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "divice")
public class Divice {

    @PrimaryKey(autoGenerate = true)
    private long idDivice;

    private String nameDivice;

    private byte[] imageDivice;

    private long priceDivice;

    private int storageDicie;

    @Embedded
    private Brand brand;

    public Divice(String nameDivice, byte[] imageDivice, long priceDivice, int storageDicie, Brand brand) {
        this.nameDivice = nameDivice;
        this.imageDivice = imageDivice;
        this.priceDivice = priceDivice;
        this.storageDicie = storageDicie;
        this.brand = brand;
    }

    public Divice() {
    }

    public long getIdDivice() {
        return idDivice;
    }

    public void setIdDivice(long idDivice) {
        this.idDivice = idDivice;
    }

    public String getNameDivice() {
        return nameDivice;
    }

    public void setNameDivice(String nameDivice) {
        this.nameDivice = nameDivice;
    }

    public byte[] getImageDivice() {
        return imageDivice;
    }

    public void setImageDivice(byte[] imageDivice) {
        this.imageDivice = imageDivice;
    }

    public long getPriceDivice() {
        return priceDivice;
    }

    public void setPriceDivice(long priceDivice) {
        this.priceDivice = priceDivice;
    }

    public int getStorageDicie() {
        return storageDicie;
    }

    public void setStorageDicie(int storageDicie) {
        this.storageDicie = storageDicie;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Divice{" +
                "idDivice=" + idDivice +
                ", nameDivice='" + nameDivice + '\'' +
                ", imageDivice=" + Arrays.toString(imageDivice) +
                ", priceDivice=" + priceDivice +
                ", storageDicie=" + storageDicie +
                ", brand=" + brand +
                '}';
    }
}
