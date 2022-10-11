package com.emery.qwizzo.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.emery.qwizzo.models.Player;
import com.emery.qwizzo.models.UserLogin;
import com.emery.qwizzo.repositories.PlayerRepo;


@Service
public class PlayerServ {
	
	@Autowired
	private PlayerRepo playerRepo;
	
	
	public Player register(Player newPlayer, BindingResult results) {
		if (newPlayer.getPassword().equals(newPlayer.getConfirmPassword())) {
			newPlayer.setPassword(BCrypt.hashpw(newPlayer.getPassword(), BCrypt.gensalt()));
			return playerRepo.save(newPlayer);
		} else {
			results.rejectValue("confirmPassword", "Matches", "Passwords do not match");
			return null;
		}
		
	}
	
	
	public Player getOneById(Long id) {
		Optional<Player> player = playerRepo.findById(id);
		if (player.isPresent()) {
			return player.get();
			
		} else {
			return null;
		}
	}
	public List<Player> getAllPlayers(){
		return playerRepo.findAll();
	}
	
	public Player getOneByEmail(UserLogin userLogin, BindingResult result) {
		Optional<Player> player = playerRepo.findByEmail(userLogin.getEmail());
		if (player.isPresent()) {
			if (BCrypt.checkpw(userLogin.getPassword(), player.get().getPassword())) {
				return player.get();
				
			} else {
				
				result.rejectValue("email", "Login", "Invalid Username/Password");
				return null;
			}
		} else {
			result.rejectValue("email", "Login", "Invalid Username/Password");
			return null;
			
		}
	}
	
	public void deletePlayer(Long id) {
		  Optional<Player> optionalPlayerOptional = playerRepo.findById(id);
		  if(optionalPlayerOptional.isPresent()) {
			  playerRepo.deleteById(id);
		  }
	  }

}
