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

/**
 * An {@link News} object contains information related to a single news story.
 */
public class News {

    /** Title of the news story */
    private String mTitle;

    /** Author of the news story */
    private String mAuthor;

    /** Category the news story can be applied to */
    private String mSection;

    /** Publication Date of the news story */
    private String mWebPublicationDate;

    /** Website URL of the news story */
    private String mUrl;

    /**
     * Constructs a new {@link News} object.
     *
     * @param title is the name of the book
     * @param author is the author of the book
     * @param section is the category the news story can be applied to
     * @param webPublicationDate is the web publication date of the news story
     * @param url is the website URL to the whole story
     */
    public News(String title, String author, String section, String webPublicationDate, String url) {
        mTitle = title;
        mAuthor = author;
        mSection = section;
        mWebPublicationDate = webPublicationDate;
        mUrl = url;
    }

    /**
     * Returns the name of the book.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the author of the book.
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Returns the category the news story can be applied to.
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Returns the web publication date of the news story.
     */
    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}