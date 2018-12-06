package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.adapter.adapter_brand;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;

import static thieuhoang.nhan.myapplication.Constant.*;


public class brand extends AppCompatActivity {
    ListView listView;
    Fragment fragment;
    AppDatabase db;
    ArrayAdapter adapter;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAllBrand();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        setControls();
        setEvents();

    }

    private void setEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(brand.this,divice.class);
                Brand brand = (Brand) adapter.getItem(position);
                intent.putExtra(BRAND,brand.getIdBrand());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(brand.this,sub_brand.class);
                Brand brand = (Brand) adapter.getItem(position);
                intent.putExtra(BRAND,brand.getIdBrand());
                startActivity(intent);
                return true;
            }
        });
    }

    private void setControls() {
        listView = findViewById(R.id.list_brand);
        db = AppDatabase.getInstance(this);
        adapter = new adapter_brand(this, R.layout.activity_adapter_brand, new ArrayList<>());
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_add:
                Intent intent = new Intent(brand.this,sub_brand.class);
                intent.putExtra(MESSAGE,ADD);
                startActivity(intent);

                break;
            case R.id.option_menu_about:
                Intent intent1 = new Intent(brand.this,MapsActivity.class);
                startActivity(intent1);
                break;
            case R.id.option_menu_exit:
                moveTaskToBack(true);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void loadAllBrand(){
        new AsyncTask<Void,Void, List<Brand>>(){

            @Override
            protected List<Brand> doInBackground(Void... voids) {
                return db.brandDao().getAllBrand();
            }

            @Override
            protected void onPostExecute(List<Brand> brands) {
                super.onPostExecute(brands);
                adapter.clear();
                adapter.addAll(brands);
            }
        }.execute();
    }
}
