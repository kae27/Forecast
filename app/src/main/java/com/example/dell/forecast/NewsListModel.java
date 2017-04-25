package com.example.dell.forecast;

/**
 * Created by Dell on 4/25/2017.
 */

public class NewsListModel
{

    String title_text;
    String content_text;

    public NewsListModel(String title_text, String content_text) {
        this.title_text = title_text;
        this.content_text = content_text;
    }

    public String getTitle_text() {
        return title_text;
    }

    public void setTitle_text(String title_text) {
        this.title_text = title_text;
    }

    public String getContent_text() {
        return content_text;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text;
    }

}
