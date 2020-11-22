package com.example.ccd;

public class resultData {
    private String rTitle;
    private String rAuthor;
    private int rImg;

    resultData(String rTitle, String rAuthor, int rImg) {
        this.rTitle = rTitle;
        this.rAuthor = rAuthor;
        this.rImg = rImg;
    }

    public resultData() {

    }

    public String getrTitle() { return rTitle; }
    public String getrAuthor() { return rAuthor; }
    public int getrImg() { return rImg; }

    public void setrTitle(String rTitle) { this.rTitle = rTitle; }
    public void setrAuthor(String rAuthor) { this.rAuthor = rAuthor; }
    public void setrImg(int rImg) { this.rImg = rImg; }
}
