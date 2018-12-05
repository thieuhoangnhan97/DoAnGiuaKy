package thieuhoang.nhan.myapplication.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import thieuhoang.nhan.myapplication.db.dao.BrandDao;
import thieuhoang.nhan.myapplication.db.dao.DiviceDao;
import thieuhoang.nhan.myapplication.db.entity.Brand;
import thieuhoang.nhan.myapplication.db.entity.Divice;

@Database(entities = {Brand.class,Divice.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "managerDivice.sqlite";

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null){
                    instance = Room.databaseBuilder(context,AppDatabase.class,DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract DiviceDao diviceDao();

    public abstract BrandDao brandDao();
}
