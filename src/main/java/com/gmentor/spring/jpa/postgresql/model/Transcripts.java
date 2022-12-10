package com.gmentor.spring.jpa.postgresql.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transcript")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transcripts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transcript_id_generator")
	@SequenceGenerator(name = "transcript_id_generator", sequenceName = "transcript_id_seq", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "filename",unique = true)
	private String filename;

	@Column(name = "uploadurl")
	private String uploadurl;

	@Column(name = "transcriptid")
	private String transcriptid;


	public Transcripts(String filename, String uploadurl) {
		this.filename = filename;
		this.uploadurl = uploadurl;
	}
}
