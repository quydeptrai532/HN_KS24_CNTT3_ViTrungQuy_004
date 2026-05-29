package com.example.hackathon.model.dto;

import com.example.hackathon.model.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MovieRequestDTO {
    @NotBlank(message = "Tên ko được để trống")
    private String title;
    @NotBlank(message = "Tên đạo diễn ko được để trống")
    private String director;
    @Positive(message = "Thời lượng phim phải lớn hơn 0")
    private int duration_minutes;
    private Genre genre;
}
