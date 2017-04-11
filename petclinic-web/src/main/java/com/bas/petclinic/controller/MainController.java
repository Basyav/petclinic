package com.bas.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "auth.html";
    }

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET)
    public String owner() {
        return "/owner.html";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String employee() {
        return "/employee.html";
    }



}
