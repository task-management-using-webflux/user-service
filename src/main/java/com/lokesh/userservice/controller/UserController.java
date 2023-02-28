package com.lokesh.userservice.controller;


import com.lokesh.userservice.model.User;
import com.lokesh.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<User> save(@RequestBody User user) {
        log.info("user is added...");
        return this.userService.save(user);
    }
    @DeleteMapping("/users/{id}")
    private Mono<ResponseEntity<String>> delete(@PathVariable("id") String id) {
        return this.userService.delete(id)
                .flatMap(user -> Mono.just(ResponseEntity
                        .ok("Deleted Successfully")))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .notFound().build()));
    }
    @PutMapping("/users/{id}")
    private Mono<ResponseEntity<User>> update
            (@PathVariable("id") String id,
             @RequestBody User user) {

        return this.userService.update(id, user)
                .flatMap(user1 -> Mono.just(ResponseEntity
                        .ok(user1))).switchIfEmpty(Mono
                        .just(ResponseEntity.notFound().build()));

    }
    @GetMapping(value = "/users")
    private Flux<User> findAll() {
        log.info("getting all users...");
        return this.userService.findAll();
    }
}
