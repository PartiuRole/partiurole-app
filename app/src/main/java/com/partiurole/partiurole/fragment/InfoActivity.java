package com.partiurole.partiurole.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.partiurole.partiurole.R;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.model.Event;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
            txtDate.setText(event.getDate());

            TextView txtInfoAttraction = (TextView) findViewById(R.id.txtInfoAttraction);
            txtInfoAttraction.setText(event.getAttractions());

            TextView txtInfoGeneralInfo = (TextView) findViewById(R.id.txtInfoGeneralInfo);
            txtInfoGeneralInfo.setText(event.getDescription());

//            TextView txtPrice = (TextView) findViewById(R.id.txtPrice);
//            txtPrice.setText("-");
//
//            if (event.getMinPrice() > 0 && event.getMaxPrice() > 0) {
//                txtPrice.setText(event.getMinPrice().toString() + " - " + event.getMaxPrice().toString());
//                if (event.getMinPrice() == event.getMaxPrice())
//                    txtPrice.setText(event.getMinPrice().toString());
//            }

            ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
            imgBack.setOnClickListener(v -> finish());

//            ImageView imgEvent = (ImageView) findViewById(R.id.imgEvent);
//            Bitmap bitmap = null;
//            try {
//                if (event.getImageUrl() != null) {
////                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(event.getImageUrl()).getContent());
//                    String url = "https://www.melhoresdestinos.com.br/wp-content/uploads/2023/02/lollapalooza-brasil-hotel-passagem-aerea-capa.jpg";
//                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
//                } else {
//                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.event);
//                }
//                imgEvent.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

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
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getEventUrl()));
                startActivity(browserIntent);
            });

        }



    }
}