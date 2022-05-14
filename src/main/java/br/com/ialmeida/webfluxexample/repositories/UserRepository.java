package br.com.ialmeida.webfluxexample.repositories;

import br.com.ialmeida.webfluxexample.entities.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
