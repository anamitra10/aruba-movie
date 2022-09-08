package com.example.aruba.arubamovie.service;



import com.example.aruba.arubamovie.model.Movie;
import com.example.aruba.arubamovie.repository.moveRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    public moveRepository movieRepository;

    @InjectMocks
    @Autowired
    MovieService movieService;


    @Test
    public void getMovies(){

        String type="Movie";
        String country="India";
        List<Movie> movieList=new LinkedList<>();
        Movie movie=new Movie("test","Movie","TestMovie","India",2022);

        movieList.add(movie);

        when(movieRepository.findByTypeAndCountry(type,country)).thenReturn(movieList);
        //movieRepository.save(movie);
        movieService.getMovies(type,country);

    }

    @Test
    public void updateReleaseDateTest() {

        int date=2000;
        String id="s1";
        String title="test";
        Movie movie=new Movie("s1","Movie","test","India",2022);

        when(movieRepository.findByIdOrTitle(title,id)).thenReturn(Optional.of(movie));
        //movieRepository.save(movie);
        movieService.updateMovies(id,title,date);

    }

    @Test
    public void updateReleaseDateEmptyTest() {

        int date=2000;
        String id="s1";
        String title="test";

        when(movieRepository.findByIdOrTitle(title,id)).thenReturn(Optional.empty());
        //movieRepository.save(movie);
        movieService.updateMovies(id,title,date);

    }

}
