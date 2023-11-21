package com.partiurole.partiurole.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.github.islamkhsh.CardSliderViewPager;
import com.partiurole.partiurole.R;
import com.partiurole.partiurole.adapter.EventsAdapter;
import com.partiurole.partiurole.adapter.FavoritesAdapter;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.model.Event;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    FavoritesAdapter favoritesAdapter;

    public FavoritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ArrayList<Event> events = new ArrayList<Event>();
//        events.add(new Event("1", "Event 1", "2020-01-01", 10.0, 20.0, "All", "Location 1", "City 1", "Region 1", "Maps URL 1", "Event URL 1", "Opening Time 1", "Start Time 1", "Principal Attraction Time 1", "Description 1", "Image URL 1", "Attractions 1", false));
//        events.add(new Event("2", "Event 2", "2020-01-02", 20.0, 30.0, "All", "Location 2", "City 2", "Region 2", "Maps URL 2", "Event URL 2", "Opening Time 2", "Start Time 2", "Principal Attraction Time 2", "Description 2", "Image URL 2", "Attractions 2", true));

        EventDAO eventDAO = new EventDAO();
        ArrayList<Event> events = eventDAO.getAllFavorites();

        favoritesAdapter = new FavoritesAdapter(events);

        RecyclerView recyclerView = view.findViewById(R.id.viewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoritesAdapter);

        SearchView txtSearchFav = (SearchView) view.findViewById(R.id.txtSearchFav);

        txtSearchFav.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                favoritesAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                favoritesAdapter.getFilter().filter(newText);
                return false;
            }
        });

//        cardSliderViewPager.setAdapter(new EventsAdapter(events));

    }
}