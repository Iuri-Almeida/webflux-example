package br.com.ialmeida.webfluxexample.controllers;

import br.com.ialmeida.webfluxexample.entities.User;
import br.com.ialmeida.webfluxexample.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Flux<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<User>> findUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<User>> insertUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mono<Void>> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
