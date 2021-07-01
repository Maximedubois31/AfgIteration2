package fr.afg.iteration1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.afg.iteration1.service.UserService;

/**
 * The type User controller.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * List users string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/users")
    public String listUsers(final Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


}
