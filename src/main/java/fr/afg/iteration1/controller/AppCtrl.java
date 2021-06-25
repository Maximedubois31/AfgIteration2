package fr.afg.iteration1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
//@RequestMapping(value="/mvc/welcome" , produces = "text/html;charset=UTF-8")
@RequestMapping("/app")
public class AppCtrl {

	@ModelAttribute("idSession")
	public String idSession(HttpSession session) {
		return session.getId();
	}
	
	@RequestMapping("/to-welcome")
	String toWelcome(Model model) {
		model.addAttribute("message", "bienvenu(e)");
		model.addAttribute("title","welcome");
	    return "welcome"; 
	}
	
	@RequestMapping("/to-login")
	String toLogin(Model model) {
		model.addAttribute("title","login");
	    return "login"; 
	}
	

	
	@RequestMapping("/session-end")
    public String finSession(Model model,HttpSession session) {
	    SecurityContextHolder.clearContext(); //RAZ context Spring security
	    session.invalidate();
        model.addAttribute("message", "session terminée");
        model.addAttribute("title","welcome");
        return "welcome"; 
    }
	

}
