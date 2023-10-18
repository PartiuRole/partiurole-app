package com.partiurole.partiurole.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.partiurole.partiurole.R;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.model.Event;

import org.w3c.dom.Text;

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
//            imgEvent.setImageResource(event.getImage());

            ImageView imgFavorites = (ImageView) findViewById(R.id.imgFavorites);
            if (event.getIsFavorite()) {
                imgFavorites.setImageResource(R.drawable.ic_heart_outline);
            } else {
                imgFavorites.setImageResource(R.drawable.ic_heart);
            }
            imgFavorites.setOnClickListener(v -> {
                if (event.getIsFavorite()) {
                    imgFavorites.setImageResource(R.drawable.ic_heart);
                    event.setIsFavorite(false);
                } else {
                    imgFavorites.setImageResource(R.drawable.ic_heart_outline);
                    event.setIsFavorite(true);
                }
                eventDAO.update(event);
            });
        }



    }
}