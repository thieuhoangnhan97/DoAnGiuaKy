package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;
import thieuhoang.nhan.myapplication.db.entity.Divice;

import static thieuhoang.nhan.myapplication.Constant.*;


public class sub_brand extends AppCompatActivity {
    TextView title,txtid;
    ImageView imageView;
    EditText edtName;
    Button btnSave,btnModify,btnDelete,btnCancel,btnUpdate;
    AppDatabase db;
    Intent intent;
    Bitmap bitmap;
    Brand brand;
    int number;



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
                    return db.brandDao().insert(new Brand(id,edtName.getText().toString(),BitmapToByteArray(bitmap)));
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
            imageView.setEnabled(true);



        });

        btnUpdate.setOnClickListener(v->{
            brand.setImageBrand(BitmapToByteArray(bitmap));
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
            if(number > 0){
                Toast.makeText(this,getResources().getString(R.string.warring_delete_brand),Toast.LENGTH_LONG).show();
            }
            else {
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
            }


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
        txtid = findViewById(R.id.txt_id_brand);
        db = AppDatabase.getInstance(this);
        intent = getIntent();
        getBrandById();
        loadAllDivice();

    }

    private void setUpActivity() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture);
        imageView.setImageBitmap(bitmap);
        if(intent.getIntExtra(MESSAGE,-1) == ADD ){
            btnDelete.setVisibility(View.GONE);
            btnModify.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }else {
            title.setText(R.string.detail);
            btnSave.setVisibility(View.GONE);
            edtName.setEnabled(false);
            btnUpdate.setVisibility(View.GONE);
            imageView.setEnabled(false);
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
                if(brand != null){
                    edtName.setText(brand.getNameBrand());
                    bitmap = ByteArrayToBitmap(brand.getImageBrand());
                    imageView.setImageBitmap(bitmap);
                    txtid.setText(Long.toString(brand.getIdBrand()));
                }


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
    void loadAllDivice(){
        new AsyncTask<Void,Void,List<Divice>>(){

            @Override
            protected List<Divice> doInBackground(Void... voids) {
                return db.diviceDao().getDivcesByIDBrand(intent.getLongExtra(BRAND,0));
            }

            @Override
            protected void onPostExecute(List<Divice> divices) {
                super.onPostExecute(divices);
                number = divices.size();
            }
        }.execute();
    }


}
