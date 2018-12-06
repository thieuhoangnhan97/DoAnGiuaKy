package thieuhoang.nhan.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Constant {
    public  static final int ADD = 1;
    public  static final String POSITION = "position";
    public  static final String MESSAGE = "message";
    public  static final String BRAND = "brand";
    public static final int PICK_IMAGE = 1;
    public static final String DIVICE = "divice";
    public static final String STRING_COUNT = "string_count";

    public static long COUNT = 0;


    public static long loadCount(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        COUNT = sharedPreferences.getLong(STRING_COUNT,0);
        saveCount(context);
        return COUNT;
    }

    public static void saveCount(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(COUNT == 0){
            COUNT = 12346;
        }
        COUNT += 1;
        editor.putLong(STRING_COUNT,COUNT);
        editor.commit();
    }


}
