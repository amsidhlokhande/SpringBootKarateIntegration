package com.amsidh.mvc.springbootkarateintegration.controller;


import com.amsidh.mvc.springbootkarateintegration.exception.BadRequestException;
import com.amsidh.mvc.springbootkarateintegration.exception.ForbiddenException;
import com.amsidh.mvc.springbootkarateintegration.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = {"/hello/{name}"})
    public String hello(@PathVariable(name = "name") String name) {
        if (name.equalsIgnoreCase("bad")) {
            throw new BadRequestException("Custom Bad request exception thrown");
        } else if (name.equalsIgnoreCase("notfound")) {
            throw new NotFoundException("Custom NotFound request exception thrown");
        } else if (name.equalsIgnoreCase("forbidden")) {
            throw new ForbiddenException("To show an example of a custom message");
        }

        return "Hello! " + name + ", How are you?";
    }
}
