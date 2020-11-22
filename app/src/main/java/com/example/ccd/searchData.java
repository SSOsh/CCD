package com.example.ccd;

public class searchData {
    private String historyText;

    public searchData(String bookText) {
        this.historyText = bookText;
    }

    public searchData() {}

    public String getHistoryText() { return historyText; }

    public void setHistoryText(String historyText) { this.historyText = historyText; }
}
