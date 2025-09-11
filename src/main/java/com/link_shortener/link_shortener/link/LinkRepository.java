package com.link_shortener.link_shortener.link;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    Link findByShortURL(String url);
}
