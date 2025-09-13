package com.link_shortener.link_shortener.link;

public class CreateLinkRequest {
    private String link;

    // A no-argument constructor, getters, and setters are needed for the JSON library (Jackson) to work.
    public CreateLinkRequest() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
