package com.amg.android.recyclerviewtutorial.data.model;

import org.json.JSONObject;

public class ImgurImage {

    public String title;
    public String link;

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    private void setLink(String link) {
        this.link = link;
    }

    public boolean isValid(){
        return title != null && title.length() > 0 &&
                link != null && link.length() > 0;
    }

    public void parse(JSONObject jsonObject) {
        if (jsonObject != null) {
            setTitle(jsonObject.optString("title"));
            setLink(jsonObject.optString("link"));
        }
    }

}
