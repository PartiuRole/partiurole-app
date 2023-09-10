package com.partiurole.partiurole.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.partiurole.partiurole.R;
import com.partiurole.partiurole.model.Event;

public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAMEVENT = "event";

    // TODO: Rename and change types of parameters
    private Event event;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(Event paramEvent) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
//        args.putSerializable(ARG_PARAMEVENT, paramEvent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            event = (Event) getArguments().getSerializable(ARG_PARAMEVENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}