package ru.broject.site.auth.service;

import org.springframework.stereotype.Component;
import ru.broject.site.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by vyacheslav.svininyh on 22.01.2016.
 */
@Component
public class AuthServiceImpl implements AuthService {

    private static final Map<String, User> sessionIdToUser = new ConcurrentHashMap<>();
    private static final Map<String, User> registeredUsers = createUsers();

    private static Map<String, User> createUsers() {
        Map<String, User> registeredUsers = new HashMap<>();
        registeredUsers.put("admin", new User("admin", "admin"));
        registeredUsers.put("user", new User("user", "user"));
        return registeredUsers;
    }

    @Override
    public User getUserBySession(String sessionId) {
        return sessionIdToUser.get(sessionId);
    }

    @Override
    public void addSession(String sessionId, User user) {
        sessionIdToUser.put(sessionId, user);
    }

    @Override
    public void deleteSession(String sessionId) {
        sessionIdToUser.remove(sessionId);
    }

    @Override
    public User getUser(String login) {
        return registeredUsers.get(login);
    }

    @Override
    public boolean isUserValid(User user, String login, String password) {
        return login.equals(user.getLogin()) && password.equals(user.getPassword());
    }
}
