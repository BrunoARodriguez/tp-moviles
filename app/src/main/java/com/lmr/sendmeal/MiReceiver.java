package com.lmr.sendmeal;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.nio.channels.Channel;

public class MiReceiver extends BroadcastReceiver {
public  static  final  String EVENTO_01="com.lmr.sendmeal.EVENTO_PARA_OFERTA";
    public  static  final  String EVENTO_02="com.lmr.sendmeal.EVENTO_02_MENSAJE";
    long[] v = {500,1000,250,500};

            @Override
    public  void  onReceive(Context context, Intent intent) {
                Intent destino=new Intent(context,AltaPlatoActivity);
                destino.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent=PendingIntent.getActivities(context,0,destino,0);
                NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this, canl)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(intent.getExtras().getString("titulo"))
                        .setContentText(intent.getExtras().getString("texto"))
                        .setContentIntent(pendingIntent)
.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setVibrate(v,3)
                        .setAutoCancel(true);

            }

}
