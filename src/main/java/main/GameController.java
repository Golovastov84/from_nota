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
public class GameController {


    @Autowired
    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @GetMapping("/games")
    public List<Game> ListGame() {
        Iterable<Game> gameIterable = gameRepository.findAll();
        ArrayList<Game> games = new ArrayList<>();
        for (Game game : gameIterable) {
            games.add(game);
        }
        return games;
    }

    @PostMapping("/games")
    public int addGame(Game game) {
        if (gameRepository.count() == 0) {
            game.setId(1);
        }
        Game newGame = gameRepository.save(game);
        return newGame.getId();
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<?> getGame(@PathVariable int id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (!optionalGame.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(optionalGame.get(), HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> dellGame(@PathVariable int id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (!optionalGame.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        gameRepository.deleteById(id);
        return new ResponseEntity<>(gameRepository.count(), HttpStatus.OK);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<?> putGameId(Game newGame, @PathVariable int id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (!optionalGame.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        gameRepository.save(newGame);
        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }

    @DeleteMapping("/games")
    public ResponseEntity dellAllGames() {
        if (gameRepository.count() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The list is already empty.");
        }
        gameRepository.deleteAll();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
