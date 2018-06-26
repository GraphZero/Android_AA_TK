package com.aatk.pmanager.quotes.web;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("ID")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("link")
    private String link;

    @SerializedName("custom_meta")
    private CustomMeta customMeta;

    static class CustomMeta{

        @SerializedName("Source")
        private String source;

        public CustomMeta(String source) {
            this.source = source;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public Quote(int id, String title, String content, String link, CustomMeta customMeta) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.customMeta = customMeta;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public CustomMeta getCustomMeta() {
        return customMeta;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", customMeta=" + customMeta +
                '}';
    }
}
