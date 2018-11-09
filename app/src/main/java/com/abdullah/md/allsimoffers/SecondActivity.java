package com.abdullah.md.allsimoffers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    DatabaseReference dref;
    ListView listview;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> keyCheck = new ArrayList<>();
    Bundle bundle;
    String title;
    private AdView adView;
    private AdRequest adRequest;
    TextView preloader;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        preloader = findViewById(R.id.preloaderId);

        adView = findViewById(R.id.adViewId);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        listview = findViewById(R.id.listViewId);
        adapter = new ArrayAdapter<String>(this, R.layout.custom_layout, R.id.headingTextViewId, list);
        listview.setAdapter(adapter);


        preloader.setVisibility(View.VISIBLE);
        listview.setVisibility(View.GONE);

        bundle = getIntent().getExtras();
        if (bundle != null) {

            String intentValue = bundle.getString("intentValue");
            if (intentValue.contains("banglalink")) {
                dref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://allsimoffers.firebaseio.com/banglalink");
                title = "Banglalink";
            } else if (intentValue.contains("grameenphone")) {
                dref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://allsimoffers.firebaseio.com/grameenphone");
                title = "grameenPhone";
            } else if (intentValue.contains("robi")) {
                dref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://allsimoffers.firebaseio.com/robi");
                title = "Robi";
            } else if (intentValue.contains("airtel")) {
                dref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://allsimoffers.firebaseio.com/airtel");
                title = "Airtel";
            } else if (intentValue.contains("teletalk")) {
                dref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://allsimoffers.firebaseio.com/teletalk");
                title = "Teletalk";
            }
        }


        dref.keepSynced(true);


        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(String.class));
                keyCheck.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                String key = (dataSnapshot.getKey());
                int valueOfKey = keyCheck.indexOf(key);
                list.set(valueOfKey, value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getValue(String.class));
                keyCheck.remove(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SecondActivity.this, databaseError.toException().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        getSupportActionBar().setTitle(title);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 50; i++) {
                    if (!list.isEmpty()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                preloader.setVisibility(View.GONE);
                                listview.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i==50){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                preloader.setText(getResources().getString(R.string.how_to_use));
                            }
                        });
                    }

                }
            }
        });
        thread.start();

//---------------------------onCreate method end--------------------------
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

            Intent intent = new Intent(this, FirstActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else if (item.getItemId() == R.id.aboutMenuId) {
            Intent intent = new Intent(this, ThirdActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, FirstActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

//---------------------options menu end--------------------------------------

}
