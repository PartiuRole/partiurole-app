package com.partiurole.partiurole.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.islamkhsh.CardSliderViewPager;
import com.partiurole.partiurole.R;
import com.partiurole.partiurole.adapter.EventsAdapter;
import com.partiurole.partiurole.dao.EventDAO;
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

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        EventDAO eventDAO = new EventDAO();
        ArrayList<Event> events = eventDAO.getAll("");
        ArrayList<Event> eventsFree = eventDAO.getAllFree();
        ArrayList<Event> eventsMostViewed = eventDAO.getRandom();

        CardSliderViewPager sliderNearYou = (CardSliderViewPager) getView().findViewById(R.id.sliderNearYou);
        sliderNearYou.setAdapter(new EventsAdapter(events));

        CardSliderViewPager sliderMostViewed = (CardSliderViewPager) getView().findViewById(R.id.sliderMostViewed);
        sliderMostViewed.setAdapter(new EventsAdapter(eventsMostViewed));

        CardSliderViewPager sliderFree = (CardSliderViewPager) getView().findViewById(R.id.sliderFree);
        sliderFree.setAdapter(new EventsAdapter(eventsFree));

        SearchView txtSearchFav = (SearchView) view.findViewById(R.id.txtSearchFavHome);
        txtSearchFav.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sliderNearYou.setAdapter(getAllAdapter(newText));
                return false;
            }
        });

    }

    private EventsAdapter getAllAdapter(String query) {
        EventDAO eventDAO = new EventDAO();
        ArrayList<Event> events = eventDAO.getAll(query);
        return new EventsAdapter(events);
    }
}