package com.example.demo.infydemosample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Display list of heroes
 */
public class HeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //adding fragment dynamically
        HeroFragment mainFragment = new HeroFragment();
        addFragment(mainFragment);


    }

    //Add a fragment
    private void addFragment(HeroFragment mainFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mainFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}
