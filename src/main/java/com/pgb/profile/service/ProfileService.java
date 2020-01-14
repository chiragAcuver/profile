package com.pgb.profile.service;

import com.pgb.profile.config.ServiceConfigurations;
import com.pgb.profile.data.Profile;
import com.pgb.profile.data.couchbase.ReactiveProfileRepository;
import com.pgb.profile.data.dto.ContactDTO;
import com.pgb.profile.data.dto.CreateProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class ProfileService {
    private final ReactiveProfileRepository profileRepository;
    private final WebClient.Builder webClient;
    private final ServiceConfigurations configurations;

    public ProfileService(ReactiveProfileRepository profileRepository, WebClient.Builder webClient, ServiceConfigurations configurations) {
        this.profileRepository = profileRepository;
        this.webClient = webClient;
        this.configurations = configurations;
    }
    // private final ReactorLoadBalancerExchangeFilterFunction lbFunction;




    public  Mono<ResponseEntity<Void>> post(CreateProfileDTO profileDTO){
        String id= UUID.randomUUID().toString();
        profileDTO.getProfile().setId(id);
        profileDTO.getContactDTO().setId(id);
        profileRepository.save(profileDTO.getProfile()).subscribe();
        return postIdentity(profileDTO).onErrorResume(HttpServerErrorException.class, e -> {
            log.info("Error Occured in invoking identity api : {} ",e.getMessage());
            return Mono.just(new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE));
        });


    }

    public Flux<Profile> findAll(){
        return profileRepository.findAll();
    }

    public Mono<ResponseEntity<Profile>> findById(String id){
        return profileRepository.findById(id)
                .map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    private Mono<ResponseEntity<Void>> postIdentity(CreateProfileDTO profileDTO) {
        return webClient
                //.filter(lbFunction)
                .build()
                .post()
                .uri(configurations.API_IDENTITY_SIGNUP_HOST+configurations.API_IDENTITY_SIGNUP_ENDPOINT)
                .body(Mono.just(profileDTO.getContactDTO()),ContactDTO.class)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError,clientResponse ->{
                    log.info("Error occurred in invoking identity api, deleting the saved profile");
                    profileRepository.delete(profileDTO.getProfile()).subscribe();// On 500 error for identity service delete the previously saved profile
                    return Mono.error(new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE));
                })

                .toBodilessEntity();
    }
    // TODO  use onStatus for error handling https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
}
