package com.kolti.lissa.partymaker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by kajetan on 19.11.17.
 */

public class DownloadEventsFromFB extends AsyncTask<Void, Void, ArrayList<Event>> {

    private Context context;
    private CallbackManager mCallbackManager;

    public DownloadEventsFromFB(Context context){
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    JSONObject events;
    ArrayList<Event> eventsList;
    private final ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Downloading events");
        dialog.setIndeterminate(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected ArrayList<Event> doInBackground(Void... params) {


        mCallbackManager = CallbackManager.Factory.create();

        // Set the initial permissions to request from the user while logging in
        //mLoginButton.setReadPermissions(Arrays.asList(EMAIL, USER_POSTS, USER_EVENTS));
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                //Log.e(TAG,object.toString());
                //Log.e(TAG,response.toString());

                try {
                    //userId = object.getString("id");
                    //profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
                    if (object.has("first_name"))
                        //firstName = object.getString("first_name");
                        if (object.has("last_name"))
                            //lastName = object.getString("last_name");
                            if (object.has("email"))
                                //email = object.getString("email");
                                if (object.has("events"))
                                    events = object.getJSONObject("events");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, birthday, events");
        request.setParameters(parameters);
        request.executeAndWait();

        JSONArray eventsJSONArray = null;
        eventsList = new ArrayList<>();
        try {
            eventsJSONArray = events.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert eventsJSONArray != null;

        for (int i=0; i < eventsJSONArray.length(); i++) {
            JSONObject jsonObject = null;
            String description = "error";
            String name = "error";
            String address = "adres";
            Date date = new Date();
            date.setTime(349732);
            try {
                jsonObject = eventsJSONArray.getJSONObject(i);
                description = jsonObject.getString("description");
                name = jsonObject.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            eventsList.add(new Event(name, description, address, date, "HOST KAPPA"));
        }

        return eventsList;

    }

    protected void onPostExecute(ArrayList<Event> events) {
        // do further things with JSONObject as this runs on UI thread.
        dialog.dismiss();
    }

}
