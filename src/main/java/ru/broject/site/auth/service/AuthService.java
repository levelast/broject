package ru.broject.site.auth.service;

import ru.broject.site.model.User;

/**
 * Created by vyacheslav.svininyh on 22.01.2016.
 */
public interface AuthService {

    User getUserBySession(String sessionId);

    void addSession(String sessionId, User user);

    void deleteSession(String sessionId);

    User getUser(String login);

    boolean isUserValid(User user, String login, String password);
}
