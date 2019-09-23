package com.lmr.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
private Toolbar toolbar;
Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.toolbar= (Toolbar) findViewById(R.id.myTolbar);
        setSupportActionBar(toolbar);

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
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                return true;
            case R.id.mnuOpt2:
Intent i2=new Intent(this,AltaPlatoActivity.class);
startActivity(i2);
                return true;
            case R.id.mnuOpt3:
                Intent i3=new Intent(this,ListaItemsActivity.class);
                startActivity(i3);
                return true;

            default:
                Toast.makeText(this, "¡Opción incorrecta!", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}