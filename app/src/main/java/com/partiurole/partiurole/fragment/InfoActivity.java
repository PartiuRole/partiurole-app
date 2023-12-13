package com.partiurole.partiurole.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.partiurole.partiurole.R;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.model.Event;
import com.partiurole.partiurole.util.DateParser;

import java.text.ParseException;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // get Event extra
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // get event
            String eventID = extras.getString("eventID");
            EventDAO eventDAO = new EventDAO();
            Event event = eventDAO.getById(eventID);

            TextView txtName = (TextView) findViewById(R.id.txtInfoName);
            txtName.setText(event.getName());

            TextView txtLocation = (TextView) findViewById(R.id.txtInfoLocation);
            txtLocation.setText(event.getLocation());

            TextView txtDate = (TextView) findViewById(R.id.txtInfoDate);
            try {
                txtDate.setText(DateParser.formatDate(event.getDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            TextView txtInfoGeneralInfo = (TextView) findViewById(R.id.txtInfoGeneralInfo);
            txtInfoGeneralInfo.setText(event.getDescription());

            ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
            imgBack.setOnClickListener(v -> finish());

            if (!event.getImageBase64().isEmpty()) {
                ImageView imgEvent = (ImageView) findViewById(R.id.imgEventInfo);
                byte[] decodedString = Base64.decode(event.getImageBase64(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imgEvent.setImageBitmap(decodedByte);
            }

            ImageView imgFavorites = (ImageView) findViewById(R.id.imgFavorites);
            if (event.getIsFavorite()) {
                imgFavorites.setImageResource(R.drawable.ic_heart);
            } else {
                imgFavorites.setImageResource(R.drawable.ic_heart_outline);
            }

            imgFavorites.setOnClickListener(v -> {
                if (event.getIsFavorite()) {
                    imgFavorites.setImageResource(R.drawable.ic_heart_outline);
                    event.setIsFavorite(false);
                } else {
                    imgFavorites.setImageResource(R.drawable.ic_heart);
                    event.setIsFavorite(true);
                }
                eventDAO.update(event);
            });

            FloatingActionButton fabTicket = (FloatingActionButton) findViewById(R.id.fabTicket);
            fabTicket.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getUrl()));
                startActivity(browserIntent);
            });

        }
    }
}