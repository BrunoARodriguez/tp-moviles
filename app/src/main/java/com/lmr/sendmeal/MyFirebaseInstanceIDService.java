package com.lmr.sendmeal;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.prefs.Preferences;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {




    private void saveTokenToPrefs(String token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString("registrar_id", token);
       editor.apply();
    }

    @Override
    public void onNewToken(@NonNull String s) {
s = FirebaseInstanceId.getInstance().getToken();
    saveTokenToPrefs(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//remoteMessage.getNotification().notify();
        String tag = "sendmeal";


        Log.d(tag, "from "+remoteMessage.getFrom());
        Log.d(tag, "getCollapse: "+remoteMessage.getCollapseKey());
        Log.d(tag, "message id: " + remoteMessage.getMessageId());
        Log.d(tag, "getMessageType: " + remoteMessage.getMessageType());
        Log.d(tag, "getTo: " + remoteMessage.getTo());

if (remoteMessage.getNotification() != null){
    Log.d(tag, "cuerpo notificacion: "+ remoteMessage.getNotification().getBody());
    crearNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getChannelId());
}
    }

    private  void  crearNotificacion(String titulo, String cuerpo, String canal){

        Intent intent = new Intent(this, CrearPedidoActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
        .setContentTitle(titulo)
        .setContentText(cuerpo)
        .setChannelId(canal)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificacion.build());
    }
}


