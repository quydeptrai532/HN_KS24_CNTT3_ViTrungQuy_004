package com.example.hackathon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private int duration_minutes;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
