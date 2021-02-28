package com.rj.ratingdataservice.resource;

import com.rj.ratingdataservice.resource.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRatingData(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }
}
