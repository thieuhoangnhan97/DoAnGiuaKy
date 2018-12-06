package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;

import static thieuhoang.nhan.myapplication.activity.brand.ADD;
import static thieuhoang.nhan.myapplication.activity.brand.BRAND;
import static thieuhoang.nhan.myapplication.activity.brand.MESSAGE;
import static thieuhoang.nhan.myapplication.activity.brand.POSITION;

public class sub_brand extends AppCompatActivity {
    TextView title;
    ImageView imageView;
    EditText edtName;
    Button btnSave,btnModify,btnDelete,btnCancel,btnUpdate;
    public static final int PICK_IMAGE = 1;
    AppDatabase db;
    Intent intent;



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
            new AsyncTask<Void,Void,Long>(){

                @Override
                protected Long doInBackground(Void... voids) {
                    return db.brandDao().insert(new Brand(edtName.getText().toString(),null));
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
            Brand brand = (Brand) intent.getSerializableExtra(BRAND);
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
            Brand brand = (Brand) intent.getSerializableExtra(BRAND);
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


    }

    private void setUpActivity() {
        db = AppDatabase.getInstance(this);
        intent = getIntent();
        if(intent.getIntExtra(MESSAGE,-1) == ADD ){
            btnDelete.setVisibility(View.GONE);
            btnModify.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }else {
            title.setText(R.string.detail);
            btnSave.setVisibility(View.GONE);
            edtName.setEnabled(false);
            btnUpdate.setVisibility(View.GONE);


            Brand brand = (Brand) intent.getSerializableExtra(BRAND);
            edtName.setText(brand.getNameBrand());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
        }
    }


}
