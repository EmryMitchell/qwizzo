package com.emery.qwizzo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.emery.qwizzo.models.Player;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Long> {
	
	Optional<Player> findById(Long id);
	Optional<Player> findByEmail(String email);
	List<Player> findAll();
	

}
