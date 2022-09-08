package com.example.aruba.arubamovie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Entity
    @Table(name = "movie")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Movie {
        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        private String type;
        private String title;
        private String country;
        private Integer date;

    }
