package com.link_shortener.link_shortener.link;

public class CreateLinkResponse {
    private String shortUrl;

    public CreateLinkResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
