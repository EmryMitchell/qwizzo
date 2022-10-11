package com.emery.qwizzo.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emery.qwizzo.models.Player;
import com.emery.qwizzo.models.UserLogin;
import com.emery.qwizzo.services.PlayerServ;

@Controller
public class HomeController {
	
	@Autowired
	private PlayerServ playerServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newPlayer", new Player());
		model.addAttribute("userLogin", new UserLogin());
		return "index.jsp";
		
		
	}
	
	
	
	@GetMapping("/player")
	public String allPlayers(@ModelAttribute("player") PlayerServ player, Model model) {
		List<Player> players = playerServ.getAllPlayers();
		model.addAttribute("players", players);
		return "index.jsp";
	}
	

	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newPlayer") Player newPlayer, BindingResult 
			result, HttpSession session, Model model) {
		Player loggedPlayer = playerServ.register(newPlayer, result);
		if (result.hasErrors()) {
			model.addAttribute("userLogin", new UserLogin());
			return "index.jsp";
		} else {
			session.setAttribute("playerId", loggedPlayer.getId());
			return "redirect:/home";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult 
			result, HttpSession session, Model model) { 
		Player loggedPlayer = playerServ.getOneByEmail(userLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("newPlayer", new Player());
			return "index.jsp";
			
		} else {
			if (BCrypt.checkpw(userLogin.getPassword(), loggedPlayer.getPassword())) {
				session.setAttribute("playerId", loggedPlayer.getId());
				return "redirect:/home";
			} else {
				return "index.jsp";
			}
		}
		
		
	}
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("playerId");
		model.addAttribute("player", playerServ.getOneById(id));
		return "home.jsp";
	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("playerId");
		return "redirect:/";
	}

}
