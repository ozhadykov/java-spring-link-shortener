package com.link_shortener.link_shortener.link;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class CreateLinkRequest {
    @NotBlank(message = "URL can't be empty")
    @URL(message = "Invalid URL format")
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
