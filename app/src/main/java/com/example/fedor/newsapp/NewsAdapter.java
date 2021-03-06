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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.fedor.newsapp.R.id.date;

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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news story at the given position in the list of news
        News currentNews = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        // Display the title of the current news story in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID category
        TextView categoryView = (TextView) convertView.findViewById(R.id.category);
        // Display the location of the current news story in that TextView
        categoryView.setText(currentNews.getSection());

        // Find the TextView with view ID author
        TextView authorView = (TextView) convertView.findViewById(R.id.author);
        // Display the author of the current news story in that TextView
        authorView.setText(currentNews.getAuthor());

        // Get the web publication string from the news story object.
        String webPublicationDate = currentNews.getWebPublicationDate();

        // Create new String object from the ISO 8601 date and time info of the news story
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dateInString = webPublicationDate;

        try {
            // Create a new Date object from the ISO 8601 date and time info of the news story
            Date dateObject = formatter.parse(dateInString.replaceAll("Z$", "+0000"));
            // Find the TextView with view ID date
            TextView dateView = (TextView) convertView.findViewById(date);
            // Format the date string (i.e. "Mar 3, 1984")
            String formattedDate = formatDate(dateObject);
            // Display the date of the current earthquake in that TextView
            dateView.setText(formattedDate);

            // Find the TextView with view ID time
            TextView timeView = (TextView) convertView.findViewById(R.id.time);
            // Format the time string (i.e. "4:30PM")
            String formattedTime = formatTime(dateObject);
            // Display the time of the current earthquake in that TextView
            timeView.setText(formattedTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Return the list item view that is now showing the appropriate data
        return convertView;
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