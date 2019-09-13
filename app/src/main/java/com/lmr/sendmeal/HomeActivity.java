package com.lmr.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuOpt1:
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                return true;
            case R.id.mnuOpt2:
                return true;
            default:
                Toast.makeText(this, ". . . . ", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }