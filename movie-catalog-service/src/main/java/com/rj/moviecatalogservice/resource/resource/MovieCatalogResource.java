package com.rj.moviecatalogservice.resource.resource;

import com.rj.moviecatalogservice.resource.model.CatalogItem;
import com.rj.moviecatalogservice.resource.model.Movie;
import com.rj.moviecatalogservice.resource.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cataloge")
public class MovieCatalogResource {

    // Get all rated movie IDs
    // for each movie id call movie info service and get details
    // put them all together
    @RequestMapping("/{userId}")
 public List<CatalogItem> getCataloge(@PathVariable("userId") String userId){
        RestTemplate restTemplate = new RestTemplate();

        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("456",5)
        );

        return ratings.stream().map(rating-> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
        })
                .collect(Collectors.toList());

        //return Collections.singletonList(new CatalogItem("DDLJ","Romantic Movie",5));
    }
}
