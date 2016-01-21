package ru.broject.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.broject.site.service.TestService;

import javax.sql.DataSource;

/**
 * Created by vyacheslav.svininyh on 21.01.2016.
 */
@Controller
public class IndexController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public ModelAndView testTemplate(@RequestParam(name = "param") String param) {
        ModelAndView mav = new ModelAndView("template");
        mav.addObject("param", param);
        return mav;
    }

    @RequestMapping(value = "/connection", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String testConnection() {
        return testService.testConnection() == 1 ? "Everything is good" : "Something failed";
    }
}
