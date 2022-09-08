package com.example.aruba.arubamovie.controller;


import com.example.aruba.arubamovie.model.Movie;
import com.example.aruba.arubamovie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Slf4j

public class movieController {

    @Autowired
    public MovieService movieService;

    @GetMapping("/tvshows")
    public List<Movie> getMovieList(@RequestParam("type")String type, @RequestParam("country")String country){
        return movieService.getMovies(type,country);
    }

    @PostMapping("/releaseUpdate/{date}")
    public String updateMovieList(@PathVariable("date")int date, @RequestParam("id")String id, @RequestParam("title")String title){
        log.info("inside controller");
        return movieService.updateMovies(id,title,date);
    }
    @GetMapping("/save-from-csv")
    public ResponseEntity<List<Movie>> saveAll(HttpServletResponse res){

        long startTime = System.currentTimeMillis();
        log.info("save movies from csv to dynamoDb");
        List<Movie> result=movieService.loadDataInH2Db();
        long endTime = System.currentTimeMillis();

//        calculateExecutionTime(startTime,endTime,res);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



}
