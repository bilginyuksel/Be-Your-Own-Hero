package com.example.yuksel.beyourownhero;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    static ArrayList<Reminder> reminders;
    LocalDatabase local_db;
    Context mContext;
    static Context ct ;
    EditText etTitle,etBody;
    Button btnCancel,btnSave;
    TextView txtDate,txtTime;
    int j = 0;

    private static class ViewHolder{
        TextView txtHeader;
        TextView txtDate;
        ImageButton btnShow;
        ImageButton btnDelete;
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
        local_db = new LocalDatabase(mContext);

        switch (v.getId()){
            case R.id.btnDelete:

                local_db.deleteM(this.dataSet.get(position).getHeader());
                this.dataSet.remove(position);
                this.notifyDataSetChanged();
                break;

            case R.id.btnShow:
                if(this.dataSet.get(position).getHeader().equals("ALARM!!!")){
                    Toast.makeText(ct, "Just an alarm why do you want to see that...", Toast.LENGTH_SHORT).show();
                    break;
                }

                String title = this.dataSet.get(position).getHeader();

                for(int i=0;i<reminders.size();i++)
                    if (reminders.get(i).getHeader().equals(title)) {
                        j = i;
                        break;
                    }

                Dialog dialog = new Dialog(ct);
                dialog.setContentView(R.layout.remind_notes);
                txtDate = dialog.findViewById(R.id.txtRemindDate);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                btnSave = dialog.findViewById(R.id.btnSave);
                txtTime = dialog.findViewById(R.id.txtRemindTime);
                etBody = dialog.findViewById(R.id.etBody);
                etTitle = dialog.findViewById(R.id.etHeader);
                etBody.setEnabled(false);
                etTitle.setEnabled(false);
                txtTime.setEnabled(false);
                txtDate.setEnabled(false);
                btnSave.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));





                txtDate.setText(reminders.get(j).getRemindDate());
                txtTime.setText(reminders.get(j).getRemindTime());
                etTitle.setText(reminders.get(j).getHeader());
                etBody.setText(reminders.get(j).getText());



                dialog.show();


                break;
        }

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
            viewHolder.btnShow = (ImageButton)convertView.findViewById(R.id.btnShow);
            viewHolder.btnDelete=(ImageButton)convertView.findViewById(R.id.btnDelete);

            result = convertView;
            convertView.setTag(viewHolder);

        }else {
            viewHolder =(ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastposition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastposition = position;

        viewHolder.txtHeader.setText(model.getHeader());
        viewHolder.txtDate.setText(model.getDate());
        viewHolder.btnShow.setOnClickListener(this);
        viewHolder.btnDelete.setOnClickListener(this);
        viewHolder.btnShow.setTag(position);
        viewHolder.btnDelete.setTag(position);

        return convertView;

    }

}
