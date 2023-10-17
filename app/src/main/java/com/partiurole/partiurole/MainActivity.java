package com.partiurole.partiurole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.github.islamkhsh.CardSliderViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.partiurole.partiurole.adapter.EventsAdapter;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.fragment.FavoritesFragment;
import com.partiurole.partiurole.fragment.HomeFragment;
import com.partiurole.partiurole.fragment.SettingsFragment;
import com.partiurole.partiurole.model.Event;
import com.partiurole.partiurole.util.MyApplication;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    private static SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =  findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance();
        db = persistenceHelper.getWritableDatabase();

        loadFragment(homeFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(android.view.MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeFragment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                    return true;
                case R.id.favoritesFragment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, favoritesFragment).commit();
                    return true;
                case R.id.settingsFragment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, settingsFragment).commit();
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}