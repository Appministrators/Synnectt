package com.appministrator.synnectt.models;

public class event {
    private String name, venue, type, about, date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public event(String name, String venue, String type, String about, String date) {
        this.name = name;
        this.venue = venue;
        this.type = type;
        this.about = about;
        this.date = date;
    }

    public event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}