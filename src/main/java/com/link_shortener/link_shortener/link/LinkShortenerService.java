package com.link_shortener.link_shortener.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkShortenerService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String createShortLink(String link) {
        String shortCode = this.generateShortCode();
        return "shortLink";
    }

    private String generateShortCode() {
        return "shortCode";
    }
}
