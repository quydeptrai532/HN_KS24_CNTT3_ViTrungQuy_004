package com.example.hackathon.controller;

import com.example.hackathon.model.dto.MovieRequestDTO;
import com.example.hackathon.model.dto.MovieResponseDTO;
import com.example.hackathon.model.entity.Movie;
import com.example.hackathon.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping
    MovieResponseDTO create(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return movieService.create(movieRequestDTO);
    }

    @GetMapping
    Page<MovieResponseDTO> getAll(@RequestParam(defaultValue = "")String keyword,
                                  @RequestParam(defaultValue = "0")int page,
                                  @RequestParam(defaultValue = "5") int size){
        return movieService.findAll(keyword, page, size);
    }

    @PutMapping("/{id}")
    MovieResponseDTO update(@PathVariable Long id, @Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return movieService.update(id,movieRequestDTO);
    }

    @PatchMapping("/{id}")
    MovieResponseDTO patch(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO) {
        return movieService.patch(id, movieRequestDTO);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        movieService.delete(id);
    }
}
