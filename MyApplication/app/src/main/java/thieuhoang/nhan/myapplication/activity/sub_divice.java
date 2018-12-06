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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.adapter.adapter_divice;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;
import thieuhoang.nhan.myapplication.db.entity.Divice;

import static thieuhoang.nhan.myapplication.Constant.*;


public class sub_divice extends AppCompatActivity {
    TextView title,txtNameBrand,txtid;
    ImageView imageView;
    EditText edtName,edtPrice;
    Spinner spinner;
    Button btnSave,btnUpdate,btnModify,btnDelete,btnCancel;
    Intent intent;
    AppDatabase db;
    Brand brand;
    Divice divice;
    List<Integer> arrStorageNumber = new ArrayList<>();
    List<String> arrStorageString = new ArrayList<>();
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_divice);
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

        btnSave.setOnClickListener(v->{
            long id = loadCount(this);
            Log.d("mylog", String.valueOf(id));
            new AsyncTask<Void,Void,Long>(){

                @Override
                protected Long doInBackground(Void... voids) {
                    return db.diviceDao().insert(new Divice(id,edtName.getText().toString(),BitmapToByteArray(bitmap),Long.parseLong(edtPrice.getText().toString()),arrStorageNumber.get(spinner.getSelectedItemPosition()),brand.getIdBrand()));

                }

                @Override
                protected void onPostExecute(Long aLong) {
                    super.onPostExecute(aLong);
                    finish();
                }
            }.execute();



        });

        btnModify.setOnClickListener(v->{
            title.setText(R.string.modify);
            edtName.setEnabled(true);
            edtPrice.setEnabled(true);
            spinner.setEnabled(true);
            btnUpdate.setVisibility(View.VISIBLE);
            btnModify.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            imageView.setEnabled(true);


        });

        btnUpdate.setOnClickListener(v->{
            divice.setNameDivice(edtName.getText().toString());
            divice.setPriceDivice(Long.parseLong(edtPrice.getText().toString()));
            divice.setStorageDicie(arrStorageNumber.get(spinner.getSelectedItemPosition()));
            divice.setImageDivice(BitmapToByteArray(bitmap));

            new AsyncTask<Void,Void,Integer>(){


                @Override
                protected Integer doInBackground(Void... voids) {
                    return db.diviceDao().update(divice);
                }

                @Override
                protected void onPostExecute(Integer integer) {
                    super.onPostExecute(integer);
                    finish();
                }
            }.execute();


        });

        btnDelete.setOnClickListener(v->{
            new AsyncTask<Void,Void,Integer>(){

                @Override
                protected Integer doInBackground(Void... voids) {
                    return db.diviceDao().delete(divice);
                }

                @Override
                protected void onPostExecute(Integer integer) {
                    super.onPostExecute(integer);
                    finish();
                }
            }.execute();
        });

        btnCancel.setOnClickListener(v->{
            finish();
        });
    }

    private void setUpActivity() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture);
        imageView.setImageBitmap(bitmap);
        if(intent.getIntExtra(MESSAGE,-1) == ADD){
            btnUpdate.setVisibility(View.GONE);
            btnModify.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }else {
            title.setText(R.string.detail);
            edtName.setEnabled(false);
            edtPrice.setEnabled(false);
            spinner.setEnabled(false);
            btnSave.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            imageView.setEnabled(false);




        }
    }

    private void setControls() {
        title = findViewById(R.id.txt_title_sub_divice);
        imageView = findViewById(R.id.image_sub_divice);
        edtName = findViewById(R.id.edt_name_divice);
        edtPrice = findViewById(R.id.edt_price_divice);
        txtNameBrand = findViewById(R.id.txt_name_brand_of_divice);
        spinner = findViewById(R.id.spinner_storage_divice);
        btnSave = findViewById(R.id.btn_save_divice);
        btnUpdate = findViewById(R.id.btn_update_divice);
        btnModify = findViewById(R.id.btn_modify_divice);
        btnDelete = findViewById(R.id.btn_delete_divice);
        btnCancel = findViewById(R.id.btn_cancel_divice);
        txtid = findViewById(R.id.txt_id_divice);
        intent = getIntent();
        db = AppDatabase.getInstance(this);
        getBrandById();
        getDiviceByid();
        arrStorageNumber.add(8);
        arrStorageNumber.add(16);
        arrStorageNumber.add(32);
        arrStorageNumber.add(64);
        arrStorageNumber.add(128);
        arrStorageNumber.add(265);
        for (int item: arrStorageNumber
             ) {
            arrStorageString.add(item + " GB");

        }
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrStorageString);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);


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


    void getDiviceByid(){
        new AsyncTask<Void,Void,Divice>(){

            @Override
            protected Divice doInBackground(Void... voids) {
                return db.diviceDao().getDiviceById(intent.getLongExtra(DIVICE,0));


            }

            @Override
            protected void onPostExecute(Divice adivice) {
                super.onPostExecute(adivice);
                divice = adivice;
                Log.d("mylog", String.valueOf(divice));
                if(divice != null){
                    txtid.setText(divice.getIdDivice() + "");
                    edtName.setText(divice.getNameDivice());
                    edtPrice.setText(divice.getPriceDivice() + "");
                    for(int i =0; i < arrStorageNumber.size() ;i++){
                        if(divice.getStorageDicie() == arrStorageNumber.get(i)){
                            spinner.setSelection(i);
                        }
                    }
                    imageView.setImageBitmap(ByteArrayToBitmap(divice.getImageDivice()));
                }

            }
        }.execute();
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
                txtNameBrand.setText(brand.getNameBrand());

            }
        }.execute();
    }
}
