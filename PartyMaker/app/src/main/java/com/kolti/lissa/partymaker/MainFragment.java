package com.kolti.lissa.partymaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by t450 on 18.11.2017.
 */

public class MainFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);

        ListView eventsList = view.findViewById(R.id.events_list_view);
        ArrayList<Event> events = new ArrayList<>();
        Date date = new Date();
        date.setYear(2017);
        date.setMonth(3);
        date.setDate(20);
        for(int i = 0; i < 15; i++)
            events.add(new Event("party " +i, date, "Kamil"));

        EventsListAdapter adapter = new EventsListAdapter(getContext(), events);
        eventsList.setAdapter(adapter);

        return view;
    }


}
