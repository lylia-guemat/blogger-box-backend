package com.dauphine.blogger_box_backend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name= "Hello world API",
        description= "My first world endpoints"
)
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World !";
    }

    @GetMapping("hello")
    public String helloByName(@RequestParam String name) {
        return "Hello " + name ;
    }

    // @PathVariable allow us to extract data from the URL path
    @GetMapping("hello/{name}")
    public String hello (@PathVariable String name) {
        return "Hello " + name;
    }

}
