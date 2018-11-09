package com.abdullah.md.allsimoffers;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int setValue;

//-------------------Global Variable ended-------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        progressBar = findViewById(R.id.progressBar);


        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgressing();
                goToFirstActivity();
            }
        });
        thread.start();
    }
//-----------------------onCreate method ended--------------------------


    private void goToFirstActivity() {

        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(intent);
    }
//---------------------goToFirstActivity method ended----------------


    private void startProgressing() {
        for (setValue = 0; setValue <= 100; setValue += 10) {
            progressBar.setProgress(setValue);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//-------------------startProgressing method ended-------------------



}
//----------------------MainActivity Class ended-------------------------


