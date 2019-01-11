package com.example.yuksel.beyourownhero;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class ActivityLogin extends AppCompatActivity {


    String str;
    final static int REQUEST_CODE = 1;
    Context mcontext = ActivityLogin.this;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    DataModel dataModel;
    static ArrayList<User> arrUsers = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    TimePicker timePickerAlarm;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    static ArrayList<Word> arrWords = new ArrayList<>();
    TextView txtCat,txtWord,txtRemindDate,txtRemindTime,txtHeroProfile;
    ImageButton btnWordOfDay,btnalarm,btnProfile,btnNotebook,btnFavourite,btnDislike;
    LocalDatabase local_db;
    ArrayList<DataModel> dataModels;
    Button btnFeelings,btnAlarmSave,btnCancel,btnSave;
    ArrayList<Reminder> reminders;
    ListView listView;
    private CustomAdapter adapter;
    EditText etHeader,etBody;






    public void __init__(){

        CustomAdapter.ct = ActivityLogin.this;

        local_db = new LocalDatabase(mcontext);
        btnFeelings = (Button)findViewById(R.id.btnFeelings);
        reminders = new ArrayList<>();
        btnWordOfDay=(ImageButton)findViewById(R.id.imgButtonWordofDay);
        btnalarm=(ImageButton)findViewById(R.id.btnAlarm);
        btnProfile=(ImageButton)findViewById(R.id.btnProfile);
        btnNotebook = (ImageButton)findViewById(R.id.btnNotebook);


        listView = (ListView)findViewById(R.id.lstView);
        dataModels = new ArrayList<>();

        dataModels = local_db.getM();
        CustomAdapter.reminders = local_db.getR();





        adapter = new CustomAdapter(dataModels,getApplicationContext());
        listView.setAdapter(adapter);



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        FirebaseDatabase db = new FirebaseDatabase();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String mail = mAuth.getCurrentUser().getEmail();
        db.getUser(mail);
        __init__();





        final Intent intent = new Intent(this,ActivityWords.class);



        btnFeelings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });

        btnWordOfDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWordOfTheDay();

            }
        });

        btnalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarm();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showProfile(); }});

        btnNotebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotes();
            }
        });




    }


    //ALERT FUNCTIONS
    public void showWordOfTheDay(){
        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.alert_wordofday);
        dialog.setCancelable(false);
        txtCat = dialog.findViewById(R.id.txtCategory);
        btnDislike = dialog.findViewById(R.id.btnDislike);
        txtWord = dialog.findViewById(R.id.txtMotivationWord);
        btnFavourite = dialog.findViewById(R.id.btnFavourite);
        Random rand = new Random();
        final int rndword = rand.nextInt(arrWords.size()-1);
        txtCat.setHint(""+arrWords.get(rndword).getAuthor());
        txtWord.setText(""+arrWords.get(rndword).getMotivationWord());

        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                local_db.addW(arrWords.get(rndword));
                Toast.makeText(ActivityLogin.this, "Go check your Library", Toast.LENGTH_SHORT).show();
            }
        });

        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();

    }


    public void showAlarm(){
        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.alarm_manager);
        btnAlarmSave = (Button)dialog.findViewById(R.id.btnAlarmSave);
        timePickerAlarm = (TimePicker)dialog.findViewById(R.id.timePickerAlarm);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btnAlarmSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ALARM MANAGER
                Calendar calset = (Calendar)calendar.clone();

                calset.set(Calendar.HOUR_OF_DAY,timePickerAlarm.getHour());
                calset.set(Calendar.MINUTE,timePickerAlarm.getMinute());
                calset.set(Calendar.SECOND,0);
                calset.set(Calendar.MILLISECOND,0);

                if(calset.compareTo(calendar)<=0)
                    calset.add(Calendar.DATE,1);

                setAlarm(calset);
                dataModel = new DataModel("ALARM!!!",timePickerAlarm.getHour()+":"+timePickerAlarm.getMinute());
                local_db.addD(dataModel);
                dataModels.add(dataModel);
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });
        dialog.show();
    }



    public void showProfile(){

        Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.heroesprofile);
        dialog.setCancelable(true);
        txtHeroProfile = (TextView)dialog.findViewById(R.id.txtHeroProfile);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));



        txtHeroProfile.setText("You are a HERO "+arrUsers.get(0).getName()+"!!!.I guess your age is "+arrUsers.get(0).getAge()
                +"..You came to planet EARTH from planet  '"+arrUsers.get(0).getPlanet() +"' "+"Humans which you rescued calls you "+
                        arrUsers.get(0).getUsername() + " .Your life quality is a bit low but you are trying to improve it...");
        dialog.show();
    }



    public void showNotes(){
        final Dialog dialog1 = new Dialog(mcontext);
        dialog1.setContentView(R.layout.remind_notes);

        txtRemindDate = (TextView)dialog1.findViewById(R.id.txtRemindDate);
        txtRemindTime = (TextView)dialog1.findViewById(R.id.txtRemindTime);
        btnCancel = (Button)dialog1.findViewById(R.id.btnCancel);
        btnSave = (Button)dialog1.findViewById(R.id.btnSave);
        etHeader = (EditText)dialog1.findViewById(R.id.etHeader);
        etBody = dialog1.findViewById(R.id.etBody);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String header,date,body,time;

                if(!etHeader.getText().toString().isEmpty()){
                    date = txtRemindDate.getText().toString();
                    if(date.equals("Click for Remind Date"))
                        date = "Its just a note..";

                    header = etHeader.getText().toString();
                    DataModel m = new DataModel(header,date);
                    dataModels.add(m);
                    local_db.addD(m);
                    body = etBody.getText().toString();
                    if(body.isEmpty())
                        body = "Nothing but space...";
                    time = txtRemindTime.getText().toString();

                    Reminder r = new Reminder(header,body,date,time);
                    local_db.addR(r);
                    FirebaseDatabase fb = new FirebaseDatabase();
                    fb.addToHost("Reminder",r,fb.uuid());
                    //we have to add reminder variables to sqlite database it will solve our problem



                    dialog1.dismiss();

                }
                adapter.notifyDataSetChanged();
                finish();
                startActivity(getIntent());


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        txtRemindTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        txtRemindDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setCancelable(false);
        dialog1.show();
    }



    public void showTimePickerDialog(){
        TimePickerDialog dialog = new TimePickerDialog(mcontext, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                str = sdf.format(new Time(hourOfDay,minute,0));
                txtRemindTime.setText(str);
            }
        },8,0,true);

            dialog.show();

    }



    public void showDatePickerDialog(){

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dtp = new DatePickerDialog(ActivityLogin.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
        dtp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dtp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                txtRemindDate.setText(format.format(new Date(year-1900,month,dayOfMonth)));
            }
        });
        dtp.show();
    }

    public void setAlarm(Calendar alarmCalendar){

        Toast.makeText(mcontext, "Alarm set successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(),AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(),REQUEST_CODE,intent,0);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,alarmCalendar.getTimeInMillis(),pendingIntent);
    }

}
