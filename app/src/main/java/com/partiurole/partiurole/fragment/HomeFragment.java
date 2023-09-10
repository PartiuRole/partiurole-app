package com.partiurole.partiurole.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.islamkhsh.CardSliderViewPager;
import com.partiurole.partiurole.R;
import com.partiurole.partiurole.adapter.EventsAdapter;
import com.partiurole.partiurole.model.Event;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event("1", "Event 1", "2020-01-01", 10.0, 20.0, "All", "Location 1", "City 1", "Region 1", "Maps URL 1", "Event URL 1", "Opening Time 1", "Start Time 1", "Principal Attraction Time 1", "Description 1", "Image URL 1", "Attractions 1"));
        events.add(new Event("2", "Event 2", "2020-01-02", 20.0, 30.0, "All", "Location 2", "City 2", "Region 2", "Maps URL 2", "Event URL 2", "Opening Time 2", "Start Time 2", "Principal Attraction Time 2", "Description 2", "Image URL 2", "Attractions 2"));
        // add items to arraylist

        CardSliderViewPager sliderNearYou = (CardSliderViewPager) getView().findViewById(R.id.sliderNearYou);
        sliderNearYou.setAdapter(new EventsAdapter(events));

        CardSliderViewPager sliderMostViewed = (CardSliderViewPager) getView().findViewById(R.id.sliderMostViewed);
        sliderMostViewed.setAdapter(new EventsAdapter(events));

        CardSliderViewPager sliderFree = (CardSliderViewPager) getView().findViewById(R.id.sliderFree);
        sliderFree.setAdapter(new EventsAdapter(events));

        // onclick listener for each item of arraylist open infoFragment with the Event
        getView().findViewById(R.id.sliderNearYou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open infoFragment with the Event

            }
        });
    }

    // onclick listener for each item of arraylist open infoFragment with the Event


}