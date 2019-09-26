package com.example.moviereviewsystem;

public class Movie_Details {

    String Category, Title, Description;
    int releaseYear, rating;
    //ImageIcon image;

    Movie_Details(String Category, String Title, int releaseYear, int rating, String Description)
    {
        this.Category=Category;
        this.Title=Title;
        this.releaseYear=releaseYear;
        this.rating=rating;
        this.Description=Description;
    }

}
