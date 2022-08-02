package com.example.consumirapi3.controller;


import com.example.consumirapi3.client.RickAndMortyClient;
import com.example.consumirapi3.response.CharacterResponse;
import com.example.consumirapi3.response.EpisodeResponse;
import com.example.consumirapi3.response.ListOfEpisodesResponse;
import com.example.consumirapi3.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient") //rota da controler
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id){
        return rickAndMortyClient.findAnyCharacterByid(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id){
        return rickAndMortyClient.findAnLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id){
        return rickAndMortyClient.findAnEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodesResponse> getAllEpisodes(){
        return rickAndMortyClient.getAllEpisodes();
    }

}
