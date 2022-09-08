package com.example.aruba.arubamovie.controller;

import com.example.aruba.arubamovie.model.Movie;
import com.example.aruba.arubamovie.repository.moveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    moveRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void setUp() throws Exception {
        movie=new Movie("test","Movie","TestMovie","India",2022);

    }


    @Test
    void getMovieListTest() throws Exception {

        Movie m1=movieRepository.save(movie);

        mockMvc.perform(get("/tvshows")
                        .param("type", "Movie")
                        .param("country", "India")
                        .contentType("application/json"))
                .andExpect(status().isOk())
        ;

    }

    @Test
    void releaseDateUpdateTest() throws Exception {

        mockMvc.perform(post("/releaseUpdate"+"/2000")
                        .param("id", "test")
                        .param("title", "TestMovie")
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    void saveCSVTest() throws Exception {

        mockMvc.perform(get("/save-from-csv")
                        .contentType("application/json"))
                .andExpect(status().isCreated());

    }

}
