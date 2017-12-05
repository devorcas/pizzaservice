package ua.rd.web.html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;
import ua.rd.service.PizzaService;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping("/get")
    @Secured("hasRole('USER')")
    public String findAllPizzas(Model model) {
        List<Pizza> pizzas = pizzaService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        model.addAttribute("pizzas", pizzas);
        return "pizzas";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removePizza(@RequestParam("id") Long id, Model model) {
        pizzaService.remove(id);
        return findAllPizzas(model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPizza(@RequestParam("name") String name,
                           @RequestParam("price") Double price,
                           @RequestParam("type") PizzaType type,
                           Model model) {
        Pizza pizza = new Pizza(name,price,PizzaType.MEAT);
        pizzaService.save(pizza);
        return findAllPizzas(model);
    }

    public Pizza getById(Model model) {
        return pizzaService.getById(1L);
    }

    public Pizza save(Pizza entity) {
        return pizzaService.save(entity);
    }

    public boolean remove(Long id) {
        return pizzaService.remove(id);
    }
}
