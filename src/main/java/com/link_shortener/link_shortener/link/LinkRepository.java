package com.link_shortener.link_shortener.link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    Optional<Link> findByShortURL(String url);
}
