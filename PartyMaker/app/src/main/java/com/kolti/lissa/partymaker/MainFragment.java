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
        for(int i = 0; i < 15; i++)
            events.add(new Event("test" +i));

        EventsListAdapter adapter = new EventsListAdapter(getContext(), events);
        eventsList.setAdapter(adapter);

        return view;
    }
}
