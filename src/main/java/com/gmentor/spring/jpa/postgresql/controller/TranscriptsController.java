package com.gmentor.spring.jpa.postgresql.controller;

import com.gmentor.spring.jpa.postgresql.model.Transcripts;
import com.gmentor.spring.jpa.postgresql.repository.TranscriptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TranscriptsController {

	@Autowired
	TranscriptsRepository transcriptsRepository;


	@PostMapping("/register")
	public ResponseEntity<Transcripts> createTranscript(@RequestBody Transcripts transcripts) {
		try {
			Transcripts _transcript = transcriptsRepository.save(new Transcripts(
					transcripts.getFilename(),
					transcripts.getUploadurl()
			));
			return new ResponseEntity<>(_transcript, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
