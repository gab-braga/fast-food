package io.github.fgabrielbraga.fastfoodsimulator.controllers;

import io.github.fgabrielbraga.fastfoodsimulator.models.Food;
import io.github.fgabrielbraga.fastfoodsimulator.models.Item;
import io.github.fgabrielbraga.fastfoodsimulator.models.Order;
import io.github.fgabrielbraga.fastfoodsimulator.repositories.FoodRepository;
import io.github.fgabrielbraga.fastfoodsimulator.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute("allFoods")
    public List<Food> populateFoods() {
        return foodRepository.findAll();
    }

    @ModelAttribute("allOrders")
    public List<Order> populateOrders() {
        return orderRepository.findAllSortedByClient();
    }

    @GetMapping
    public String order() {
        return "food-order";
    }

    @RequestMapping("/new")
    public String newOrder(Order order) {
        return "new-food-order";
    }

    @RequestMapping(value = "/new", params = {"addItem"})
    public String addItem(Order order, BindingResult bindingResult) {
        order.calculateTotal();
        order.getItems().add(new Item());
        return "new-food-order";
    }

    @RequestMapping(value = "/new", params = {"removeItem"})
    public String removeItem(Order order, BindingResult bindingResult, HttpServletRequest req) {
        order.calculateTotal();
        final Integer itemId = Integer.valueOf(req.getParameter("removeItem"));
        order.getItems().remove(itemId.intValue());
        return "new-food-order";
    }

    @RequestMapping(value = "/new", params = {"continue"})
    public String continueOrder(Order order, BindingResult bindingResult) {
        order.calculateTotal();
        return "new-food-order";
    }

    @RequestMapping(value = "/new", params = {"save"})
    public String saveOrder(@Valid Order order, BindingResult result, RedirectAttributes attributes) {
        order.calculateTotal();
        if(result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            for(ObjectError error : allErrors)
                errorMessages.add(error.getDefaultMessage());
            attributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/order/new";
        }
        orderRepository.save(order);
        return "redirect:/order";
    }
}
