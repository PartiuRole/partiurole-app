package com.partiurole.partiurole.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.partiurole.partiurole.R;
import com.partiurole.partiurole.adapter.FavoritesAdapter;
import com.partiurole.partiurole.dao.EventDAO;
import com.partiurole.partiurole.model.Event;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    FavoritesAdapter favoritesAdapter;

    public FavoritesFragment() {}

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

    private FavoritesAdapter getFavoritesAdapter(String query) {
        EventDAO eventDAO = new EventDAO();
        ArrayList<Event> events = eventDAO.getAllFavorites(query);
        favoritesAdapter = new FavoritesAdapter(events);
        return favoritesAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.viewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true); //teste
        recyclerView.setAdapter(getFavoritesAdapter(""));

        SearchView txtSearchFav = (SearchView) view.findViewById(R.id.txtSearchFav);
        txtSearchFav.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.setAdapter(getFavoritesAdapter(newText));
                return false;
            }
        });
    }
}