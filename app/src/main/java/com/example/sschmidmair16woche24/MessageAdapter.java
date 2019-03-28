package com.example.sschmidmair16woche24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter {
    private final String TAG = "BRWTalk";
    private static final String collectionName = "messages";
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public MessageAdapter(Context context, ArrayList notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Message m = (Message) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message, parent, false);
        }

        TextView name_TV = convertView.findViewById(R.id.name_TV);
        TextView date_TV = convertView.findViewById(R.id.date_TV);
        TextView message_TV = convertView.findViewById(R.id.message_TV);

        name_TV.setText(m.getUsername());
        date_TV.setText(sdf.format(m.getDate()));
        message_TV.setText(m.getMessage());

        return convertView;

    }
}