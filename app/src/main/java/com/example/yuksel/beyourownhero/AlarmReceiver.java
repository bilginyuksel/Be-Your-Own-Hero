package com.example.yuksel.beyourownhero;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, final Intent intent) {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if(alarmUri == null)
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        final Ringtone ringtone = RingtoneManager.getRingtone(context,alarmUri);
        ringtone.play();


        AlertDialog.Builder alert = new AlertDialog.Builder(CustomAdapter.ct);
        alert.setTitle("ALARM");
        alert.setMessage("WAKE UP !!!");
        alert.setPositiveButton("ENOUGH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlarmManager alarmManager = (AlarmManager) CustomAdapter.ct.getSystemService(Context.ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(CustomAdapter.ct, 1253, intent, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);
                alarmManager.cancel(pendingIntent);
                ringtone.stop();

            dialog.dismiss();
            }
        });

        alert.show();
    }
}
