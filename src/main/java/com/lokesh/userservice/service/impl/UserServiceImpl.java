package com.lokesh.userservice.service.impl;

import com.lokesh.userservice.model.User;
import com.lokesh.userservice.repository.UserRepository;
import com.lokesh.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Mono<User> delete(String id) {
        return this.userRepository
                .findById(id).flatMap(p ->
                        this.userRepository
                                .deleteById(p.getUserId())
                                .thenReturn(p));
    }
    @Override
    public Mono<User> update(String id, User user) {

        return this.userRepository.findById(id)
                .flatMap(u -> {
                    u.setUserId(id);
                    u.setEmailId(user.getEmailId());
                    u.setName(user.getName());
                    return save(u);
                }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<User> findAll() {
        log.info("getting all users...");
        return this.userRepository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return this.userRepository.findById(id);
    }
}
