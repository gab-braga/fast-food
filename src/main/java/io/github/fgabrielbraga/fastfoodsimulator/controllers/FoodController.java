package io.github.fgabrielbraga.fastfoodsimulator.controllers;

import io.github.fgabrielbraga.fastfoodsimulator.models.Food;
import io.github.fgabrielbraga.fastfoodsimulator.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/settings")
    public ModelAndView listFood() {
        ModelAndView modelAndView = new ModelAndView("settings");
        modelAndView.addObject(new Food());
        modelAndView.addObject("foods", foodRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public String foodSave(@Valid Food food, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            for(ObjectError error : allErrors)
                errorMessages.add(error.getDefaultMessage());
            attributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/food/settings";
        }

        foodRepository.save(food);
        return "redirect:/food/settings";
    }

    @GetMapping("/delete")
    public String foodDelete(Long code) {
        Food food = foodRepository.findById(code).get();
        foodRepository.delete(food);
        return "redirect:/food/settings";
    }
}
