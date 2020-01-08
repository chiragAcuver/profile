package com.pgb.profile.web;

import com.pgb.profile.data.Profile;
import com.pgb.profile.data.dto.CreateProfileDTO;
import com.pgb.profile.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profiles")
    public Mono<ResponseEntity<Void>> post(@RequestBody CreateProfileDTO prof){
        return profileService.post(prof);
    }

    @GetMapping("/profiles")
    public Flux<Profile> findAll(){
        return profileService.findAll();
    }

    @GetMapping("/profiles/{id}")
    public Mono<ResponseEntity<Profile>> findById(@PathVariable("id") String id){
        return profileService.findById(id);
    }
}
