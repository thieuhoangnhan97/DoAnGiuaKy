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
    void update(Divice divice);

}
