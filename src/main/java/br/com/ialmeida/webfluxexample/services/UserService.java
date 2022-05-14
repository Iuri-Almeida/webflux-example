package br.com.ialmeida.webfluxexample.services;

import br.com.ialmeida.webfluxexample.entities.User;
import br.com.ialmeida.webfluxexample.repositories.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(br.com.ialmeida.webfluxexample.repositories.UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> insertUser(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }
}
