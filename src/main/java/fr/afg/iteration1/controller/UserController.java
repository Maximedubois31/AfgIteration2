package fr.afg.iteration1.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afg.iteration1.entity.Company;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.UserService;

/**
 * The type User controller.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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

    @GetMapping("/user")
    public String getUser(final Model model, final HttpSession session) {

        User user = userService.getUserById((Long) session.getAttribute("idUser"));

        Company company = user.getCompany();

        model.addAttribute("user", user);
        model.addAttribute("company", company);
        return "user";
    }

    /**
     * Post product edit string.
     *
     * @param model   the model
     * @param user    the user
     * @param company the company
     * @return the string
     */
    @PostMapping("/user")
    public String postUserEdit(final Model model, @ModelAttribute("user") final User user,
            @ModelAttribute("company") final Company company) {

        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);

        return "redirect:/user";
    }

}
