package com.example.ccd;

public class chatbotData {
    chatbotData() {
        this.content = "";
        this.viewType = -1;
    }
    chatbotData(String content, int viewType) {
        this.content = content;
        this.viewType = viewType;
    }

    String content;
    int viewType;

    String getContent() {
        return content;
    }

    int getViewType() {
        return viewType;
    }

    void setContent(String content) {
        this.content = content;
    }

    void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
