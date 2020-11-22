package com.example.ccd;

public class bsResultData {
    private String bsrTitle;
    private String bsrAuthor;
    private int bsrImg;

    bsResultData(String bsrTitle, String bsrAuthor, int bsrImg) {
        this.bsrTitle = bsrTitle;
        this.bsrAuthor = bsrAuthor;
        this.bsrImg = bsrImg;
    }

    public bsResultData() {

    }

    public String getBsrTitle() { return bsrTitle; }
    public String getBsrAuthor() { return bsrAuthor; }
    public int getBsrImg() { return bsrImg; }

    public void setBsrTitle(String bsrTitle) { this.bsrTitle = bsrTitle; }
    public void setBsrAuthor(String bsrAuthor) { this.bsrAuthor = bsrAuthor; }
    public void setBsrImg(int bsrImg) { this.bsrImg = bsrImg; }
}
