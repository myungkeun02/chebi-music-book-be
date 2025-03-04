package com.example.chebimusicbookbe.domain.music.repository;

import com.example.chebimusicbookbe.domain.music.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByTitle(String title);
}
