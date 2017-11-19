package com.kolti.lissa.partymaker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by t450 on 19.11.2017.
 */

public class EventDetailsFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_details_fragment, container, false);

        Event event = loadEvent(MainFragment.CHOSEN_EVENT_DETAILS_ID);

        TextView title = view.findViewById(R.id.event_details_title);
        TextView description = view.findViewById(R.id.event_details_description);
        TextView address = view.findViewById(R.id.event_details_address);
        TextView date = view.findViewById(R.id.event_details_date);

        title.setText(event.getName());
        description.setText(event.getDescription());
        address.setText(event.getAddress());
        date.setText(event.getFormattedDate());

        return view;
    }

    private Event loadEvent(String id){
        SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(id, "");
        return gson.fromJson(json, Event.class);
    }

}
