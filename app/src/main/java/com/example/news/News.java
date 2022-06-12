package com.example.news;

public class News {
    int id;
    String url;
    String description;
    String heading;
    public News(int id,String url,String description,String heading)
    {
        this.id=id;
        this.url=url;
        this.description=description;
        this.heading=heading;
    }
    public int getId() {
        return this.id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getHeading() {
        return heading;
    }

}
