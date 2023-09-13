package main;


import main.model.Game;
import main.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private static int currentId = 1;
    
    private static int currentGameId = 1;
    private static final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    
    private static final ConcurrentHashMap<Integer, Game> games = new ConcurrentHashMap<>();

    public static List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        usersList.addAll(users.values());
        return usersList;
    }

    public static int addUser(User user) {
        int id = currentId++;
        user.setId(id);
        users.put(id, user);
        return id;
    }

    public static int setUser(User user) {
        int IdUser = user.getId();
        users.put(IdUser, user);
        return IdUser;
    }

    public static User getUser(int userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        }
        return null;
    }

    public static int dellUser(int userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return userId;
        }
        return 0;
    }

    public static int dellAllUser() {
        users.clear();
        currentId = 1;
        return 0;
    }
    
//    для game

    public static List<Game> getAllGames() {
        ArrayList<Game> gamesList = new ArrayList<>();
        gamesList.addAll(games.values());
        return gamesList;
    }

    public static int addGame(Game game) {
        int id = currentGameId++;
        game.setId(id);
        games.put(id, game);
        return id;
    }

    public static int setGame(Game game) {
        int IdGame = game.getId();
        games.put(IdGame, game);
        return IdGame;
    }

    public static Game getGame(int gameId) {
        if (games.containsKey(gameId)) {
            return games.get(gameId);
        }
        return null;
    }

    public static int dellGame(int gameId) {
        if (games.containsKey(gameId)) {
            games.remove(gameId);
            return gameId;
        }
        return 0;
    }

    public static int dellAllGame() {
        games.clear();
        currentGameId = 1;
        return 0;
    }
}