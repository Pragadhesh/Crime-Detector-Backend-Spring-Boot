package com.gmentor.spring.jpa.postgresql.controller;

import com.gmentor.spring.jpa.postgresql.dto.CreateTranscript;
import com.gmentor.spring.jpa.postgresql.model.Transcripts;
import com.gmentor.spring.jpa.postgresql.repository.TranscriptsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TranscriptsController<Gson> {

	@Autowired
	TranscriptsRepository transcriptsRepository;

	@Autowired
	private RestTemplate restTemplate;

	String url = "https://api.assemblyai.com/v2/transcript";

	@PostMapping("/register")
	public ResponseEntity<Transcripts> createTranscript(@RequestBody Transcripts transcripts) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("authorization","485ffa2783fe4d80b818b28a1a50aa0d");

			JSONObject body = new JSONObject();
			body.put("audio_url", transcripts.getUploadurl());
			body.put("content_safety", true);
			body.put("summarization", true);
			body.put("iab_categories", true);
			body.put("auto_highlights",true);
			
			HttpEntity request = new HttpEntity(body.toString(),headers);
			
			CreateTranscript response = restTemplate.exchange(
					url,
					HttpMethod.POST,
					request,
					CreateTranscript.class
			).getBody();
			System.out.println(response);
			Transcripts _transcript = transcriptsRepository.save(new Transcripts(
					transcripts.getFilename(),
					transcripts.getUploadurl(),
					response.getId()
			));
			return new ResponseEntity<>(_transcript, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transcripts")
	public ResponseEntity<List<Transcripts>> getTranscripts()
	{
		List<Transcripts> events = transcriptsRepository.findAll();
		if (events.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(events, HttpStatus.OK);
		}
	}





}
