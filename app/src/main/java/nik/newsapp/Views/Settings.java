package nik.newsapp.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Set;

import nik.newsapp.R;

public class Settings extends AppCompatActivity {

    Switch mode;
    AppCompatButton apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
            Log.d("dark", "onCreate: set1");
        }
        apply=findViewById(R.id.apply);


        mode=findViewById(R.id.mode);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            mode.setChecked(true);
            Log.d("dark", "onCreate: set2");
        }
        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.d("dark", "onCreate: set3");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


                }
                else{
                    Log.d("dark", "onCreate: set4");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this,Main.class));
            }
        });
    }

    private void restartApp() {
        startActivity(new Intent(getApplicationContext(),Settings.class));
        finish();
    }
}
