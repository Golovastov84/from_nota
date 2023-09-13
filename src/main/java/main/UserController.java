package main;

import main.model.Game;
import main.model.GameRepository;
import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> ListUser() {
        Iterable<User> userIterable = userRepository.findAll();

        ArrayList<User> users = new ArrayList<>();
        for (User user : userIterable) {
            users.add(user);
        }
        return users;
    }

    @PostMapping("/users")
    public int addUser(User user) {
        if (userRepository.count() == 0) {
            user.setId(1);
        }
        boolean similarName = false;
        for(User userBase : userRepository.findAll()){
            if(userBase.getName().equals(user.getName())){
                similarName = true;
                break;
            }
        }
        if(similarName){
            return -1;
        } else {
            User newUser = userRepository.save(user);
            return newUser.getId();
        }
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<?> getUser(@PathVariable String name) {
        for(User userFind : userRepository.findAll()){
            if(userFind.getName().equals(name)){
                return new ResponseEntity<>(userFind, HttpStatus.OK);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> dellUser(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(userRepository.count(), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> putUserId(User newUser, @PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity dellAllUsers() {
        if (userRepository.count() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The list is already empty.");
        }
        userRepository.deleteAll();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
