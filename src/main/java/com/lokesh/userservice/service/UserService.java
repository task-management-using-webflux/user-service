package com.lokesh.userservice.service;

import com.lokesh.userservice.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> save(User user);
    Mono<User> delete(String id);
    Mono<User> update(String id, User user);
    Flux<User> findAll();
    Mono<User> findById(String id);
}
