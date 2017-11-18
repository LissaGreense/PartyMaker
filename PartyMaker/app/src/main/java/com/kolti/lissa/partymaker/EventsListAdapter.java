package com.kolti.lissa.partymaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by t450 on 18.11.2017.
 */

public class EventsListAdapter extends BaseAdapter{

    private ArrayList<Event> eventsList;
    private Context context;

    public EventsListAdapter(Context context, ArrayList<Event> eventsList){
        this.eventsList = eventsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public Object getItem(int i) {
        return eventsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        if(i > 0 && i < eventsList.size()-1)
            return i;
        else
            return 0;
            //?? 0 or -1
    }

    private static class ViewHolder {
        TextView eventName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.event_row_item, parent, false);
            viewHolder.eventName = convertView.findViewById(R.id.event_name);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.eventName.setText(eventsList.get(position).getName());
        // Return the completed view to render on screen
        return result;
    }
}
