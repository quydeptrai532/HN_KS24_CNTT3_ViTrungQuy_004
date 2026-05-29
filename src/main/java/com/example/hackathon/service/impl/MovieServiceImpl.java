package com.example.hackathon.service.impl;

import com.example.hackathon.exception.NotFoundException;
import com.example.hackathon.model.dto.MovieRequestDTO;
import com.example.hackathon.model.dto.MovieResponseDTO;
import com.example.hackathon.model.entity.Movie;
import com.example.hackathon.repository.MovieRepository;
import com.example.hackathon.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    @Override
    public MovieResponseDTO create(MovieRequestDTO dto) {
        Movie movie=Movie.builder().title(dto.getTitle()).director(dto.getDirector()).
                duration_minutes(dto.getDuration_minutes()).genre(dto.getGenre()).
                isDeleted(false).build();
        movieRepository.save(movie);
        return mapToDTO(movie);
    }

    @Override
    public Page<MovieResponseDTO> findAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies;
        if(keyword==null || keyword.isEmpty()){
            movies = movieRepository.findAll(pageable);
        }
        else{
            movies=movieRepository.findByTitleContainingOrDirectorContaining(keyword, keyword, pageable);
        }
        return movies.map(this::mapToDTO);
    }

    @Override
    public MovieResponseDTO update(Long id, MovieRequestDTO movieDTO) {
        Movie movie=movieRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Khong tim thay movie"));
        movie.setTitle(movieDTO.getTitle());
        movie.setDirector(movieDTO.getDirector());
        movie.setDuration_minutes(movieDTO.getDuration_minutes());
        movie.setGenre(movieDTO.getGenre());
        movieRepository.save(movie);
        return mapToDTO(movie);
    }

    @Override
    public MovieResponseDTO patch(Long id, MovieRequestDTO dto) {
        Movie movie=movieRepository.findById(id).orElseThrow(()-> new NotFoundException("Khong tim thay movie"));
        if(dto.getTitle()!=null){
            movie.setTitle(dto.getTitle());
        }
        if(dto.getDirector()!=null){
            movie.setDirector(dto.getDirector());
        }
        if (dto.getDuration_minutes()>0){
            movie.setDuration_minutes(dto.getDuration_minutes());
        }
        if(dto.getGenre()!=null){
            movie.setGenre(dto.getGenre());
        }
        movieRepository.save(movie);
        return mapToDTO(movie);
    }

    @Override
    public void delete(Long id) {
        Movie movie=movieRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Khong tim thay movie"));
        movie.setDeleted(true);
        movieRepository.save(movie);
    }

    MovieResponseDTO mapToDTO(Movie movie) {
        return MovieResponseDTO.builder().id(movie.getId())
                .title(movie.getTitle()).director(movie.getDirector())
                .duration_minutes(movie.getDuration_minutes()).genre(movie.getGenre())
                .build();
    }
}
