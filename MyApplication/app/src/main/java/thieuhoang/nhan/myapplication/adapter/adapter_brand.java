package thieuhoang.nhan.myapplication.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import thieuhoang.nhan.myapplication.R;
import thieuhoang.nhan.myapplication.db.entity.Brand;

import static thieuhoang.nhan.myapplication.Constant.ByteArrayToBitmap;

public class adapter_brand extends ArrayAdapter<Brand> {
    Context context;
    int resource;
    ArrayList<Brand> arrBrand;



    public adapter_brand( Context context, int resource, ArrayList<Brand> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrBrand = objects;
    }



    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolderBrand view;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_adapter_brand,parent,false);
            view = new ViewHolderBrand();

            view.imageView = convertView.findViewById(R.id.image_adapter_brand);
            view.textView = convertView.findViewById(R.id.txt_name_adapter_brand);
            view.txtID = convertView.findViewById(R.id.txt_id_adapter_brand);

            convertView.setTag(view);


        }
        else {
            view = (ViewHolderBrand) convertView.getTag();
        }

        view.textView.setText(arrBrand.get(position).getNameBrand());
        view.imageView.setImageBitmap(ByteArrayToBitmap(arrBrand.get(position).getImageBrand()));
        view.txtID.setText("" + arrBrand.get(position).getIdBrand());




        return convertView;
    }
}

class ViewHolderBrand {
    ImageView imageView;
    TextView textView;
    TextView txtID;
}
