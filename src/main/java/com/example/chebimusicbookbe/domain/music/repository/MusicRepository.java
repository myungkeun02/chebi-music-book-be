package com.example.chebimusicbookbe.domain.music.repository;

import com.example.chebimusicbookbe.domain.artist.model.Artist;
import com.example.chebimusicbookbe.domain.music.model.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByTitle(String title);
    Page<Music> findByArtist(String artist, Pageable pageable);
    Page<Music> findByCategory(String category, Pageable pageable);
    Page<Music> findByTitleContaining(String keyword, Pageable pageable);
}
