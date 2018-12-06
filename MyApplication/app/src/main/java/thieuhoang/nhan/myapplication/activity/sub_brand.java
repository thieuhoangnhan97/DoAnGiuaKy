package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;

import static thieuhoang.nhan.myapplication.Constant.*;


public class sub_brand extends AppCompatActivity {
    TextView title;
    ImageView imageView;
    EditText edtName;
    Button btnSave,btnModify,btnDelete,btnCancel,btnUpdate;
    AppDatabase db;
    Intent intent;
    Bitmap bitmap;
    Brand brand;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_brand);
        setControls();
        setUpActivity();
        setEvents();
    }

    private void setEvents() {
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });

        btnSave.setOnClickListener(v -> {
            long id = loadCount(this);
            Log.d("mylog", String.valueOf(id));
            new AsyncTask<Void,Void,Long>(){

                @Override
                protected Long doInBackground(Void... voids) {
                    return db.brandDao().insert(new Brand(id,edtName.getText().toString(),null));
                }

                @Override
                protected void onPostExecute(Long aLong) {
                    super.onPostExecute(aLong);
                    finish();
                }
            }.execute();

        });
        btnModify.setOnClickListener(v->{
            btnUpdate.setVisibility(View.VISIBLE);
            btnModify.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            edtName.setEnabled(true);
            title.setText(R.string.modify);


        });

        btnUpdate.setOnClickListener(v->{
            brand.setNameBrand(edtName.getText().toString());

            new AsyncTask<Void,Void,Integer>(){

                @Override
                protected Integer doInBackground(Void... voids) {
                    return db.brandDao().update(brand);
                }

                @Override
                protected void onPostExecute(Integer aInt) {
                    super.onPostExecute(aInt);
                    finish();
                }
            }.execute();

        });

        btnCancel.setOnClickListener(v->{
            finish();
        });

        btnDelete.setOnClickListener(v->{
            new AsyncTask<Void,Void,Integer>(){

                @Override
                protected Integer doInBackground(Void... voids) {
                    return db.brandDao().delete(brand);
                }

                @Override
                protected void onPostExecute(Integer integer) {
                    super.onPostExecute(integer);
                    finish();
                }
            }.execute();
        });
    }

    private void setControls() {
        title = findViewById(R.id.txt_title_sub_brand);
        imageView = findViewById(R.id.image_sub_brand);
        btnSave = findViewById(R.id.btn_save_brand);
        edtName = findViewById(R.id.edt_name_brand);
        btnModify = findViewById(R.id.btn_modify_brand);
        btnDelete = findViewById(R.id.btn_delete_brand);
        btnCancel = findViewById(R.id.btn_cancel__brand);
        btnUpdate = findViewById(R.id.btn_update_brand);
        db = AppDatabase.getInstance(this);
        intent = getIntent();

    }

    private void setUpActivity() {

        if(intent.getIntExtra(MESSAGE,-1) == ADD ){
            btnDelete.setVisibility(View.GONE);
            btnModify.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }else {
            title.setText(R.string.detail);
            btnSave.setVisibility(View.GONE);
            edtName.setEnabled(false);
            btnUpdate.setVisibility(View.GONE);


            getBrandById();
        }

    }

    void getBrandById(){
        new AsyncTask<Void,Void,Brand>(){

            @Override
            protected Brand doInBackground(Void... voids) {
                return db.brandDao().getBrandByID(intent.getLongExtra(BRAND,0));
            }

            @Override
            protected void onPostExecute(Brand abrand) {
                super.onPostExecute(abrand);
                brand =  abrand;
                edtName.setText(brand.getNameBrand());

            }
        }.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
