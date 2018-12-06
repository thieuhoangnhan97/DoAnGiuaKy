package thieuhoang.nhan.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

import java.io.ByteArrayOutputStream;

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


    public static byte[] BitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
        return blob.toByteArray();
    }

    public static Bitmap ByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
