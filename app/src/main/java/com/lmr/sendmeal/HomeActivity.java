package com.lmr.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        createNotificacionChanel();
        this.toolbar = (Toolbar) findViewById(R.id.myTolbar);

        setSupportActionBar(toolbar);


//this.createNotificacionChanel();
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()){
                    return;
                }

                String token = task.getResult().getToken();

                String msg = getString(R.string.msg_token, token);
                Log.d("sendmeals", msg);
                Toast.makeText(HomeActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }//cierra el metodo onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuOpt1:
                Intent i1 = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i1);
                return true;
            case R.id.mnuOpt2:
                Intent i2 = new Intent(HomeActivity.this, AltaPlatoActivity.class);
                startActivity(i2);
                return true;
            case R.id.mnuOpt3:
                Intent i3 = new Intent(HomeActivity.this, ListaPlatosActivity.class);
                startActivity(i3);
                return true;
            case  R.id.mnuOpt4:
Intent i4= new Intent(HomeActivity.this,CrearPedidoActivity.class);
startActivity(i4);
                return  true;
            case  R.id.mnuOpt5:
                Intent i5 = new Intent(HomeActivity.this,Maps2Activity.class);
                startActivity(i5);
                return  true;
            default:
                Toast.makeText(this, "¡Opción incorrecta!", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }



private void  createNotificacionChanel(){
if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
    CharSequence name=getString(R.string.nombre_canal);
    String descripcion=getString(R.string.descripcion_canal);
    int importancia= NotificationManager.IMPORTANCE_DEFAULT;
    NotificationChannel canal=new NotificationChannel("sendmeals",name,importancia);
    canal.setDescription(descripcion);
    NotificationManager notificationManager=getSystemService(NotificationManager.class);
notificationManager.createNotificationChannel(canal);
}
}


}

