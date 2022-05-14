package br.com.ialmeida.webfluxexample.services;

import br.com.ialmeida.webfluxexample.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFlux
public class UserController2 {

    private static final String BASE_URL = "/v2/users";
    private final UserService userService;

    public UserController2(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public RouterFunction<ServerResponse> getAllUser() {
        return RouterFunctions.route().GET(BASE_URL, request -> ServerResponse.ok().body(userService.findAll(), User.class)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> getUserById() {
        return RouterFunctions.route()
                .GET(BASE_URL.concat("/{id}"), request -> {
                    String id = request.pathVariable("id");
                    return ServerResponse.ok().body(userService.findUserById(id), User.class);
                }).build();
    }

    @Bean
    public RouterFunction<ServerResponse> createUser() {
        return RouterFunctions.route()
                .POST(BASE_URL, request -> request.bodyToMono(User.class)
                        .flatMap(userService::insertUser)
                        .flatMap(user -> ServerResponse.status(HttpStatus.CREATED).body(Mono.just(user), User.class)))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deleteById() {
        return RouterFunctions.route()
                .DELETE(BASE_URL.concat("/{id}"), request -> {
                    String id = request.pathVariable("id");
                    return ServerResponse.ok().body(userService.deleteUser(id), User.class);
                }).build();
    }

}
