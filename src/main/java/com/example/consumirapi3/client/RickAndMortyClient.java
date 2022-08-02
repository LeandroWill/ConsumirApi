package com.example.consumirapi3.client;


import com.example.consumirapi3.response.CharacterResponse;
import com.example.consumirapi3.response.EpisodeResponse;
import com.example.consumirapi3.response.ListOfEpisodesResponse;
import com.example.consumirapi3.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RickAndMortyClient {

    //O "WebClient" já vem com o webflux.
    private final WebClient webClient;

    //Construtor que faz a ligação com a API usando a base Url dela.
    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    //Foi criado um client para cada endpoint.
    //Como estamos tratando de apenas um personagem e não uma lista utilizamos "Mono", para lista é "Flux".
    public Mono<CharacterResponse> findAnyCharacterByid(String id){

            log.info("Buscando o persoangem com o Id [{}]", id);
            return webClient
                    .get()
                    .uri("/character/" +id)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                    .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findAnLocationById(String id){

        log.info("Buscando a localizacao com o Id [{}]", id);
        return webClient
                .get()
                .uri("/location/" +id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findAnEpisodeById(String id){

        log.info("Buscando o episodio com o Id [{}]", id);
        return webClient
                .get()
                .uri("/episode/" +id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> getAllEpisodes(){

        log.info("Listando todos os episodios");
        return webClient
                .get()
                .uri("/episode/")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }
}
