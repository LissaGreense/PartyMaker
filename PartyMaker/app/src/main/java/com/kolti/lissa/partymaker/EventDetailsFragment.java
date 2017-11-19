package com.kolti.lissa.partymaker;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        final Event event = loadEvent(MainFragment.CHOSEN_EVENT_DETAILS_ID);

        TextView title = view.findViewById(R.id.event_details_title);
        final Button description = view.findViewById(R.id.event_details_description);
        TextView address = view.findViewById(R.id.event_details_address);
        TextView date = view.findViewById(R.id.event_details_date);

        title.setText(event.getName());
        description.setText(event.getDescription());
        address.setText(event.getAddress());
        date.setText(event.getFormattedDate());

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Description")
                        .setMessage(event.getDescription() +"")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        return view;
    }

    private Event loadEvent(String id){
        SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(id, "");
        return gson.fromJson(json, Event.class);
    }

}
