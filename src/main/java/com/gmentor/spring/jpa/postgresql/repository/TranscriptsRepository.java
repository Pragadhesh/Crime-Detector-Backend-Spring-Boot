package com.gmentor.spring.jpa.postgresql.repository;

import com.gmentor.spring.jpa.postgresql.model.Transcripts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptsRepository extends JpaRepository<Transcripts, Long> {

}
