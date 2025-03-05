package com.example.chebimusicbookbe.domain.artist.repository;

import com.example.chebimusicbookbe.domain.artist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByName(String name);
}
