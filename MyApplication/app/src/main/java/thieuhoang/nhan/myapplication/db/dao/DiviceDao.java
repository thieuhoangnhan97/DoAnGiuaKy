package thieuhoang.nhan.myapplication.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import thieuhoang.nhan.myapplication.db.entity.Divice;

@Dao
public interface DiviceDao {
    @Query("select * from divice")
    List<Divice> getAllDivice();

    @Insert
    long insert(Divice divice);

    @Delete
    int delete(Divice divice);

    @Update
    int update(Divice divice);

    @Query("select * from divice where idBrandofDivice == :idBrand")
    List<Divice> getDivcesByIDBrand(long idBrand);

    @Query("select * from divice where idDivice == :id")
    Divice getDiviceById(long id);

}
