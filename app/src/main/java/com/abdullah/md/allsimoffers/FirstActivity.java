package com.abdullah.md.allsimoffers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    CardView banglalinkCardView, grameenPhoneCardView, airtelCardView;
    CardView robiCardView, teletalkCardView, aboutMeCardView;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);






        banglalinkCardView = findViewById(R.id.banglalinkId);
        grameenPhoneCardView = findViewById(R.id.grameenPhoneId);
        airtelCardView = findViewById(R.id.airtelId);
        robiCardView = findViewById(R.id.robiId);
        teletalkCardView = findViewById(R.id.teletalkId);
        aboutMeCardView = findViewById(R.id.aboutMeId);


        banglalinkCardView.setOnClickListener(this);
        grameenPhoneCardView.setOnClickListener(this);
        airtelCardView.setOnClickListener(this);
        robiCardView.setOnClickListener(this);
        teletalkCardView.setOnClickListener(this);
        aboutMeCardView.setOnClickListener(this);


//-------------------------onCreate method ended------------------------------
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.banglalinkId) {
            intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("intentValue", "banglalink");
            startActivity(intent);
        } else if (view.getId() == R.id.grameenPhoneId) {
            intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("intentValue", "grameenphone");
            startActivity(intent);
        } else if (view.getId() == R.id.airtelId) {
            intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("intentValue", "airtel");
            startActivity(intent);
        } else if (view.getId() == R.id.robiId) {
            intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("intentValue", "robi");
            startActivity(intent);
        } else if (view.getId() == R.id.teletalkId) {
            intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("intentValue", "teletalk");
            startActivity(intent);
        } else if (view.getId() == R.id.aboutMeId) {
            intent = new Intent(FirstActivity.this, ThirdActivity.class);
            startActivity(intent);
        }

//-----------------------------onClick method end--------------------------------
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.homeMenuId) {
            Toast.makeText(this, "You are in home Menu", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.aboutMenuId) {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

//---------------------options menu end--------------------------------------


    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Back Button Pressed");
        builder.setIcon(R.drawable.ic_cancel);
        builder.setMessage("Do you want to exit?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(FirstActivity.this, FinishActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
