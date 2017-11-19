package com.kolti.lissa.partymaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by t450 on 18.11.2017.
 */

public class MainFragment extends Fragment {

    public static final String CHOSEN_EVENT_DETAILS_ID = "";

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);

        final ListView eventsList = view.findViewById(R.id.events_list_view);
        final ArrayList<Event> events = new ArrayList<>();
        Date date = new Date();
        date.setYear(2017);
        date.setMonth(3);
        date.setDate(20);
        for(int i = 0; i < 15; i++)
            events.add(new Event("party " +i,
                    "Opis opis moj wspanialy opis",
                    "tutaj adresik",
                    date,
                    "Kamil"));

        final EventsListAdapter adapter = new EventsListAdapter(getContext(), events);
        eventsList.setAdapter(adapter);
        eventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                saveEvent(CHOSEN_EVENT_DETAILS_ID, (Event) adapter.getItem(i));

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, new EventDetailsFragment())
                        .addToBackStack("event_details")
                        .commit();
            }
        });

        return view;
    }

    private void saveEvent(String id, Event event){
        SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(event);
        prefsEditor.putString(id, json);
        prefsEditor.apply();
    }


}
