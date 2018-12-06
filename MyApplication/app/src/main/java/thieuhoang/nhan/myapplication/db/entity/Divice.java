package thieuhoang.nhan.myapplication.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Arrays;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "divice",foreignKeys = @ForeignKey(entity = Brand.class,parentColumns = "idBrand",childColumns = "idBrandofDivice",onDelete = CASCADE))
public class Divice implements Serializable {

    @PrimaryKey
    private long idDivice;

    private String nameDivice;

    private byte[] imageDivice;

    private long priceDivice;

    private int storageDicie;

    private long idBrandofDivice;


    public Divice(long id,String nameDivice, byte[] imageDivice, long priceDivice, int storageDicie, long idBrandofDivice) {
        this.idDivice = id;
        this.nameDivice = nameDivice;
        this.imageDivice = imageDivice;
        this.priceDivice = priceDivice;
        this.storageDicie = storageDicie;
        this.idBrandofDivice = idBrandofDivice;
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

    public long getIdBrandofDivice() {
        return idBrandofDivice;
    }

    public void setIdBrandofDivice(long idBrandofDivice) {
        this.idBrandofDivice = idBrandofDivice;
    }

    @Override
    public String toString() {
        return "Divice{" +
                "idDivice=" + idDivice +
                ", nameDivice='" + nameDivice + '\'' +
                ", imageDivice=" + Arrays.toString(imageDivice) +
                ", priceDivice=" + priceDivice +
                ", storageDicie=" + storageDicie +
                ", idBrandofDivice=" + idBrandofDivice +
                '}';
    }
}
