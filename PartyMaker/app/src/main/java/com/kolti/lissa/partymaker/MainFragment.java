package com.kolti.lissa.partymaker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.GraphRequest.TAG;

/**
 * Created by t450 on 18.11.2017.
 */

public class MainFragment extends Fragment {

    public static final String CHOSEN_EVENT_DETAILS_ID = "2";

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);

        final ListView eventsListView = view.findViewById(R.id.events_list_view);
        final EventsListAdapter adapter = new EventsListAdapter(getContext(), new ArrayList<Event>());
        eventsListView.setAdapter(adapter);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        Button button = view.findViewById(R.id.create_party);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject events = null;
                    events = loadEvent("events");
                JSONArray eventsJSONArray = null;
                ArrayList<Event> eventsList = new ArrayList<>();
                try {
                    eventsJSONArray= events.getJSONArray("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                    String description = "error";
                    String name = "error";
                    String address = "adres";
                    Date date = new Date();
                    date.setTime(349732);
                    for(int i = 0 ; i < eventsJSONArray.length(); i++) {
                        try {
                                name = eventsJSONArray.getJSONObject(i).getString("name");
                                description = eventsJSONArray.getJSONObject(i).getString("description");
                                address = "address";
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        eventsList.add(new Event(name, description, address, date, "HOST KAPPA"));
                        adapter.setEventsList(eventsList);
                    }
                    adapter.notifyDataSetChanged();
                    Log.e("alekeks",events.toString());
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


    private JSONObject loadEvent(String id){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String json = sharedPreferences.getString(id, "") ;
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
