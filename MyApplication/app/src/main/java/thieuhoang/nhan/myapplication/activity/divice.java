package thieuhoang.nhan.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
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
import thieuhoang.nhan.myapplication.adapter.adapter_divice;
import thieuhoang.nhan.myapplication.db.AppDatabase;
import thieuhoang.nhan.myapplication.db.entity.Brand;
import thieuhoang.nhan.myapplication.db.entity.Divice;

import static thieuhoang.nhan.myapplication.Constant.*;


public class divice extends AppCompatActivity {
    ListView listView;
    AppDatabase db;
    ArrayAdapter adapter;
    Intent intent;
    Brand brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divice);
        setControls();
        setUpActivity();
        setEvents();

    }

    private void setEvents() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Divice divice = (Divice) adapter.getItem(position);
                Intent intent = new Intent(divice.this,sub_divice.class);
                intent.putExtra(DIVICE,divice);
                intent.putExtra(BRAND,brand);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAllDivice();
    }

    private void setUpActivity() {
        new AsyncTask<Void,Void,List<Divice>>(){

            @Override
            protected List<Divice> doInBackground(Void... voids) {
                return db.diviceDao().getDivcesByIDBrand(brand.getIdBrand());
            }

            @Override
            protected void onPostExecute(List<Divice> divices) {
                super.onPostExecute(divices);
                adapter.clear();
                adapter.addAll(divices);
            }
        }.execute();
    }

    private void setControls() {
        listView = findViewById(R.id.list_divice);
        db = AppDatabase.getInstance(this);
        adapter = new adapter_divice(this,R.layout.activity_adapter_divice, new ArrayList<Divice>());
        listView.setAdapter(adapter);
        intent = getIntent();
        brand = (Brand) intent.getSerializableExtra(BRAND);

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
                Intent intent = new Intent(divice.this,sub_divice.class);
                intent.putExtra(MESSAGE,ADD);
                intent.putExtra(BRAND,brand);
                startActivity(intent);
                break;
            case R.id.option_menu_about:
                Intent intent1 = new Intent(divice.this,MapsActivity.class);
                startActivity(intent1);
                break;
            case R.id.option_menu_exit:
                moveTaskToBack(true);
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    void loadAllDivice(){
        new AsyncTask<Void,Void,List<Divice>>(){

            @Override
            protected List<Divice> doInBackground(Void... voids) {
                return db.diviceDao().getDivcesByIDBrand(brand.getIdBrand());
            }

            @Override
            protected void onPostExecute(List<Divice> divices) {
                super.onPostExecute(divices);
                adapter.clear();
                adapter.addAll(divices);
            }
        }.execute();
    }
}
