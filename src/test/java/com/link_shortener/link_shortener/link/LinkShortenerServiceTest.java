package com.link_shortener.link_shortener.link;

import org.hashids.Hashids;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkShortenerServiceTest {

    @Mock
    private LinkRepository linkRepository;

    @Mock
    private Hashids hashids;

    @InjectMocks
    private LinkShortenerService linkShortenerService;

    @BeforeEach
    void setUp() {
        // Set the baseUrl field using reflection, as it's not injected via constructor
        ReflectionTestUtils.setField(linkShortenerService, "baseUrl", "http://localhost:8080/");
    }

    @Test
    void createShortLink_should_return_a_valid_short_link() {
        // Given (Setup)
        String originalUrl = "https://www.google.com";
        long linkId = 1L;
        String shortCode = "gYvA7";

        Link linkWithoutId = new Link();
        linkWithoutId.setOriginalURL(originalUrl);

        Link linkWithId = new Link();
        linkWithId.setId((int) linkId);
        linkWithId.setOriginalURL(originalUrl);

        // Define the behavior of our mocks
        when(linkRepository.save(any(Link.class)))
                .thenReturn(linkWithId) // First save returns the link with an ID
                .thenReturn(null); // Second save, we don't care about the return value

        when(hashids.encode(linkId)).thenReturn(shortCode);

        // When (Action)
        String result = linkShortenerService.createShortLink(originalUrl);

        // Then (Verification)
        assertThat(result).isEqualTo("http://localhost:8080/r/" + shortCode);

        // Verify that the save method was called twice
        verify(linkRepository, org.mockito.Mockito.times(2)).save(any(Link.class));

        // Verify that the encode method was called once with the correct ID
        verify(hashids).encode(linkId);
    }
}
