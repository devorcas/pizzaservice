package ua.rd.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.service.PizzaService;

import java.security.Principal;
import java.util.List;

/**
 * Created by laguna on 25.05.2016.
 */
@RestController
public class RestPizzaController {
    @Autowired
    private PizzaService pizzaService;

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    //@ResponseBody
    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public List<Pizza> view() {
        return pizzaService.findAll();
    }
}
