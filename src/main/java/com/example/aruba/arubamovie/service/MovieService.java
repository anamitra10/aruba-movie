package com.example.aruba.arubamovie.service;

import com.example.aruba.arubamovie.model.Movie;
import com.example.aruba.arubamovie.repository.moveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieService {

    @Autowired
    public moveRepository MovieRepository;


    public List<Movie> getMovies(String type, String country) {
        return MovieRepository.findByTypeAndCountry(type, country);

    }

    public String updateMovies(String id, String title, int date) {
        Optional<Movie> m1 = MovieRepository.findByIdOrTitle(title, id);
        if(m1.isEmpty()){
            return "No data found!";
        }
        else{
            Movie m2=m1.get();
            m2.setDate(date);
            MovieRepository.save(m2);
            return "Release date updated successfully";
        }
    }
    public List<Movie> loadDataInH2Db() {

        boolean firstline=true;
        String line="";
        List<Movie> records = new ArrayList<>();
        try {
            Movie movie;
            BufferedReader bufferedReader=new BufferedReader(new FileReader("src/main/resources/netflix_titles.csv"));
            log.debug("File Read Successfully form csv to buffer reader");
            while((line=bufferedReader.readLine())!=null) {

                if(firstline){
                    firstline=false;
                    continue;
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                log.info(Arrays.toString(values));
                movie=new Movie(values[0],values[1],values[2],values[5],Integer.parseInt(values[7]));
                records.add(movie);

            }


            log.info("Data Mapped successfully from csv to Model");

            return MovieRepository.saveAll(records);

//        } catch (FileNotFoundException e) {
//            throw new ResourceNotFoundException("CSV File Not Found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }

    }


