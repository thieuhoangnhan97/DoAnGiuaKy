package thieuhoang.nhan.myapplication.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import thieuhoang.nhan.myapplication.db.entity.Brand;

@Dao
public interface BrandDao {

    @Query("select * from brand")
    List<Brand> getAllBrand();

    @Insert
    long insert(Brand brand);

    @Delete
    int delete(Brand brand);

    @Update
    void update(Brand brand);
    


}
