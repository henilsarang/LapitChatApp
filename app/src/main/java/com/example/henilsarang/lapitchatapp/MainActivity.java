package com.example.henilsarang.lapitchatapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;
    private ViewPager mViewPager;
    private SectionsPageAdapter mSectionsPageAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("ChattingItUp");

        //Tabs
        mViewPager= findViewById(R.id.main_tabPager);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPageAdapter);

        mTabLayout = findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if(currentUser == null)
        {
            sentToStart();

        }
    }

    private void sentToStart() {
        Intent startIntent= new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



         super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
    if(item.getItemId() == R.id.main_logout_btn){
        FirebaseAuth.getInstance().signOut();
        sentToStart();

    }


    if(item.getItemId() == R.id.main_settings_btn){

        Intent settingIntent = new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(settingIntent);
    }

    if(item.getItemId() == R.id.main_all_btn){

            Intent AllUsersIntent = new Intent(MainActivity.this,UsersActivity.class);
            startActivity(AllUsersIntent);
        }


         return true;
    }
}
