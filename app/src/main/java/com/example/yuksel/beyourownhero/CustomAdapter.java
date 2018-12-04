package com.example.yuksel.beyourownhero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;

    private static class ViewHolder{
        TextView txtHeader;
        TextView txtDate;
    }
    public CustomAdapter(ArrayList<DataModel> data ,Context context){
        super(context,R.layout.row_login,data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;



    }
    private int lastposition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        DataModel model = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_login ,parent,false);
            viewHolder.txtHeader=(TextView)convertView.findViewById(R.id.txtHeader);
            viewHolder.txtDate = (TextView)convertView.findViewById(R.id.txtDate);

            result = convertView;
            convertView.setTag(viewHolder);

        }else {
            viewHolder =(ViewHolder) convertView.getTag();
            result = convertView;
        }
        /*
        *
        * Animation parts
        *
        * */
        viewHolder.txtHeader.setText(model.getHeader());
        viewHolder.txtDate.setText(model.getDate());

        return convertView;

    }

}
