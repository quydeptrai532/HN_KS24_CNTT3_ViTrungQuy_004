package com.example.hackathon.model.dto;

import com.example.hackathon.model.entity.Genre;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String director;
    private int duration_minutes;
    private Genre genre;
}
