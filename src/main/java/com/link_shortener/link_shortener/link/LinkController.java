package com.link_shortener.link_shortener.link;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/r")
public class LinkController {

    private final LinkShortenerService linkShortenerService;

    @Autowired
    public LinkController(LinkShortenerService linkShortenerService) {
        this.linkShortenerService = linkShortenerService;
    }

    @PostMapping("/create-link")
    public ResponseEntity<CreateLinkResponse> createLink(@Valid @RequestBody CreateLinkRequest link) {
        String shortLink = this.linkShortenerService.createShortLink(link.getLink());
        CreateLinkResponse response = new CreateLinkResponse(shortLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @GetMapping("/{linkId}")
    public RedirectView getLink(@PathVariable String linkId) {
        String originalURL = this.linkShortenerService.getOriginalLink(linkId);
        return new RedirectView(originalURL);
    }

}
