/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.fedor.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news story
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsAdapter.class.getName();

    /**
     * The part of the date string from the Guardian service that we use to split it into two halfs.
     */
    private static final String TIME_SEPARATOR = "T";

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param newsC is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsC) {
        super(context, 0, newsC);
    }

    /**
     * Returns a list item view that displays information about the news story at the given position
     * in the list of news.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news story at the given position in the list of news
        News currentNews = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current news story in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID category
        TextView categoryView = (TextView) listItemView.findViewById(R.id.category);
        // Display the location of the current news story in that TextView
        categoryView.setText(currentNews.getSection());

        // Find the TextView with view ID author
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        // Display the author of the current news story in that TextView
        authorView.setText(currentNews.getAuthor());

        // Get the web publication string from the news story object.
        String webPublicationDate = currentNews.getWebPublicationDate();

        // Split the string into different parts (as an array of Strings)
        // based on the "T" character. We expect an array of 2 Strings, where
        // the first String will be the date and the second String will be the exact time.
        String[] parts = webPublicationDate.split(TIME_SEPARATOR);
        // Date
        String date = parts[0];
        // Exact time
        String time = parts[1];

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Display the date of the current news story in that TextView
        dateView.setText(date);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Display the time of the current news story in that TextView
        timeView.setText(time);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateObject);
    }
}