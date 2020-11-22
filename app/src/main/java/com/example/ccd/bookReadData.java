package com.example.ccd;

public class bookReadData {
    private String bingTitle;
    private String bingAuthor;
    private int bingImg;

    public bookReadData(String brTitleV, String brAuthorV, String brImgV) {
        this.bingTitle = brTitleV;
        this.bingAuthor = brAuthorV;
        this.bingImg = Integer.parseInt(brImgV);
    }
    public bookReadData() {
        bingTitle = "";
        bingAuthor = "";
        bingImg = 0;
    }

    public String getBingTitle() {return bingTitle;}
    public String getBingAuthor() {return bingAuthor;}
    public int getBingImg() {return bingImg;}

    public void setBingTitle(String bingTitle) {this.bingTitle = bingTitle;}
    public void setBingAuthor(String bingAuthor) {this.bingAuthor = bingAuthor;}
    public void setBingImg(int bingImg) {this.bingImg = bingImg;}
}
