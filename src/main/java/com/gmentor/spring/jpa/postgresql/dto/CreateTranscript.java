package com.gmentor.spring.jpa.postgresql.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateTranscript {

    private String id;

    private String status;

    private String language_code;

}
