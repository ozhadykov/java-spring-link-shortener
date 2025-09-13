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

        // insert it in DB
        Link newLink = new Link();
        newLink.setShortURL(shortCode);
        newLink.setOriginalURL(link);
        this.linkRepository.save(newLink);

        // return short URL
        return "https://link.short/" + shortCode;
    }

    private String generateShortCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int n = 8;
        String shortURL;

        while (true) {
            // generate random string
            StringBuilder sb = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                int index = (int)(AlphaNumericString.length() * Math.random());
                sb.append(AlphaNumericString.charAt(index));
            }

            shortURL = sb.toString();

            // check if code is there
            Link linkByShortURL = this.linkRepository.findByShortURL(shortURL);
            if (linkByShortURL == null)
                break;
        }

        return shortURL;
    }
}
