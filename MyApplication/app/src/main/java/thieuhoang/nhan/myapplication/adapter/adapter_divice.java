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
import thieuhoang.nhan.myapplication.db.entity.Divice;

import static thieuhoang.nhan.myapplication.Constant.ByteArrayToBitmap;

public class adapter_divice extends ArrayAdapter<Divice> {

    Context context;
    int resource;
    ArrayList<Divice> arrDivice;


    public adapter_divice( Context context, int resource, ArrayList<Divice> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrDivice = objects;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolderDivice view;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_adapter_divice,parent,false);
            view = new ViewHolderDivice();

            view.imageView = convertView.findViewById(R.id.image_adapter_divice);
            view.nameDivice = convertView.findViewById(R.id.txt_name_adapter_divice);
            view.priceDivice = convertView.findViewById(R.id.txt_price_adapter_divice);
            view.txtID = convertView.findViewById(R.id.txt_id_adapter_divice);



            convertView.setTag(view);

        }else {
            view = (ViewHolderDivice) convertView.getTag();
        }

        view.nameDivice.setText(getContext().getResources().getString(R.string.name) + ": "+ arrDivice.get(position).getNameDivice());
        view.priceDivice.setText( getContext().getResources().getString(R.string.price_divice)  + ": "+ arrDivice.get(position).getPriceDivice() + " VND");
        view.imageView.setImageBitmap(ByteArrayToBitmap(arrDivice.get(position).getImageDivice()));
        view.txtID.setText( getContext().getResources().getString(R.string.id_brand) + ": "+ arrDivice.get(position).getIdDivice());






        return convertView;
    }
}
class ViewHolderDivice {
    ImageView imageView;
    TextView nameDivice;
    TextView priceDivice;
    TextView txtID;
}