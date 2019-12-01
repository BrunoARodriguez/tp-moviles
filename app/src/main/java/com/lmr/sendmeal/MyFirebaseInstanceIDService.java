package com.lmr.sendmeal;

import android.app.Service;
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

NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
notificationManager.notify(99, remoteMessage.getNotification());
        Log.d("cuerpo de la notificacion", remoteMessage.getNotification().getBody());
    }
}


