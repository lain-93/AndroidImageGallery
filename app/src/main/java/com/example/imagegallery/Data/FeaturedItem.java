package com.example.imagegallery.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FeaturedItem implements Serializable {
    private String id;
    private Map<String, String> urls = new HashMap<String, String>();
    private User user = new User();

    public FeaturedItem(String id, Map<String, String> imageUrl, User userInfo) {
        super();
        this.id = id;
        this.urls = imageUrl;
        this.user = userInfo;
    }

    public String getId() {
        return id;
    }

    public String getThumb() {

        return urls.get("thumb");
    }

    public String getSmallImage() {
        return urls.get("small");
    }

    public String getUser() {
        return user.getId();
    }
}
