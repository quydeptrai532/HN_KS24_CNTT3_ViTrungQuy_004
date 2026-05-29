package com.example.hackathon.service;

import com.example.hackathon.model.dto.MovieRequestDTO;
import com.example.hackathon.model.dto.MovieResponseDTO;
import org.springframework.data.domain.Page;

public interface MovieService {
    MovieResponseDTO create(MovieRequestDTO movieDTO);
    Page<MovieResponseDTO> findAll(String keyword, int page, int size);
    MovieResponseDTO update(Long id, MovieRequestDTO movieDTO);
    MovieResponseDTO patch(Long id, MovieRequestDTO movieDTO);
    void delete(Long id);
}
