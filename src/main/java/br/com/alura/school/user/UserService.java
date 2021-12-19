package br.com.alura.school.user;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()){
            return user.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, format("Invalid username"));
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
