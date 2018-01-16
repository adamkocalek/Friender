package com.example.friender.fiender;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<String> ids;
    ArrayList<String> names;
    ArrayList<String> ages;
    ArrayList<String> hobbies;
    ArrayList<String> sexs;
    ArrayList<String> picture;
    ArrayList<String> logins;
    ArrayList<String> passwords;
    ArrayList<String> cities;
    ArrayList<String> reservations;
    ArrayList<String> id_users;
    ArrayList<String> ranks;
    ArrayList<String> avaliables;

    public ListViewAdapter(Activity activity, ArrayList<String> ids, ArrayList<String> names,  ArrayList<String> ages, ArrayList<String> hobbies, ArrayList<String> sexs, ArrayList<String> picture, ArrayList<String> logins, ArrayList<String> passwords, ArrayList<String> cities, ArrayList<String> reservations, ArrayList<String> id_users, ArrayList<String> ranks, ArrayList<String> avaliables) {
        super();
        this.activity = activity;
        this.names = names;
        this.ages = ages;
        this.hobbies = hobbies;
        this.sexs = sexs;
        this.picture = picture;
        this.logins = logins;
        this.passwords = passwords;
        this.cities = cities;
        this.reservations = reservations;
        this.id_users = id_users;
        this.ranks = ranks;
        this.avaliables = avaliables;
    }

    public int getCount() {

        return ids.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView textViewIds;
        TextView textViewSubjects;
        TextView textViewAuthors;
        TextView textViewLikes;
        TextView textViewDates;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        LayoutInflater inflater = activity.getLayoutInflater();
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.list_projects, null);
//            holder = new ViewHolder();
//            holder.textViewIds = (TextView) convertView.findViewById(R.id.textViewIds);
//            holder.textViewSubjects = (TextView) convertView.findViewById(R.id.textViewSubjects);
//            holder.textViewAuthors = (TextView) convertView.findViewById(R.id.textViewAuthors);
//            holder.textViewLikes = (TextView) convertView.findViewById(R.id.textViewLikes);
//            holder.textViewDates = (TextView) convertView.findViewById(R.id.textViewDates);
//            convertView.setTag(holder);
//
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        try {
//            holder.textViewAuthors.setText(authors.get(position));
//            holder.textViewDates.setText(dates.get(position));
//            holder.textViewIds.setText(ids.get(position));
//            holder.textViewSubjects.setText(subjects.get(position));
//            holder.textViewLikes.setText(likes.get(position));
//        } catch (IndexOutOfBoundsException e) {
//            Log.d("BŁĄD: ", "OutOfBound Exception w liście...");
//        }

        return convertView;
    }
}