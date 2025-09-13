package com.link_shortener.link_shortener.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

    private final LinkShortenerService linkShortenerService;

    @Autowired
    public LinkController(LinkShortenerService linkShortenerService) {
        this.linkShortenerService = linkShortenerService;
    }


    @PostMapping("/create-link")
    public ResponseEntity<CreateLinkResponse> createLink(@RequestBody CreateLinkRequest link) {
        String shortLink = this.linkShortenerService.createShortLink(link.getLink());
        CreateLinkResponse response = new CreateLinkResponse(shortLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
