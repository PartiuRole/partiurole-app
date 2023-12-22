package com.partiurole.partiurole.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.islamkhsh.CardSliderAdapter;
import com.partiurole.partiurole.R;
import com.partiurole.partiurole.fragment.InfoActivity;
import com.partiurole.partiurole.model.Event;
import com.partiurole.partiurole.util.DateParser;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

public class EventsAdapter extends CardSliderAdapter<EventsAdapter.EventViewHolder> {

    private ArrayList<Event> events;

    public EventsAdapter(ArrayList<Event> events){
        this.events = events;
    }

    @Override
    public int getItemCount(){
        return events.size();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void bindVH(@NonNull EventsAdapter.EventViewHolder eventViewHolder, int i) {
        Event event = events.get(i);

        TextView txtName = (TextView) eventViewHolder.itemView.findViewById(R.id.txtName);
        txtName.setText(event.getName());

        TextView txtLocation = (TextView) eventViewHolder.itemView.findViewById(R.id.txtLocation);
        txtLocation.setText(event.getLocation());

        TextView txtDate = (TextView) eventViewHolder.itemView.findViewById(R.id.txtDate);
        try {
            txtDate.setText(DateParser.formatDate(event.getDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        TextView txtPrice = (TextView) eventViewHolder.itemView.findViewById(R.id.txtPrice);
        txtPrice.setText("Consulte");
        if (Double.parseDouble(event.getPrice().replace(",", ".")) > 0)
            txtPrice.setText(event.getPrice());

        if (!event.getImageBase64().isEmpty()) {
            ImageView imgEvent = (ImageView) eventViewHolder.itemView.findViewById(R.id.imgEvent);
            byte[] decodedString = Base64.decode(event.getImageBase64(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgEvent.setImageBitmap(decodedByte);
        }

        eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open InfoActivity
                Intent myIntent = new Intent(eventViewHolder.itemView.getContext(), InfoActivity.class);
                // pass event
                myIntent.putExtra("eventID", event.getUuid());
                eventViewHolder.itemView.getContext().startActivity(myIntent);

            }
        });

    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(View view){
            super(view);
        }
    }
}
