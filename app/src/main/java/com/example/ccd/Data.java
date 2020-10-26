package com.example.ccd;

public class Data {
    private String bookTitle;
    private String author;
    private String starRating;
    private int bookCoverImg;

    public String getBookTitle() {return bookTitle;}
    public String getAuthor() {return author;}
    public String getStarRating() {return starRating;}
    public int getBookCoverImg() {return bookCoverImg;}

    public void setBookTitle(String bookTitle) {this.bookTitle = bookTitle;}
    public void setAuthor(String author) {this.author = author;}
    public void setStarRating(String starRating) {this.starRating = starRating;}
    public void setBookCoverImg(int bookCoverImg) {this.bookCoverImg = bookCoverImg;}
}
