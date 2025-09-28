package com.link_shortener.link_shortener.link;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {

    private final LinkRepository linkRepository;
    private final Hashids hashids;

    @Value("${app.base-url}")
    private String baseUrl;

    public LinkShortenerService(LinkRepository linkRepository, Hashids hashids) {
        this.linkRepository = linkRepository;
        this.hashids = hashids;
    }

    public String getOriginalLink(String shortLinkId) {
        return this.linkRepository.findByShortURL(shortLinkId)
                .map(Link::getOriginalURL)
                .orElseThrow(() -> new LinkNotFoundException("Link not found"));
    }

    public String createShortLink(String link) {
        // Create a new link object and save it to the database to get its ID
        Link newLink = new Link();
        newLink.setOriginalURL(link);
        newLink = linkRepository.save(newLink);

        // Encode the database ID to create the short URL
        String shortCode = hashids.encode(newLink.getId());
        newLink.setShortURL(shortCode);

        // Save the updated link with the short code
        linkRepository.save(newLink);

        // Return the full short URL
        return this.baseUrl + "r/" + shortCode;
    }
}
