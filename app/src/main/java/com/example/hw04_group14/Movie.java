package com.example.hw04_group14;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String description;
    private String genre;
    private int rating;
    private int year;
    private String url;

    public Movie() {
    }

    public Movie(String title, String description, String genre, int rating, int year, String url) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", url='" + url + '\'' +
                '}';
    }
}
