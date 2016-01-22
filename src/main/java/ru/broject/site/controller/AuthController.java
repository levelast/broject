package ru.broject.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.broject.site.auth.service.AuthService;
import ru.broject.site.model.User;

import javax.servlet.http.HttpSession;

/**
 * Created by vyacheslav.svininyh on 22.01.2016.
 */
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView auth(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        User user = authService.getUser(login);

        if (user != null) {
            if (authService.isUserValid(user, login, password)) {
                authService.addSession(httpSession.getId(), user);
                mav.setViewName("redirect:");
                return mav;
            } else {
                mav.addObject("message", "Неверный логин или пароль");
                mav.setViewName("login");
                return mav;
            }
        } else {
            mav.addObject("message", "Пользователь незарегистрирован");
            mav.setViewName("login");
            return mav;
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession httpSession) {
        authService.deleteSession(httpSession.getId());
        ModelAndView mav = new ModelAndView("redirect:login");
        return mav;
    }
}
