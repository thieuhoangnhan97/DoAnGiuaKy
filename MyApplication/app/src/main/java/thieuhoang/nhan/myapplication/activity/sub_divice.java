package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.adapter.adapter_divice;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;
import thieuhoang.nhan.myapplication.db.entity.Divice;

import static thieuhoang.nhan.myapplication.activity.brand.ADD;
import static thieuhoang.nhan.myapplication.activity.brand.BRAND;
import static thieuhoang.nhan.myapplication.activity.brand.MESSAGE;
import static thieuhoang.nhan.myapplication.activity.divice.DIVICE;
import static thieuhoang.nhan.myapplication.activity.sub_brand.PICK_IMAGE;

public class sub_divice extends AppCompatActivity {
    TextView title,txtNameBrand;
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

            new AsyncTask<Void,Void,Long>(){

                @Override
                protected Long doInBackground(Void... voids) {
                    return db.diviceDao().insert(new Divice(edtName.getText().toString(),null,Long.parseLong(edtPrice.getText().toString()),arrStorageNumber.get(spinner.getSelectedItemPosition()),brand.getIdBrand()));

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

        });

        btnUpdate.setOnClickListener(v->{
            divice.setNameDivice(edtName.getText().toString());
            divice.setPriceDivice(Long.parseLong(edtPrice.getText().toString()));
            divice.setStorageDicie(arrStorageNumber.get(spinner.getSelectedItemPosition()));

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
        Brand brand = (Brand) intent.getSerializableExtra(BRAND);
        txtNameBrand.setText(brand.getNameBrand());
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
            edtName.setText(divice.getNameDivice());
            edtPrice.setText(divice.getPriceDivice() + "");
            for(int i =0; i < arrStorageNumber.size() ;i++){
                if(divice.getStorageDicie() == arrStorageNumber.get(i)){
                    spinner.setSelection(i);
                }
            }
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
        intent = getIntent();
        db = AppDatabase.getInstance(this);
        brand = (Brand) intent.getSerializableExtra(BRAND);
        divice = (Divice) intent.getSerializableExtra(DIVICE);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
        }
    }
}
