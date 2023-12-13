package com.partiurole.partiurole;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.fragment.FavoritesFragment;
import com.partiurole.partiurole.fragment.HomeFragment;
import com.partiurole.partiurole.fragment.SettingsFragment;
import com.partiurole.partiurole.model.Event;
import com.partiurole.partiurole.util.DateParser;
import com.partiurole.partiurole.util.DownloadImageAndConvertToBase64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        getEvents();
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

    private void getEvents() {
        Call<List<Event>> call = RetrofitClient.getInstance().getMyApi().getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> events = response.body();
                EventDAO eventDAO = new EventDAO();
                for (Event event : events) {
                    Event event1 = eventDAO.getById(event.getUuid());
                    if (event.getImage() != null && event.getImage().length() > 0 && event1.getImageBase64().isEmpty()) {
                        DownloadImageAndConvertToBase64 downloader = new DownloadImageAndConvertToBase64(new DownloadImageAndConvertToBase64.OnImageDownloadListener() {
                            @Override
                            public void onImageDownloaded(String base64Image) {
                                event.setImageBase64(base64Image);
                                eventDAO.updateImageBase64(event);
                            }
                        });
                        downloader.execute(event.getImage());
                    }

                    try {
                        if (event1.getUuid() != null && DateParser.isOldest(event1.getUpdatedAt(), event.getUpdatedAt())) {
                            eventDAO.update(event);
                        } else {
                            eventDAO.insert(event);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}