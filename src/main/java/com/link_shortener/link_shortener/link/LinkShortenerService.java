package com.link_shortener.link_shortener.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {

    private final LinkRepository linkRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    @Autowired
    public LinkShortenerService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public String getOriginalLink(String shortLinkId) {
        return this.linkRepository.findByShortURL(shortLinkId)
                .map(Link::getOriginalURL)
                .orElseThrow(() -> new LinkNotFoundException("Link not found"));
    }

    public String createShortLink(String link) {
        String shortCode = this.generateShortCode();

        // insert it in DB
        Link newLink = new Link();
        newLink.setShortURL(shortCode);
        newLink.setOriginalURL(link);
        this.linkRepository.save(newLink);

        // return short URL
        return this.baseUrl + "r/" + shortCode;
    }

    private String generateShortCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int n = 8;
        String shortURL;

        // generate random string
        do {
            StringBuilder sb = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                int index = (int)(AlphaNumericString.length() * Math.random());
                sb.append(AlphaNumericString.charAt(index));
            }
            shortURL = sb.toString();
        } while (this.linkRepository.findByShortURL(shortURL).isPresent());

        return shortURL;
    }
}
