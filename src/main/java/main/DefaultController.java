package main;

import main.model.Game;
import main.model.GameRepository;
import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for(User user : userIterable){
            users.add(user);
        }
        Iterable<Game> gameIterable = gameRepository.findAll();
        ArrayList<Game> games = new ArrayList<>();
        for(Game game : gameIterable){
            games.add(game);
        }

        model.addAttribute("users", users);
        model.addAttribute("games", games);
        return "index";
    }
}
